package com.tengzhi.business.finance.voucher.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "e_f_voucher_ac")
public class Account {
	    @Id
	   	private Long faccountid;
		private String fnumber;
		private String fname;
		private Integer flevel;
		private Integer fdetail;
		private Long fparentid;
		private Long frootid;
		private Long fgroupid;
		private Long fdc;
		private String fhelpercode;
		private Long fcurrencyid;
		private Long fadjustrate;
		private Integer fearnaccountid;
		private Integer fquantities;
		private Integer funitgroupid;
		private Integer fmeasureunitid;
		private Integer fiscash;
		private Integer fisbank;
		private Integer fjournal;
		private Integer fcontact;
		private Integer fiscashflow;
		private Integer fdetailid;
		private Integer facnt;
		private Integer floan;
		private Integer fdpst;
		private Integer fstateddpst;
		private Integer finterest;
		private Integer fisacnt;
		private Integer fisbudget;
		private Integer facntid;
		private String fbrno;
		private Integer facctint;
		private Float fintrate;
		private Date flastintdate;
		private Integer facnttype;
		private String ftradenum;
		private Integer fcontrol;
		private Integer fviewmsg;
		private String fmessage;
		private Integer fdelete;
		private Integer fisbusi;
		private String ffullname;
		private Integer fsystemtype;
		private Integer fcontrolsystem;
		private String uuid;
		private String frightuserid;
		private Integer fisbalance;

		private Integer fisdelete;
		private Integer fcustom;
		private Integer fsupplier;
		private Integer fdept;
		private Integer femp;
		private Integer fisrateadj;
		private Integer fisqtyaux;
		private Integer fistrans;
		private Integer finventory;
		private Integer fisitem;
		private Long fplaccountid;
		private Integer fproject;
		private Integer flimited;
		private Integer fitemclassid;
		private String funit;
		private String fcur;
		private Integer fisjrnl;
		private Integer fclassid;
		private Integer fqt;
		private String dataCorp;

		public Long getFaccountid() {
			return faccountid;
		}

		public void setFaccountid(Long faccountid) {
			this.faccountid = faccountid;
		}

		public String getFnumber() {
			return fnumber;
		}

		public void setFnumber(String fnumber) {
			this.fnumber = fnumber;
		}

		public String getFname() {
			return fname;
		}

		public void setFname(String fname) {
			this.fname = fname;
		}

		public Integer getFlevel() {
			return flevel;
		}

		public void setFlevel(Integer flevel) {
			this.flevel = flevel;
		}

		public Integer getFdetail() {
			return fdetail;
		}

		public void setFdetail(Integer fdetail) {
			this.fdetail = fdetail;
		}

		public Long getFparentid() {
			return fparentid;
		}

		public void setFparentid(Long fparentid) {
			this.fparentid = fparentid;
		}

		public Long getFrootid() {
			return frootid;
		}

		public void setFrootid(Long frootid) {
			this.frootid = frootid;
		}

		public Long getFgroupid() {
			return fgroupid;
		}

		public void setFgroupid(Long fgroupid) {
			this.fgroupid = fgroupid;
		}

		public Long getFdc() {
			return fdc;
		}

		public void setFdc(Long fdc) {
			this.fdc = fdc;
		}

		public String getFhelpercode() {
			return fhelpercode;
		}

		public void setFhelpercode(String fhelpercode) {
			this.fhelpercode = fhelpercode;
		}

		public Long getFcurrencyid() {
			return fcurrencyid;
		}

		public void setFcurrencyid(Long fcurrencyid) {
			this.fcurrencyid = fcurrencyid;
		}

		public Long getFadjustrate() {
			return fadjustrate;
		}

		public void setFadjustrate(Long fadjustrate) {
			this.fadjustrate = fadjustrate;
		}

		public Integer getFearnaccountid() {
			return fearnaccountid;
		}

		public void setFearnaccountid(Integer fearnaccountid) {
			this.fearnaccountid = fearnaccountid;
		}

		public Integer getFquantities() {
			return fquantities;
		}

		public void setFquantities(Integer fquantities) {
			this.fquantities = fquantities;
		}

		public Integer getFunitgroupid() {
			return funitgroupid;
		}

