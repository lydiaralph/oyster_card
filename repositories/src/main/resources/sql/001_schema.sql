CREATE TABLE IF NOT EXISTS stations
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(100) UNIQUE NOT NULL,
    min_zone INTEGER             NOT NULL,
    max_zone INTEGER             NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
    id VARCHAR(20) UNIQUE PRIMARY KEY,
    user_name   VARCHAR(200),
    balance     MONEY,
    day_total   MONEY
);

CREATE TABLE IF NOT EXISTS journeys
(
    id                SERIAL PRIMARY KEY,
    user_id           VARCHAR REFERENCES users (id) NOT NULL,
    station_in        INT REFERENCES stations (id)           NOT NULL,
    station_out       INT REFERENCES stations (id),
    date_started      DATE default NOW(),
    journey_completed boolean
);

