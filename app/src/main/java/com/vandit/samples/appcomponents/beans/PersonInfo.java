package com.vandit.samples.appcomponents.beans;

/**
 * Created by vandi on 1/4/2017.
 */

public class PersonInfo {

    String mPersonName;
    String mPersonSong;
    String mPersonAvtar;

    public PersonInfo() {
    }

    public PersonInfo(String name, String personSong, String personAvtar){
        this.mPersonName = name;
        this.mPersonSong = personSong;
        this.mPersonAvtar = personAvtar;
    }

    public String getmPersonSong() {
        return mPersonSong;
    }

    public void setmPersonSong(String mPersonSong) {
        this.mPersonSong = mPersonSong;
    }

    public String getmPersonName() {
        return mPersonName;
    }

    public void setmPersonName(String mPersonName) {
        this.mPersonName = mPersonName;
    }

    public String getmPersonAvtar() {
        return mPersonAvtar;
    }

    public void setmPersonAvtar(String mPersonAvtar) {
        this.mPersonAvtar = mPersonAvtar;
    }
}