		public void setFunitgroupid(Integer funitgroupid) {
			this.funitgroupid = funitgroupid;
		}

		public Integer getFmeasureunitid() {
			return fmeasureunitid;
		}

		public void setFmeasureunitid(Integer fmeasureunitid) {
			this.fmeasureunitid = fmeasureunitid;
		}

		public Integer getFiscash() {
			return fiscash;
		}

		public void setFiscash(Integer fiscash) {
			this.fiscash = fiscash;
		}

		public Integer getFisbank() {
			return fisbank;
		}

		public void setFisbank(Integer fisbank) {
			this.fisbank = fisbank;
		}

		public Integer getFjournal() {
			return fjournal;
		}

		public void setFjournal(Integer fjournal) {
			this.fjournal = fjournal;
		}

		public Integer getFcontact() {
			return fcontact;
		}

		public void setFcontact(Integer fcontact) {
			this.fcontact = fcontact;
		}

		public Integer getFiscashflow() {
			return fiscashflow;
		}

		public void setFiscashflow(Integer fiscashflow) {
			this.fiscashflow = fiscashflow;
		}

		public Integer getFdetailid() {
			return fdetailid;
		}

		public void setFdetailid(Integer fdetailid) {
			this.fdetailid = fdetailid;
		}

		public Integer getFacnt() {
			return facnt;
		}

		public void setFacnt(Integer facnt) {
			this.facnt = facnt;
		}

		public Integer getFloan() {
			return floan;
		}

		public void setFloan(Integer floan) {
			this.floan = floan;
		}

		public Integer getFdpst() {
			return fdpst;
		}

		public void setFdpst(Integer fdpst) {
			this.fdpst = fdpst;
		}

		public Integer getFstateddpst() {
			return fstateddpst;
		}

		public void setFstateddpst(Integer fstateddpst) {
			this.fstateddpst = fstateddpst;
		}

		public Integer getFinterest() {
			return finterest;
		}

		public void setFinterest(Integer finterest) {
			this.finterest = finterest;
		}

		public Integer getFisacnt() {
			return fisacnt;
		}

		public void setFisacnt(Integer fisacnt) {
			this.fisacnt = fisacnt;
		}

		public Integer getFisbudget() {
			return fisbudget;
		}

		public void setFisbudget(Integer fisbudget) {
			this.fisbudget = fisbudget;
		}

		public Integer getFacntid() {
			return facntid;
		}

		public void setFacntid(Integer facntid) {
			this.facntid = facntid;
		}

		public String getFbrno() {
			return fbrno;
		}

		public void setFbrno(String fbrno) {
			this.fbrno = fbrno;
		}

		public Integer getFacctint() {
			return facctint;
		}

		public void setFacctint(Integer facctint) {
			this.facctint = facctint;
		}

		public Float getFintrate() {
			return fintrate;
		}

		public void setFintrate(Float fintrate) {
			this.fintrate = fintrate;
		}

		public Date getFlastintdate() {
			return flastintdate;
		}

		public void setFlastintdate(Date flastintdate) {
			this.flastintdate = flastintdate;
		}

		public Integer getFacnttype() {
			return facnttype;
		}

		public void setFacnttype(Integer facnttype) {
			this.facnttype = facnttype;
		}

		public String getFtradenum() {
			return ftradenum;
		}

		public void setFtradenum(String ftradenum) {
			this.ftradenum = ftradenum;
		}

		public Integer getFcontrol() {
			return fcontrol;
		}

		public void setFcontrol(Integer fcontrol) {
			this.fcontrol = fcontrol;
		}

		public Integer getFviewmsg() {
			return fviewmsg;
		}

		public void setFviewmsg(Integer fviewmsg) {
			this.fviewmsg = fviewmsg;
		}

		public String getFmessage() {
			return fmessage;
		}

		public void setFmessage(String fmessage) {
			this.fmessage = fmessage;
		}

		public Integer getFdelete() {
			return fdelete;
		}

		public void setFdelete(Integer fdelete) {
			this.fdelete = fdelete;
		}

		public Integer getFisbusi() {
			return fisbusi;
		}

