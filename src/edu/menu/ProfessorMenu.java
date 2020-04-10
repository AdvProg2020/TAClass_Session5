package edu.menu;

import java.util.HashMap;

public class ProfessorMenu extends Menu {
    public ProfessorMenu(Menu parentMenu) {
        super("Professor Menu", parentMenu);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getSubmitMarkMenu());
        this.setSubmenus(submenus);
    }

    private Menu getSubmitMarkMenu() {
        return new Menu("Submit Mark", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter course's name, mark and student's id or Back to return");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                } else {
                    String[] splitInput = input.split("\\s");
                    manager.submitCourseMarkForStudent(splitInput[0], Float.parseFloat(splitInput[1]), splitInput[2]);
                    this.show();
                    this.execute();
                }
            }
        };
    }

}
