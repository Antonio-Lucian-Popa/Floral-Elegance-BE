# 🌸 Flower Order API

A complete flower ordering backend application built with **Spring Boot**, secured by **Keycloak**, and supporting admin panel features like flower/decoration management, image uploads, and cart preview.

---

## 🚀 Technologies Used

- Java 17
- Spring Boot 3
- Spring Security + OAuth2 (Keycloak)
- Spring Data JPA
- PostgreSQL / H2
- MapStruct
- Multipart file upload (local)
- RESTful API design
- Role-based access control (ADMIN / CLIENT)

---

## 🔐 Authentication & Roles

- Users register and log in using Keycloak (JWT)
- After login, the app uses JWT to secure all requests
- Roles:
    - `ADMIN`: Can manage flowers, decorations, images, see all orders
    - `CLIENT`: Can view and place orders, see only their own data

---

## 📦 API Structure

### 🔐 Auth (Keycloak)
- `POST /api/auth/register` – Register new user
- `POST /api/auth/login` – Get JWT token

### 🌸 Flowers (Public / Admin)
- `GET /api/flowers` – List all
- `GET /api/flowers/popular` – Most ordered in last 30 days
- `GET /api/flowers?minPrice=10&maxPrice=100&sort=price,asc` – Filter/sort
- `POST /api/admin/flowers` – Add flower
- `PUT /api/admin/flowers/{id}` – Update flower
- `DELETE /api/admin/flowers/{id}` – Delete flower
- `POST /api/admin/flowers/{id}/upload` – Upload image

### 🎀 Decorations (Public / Admin)
- `GET /api/decorations` – List all
- `POST /api/admin/decorations` – Add decoration
- `PUT /api/admin/decorations/{id}` – Update
- `DELETE /api/admin/decorations/{id}` – Delete

### 📦 Orders (Client)
- `GET /api/orders/user/{userId}` – My orders
- `GET /api/orders/{orderId}/user/{userId}` – Order by ID
- `POST /api/orders/user/{userId}` – Place new order
- `PUT /api/orders/{orderId}/cancel/user/{userId}` – Cancel order

### 🛒 Cart (Optional)
- `GET /api/cart/user/{userId}` – See cart preview
- `POST /api/cart/user/{userId}` – Add to cart
- `DELETE /api/cart/{itemId}/user/{userId}` – Remove from cart
- `POST /api/cart/checkout/user/{userId}` – Place order from cart

---

## 📂 File Upload

Images are uploaded using `MultipartFile` and stored locally under:

## 🧾 Error Handling

All exceptions are handled via `@ControllerAdvice`:
- `NotFoundException`
- `FileStorageException`
- Runtime fallback → 400 Bad Request

---

## 🔧 Running Locally

1. Make sure PostgreSQL or H2 is running
2. Configure `application.yml`:
```yaml
keycloak:
  auth-server-url: http://localhost:8080
  realm: flower-app
  resource: flower-client
  credentials:
    secret: xxx

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/flowers
    username: postgres
    password: yourpassword