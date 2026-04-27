# Decisión de Diseño: Sistema de Roles y Permisos por Tenant (RBAC)

## Contexto

La plataforma evoluciona desde un sistema de soporte hacia una solución más amplia (CRM + futuras integraciones). Esto implica que:

- Cada **tenant (organización)** puede tener necesidades distintas.
- Los **usuarios globales** pueden pertenecer a múltiples tenants.
- Un mismo usuario puede cumplir **múltiples funciones dentro de un tenant**.
- Los roles no pueden ser rígidos ni universales.

---

## Problema con el enfoque inicial

El uso de un `ENUM` para roles por tenant presenta limitaciones:

- No permite personalización por tenant.
- Obliga a definir roles estáticos globales.
- No soporta múltiples roles por usuario.
- Genera explosión de combinaciones (ej: `ADMIN_DEV_SUPPORT`).

Este enfoque no escala para un sistema flexible.

---

## Decisión

Se adopta un modelo **RBAC (Role-Based Access Control) por tenant**, con las siguientes características:

### 1. Separación de responsabilidades

- **Usuarios (`users`)**
  - Identidad global
  - Autenticación
  - Rol global de plataforma (`SUPER_ADMIN`, `ADMIN`, `USER`)

- **Tenants (`tenants`)**
  - Organizaciones independientes

- **Relación (`user_tenants`)**
  - Vincula usuarios con tenants

---

### 2. Roles definidos por tenant

Cada tenant puede:

- Crear roles propios
- Modificar roles existentes
- Adaptar permisos según su operación

Ejemplos:

- `DEVELOPER`
- `SUPPORT_AGENT`
- `SALES`
- `ADMIN_INTERNO`

---

### 3. Permisos como unidad mínima

Los permisos son:

- Definidos globalmente
- Estables en el tiempo
- Reutilizables entre tenants

Ejemplos:

- `TICKET_READ`
- `TICKET_ASSIGN`
- `USER_MANAGE`
- `CRM_EDIT`

---

### 4. Relación Roles ↔ Permisos

Un rol es un conjunto de permisos.

Esto permite:

- Reutilización
- Composición flexible
- Evitar duplicación lógica

---

### 5. Múltiples roles por usuario

Un usuario dentro de un tenant puede tener varios roles simultáneamente.

Ejemplo real:

- `DEVELOPER` → trabaja tickets
- `ADMIN_PARTIAL` → gestiona ciertas configuraciones

Esto evita crear roles combinados artificiales.

---

## Modelo de datos (resumen)

- `users`
- `tenants`
- `user_tenants`
- `roles`
- `permissions`
- `role_permissions`
- `user_tenant_roles`

---

## Flujo de autorización

1. Usuario autenticado
2. Selección de tenant activo
3. Obtención de roles del usuario en ese tenant
4. Resolución de permisos (unión de todos los roles)
5. Evaluación en backend

---

## Regla clave de seguridad

```text
Si global_role == SUPER_ADMIN:
    bypass de permisos por tenant
Sino:
    validar permisos según RBAC
```

---

## Ventajas de este enfoque

- Escalabilidad funcional
- Adaptabilidad por cliente
- Evita rediseños futuros
- Permite features avanzadas:
  - permisos granulares
  - módulos (CRM, soporte, etc.)
  - integraciones externas
  - feature flags por tenant

---

## Consideraciones técnicas

### 1. Performance

- Evitar queries repetitivas
- Cachear permisos (JWT o backend)

---

### 2. Consistencia

- Los permisos deben ser globales (no editables por tenant)
- Los roles sí son configurables

---

### 3. Migración progresiva

- Se puede empezar con roles simples
- Evolucionar a múltiples roles sin romper el modelo

---

### 4. Tenant activo

Debe definirse claramente:

- Header (`X-Tenant-Id`)
- o dentro del JWT

---

## Riesgos y mitigaciones

| Riesgo                  | Mitigación                             |
| ----------------------- | -------------------------------------- |
| Complejidad inicial     | Implementación incremental             |
| Performance             | Cache de permisos                      |
| Mal diseño de permisos  | Definir catálogo claro desde el inicio |
| Errores de autorización | Centralizar lógica en backend          |

---

## Conclusión

Este diseño permite que la plataforma:

- Escale de soporte a CRM sin fricción
- Se adapte a distintos modelos de negocio
- Mantenga control fino sobre permisos
- Evite refactors estructurales en el futuro

Es una base sólida para un sistema multi-tenant moderno.

---

## Próximos pasos sugeridos

1. Definir catálogo inicial de permisos
2. Crear roles base automáticos al crear un tenant
3. Implementar resolución de permisos en backend
4. Integrar con sistema de autenticación (JWT / sesión)
5. Añadir caching de permisos
