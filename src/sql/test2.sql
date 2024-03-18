show tables;

CREATE TABLE insarok (
	idx INT NOT NULL auto_increment PRIMARY KEY, /* 고유번호 */
	buser VARCHAR(10) NOT NULL,					/* 부서명 */
	name VARCHAR(20) NOT NULL,					/* 부서원(성명) */
	jikwi VARCHAR(10) NOT NULL DEFAULT '사원',	/* 직위 */
	gender CHAR(2) NOT NULL DEFAULT '남자',		/* 성별 */
	age INT DEFAULT 25,							/* 나이 */
	ipsail datetime NOT NULL DEFAULT now(),		/* 입사일(기본값: 오늘) now() = 오늘날짜 함수 */
	address VARCHAR(50)							/* 주소(공백 허용) */
	/* PRIMARY KEY(idx) */
);
DESC insarok;
-- DROP TABLE insarok;

SELECT * FROM insarok;

INSERT INTO insarok VALUES (DEFAULT, '인사과','홍길동','과장',DEFAULT,38,'1995-1-5','서울');
INSERT INTO insarok VALUES (DEFAULT, '총무과','한소희',DEFAULT,'여자',29,'2020-4-5','청주');
INSERT INTO insarok VALUES (DEFAULT, '영업과','권지용',DEFAULT,DEFAULT,33,'2020-4-5','서울');
INSERT INTO insarok VALUES (DEFAULT, '자재과','이도현','대리',DEFAULT,25,'2021-11-9','청주');
INSERT INTO insarok VALUES (DEFAULT, '인사과','김태희','팀장','여자',42,'1999-10-29','청주');
INSERT INTO insarok VALUES (DEFAULT, '자재과','박진영','부장',DEFAULT,48,'1992-3-18','서울');
INSERT INTO insarok VALUES (DEFAULT, '자재과','오해원',DEFAULT,'여자',22,'2023-12-1','대전');
INSERT INTO insarok VALUES (DEFAULT, '인사과','김민지',DEFAULT,'여자',21,'2023-12-1','서울');
INSERT INTO insarok VALUES (DEFAULT, '자재과','유아영','대리','여자',30,'2000-8-11','서울');
INSERT INTO insarok VALUES (DEFAULT, '총무과','박나래','과장','여자',36,'2002-8-8','경기');
INSERT INTO insarok VALUES (DEFAULT, '인사과','장도연','과장','여자',34,'2002-8-8','서울');
INSERT INTO insarok VALUES (DEFAULT, '영업과','정형돈','과장',DEFAULT,47,'2001-5-10','서울');
INSERT INTO insarok VALUES (DEFAULT, '영업과','유재석','부장',DEFAULT,52,'1998-9-21','서울');
INSERT INTO insarok VALUES (DEFAULT, '자재과','박명수','차장',DEFAULT,55,'1996-4-22','청주');
INSERT INTO insarok VALUES (DEFAULT, '영업과','노홍철',DEFAULT,DEFAULT,41,'2003-10-30','경기');
INSERT INTO insarok VALUES (DEFAULT, '총무과','하동훈',DEFAULT,DEFAULT,41,'2003-10-30','서울');
INSERT INTO insarok VALUES (DEFAULT, '영업과','이재용','부장',DEFAULT,51,'1993-7-18','서울');
INSERT INTO insarok VALUES (DEFAULT, '영업과','이건희','팀장',DEFAULT,79,'1990-9-1','대전');
INSERT INTO insarok VALUES (DEFAULT, '인사과','박남수',DEFAULT,DEFAULT,27,'2022-8-15','경기');
INSERT INTO insarok VALUES (DEFAULT, '총무과','박남수','대리',DEFAULT,32,'2018-6-20','경기');

-- insarok테이블의 성명/직위/주소 필드만 모든 자료 표시
SELECT name,jikwi,address FROM insarok;

-- 박남수 레크드만 출력
SELECT * FROM insarok WHERE name='박남수';

-- 경기에 사는 박남수 레크드만 출력
SELECT * FROM insarok WHERE address='경기' AND name='박남수';

