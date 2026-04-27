# Sprint 6: Módulo Authorization (RBAC)

**Based on:** docs/plan/plan-001.md - Fase 6
**Date:** 2026-04-30
**Duration:** 20h
**Dependencies:** sprint-005.md

## Sprint Goal

Implementar el módulo authorization (RBAC) con entidades de roles, permisos y lógica de autorización centralizada.

## Tasks

### Critical (P0)

```java
// P0 FEATURE [api] Definir AuthorizationApi y DTOs (2h)
// P0 FEATURE [controller] Implementar AuthorizationController con endpoints para gestión de roles y permisos (3h)
// P0 FEATURE [service] Implementar AuthorizationServiceImpl con resolución de permisos efectivos (4h)
// P0 FEATURE [entity] Crear entidades Role, Permission, RolePermission, UserTenantRole en authorization/entity/ (3h)
// P0 FEATURE [repository] Crear AuthorizationRepository en authorization/repository/ (1h)
// P0 TEST [service] Pruebas unitarias y de integración para Authorization (3h)
```

### Important (P1)

```java
// P1 DOCS [api] Documentar endpoints Authorization en Swagger/OpenAPI (1h)
```

### Optional (P2/P3)

```java
// P2 TECHDEBT [service] Mejorar validaciones y manejo de errores en Authorization (1h)
```

## Version

**Target Version:** 1.5.0-SNAPSHOT

- Nuevo módulo de authorization (RBAC)
- Cambio menor por integración de authorization

## Definition of Done

- [ ] Gestión de roles y permisos funcional
- [ ] Pruebas unitarias y de integración completas
- [ ] Documentación Swagger actualizada

## Next Sprint

- **Blocks:** sprint-007.md (Módulo Ticketing)
