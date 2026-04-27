# Sprint 8: Integración, Documentación y Calidad

**Based on:** docs/plan/plan-001.md - Fase 8
**Date:** 2026-05-02
**Duration:** 16h
**Dependencies:** sprint-007.md

## Sprint Goal

Integrar todos los módulos, documentar la API, validar seguridad y calidad, y asegurar la ausencia total de eventos/notificaciones.

## Tasks

### Critical (P0)

```java
// P0 FEATURE [api] Integrar módulos vía interfaces públicas (api/) (2h)
// P0 DOCS [api] Documentar todos los endpoints en Swagger/OpenAPI (2h)
// P0 SECURITY [security] Validar seguridad y acceso entre módulos solo vía API pública (2h)
// P0 REFACTOR [config] Eliminar cualquier referencia a notificaciones/eventos (2h)
// P0 TEST [service] Mejorar cobertura de pruebas y CI/CD (4h)
```

### Important (P1)

```java
// P1 VALIDATION [dto] Validar DTOs con Jakarta Validation (2h)
```

### Optional (P2/P3)

```java
// P2 DOCS [api] Mejorar documentación técnica y diagramas en docs/engineering (2h)
```

## Version

**Target Version:** 1.7.0 (STABLE RELEASE)

- Finalización de arquitectura modular
- Integración de módulos completa
- Sin cambios en endpoints públicos
- Release estable de la versión menor

## Release Checklist

Antes de cambiar de 1.7.0-SNAPSHOT a 1.7.0 y crear el tag:

- [ ] Sprint 8 completado
- [ ] Todos los tests unitarios pasan
- [ ] Tests de integración pasan
- [ ] Pruebas manuales de uso completas
- [ ] Documentación Swagger actualizada
- [ ] CHANGELOG.md actualizado con cambios de 1.7.0
- [ ] Sin referencias a eventos/notificaciones
- [ ] Seguridad validada entre módulos
- [ ] Build exitoso con Maven
- [ ] CI/CD pipeline pasa

**Pasos para release:**

1. Cambiar `1.7.0-SNAPSHOT` → `1.7.0` en pom.xml
2. Commit: `chore(release): prepare release 1.7.0`
3. Crear tag: `git tag -a v1.7.0 -m "Release 1.7.0 - Modular Monolith Architecture"`
4. Push tag: `git push origin v1.7.0`
5. Volver a `1.8.0-SNAPSHOT` para siguiente desarrollo

## Definition of Done

- [ ] Módulos integrados y funcionales
- [ ] Documentación Swagger completa
- [ ] Seguridad validada entre módulos
- [ ] Sin referencias a eventos/notificaciones
- [ ] Pruebas y CI/CD robustos
