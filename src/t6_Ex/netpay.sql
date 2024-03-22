show tables;

-- 직급별 본봉 테이블
CREATE TABLE salary (
	jikkub CHAR(8) NOT NULL PRIMARY KEY, /* 직급 */
	bonbong INT NOT NULL /* 본봉 */
);

DROP TABLE salary;
DESC salary;

INSERT INTO salary VALUES ('부장', 5000000);
INSERT INTO salary VALUES ('과장', 4000000);
INSERT INTO salary VALUES ('대리', 3000000);
INSERT INTO salary VALUES ('사원', 2000000);
SELECT * FROM salary;

-- 인사관리 테이블
CREATE TABLE insa (
	idx INT NOT NULL auto_increment PRIMARY KEY, /* 인사 고유번호 */
	sabun CHAR(8) NOT NULL, /* 직급코드 (yyMMdd일련번호(2)) */
	buseo VARCHAR(10) NOT NULL, /* 인사과, 총무과, 생산과, 영업과 */
	name VARCHAR(20) NOT NULL, /* 성명 */
	jikkub CHAR(8) NOT NULL, /* 부장, 과장, 대리, 사원 */
	age INT DEFAULT 25, /* 나이 */
	ipsail datetime DEFAULT now(), /* 입사일 */
	gender CHAR(2) DEFAULT '여자', /* 성별 */
	address VARCHAR(30), /* 주소 */
	UNIQUE KEY (sabun), /* 중복 불가 키: 사번 */
	FOREIGN KEY (jikkub) REFERENCES salary (jikkub) /* 외래키 */
);

DROP TABLE insa;
DESC insa;

INSERT INTO insa VALUES (DEFAULT, '24032101', '인사과', '홍길동', '과장', 35, '2000-1-5', '남자', '서울');
INSERT INTO insa VALUES (DEFAULT, '24032102', '영업과', '김말숙', '대리', 31, '2007-11-25', DEFAULT, '청주');
INSERT INTO insa VALUES (DEFAULT, '24032201', '총무과', '이기자', '사원', 25, '2022-8-22', '남자', '서울');
SELECT * FROM insa;

-- 이기자의 급여?
-- 사원의 본봉?
SELECT * FROM salary WHERE jikkub = '사원';
-- 이기자의 본봉?
SELECT * FROM salary s, insa i WHERE s.jikkub = i.jikkub;
SELECT i.name, i.jikkub,s.bonbong FROM salary s, insa i WHERE s.jikkub = i.jikkub;