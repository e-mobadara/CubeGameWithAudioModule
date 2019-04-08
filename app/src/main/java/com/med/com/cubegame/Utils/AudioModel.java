package com.med.com.cubegame.Utils;

public class AudioModel {
    String aPath;
    String aName;

    public String getaPath() {
        return aPath;
    }

    public AudioModel(String aPath, String aName) {
        this.aPath = aPath;
        this.aName = aName;
    }

    public void setaPath(String aPath) {
        this.aPath = aPath;
    }
    public String getaName() {
        return aName;
    }
    public void setaName(String aName) {
        this.aName = aName;
    }
}