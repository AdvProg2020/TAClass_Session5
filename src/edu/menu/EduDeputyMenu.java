package edu.menu;

import java.util.HashMap;

public class EduDeputyMenu extends Menu {
    public EduDeputyMenu(Menu parentMenu) {
        super("Edu Deputy Menu", parentMenu);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, new ManagingPeopleMenu(this));
        submenus.put(2, new ManagingCoursesMenu(this));
        this.setSubmenus(submenus);
    }
}
