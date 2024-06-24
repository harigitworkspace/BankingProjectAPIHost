package com.bank.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bank.binding.Course;
import com.bank.repo.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {
	
    private final CourseRepository courseRepo;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepo) {
    	this.courseRepo=courseRepo;
    }
    
    @Override
    public Course getCourseById(Long cid) {
       Optional<Course> optionalCourse = courseRepo.findById(cid);
    	return optionalCourse.orElse(null);
    }
    
    @Override
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    @Override
    public Course saveCourse(Course course) {
        return  courseRepo.save(course);
    }

    
    @Override
    public void deleteById(Long cid) {
    courseRepo.deleteById(cid);
    }
}
