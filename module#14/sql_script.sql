CREATE EXTENSION pg_trgm;
CREATE EXTENSION btree_gin;

CREATE INDEX idx_students_name ON students USING brin(name);

CREATE INDEX idx_students_surname ON students USING brin(surname);

CREATE INDEX idx_students_phone ON students USING(phone);


EXPLAIN ANALYZE SELECT * FROM public.students WHERE surname LIKE '%son%';

EXPLAIN ANALYZE SELECT * FROM public.students WHERE name='Matt';

EXPLAIN ANALYZE SELECT s.name, s.surname, r.grade, r.status FROM public.students as s 
INNER JOIN results as r ON r.student = s.student_id 
WHERE s.surname LIKE '%Clark%';


DROP INDEX idx_students_name;
DROP INDEX idx_students_surname;
DROP INDEX idx_students_phone;


CREATE OR REPLACE FUNCTION update_timestamp()
RETURNS TRIGGER AS $$
	BEGIN
		IF NEW.name ~* '[^a-z0-9]' THEN
			RETURN OLD;
		ELSE
			NEW.updated_date = now();
			RETURN NEW;
		END IF;
	END;
$$ language 'plpgsql';


CREATE TRIGGER update_timestamp
BEFORE UPDATE
ON students
FOR EACH ROW EXECUTE PROCEDURE  update_timestamp();

SELECT * FROM students WHERE student_id = 1234;
UPDATE students SET name='BerkantNewName' WHERE student_id= 1234;



CREATE TABLE students_snapshot AS SELECT s.name, s.surname, sub.name as subject_name, r.grade FROM students s
INNER JOIN results r ON  s.student_id = r.student
INNER JOIN subjects sub ON r.subject = sub.subject_id;

SELECT * FROM students_snapshot WHERE name = 'Berkant';



CREATE OR REPLACE FUNCTION average_student_mark(s_id INT)
RETURNS INT AS $average$
	declare
		average INT;
	BEGIN
		SELECT AVG(r.grade) into average FROM students s INNER JOIN results r ON r.student = s.student_id WHERE s.student_id = s_id;
		RETURN average;
	END;
$average$ language 'plpgsql';



	
CREATE OR REPLACE FUNCTION average_subject_mark(sub_name CHAR)
RETURNS INT AS $average$
	declare
		average INT;
	BEGIN
		SELECT AVG(r.grade) into average FROM subjects s INNER JOIN results r ON r.subject = s.subject_id WHERE s.name = sub_name;
		RETURN average;
	END;
$average$ language 'plpgsql';



CREATE OR REPLACE FUNCTION check_red_zone(s_id INT)
RETURNS CHAR AS $$
	declare
		zone INT;
	BEGIN
		SELECT count(r.grade) into zone FROM students s INNER JOIN results r ON r.student = s.student_id WHERE s.student_id = s_id AND r.grade < 50;
		IF zone <= 3 THEN
			RETURN 'IN RED ZONE';
		ELSE
			RETURN 'IN GREEN ZONE';
		END IF;
	END;
$$ language 'plpgsql';


SELECT average_student_mark(1234);
SELECT average_subject_mark('History');
SELECT check_red_zone(1234);

