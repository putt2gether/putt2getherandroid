package com.putt2gether.bean;

/**
 * Created by Ajay on 31/01/2017.
 */
public class GroupPopBean  {
    public String  group_image;
    public String group_name;
    public String group_id;
    public boolean box;
    public String action;

    public GroupPopBean(String _describe, String _image, boolean _box, String _id,String actio) {
        group_name = _describe;
        group_id = _id;
        group_image = _image;
        box = _box;
        action = actio;
    }
}