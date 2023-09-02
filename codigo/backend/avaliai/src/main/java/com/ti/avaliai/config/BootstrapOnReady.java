package com.ti.avaliai.config;

import com.google.gson.reflect.TypeToken;
import com.ti.avaliai.academicmail.AcademicEmail;
import com.ti.avaliai.academicmail.AcademicEmailService;
import com.ti.avaliai.course.Course;
import com.ti.avaliai.course.CourseService;
import com.ti.avaliai.databaseloader.DatabaseLoaderService;
import com.ti.avaliai.subject.Subject;
import com.ti.avaliai.subject.SubjectService;
import com.ti.avaliai.university.University;
import com.ti.avaliai.university.UniversityService;
import com.ti.avaliai.utils.JsonUtil;
import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.List;

@Log
@Configuration
public class BootstrapOnReady {

    private static final String PATH_JSON_UNIVERSITY = "/databaseloader/university.json";
    private static final String PATH_JSON_COURSE = "/databaseloader/course.json";
    private static final String PATH_JSON_SUBJECT = "/databaseloader/subject.json";
    private static final String PATH_JSON_ACADEMIC_EMAIL = "/databaseloader/academic_email.json";

    @Autowired
    private DatabaseLoaderService databaseLoaderService;

    @Autowired
    private UniversityService universityService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private AcademicEmailService academicEmailService;


    @EventListener(ApplicationReadyEvent.class)
    public void bootstraper() {
        List<University> universities = JsonUtil.objectListFromJson("university", PATH_JSON_UNIVERSITY, new TypeToken<ArrayList<University>>() {
        }.getType());

        List<Course> courses = JsonUtil.objectListFromJson("course", PATH_JSON_COURSE, new TypeToken<ArrayList<Course>>() {
        }.getType());

        List<Subject> subjects = JsonUtil.objectListFromJson("subject", PATH_JSON_SUBJECT, new TypeToken<ArrayList<Subject>>() {
        }.getType());

        List<AcademicEmail> academicEmails = JsonUtil.objectListFromJson("academic_email", PATH_JSON_ACADEMIC_EMAIL, new TypeToken<ArrayList<AcademicEmail>>() {
        }.getType());


        if ((databaseLoaderService.loadEntity(University.class.getSimpleName()))) {
            universities.forEach(this::createUniversity);
            databaseLoaderService.save(University.class.getSimpleName());
        }

        if ((databaseLoaderService.loadEntity(Course.class.getSimpleName()))) {
            courses.forEach(this::createCourse);
            databaseLoaderService.save(Course.class.getSimpleName());
        }

        if ((databaseLoaderService.loadEntity(Subject.class.getSimpleName()))) {
            subjects.forEach(this::createSubject);
            databaseLoaderService.save(Subject.class.getSimpleName());
        }

        if ((databaseLoaderService.loadEntity(AcademicEmail.class.getSimpleName()))) {
            academicEmails.forEach(this::createAcademicEmail);
            databaseLoaderService.save(AcademicEmail.class.getSimpleName());
        }

        this.relationCourseUniversity();
        this.relationSubjectCourse();
    }

    private void createUniversity(University university) {
        try {
            universityService.save(university);
        } catch (Exception exception) {
            log.severe("A universidade " + university.getName() + " não pode ser cadastrada. Erro: " + exception.getMessage());
        }
    }

    private void createCourse(Course course) {
        try {
            courseService.save(course);
        } catch (Exception exception) {
            log.severe("O curso " + course.getName() + " não pode ser cadastrado. Erro: " + exception.getMessage());
        }
    }

    private void createSubject(Subject subject) {
        try {
            subjectService.save(subject);
        } catch (Exception exception) {
            log.severe("A disciplina " + subject.getName() + " não pode ser cadastrada. Erro: " + exception.getMessage());
        }
    }

    private void createAcademicEmail(AcademicEmail academicEmail) {
        try {
            academicEmailService.save(academicEmail);
        } catch (Exception exception) {
            log.severe("O e-mail acadêmico " + academicEmail.getDomain() + " não pode ser cadastrado. Erro: " + exception.getMessage());
        }
    }

    @Transactional
    private void relationCourseUniversity(){
        University university = universityService.findByName("PUC Minas");
        List<Course> courses = courseService.findAll();
        university.setCourses(courses);
        universityService.save(university);
    }

    @Transactional
    private void relationSubjectCourse(){
        Course course = courseService.findByName("Engenharia de Software");
        List<Subject> subjects = subjectService.findAll();
        course.setSubjects(subjects);
        courseService.save(course);
    }
}