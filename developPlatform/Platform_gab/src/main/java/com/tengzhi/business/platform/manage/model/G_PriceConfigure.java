package com.tengzhi.business.platform.manage.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
 * 报价配置
 * @author lsh
 *
 */
@Entity
@Table(name = "g_price_configure")
public class G_PriceConfigure {
private String pcNote;
private  String pcName;
private String pcType;
private Long  pcSort;
private String pcMan;
private String pcCorp;
private Date startTime;

@Id
public String getPcNote() {
	return pcNote;
}

public void setPcNote(String pcNote) {
	this.pcNote = pcNote;
}

public String getPcName() {
	return pcName;
}

public void setPcName(String pcName) {
	this.pcName = pcName;
}

public String getPcType() {
	return pcType;
}

public void setPcType(String pcType) {
	this.pcType = pcType;
}

public Long getPcSort() {
	return pcSort;
}

public void setPcSort(Long pcSort) {
	this.pcSort = pcSort;
}

public String getPcMan() {
	return pcMan;
}

public void setPcMan(String pcMan) {
	this.pcMan = pcMan;
}

public String getPcCorp() {
	return pcCorp;
}

public void setPcCorp(String pcCorp) {
	this.pcCorp = pcCorp;
}

public Date getStartTime() {
	return startTime;
}

public void setStartTime(Date startTime) {
	this.startTime = startTime;
}





}
