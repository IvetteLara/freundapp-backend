INSERT INTO usuario(id_usuario, username, clave, estado) VALUES (1, 'ivettelaral@yahoo.com', '$2a$10$2jzSifyKXypDzm8ZVQZWKuqXR5TAJY139xBcT7GVfrS7sTYNP4Lfu', true);
INSERT INTO usuario(id_usuario, username, clave, estado) VALUES (2, 'freund@yahoo.com', 'xxx', true);

INSERT INTO Rol (id_rol, nombre, descripcion) VALUES (1, 'ADMIN', 'Administrador');
INSERT INTO Rol (id_rol, nombre, descripcion) VALUES (2, 'USER', 'Usuario');

INSERT INTO usuario_rol (id_usuario, id_rol) VALUES (1, 1);
INSERT INTO usuario_rol (id_usuario, id_rol) VALUES (2, 1);

INSERT INTO menu(id_menu, nombre, icono, url) VALUES (1, 'Ordenes', 'insert_drive_file', '/orden');
INSERT INTO menu(id_menu, nombre, icono, url) VALUES (2, 'Clientes', 'healing', '/cliente');
INSERT INTO menu(id_menu, nombre, icono, url) VALUES (3, 'Productos', 'accessibility', '/producto');

INSERT INTO menu_rol(id_menu, id_rol) VALUES(1,1);
INSERT INTO menu_rol(id_menu, id_rol) VALUES(2,1);
INSERT INTO menu_rol(id_menu, id_rol) VALUES(3,1);
INSERT INTO menu_rol(id_menu, id_rol) VALUES(1,2);

INSERT INTO unidad_med(id_unidad, nombre) VALUES(1, 'unidades');
INSERT INTO unidad_med(id_unidad, nombre) VALUES(2, 'libras');
INSERT INTO unidad_med(id_unidad, nombre) VALUES(3, 'centimetros');
INSERT INTO unidad_med(id_unidad, nombre) VALUES(4, 'cuartos');

INSERT INTO producto(id_producto, nombre, precio, id_unidad) VALUES(1, 'ventana', 2.5, 1);
INSERT INTO producto(id_producto, nombre, precio, id_unidad) VALUES(2, 'martillo', 5, 1);
INSERT INTO producto(id_producto, nombre, precio, id_unidad) VALUES(3, 'cacerola', 7.5, 1);
INSERT INTO producto(id_producto, nombre, precio, id_unidad) VALUES(4, 'bombillo', 2.8, 1);

INSERT INTO cliente(id_cliente, nombres, apellidos, numdoc, email, telefono) VALUES(1, 'Juan Jose', 'Perez', '335246', 'juan_perez@yahoo.com', '22745689');
INSERT INTO cliente(id_cliente, nombres, apellidos, numdoc, email, telefono) VALUES(2, 'Ana Maria', 'Chavez', '275246', 'maria_chavez@gmail.com', '22251210');


