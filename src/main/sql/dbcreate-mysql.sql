SET NAMES utf8;

DROP DATABASE IF EXISTS hoteldb;
CREATE DATABASE hoteldb CHARACTER SET utf8 COLLATE utf8_bin;

USE hoteldb;

CREATE TABLE roles(
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO roles VALUES(0, 'admin');
INSERT INTO roles VALUES(1, 'client');

CREATE TABLE users(
	id INTEGER NOT NULL auto_increment PRIMARY KEY,
	first_name VARCHAR (20) NOT NULL,
	last_name VARCHAR (20) NOT NULL,
	email VARCHAR (20) NOT NULL UNIQUE,
	password VARCHAR (20) NOT NULL,
	role_id INTEGER NOT NULL,
	FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE ON UPDATE RESTRICT
);

INSERT INTO users VALUES (DEFAULT, 'User', 'User', 'user', 'user', 1);
INSERT INTO users VALUES (DEFAULT, 'Admin', 'Admin', 'admin', 'admin', 0);



CREATE TABLE room_types(
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(20) NOT NULL UNIQUE
);

INSERT INTO room_types VALUES(1, 'Standard');
INSERT INTO room_types VALUES(2, 'Economy');
INSERT INTO room_types VALUES(3, 'Deluxe');
INSERT INTO room_types VALUES(4, 'Business deluxe');

CREATE TABLE rooms(
	id INTEGER NOT NULL auto_increment PRIMARY KEY,
    places INTEGER NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
	status VARCHAR(20) NOT NULL,
	type_id INTEGER NOT NULL,
    description TEXT,
    FOREIGN KEY (type_id) REFERENCES room_types(id)
);

INSERT INTO rooms VALUES (DEFAULT, 1, 70.20, 'VACANT', 1, 'Individual Design / Cotton sateen sheets / Double pillows / Soundproof windows /Ensuite bathroom with shower or bathtub please request at time of booking / Free WIFI Internet / FLAT SCREEN TV / SATELLITE TV / Bathroom Amenities / Hairdryer.');
INSERT INTO rooms VALUES (DEFAULT, 3, 120.55, 'VACANT', 3, 'Our Deluxe king size room has a seating area, ample storage, digital safe, minibar and luxurious duck down bedding. This room can also be configured with an extra roll-away bed for families of 3.');
INSERT INTO rooms VALUES (DEFAULT, 3, 200.45, 'VACANT', 4, 'Individual Design / Cotton sateen sheets Queen bed and single bed or three separate beds, as you like Double pillows / Soundproof windows Ensuite bathroom with shower or bathtub please request at time of booking Free WIFI Internet / FLAT SCREEN TV SATELLITE TV / Minibar – Fridge Bar Bathroom Amenities / Hairdryer Air Conditioning / Internet Access');
INSERT INTO rooms VALUES (DEFAULT, 2, 90.31, 'VACANT', 1, 'Queen bed or two separate beds, as you like / Double pillows / Soundproof windows / Ensuite bathroom with shower or bathtub please request at time of booking / Free WIFI Internet / FLAT SCREEN TV / SATELLITE TV / Minibar – Fridge Bar / Bathroom Amenities / Hairdryer / Air Conditioning / Internet Access.');
INSERT INTO rooms VALUES (DEFAULT, 2, 100.12, 'VACANT', 2, 'As our smallest budget rooms, the Compact bedrooms are suited for single occupancy or short-stay double occupancy as they have limited space and storage.');
INSERT INTO rooms VALUES (DEFAULT, 4, 200.45, 'VACANT', 4, 'Cotton sateen sheets / Double pillows / Soundproof windows / Air Conditioning / Ensuite bathroom with shower or bathtub please request at time of booking / Free WIFI Internet / FLAT SCREEN TV / SATELLITE TV / Minibar – Fridge Bar / Bathroom Amenities / Hairdryer / Air Conditioning / Breakfast served in the room with no extra charge / Picture lights / Bathrobe / Slippers / Chianti Wine bottle in the room upon arrival / Fresh fruit basket in the room upon arrival.');
INSERT INTO rooms VALUES (DEFAULT, 4, 300, 'VACANT', 4, 'Cotton sateen sheets / Double pillows / Soundproof windows / Air Conditioning / Ensuite bathroom with shower or bathtub please request at time of booking / Free WIFI Internet / FLAT SCREEN TV / SATELLITE TV / Minibar – Fridge Bar / Bathroom Amenities / Hairdryer / Air Conditioning / Breakfast served in the room with no extra charge / Picture lights / Bathrobe / Slippers / Chianti Wine bottle in the room upon arrival / Fresh fruit basket in the room upon arrival.');
INSERT INTO rooms VALUES (DEFAULT, 1, 100.12, 'VACANT', 2, 'Queen bed or two separate beds, as you like / Double pillows / Soundproof windows / Ensuite bathroom with shower or bathtub please request at time of booking / Free WIFI Internet / FLAT SCREEN TV / SATELLITE TV / Minibar – Fridge Bar / Bathroom Amenities / Hairdryer / Air Conditioning / Internet Access.');
INSERT INTO rooms VALUES (DEFAULT, 1, 120.55, 'VACANT', 4, 'Single Bed, queen or European king size / Cotton sateen sheets / Double pillows / Soundproof windows / Free WIFI Internet / Ensuite bathroom with shower or bathtub please request at time of booking / FLAT SCREEN TV / SATELLITE TV / Minibar – Fridge Bar / Bathroom Amenities / Hairdryer / Air Conditioning / Free Internet Access.');
INSERT INTO rooms VALUES (DEFAULT, 2, 200.45, 'VACANT', 3, 'Our Deluxe Twin/Large Double also provides views over landscaped gardens. It has a seating area, digital safe, minibar and luxurious duck down bedding. This room can be configured with either 2 single beds or zip and linked to provide a large double bed.');

CREATE TABLE orders (
    id INTEGER NOT NULL auto_increment PRIMARY KEY,
    places INTEGER NOT NULL,
	type_id INTEGER NOT NULL,
	date_in DATE NOT NULL,
	date_out DATE NOT NULL,
    user_id INTEGER NOT NULL,
    room_id INTEGER,
    sum DECIMAL(10, 2),

    FOREIGN KEY (room_id) REFERENCES rooms(id),
    FOREIGN KEY (type_id) REFERENCES room_types(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE bills(
    id INTEGER NOT NULL auto_increment PRIMARY KEY,
    sum DECIMAL(10, 2) NOT NULL,
    user_id INTEGER NOT NULL,
    room_id INTEGER NOT NULL,
    status VARCHAR(20) NOT NULL,
	date_in DATE NOT NULL,
	date_out DATE NOT NULL,

    FOREIGN KEY (room_id) REFERENCES rooms(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

