#! /bin/bash

set -eu o pipeline

psql -U "postgres" -d "postgres" -c "CREATE USER oyster_user WITH SUPERUSER PASSWORD 'oyster';"
psql -U "postgres" -d "postgres" -c "create database oyster_db WITH LC_COLLATE = 'C' TEMPLATE template0;"

cat /fixtures/*.sql | psql postgres://oyster_user@:5432/oyster_db
