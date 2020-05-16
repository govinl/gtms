package com.xyafu.gtms.entity;

public class Score {
    private String stuid;

    private String stuname;

    private Double myteascore;

    private Double anoterteascore;

    private Double replyscore;

    private Double endscore;

    private String scorelevel;

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid == null ? null : stuid.trim();
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname == null ? null : stuname.trim();
    }

    public Double getMyteascore() {
        return myteascore;
    }

    public void setMyteascore(Double myteascore) {
        this.myteascore = myteascore;
    }

    public Double getAnoterteascore() {
        return anoterteascore;
    }

    public void setAnoterteascore(Double anoterteascore) {
        this.anoterteascore = anoterteascore;
    }

    public Double getReplyscore() {
        return replyscore;
    }

    public void setReplyscore(Double replyscore) {
        this.replyscore = replyscore;
    }

    public Double getEndscore() {
        return endscore;
    }

    public void setEndscore(Double endscore) {
        this.endscore = endscore;
    }

    public String getScorelevel() {
        return scorelevel;
    }

    public void setScorelevel(String scorelevel) {
        this.scorelevel = scorelevel == null ? null : scorelevel.trim();
    }
}