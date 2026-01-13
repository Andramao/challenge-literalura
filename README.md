# 📚 LiterAlura - Catálogo Literario Inteligente

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=java&logoColor=white" />
  <img src="https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" />
  <img src="https://img.shields.io/badge/PostgreSQL-4479A1?style=for-the-badge&logo=postgresql&logoColor=white" />
  <img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white" />
</p>

---

## 📖 Descripción del Proyecto
**LiterAlura** es una herramienta de gestión de bibliotecas que conecta la consola de comandos con la API de **Gutendex**. Permite a los usuarios buscar libros en la web, almacenarlos de forma local y generar análisis avanzados sobre autores e idiomas.

---

## 🛠️ Stack Tecnológico

| Componente | Tecnología |
| :--- | :--- |
| **Lenguaje** | Java 17 |
| **Framework** | Spring Boot 3 |
| **Persistencia** | Spring Data JPA / Hibernate |
| **Base de Datos** | PostgreSQL |
| **Serialización** | Jackson (JSON) |
| **API Externa** | [Gutendex API](https://gutendex.com/) |

---

## 🚀 Funcionalidades Principales

### 🔍 Gestión de Búsquedas
* **Web Search:** Busca libros por título en tiempo real.
* **Filtro Local:** Localiza autores por nombre en tu base de datos personal.
* **Seguridad:** Validación de códigos de idioma (ISO 639-1).

### 📊 Análisis de Datos
* **Top 10:** Ranking dinámico de los libros más descargados.
* **Estadísticas:** Distribución porcentual de libros por idioma.
* **Línea de Tiempo:** Identificación de autores vivos en periodos históricos específicos.



---

## 🧩 Desafíos Superados

> [!IMPORTANT]
> **Optimización de Relaciones:** Se implementó una relación `@ManyToOne` bidireccional entre `Libro` y `Autor` con carga **EAGER**, permitiendo reportes detallados del autor y su bibliografía completa sin consultas adicionales.

| Reto | Solución Técnica |
| :--- | :--- |
| **Duplicidad de Autores** | Implementación de `findByNombreContainsIgnoreCase` para validación antes de persistir. |
| **Recursividad Infinita** | Personalización de `toString()` en la entidad Autor usando Java Streams. |
| **Datos Nulos** | Manejo de autores "presentes" mediante lógica condicional en el filtrado de años. |

---

## ⚙️ Configuración e Instalación

1. **Base de Datos:**
   ```sql
   CREATE DATABASE literalura;

2. **Properties**
   ```
    * spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
    * spring.datasource.username=tu_usuario
    * spring.datasource.password=tu_contraseña
    * spring.jpa.hibernate.ddl-auto=update  

3. **Ejecución**
   ```Bash
    ./mvnw spring-boot:run

## 📐 Arquitectura de Clases
### 🧑‍💻 Autor
Desarrollado con ❤️ por Andramao. 


## 📸 Vista Previa

<p align="center">
  <img src="img/menu.png" width="350" height="400">
  <img src="img/libro.png" width="350" height="225">
  <img src="img/nuevo-libro-insert.png" width="350" height="800">
  <img src="img/registrados.png" width="350" height="200">
</p>


## 🤝 Agradecimientos

Este proyecto fue desarrollado como parte del desafío **LiterAlura** en el programa **Oracle Next Education (ONE)** en conjunto con **Alura Latam**. 

Agradezco a los instructores por las bases proporcionadas en Java, Spring Boot y JPA, las cuales fueron fundamentales para construir esta solución.

---

<h3 align="center">❤️Agradecimientos a: </h3> 
<div align="center">
  <p >#Alura Latam.</p>
  <p >#Oracle Next Education.</p>
</div>

<div align="center"><img width="170" height="60" alt="image" src="https://github.com/user-attachments/assets/3c16f5d9-d26b-40d6-bbd9-04abebc96238" /> <img width="80" height="54" alt="image" src="https://github.com/user-attachments/assets/2fc68741-4e16-4f1c-b438-5de3cedad13d" /></div>


## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Esto significa que eres libre de usar, copiar, modificar y distribuir el código, siempre y cuando se mantenga el reconocimiento al autor original.

---

<p align="center">
  <b>Desarrollado con dedicación por Andramao - 2026</b><br>
  <i>"La lectura es a la mente lo que el ejercicio al cuerpo." — Joseph Addison</i>
</p>






