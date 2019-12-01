package com.example.myapplication;

public class Terms {
    String ARTerm;
    String ERTerm;
    String ARDef;
    String ENDef;
    String ID;
    String selectedSection;


    public Terms() {
    }

    public Terms(String ID, String ARTerm, String ERTerm, String ARDef, String ENDef, String selectedSection) {
        this.ARTerm = ARTerm;
        this.ERTerm = ERTerm;
        this.ARDef = ARDef;
        this.ENDef = ENDef;
        this.ID = ID;
        this.selectedSection = selectedSection;
    }

    public String getARTerm() {
        return ARTerm;
    }

    public String getERTerm() {
        return ERTerm;
    }

    public String getARDef() {
        return ARDef;
    }

    public String getENDef() {
        return ENDef;
    }

    public String getID() {
        return ID;
    }

    public String getSelectedSection() {
        return selectedSection;
    }
}
