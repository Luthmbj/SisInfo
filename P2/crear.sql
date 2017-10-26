CREATE TABLE Usuario (
	email VARCHAR(20) NOT NULL,
	nombre VARCHAR(20) NOT NULL,
	apellidos VARCHAR(20) NOT NULL,
	password VARCHAR(20) NOT NULL,
	fnacimiento VARCHAR(20) NOT NULL,
	CONSTRAINT usuarioPK PRIMARY KEY(email)
);

CREATE TABLE Coche (
	url VARCHAR(40) PRIMARY KEY,
	nombre VARCHAR(20)
);

CREATE TABLE Vuelo (
	url VARCHAR(40) PRIMARY KEY,
	nombre VARCHAR(20)
);

CREATE TABLE Hotel (
	url VARCHAR(40) PRIMARY KEY,
	nombre VARCHAR(20)
);

CREATE TABLE BVuelo (
	id INT NOT NULL,
	usuario VARCHAR(20) NOT NULL,
	finicio VARCHAR(20),
	ffin VARCHAR(20),
	destino VARCHAR(20),
	tipo VARCHAR(20),
	CONSTRAINT BVueloFK FOREIGN KEY(usuario) REFERENCES Usuario(email),
	CONSTRAINT BVueloPK PRIMARY KEY(usuario,id)
);

CREATE TABLE BHotel (
	id INT NOT NULL,
	usuario VARCHAR(20) NOT NULL,
	finicio VARCHAR(20),
	ffin VARCHAR(20),
	lugar VARCHAR(20),
	nhabitaciones INT,
	npersonas INT,
	CONSTRAINT BHotelFK FOREIGN KEY(usuario) REFERENCES Usuario(email),
	CONSTRAINT BHotelPK PRIMARY KEY(usuario,id)
);


CREATE TABLE BCoche (
	id INT NOT NULL,
	usuario VARCHAR(20) NOT NULL,
	finicio VARCHAR(20),
	ffin VARCHAR(20),
	lugar VARCHAR(20),

	CONSTRAINT BCocheFK FOREIGN KEY(usuario) REFERENCES Usuario(email),
	CONSTRAINT BCochePK PRIMARY KEY(usuario,id)
);

CREATE TABLE PerteneceV (
	busqueda INT REFERENCES BVuelo(id),
	vuelo VARCHAR(40) REFERENCES Vuelo(url),
	CONSTRAINT PerteneceVPK PRIMARY KEY (busqueda,vuelo)
);



CREATE TABLE PerteneceH (
	busqueda INT REFERENCES BHotel(id),
	hotel VARCHAR(40) REFERENCES Hotel(url),
	CONSTRAINT id PRIMARY KEY (busqueda,hotel)
);


CREATE TABLE PerteneceC (
	busqueda INT REFERENCES BCoche(id),
	coche VARCHAR(40) REFERENCES Coche(url),
	CONSTRAINT id PRIMARY KEY (busqueda,coche)
);



GRANT SELECT, INSERT, UPDATE, DELETE ON Usuario TO PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON Busqueda TO PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON BVuelo TO PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON PerteneceV TO PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON Vuelo TO PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON BCoche TO PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON PerteneceC TO PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON Coche TO PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON BHotel TO PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON PerteneceH TO PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON Hotel TO PUBLIC;

COMMIT;
