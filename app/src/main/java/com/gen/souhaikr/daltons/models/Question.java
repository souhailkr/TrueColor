package com.gen.souhaikr.daltons.models;

/**
 * Created by SouhaiKr on 25/02/2019.
 */

public class Question {

    private int ID;
    private String OPTA;
    private String OPTB;
    private String OPTC;
    private String OPTD;
    private String ANSWER;
    private String Pr;
    private String De;



    public Question() {
    }

    public Question(String OPTA, String OPTB, String OPTC, String OPTD, String ANSWER , String Pr , String De) {
        this.OPTA = OPTA;
        this.OPTB = OPTB;
        this.OPTC = OPTC;
        this.OPTD = OPTD;
        this.ANSWER = ANSWER;
        this.Pr = Pr;
        this.De = De ;

    }

    public Question(String OPTA, String OPTB, String OPTC, String OPTD) {
        this.OPTA = OPTA;
        this.OPTB = OPTB;
        this.OPTC = OPTC;
        this.OPTD = OPTD;
    }

    public String getPr() {
        return Pr;
    }

    public void setPr(String pr) {
        Pr = pr;
    }

    public String getDe() {
        return De;
    }

    public void setDe(String de) {
        De = de;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getOPTA() {
        return OPTA;
    }

    public void setOPTA(String OPTA) {
        this.OPTA = OPTA;
    }

    public String getOPTB() {
        return OPTB;
    }

    public void setOPTB(String OPTB) {
        this.OPTB = OPTB;
    }

    public String getOPTC() {
        return OPTC;
    }

    public void setOPTC(String OPTC) {
        this.OPTC = OPTC;
    }

    public String getOPTD() {
        return OPTD;
    }

    public void setOPTD(String OPTD) {
        this.OPTD = OPTD;
    }

    public String getANSWER() {
        return ANSWER;
    }

    public void setANSWER(String ANSWER) {
        this.ANSWER = ANSWER;
    }
}
