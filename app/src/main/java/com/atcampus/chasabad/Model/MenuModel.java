package com.atcampus.chasabad.Model;

public class MenuModel {

    private int menuImage;
    private String menuTitle;

    public MenuModel(int menuImage, String menuTitle) {
        this.menuImage = menuImage;
        this.menuTitle = menuTitle;
    }

    public int getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(int menuImage) {
        this.menuImage = menuImage;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }
}
