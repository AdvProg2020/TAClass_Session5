package edu.menu;

import edu.Course;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ManagingCoursesMenu extends Menu {
    public ManagingCoursesMenu(Menu parentMenu) {
        super("Managing Courses", parentMenu);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getAddCourseMenu());
        submenus.put(2, getShowCoursesThisSemesterMenu());
        submenus.put(3, getShowCoursesHistoryMenu());
        this.setSubmenus(submenus);
    }

    private Menu getAddCourseMenu() {
        return new Menu("Add Course", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter course's information or Back to return");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                } else {
                    String[] splitInput = input.split("\\s");
                    ArrayList<String> preCourses = new ArrayList<>();
                    if (splitInput.length == 4)
                        Collections.addAll(preCourses, splitInput[3].split(","));
                    manager.addCourse(splitInput[0], splitInput[1], splitInput[2], preCourses);
                    this.show();
                    this.execute();
                }
            }
        };
    }
    private Menu getShowCoursesThisSemesterMenu() {
        for (Menu menu : allMenus) {
            if (menu.getName().equals("Student Menu")) {
                StudentMenu studentMenu = (StudentMenu) menu;
                Menu showCoursesThisSemesterMenu = studentMenu.getShowCoursesThisSemesterMenu();
                showCoursesThisSemesterMenu.setParentMenu(this);
                return showCoursesThisSemesterMenu;
            }
        }
        return null;
    }
    private Menu getShowCoursesHistoryMenu() {
        return new Menu("List Of Courses History", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter semester or Back to return");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                } else {
                    for (Course course: manager.getCoursesHistory()) {
                        if (course.getSemester().equals(input))
                            System.out.println(course);
                    }
                    this.show();
                    this.execute();
                }
            }
        };
    }

}
