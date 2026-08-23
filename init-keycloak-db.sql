CREATE USER keycloak WITH PASSWORD 'keycloak';
CREATE DATABASE keycloak_db OWNER keycloak;
GRANT ALL PRIVILEGES ON DATABASE keycloak_db TO keycloak;