# Read Me First

This Spring Boot project utilizes Maven for the build processes. It's designed to showcase a web app that help students find study groups for different classes.

## Prerequisites

- **Java JDK 17**: Ensure Java JDK 17 is installed on your machine.
- **MySQL 8.0.33**: This project uses MySQL as the database. Make sure MySQL is installed and running 8.0.33 (If low verison update pom.xml).
- **Eclipse IDE**: For developing and running this project, Eclipse is recommended.
- **Maven**: Maven is used for managing project dependencies and building the project.
- **Lombok**: Lombok should be installed but if you are having errors in you code install it [here](https://projectlombok.org/download)

## Project Structure

- **db folder**: Contains the database schema scripts.
- **other**: Contains additional project-related files. (Figma, Schema, and design document files)

## Getting Started

### MySQL Database
1. Find the database script in the **db folder**.
2. Run the sciprt in MySQL Workbench.

### Eclipse
1. Open Eclipse.
2. Go to `File` > `Import`.
3. Select `Maven` > `Existing Maven Projects`.
4. Navigate to and select the project directory in your file system.
5. Click `Finish` to import the project.
6. Right-click the project: `Maven` > `Update Project`.
7. Find the **application.properties** file in the resource folder.
8. Update the code so its able to connect to the database
```
#spring.datasource.url=jdbc:mysql://localhost:3306/<DATABASE NAME>?useSSL=false
#spring.datasource.username=<USERNAME>
#spring.datasource.password=<PASSWORD>
```
9. Run as `Maven clean`.
10. Run as `Maven install`.
11. Run as `Java Application` to start the project.
12. Visit [http://localhost:8080/](http://localhost:8080/) in your web browser.

### Docker (Optional)
1. Update **application.properties**
```
spring.config.activate.on-profile=prod
spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
```
2. Run the file via termial 
```
docker build -t study-group-finder:latest . 

docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL='jdbc:mysql://<Database URL>?useSSL=true&requireSSL=false' \
  -e SPRING_DATASOURCE_USERNAME='<USERNAME>' \
  -e SPRING_DATASOURCE_PASSWORD='<PASSWORD>' \
  -e SPRING_DATASOURCE_DRIVER_CLASS_NAME='com.mysql.cj.jdbc.Driver' \
  -e SPRING_JPA_HIBERNATE_DDL_AUTO='none' \
  study-group-finder:latest
```
3. Visit [http://localhost:8080/](http://localhost:8080/) in your web browser.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.4/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.2.4/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Thymeleaf](https://docs.spring.io/spring-boot/docs/3.2.4/reference/htmlsingle/index.html#web.servlet.spring-mvc.template-engines)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.4/reference/htmlsingle/index.html#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

