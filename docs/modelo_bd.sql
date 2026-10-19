-- Modelo de base de datos de Petagram (SQLite)
-- Base de datos: petagram.db
-- La tabla "mascota" es idéntica al POJO Mascota.java

CREATE TABLE mascota (
    id              INTEGER PRIMARY KEY,          -- Mascota.id (identificador único)
    nombre          TEXT    NOT NULL,             -- Mascota.nombre
    foto            TEXT    NOT NULL,             -- Mascota.foto (nombre del drawable)
    raiting         INTEGER NOT NULL DEFAULT 0,   -- Mascota.raiting (huesos recibidos)
    ultimo_raiting  INTEGER NOT NULL              -- Mascota.ultimoRaiting (fecha en millis)
);

-- Al dar raiting: se guarda (o actualiza) la mascota completa...
INSERT OR REPLACE INTO mascota (id, nombre, foto, raiting, ultimo_raiting)
VALUES (?, ?, ?, ?, ?);

-- ...y se conservan solo las últimas 5 mascotas con raiting
DELETE FROM mascota
WHERE id NOT IN (SELECT id FROM mascota ORDER BY ultimo_raiting DESC LIMIT 5);

-- Consulta de la pantalla de favoritas
SELECT * FROM mascota ORDER BY ultimo_raiting DESC LIMIT 5;
