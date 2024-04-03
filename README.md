# README #

El presente documento es una guia para probar el Ejercicio Java de creación de usuario.

### 1 Requisitos para levantar la aplicación ###

* Tener instalado Java JDK 17 [https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html]
* Tener installado Maven 3.6.0+ [https://maven.apache.org/docs/history.html]
* Tener instalado el comando curl [https://curl.se/windows/] en linux ejecutar comando: sudo apt-get install curl

### 2 Pasos para levantar la aplicación? ###

* En una carpeta del tu file system descargar las fuentes desde : https://github.com/huberquinto/ejerciciojava
realizar la descarga como zip.
![Descagar zip con codigo fuente](https://github.com/huberquinto/ejerciciojava/blob/main/imagenes/descargar_fuentes.png)
* Luego mediante linea de comandos ejecutar el siguiente comando maven: `mvn clean install`

![Compilar con maven](https://github.com/huberquinto/ejerciciojava/blob/main/imagenes/levantar_proyecto01.png)

* Seguidamente levantar la aplicacion ejecutando: `mvn spring-boot:run`

![Ejecutar la aplicacion con maven](https://github.com/huberquinto/ejerciciojava/blob/main/imagenes/levantar_proyecto02.png)

### 3 Realizar pruebas invocando a APIs REST ###

#### Método 1 ejecutar curls mediante linea de comandos ####
**NOTA**: Los curls de prueba se ubica en el archivo: **curls_de_prueba.txt**
* Invocar al endpoint que genera token Nombre HFQV-Token -> http://localhost:8080/api/token

`curl --location 'http://localhost:8080/api/token' \
--header 'UserLogin: huberquintov'
  `
* Obtener el valor la respuesta **tokenCode**, ejemplo de respuesta:
`{"tokenCode":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJodWJlcnF1aW50b3YiLCJleHAiOjE3MTIxNjU2MjF9.mLtNZmNzJ6MOjImADx_JkBM9ILcYAdwfD2WRQxwgMyaQpyykeWEOPr_MpwrfeDSjdujea0Ol5elgCCHEZ3m31w"}`

* Invocar al endpoint que crear usuario **copiando el valor del tokenCode** al header Authorization-> http://localhost:8080/api/user/create

`curl --location 'http://localhost:8080/api/user/create' \
--header 'Content-Type: application/json' \
--header 'Authorization: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJodWJlcnF1aW50b3YiLCJleHAiOjE3MTIxNjU2MjF9.mLtNZmNzJ6MOjImADx_JkBM9ILcYAdwfD2WRQxwgMyaQpyykeWEOPr_MpwrfeDSjdujea0Ol5elgCCHEZ3m31w' \
--header 'UserLogin: huberquintov' \
--data-raw '{
    "name": "Pablo Juarez",
    "email": "pablor4@juarez.org",
    "password": "12376543",
    "phones": [
        {"number": "9968979371",
          "cityCode": "1",
          "countryCode": "57"
        }
    ]
}'`

* Invocar al endpoint que lista usuarios copiando el valor del tokenCode al header Authorization: -> http://localhost:8080/api/user/list

`curl --location 'http://localhost:8080/api/user/list' \
--header 'UserLogin: huberquintov' \
--header 'Authorization: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJodWJlcnF1aW50b3YiLCJleHAiOjE3MTIxNjU2MjF9.mLtNZmNzJ6MOjImADx_JkBM9ILcYAdwfD2WRQxwgMyaQpyykeWEOPr_MpwrfeDSjdujea0Ol5elgCCHEZ3m31w'
`
* A continuación ejemplo de ejecucion en terminal Linux

![Prueba con curl](https://github.com/huberquinto/ejerciciojava/blob/main/imagenes/pruebas_curl01.png)

#### Método 2 Importar collection en postman ####

* Importar collection postman: HuberQuinto_EjercicioJava.postman_collection_v1.json

![Importat postman collection](https://github.com/huberquinto/ejerciciojava/blob/main/imagenes/pruebaspostman00.png)

* Crear un enviroment en postman llamado HFQV_ENVIROMENT y registrar la variable HFQV_TOKEN
  ![Crear enviroment](https://github.com/huberquinto/ejerciciojava/blob/main/imagenes/pruebaspostman01_2.png)
* Verificar el test del endpoint que genera el token donde se asigna el valor a la variable creada.   
  ![Registrar variable en enviroment](https://github.com/huberquinto/ejerciciojava/blob/main/imagenes/pruebaspostman01_1.png)
* Invocar al endpoint que genera token Nombre HFQV-Token -> http://localhost:8080/api/token
  ![Generar token](https://github.com/huberquinto/ejerciciojava/blob/main/imagenes/pruebaspostman01.png)
* Invocar al endpoint que crear usuario: Post crear usuario -> http://localhost:8080/api/user/create
  ![Crear usuario](https://github.com/huberquinto/ejerciciojava/blob/main/imagenes/pruebaspostman02.png)
* Invocar al endpoint que lista usuarios: Get listar usuarios -> http://localhost:8080/api/user/list
  ![Listar usuario](https://github.com/huberquinto/ejerciciojava/blob/main/imagenes/pruebaspostman03.png)


### 4 Datos finales ###

* Se adjunta en el proyecto el diagrama solicitado: HuberQuinto_Creacion_Usuarios.jpg
  ![Ejecutar la aplicacion con maven](https://github.com/huberquinto/ejerciciojava/blob/main/imagenes/HuberQuinto_Creacion_Usuarios.jpg)

* Se adjunta la coleccion postman HuberQuinto_EjercicioJava.postman_collection_v1.json para probar la API Rest creada
* Dentro del proyecto a comentarios TODO dado que no alcanzo tiempo.
* No se comparte scripts sql de creacion de tablas porque utilice una base de datos en memria H2 el cual genera automaticamente las tablas.
