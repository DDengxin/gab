package com.tengzhi.business.quality.qualityArchives.qualityCertificate.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import com.tengzhi.business.quality.qualityArchives.qualityCertificate.model.QualityCertificate;
import com.tengzhi.business.sale.processProduct.processContract.vo.ProcessContractVo;

public class QualityCertificateVo implements Serializable {

	QualityCertificate qualityCertificate;
	List<QualityCertificate> qCDetailed,
	addedList = new ArrayList<>(),
    modifyedList = new ArrayList<>(),
    deleteList = new ArrayList<>();
	
	public QualityCertificate getQualityCertificate() {
		return qualityCertificate;
	}
	public void setQualityCertificate(QualityCertificate qualityCertificate) {
		this.qualityCertificate = qualityCertificate;
	}
	public List<QualityCertificate> getqCDetailed() {
		return qCDetailed;
	}
	public void setqCDetailed(List<QualityCertificate> qCDetailed) {
		this.qCDetailed = qCDetailed;
	}
	
	public List<QualityCertificate> getAddedList() {
		return addedList;
	}
	public void setAddedList(List<QualityCertificate> addedList) {
		this.addedList = addedList;
	}
	public List<QualityCertificate> getModifyedList() {
		return modifyedList;
	}
	public void setModifyedList(List<QualityCertificate> modifyedList) {
		this.modifyedList = modifyedList;
	}
	
	
	public List<QualityCertificate> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<QualityCertificate> deleteList) {
		this.deleteList = deleteList;
	}
	public static QualityCertificateVo initQualityCertificateVo(QualityCertificateVo qualityCertificateVo){
		//创建流低级方法(基于迭代器)
        StreamSupport.stream(qualityCertificateVo.getqCDetailed().spliterator(),false).forEach(qCDetailed -> {
        	switch (qCDetailed.get_state()) {
            case "added":
            	qCDetailed.setZmNote(qualityCertificateVo.getQualityCertificate().getZmNote());
            	qCDetailed.setZmDeliveryNo(qualityCertificateVo.getQualityCertificate().getZmDeliveryNo());
            	qCDetailed.setZmRq(qualityCertificateVo.getQualityCertificate().getZmRq());
            	qualityCertificateVo.getAddedList().add(qCDetailed);
                break;
            case "modified":
            	qCDetailed.setZmNote(qualityCertificateVo.getQualityCertificate().getZmNote());
            	qCDetailed.setZmDeliveryNo(qualityCertificateVo.getQualityCertificate().getZmDeliveryNo());
            	qCDetailed.setZmRq(qualityCertificateVo.getQualityCertificate().getZmRq());
            	qualityCertificateVo.getAddedList().add(qCDetailed);
                break;
            case "removed":
            	qCDetailed.setZmNote(qualityCertificateVo.getQualityCertificate().getZmNote());
            	qCDetailed.setZmDeliveryNo(qualityCertificateVo.getQualityCertificate().getZmDeliveryNo());
            	qCDetailed.setZmRq(qualityCertificateVo.getQualityCertificate().getZmRq());
            	qualityCertificateVo.getAddedList().add(qCDetailed);
                break;
        	}
        	
        });
        return qualityCertificateVo;
	}
}
