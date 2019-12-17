package com.swe.comin.models;

public class DBRequest {
    public DBRequest() {
    }

    public DBRequest(String[] array1, String[] array2) {
        this.array1 = array1;
        this.array2 = array2;
    }

    public String[] getArray1() {
        return array1;
    }

    public void setArray1(String[] array1) {
        this.array1 = array1;
    }

    public String[] getArray2() {
        return array2;
    }

    public void setArray2(String[] array2) {
        this.array2 = array2;
    }

    String[] array1;
    String[] array2;
}
