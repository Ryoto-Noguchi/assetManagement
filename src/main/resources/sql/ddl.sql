DROP DATABASE IF EXISTS training;
CREATE DATABASE test;

CREATE TABLE mst_asset (
    id SERIAL PRIMARY KEY,
    category_id INTEGER,
    admin_name VARCHAR(32),
    asset_name VARCHAR(255),
    remarks VARCHAR(255)
);

CREATE TABLE category (
    id INTEGER NOT NULL,
    category_name VARCHAR (255)
);

SELECT * FROM category;

DROP TABLE mst_asset;