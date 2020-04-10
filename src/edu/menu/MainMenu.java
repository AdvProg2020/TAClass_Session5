package edu.menu;

import java.util.HashMap;

public class MainMenu extends Menu {
    public MainMenu() {
        super("Main Menu", null);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, new StudentMenu(this));
        submenus.put(2, new ProfessorMenu(this));
        submenus.put(3, new EduDeputyMenu(this));
        this.setSubmenus(submenus);
    }
}
