package edu.menu;

import edu.Professor;
import edu.Student;

import java.util.HashMap;

public class ManagingPeopleMenu extends Menu {
    public ManagingPeopleMenu(Menu parentMenu) {
        super("Managing People", parentMenu);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getAddStudentMenu());
        submenus.put(2, getShowStudentsMenu());
        submenus.put(3, getAddProfessorMenu());
        submenus.put(4, getShowProfessorsMenu());
        this.setSubmenus(submenus);
    }

    private Menu getAddStudentMenu() {
        return new Menu("Add Student", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter student's information or Back to return");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                } else {
                    String[] splitInput = input.split("\\s");
                    manager.addStudent(splitInput[0], splitInput[1], splitInput[2], splitInput[3]);
                    this.show();
                    this.execute();
                }
            }
        };
    }
    private Menu getShowStudentsMenu() {
        return new Menu("List Of Students", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter Back to return");
            }

            @Override
            public void execute() {
                for (Student student : manager.getStudents()) {
                    System.out.println(student);
                }
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                }
            }
        };
    }
    private Menu getAddProfessorMenu() {
        return new Menu("Add Professor", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter professor's information or Back to return");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                } else {
                    String[] splitInput = input.split("\\s");
                    manager.addProfessor(splitInput[0], splitInput[1], splitInput[2], splitInput[3]);
                    this.show();
                    this.execute();
                }
            }
        };
    }
    private Menu getShowProfessorsMenu() {
        return new Menu("List Of Professors", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter Back to return");
            }

            @Override
            public void execute() {
                for (Professor professor : manager.getProfessors()) {
                    System.out.println(professor);
                }
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                }
            }
        };
    }

}
