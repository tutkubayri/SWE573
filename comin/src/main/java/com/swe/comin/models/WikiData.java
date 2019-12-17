package com.swe.comin.models;

public class WikiData {

    public WikiData() {
    }

    public WikiData(String label, String qcode) {
        this.label = label;
        this.qcode = qcode;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getQcode() {
        return qcode;
    }

    public void setQcode(String qcode) {
        this.qcode = qcode;
    }

    public String label;
    public String qcode;
}
