insert into course(id,fullname,created_date,last_updated_date,is_deleted)
values(1001,'JPA in 50 Steps',CURRENT_TIME,CURRENT_TIME,false);

insert into course(id,fullname,created_date,last_updated_date,is_deleted)
values(1002,'Spring in 50 Steps',CURRENT_TIME,CURRENT_TIME,false);

insert into course(id,fullname,created_date,last_updated_date,is_deleted)
values(1003,'Spring Boot in 100 Steps',CURRENT_TIME,CURRENT_TIME,false);

--insert into course(id,fullname,created_date,last_updated_date)
--values(1004,'dummy1',CURRENT_TIME,CURRENT_TIME);
--insert into course(id,fullname,created_date,last_updated_date)
--values(1005,'dummy2',CURRENT_TIME,CURRENT_TIME);
--insert into course(id,fullname,created_date,last_updated_date)
--values(1006,'dummy3',CURRENT_TIME,CURRENT_TIME);
--insert into course(id,fullname,created_date,last_updated_date)
--values(1007,'dummy4',CURRENT_TIME,CURRENT_TIME);
--insert into course(id,fullname,created_date,last_updated_date)
--values(1008,'dummy5',CURRENT_TIME,CURRENT_TIME);


insert into passport(id,number)
values(4001,'E1');

insert into passport(id,number)
values(4002,'E2');

insert into passport(id,number)
values(4003,'E3');


insert into student(id,name,passport_id)
values(2001,'Ranga',4001);

insert into student(id,name,passport_id)
values(2002,'Adam',4002);

insert into student(id,name,passport_id)
values(2003,'Jane',4003);



insert into review(id,rating,description,course_id)
values(5001,'FIVE','Great Course',1001);

insert into review(id,rating,description,course_id)
values(5002,'FOUR','Wonderful Course',1001);

insert into review(id,rating,description,course_id)
values(5003,'FIVE','Awesome Course',1003);


insert into student_course(student_id,course_id)
values(2001,1001);
insert into student_course(student_id,course_id)
values(2002,1001);
insert into student_course(student_id,course_id)
values(2003,1001);
insert into student_course(student_id,course_id)
values(2001,1003);