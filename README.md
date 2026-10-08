# 🍫 ChocoFrutas

Aplicación web e-commerce para la venta y administración de productos como chocolates, frutos secos, semillas, frutas deshidratadas y mezclas.

## Tecnologías

### Frontend

* React 19
* Vite
* React Router
* JavaScript
* CSS
* Vitest
* React Testing Library

### Backend

* Java 21
* Spring Boot 3.5
* Spring Security
* Spring Data JPA
* Hibernate
* JWT
* MySQL
* Swagger / OpenAPI
* JUnit
* Mockito

## Estructura del proyecto

```text
ChocoFrutas_EvP3/
│
├── choco-fruta/          # Frontend React
│   ├── public/
│   └── src/
│       ├── componentes/
│       ├── pages/
│       ├── router/
│       ├── utils/
│       └── test/
│
├── chocofruta/            # Backend Spring Boot
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   └── resources/
│   │   └── test/
│   ├── docs/
│   └── uploads/
│
├── basechoco.sql          # Script de base de datos
└── README.md
```

## Funcionalidades

### Cliente

* Registro de usuarios.
* Inicio y cierre de sesión.
* Autenticación mediante JWT.
* Catálogo de productos.
* Búsqueda y visualización de productos.
* Detalle de productos.
* Carrito de compras.
* Modificación de cantidades.
* Checkout.
* Generación de boletas.
* Historial de compras.
* Seguimiento de pedidos.
* Gestión de perfil.
* Cambio de contraseña.

### Administrador

* Dashboard.
* Gestión de productos.
* Crear, editar y eliminar productos.
* Activar y desactivar productos.
* Gestión de categorías.
* Crear y editar categorías.
* Gestión de usuarios.
* Crear y editar usuarios.
* Consulta de compras.
* Gestión de inventario.

## Arquitectura

```text
React + Vite
     │
     │ HTTP / JSON
     ▼
Spring Boot REST API
     │
     ├── Controllers
     ├── Services
     ├── Repositories
     ├── Spring Security
     └── JWT
     │
     ▼
   MySQL
```

## Base de datos

La aplicación utiliza MySQL.

Base de datos:

```text
basechoco
```

Configuración principal:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/basechoco
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD

spring.jpa.hibernate.ddl-auto=update
```

El script inicial de datos se encuentra en:

```text
basechoco.sql
```

## Instalación

### Requisitos

* Java 21
* Node.js
* npm
* MySQL
* Git

### Clonar el proyecto

```bash
git clone <URL_DEL_REPOSITORIO>
cd ChocoFrutas_EvP3
```

### Base de datos

Crear la base de datos:

```sql
CREATE DATABASE basechoco;
```

Luego ejecutar el archivo:

```text
basechoco.sql
```

## Ejecutar Backend

Entrar a la carpeta:

```bash
cd chocofruta
```

### Windows

```bash
mvnw.cmd spring-boot:run
```

### Linux / macOS

```bash
./mvnw spring-boot:run
```

Backend:

```text
http://localhost:8081
```

## Ejecutar Frontend

En otra terminal:

```bash
cd choco-fruta
npm install
npm run dev
```

Frontend:

```text
http://localhost:5173
```

## API REST

### Autenticación

```text
POST /api/auth/login
POST /api/auth/refresh
POST /api/auth/logout
```

### Productos

```text
GET    /api/productos
GET    /api/productos/{id}
POST   /api/productos
PUT    /api/productos/{id}
DELETE /api/productos/{id}
PATCH  /api/productos/{id}/activar
PATCH  /api/productos/{id}/desactivar
```

### Categorías

```text
GET    /api/categorias
GET    /api/categorias/{id}
POST   /api/categorias
PUT    /api/categorias/{id}
DELETE /api/categorias/{id}
```

### Usuarios

```text
GET    /api/usuarios
GET    /api/usuarios/{id}
POST   /api/usuarios
PUT    /api/usuarios/{id}
DELETE /api/usuarios/{id}
```

### Carrito

```text
GET    /api/carrito
POST   /api/carrito/items
PUT    /api/carrito/items/{id}
DELETE /api/carrito/items/{id}
DELETE /api/carrito
```

### Compras

```text
POST /api/boletas/checkout
GET  /api/boletas/historial
GET  /api/admin/compras
```

## Autenticación

Las rutas protegidas utilizan JWT.

El token se envía mediante:

```http
Authorization: Bearer <TOKEN>
```

Roles disponibles:

```text
ADMIN
CLIENTE
```

## Principales páginas

```text
/
 /catalogo
 /producto/:id
 /login
 /registro
 /carrito
 /proceso-pago
 /mis-compras
 /seguimiento-pedido
 /perfil
```

También existen vistas administrativas para:

```text
Dashboard
Productos
Crear producto
Editar producto
Categorías
Crear categoría
Editar categoría
Usuarios
Crear usuario
Editar usuario
Inventario
```

## Flujo de compra

```text
Catálogo
   ↓
Detalle del producto
   ↓
Agregar al carrito
   ↓
Carrito
   ↓
Checkout
   ↓
Generación de boleta
   ↓
Actualización de stock
   ↓
Historial de compras
```

## Swagger

Con el backend ejecutándose:

```text
http://localhost:8081/swagger-ui.html
```

Documentación OpenAPI:

```text
http://localhost:8081/api-docs
```

## Postman

La colección de Postman se encuentra en:

```text
chocofruta/docs/chocofruta.postman_collection.json
```

## Testing

### Frontend

```bash
cd choco-fruta
npm test -- --run
```

Cobertura:

```bash
npm run test:cov
```

### Backend

Windows:

```bash
cd chocofruta
mvnw.cmd test
```

Linux / macOS:

```bash
cd chocofruta
./mvnw test
```

## Build

### Frontend

```bash
cd choco-fruta
npm install
npm run build
```

El resultado queda en:

```text
dist/
```

### Backend

```bash
cd chocofruta
mvnw.cmd clean package
```

El `.jar` se genera en:

```text
target/
```

## URLs

| Servicio | URL                                     |
| -------- | --------------------------------------- |
| Frontend | `http://localhost:5173`                 |
| Backend  | `http://localhost:8081`                 |
| Swagger  | `http://localhost:8081/swagger-ui.html` |
| OpenAPI  | `http://localhost:8081/api-docs`        |

## 👥 Usuarios de prueba

| Usuario | Email                                             | Rol     |
| ------- | ------------------------------------------------- | ------- |
| admin   | [admin@chocofruta.cl](mailto:admin@chocofruta.cl) | ADMIN   |
| juan    | [juan@gmail.com](mailto:juan@gmail.com)           | CLIENTE |
| maria   | [maria@gmail.com](mailto:maria@gmail.com)         | CLIENTE |

## 🍫 ChocoFrutas

Proyecto full stack desarrollado con **React, Spring Boot, JWT y MySQL**.
