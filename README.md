# Wedding Planner Project
    📋 Descripción del proyecto 📋
Esta aplicación es un proyecto diseñado para simplificar la planificación y gestión de una boda, tanto para la pareja 
que se casa como para el o la organizador de boda. Tiene funcionalidades para organizar y consultar invitados, gestionar y modificar
detalles del evento o de la pareja, con un sistema de roles de usuario (Admin para Wedding Organizer, Editor para la pareja y Guest
para lxs invitadxs), que les ofrece acceso personalizado.

El proyecto es una aplicación de back end diseñada en Java con Spring Boot, y que proporciona API RESTful para la gsetión de los 
datos y una autenticación de usuarios para proteger información sensible, que se accede mediante tokens JWT.

    📐 Diagrama de clases
![img.png](Documentación/Diagrama de clases.png)

    🚀 Tecnologías y herramientas usadas 🚀
-Java 17

-Spring Boot 3

-Jpa Hibernate

-JWT Authentication

-Maven : dependencias

-MYSQL : base de datos

-Postman : pruebas

-Git : control de versiones

    🪜 Iniciar la aplicación 🪜 

1. Clona el repositorio
2. Importa el proyecto en tu IDE
3. Configura el archivo  "application.properties"


    spring.application.name=weddingplanner
    spring.datasource.url=jdbc:mysql://localhost:3306/wedding_planner
    spring.datasource.username=your-username
    spring.datasource.password=your-password
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.hibernate.ddl-auto=update
    server.error.include-stacktrace=never
    spring.main.allow-circular-references=true
    logging.level.org.springframework.security=DEBUG

4. Ejecuta el proyecto.
5. Haz las pruebas con Postman u otra aplicación.

    
    📁 Controladores y rutas 📁
-Auth Controller: para iniciar sesión y registrar usuarios según su tipo.

-Admin Controller: CRUD para bodas y usuarios (requiere rol ADMIN, es para Wedding Organizer)

-Editor Controller: permite a la pareja gestionar su boda e invitados.

-Guest Controller: permite a lxs invitadxs acceder a la información de la boda y modificar su acompañante.

    🔗 Enlaces 🔗
-Trello: https://trello.com/invite/b/681a10787b0754ffa4a954f0/ATTI5c205d7d7c2d56b3408d6673daa8847b41589C61/weddingplanner.

-Presentación: https://docs.google.com/presentation/d/1gsCotCVp8KKx__3LermxoJ-0wM5HIGneRTDEYYF8Jj8/edit?usp=sharing

-Repositorio GitHub: https://github.com/Irenevn16/WeddingPlanner.git

    🛠️  Me️joras futuras 🛠️

-Implementar MockMvc 

-Mejor manejo de errores y de seguridad

-Más endpoint que permitan más flexibilidad de modificación
    
    📚 Documentación 📚
-Estructura del proyecto

-Diagrama de clases

-SQL queries iniciales

    👥 Autora 👥
Irene Villarreal Nieto

Con el apoyo del profesorado de Ironhack (Marcel y Héctor).
