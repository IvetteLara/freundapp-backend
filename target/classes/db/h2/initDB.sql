CREATE SEQUENCE reset_token_id_seq
    INCREMENT BY 1
    START WITH  1
    MINVALUE 1
    MAXVALUE 2147483647
	NOCACHE 
	NOCYCLE;
	
CREATE SEQUENCE rol_id_rol_seq
    INCREMENT BY 1
    START WITH  1
    MINVALUE 1
    MAXVALUE 2147483647
	NOCACHE 
	NOCYCLE;
	
CREATE SEQUENCE menu_id_menu_seq
    INCREMENT BY 1
    START WITH  1
    MINVALUE 1
    MAXVALUE 2147483647
	NOCACHE 
	NOCYCLE;

CREATE SEQUENCE unidadmed_id_unidad_seq
    INCREMENT BY 1
    START WITH  1
    MINVALUE 1
    MAXVALUE 2147483647
	NOCACHE 
	NOCYCLE;
	
CREATE SEQUENCE producto_id_producto_seq
    INCREMENT BY 1
    START WITH  1
    MINVALUE 1
    MAXVALUE 2147483647
	NOCACHE 
	NOCYCLE;
	
CREATE SEQUENCE cliente_id_cliente_seq
    INCREMENT BY 1
    START WITH  1
    MINVALUE 1
    MAXVALUE 2147483647
	NOCACHE 
	NOCYCLE;

CREATE SEQUENCE orden_id_orden_seq
    INCREMENT BY 1
    START WITH  1
    MINVALUE 1
    MAXVALUE 2147483647
	NOCACHE 
	NOCYCLE;
	
CREATE SEQUENCE orden_detalle_id_detalle_seq
    INCREMENT BY 1
    START WITH  1
    MINVALUE 1
    MAXVALUE 2147483647
	NOCACHE 
	NOCYCLE;



CREATE TABLE oauth_access_token (
  token_id VARCHAR(256),
  token bytea,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication bytea,
  refresh_token VARCHAR(256)
);

CREATE TABLE oauth_refresh_token (
  token_id VARCHAR(256),
  token bytea,
  authentication bytea
);

CREATE TABLE reset_token
(
    id integer NOT NULL DEFAULT nextval('reset_token_id_seq'::regclass),
    expiracion timestamp without time zone NOT NULL,
    token character varying(255) NOT NULL,
    id_usuario integer NOT NULL,
    CONSTRAINT reset_token_pkey PRIMARY KEY (id),
    CONSTRAINT uk_shiutqgqq3m7hdrlmckbk4am6 UNIQUE (token),
    CONSTRAINT fkoc8cqwnb1m8ijoboimhcybrl4 FOREIGN KEY (id_usuario)
        REFERENCES usuario (id_usuario) 
);

CREATE TABLE usuario
(
    id_usuario integer NOT NULL,
    estado boolean NOT NULL,
    clave character varying(255) NOT NULL,
    username character varying(255) NOT NULL,
    CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario),
    CONSTRAINT uk_863n1y3x0jalatoir4325ehal UNIQUE (username)
);


CREATE TABLE rol
(
    id_rol integer NOT NULL DEFAULT nextval('rol_id_rol_seq'::regclass),
    descripcion character varying(150),
    nombre character varying(50) NOT NULL,
    CONSTRAINT rol_pkey PRIMARY KEY (id_rol)
);

CREATE TABLE menu
(
    id_menu integer NOT NULL DEFAULT nextval('menu_id_menu_seq'::regclass),
    icono character varying(20) NOT NULL,
    nombre character varying(20) NOT NULL,
    url character varying(50) NOT NULL,
    CONSTRAINT menu_pkey PRIMARY KEY (id_menu)
);

CREATE TABLE usuario_rol
(
    id_usuario integer NOT NULL,
    id_rol integer NOT NULL,
    CONSTRAINT fk3ftpt75ebughsiy5g03b11akt FOREIGN KEY (id_usuario)
        REFERENCES usuario (id_usuario) ,
    CONSTRAINT fkkxcv7htfnm9x1wkofnud0ewql FOREIGN KEY (id_rol)
        REFERENCES rol (id_rol) 
);

CREATE TABLE menu_rol
(
    id_menu integer NOT NULL,
    id_rol integer NOT NULL,
    CONSTRAINT fk8uyjomykqlidw6whr5a9vx16d FOREIGN KEY (id_menu)
        REFERENCES menu (id_menu) ,
    CONSTRAINT fkjtnyb2364scc8ojb7vwh2jflj FOREIGN KEY (id_rol)
        REFERENCES rol (id_rol) 
);


CREATE TABLE unidad_med
(
    id_unidad integer NOT NULL DEFAULT nextval('unidadmed_id_unidad_seq'::regclass),
    nombre character varying(70) NOT NULL,
    CONSTRAINT unidadmed_pkey PRIMARY KEY (id_unidad)
);

CREATE TABLE producto
(
    id_producto integer NOT NULL DEFAULT nextval('producto_id_producto_seq'::regclass),
    nombre character varying(70) NOT NULL,
    precio double precision NOT NULL,
    id_unidad integer NOT NULL,
    CONSTRAINT producto_pkey PRIMARY KEY (id_producto),
    CONSTRAINT fk_producto_unidad FOREIGN KEY (id_unidad)
        REFERENCES unidad_med (id_unidad) 
);

CREATE TABLE cliente
(
    id_cliente integer NOT NULL DEFAULT nextval('cliente_id_cliente_seq'::regclass),
    nombres character varying(70) NOT NULL,
    apellidos character varying(70) NOT NULL,
    numdoc character varying(9),
    email character varying(55),
    telefono character varying(15),
    CONSTRAINT cliente_pkey PRIMARY KEY (id_cliente)
);

CREATE TABLE orden
(
    id_orden integer NOT NULL DEFAULT nextval('orden_id_orden_seq'::regclass),
    descripcion character varying(255),
    fecha timestamp without time zone NOT NULL,
    num_orden character varying(255),
    id_cliente integer NOT NULL,
    monto double precision DEFAULT 0,
    CONSTRAINT orden_pkey PRIMARY KEY (id_orden),
    CONSTRAINT fk_orden_cliente FOREIGN KEY (id_cliente)
        REFERENCES cliente (id_cliente) 
);

CREATE TABLE orden_detalle
(
    id_detalle integer NOT NULL DEFAULT nextval('orden_detalle_id_detalle_seq'::regclass),
    cantidad integer NOT NULL,
    precio double precision NOT NULL,
    id_orden integer NOT NULL,
    id_producto integer NOT NULL,
    CONSTRAINT orden_detalle_pkey PRIMARY KEY (id_detalle),
    CONSTRAINT fk_orden_detalle FOREIGN KEY (id_orden)
        REFERENCES orden (id_orden) ,
    CONSTRAINT fk_orden_producto FOREIGN KEY (id_producto)
        REFERENCES producto (id_producto) 
)










