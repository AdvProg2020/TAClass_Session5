package edu.menu;

import edu.Course;
import edu.CourseInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class StudentMenu extends Menu {
    public StudentMenu(Menu parentMenu) {
        super("Student Menu", parentMenu);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getShowCoursesThisSemesterMenu());
        submenus.put(2, getTakeCourseMenu());
        submenus.put(3, getDropCourseMenu());
        submenus.put(4, getShowStudentCoursesThisSemesterMenu());
        submenus.put(5, getShowReportMenu());
        this.setSubmenus(submenus);
    }

    protected Menu getShowCoursesThisSemesterMenu() {
        return new Menu("List Of Courses This Semester", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter Back to return");
            }

            @Override
            public void execute() {
                for (Course course: manager.getCoursesThisSemester()) {
                    System.out.println(course);
                }
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                }
            }
        };
    }
    private Menu getTakeCourseMenu() {
        return new Menu("Take Course", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter course's name and student's id or Back to return");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                } else {
                    String[] splitInput = input.split("\\s");
                    if (manager.takeCourseForStudent(splitInput[0], splitInput[1])) {
                        System.out.println("Successfully taken the course!");
                    } else {
                        System.out.println("Failed to take the course!");
                    }
                    this.show();
                    this.execute();
                }
            }
        };
    }
    private Menu getDropCourseMenu() {
        return new Menu("Drop Course", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter course's name and student's id or Back to return");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                } else {
                    String[] splitInput = input.split("\\s");
                    manager.dropCourseForStudent(splitInput[0], splitInput[1]);
                    this.show();
                    this.execute();
                }
            }
        };
    }
    private Menu getShowStudentCoursesThisSemesterMenu() {
        return new Menu("List Of Student Courses This Semester", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter student's id or Back to return");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                } else {
                    for (Course course: manager.getCoursesOfStudentThisSemester(input)) {
                        System.out.println(course);
                    }
                    this.show();
                    this.execute();
                }
            }
        };
    }
    private Menu getShowReportMenu() {
        return new Menu("Report Of Courses This Semester", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter student's id or Back to return");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                } else {
                    HashMap<Course, CourseInfo> coursesInfo = manager.getCoursesInfoOfStudentThisSemester(input);
                    for (Course course : coursesInfo.keySet()) {
                        System.out.println(course.getName() + " : " + coursesInfo.get(course).getMark());
                    }
                    this.show();
                    this.execute();
                }
            }
        };
    }



}
