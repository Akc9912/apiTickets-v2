@echo off
REM Script de inicialización para ApiTickets (Windows)
REM Uso: init_db.bat [ruta_sql]

setlocal
set DB_USER=%DB_USER%
set DB_PASS=%DB_PASS%
set DB_NAME=%DB_NAME%
set DB_HOST=%DB_HOST%
set DB_PORT=%DB_PORT%

if "%1"=="" (
  set SQL_FILE=..\script\database\01_init_db.sql
) else (
  set SQL_FILE=%1
)

if not exist "%SQL_FILE%" (
  echo No se encontró el archivo de inicialización: %SQL_FILE%
  exit /b 1
)

mysql -u %DB_USER% -p%DB_PASS% -h %DB_HOST% -P %DB_PORT% < "%SQL_FILE%"
if errorlevel 1 exit /b 1

echo Base de datos '%DB_NAME%' inicializada correctamente.
endlocal
