show tables;

-- 기본키(primary key): 테이블을 대표하는 키, 중복을 허용하지 않는다. 기본키는 여러개가 올 수 있다.
CREATE TABLE test1 (
	idx INT NOT NULL auto_increment PRIMARY KEY,
	name VARCHAR(20) NOT NULL,
	age INT DEFAULT 20,
	address VARCHAR(50)
);
DESC test1;
-- DROP TABLE test1;
SELECT * FROM test1;
INSERT INTO test1 VALUES (DEFAULT,'홍길동',DEFAULT,'서울');
INSERT INTO test1 VALUES (DEFAULT,'김말숙',25,'청주');
INSERT INTO test1 VALUES (1,'소나무',55,'제주');

CREATE TABLE test2 (
	idx	INT NOT NULL auto_increment PRIMARY KEY,
	name VARCHAR(20) NOT NULL,
	age	INT DEFAULT 20,
	test2Code VARCHAR(10) NOT NULL
	/* PRIMARY KEY(idx, test2Code) 다른 테이블에서 불러올 때 그룹 primary key로 줬기 때문에 그룹으로 불러와야 함 따로 부르기 X */
);
DESC test2;
-- DROP TABLE test2;
SELECT * FROM test2;
INSERT INTO test2 VALUES (DEFAULT,'이기자',23,'aaa');
INSERT INTO test2 VALUES (DEFAULT,'김길자',33,'bbb');
INSERT INTO test2 VALUES (1,'소나무',55,'ccc'); -- O -> X
INSERT INTO test2 VALUES (DEFAULT,'소나무',55,'bbb'); -- O -> O

-- unique key: 중복 불허를 위해 설정하는 키(primary key를 대신해서 중복을 불허시키고자 할 때 사용)
CREATE TABLE test3 (
	idx	INT NOT NULL auto_increment,
	name VARCHAR(20) NOT NULL,
	age	INT DEFAULT 20,
	job VARCHAR(10) NOT NULL,
	address	VARCHAR(20) NOT NULL,
	test3Code VARCHAR(10) NOT NULL,
	PRIMARY KEY(idx),
	UNIQUE KEY(test3Code)
);
DESC test3;
-- DROP TABLE test3;
SELECT * FROM test3;
INSERT INTO test3 VALUES (DEFAULT,'소나무',13,'학생','서울','ccc');
INSERT INTO test3 VALUES (DEFAULT,'대나무',43,'회사원','청주','eee');
INSERT INTO test3 VALUES (DEFAULT,'사과나무',27,'군인','대전','ggg');
INSERT INTO test3 VALUES (1,'감나무',19,'fff'); -- X
INSERT INTO test3 VALUES (DEFAULT,'감나무',19,'eee'); -- X

-- 외래키(foreign key): 하나의 테이블에서 다른 테이블의 정보를 찾기 위해 연결해주는 역할을 할 때 지정하는 키
-- 조건: 현재 테이블에서 필드에 외래키로 설정하려한다면? 반드시 상대쪽 테이블의 해당 필드는 primary key이거나 unique key 등록되어 있어야 한다.
-- 또한, 외래키로 지정하려는 필드는 상대쪽 테이블의 해당 필드 속성과 같아야 한다.
CREATE TABLE test4 (
	idx	INT NOT NULL auto_increment PRIMARY KEY,
	gender CHAR(2) DEFAULT '남자',
	test2Idx INT NOT NULL,
	test3Code VARCHAR(10) NOT NULL,
	FOREIGN KEY (test2Idx) REFERENCES test2 (idx),
	FOREIGN KEY (test3Code) REFERENCES test3 (test3Code)
);
DESC test4;
-- DROP TABLE test4;
SELECT * FROM test4;
INSERT INTO test4 VALUES (DEFAULT,DEFAULT, 1, 'ggg');
INSERT INTO test4 VALUES (DEFAULT,DEFAULT, 1, 'ccc');

-- SELECT 필드명 FROM 테이블명 WHERE 조건식 옵션;
SELECT * FROM test3, test4; /*크로스 조인*/
SELECT test3.*,gender FROM test3, test4;
SELECT test4.idx,gender FROM test3, test4;
SELECT t4.idx AS 고유번호,gender AS 성별 FROM test3 t3, test4 t4; /* 별명 만들어 간단히 만들기*/

SELECT t3.*, t4.gender FROM test3 t3, test4 t4 WHERE t3.test3Code = t4.test3Code; /*인너조인 가장 많이 씀*/