package com.putt2gether.bean;

/**
 * Created by Ajay on 23/06/2016.
 */
public class GroupsBean {

    private String name;
    private String image;
    private String id;

    private boolean clicked;
    private boolean clickedRemove;

    private String isAdded;

    public String getIsAdded() {
        return isAdded;
    }

    public void setIsAdded(String isAdded) {
        this.isAdded = isAdded;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public boolean isClickedRemove() {
        return clickedRemove;
    }

    public void setClickedRemove(boolean clickedRemove) {
        this.clickedRemove = clickedRemove;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
