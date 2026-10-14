# Sistema de Gestión de Coworking - Proyecto Integrador

![Java](https://img.shields.io/badge/Java-21-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green.svg)
![Vaadin](https://img.shields.io/badge/Vaadin-25.3-blue.svg)
![Build](https://img.shields.io/badge/Build-Passing-brightgreen.svg)

## Resumen Ejecutivo
El sistema resuelve la gestión centralizada de espacios de coworking, permitiendo administrar usuarios, áreas de trabajo y reservas. Proporciona una interfaz web reactiva y una persistencia de datos liviana y optimizada sin sobreingeniería, garantizando alta eficiencia en el manejo de transacciones CRUD.

## Arquitectura
Arquitectura en capas desacoplada siguiendo el patrón DAO:
- **Capa de Presentación (UI):** Vistas web interactivas construidas con Vaadin Flow.
- **Capa de Acceso a Datos (DAO):** Consultas SQL directas mediante `JdbcTemplate` y mapeo manual con `RowMapper`.
- **Capa del Modelo (POJO):** Entidades puras en Java sin anotaciones ORM/JPA.
- **Base de Datos:** PostgreSQL en la nube (Neon.tech).

## Modelo de Datos
El modelo relacional está compuesto por tres entidades principales: `Usuario`, `Espacio` y `Reserva`.

![Diagrama MER](docs/mer.png)

## Stack Tecnológico
- **Lenguaje:** Java 21
- **Framework Web & UI:** Vaadin 25.3.0 + Spring Boot
- **Persistencia:** Spring JDBC (`JdbcTemplate`)
- **Base de Datos:** PostgreSQL (Neon.tech)
- **Gestor de Dependencias:** Apache Maven

## Guía de Configuración

### Prerrequisitos
- JDK 21 instalado
- Git

### Variables de Entorno y Conexión
Configurar `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://<HOST_NEON>:5432/<DATABASE>?sslmode=require
spring.datasource.username=<USUARIO>
spring.datasource.password=<PASSWORD>