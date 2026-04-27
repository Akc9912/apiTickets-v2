src/main/java/com/empresa/
  modules/
    auth/
      api/
      controller/
      service/
      repository/
      entity/
      dto/
    users/
      api/
      controller/
      service/
      repository/
      entity/
      dto/
    ticketing/
      api/
      controller/
      service/
      repository/
      entity/
      dto/
  shared/
  security/
  config/
# Plan de Implementación Ajustado: Arquitectura Modular Monolith (RBAC + Multi-Tenant)

## Enfoque Recomendado

Se implementará una arquitectura **Modular Monolith** basada en:

* Package by Feature
* Vertical Slices

Stack:

* Java 21+
* Spring Boot
* MySQL 8+

### Módulos definidos

* `auth` → autenticación
* `identity` → usuario global
* `tenancy` → organizaciones (tenants)
* `membership` → relación usuario ↔ tenant
* `authorization` → roles y permisos (RBAC)
* `ticketing` → dominio de tickets

### Fuera de alcance

* Notificaciones
* Eventos
  → Deben eliminarse completamente si existen

---

# Fases y Roadmap

## Fase 1: Estructura Base y Configuración

### Estructura inicial

```
modules/
  auth/
  identity/
  tenancy/
  membership/
  authorization/
  ticketing/
```

Se mantienen:

```
shared/
security/
config/
```

---

### Dependencias

* Spring Boot
* Spring Data JPA
* Spring Security
* MySQL
* Lombok
* Swagger / OpenAPI

---

### Regla desde el inicio

> Ningún módulo de negocio puede implementar lógica de permisos directamente.

---

## Fase 2: Módulo Identity

Responsabilidad: **usuario global**

### Incluye

* Entidad `User`
* Email único
* Password
* `globalRole` (SUPER_ADMIN, ADMIN, USER)
* Estado (blocked, deleted)

### Estructura

```
identity/
  api/
  controller/
  service/
  repository/
  entity/
  dto/
```

---

## Fase 3: Módulo Auth

Responsabilidad: **autenticación**

### Incluye

* Login
* Generación de JWT
* Refresh tokens
* Integración con Spring Security

### Importante

* Usa `identity`
* NO maneja tenants
* NO maneja roles por tenant

---

## Fase 4: Módulo Tenancy

Responsabilidad: **organizaciones**

### Incluye

* Entidad `Tenant`
* Creación de tenants
* Metadata básica

---

## Fase 5: Módulo Membership

Responsabilidad: **relación usuario ↔ tenant**

### Incluye

* `user_tenants`
* Estado del usuario en el tenant (blocked)
* Invitaciones (opcional en esta fase)

### Rol en la arquitectura

Este módulo conecta:

* identity
* tenancy
* authorization

---

## Fase 6: Módulo Authorization (RBAC)

Responsabilidad: **gestión de permisos**

### Incluye

* `roles`
* `permissions`
* `role_permissions`
* `user_tenant_roles`

### También incluye

* `AuthorizationService`
* Resolución de permisos efectivos
* Posible cache de permisos

### Uso esperado

```
authorizationService.hasPermission(userId, tenantId, "TICKET_ASSIGN");
```

---

## Fase 7: Módulo Ticketing

Responsabilidad: **dominio de negocio**

### Incluye

* `Ticket`
* Estados
* Asignaciones
* Comentarios

### Restricción crítica

NO define:

* Roles
* Permisos

SIEMPRE delega en authorization:

```
authorizationService.checkPermission(...)
```

---

## Fase 8: Integración

* Comunicación entre módulos vía `api/`
* Documentación con Swagger
* Seguridad centralizada
* Eliminación total de eventos/notificaciones

---

# Estructura de Carpetas Final

```
src/main/java/com/empresa/
  modules/
    auth/
    identity/
    tenancy/
    membership/
    authorization/
    ticketing/
  shared/
  security/
  config/
```

---

# Cambios Clave Respecto al Plan Original

## Eliminación del módulo `users`

Se reemplaza por:

* `identity` → usuario global
* `membership` → relación con tenants

---

# Modelado de Roles

## Nivel global (identity)

```
globalRole:
  - SUPER_ADMIN
  - ADMIN
  - USER
```

---

## Nivel tenant (authorization)

* Roles dinámicos por tenant
* Permisos configurables
* Relación many-to-many con usuarios

---

# Reglas de Arquitectura

## 1. Separación estricta

* Auth ≠ Authorization
* Identity ≠ Membership

---

## 2. Permisos centralizados

❌ Incorrecto:

```
if (user.role == ADMIN)
```

✔ Correcto:

```
authorizationService.hasPermission(...)
```

---

## 3. Comunicación entre módulos

* Solo vía `api/`
* Sin dependencias directas entre módulos

---

## 4. Lógica de negocio

* No en controllers
* Implementada en services / use cases (vertical slices)

---

# Riesgos y Mitigaciones

### Riesgo: lógica de permisos en módulos de negocio

**Mitigación:** uso obligatorio de `AuthorizationService`

---

### Riesgo: mezclar tenant con usuario

**Mitigación:** uso del módulo `membership`

---

### Riesgo: sobreingeniería temprana

**Mitigación:**

* Implementar RBAC mínimo viable:

  * roles
  * permissions
* Sin UI compleja inicialmente

---

# Criterios de Éxito

* Soporte multi-tenant funcional
* Roles dinámicos por tenant
* Permisos desacoplados del dominio
* Módulo ticketing independiente de seguridad
* Ausencia total de eventos/notificaciones

---

# Orden de Implementación Recomendado

1. `identity`
2. `auth`
3. `tenancy`
4. `membership`
5. `authorization` (básico)
6. `ticketing`

---

# Próximos Pasos

1. Definir catálogo inicial de permisos
2. Diseñar contrato de `AuthorizationService`
3. Implementar resolución de permisos
4. Integrar permisos con JWT o contexto de request

> La correcta definición de permisos y autorización es crítica. Si esto se diseña mal, impacta en toda la arquitectura.
