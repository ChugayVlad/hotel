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

INSERT INTO users VALUES (DEFAULT, 'Oleh', 'Abcd', 'aaa', 'aaa', 1);
INSERT INTO users VALUES (DEFAULT, 'Ihor', 'Bbbb', 'bbb', 'bbb', 0);



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
    price DECIMAL(10,2) NOT NULL,
	status VARCHAR(20) NOT NULL,
	type_id INTEGER NOT NULL,
    FOREIGN KEY (type_id) REFERENCES room_types(id)
);

INSERT INTO rooms VALUES (DEFAULT, 1, 70.20, 'VACANT', 1);
INSERT INTO rooms VALUES (DEFAULT, 2, 90.31, 'VACANT', 1);
INSERT INTO rooms VALUES (DEFAULT, 2, 100.12, 'VACANT', 2);
INSERT INTO rooms VALUES (DEFAULT, 3, 120.55, 'VACANT', 3);
INSERT INTO rooms VALUES (DEFAULT, 4, 200.45, 'VACANT', 4);

CREATE TABLE orders (
    id INTEGER NOT NULL auto_increment PRIMARY KEY,
    places INTEGER NOT NULL,
	type_id INTEGER NOT NULL,
	date_in DATE NOT NULL,
	date_out DATE NOT NULL,
    user_id INTEGER NOT NULL,
    room_id INTEGER,

    FOREIGN KEY (room_id) REFERENCES rooms(id),
    FOREIGN KEY (type_id) REFERENCES room_types(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE bills(
    id INTEGER NOT NULL auto_increment PRIMARY KEY,
    sum DECIMAL(10,2) NOT NULL,
    user_id INTEGER NOT NULL,
    room_id INTEGER NOT NULL,
    status VARCHAR(20) NOT NULL,

    FOREIGN KEY (room_id) REFERENCES rooms(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

