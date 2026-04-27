-- =========================================
-- SCRIPT COMPLETO DE BASE DE DATOS
-- SISTEMA DE TICKETS v1.0.0
-- =========================================


-- Crear y usar la base de datos
CREATE DATABASE IF NOT EXISTS apiticket 
    CHARACTER SET utf8mb4 
    COLLATE utf8mb4_unicode_ci;

USE apiticket;

-- =========================================
-- DOMAIN: AUTH
-- =========================================

CREATE TABLE users (
    id BINARY(16) PRIMARY KEY,

    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,

    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,

    global_role ENUM('SUPER_ADMIN', 'ADMIN', 'USER') NOT NULL DEFAULT 'USER',

    blocked TINYINT(1) NOT NULL DEFAULT 0,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE tenants (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE user_tenants (
    id BINARY(16) PRIMARY KEY,

    user_id BINARY(16) NOT NULL,
    tenant_id BINARY(16) NOT NULL,

    blocked TINYINT(1) NOT NULL DEFAULT 0,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    UNIQUE KEY uk_user_tenant (user_id, tenant_id),

    INDEX idx_ut_user (user_id),
    INDEX idx_ut_tenant (tenant_id),

    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (tenant_id) REFERENCES tenants(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE permissions (
    id BINARY(16) PRIMARY KEY,

    code VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255) NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE roles (
    id BINARY(16) PRIMARY KEY,

    tenant_id BINARY(16) NOT NULL,

    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),

    is_system TINYINT(1) NOT NULL DEFAULT 0,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    UNIQUE KEY uk_role_name_per_tenant (tenant_id, name),

    INDEX idx_roles_tenant (tenant_id),

    FOREIGN KEY (tenant_id) REFERENCES tenants(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE role_permissions (
    role_id BINARY(16) NOT NULL,
    permission_id BINARY(16) NOT NULL,

    PRIMARY KEY (role_id, permission_id),

    FOREIGN KEY (role_id) REFERENCES roles(id),
    FOREIGN KEY (permission_id) REFERENCES permissions(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE user_tenant_roles (
    user_tenant_id BINARY(16) NOT NULL,
    role_id BINARY(16) NOT NULL,

    PRIMARY KEY (user_tenant_id, role_id),

    FOREIGN KEY (user_tenant_id) REFERENCES user_tenants(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

