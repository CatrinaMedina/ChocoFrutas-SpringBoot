# Backend ChocoFruta

## Requisitos

- Java 21+, Maven, MySQL 8+
- Node.js 18+ (para frontend)

## Configuración

- Edita `src/main/resources/application.properties` con tu MySQL:
  - `spring.datasource.url=jdbc:mysql://localhost:3306/basechoco?...`
  - `spring.datasource.username=<tu_usuario>`
  - `server.port=8081`
- Inicializa datos ejecutando el script SQL: `basechoco.sql` en la raíz del repositorio.

## Ejecución

- Desarrollo: `./mvnw.cmd spring-boot:run`
- Build: `./mvnw.cmd -DskipTests package`
- API: `http://localhost:8081`
- Swagger: `http://localhost:8081/swagger-ui.html`

## Autenticación

- Login: `POST /api/auth/login` con cuerpo JSON `{"username":"<usuario>","password":"<clave>"}`
- Refresh: `POST /api/auth/refresh` (encabezado `Authorization: Bearer <token>`)
- Logout: `POST /api/auth/logout`
- En rutas protegidas incluir `Authorization: Bearer <token>`

## Endpoints

- Auth: `POST /api/auth/login`, `POST /api/auth/refresh`, `POST /api/auth/logout`
- Categorías: `GET /api/categorias`, `POST /api/categorias`, `GET /api/categorias/{id}`, `PUT /api/categorias/{id}`, `DELETE /api/categorias/{id}`
- Productos: `GET /api/productos?page&size`, `POST /api/productos`, `PUT /api/productos/{id}`, `DELETE /api/productos/{id}`, `PATCH /api/productos/{id}/activar`, `PATCH /api/productos/{id}/desactivar`, `GET /api/productos/stock-bajo`
- Carrito: `GET /api/carrito`, `POST /api/carrito`, `PUT /api/carrito/{itemId}`, `DELETE /api/carrito/{itemId}`, `DELETE /api/carrito`
- Boletas: `POST /api/boletas`, `GET /api/boletas/historial`
- Admin Compras: `GET /api/admin/compras?page&size`
- Upload: `POST /api/upload/imagenes`, `GET /api/upload/imagenes/{filename}`

## Credenciales de prueba

- Admin: usuario creado por el `basechoco.sql` (username `admin`). Define la clave según tu seed.
- Clientes de ejemplo incluidos en el seed.

## Notas

- CORS frontend: `http://localhost:5173`
- JWT incluye `sub` (username) y `rol`. Usa rol `ADMIN` para rutas de administración.
