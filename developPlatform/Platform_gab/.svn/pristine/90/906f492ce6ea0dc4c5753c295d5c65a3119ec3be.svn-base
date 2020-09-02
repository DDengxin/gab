package com.tengzhi.business.platform.specialist.chamberactivities.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author 鱼游浅水
 * @create 2020-06-23
 */
@Entity
@Table(name = "g_chamber_activities")
public class chamberActivities{
    private String activitiesId;
    private String title;
    private String classify;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date eventDateStart;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date eventDateEnd;
    private String image;
    private String briefIntroduction;
    private String content;
    private String status;
    private String founder;
    private String founderId;
    private String founderSCompany;
    private String annex;
    private boolean homeShow;


    @Id
    @Basic
    @Column(name = "activities_id", nullable = true, length = 32)
    public String getActivitiesId() {
        return activitiesId;
    }

    public void setActivitiesId(String activitiesId) {
        this.activitiesId = activitiesId;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "classify", nullable = true, length = 80)
    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public Date getEventDateStart() {
        return eventDateStart;
    }

    public void setEventDateStart(Date eventDateStart) {
        this.eventDateStart = eventDateStart;
    }

    public Date getEventDateEnd() {
        return eventDateEnd;
    }

    public void setEventDateEnd(Date eventDateEnd) {
        this.eventDateEnd = eventDateEnd;
    }

    @Basic
    @Column(name = "image", nullable = true, length = 255)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "brief_introduction", nullable = true, length = 255)
    public String getBriefIntroduction() {
        return briefIntroduction;
    }

    public void setBriefIntroduction(String briefIntroduction) {
        this.briefIntroduction = briefIntroduction;
    }

    @Basic
    @Column(name = "content", nullable = true, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 5)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "founder", nullable = true, length = 32)
    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    @Basic
    @Column(name = "founder_id", nullable = true, length = 32)
    public String getFounderId() {
        return founderId;
    }

    public void setFounderId(String founderId) {
        this.founderId = founderId;
    }

    @Basic
    @Column(name = "founder_s_company", nullable = true, length = 32)
    public String getFounderSCompany() {
        return founderSCompany;
    }

    public void setFounderSCompany(String founderSCompany) {
        this.founderSCompany = founderSCompany;
    }

    @Basic
    @Column(name = "annex", nullable = true, length = 255)
    public String getAnnex() {
        return annex;
    }

    public void setAnnex(String annex) {
        this.annex = annex;
    }

    public boolean getHomeShow() {
        return homeShow;
    }

    public void setHomeShow(boolean homeShow) {
        this.homeShow = homeShow;
    }
}
