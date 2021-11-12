# cashonline
Api rest para crud de usuarios con prestamos y paginación
REQUERIMIENTO: tener instaladas y configuradas en la pc la version de java jdk 11, maven 3, git y postman.

#PASO 1: en la pc en el disco C: crear una carpeta que se llame broker
![image](https://user-images.githubusercontent.com/6340170/141404027-e3a66c70-f3e5-48cc-a449-f3933e73034e.png)

#PASO 2: con git bash situarse dentre de la carpeta C:\broker
![image](https://user-images.githubusercontent.com/6340170/141404162-e157143c-b27d-4097-ac23-f5e4685d0778.png)

#PASO 3: bajarse el proyecto desde git con el siguiente comando:
git clone https://github.com/edumarvega/cashonline.git
![image](https://user-images.githubusercontent.com/6340170/141404355-0a29c5c5-75dc-4315-ac4e-3054e48dd269.png)
![image](https://user-images.githubusercontent.com/6340170/141404416-6c843f09-4f20-47f8-ac8a-f42c51914a4b.png)

#PASO 4:Con git bash Ingresar a C:\broker\cashonline, pararse sobre el master con el siguiente comando:
git checkout master
![image](https://user-images.githubusercontent.com/6340170/141404571-a2c9a5b1-1987-4e34-a985-47b157661b02.png)
![image](https://user-images.githubusercontent.com/6340170/141404661-153a411f-9912-4b26-bb29-d4ac6c04433a.png)

#PASO 5: Con el interprete de comandos de windows siturarse dentro de esta carpeta C:\broker\cashonline
cd C:\broker\cashonline
![image](https://user-images.githubusercontent.com/6340170/141404978-275b43ed-4ec1-471a-a63d-3796b409d79c.png)

#PASO 6: Ejecutar el siguiente comando maven: mvn spring-boot:run
![image](https://user-images.githubusercontent.com/6340170/141405097-5b81f6d3-f78d-4980-82fa-172f6f773857.png)

#PASO 7: Una vez levantado el sevidor tomcat embebido sobre el puerto 8080
![image](https://user-images.githubusercontent.com/6340170/141405239-b46654da-2ac9-4433-a628-276bf168d752.png)

La api rest quedo corriendo la siguiente url: http://localhost:8080 

#PASO 8: Base datos en memoria usando H2, para ello ingresar a http://localhost:8080/h2-console , luego presionar el boton Connect
![image](https://user-images.githubusercontent.com/6340170/141405526-f9cbb2a4-7fab-4639-b98e-ab7ecfe79d3d.png)

#PASO 9: Verifacar datos de pruebas en las tablas users y loans, en sql editor correr los siguientes comandos:
select * from users
select * from loans
En la tabla loans hay 1500 registros insertados para poder haces las pruebas de paginacion de la api con el postman.

![image](https://user-images.githubusercontent.com/6340170/141405762-a139cc1e-bae8-4aeb-aabe-3433e261fb2d.png)
![image](https://user-images.githubusercontent.com/6340170/141405908-15906e27-9d00-4bc8-9cc6-4500e65300ab.png)

#PASO 10: Importar en postman la siguiente collection subido al googledrive, descargar archivo luego importarlo:
https://drive.google.com/drive/folders/1pWPM6ooEFPD5bGBYmT4sZa-lzbG5HeZr?usp=sharing

![image](https://user-images.githubusercontent.com/6340170/141406169-eab42603-2e0f-4700-94f2-5d3a983515c7.png)

Endpoints:
POST crearUsuarioSinPrestamo: endpoint para crear un usuario sin prestamo el id es generado por la base de datos.
POST crearUsuarioSinPrestamo: endpoint para crear un usuario con prestamo como un array, los id es generado por la base de datos.
PUT modificarUsuario: endpoint para modificar datos del usuario dado el id del usuario.
GET modificarUsuario: endpoint para obtener datos del usuario con sus prestamos dado el id del usuario.
GET obtenerUsuarios: endpoint para obtener los datos de todos los usuarios con sus prestamos.
DELETE eliminarUsuario: endpoint para elimiar un usuario y sus prestamo dado el id del usuario.
DELETE eliminarUsuarios: endpoint para eliminar todos los usuarios con sus prestamos.
GET obtenerPrestamos: endpoint para obetener todos los prestamos de todos los usuarios paginados por page y size como parametros.
GET obtenerPrestamosPorUsuario: endpoint para obetener todos los prestamos de todos los usuarios paginados por page, size Y user_id como parametros.
