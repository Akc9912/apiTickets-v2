# Sprint 7: Módulo Ticketing

**Based on:** docs/plan/plan-001.md - Fase 7
**Date:** 2026-05-01
**Duration:** 20h
**Dependencies:** sprint-006.md

## Sprint Goal

Implementar el módulo ticketing (dominio de tickets) con entidades, endpoints y lógica de negocio, delegando permisos a AuthorizationService.

## Tasks

### Critical (P0)

```java
// P0 FEATURE [api] Definir TicketingApi y DTOs (2h)
// P0 FEATURE [controller] Implementar TicketingController con endpoints CRUD de tickets, estados, asignaciones, comentarios (3h)
// P0 FEATURE [service] Implementar TicketingServiceImpl con lógica de negocio y delegación de permisos a AuthorizationService (4h)
// P0 FEATURE [entity] Crear entidades Ticket, TicketState, Assignment, Comment en ticketing/entity/ (3h)
// P0 FEATURE [repository] Crear TicketingRepository en ticketing/repository/ (1h)
// P0 TEST [service] Pruebas unitarias y de integración para Ticketing (3h)
```

### Important (P1)

```java
// P1 DOCS [api] Documentar endpoints Ticketing en Swagger/OpenAPI (1h)
```

### Optional (P2/P3)

```java
// P2 TECHDEBT [service] Mejorar validaciones y manejo de errores en Ticketing (1h)
```

## Version

**Target Version:** 1.6.0-SNAPSHOT

- Nuevo módulo de ticketing (dominio de tickets)
- Cambio menor por integración de ticketing

## Definition of Done

- [ ] CRUD de tickets funcional
- [ ] Pruebas unitarias y de integración completas
- [ ] Documentación Swagger actualizada

## Next Sprint

- **Blocks:** sprint-008.md (Integración, Documentación y Calidad)
