create table student(
student_id int(6) zerofill not null auto_increment,
first_name varchar(255),
last_name varchar(255),
birth_date date,
gender char(1),
grade int,
primary key(student_id)
);

create table course(
course_id int(6) zerofill not null auto_increment,
subject varchar(255),
course_name varchar(255),
teacher_last_name varchar(255),
period int,
primary key(course_id)
);

create table schedule_join(
student_id int(6) zerofill not null,
course_id int(6) zerofill not null,
foreign key(student_id) references student(student_id),
foreign key(course_id) references course(course_id)
);