package com.turtywurty.railroad.fetch.version.forge;

//Author: MMD. Taken and changed on 08/02/21
public class ForgeVersion {
    private String recommended;
    private String latest;

    public ForgeVersion() {
    }

    public ForgeVersion(String recommended, String latest) {
        this.recommended = recommended;
        this.latest = latest;
    }

    public String getRecommended() {
        return recommended;
    }

    public void setRecommended(String recommended) {
        this.recommended = recommended;
    }

    public String getLatest() {
        return latest;
    }

    public void setLatest(String latest) {
        this.latest = latest;
    }
}
