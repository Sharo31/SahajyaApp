package com.example.vaibhav.sahajya2;

public class LostPersonData {


    private String LostPersonName;

    public LostPersonData() {
    }

    private String LostPersonAge;
    private  String LostPersonDesc;

    private String profileURL;

    private String mTime;

    public String getLostPersonAge() {
        return LostPersonAge;
    }

    public LostPersonData(String lostPersonName, String lostPersonAge, String lostPersonDesc, String profileURL, String mTime) {
        LostPersonName = lostPersonName;
        LostPersonAge = lostPersonAge;
        LostPersonDesc = lostPersonDesc;
        this.profileURL = profileURL;
        this.mTime = mTime;

    }

    public String getProfileURL() {
        return profileURL;
    }

    public String getLostPersonName() {
        return LostPersonName;
    }

    public String getLostPersonDesc() {
        return LostPersonDesc;
    }


    public String getmTime() {
        return mTime;
    }
}


