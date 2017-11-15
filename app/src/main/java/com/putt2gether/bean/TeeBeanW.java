package com.putt2gether.bean;

import java.util.Comparator;

/**
 * Created by Ajay on 09/07/2016.
 */
public class TeeBeanW {

    private String w_tee_id;
    private String w_tee_name;

    private String code;
    private String w_tee_color;

    public TeeBeanW(String code, String m_tee_id,String m_tee_name,String m_tee_color) {
        this.code = code;
        this.w_tee_id = m_tee_id;
        this.w_tee_name = m_tee_name;
        this.w_tee_color = m_tee_color;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getW_tee_id() {
        return w_tee_id;
    }

    public void setW_tee_id(String w_tee_id) {
        this.w_tee_id = w_tee_id;
    }

    public String getW_tee_name() {
        return w_tee_name;
    }

    public void setW_tee_name(String w_tee_name) {
        this.w_tee_name = w_tee_name;
    }

    public String getW_tee_color() {
        return w_tee_color;
    }

    public void setW_tee_color(String w_tee_color) {
        this.w_tee_color = w_tee_color;
    }





    public static Comparator<TeeBeanW> StuNameComparator = new Comparator<TeeBeanW>() {

        public int compare(TeeBeanW s1, TeeBeanW s2) {
            String StudentName1 = s1.getCode();
            String StudentName2 = s2.getCode();

            //ascending order
            return StudentName2.compareTo(StudentName1);

            //descending order
            //return StudentName2.compareTo(StudentName1);
        }};
    @Override
    public String toString() {
        return "[ code=" + code + ", id=" + w_tee_id + ", name=" + w_tee_name + ", name=" + w_tee_color +"]";
    }
}

