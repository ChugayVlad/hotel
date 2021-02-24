SET NAMES utf8;

DROP DATABASE IF EXISTS hoteldb;
CREATE DATABASE hoteldb CHARACTER SET utf8 COLLATE utf8_bin;

USE hoteldb;

CREATE TABLE roles
(
    id   INTEGER     NOT NULL PRIMARY KEY,
    name VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE users
(
    id         INTEGER     NOT NULL auto_increment PRIMARY KEY,
    first_name VARCHAR(20) NOT NULL,
    last_name  VARCHAR(20) NOT NULL,
    email      VARCHAR(20) NOT NULL UNIQUE,
    password   VARCHAR(40) NOT NULL,
    role_id    INTEGER     NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE rooms
(
    id          INTEGER        NOT NULL auto_increment PRIMARY KEY,
    places      INTEGER        NOT NULL,
    price       DECIMAL(10, 2) NOT NULL,
    status      VARCHAR(20)    NOT NULL,
    type        VARCHAR(20)    NOT NULL,
    image       VARCHAR(20),
    description TEXT
);


CREATE TABLE orders
(
    id       INTEGER     NOT NULL auto_increment PRIMARY KEY,
    places   INTEGER     NOT NULL,
    type     VARCHAR(20) NOT NULL,
    date_in  DATE        NOT NULL,
    date_out DATE        NOT NULL,
    user_id  INTEGER     NOT NULL,
    room_id  INTEGER,
    sum      DECIMAL(10, 2),

    FOREIGN KEY (room_id) REFERENCES rooms (id) ON DELETE CASCADE ON UPDATE RESTRICT,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE bills
(
    id       INTEGER        NOT NULL auto_increment PRIMARY KEY,
    sum      DECIMAL(10, 2) NOT NULL,
    user_id  INTEGER        NOT NULL,
    room_id  INTEGER        NOT NULL,
    status   VARCHAR(20)    NOT NULL,
    date_in  DATE           NOT NULL,
    date_out DATE           NOT NULL,

    FOREIGN KEY (room_id) REFERENCES rooms (id) ON DELETE CASCADE ON UPDATE RESTRICT,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE RESTRICT
);

INSERT INTO roles
VALUES (0, 'admin');
INSERT INTO roles
VALUES (1, 'client');


INSERT INTO users
VALUES (DEFAULT, 'User', 'User', 'user@user.ua', '0b4e7a0e5fe84ad35fb5f95b9ceeac79', 1);
INSERT INTO users
VALUES (DEFAULT, 'Admin', 'Admin', 'admin@admin.ua', '0b4e7a0e5fe84ad35fb5f95b9ceeac79', 0);

INSERT INTO rooms
VALUES (DEFAULT, 1, 70.20, 'VACANT', 'STANDARD', '1.jpg',
        'Individual Design / Cotton sateen sheets / Double pillows / Soundproof windows /Ensuite bathroom with shower or bathtub please request at time of booking / Free WIFI Internet / FLAT SCREEN TV / SATELLITE TV / Bathroom Amenities / Hairdryer.');
INSERT INTO rooms
VALUES (DEFAULT, 3, 120.55, 'VACANT', 'DELUXE', '2.jpg',
        'Our Deluxe king size room has a seating area, ample storage, digital safe, minibar and luxurious duck down bedding. This room can also be configured with an extra roll-away bed for families of 3.');
INSERT INTO rooms
VALUES (DEFAULT, 3, 200.45, 'VACANT', 'BUSINESS_DELUXE', '3.jpg',
        'Individual Design / Cotton sateen sheets Queen bed and single bed or three separate beds, as you like Double pillows / Soundproof windows Ensuite bathroom with shower or bathtub please request at time of booking Free WIFI Internet / FLAT SCREEN TV SATELLITE TV / Minibar – Fridge Bar Bathroom Amenities / Hairdryer Air Conditioning / Internet Access');
INSERT INTO rooms
VALUES (DEFAULT, 2, 90.31, 'VACANT', 'STANDARD', '4.jpg',
        'Queen bed or two separate beds, as you like / Double pillows / Soundproof windows / Ensuite bathroom with shower or bathtub please request at time of booking / Free WIFI Internet / FLAT SCREEN TV / SATELLITE TV / Minibar – Fridge Bar / Bathroom Amenities / Hairdryer / Air Conditioning / Internet Access.');
INSERT INTO rooms
VALUES (DEFAULT, 2, 100.12, 'VACANT', 'ECONOMY', '5.jpg',
        'As our smallest budget rooms, the Compact bedrooms are suited for single occupancy or short-stay double occupancy as they have limited space and storage.');
INSERT INTO rooms
VALUES (DEFAULT, 4, 200.45, 'VACANT', 'BUSINESS_DELUXE', '6.jpg',
        'Cotton sateen sheets / Double pillows / Soundproof windows / Air Conditioning / Ensuite bathroom with shower or bathtub please request at time of booking / Free WIFI Internet / FLAT SCREEN TV / SATELLITE TV / Minibar – Fridge Bar / Bathroom Amenities / Hairdryer / Air Conditioning / Breakfast served in the room with no extra charge / Picture lights / Bathrobe / Slippers / Chianti Wine bottle in the room upon arrival / Fresh fruit basket in the room upon arrival.');
INSERT INTO rooms
VALUES (DEFAULT, 4, 300, 'VACANT', 'BUSINESS_DELUXE', '7.jpg',
        'Cotton sateen sheets / Double pillows / Soundproof windows / Air Conditioning / Ensuite bathroom with shower or bathtub please request at time of booking / Free WIFI Internet / FLAT SCREEN TV / SATELLITE TV / Minibar – Fridge Bar / Bathroom Amenities / Hairdryer / Air Conditioning / Breakfast served in the room with no extra charge / Picture lights / Bathrobe / Slippers / Chianti Wine bottle in the room upon arrival / Fresh fruit basket in the room upon arrival.');
INSERT INTO rooms
VALUES (DEFAULT, 1, 100.12, 'VACANT', 'ECONOMY', '8.jpg',
        'Queen bed or two separate beds, as you like / Double pillows / Soundproof windows / Ensuite bathroom with shower or bathtub please request at time of booking / Free WIFI Internet / FLAT SCREEN TV / SATELLITE TV / Minibar – Fridge Bar / Bathroom Amenities / Hairdryer / Air Conditioning / Internet Access.');
INSERT INTO rooms
VALUES (DEFAULT, 1, 120.55, 'VACANT', 'BUSINESS_DELUXE', '9.jpg',
        'Single Bed, queen or European king size / Cotton sateen sheets / Double pillows / Soundproof windows / Free WIFI Internet / Ensuite bathroom with shower or bathtub please request at time of booking / FLAT SCREEN TV / SATELLITE TV / Minibar – Fridge Bar / Bathroom Amenities / Hairdryer / Air Conditioning / Free Internet Access.');
INSERT INTO rooms
VALUES (DEFAULT, 2, 200.45, 'VACANT', 'DELUXE', '10.jpg',
        'Our Deluxe Twin/Large Double also provides views over landscaped gardens. It has a seating area, digital safe, minibar and luxurious duck down bedding. This room can be configured with either 2 single beds or zip and linked to provide a large double bed.');
