package com.putt2gether.bean;

import java.util.Comparator;

/**
 * Created by Ajay on 09/07/2016.
 */
public class TeeBeanJ {

    private String j_tee_id;
    private String j_tee_name;
    private String j_tee_color;
    private String code;


    public TeeBeanJ(String code, String m_tee_id,String m_tee_name,String m_tee_color) {
        this.code = code;
        this.j_tee_id = m_tee_id;
        this.j_tee_name = m_tee_name;
        this.j_tee_color = m_tee_color;
    }

    public String getJ_tee_id() {
        return j_tee_id;
    }

    public void setJ_tee_id(String j_tee_id) {
        this.j_tee_id = j_tee_id;
    }

    public String getJ_tee_name() {
        return j_tee_name;
    }

    public void setJ_tee_name(String j_tee_name) {
        this.j_tee_name = j_tee_name;
    }

    public String getJ_tee_color() {
        return j_tee_color;
    }

    public void setJ_tee_color(String j_tee_color) {
        this.j_tee_color = j_tee_color;
    }




    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public static Comparator<TeeBeanJ> StuNameComparator = new Comparator<TeeBeanJ>() {

        public int compare(TeeBeanJ s1, TeeBeanJ s2) {
            String StudentName1 = s1.getCode();
            String StudentName2 = s2.getCode();

            //ascending order
            return StudentName2.compareTo(StudentName1);

            //descending order
            //return StudentName2.compareTo(StudentName1);
        }};

    @Override
    public String toString() {
        return "[ code=" + code + ", id=" + j_tee_id + ", name=" + j_tee_name + ", name=" + j_tee_color +"]";
    }
}