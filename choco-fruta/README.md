# 📄 **README.md 
# 🍫 Choco&Frutas - Sistema de Gestión de Tienda Virtual

![Logo](public/img/Logo.png)

Sistema web full-stack para la gestión integral de una tienda virtual especializada en chocolates, frutos secos, frutas deshidratadas y semillas. Desarrollado con **Spring Boot** (Backend) y **React** (Frontend).

---

## 🚀 Inicio Rápido (Frontend)

- En una terminal, desde la carpeta `choco-fruta`:
  - `npm install`
  - `npm run dev`
- Accede a `http://localhost:5173/`
- Para que la app funcione con datos reales, ejecuta también el backend en `http://localhost:8081`.

---

## 📋 Tabla de Contenidos

- [Descripción](#-descripción)
- [Tecnologías Utilizadas](#-tecnologías-utilizadas)
- [Características Principales](#-características-principales)
- [Requisitos Previos](#-requisitos-previos)
- [Instalación](#-instalación)
- [Configuración de Base de Datos](#-configuración-de-base-de-datos)
- [Ejecución del Proyecto](#-ejecución-del-proyecto)
- [Credenciales de Prueba](#-credenciales-de-prueba)
- [Documentación de API](#-documentación-de-api)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Funcionalidades Implementadas](#-funcionalidades-implementadas)
- [Testing](#-testing)
- [Capturas de Pantalla](#-capturas-de-pantalla)
- [Autores](#-autores)

---

## 📖 Descripción

**Choco&Frutas** es una aplicación web que permite a los administradores gestionar el inventario de productos, usuarios y realizar seguimiento de stock en tiempo real. El sistema incluye alertas automáticas para productos con stock crítico y un panel de control (dashboard) con estadísticas en vivo.

**Contexto Académico:** Proyecto desarrollado para la asignatura de Programación Web en DUOC UC.

---

## 🎯 Rúbrica Experiencia 3 - ET

### Descripción de la etapa
En esta tercera etapa se continúa la preparación de la tienda online y se optimizan procesos de la experiencia 2, integrando seguridad, carrito de compra, emisión de boleta y pruebas de frontend.

### Objetivos de aprendizaje
- Implementar servicios REST con Spring Boot
- Desarrollar interfaces de usuario con React
- Integrar frontend y backend
- Implementar testing en FrontEnd
- Implementar carrito de compra

### Requerimientos funcionales (implementados)

- Implementar Login con JWT
  - Generación de token con payload `rol`, expiración configurada y secret seguro
  - Autenticación y validación del token
  - Manejo de sesión: logout, refresh de token, expiración manejada correctamente
  - Protección de rutas: verificación de permisos y rutas protegidas

- Hashing de contraseñas
  - BCrypt con salt rounds 12 configurado
  - Implementación con manejo de excepciones y verificación segura

- Perfil Cliente: Carrito de compra
  - Modelo y relaciones para carrito e ítems
  - CRUD de carrito y validaciones
  - Integración entre usuario, carrito y pedidos
  - UI responsiva para gestionar el perfil y el carrito

- Emisión y generación de boleta
  - Estructura completa de boleta y detalle con numeración correlativa
  - Cálculos de neto, IVA (19%) y total
  - Persistencia en BD y generación automática al finalizar la compra
  - Historial de compras accesible para el cliente

- Testing en React
  - Pruebas unitarias e integración con React Testing Library y Vitest
  - Scripts de testing configurados; cobertura opcional

- Dashboard Admin
  - Acceso a compras realizadas por los clientes y navegación a detalles

### Entregables
- Repositorio FrontEnd (URL), con instrucciones de instalación y ejecución
- Repositorio Backend (URL), con README: instalación, ejecución, credenciales de prueba y documentación de API (Swagger/Postman)
- Archivos comprimidos Backend y FrontEnd
- Documento ERS (PDF)
- Video tutorial de la aplicación
- Documento de Testing
- Base de Datos: script SQL de creación y seed data

### Bonificación
- Paginación en listados (+2 pts)
- Backend desplegado en Cloud (+10 pts)

### Fechas
- Semana del 24 de noviembre (según sección)

---

## 🛠️ Tecnologías Utilizadas

### Backend
- **Java 17** - Lenguaje de programación
- **Spring Boot 3.2.0** - Framework principal
- **Spring Data JPA** - Persistencia de datos
- **MySQL 8.0** - Base de datos relacional
- **Lombok** - Reducción de código boilerplate
- **Swagger/OpenAPI 3.0** - Documentación de API
- **Mockito & JUnit 5** - Testing

### Frontend
- **React 18.3.1** - Librería UI
- **React Router DOM 6.x** - Enrutamiento SPA
- **Bootstrap 5.3.3** - Framework CSS
- **Bootstrap Icons** - Iconografía
- **Vite** - Build tool y servidor de desarrollo

### Herramientas
- **Git/GitHub** - Control de versiones
- **Maven** - Gestión de dependencias (Backend)
- **npm** - Gestión de dependencias (Frontend)
- **MySQL Workbench** - Gestión de base de datos

---

## ✨ Características Principales

### Módulo de Productos
- ✅ CRUD completo de productos
- ✅ Búsqueda en tiempo real por nombre
- ✅ Filtrado por categoría
- ✅ Alertas visuales de stock bajo (< 5 unidades)
- ✅ Gestión de imágenes (URL)
- ✅ Control de stock y precios

### Módulo de Usuarios
- ✅ CRUD completo de usuarios
- ✅ Gestión de roles (Cliente, Vendedor, Super-Admin)
- ✅ Búsqueda y filtrado
- ✅ Activación/Desactivación de usuarios

### Dashboard Administrativo
- ✅ Estadísticas en tiempo real
- ✅ Total de productos en inventario
- ✅ Total de usuarios registrados
- ✅ Alertas de productos con stock crítico
- ✅ Accesos rápidos a módulos principales

### Autenticación
- ✅ Sistema de login con validación
- ✅ Manejo de sesión (localStorage)
- ✅ Rutas protegidas (React Router)
- ✅ Validación de roles de administrador

### Validaciones
- ✅ Validación en tiempo real de formularios
- ✅ Mensajes de error descriptivos
- ✅ Feedback visual (colores, iconos)
- ✅ Validación de formatos (email, números)

---

## 📋 Requisitos Previos

Antes de comenzar, asegúrate de tener instalado:

- **Java JDK 17** o superior
- **Maven 3.8+**
- **Node.js 18+** y **npm 9+**
- **MySQL 8.0+**
- **Git**

Verificar instalaciones:
```bash
java -version
mvn -version
node -version
npm -version
mysql --version
git --version
```

---

## 🚀 Instalación

### 1. Clonar el Repositorio

```bash
git clone https://github.com/tu-usuario/choco-frutas.git
cd choco-frutas
```

### 2. Configurar Backend

#### 2.1. Navegar a la carpeta del backend
```bash
cd backend
```

#### 2.2. Configurar `application.properties`

Edita `src/main/resources/application.properties`:

```properties
# Configuración del servidor
server.port=8080

# Configuración de MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/chocofrutas_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=tu_password_mysql

# Configuración de JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Configuración de CORS
spring.web.cors.allowed-origins=http://localhost:5173
spring.web.cors.allowed-methods=GET,POST,PUT,DELETE,PATCH,OPTIONS
```

> ⚠️ **IMPORTANTE:** Reemplaza `tu_password_mysql` con tu contraseña de MySQL.

#### 2.3. Instalar dependencias
```bash
mvn clean install
```

### 3. Configurar Frontend

#### 3.1. Navegar a la carpeta del frontend
```bash
cd ../frontend
```

#### 3.2. Instalar dependencias
```bash
npm install
```

---

## 🗄️ Configuración de Base de Datos

### 1. Crear Base de Datos

Abre **MySQL Workbench** o tu cliente MySQL favorito y ejecuta:

```sql
CREATE DATABASE IF NOT EXISTS chocofrutas_db;
USE chocofrutas_db;
```

### 2. Ejecutar Script de Población

**Ubicación del script:** `backend/src/main/resources/data.sql`

Ejecuta el script SQL completo en MySQL Workbench:

```sql
-- =====================================================
-- Script SQL: Choco&Frutas
-- Fecha: 2024
-- Descripción: Población inicial de datos
-- =====================================================

-- Limpieza de tablas (opcional, solo para desarrollo)
SET SQL_SAFE_UPDATES = 0;
DELETE FROM producto;
DELETE FROM categoria;
DELETE FROM usuario;
SET SQL_SAFE_UPDATES = 1;

-- =====================================================
-- 1. INSERTAR CATEGORÍAS (5 categorías)
-- =====================================================
INSERT INTO categoria (id, nombre) VALUES 
(1, 'Frutos Secos'),
(2, 'Semillas'),
(3, 'Frutas Deshidratadas'),
(4, 'Chocolates'),
(5, 'Mezclas');

-- =====================================================
-- 2. INSERTAR USUARIO ADMINISTRADOR
-- =====================================================
INSERT INTO usuario (id, nombre, email, password, rol, activo, fecha_creacion) VALUES 
(1, 'Administrador', 'admin@chocofrutas.cl', '12345678', 'super-admin', true, NOW()),
(2, 'Vendedor Principal', 'vendedor@chocofrutas.cl', '12345678', 'vendedor', true, NOW()),
(3, 'Cliente Demo', 'cliente@chocofrutas.cl', '12345678', 'cliente', true, NOW());

-- =====================================================
-- 3. INSERTAR PRODUCTOS (16 productos)
-- =====================================================

-- CATEGORÍA 1: Frutos Secos (5 productos)
INSERT INTO producto (id, nombre, descripcion, precio, stock, imagen, activo, fecha_creacion, categoria_id) VALUES 
(1, 'Almendras Premium', 'Almendras naturales seleccionadas de primera calidad', 3500, 15, 'almendras.jpg', true, NOW(), 1),
(2, 'Nueces de Nogal', 'Nueces frescas y crujientes', 4200, 3, 'nueces.jpg', true, NOW(), 1),
(3, 'Pistachos Tostados', 'Pistachos con sal de mar', 5500, 8, 'pistachos.jpg', true, NOW(), 1),
(4, 'Castañas de Cajú', 'Castañas de cajú premium sin sal', 6000, 12, 'caju.jpg', true, NOW(), 1),
(5, 'Avellanas Enteras', 'Avellanas naturales enteras', 4800, 7, 'avellanas.jpg', true, NOW(), 1);

-- CATEGORÍA 2: Semillas (3 productos)
INSERT INTO producto (id, nombre, descripcion, precio, stock, imagen, activo, fecha_creacion, categoria_id) VALUES 
(6, 'Semillas de Chía', 'Chía orgánica de alta calidad', 2800, 20, 'chia.jpg', true, NOW(), 2),
(7, 'Semillas de Calabaza', 'Semillas de calabaza tostadas', 3200, 15, 'calabaza.jpg', true, NOW(), 2),
(8, 'Semillas de Maravilla', 'Maravilla sin sal', 2500, 4, 'maravilla.jpg', true, NOW(), 2);

-- CATEGORÍA 3: Frutas Deshidratadas (4 productos)
INSERT INTO producto (id, nombre, descripcion, precio, stock, imagen, activo, fecha_creacion, categoria_id) VALUES 
(9, 'Plátano Deshidratado', 'Rodajas de plátano naturalmente dulces', 3200, 2, 'platano.jpg', true, NOW(), 3),
(10, 'Manzana Deshidratada', 'Rodajas de manzana sin azúcar añadida', 3000, 10, 'manzana.jpg', true, NOW(), 3),
(11, 'Pasas Sultanas', 'Pasas dulces sin semilla', 2800, 18, 'pasas.jpg', true, NOW(), 3),
(12, 'Berries Mix', 'Mezcla de arándanos y cranberries', 5200, 6, 'berries.jpg', true, NOW(), 3);

-- CATEGORÍA 4: Chocolates (3 productos)
INSERT INTO producto (id, nombre, descripcion, precio, stock, imagen, activo, fecha_creacion, categoria_id) VALUES 
(13, 'Chocolate 70% Cacao', 'Chocolate amargo premium', 4500, 12, 'chocolate70.jpg', true, NOW(), 4),
(14, 'Chocolate con Leche', 'Chocolate suave y cremoso', 3800, 15, 'choco-leche.jpg', true, NOW(), 4),
(15, 'Chocolate con Avellanas', 'Chocolate con avellanas enteras', 5000, 9, 'choco-avellanas.jpg', true, NOW(), 4);

-- CATEGORÍA 5: Mezclas (1 producto)
INSERT INTO producto (id, nombre, descripcion, precio, stock, imagen, activo, fecha_creacion, categoria_id) VALUES 
(16, 'Mix Energético', 'Mezcla de frutos secos y semillas', 4200, 3, 'mix-energetico.jpg', true, NOW(), 5);

-- =====================================================
-- 4. VERIFICAR DATOS INSERTADOS
-- =====================================================
SELECT * FROM categoria;
SELECT id, nombre, email, rol, activo FROM usuario;
SELECT id, nombre, precio, stock, activo, categoria_id FROM producto;
```

### 3. Verificar Datos

```sql
-- Ver categorías
SELECT * FROM categoria;

-- Ver usuarios
SELECT id, nombre, email, rol FROM usuario;

-- Ver productos
SELECT id, nombre, precio, stock FROM producto;

-- Ver productos con stock bajo
SELECT * FROM producto WHERE stock < 5 AND activo = true;
```

---

## ▶️ Ejecución del Proyecto

### Opción 1: Ejecución Manual

#### 1. Iniciar Backend
```bash
cd backend
mvn spring-boot:run
```

El backend estará disponible en: `http://localhost:8081`

#### 2. Iniciar Frontend (en otra terminal)
```bash
cd frontend
npm run dev
```

El frontend estará disponible en: `http://localhost:5173`

### Opción 2: Compilar para Producción

#### Backend
```bash
cd backend
mvn clean package
java -jar target/backend-chocofruta-0.0.1-SNAPSHOT.jar
```

#### Frontend
```bash
cd frontend
npm run build
npm run preview
```

---

## 🔑 Credenciales de Prueba

### Usuario Administrador
```
Email: admin@chocofrutas.cl
Password: 12345678
Rol: admin
```

### Usuario Cliente
```
Email: cliente@gmail.com
Password: 12345678
Rol: cliente
```
## 📚 Documentación de API

### Swagger UI (Documentación Interactiva)

Una vez que el backend esté ejecutándose, accede a:

```
http://localhost:8081/swagger-ui.html
```

### Endpoints Principales

#### Autenticación
```http
POST /api/auth/login
POST /api/auth/refresh
POST /api/auth/logout
```

#### Productos
```http
GET    /api/productos              # Listar todos
GET    /api/productos/{id}         # Obtener por ID
POST   /api/productos              # Crear nuevo
PUT    /api/productos/{id}         # Actualizar
PATCH  /api/productos/{id}/desactivar  # Desactivar
DELETE /api/productos/{id}         # Eliminar
GET    /api/productos/buscar?nombre={nombre}  # Buscar por nombre
GET    /api/productos/categoria/{id}  # Filtrar por categoría
GET    /api/productos/stock-bajo   # Productos con stock < 5
```

#### Usuarios
```http
GET    /api/usuarios               # Listar todos
GET    /api/usuarios/{id}          # Obtener por ID
POST   /api/usuarios               # Crear nuevo
PUT    /api/usuarios/{id}          # Actualizar
PATCH  /api/usuarios/{id}/desactivar  # Desactivar
DELETE /api/usuarios/{id}          # Eliminar
```

#### Categorías
```http
GET    /api/categorias             # Listar todas
GET    /api/categorias/{id}        # Obtener por ID
POST   /api/categorias             # Crear nueva
PUT    /api/categorias/{id}        # Actualizar
DELETE /api/categorias/{id}        # Eliminar
```

#### Carrito
```http
GET    /api/carrito                # Listar ítems del carrito del usuario autenticado
POST   /api/carrito/items          # Agregar ítem (params: productoId, cantidad)
DELETE /api/carrito/items/{itemId} # Eliminar ítem
DELETE /api/carrito                # Vaciar carrito
```

#### Compras y Boletas
```http
POST   /api/checkout               # Genera boleta desde el carrito del usuario
GET    /api/compras                # Historial de boletas del usuario autenticado
GET    /api/admin/compras          # Listado de compras para administrador
GET    /api/admin/compras/{username} # Compras por usuario (admin)
```

### Ejemplo de Request (Crear Producto)

```json
POST /api/productos
Content-Type: application/json

{
  "nombre": "Almendras Premium",
  "descripcion": "Almendras naturales de primera calidad",
  "precio": 3500,
  "stock": 15,
  "categoria": {
    "id": 1
  },
  "imagen": "almendras.jpg"
}
```

---

## 📁 Estructura del Proyecto

```
choco-frutas/
│
├── backend/                          # Proyecto Spring Boot
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/backend/backend_chocofruta/
│   │   │   │   ├── config/          # Configuraciones (Swagger, CORS)
│   │   │   │   ├── controller/      # Controladores REST
│   │   │   │   ├── entities/        # Entidades JPA
│   │   │   │   ├── repositories/    # Repositorios Spring Data
│   │   │   │   └── services/        # Servicios de negocio
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── data.sql         # Script de población
│   │   └── test/                    # Tests con Mockito
│   └── pom.xml                      # Dependencias Maven
│
├── frontend/                         # Proyecto React + Vite
│   ├── public/
│   │   └── img/                     # Imágenes estáticas
│   ├── src/
│   │   ├── componentes/             # Componentes React
│   │   │   ├── CrearProducto/
│   │   │   ├── Dashboard/
│   │   │   ├── EditarProducto/
│   │   │   ├── Footer/
│   │   │   ├── Login/
│   │   │   ├── Navbar/
│   │   │   ├── Productos/
│   │   │   └── Usuarios/
│   │   ├── pages/                   # Páginas principales
│   │   │   ├── Home/
│   │   │   ├── Contacto/
│   │   │   └── Inventario/
│   │   ├── router/                  # Rutas protegidas
│   │   ├── utils/                   # Utilidades (sesión)
│   │   ├── App.jsx                  # Componente raíz
│   │   └── main.jsx                 # Punto de entrada
│   ├── package.json                 # Dependencias npm
│   └── vite.config.js              # Configuración Vite
│
└── README.md                        # Este archivo
```

---

## ✅ Funcionalidades Implementadas

### Backend (Spring Boot)
- ✅ API REST completa con CRUD
- ✅ Arquitectura en capas (Controller-Service-Repository)
- ✅ Persistencia con Spring Data JPA
- ✅ Validaciones en modelo y controlador
- ✅ Manejo de excepciones personalizado
- ✅ CORS configurado para frontend
- ✅ Documentación con Swagger/OpenAPI
- ✅ Tests unitarios con Mockito (54 tests)

### Frontend (React)
- ✅ SPA con React Router
- ✅ Componentes reutilizables
- ✅ Integración completa con API REST
- ✅ Validaciones en tiempo real
- ✅ Manejo de estados de carga y errores
- ✅ Diseño responsive con Bootstrap
- ✅ Sistema de autenticación
- ✅ Rutas protegidas
- ✅ Proceso de pago con sincronización de carrito y seguimiento de pedido
- ✅ Manejo de sesión con refresh de token
- ✅ Alertas visuales de stock

### Base de Datos
- ✅ Modelo relacional normalizado
- ✅ 5 categorías de productos
- ✅ 16 productos de prueba
- ✅ 3 usuarios con roles diferentes
- ✅ Relaciones (Producto ↔ Categoría)

---

## 🧪 Testing

### Ejecutar Tests del Backend

```bash
cd backend

# Ejecutar todos los tests
mvn test

# Ejecutar tests con reporte de cobertura
mvn test jacoco:report

# Ver reporte de cobertura
# El reporte estará en: target/site/jacoco/index.html
```

### Cobertura de Tests

| Módulo | Tests | Cobertura |
|--------|-------|-----------|
| ProductoServicesImpl | 11 tests | 100% |
| UsuarioServicesImpl | 12 tests | 100% |
| ProductoServices (Interface) | 9 tests | 100% |
| UsuarioServices (Interface) | 7 tests | 100% |
| ProductoRestController | 9 tests | 100% |
| UsuarioRestController | 6 tests | 100% |
| **TOTAL** | **54 tests** | **~95%** |

### Tecnologías de Testing
- **JUnit 5** - Framework de testing
- **Mockito** - Mocking de dependencias
- **MockMvc** - Testing de controladores REST

### Testing en React (Frontend)

#### Scripts
```bash
npm run test        # Ejecuta tests en Vitest
npm run test:ui     # Ejecuta interfaz interactiva de Vitest
# Opcional: cobertura
# npm i -D @vitest/coverage-v8 && npm run test:cov
```

#### Cobertura de pruebas frontend
- Pruebas de rutas protegidas (`ProtectedRoute`)
- Proceso de pago y manejo de errores (`ProcesoPago`)
- Seguimiento de pedido e historial (`SeguimientoPedido`)
- Navbar y navegación (`Navbar`)
- Validaciones de Login (`Login`)

### Documento de Testing (estructura sugerida)
- Introducción y propósito
- Herramientas y configuración
- Plan de testing
  - Componentes/pages seleccionados (5)
  - Ejemplos de código
  - Resultados (consola e interfaz gráfica)
- Conclusión

---

## 📸 Capturas de Pantalla

### Dashboard Administrativo
![Dashboard](docs/screenshots/dashboard.png)

### Gestión de Productos
![Productos](docs/screenshots/productos.png)

### Gestión de Usuarios
![Usuarios](docs/screenshots/usuarios.png)

### Login
![Login](docs/screenshots/login.png)

> 📌 **Nota:** Crea una carpeta `docs/screenshots/` y agrega capturas reales de tu proyecto.

---

## 🎨 Paleta de Colores

```css
/* Colores principales del proyecto */
--color-primary: #b07d62      /* Café/rosa principal */
--color-secondary: #8d7964    /* Café grisáceo */
--color-accent: #e2cfc3       /* Beige claro */
--color-dark: #7c4f32         /* Café oscuro */
--color-light: #f5eee6        /* Crema */
--text-dark: #3a2212          /* Texto principal */
```

---

## 🚀 Mejoras Futuras

- [ ] Paginación en listados
- [ ] Implementar paginación en listados
- [ ] Subida de imágenes al servidor
- [ ] Sistema de reportes en PDF
- [ ] Dashboard con gráficos (Chart.js)
- [ ] Notificaciones en tiempo real (WebSockets)
- [ ] Carrito de compras para clientes
- [ ] Sistema de pagos integrado
- [ ] Multi-idioma (i18n)

---

## 🤝 Contribución

Este es un proyecto académico, pero las contribuciones son bienvenidas:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/nuevaFuncionalidad`)
3. Commit tus cambios (`git commit -m 'Add: nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nuevaFuncionalidad`)
5. Abre un Pull Request

---

## 📝 Licencia

Este proyecto fue desarrollado con fines académicos para **DUOC UC**.

---

## 👥 Autores

**Tu Nombre**
- GitHub: [@tu-usuario](https://github.com/tu-usuario)
- Email: tu.correo@duocuc.cl

**Profesor Guía**
- Email: vpobletel@profesor.duoc.cl

---

## 🙏 Agradecimientos

- DUOC UC - Escuela de Informática y Telecomunicaciones
- Profesor Víctor Poblete

---

## 📞 Soporte

Si tienes dudas o problemas:

1. Revisa la [documentación de API](#-documentación-de-api)
2. Verifica los [requisitos previos](#-requisitos-previos)
3. Consulta las [credenciales de prueba](#-credenciales-de-prueba)
4. Contacta al autor por email

---

<div align="center">

**Desarrollado con ❤️ usando Spring Boot y React**

⭐ Si te gustó el proyecto, dale una estrella en GitHub ⭐

</div>
```
