select * from `Student`;

/*---------------------------------GET COURSE REPORT FOR A STUDENT-----------------------------------*/
delimiter //
create procedure getCourseReport(in studentId varchar(8),in groupId int)
begin
select * from 
	Student s join Participate p on s.studentId = p.studentId
	join `Group` g on p.groupId = g.groupId
    join Course c on g.courseId = c.courseId
	join GradeCategory gc on gc.courseId = c.courseId 
    join Grade gr on gr.gradeCategoryId = gc.gradeCategoryId and s.studentId = gr.studentId
    where s.studentId = studentId and g.groupId = groupId and gc.gradeItemName != 'Total';
end //

-- call getCourseReport('HE170863', 15)
-- drop procedure if exists getCourseReport

/*---------------------------------GET ACADEMIC REPORT FOR A STUDENT-----------------------------------*/
delimiter //
create procedure getAcademicReport(in studentId varchar(8))
begin
select * from 
	Student s join Participate p on s.studentId = p.studentId
	join `Group` g on p.groupId = g.groupId
    join Course c on g.courseId = c.courseId
	join GradeCategory gc on gc.courseId = c.courseId 
    join Grade gr on gr.gradeCategoryId = gc.gradeCategoryId and s.studentId = gr.studentId
    where s.studentId = studentId and gc.gradeItemName != 'Total';
end //

-- call getAcademicReport('HE170863')
-- drop procedure if exists getAcademicReport