--CREATE TABLE USER_DETAILS  (
--    ID INT PRIMARY KEY,
--    NAME VARCHAR(255),
--    BIRTH_DATE DATE
--);
--User detail table population
insert into user_details(id,NAME,BIRTH_DATE) 
values(10001,'Ranga',current_date());

insert into user_details(id,NAME,BIRTH_DATE) 
values(10002,'Ganga',current_date());

insert into user_details(id,NAME,BIRTH_DATE) 
values(10003,'Vanga',current_date());

insert into user_details(id,NAME,BIRTH_DATE) 
values(10004,'Thanga',current_date());

--Post table population

insert into post(id,description,user_id) 
values(20001,'Learn AWS',10001);

insert into post(id,description,user_id) 
values(20002,'Learn Azure',10001);

insert into post(id,description,user_id) 
values(20003,'Learn Google Cloud',10002);

insert into post(id,description,user_id) 
values(20004,'Learn Oracle Cloud',10002);

insert into post(id,description,user_id) 
values(20005,'Learn C++',10003);

insert into post(id,description,user_id) 
values(20007,'Learn Rust',10003);

insert into post(id,description,user_id) 
values(20006,'Learn Python',10003);

insert into post(id,description,user_id) 
values(20009,'Learn Rust',10004);

insert into post(id,description,user_id) 
values(20008,'Learn GoLang',10004);