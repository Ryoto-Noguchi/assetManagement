-- mst_categoryのレコード挿入
INSERT INTO mst_category (category_name) VALUES ('PC');
INSERT INTO mst_category (category_name) VALUES ('モニタ');
INSERT INTO mst_category (category_name) VALUES ('ソフト');
INSERT INTO mst_category (category_name) VALUES ('書籍');
INSERT INTO mst_category (category_name) VALUES ('その他');

SELECT * FROM mst_category;

-- mst_assetのレコード挿入
INSERT INTO mst_asset (category_id, admin_name, asset_name, remarks, serial_id, purchase_date, maker_name, accessory, storing_place) VALUES (1, 'John', 'A', 'nothing', 'A10000', '2020-10-10', 'Apple', 'X', 'B1' );
INSERT INTO mst_asset (category_id, admin_name, asset_name, remarks, serial_id, purchase_date, maker_name, accessory, storing_place) VALUES (1, 'Ben', 'B', 'nothing', 'A20000', '2020-10-20', 'Google', 'Z', 'B1' );
INSERT INTO mst_asset (category_id, admin_name, asset_name, remarks, serial_id, purchase_date, maker_name, accessory, storing_place) VALUES (1, 'Caroline', 'C', 'nothing', 'A30000', '2020-10-20', 'SHARP', 'X', 'B1' );
INSERT INTO mst_asset (category_id, admin_name, asset_name, remarks, serial_id, purchase_date, maker_name, accessory, storing_place) VALUES (1, 'Stephan', 'D', 'nothing', 'A40000', '2020-10-10', 'Amazon', 'X', 'B1' );
INSERT INTO mst_asset (category_id, admin_name, asset_name, remarks, serial_id, purchase_date, maker_name, accessory, storing_place) VALUES (3, 'Joey', 'E', 'nothing', 'A50000', '2020-10-30', 'MicroSoft', 'X', 'B1' );
INSERT INTO mst_asset (category_id, admin_name, asset_name, remarks, serial_id, purchase_date, maker_name, accessory, storing_place) VALUES (1, 'Sean', 'F', 'nothing', 'A60000', '2020-10-15', 'Apple', 'X', 'B1' );
INSERT INTO mst_asset (category_id, admin_name, asset_name, remarks, serial_id, purchase_date, maker_name, accessory, storing_place) VALUES (1, 'Jacob', 'G', 'nothing', 'A20000', '2020-11-10', 'TOSHIBA', 'X', 'B1' );
INSERT INTO mst_asset (category_id, admin_name, asset_name, remarks, serial_id, purchase_date, maker_name, accessory, storing_place) VALUES (1, 'Tania', 'H', 'nothing', 'A30000', '2020-12-10', 'DELL', 'X', 'B1' );
INSERT INTO mst_asset (category_id, admin_name, asset_name, remarks, serial_id, purchase_date, maker_name, accessory, storing_place) VALUES (1, 'Melanie', 'I', 'nothing', 'A14000', '2020-09-10', 'ASUS', 'X', 'B1' );
INSERT INTO mst_asset (category_id, admin_name, asset_name, remarks, serial_id, purchase_date, maker_name, accessory, storing_place) VALUES (1, 'Jakson', 'J', 'nothing', 'B10000', '2019-10-10', 'SONY', 'X', 'B1' );
INSERT INTO mst_asset (category_id, admin_name, asset_name, remarks, serial_id, purchase_date, maker_name, accessory, storing_place) VALUES (2, 'Adam', 'K', 'nothing', 'A60000', '2020-12-16', 'Nicon', 'X', 'B2' );
INSERT INTO mst_asset (category_id, admin_name, asset_name, remarks, serial_id, purchase_date, maker_name, accessory, storing_place) VALUES (2, 'Bianca', 'L', 'nothing', 'A15000', '2020-10-19', 'HAWEI', 'Y', 'B2' );
INSERT INTO mst_asset (category_id, admin_name, asset_name, remarks, serial_id, purchase_date, maker_name, accessory, storing_place) VALUES (1, 'Casandra', 'M', 'nothing', 'A30000', '2021-10-10', 'FUJITSU', 'X', 'B3' );
INSERT INTO mst_asset (category_id, admin_name, asset_name, remarks, serial_id, purchase_date, maker_name, accessory, storing_place) VALUES (1, 'Dustin', 'N', 'nothing', 'B40000', '2016-10-10', 'OPPO', 'X', 'B1' );
INSERT INTO mst_asset (category_id, admin_name, asset_name, remarks, serial_id, purchase_date, maker_name, accessory, storing_place) VALUES (3, 'Erica', 'O', 'nothing', 'A11000', '2020-10-13', 'MicroSoft', 'X', 'B1' );
INSERT INTO mst_asset (category_id, admin_name, asset_name, remarks, serial_id, purchase_date, maker_name, accessory, storing_place) VALUES (3, 'Felix', 'P', 'nothing', 'A16000', '2020-12-12', 'Nintendo', 'X', 'B3' );
INSERT INTO mst_asset (category_id, admin_name, asset_name, remarks, serial_id, purchase_date, maker_name, accessory, storing_place) VALUES (4, 'Gill', 'Q', 'nothing', 'A50000', '2020-10-30', 'NIKKEI', 'X', 'B2' );
INSERT INTO mst_asset (category_id, admin_name, asset_name, remarks, serial_id, purchase_date, maker_name, accessory, storing_place) VALUES (4, 'Hide', 'R', 'nothing', 'A30000', '2018-10-30', 'MARVEL', 'X', 'B2' );
INSERT INTO mst_asset (category_id, admin_name, asset_name, remarks, serial_id, purchase_date, maker_name, accessory, storing_place) VALUES (4, 'Jake', 'S', 'nothing', 'A62000', '2019-10-30', 'MicroSoft', 'X', 'B1' );
INSERT INTO mst_asset (category_id, admin_name, asset_name, remarks, serial_id, purchase_date, maker_name, accessory, storing_place) VALUES (5, 'Kate', 'T', 'nothing', 'A31000', '2017-10-30', 'SEGA', 'X', 'B3' );
INSERT INTO mst_asset (category_id, admin_name, asset_name, remarks, serial_id, purchase_date, maker_name, accessory, storing_place) VALUES (5, 'Lourence', 'U', 'nothing', 'A34000', '2018-11-30', 'Nintendo', 'X', 'B2' );

SELECT * FROM mst_asset ORDER BY admin_name ASC;