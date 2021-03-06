package com.tengzhi.business.platform.manage.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
/**
 * 服务管理
 * @author lsh
 *
 */
@Entity
@Table(name = "g_service_manage")
public class G_ServiceManage {
	private String serviceNote;
	private String serviceCode;
	private String serviceProduct;
	private String serviceType;
	private String serviceResult;
	private String productImg;
	private String productImgPaper;
	private String productFile;
	private String serviceTitle;
	private String serviceFlag;
	private String dataMan;
	private String dataCorp;
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataDate;
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date applyDate;
	private String applyMan;
	
	@Id
	public String getServiceNote() {
		return serviceNote;
	}
	public void setServiceNote(String serviceNote) {
		this.serviceNote = serviceNote;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	
	public String getServiceProduct() {
		return serviceProduct;
	}
	public void setServiceProduct(String serviceProduct) {
		this.serviceProduct = serviceProduct;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getServiceResult() {
		return serviceResult;
	}
	public void setServiceResult(String serviceResult) {
		this.serviceResult = serviceResult;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public String getProductImgPaper() {
		return productImgPaper;
	}
	public void setProductImgPaper(String productImgPaper) {
		this.productImgPaper = productImgPaper;
	}
	public String getProductFile() {
		return productFile;
	}
	public void setProductFile(String productFile) {
		this.productFile = productFile;
	}
	public String getServiceTitle() {
		return serviceTitle;
	}
	public void setServiceTitle(String serviceTitle) {
		this.serviceTitle = serviceTitle;
	}
	public String getServiceFlag() {
		return serviceFlag;
	}
	public void setServiceFlag(String serviceFlag) {
		this.serviceFlag = serviceFlag;
	}
	public String getDataMan() {
		return dataMan;
	}
	public void setDataMan(String dataMan) {
		this.dataMan = dataMan;
	}
	public String getDataCorp() {
		return dataCorp;
	}
	public void setDataCorp(String dataCorp) {
		this.dataCorp = dataCorp;
	}
	public Date getDataDate() {
		return dataDate;
	}
	public void setDataDate(Date dataDate) {
		this.dataDate = dataDate;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public String getApplyMan() {
		return applyMan;
	}
	public void setApplyMan(String applyMan) {
		this.applyMan = applyMan;
	}
}
