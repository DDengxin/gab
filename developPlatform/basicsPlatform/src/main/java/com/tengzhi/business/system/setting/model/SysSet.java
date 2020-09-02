package com.tengzhi.business.system.setting.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "sys_set")
@IdClass(SysSet.SysSet_PK.class)
public class SysSet implements Serializable{

	private String sysItemid;
	private String sysItem;
	private String sysValue;
	private String sysFile;
	private String sysOrd;
	@Id
	private String sysSecode;
	@Id
	private String sysCorp;
	
	public String getSysItemid() {
		return sysItemid;
	}
	public void setSysItemid(String sysItemid) {
		this.sysItemid = sysItemid;
	}
	public String getSysItem() {
		return sysItem;
	}
	public void setSysItem(String sysItem) {
		this.sysItem = sysItem;
	}
	public String getSysValue() {
		return sysValue;
	}
	public void setSysValue(String sysValue) {
		this.sysValue = sysValue;
	}
	public String getSysOrd() {
		return sysOrd;
	}
	public void setSysOrd(String sysOrd) {
		this.sysOrd = sysOrd;
	}
	public String getSysCorp() {
		return sysCorp;
	}
	public void setSysCorp(String sysCorp) {
		this.sysCorp = sysCorp;
	}
	public String getSysFile() {
		return sysFile;
	}
	public void setSysFile(String sysFile) {
		this.sysFile = sysFile;
	}
	public String getSysSecode() {
		return sysSecode;
	}
	public void setSysSecode(String sysSecode) {
		this.sysSecode = sysSecode;
	}

	public static class SysSet_PK implements Serializable{

		private String sysSecode;
		private String sysCorp;
		public String getSysCorp() {
			return sysCorp;
		}
		public void setSysCorp(String sysCorp) {
			this.sysCorp = sysCorp;
		}
		public String getSysSecode() {
			return sysSecode;
		}
		public void setSysSecode(String sysSecode) {
			this.sysSecode = sysSecode;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			SysSet_PK sysSet_pk = (SysSet_PK) o;
			return Objects.equals(sysSecode, sysSet_pk.sysSecode) &&
					Objects.equals(sysCorp, sysSet_pk.sysCorp);
		}

		@Override
		public int hashCode() {
			return Objects.hash(sysSecode, sysCorp);
		}
	}

}
