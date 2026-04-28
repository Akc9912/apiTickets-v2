# 🎫 ApiTickets - Sistema de Gestión de Tickets

<div align="center">
	<img src="https://img.shields.io/badge/Java-21-blue.svg" />
	<img src="https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen.svg" />
	<img src="https://img.shields.io/badge/Spring%20Security-6.5.1-yellow.svg" />
	<img src="https://img.shields.io/badge/MySQL-8.0+-blue.svg" />
	<img src="https://img.shields.io/badge/Maven-3.9+-purple.svg" />
	<img src="https://img.shields.io/badge/Lombok-enabled-orange.svg" />
	<img src="https://img.shields.io/badge/Swagger-OpenAPI%203-brightgreen.svg" />
	<img src="https://img.shields.io/badge/JWT-jjwt-red.svg" />
	<img src="https://img.shields.io/badge/Actuator-enabled-lightgrey.svg" />
	<img src="https://img.shields.io/badge/H2%20DB-testing-informational.svg" />
	<img src="https://img.shields.io/badge/Version-1.0.1--SNAPSHOT-brightgreen.svg" />
</div>

---

Para ver el historial de cambios y versiones consulta el archivo [CHANGELOG.md](./CHANGELOG.md).
# ApiTickets - Sistema de Gestión

**Stack principal:**

- Java 21 (LTS)
- Spring Boot 3.5.3
- Spring Security 6.5.1
- Spring Data JPA
- MySQL 8.0+
- Maven 3.9+
- Lombok
- Swagger/OpenAPI 3
- JWT (jjwt)
- Actuator
- H2 (testing)

---

## 📦 Estructura y Dependencias

```
src/main/java/com/poo/miapi/
├── config/         # Configuración general y beans
├── controller/     # Controladores REST (por vertical)
├── dto/            # Data Transfer Objects
├── exception/      # Manejo de excepciones
├── model/          # Entidades de dominio (core, enums, etc.)
├── modules/        # Módulos funcionales (auth, identity, tenancy, membership, authorization, ticketing)
├── repository/     # Repositorios JPA
├── security/       # Seguridad y JWT
├── service/        # Servicios de dominio y aplicación
├── shared/         # Utilidades y componentes compartidos
├── util/           # Utilidades generales
└── MiapiApplication.java # Main Spring Boot

src/main/resources/
├── application.properties         # Configuración principal (MySQL, JWT, logging, etc.)
├── application-test.properties    # Configuración de test (H2, perfiles, logging)

src/test/java/com/poo/miapi/
└── MiapiApplicationTests.java     # Test base de contexto Spring Boot

src/test/resources/
└── application-test.properties    # Configuración de test
```

**Dependencias principales (pom.xml):**

```xml
<dependency>org.springframework.boot:spring-boot-starter-data-jpa</dependency>
<dependency>org.springframework.boot:spring-boot-starter-web</dependency>
<dependency>org.springframework.boot:spring-boot-starter-security</dependency>
<dependency>org.springdoc:springdoc-openapi-starter-webmvc-ui</dependency>
<dependency>com.mysql:mysql-connector-j</dependency>
<dependency>io.jsonwebtoken:jjwt-api</dependency>
<dependency>io.jsonwebtoken:jjwt-impl</dependency>
<dependency>io.jsonwebtoken:jjwt-jackson</dependency>
<dependency>org.projectlombok:lombok</dependency>
<dependency>org.springframework.boot:spring-boot-starter-validation</dependency>
<dependency>org.springframework.boot:spring-boot-starter-actuator</dependency>
<dependency>org.springframework.boot:spring-boot-starter-test</dependency>
```

---


