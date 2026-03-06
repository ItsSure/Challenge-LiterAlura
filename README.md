# 📚 Literal

**Literal** es una aplicación de consola que permite buscar libros usando la API de Gutendex y almacenarlos en una base de datos local PostgreSQL.  
Posteriormente, la aplicación permite consultar la información almacenada mediante diferentes opciones de búsqueda.

---

## 🚀 Características

La aplicación ofrece un menú interactivo con las siguientes opciones:

1. **Buscar libro por título**
   - Consulta la API de Gutendex
   - Guarda el libro y su autor en la base de datos PostgreSQL

2. **Listar libros**
   - Muestra todos los libros almacenados en la base de datos

3. **Listar autores**
   - Muestra los autores registrados en la base de datos

4. **Listar libros por idioma**
   - Filtra los libros almacenados según su idioma

5. **Autores vivos en un año**
   - Muestra autores que estaban vivos en un año específico

0. **Salir**

---

## 🛠️ Tecnologías utilizadas

- Java
- PostgreSQL
- Gutendex API
- Maven / Gradle

---

## 🌐 API utilizada

La aplicación utiliza la API pública de **Gutendex**, que permite consultar libros del proyecto **Project Gutenberg**.

Endpoint utilizado:
https://gutendex.com/books/?search=


---

## 🗄️ Base de datos

La aplicación almacena la información en una base de datos **PostgreSQL** local.

---

## ⚙️ Configuración

### 1. Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/literal.git
cd literal
```
### 2. Configurar conexión

Editar el archivo application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/literal

spring.datasource.username=tu_usuario

spring.datasource.password=tu_password

## 📌 Ejemplo de uso
1 - Buscar libro por título

2 - Listar libros

3 - Listar autores

4 - Listar libros por idioma

5 - Autores vivos en un año

0 - Salir