package com.bank.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.binding.Course;


public interface CourseRepository extends JpaRepository<Course, Long> {

	
	
}