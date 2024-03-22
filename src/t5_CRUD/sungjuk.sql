show tables;

CREATE TABLE sungjuk (
	idx INT NOT NULL auto_increment PRIMARY KEY, /* 성적 고유번호 */
	name VARCHAR(20) NOT NULL, /* 성명 */
	kor INT DEFAULT 0, /* 국어점수 */
	eng INT DEFAULT 0, /* 영어점수 */
	mat INT DEFAULT 0 /* 수학점수 */
);

DESC sungjuk;

INSERT INTO sungjuk VALUES (DEFAULT, '홍길동', 100, 90, 80);

SELECT * FROM sungjuk;