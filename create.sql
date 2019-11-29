	CREATE TABLE departments(
    id int NOT NULL,
	name text NOT NULL,
 	PRIMARY KEY( id ) );
    
	CREATE TABLE drugs(
	id int NOT NULL,
	name text NOT NULL,
	description text NOT NULL,
	PRIMARY KEY( id ) );
	
	CREATE TABLE patient(
	patientAMKA BIGint NOT NULL,
	userid text NOT NULL,
	password text NOT NULL,
	name text NOT NULL,
	surname text NOT NULL,
	gender text NOT NULL,
	PRIMARY KEY( patientAMKA));

	CREATE TABLE doctor(
	doctorAMKA BIGint NOT NULL,
	username text NOT NULL,
	password text NOT NULL,
	name text NOT NULL,
	surname text NOT NULL,
	specialty int NOT NULL,
	region text NOT NULL,
	PRIMARY KEY( doctorAMKA ));

	CREATE TABLE appointments(
	id int NOT NULL,
    	t timestamp NOT NULL,
	patientAMKA BIGint NOT NULL,
	doctorAMKA BIGint NOT NULL,
 	diagnosis text,
 	PRIMARY KEY( id ));
    
	CREATE TABLE medical_folder(
    id int NOT NULL,
	patient BIGint NOT NULL,
	cure text NOT NULL,
    drug_id int NOT NULL,
    PRIMARY KEY( id ));
	


COPY appointments FROM 'C:\...\appointments.csv'
WITH CSV HEADER;

COPY departments FROM 'C:\...\departments.csv'
WITH CSV HEADER;

COPY drugs FROM 'C:\...\drugs.csv'
WITH CSV HEADER;

COPY medical_folder FROM 'C:\...\medical_folder.csv'
WITH CSV HEADER;

COPY patient FROM 'C:\Users\...\patient.csv'
WITH CSV HEADER;

COPY doctor FROM 'C:\Users\...\doctor.csv'
WITH CSV HEADER;