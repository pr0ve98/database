show tables;

create table test (
	idx int not null auto_increment primary key,	/* 고유번호 */
	name varchar(20) not null,			/* 성명 */
	age int default 20,					/* 나이 */
	gender varchar(2) default '남자',		/* 성별 */
	job varchar(20) default '기타',		/* 직업 */
	address varchar(50)					/* 주소 */
);

drop table test; /* 테이블 자체를 삭제 */
delete from test; /* 테이블 내용을 삭제 */

insert into test values (default,'홍길동',default,default,default,'서울');
insert into test values (default,'한소희',24,'여자',default,'서울');
insert into test values (default,'김애옹',default,'여자',default,'청주');
insert into test values (default,'박민수',33,default,default,'대전');
insert into test values (default,'김외계',820,'모름',default,'화성');
insert into test values (default,'유재석',55,default,default,'경기');
insert into test values (default,'강호동',57,default,'연예인','서울');
insert into test values (default,'신동엽',56,default,'MC','서울');

select * from test;

delete from test where name='홍길동';

/* 레코드 수정하기: update 테이블명 set 필드명='수정내용' where '조건(필드명=값)'; */
update test set name='김왹져' where name='김외계';

/* 남자들의 나이를 1살씩 모두 더하시오 */
update test set age = age + 1; /* 모두 더해짐 */
update test set age = age - 1;
update test set age = age + 1 where gender='남자';

/* 서울에 사는 사람들만 보여주시오 */
select * from test where address='서울';

/* 서울과 대전에 사는 사람들만 보여주시오 */
select * from test where address='서울' or address='대전';

/* 나이가 30살 미만인 사람만 보여주시오 */
select * from test where age < 30;

/* 나이가 30살 미만이면서 여자인 사람만 보여주시오 */
select * from test where age < 30 and gender='여자';

/* 청주에 사는 회원 확인 */
select * from test WHERE address='청주';

/* 청주에 사는 회원 삭제 */
DELETE FROM test WHERE address='청주';

/* 청주/남자/19/강감찬 인 회원을 등록하세요 */
INSERT INTO test VALUES ('강감찬',19,default,'청주');

/* 서울에 사는 여자 사람의 나이를 2살씩 빼주시오 */
UPDATE test SET age=age-2 WHERE address='서울';

/* test 테이블의 구조 보기 */
desc test;

/* 필드 구조 변경 (alter table~~) */
-- test 테이블에 job필드(직업명은 5글자 이내, 기본값:기타)-컬럼(을)를 추가(add column)하시오
alter table test add column job varchar(6) default '기타'; /* varchar(6)인 이유는: varchar는 가변형이라 마지막에 null도 들어가야 하므로 한칸 더 필요 */

-- test테이블에 job필드 삭제하기(drop column)
alter table test drop column job;

-- test테이블의 job필드의 길이를 20자로 수정하시오(modify column)
alter table test modify column job varchar(20);

-- test테이블의 name필드명을 irum필드로 변경하시오.(change column)
ALTER TABLE test change COLUMN name irum VARCHAR(20);
ALTER TABLE test change COLUMN irum name VARCHAR(20);

-- test 테이블에 고유번호(idx)를 추가하시오. - 기본키(구분이 될 수 있는 중복되지않는 필드) 추가
ALTER TABLE test ADD COLUMN idx INT NOT NULL auto_increment PRIMARY KEY; /* mySQL에서 auto_increment와 PRIMARY KEY는 한몸 */
-- 하나의 테이블에 여러개의 PRIMARY KEY를 쓸 수 있다(mySQL에서는 처음엔 PRIMARY KEY로 사용하고 또 중복되지 않아야 할 키가 필요할 경우 UNIQUE KEY로 주는 게 좋다)

-- idx = 7번 삭제
DELETE FROM test WHERE idx = 7;

/* 고유번호(idx) 값을 5번부터 시작하도록 설정하시오 */
ALTER TABLE test auto_increment = 5;