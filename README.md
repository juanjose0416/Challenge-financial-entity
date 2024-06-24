# Challenge-financial-entity

## Descripción

Este proyecto es una aplicación destinada a ser utilizada por los trabajadores de una entidad financiera. La aplicación permite la administración de clientes, incluyendo el registro, actualización y eliminación de clientes. También permite la creación de productos financieros para los clientes y la realización de movimientos transaccionales sobre estos productos, además de la consulta de estados de cuenta.

## Tecnologías Usadas

- **Java 17**
- **Spring Boot 3.3.1**
- **H2 Database**

## Requisitos Previos

- **JDK de Java 17**
- **Maven**
- **Un IDE para ejecutar el proyecto (Eclipse, IntelliJ, VSCode, etc.)**

## Comandos Útiles

Para limpiar y construir el proyecto, puedes utilizar los siguientes comandos de Maven:

```sh
mvn clean build
mvn clean compile
mvn clean install

## Documentación de la API

La documentación de la API está disponible en Swagger. Puedes acceder a ella en la siguiente URL después de iniciar la aplicación:

[Swagger UI](http://localhost:8080/swagger-ui/index.html)

## Arquitectura y Patrones

El proyecto está diseñado utilizando una arquitectura hexagonal. Además, se han implementado tres patrones de diseño GOF:

- **Strategy**
- **Factory**
- **Builder**
