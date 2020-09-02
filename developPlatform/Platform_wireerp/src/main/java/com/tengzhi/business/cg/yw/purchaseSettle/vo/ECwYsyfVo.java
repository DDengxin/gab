package com.tengzhi.business.cg.yw.purchaseSettle.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import com.tengzhi.business.cg.yw.purchaseSettle.model.ECwYsyf;



public class ECwYsyfVo implements Serializable {
	
	ECwYsyf eCwYsyf;  
	List<ECwYsyf> eCwYsyfMx, addedList = new ArrayList<>();

	public List<ECwYsyf> geteCwYsyfMx() {
		return eCwYsyfMx;
	}


	public void seteCwYsyfMx(List<ECwYsyf> eCwYsyfMx) {
		this.eCwYsyfMx = eCwYsyfMx;
	}


	public List<ECwYsyf> getAddedList() {
		return addedList;
	}


	public ECwYsyf geteCwYsyf() {
		return eCwYsyf;
	}


	public void seteCwYsyf(ECwYsyf eCwYsyf) {
		this.eCwYsyf = eCwYsyf;
	}


	public static ECwYsyfVo initECwYsyfVo(ECwYsyfVo eCwYsyfVo){
        //创建流低级方法(基于迭代器)
        StreamSupport.stream(eCwYsyfVo.geteCwYsyfMx().spliterator(),false).forEach(eCwYsyfMx -> {
        	//eCwYsyfMx.setCwNote(eCwYsyfVo.geteCwYsyf().getCwNote());
        	eCwYsyfMx.setCwFkrq(eCwYsyfVo.geteCwYsyf().getCwFkrq());  
        	eCwYsyfVo.getAddedList().add(eCwYsyfMx);
        });
        return eCwYsyfVo;
    }

}
