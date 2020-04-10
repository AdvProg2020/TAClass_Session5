package edu;

import edu.menu.MainMenu;
import edu.menu.Menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class CommandProcessor {
    private Manager manager;
    private Scanner scanner;

    public CommandProcessor(Manager manager) {
        this.manager = manager;
        scanner = new Scanner(System.in);
    }

    private void processAddStudent(String[] splitInput) {
        manager.addStudent(splitInput[2], splitInput[3], splitInput[4], splitInput[5]);
    }

    private void processAddProfessor(String[] splitInput) {
        manager.addProfessor(splitInput[2], splitInput[3], splitInput[4], splitInput[5]);
    }

    private void processAddCourse(String[] splitInput) {
        ArrayList<String> preCourses = new ArrayList<>();
        if (splitInput.length == 6)
            Collections.addAll(preCourses, splitInput[5].split(","));
        manager.addCourse(splitInput[2], splitInput[3], splitInput[4], preCourses);
    }

    private void processShowStudents() {
        for (Student student : manager.getStudents()) {
            System.out.println(student);
        }
    }

    private void processShowProfessors() {
        for (Professor professor : manager.getProfessors()) {
            System.out.println(professor);
        }
    }

    private void processShowThisSemesterCourses() {
        for (Course course : manager.getCoursesThisSemester()) {
            System.out.println(course);
        }
    }

    private void processTakeCourse(String[] splitInput) {
        if (manager.takeCourseForStudent(splitInput[2], splitInput[3])) {
            System.out.println("Successfully taken the course!");
        } else {
            System.out.println("Failed to take the course!");
        }
    }

    private void processShowCoursesForStudentThisSemester(String[] splitInput) {
        for (Course course : manager.getCoursesOfStudentThisSemester(splitInput[4])) {
            System.out.println(course);
        }
    }

    private void processDropCourse(String[] splitInput) {
        manager.dropCourseForStudent(splitInput[2], splitInput[3]);
    }

    private void processSubmitMark(String[] splitInput) {
        manager.submitCourseMarkForStudent(splitInput[3], Float.parseFloat(splitInput[4]), splitInput[5]);
    }

    private void processShowReport(String[] splitInput) {
        HashMap<Course, CourseInfo> coursesInfo = manager.getCoursesInfoOfStudentThisSemester(splitInput[4]);
        for (Course course : coursesInfo.keySet()) {
            System.out.println(course.getName() + " : " + coursesInfo.get(course).getMark());
        }
    }

    private void processGoNextSemester() {
        manager.goNextSemester();
        System.out.println("Welcome to semester " + manager.getCurrentSemester());
    }

    private void processShowCoursesHistory(String[] splitInput) {
        for (Course course : manager.getCoursesHistory()) {
            if (course.getSemester().equals(splitInput[3]))
                System.out.println(course);
        }
    }

    private void processReceiveLoan(String[] splitInput) {
        Person person = manager.receiveLoanFor(splitInput[2]);
        System.out.println("Granted money to " + person.getFirstName() + " " + person.getLastName());
    }

    public void run() {
        String input;
        System.out.println("Enter your command :");
        while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
            if (input.startsWith("add student")) {
                processAddStudent(input.split("\\s"));
            } else if (input.startsWith("add professor")) {
                processAddProfessor(input.split("\\s"));
            } else if (input.startsWith("add course")) {
                processAddCourse(input.split("\\s"));
            } else if (input.startsWith("show students")) {
                processShowStudents();
            } else if (input.startsWith("show professors")) {
                processShowProfessors();
            } else if (input.startsWith("show courses this semester")) {
                processShowThisSemesterCourses();
            } else if (input.startsWith("take course")) {
                processTakeCourse(input.split("\\s"));
            } else if (input.startsWith("show courses for student")) {
                processShowCoursesForStudentThisSemester(input.split("\\s"));
            } else if (input.startsWith("drop course")) {
                processDropCourse(input.split("\\s"));
            } else if (input.startsWith("submit course mark")) {
                processSubmitMark(input.split("\\s"));
            } else if (input.startsWith("show report for student")) {
                processShowReport(input.split("\\s"));
            } else if (input.startsWith("go next semester")) {
                processGoNextSemester();
            } else if (input.startsWith("show courses history")) {
                processShowCoursesHistory(input.split("\\s"));
            } else if (input.startsWith("receive loan")) {
                processReceiveLoan(input.split("\\s"));
            } else {
                System.err.println("invalid command");
            }
        }
    }

    public void runWithMenu() {
        Menu.setScanner(this.scanner);
        Menu.setManager(this.manager);

        Menu currentMenu = new MainMenu();
        currentMenu.show();
        currentMenu.execute();
    }
}
