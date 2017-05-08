CREATE TABLE GRADE ( 
	id_grade INTEGER SERIAL PRIMARY KEY,
	title varchar(50),
	description varchar(250));
	
INSERT INTO GRADE (id_grade, title, description ) VALUES (1, 'Math', 'Great class');