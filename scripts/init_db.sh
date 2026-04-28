#!/bin/bash
# Script de inicialización para ApiTickets
# Uso: ./init_db.sh

set -e

DB_USER=${DB_USER:-root}
DB_PASS=${DB_PASS:-}
DB_NAME=${DB_NAME:-apiticket}
DB_HOST=${DB_HOST:-localhost}
DB_PORT=${DB_PORT:-3306}

SQL_FILE=${1:-"$(dirname "$0")/../script/database/01_init_db.sql"}

if [ ! -f "$SQL_FILE" ]; then
  echo "No se encontró el archivo de inicialización: $SQL_FILE"
  exit 1
fi

mysql -u "$DB_USER" -p"$DB_PASS" -h "$DB_HOST" -P "$DB_PORT" < "$SQL_FILE"

echo "Base de datos '$DB_NAME' inicializada correctamente."
