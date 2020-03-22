package edu;

import java.util.ArrayList;
import java.util.HashMap;

public class Student extends Person {
    private String studentId;
    private HashMap<Course, CourseInfo> coursesThisSemester;
    private HashMap<Course, CourseInfo> coursesPassed;

    public Student(String studentId, String firstName, String lastName, String nationalCode) {
        super(firstName, lastName, nationalCode);
        this.studentId = studentId;
        coursesThisSemester = new HashMap<>();
        coursesPassed = new HashMap<>();
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public String getStudentId() {
        return studentId;
    }

    public HashMap<Course, CourseInfo> getCoursesThisSemester() {
        return coursesThisSemester;
    }

    public Boolean hasCourseThisSemester(Course course) {
        return coursesThisSemester.containsKey(course);
    }

    public Boolean hasPassedCourse(Course course) {
        for (Course passedCourse : coursesPassed.keySet()) {
            if (passedCourse.getName().equals(course.getName()))
                return true;
        }
        return false;
    }

    public Boolean hasPassedPreCourses(Course course) {
        ArrayList<String> preCoursesNames = course.getPreCourses();
        for (Course passedCourse : coursesPassed.keySet()) {
            for (String preCourseName : preCoursesNames) {
                if (passedCourse.getName().equals(preCourseName)) {
                    preCoursesNames.remove(preCourseName);
                    break;
                }
            }
        }
        return preCoursesNames.isEmpty();
    }

    public void takeCourse(Course course) {
        coursesThisSemester.put(course, new CourseInfo());
    }

    public void dropCourse(Course course) {
        coursesThisSemester.remove(course);
    }

    public void submitMark(Course course, float mark) {
        CourseInfo courseInfo = coursesThisSemester.get(course);
        courseInfo.setMark(mark);
    }

    public void passCourses() {
        for (Course course : coursesThisSemester.keySet()) {
            CourseInfo courseInfo = coursesThisSemester.get(course);
            if (courseInfo.getMark() >= 10) {
                coursesPassed.put(course, courseInfo);
            }
        }
        coursesThisSemester.clear();
    }
}
