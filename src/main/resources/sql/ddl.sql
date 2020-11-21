DROP DATABASE IF EXISTS training;
CREATE DATABASE test;

CREATE TABLE mst_asset (
    id SERIAL PRIMARY KEY,
    category_id INTEGER,
    admin_name VARCHAR(32),
    asset_name VARCHAR(255),
    remarks VARCHAR(255),
    serial_id VARCHAR(255),
    purchase_date VARCHAR(255),
    maker_name VARCHAR(255),
    accessory VARCHAR(255),
    storing_place VARCHAR(255),
    delte_flag BOOLEAN NOT NULL DEFAULT 'FALSE',
    FOREIGN KEY (category_id) REFERENCES mst_category (category_id)
);

CREATE TABLE mst_category (
    category_id SERIAL PRIMARY KEY,
    category_name VARCHAR (255)
);


SELECT * FROM mst_category;
SELECT admin_name, delete_flag FROM mst_asset ORDER BY id;

DROP TABLE mst_asset;
DROP TABLE category;

ALTER TABLE mst_asset ADD delete_flag BOOLEAN DEFAULT 'FALSE';

UPDATE mst_asset SET delete_flag = false WHERE id = 1;