-- 박남수 사원만 출력
SELECT * FROM insarok WHERE jikwi='사원' AND name='박남수';

-- 서울에 사는 모든 사람?
SELECT * FROM insarok WHERE address='서울';

-- 서울에 살지 않는 직원?
SELECT * FROM insarok WHERE address != '서울';
SELECT * FROM insarok WHERE address <> '서울';

-- 입사년도가 2000년 이전에 입사한 직원을 보여주시오
SELECT * FROM insarok WHERE ipsail < '2000-1-1';

-- 입사년도가 2000년 ~ 2010년에 입사한 직원을 보여주시오
SELECT * FROM insarok WHERE ipsail >= '2000-1-1' AND ipsail <= '2010-12-31'; 
-- 앞의 범위 연선자 대신에 between~and 변경가능(숫자)
SELECT * FROM insarok WHERE ipsail BETWEEN '2000-1-1' AND '2010-12-31'; 

-- 30대 회사원 출력해주세요
SELECT * FROM insarok WHERE age >= 30 AND age <= 39;
SELECT * FROM insarok WHERE age BETWEEN 30 AND 39;

-- 서울/청주에 사는 직원?
SELECT * FROM insarok WHERE address='서울' OR address='청주';
-- 앞의 or 연산자는 in()으로 변경가능
SELECT * FROM insarok WHERE address IN('서울','청주');

-- 서울/청주에 사는 사원만 출력
SELECT * FROM insarok WHERE jikwi='사원' AND address IN('서울','청주');

-- '김'씨만 출력(like)
SELECT * FROM insarok WHERE name LIKE '김%';

-- 용으로 끝나는 이름을 가진 직원
SELECT * FROM insarok WHERE name LIKE '%용';

-- 홍길동을 정재현으로 변경
UPDATE insarok SET name='정재현' WHERE name='홍길동';

-- 이름 중에서 '재'라는 글자를 포함한 직원의 직급을 '과장'으로 변경하시오
SELECT * FROM insarok WHERE name LIKE '%재%';
UPDATE insarok SET jikwi='과장' WHERE name LIKE '%재%';

-- 이름 중 2번째 글자가 '도'인 직원은?
SELECT * FROM insarok WHERE name LIKE '_도%'; /* 밑줄 개수가 n번째 개수*/

-- 이름 중에서 '재'라는 글자를 포함한 직원 중에서 '서울'에 사는 직원의 이름과 주소와 입사일?
SELECT name,ipsail,address FROM insarok WHERE name LIKE '%재%' AND address='서울';

-- 이름 중에서 '재'라는 글자를 포함한 직원 중에서 '서울'에 사는 직원중 나이가 40이상을 퇴사시키시오
SELECT * FROM insarok WHERE name LIKE '%재%' AND address='서울';
DELETE FROM insarok WHERE name LIKE '%재%' AND address='서울' AND age >= 40;

-- 이름 오름차순으로 출력하세요(순서: order by~~, 오름차순: asc(생략가능), 내림차순: desc)
SELECT * FROM insarok ORDER BY name;
SELECT * FROM insarok ORDER BY name DESC;

-- 남자인 자료 중에서 나이 오름차순으로 출력?
SELECT * FROM insarok WHERE gender='남자' ORDER BY age;
-- 남자인 자료 중에서 나이 오름차순으로, 같은 나이이면 입사일 내림차순 출력?
SELECT * FROM insarok WHERE gender='남자' ORDER BY age, ipsail DESC;

-- 전체 자료 중에서 5명만 출력하시오 (입력 순서 중 앞에서 5개)
SELECT * FROM insarok limit 5;

-- 뒤에서 5명만 출력?(나중에 입력한 회원)
SELECT * FROM insarok ORDER BY idx DESC limit 5;

-- 남자 회원 5명만 나이 내림차순으로 보여주시오(limit 출력개수)
SELECT * FROM insarok WHERE gender='남자' ORDER BY age DESC limit 5;

-- 남자 회원 중에서 뒤에서 2명을 빼고, 5명만 출력하시오(limit 인덱스번호, 출력개수)
SELECT * FROM insarok WHERE gender='남자' ORDER BY idx limit 2,5;
