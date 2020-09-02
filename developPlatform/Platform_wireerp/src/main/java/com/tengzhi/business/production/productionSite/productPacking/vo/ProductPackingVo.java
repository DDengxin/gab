package com.tengzhi.business.production.productionSite.productPacking.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import com.tengzhi.business.production.productionSite.siteTask.model.MScGclist;
import com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContract;
import com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContractDetailed;
import com.tengzhi.business.sale.saleProduct.saleContract.vo.EXsContractVo;

public class ProductPackingVo implements Serializable {

	MScGclist mScGclist;
	List<MScGclist> mScGclistMx,
    addedList = new ArrayList<>(),
    modifyedList = new ArrayList<>(),
    deletedList = new ArrayList<>();
	
	
	public MScGclist getmScGclist() {
		return mScGclist;
	}


	public void setmScGclist(MScGclist mScGclist) {
		this.mScGclist = mScGclist;
	}


	public List<MScGclist> getmScGclistMx() {
		return mScGclistMx;
	}


	public void setmScGclistMx(List<MScGclist> mScGclistMx) {
		this.mScGclistMx = mScGclistMx;
	}


	public List<MScGclist> getAddedList() {
		return addedList;
	}


	public void setAddedList(List<MScGclist> addedList) {
		this.addedList = addedList;
	}


	public List<MScGclist> getModifyedList() {
		return modifyedList;
	}


	public void setModifyedList(List<MScGclist> modifyedList) {
		this.modifyedList = modifyedList;
	}


	public List<MScGclist> getDeletedList() {
		return deletedList;
	}


	public void setDeletedList(List<MScGclist> deletedList) {
		this.deletedList = deletedList;
	}


	public static ProductPackingVo initProductPackingVo(ProductPackingVo productPackingVo){
        //创建流低级方法(基于迭代器)
        StreamSupport.stream(productPackingVo.getmScGclistMx().spliterator(),false).forEach(model -> {
            switch (model.get_state()) {
                case "added":
                	model.setPackNo(productPackingVo.getmScGclist().getPackNo());
                	productPackingVo.getAddedList().add(model);
                    break;
                case "modified":
                	productPackingVo.getModifyedList().add(model);
                    break;
            }
        });
        return productPackingVo;
    }
}