		public void setFisbusi(Integer fisbusi) {
			this.fisbusi = fisbusi;
		}

		public String getFfullname() {
			return ffullname;
		}

		public void setFfullname(String ffullname) {
			this.ffullname = ffullname;
		}

		public Integer getFsystemtype() {
			return fsystemtype;
		}

		public void setFsystemtype(Integer fsystemtype) {
			this.fsystemtype = fsystemtype;
		}

		public Integer getFcontrolsystem() {
			return fcontrolsystem;
		}

		public void setFcontrolsystem(Integer fcontrolsystem) {
			this.fcontrolsystem = fcontrolsystem;
		}

		public String getUuid() {
			return uuid;
		}

		public void setUuid(String uuid) {
			this.uuid = uuid;
		}

		public String getFrightuserid() {
			return frightuserid;
		}

		public void setFrightuserid(String frightuserid) {
			this.frightuserid = frightuserid;
		}

		public Integer getFisbalance() {
			return fisbalance;
		}

		public void setFisbalance(Integer fisbalance) {
			this.fisbalance = fisbalance;
		}

		public Integer getFisdelete() {
			return fisdelete;
		}

		public void setFisdelete(Integer fisdelete) {
			this.fisdelete = fisdelete;
		}

		public Integer getFcustom() {
			return fcustom;
		}

		public void setFcustom(Integer fcustom) {
			this.fcustom = fcustom;
		}

		public Integer getFsupplier() {
			return fsupplier;
		}

		public void setFsupplier(Integer fsupplier) {
			this.fsupplier = fsupplier;
		}

		public Integer getFdept() {
			return fdept;
		}

		public void setFdept(Integer fdept) {
			this.fdept = fdept;
		}

		public Integer getFemp() {
			return femp;
		}

		public void setFemp(Integer femp) {
			this.femp = femp;
		}

		public Integer getFisrateadj() {
			return fisrateadj;
		}

		public void setFisrateadj(Integer fisrateadj) {
			this.fisrateadj = fisrateadj;
		}

		public Integer getFisqtyaux() {
			return fisqtyaux;
		}

		public void setFisqtyaux(Integer fisqtyaux) {
			this.fisqtyaux = fisqtyaux;
		}

		public Integer getFistrans() {
			return fistrans;
		}

		public void setFistrans(Integer fistrans) {
			this.fistrans = fistrans;
		}

		public Integer getFinventory() {
			return finventory;
		}

		public void setFinventory(Integer finventory) {
			this.finventory = finventory;
		}

		public Integer getFisitem() {
			return fisitem;
		}

		public void setFisitem(Integer fisitem) {
			this.fisitem = fisitem;
		}

		public Long getFplaccountid() {
			return fplaccountid;
		}

		public void setFplaccountid(Long fplaccountid) {
			this.fplaccountid = fplaccountid;
		}

		public Integer getFproject() {
			return fproject;
		}

		public void setFproject(Integer fproject) {
			this.fproject = fproject;
		}

		public Integer getFlimited() {
			return flimited;
		}

		public void setFlimited(Integer flimited) {
			this.flimited = flimited;
		}

		public Integer getFitemclassid() {
			return fitemclassid;
		}

		public void setFitemclassid(Integer fitemclassid) {
			this.fitemclassid = fitemclassid;
		}

		public String getFunit() {
			return funit;
		}

		public void setFunit(String funit) {
			this.funit = funit;
		}

		public String getFcur() {
			return fcur;
		}

		public void setFcur(String fcur) {
			this.fcur = fcur;
		}

		public Integer getFisjrnl() {
			return fisjrnl;
		}

		public void setFisjrnl(Integer fisjrnl) {
			this.fisjrnl = fisjrnl;
		}

		public Integer getFclassid() {
			return fclassid;
		}

		public void setFclassid(Integer fclassid) {
			this.fclassid = fclassid;
		}

		public Integer getFqt() {
			return fqt;
		}

		public void setFqt(Integer fqt) {
			this.fqt = fqt;
		}

		public String getDataCorp() {
			return dataCorp;
		}

		public void setDataCorp(String dataCorp) {
			this.dataCorp = dataCorp;
		}
		


	
	
}

