
# LiterAlura-Catalog

**LiterAlura-Catalog** es una aplicación desarrollada en Java que permite a los usuarios buscar y gestionar libros de dominio público. Utiliza la API de Gutendex para obtener información sobre los libros y emplea Spring Boot junto con PostgreSQL para la gestión y almacenamiento de datos.

## Características

- **Búsqueda de libros por título**: Permite a los usuarios buscar libros ingresando el título deseado.
- **Listado de libros registrados**: Muestra todos los libros almacenados en la base de datos.
- **Listado de autores registrados**: Presenta una lista de todos los autores disponibles en la base de datos.
- **Listado de autores vivos en un año específico**: Permite identificar qué autores estaban vivos en un año determinado.
- **Listado de libros por idioma**: Filtra y muestra libros según el idioma seleccionado.

## Requisitos

- **Java**: Versión 17 o superior.
- **Maven**: Para la gestión de dependencias y construcción del proyecto.
- **PostgreSQL**: Base de datos relacional para el almacenamiento de información.

## Instalación

1. **Clonar el repositorio**:

   ```bash
   git clone https://github.com/enriquevaldivia1988/LiterAlura-Catalog.git
   ```

2. **Navegar al directorio del proyecto**:

   ```bash
   cd LiterAlura-Catalog
   ```

3. **Configurar la base de datos**:

    - Crear una base de datos en PostgreSQL.
    - Actualizar las credenciales de la base de datos en el archivo `application.properties` ubicado en `src/main/resources/`.

4. **Construir y ejecutar la aplicación**:

   ```bash
   ./mvnw spring-boot:run
   ```

## Uso

Al ejecutar la aplicación, se presentará un menú en la consola con las siguientes opciones:

1. Buscar libro por título.
2. Listar libros registrados.
3. Listar autores registrados.
4. Listar autores vivos en un determinado año.
5. Listar libros por idioma.
6. Salir.

Seleccione la opción deseada ingresando el número correspondiente y siga las instrucciones proporcionadas.

## Dependencias

El proyecto utiliza las siguientes dependencias principales:

- **Spring Boot**: Framework para facilitar el desarrollo de aplicaciones Java.
- **Spring Data JPA**: Para la interacción con la base de datos.
- **PostgreSQL Driver**: Controlador JDBC para PostgreSQL.
- **Jackson Databind**: Para el procesamiento de datos JSON.

## Contribuciones

Las contribuciones son bienvenidas. Si desea colaborar, por favor realice un fork del repositorio, cree una nueva rama para su funcionalidad o corrección, y envíe un pull request para su revisión.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulte el archivo `LICENSE` para más detalles.

## Contacto

Para consultas o sugerencias, puede contactar al autor a través de su perfil de GitHub: [enriquevaldivia1988](https://github.com/enriquevaldivia1988).
