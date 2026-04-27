# Sprint 2: Módulo Identity

**Based on:** docs/plan/plan-001.md - Fase 2
**Date:** 2026-04-25
**Duration:** 20h
**Dependencies:** sprint-001.md

## Sprint Goal

Implementar el módulo identity (usuario global) con entidad User, roles globales y endpoints CRUD.

## Tasks

### Critical (P0)
```java
// P0 FEATURE [api] Definir IdentityApi y DTOs (2h)
// P0 FEATURE [controller] Implementar IdentityController con endpoints CRUD de usuario global (3h)
// P0 FEATURE [service] Implementar IdentityServiceImpl con lógica de negocio y validación de globalRole (4h)
// P0 FEATURE [entity] Crear entidad User con email único, password, globalRole (SUPER_ADMIN, ADMIN, USER), estado (blocked, deleted) (2h)
// P0 FEATURE [repository] Crear IdentityRepository en identity/repository/ (1h)
// P0 TEST [service] Pruebas unitarias y de integración para Identity (3h)
```

### Important (P1)
```java
// P1 DOCS [api] Documentar endpoints Identity en Swagger/OpenAPI (1h)
```

### Optional (P2/P3)
```java
// P2 TECHDEBT [service] Mejorar validaciones y manejo de errores en Identity (1h)
```

## Version

**Target Version:** 1.1.0-SNAPSHOT
- Nuevo modelo de usuario global y roles
- Cambio menor por migración de modelo

## Definition of Done

- [ ] CRUD de usuario global funcional
- [ ] User y globalRole implementados
- [ ] Pruebas unitarias y de integración completas
- [ ] Documentación Swagger actualizada

## Next Sprint

- **Blocks:** sprint-003.md (Módulo Auth)
