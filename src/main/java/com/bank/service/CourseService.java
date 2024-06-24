package com.bank.service;

import java.util.List;

import com.bank.binding.Course;


public interface CourseService {
	
	 Course saveCourse(Course course);
	 Course getCourseById(Long cid);
	 List<Course> getAllCourses();
	 void deleteById(Long cid);
	

}
