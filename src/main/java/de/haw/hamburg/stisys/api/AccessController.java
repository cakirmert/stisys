package de.haw.hamburg.stisys.api;

import de.haw.hamburg.stisys.core.AccessControlProxy;
import de.haw.hamburg.stisys.core.ControlledObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AccessController {

    private final AccessControlProxy<ControlledObject> controlledObjectProxy;

    @Autowired
    public AccessController(AccessControlProxy<ControlledObject> controlledObjectProxy) {
        this.controlledObjectProxy = controlledObjectProxy;
    }

    @PostMapping("/courses")
    public int createCourse(@RequestBody CourseRequest courseRequest) {
        AccessControlProxy<ControlledObject> controlledCourse = controlledObjectProxy.createCourse(courseRequest.getCourseName(), courseRequest.getCredits(), null);
        return controlledObjectProxy.saveCourse(controlledCourse);
    }

    @PostMapping("/labs")
    public int createLab(@RequestBody LabRequest labRequest) {
        AccessControlProxy<ControlledObject> controlledCourse = controlledObjectProxy.createCourse(labRequest.getCourseName(), labRequest.getCredits(), null);
        AccessControlProxy<ControlledObject> controlledLab = controlledObjectProxy.createLab(labRequest.getLabName(), labRequest.getCredits(), null, controlledCourse);
        return controlledObjectProxy.saveLab(controlledLab);
    }

    @PostMapping("/students")
    public int createStudent(@RequestBody StudentRequest studentRequest) {
        AccessControlProxy<ControlledObject> controlledStudent = ( controlledObjectProxy).createStudent(studentRequest.getName(), studentRequest.getPassword());
        return controlledObjectProxy.saveStudent(controlledStudent);
    }

    @PostMapping("/professors")
    public int createProfessor(@RequestBody ProfessorRequest professorRequest) {
        AccessControlProxy<ControlledObject> controlledProfessor = controlledObjectProxy.createProfessor(professorRequest.getName(), professorRequest.getPassword());
        return controlledObjectProxy.saveStudent(controlledProfessor);
    }

    // Define additional endpoints for other operations as required

    // Inner classes for request payloads
    private static class CourseRequest {
        private String courseName;
        private int credits;

        // Getters and setters
        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public int getCredits() {
            return credits;
        }

        public void setCredits(int credits) {
            this.credits = credits;
        }
    }

    private static class LabRequest {
        private String labName;
        private String courseName;
        private int credits;

        // Getters and setters
        public String getLabName() {
            return labName;
        }

        public void setLabName(String labName) {
            this.labName = labName;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public int getCredits() {
            return credits;
        }

        public void setCredits(int credits) {
            this.credits = credits;
        }
    }

    private static class StudentRequest {
        private String name;
        private String password;

        // Getters and setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    private static class ProfessorRequest {
        private String name;
        private String password;

        // Getters and setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
