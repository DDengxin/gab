package com.tengzhi.business.platform.specialist.advisory.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author 鱼游浅水
 * @create 2020-06-19
 */
@Entity
@Table(name = "g_advisory")
public class Advisorys {
    private String articleId;
    private String title;
    private String classify;
    private String whetherToPay;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;
    private String image;
    private String briefIntroduction;
    private String content;
    private String status;
    private String founder;
    private String founderId;
    private String founderSCompany;
    private String annex;
    private String functionalMark;
    private String relationProject;

    @Id
    @Column(name = "article_id", nullable = false, length = 32)
    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getFunctionalMark() {
        return functionalMark;
    }

    public void setFunctionalMark(String functionalMark) {
        this.functionalMark = functionalMark;
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

    @Basic
    @Column(name = "whether_to_pay", nullable = true)
    public String getWhetherToPay() {
        return whetherToPay;
    }

    public void setWhetherToPay(String whetherToPay) {
        this.whetherToPay = whetherToPay;
    }


    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
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
    @Column(name = "content", nullable = true, length = 500)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "status", nullable = true)
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
    
    
    @Basic
    @Column(name = "relationProject", nullable = true, length = 25)
    public String getRelationProject() {
		return relationProject;
	}

	public void setRelationProject(String relationProject) {
		this.relationProject = relationProject;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advisorys advisory = (Advisorys) o;
        return Objects.equals(articleId, advisory.articleId) &&
                Objects.equals(title, advisory.title) &&
                Objects.equals(classify, advisory.classify) &&
                Objects.equals(whetherToPay, advisory.whetherToPay) &&
                Objects.equals(releaseDate, advisory.releaseDate) &&
                Objects.equals(image, advisory.image) &&
                Objects.equals(briefIntroduction, advisory.briefIntroduction) &&
                Objects.equals(content, advisory.content) &&
                Objects.equals(status, advisory.status) &&
                Objects.equals(founder, advisory.founder) &&
                Objects.equals(founderId, advisory.founderId) &&
                Objects.equals(founderSCompany, advisory.founderSCompany) &&
                Objects.equals(annex, advisory.annex)&&
                Objects.equals(relationProject, advisory.relationProject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleId, title, classify, whetherToPay, releaseDate, image, briefIntroduction, content, status, founder, founderId, founderSCompany, annex,relationProject);
    }
}
