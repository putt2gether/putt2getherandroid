package com.putt2gether.fragments.statsfrag;

/**
 * Created by Ajay on 05/12/2016.
 *

 score_stats {
 avg_gross_score 5.3,
 avg_par3s 0.3,
 avg_par4s 1.28,
 avg_par5s 1.55,
 avg_out 0.3,
 avg_in 0.1,
 last_gross_score 0,
 last_par3s 0,
 last_par4s 0,
 last_par5s 0,
 last_out 0,
 last_in 0
 },
 gir_percentage {
 hit 6,
 missed 38
 },
 fairway_percentage {
 left 0,
 right 1,
 hit 0
 },
 putting_stats {
 per_hole_avg 0.05,
 per_gir_avg 0.2,
 per_round_avg 0.3
 },
 recovery_stats {
 scrmbl_avg 1,
 sand_avg 0

 "gscore_change": 0.81,
 "gscore_change_color": "#ff0000",
 "par3_change": 0.05,
 "par3_change_color": "#325604",
 "par4_change": 0.09,
 "par4_change_color": "#325604",
 "par5_change": 0.18,
 "par5_change_color": "#325604",
 "in_change": 0.33,
 "in_change_color": "#ff0000",
 "out_change": 0.2,
 "out_change_color": "#ff0000"

 */
public class StatsBean {


    String gscore_change;
    String gscore_change_color ;
    String par3_change ;
    String par3_change_color ;
    String par4_change ;
    String par4_change_color ;
    String par5_change ;
    String par5_change_color ;
    String in_change ;
    String in_change_color ;
    String out_change ;
    String out_change_color ;

    String avg_gross_score;
    String avg_par3s ;
    String avg_par4s ;
    String avg_par5s ;
    String avg_out ;
    String avg_in ;
    String last_gross_score ;
    String last_par3s ;
    String last_par4s ;
    String last_par5s ;
    String last_out ;
    String last_in ;

    String gir_hit;
    String gir_missed;

    String per_hole_avg;
    String per_gir_avg;
    String per_round_avg;

    String scrmbl_avg;
    String sand_avg;

    String left_fairway;
    String right_fairway;
    String hit_fairway;


    public String getGscore_change() {
        return gscore_change;
    }

    public void setGscore_change(String gscore_change) {
        this.gscore_change = gscore_change;
    }

    public String getGscore_change_color() {
        return gscore_change_color;
    }

    public void setGscore_change_color(String gscore_change_color) {
        this.gscore_change_color = gscore_change_color;
    }

    public String getPar3_change() {
        return par3_change;
    }

    public void setPar3_change(String par3_change) {
        this.par3_change = par3_change;
    }

    public String getPar3_change_color() {
        return par3_change_color;
    }

    public void setPar3_change_color(String par3_change_color) {
        this.par3_change_color = par3_change_color;
    }

    public String getPar4_change() {
        return par4_change;
    }

    public void setPar4_change(String par4_change) {
        this.par4_change = par4_change;
    }

    public String getPar4_change_color() {
        return par4_change_color;
    }

    public void setPar4_change_color(String par4_change_color) {
        this.par4_change_color = par4_change_color;
    }

    public String getPar5_change() {
        return par5_change;
    }

    public void setPar5_change(String par5_change) {
        this.par5_change = par5_change;
    }

    public String getPar5_change_color() {
        return par5_change_color;
    }

    public void setPar5_change_color(String par5_change_color) {
        this.par5_change_color = par5_change_color;
    }

    public String getIn_change() {
        return in_change;
    }

    public void setIn_change(String in_change) {
        this.in_change = in_change;
    }

    public String getIn_change_color() {
        return in_change_color;
    }

    public void setIn_change_color(String in_change_color) {
        this.in_change_color = in_change_color;
    }

    public String getOut_change() {
        return out_change;
    }

    public void setOut_change(String out_change) {
        this.out_change = out_change;
    }

    public String getOut_change_color() {
        return out_change_color;
    }

    public void setOut_change_color(String out_change_color) {
        this.out_change_color = out_change_color;
    }

    public String getAvg_gross_score() {
        return avg_gross_score;
    }

    public void setAvg_gross_score(String avg_gross_score) {
        this.avg_gross_score = avg_gross_score;
    }

    public String getAvg_par3s() {
        return avg_par3s;
    }

    public void setAvg_par3s(String avg_par3s) {
        this.avg_par3s = avg_par3s;
    }

    public String getAvg_par4s() {
        return avg_par4s;
    }

    public void setAvg_par4s(String avg_par4s) {
        this.avg_par4s = avg_par4s;
    }

    public String getAvg_out() {
        return avg_out;
    }

    public void setAvg_out(String avg_out) {
        this.avg_out = avg_out;
    }

    public String getAvg_par5s() {
        return avg_par5s;
    }

    public void setAvg_par5s(String avg_par5s) {
        this.avg_par5s = avg_par5s;
    }

    public String getAvg_in() {
        return avg_in;
    }

    public void setAvg_in(String avg_in) {
        this.avg_in = avg_in;
    }

    public String getLast_gross_score() {
        return last_gross_score;
    }

    public void setLast_gross_score(String last_gross_score) {
        this.last_gross_score = last_gross_score;
    }

    public String getLast_par3s() {
        return last_par3s;
    }

    public void setLast_par3s(String last_par3s) {
        this.last_par3s = last_par3s;
    }

    public String getLast_par4s() {
        return last_par4s;
    }

    public void setLast_par4s(String last_par4s) {
        this.last_par4s = last_par4s;
    }

    public String getLast_par5s() {
        return last_par5s;
    }

    public void setLast_par5s(String last_par5s) {
        this.last_par5s = last_par5s;
    }

    public String getLast_out() {
        return last_out;
    }

    public void setLast_out(String last_out) {
        this.last_out = last_out;
    }

    public String getLast_in() {
        return last_in;
    }

    public void setLast_in(String last_in) {
        this.last_in = last_in;
    }

    public String getGir_hit() {
        return gir_hit;
    }

    public void setGir_hit(String gir_hit) {
        this.gir_hit = gir_hit;
    }

    public String getGir_missed() {
        return gir_missed;
    }

    public void setGir_missed(String gir_missed) {
        this.gir_missed = gir_missed;
    }

    public String getPer_hole_avg() {
        return per_hole_avg;
    }

    public void setPer_hole_avg(String per_hole_avg) {
        this.per_hole_avg = per_hole_avg;
    }

    public String getPer_gir_avg() {
        return per_gir_avg;
    }

    public void setPer_gir_avg(String per_gir_avg) {
        this.per_gir_avg = per_gir_avg;
    }

    public String getPer_round_avg() {
        return per_round_avg;
    }

    public void setPer_round_avg(String per_round_avg) {
        this.per_round_avg = per_round_avg;
    }

    public String getScrmbl_avg() {
        return scrmbl_avg;
    }

    public void setScrmbl_avg(String scrmbl_avg) {
        this.scrmbl_avg = scrmbl_avg;
    }

    public String getSand_avg() {
        return sand_avg;
    }

    public void setSand_avg(String sand_avg) {
        this.sand_avg = sand_avg;
    }

    public String getLeft_fairway() {
        return left_fairway;
    }

    public void setLeft_fairway(String left_fairway) {
        this.left_fairway = left_fairway;
    }

    public String getRight_fairway() {
        return right_fairway;
    }

    public void setRight_fairway(String right_fairway) {
        this.right_fairway = right_fairway;
    }

    public String getHit_fairway() {
        return hit_fairway;
    }

    public void setHit_fairway(String hit_fairway) {
        this.hit_fairway = hit_fairway;
    }
}
