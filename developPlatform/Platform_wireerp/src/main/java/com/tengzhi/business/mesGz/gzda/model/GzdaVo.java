package com.tengzhi.business.mesGz.gzda.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tengzhi.business.mesGz.gzck.vo.EckOut.ECkOut_PK;


public class GzdaVo {
	
	Gzda gzda;
    List<Gzda> gzdaMx,
    addedList = new ArrayList<>(),
    modifyedList = new ArrayList<>(),
    deletedList = new ArrayList<>();
    
    
    
	public Gzda getgzda() {
		return gzda;
	}
	public void setgzda(Gzda gzda) {
		this.gzda = gzda;
	}
	public List<Gzda> getGzdaMx() {
		return gzdaMx;
	}
	public void setGzdaMx(List<Gzda> gzdaMx) {
		this.gzdaMx = gzdaMx;
	}
	public List<Gzda> getAddedList() {
		return addedList;
	}
	public void setAddedList(List<Gzda> addedList) {
		this.addedList = addedList;
	}
	public List<Gzda> getModifyedList() {
		return modifyedList;
	}
	public void setModifyedList(List<Gzda> modifyedList) {
		this.modifyedList = modifyedList;
	}
	public List<Gzda> getDeletedList() {
		return deletedList;
	}
	public void setDeletedList(List<Gzda> deletedList) {
		this.deletedList = deletedList;
	}
    
public static class Gzda_PK implements Serializable {
    	
    	private String outNote;
    	private String outPack;
    	
		
		public String getOutNote() {
			return outNote;
		}


		public void setInNote(String inNote) {
			this.outNote = outNote;
		}


		public String getInPack() {
			return outPack;
		}


		public void setInPack(String inPack) {
			this.outPack = inPack;
		}


	


		public Gzda_PK(String outNote,String outPack) {
			setInNote(outNote);
			setInPack(outPack);
			
		}
		
		
	

    	
		public Gzda_PK() {
			
		}
}

	

}
