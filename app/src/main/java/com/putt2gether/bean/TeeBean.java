package com.putt2gether.bean;

import java.util.Comparator;

/**
 * Created by Ajay on 09/07/2016.
 *
 * "tee_id": 4,
 "tee_name": "Yellow",
 "tee_color": "#FFFF00"
 */
public class TeeBean  {

    private String m_tee_id;
    private String m_tee_name;
    private String m_tee_color;
    private String code;


    public TeeBean(String code, String m_tee_id,String m_tee_name,String m_tee_color) {
        this.code = code;
        this.m_tee_id = m_tee_id;
        this.m_tee_name = m_tee_name;
        this.m_tee_color = m_tee_color;
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }



    public String getM_tee_id() {
        return m_tee_id;
    }

    public void setM_tee_id(String m_tee_id) {
        this.m_tee_id = m_tee_id;
    }

    public String getM_tee_name() {
        return m_tee_name;
    }

    public void setM_tee_name(String m_tee_name) {
        this.m_tee_name = m_tee_name;
    }

    public String getM_tee_color() {
        return m_tee_color;
    }

    public void setM_tee_color(String m_tee_color) {
        this.m_tee_color = m_tee_color;
    }


    public static Comparator<TeeBean> StuNameComparator = new Comparator<TeeBean>() {

        public int compare(TeeBean s1, TeeBean s2) {
            String StudentName1 = s1.getCode();
            String StudentName2 = s2.getCode();

            //ascending order
            return StudentName2.compareTo(StudentName1);

            //descending order
            //return StudentName2.compareTo(StudentName1);
        }};
    @Override
    public String toString() {
        return "[ code=" + code + ", id=" + m_tee_id + ", name=" + m_tee_name + ", name=" + m_tee_color +"]";
    }
}
