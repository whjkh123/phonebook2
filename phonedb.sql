CREATE USER phonedb IDENTIFIED BY phonedb;

GRANT resource, CONNECT TO phonedb;

----------------------------------------------------------

CREATE TABLE person (person_id NUMBER(5),
                     name VARCHAR2(30) NOT NULL,
                     hp VARCHAR2(20),
                     company VARCHAR2(20),
                     PRIMARY KEY (person_id));

----------------------------------------------------------

DELETE FROM person;

DROP SEQUENCE seq_person_id;

CREATE SEQUENCE seq_person_id INCREMENT by 1 START WITH 1;

----------------------------------------------------------

INSERT INTO person VALUES(seq_person_id.NEXTVAL, '이효리', '010-1111-1111', '02-1111-1111');
INSERT INTO person VALUES(seq_person_id.NEXTVAL, '정우성', '010-2222-2222', '02-2222-2222');
INSERT INTO person VALUES(seq_person_id.NEXTVAL, '유재석', '010-3333-3333', '02-3333-3333');
INSERT INTO person VALUES(seq_person_id.NEXTVAL, '이정재', '010-4444-4444', '02-4444-4444');
INSERT INTO person VALUES(seq_person_id.NEXTVAL, '서장훈', '010-5555-5555', '02-5555-5555');

SELECT  person_id,
        name,
        hp,
        company
FROM    person
ORDER BY person_id;

COMMIT;

ROLLBACK;