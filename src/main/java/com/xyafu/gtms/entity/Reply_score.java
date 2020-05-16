package com.xyafu.gtms.entity;

public class Reply_score {
    private Integer id;

    private String stuid;

    private String stuname;

    private String teaid;

    private String teaname;

    private String ptitle;

    private Double fscore;

    private Double sscore;

    private Double tscore;

    private Double sumcore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getTeaid() {
        return teaid;
    }

    public void setTeaid(String teaid) {
        this.teaid = teaid == null ? null : teaid.trim();
    }

    public String getTeaname() {
        return teaname;
    }

    public void setTeaname(String teaname) {
        this.teaname = teaname == null ? null : teaname.trim();
    }

    public String getPtitle() {
        return ptitle;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle == null ? null : ptitle.trim();
    }

    public Double getFscore() {
        return fscore;
    }

    public void setFscore(Double fscore) {
        this.fscore = fscore;
    }

    public Double getSscore() {
        return sscore;
    }

    public void setSscore(Double sscore) {
        this.sscore = sscore;
    }

    public Double getTscore() {
        return tscore;
    }

    public void setTscore(Double tscore) {
        this.tscore = tscore;
    }

    public Double getSumcore() {
        return sumcore;
    }

    public void setSumcore(Double sumcore) {
        this.sumcore = sumcore;
    }
}