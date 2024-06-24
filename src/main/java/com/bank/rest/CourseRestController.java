package com.bank.rest;

import com.bank.binding.Course;
import com.bank.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseRestController {
    private final CourseService courseService;
 
    @Autowired
    public CourseRestController(CourseService courseService) {
        this.courseService = courseService;
    }
   
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }
 
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }
 
    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }
    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course) {
        course.setCid(id);
        return courseService.saveCourse(course);
    }
    @DeleteMapping("/{cid}")
    public void deleteCourse(@PathVariable Long cid) {
        courseService.deleteById(cid);
    }
}
