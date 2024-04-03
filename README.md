# README #

El presente documento es una guia para probar el Ejercicio Java de creación de usuario.

### Requisitos para levantar la aplicación ###

* Tener instalado Java JDK 17 [https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html]
* Tener installado Maven 3.6.0 + [https://maven.apache.org/docs/history.html]
* Tener instalado git para descargar fuentes. [https://git-scm.com/book/en/v2/Getting-Started-Installing-Git]
* Tener instalado el comando curl [https://curl.se/windows/] o en lunux ejecutar comando: sudo apt-get install curl

### Pasos para levantar la aplicación? ###

* En una carpeta del tu file system descargar las fuentes desde : https://github.com/huberquinto/ejerciciojava
* Luego mediante linea de comandos ejecutar el siguiente comando maven:  mvn clean install
* Seguidamente levantar la aplicacion ejecutando: mvn spring-boot:run

### Realizar pruebas invocando a APIs REST ###
#### Metodo 1 Importar collection en postman ####
* Importar collection: HuberQuinto_EjercicioJava.postman_collection_v1.json
* Crear un enviroment llamado HFQV_ENVIROMENT
* Registrar en este envorment la variable HFQV_TOKEN.
* Invocar al endpoint que genera token Nombre HFQV-Token -> http://localhost:8080/api/token
* Invocar al endpoint que crear usuario: Post crear usuario -> http://localhost:8080/api/user/create
* Invocar al endpoint que lista usuarios: Get listar usuarios -> http://localhost:8080/api/user/list
#### Metodo 2 Importar ejecutar mediante linea de comandos ####

* Invocar al endpoint que genera token Nombre HFQV-Token -> http://localhost:8080/api/token
  


### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact
