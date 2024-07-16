# Catálogo de Libros

## Descripción

Este proyecto es una aplicación de consola que interactúa con una API externa para buscar libros y autores, y guarda la información en una base de datos PostgreSQL.

## Requerimientos

- Java 17 o superior
- Spring Boot 3.2.3
- PostgreSQL

## Configuración

1. Clona el repositorio.
2. Configura la base de datos PostgreSQL con las credenciales en `src/main/resources/application.properties`.
3. Ejecuta la aplicación desde la clase principal.

## Uso

La aplicación ofrece las siguientes opciones:
1. Buscar libro por título
2. Listar libros registrados
3. Listar autores registrados
4. Listar autores vivos en un determinado año
5. Listar libros por idioma
0. Salir

## Dependencias

- Spring Data JPA
- Postgres Driver
- Jackson
