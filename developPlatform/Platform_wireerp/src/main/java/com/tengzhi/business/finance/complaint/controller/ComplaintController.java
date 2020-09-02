package com.tengzhi.business.finance.complaint.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.finance.complaint.model.Complaint;
import com.tengzhi.business.finance.complaint.model.ComplaintMx;
import com.tengzhi.business.finance.complaint.service.ComplaintService;

/**
 * @author: gaodu
 * @date: 2020/6/15 13:34
 **/
@RestController
@RequestMapping("/finance/complaint/")
public class ComplaintController {

	@Autowired
	private ComplaintService complaintService;
	
    /**
     * 页面视图跳转
     */
    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * list页面跳转
     */
    @GetMapping("list.html")
    public ModelAndView list(ModelAndView mv,@RequestParam String stype) {
    	mv.addObject("stype", stype);
        return mv;
    }
    
    /**
     * 查询数据
     */
    @PostMapping("search")
    public Result search(BaseDto dto) throws IOException {
    	return complaintService.search(dto).getResult();
    }
    
    /**
     * 新增数据 
     */
    @PostMapping("add.html")
    public Result add(@RequestBody Complaint complaint) throws Exception {
		return complaintService.add(complaint);
	}
    
    /**
     * 修改数据 
     */
    @PutMapping("add.html")
    public Result update(@RequestBody Complaint complaint) throws Exception {
		return complaintService.update(complaint);
	}
    
    /**
     * 删除数据
     */
    @DeleteMapping("deletAll/{ksNote}")
    public Result deletAll(@PathVariable("ksNote") String ksNote) throws Exception {
    	return complaintService.deleteAll(ksNote);
	}
    
    /**
     * 接收客诉
     */
    @PutMapping("accept/{ksNote}")
    public Result accept(@PathVariable("ksNote") String ksNote) {
    	return complaintService.accept(ksNote);
    }
    
    /**
     * 受理客诉
     */
    @PutMapping("acceptance")
    public Result acceptance(@RequestBody Complaint complaint) {
    	return complaintService.acceptance(complaint);
    }
    
    /**
     * 处理客诉
     */
    @PostMapping("dispose")
    public Result dispose(@RequestBody ComplaintMx complaintMx) throws Exception {
    	return complaintService.dispose(complaintMx);
    }
    
    /**
     * 处理查询数据
     */
    @PostMapping("gridSearch")
    public Result gridSearch(@RequestParam("ksNote") String ksNote) throws Exception {
    	return Result.resultOk(complaintService.gridSearch(ksNote));
    }
    
    /**
     * 处理状态修改
     */
    @GetMapping("disposeFlag/{ksNote}")
    public Result disposeFlag(@PathVariable("ksNote") String ksNote) throws Exception {
    	return complaintService.disposeFlag(ksNote);
	}
    
    /**
     * 删除处理数据
     */
    @DeleteMapping("deleteMx/{ksNote}")
    public Result deleteMx(@PathVariable("ksNote") String ksNote) throws Exception {
    	return complaintService.deleteMx(ksNote);
	}
    
    /**
     * 结案客诉
     */
    @PutMapping("closeout")
    public Result closeout(@RequestBody Complaint complaint) {
    	return complaintService.closeout(complaint);
    }
    
    /**
     * 获取单条数据 
     */
    @GetMapping("getOne/{ksNote}")
    public Result getOne(@PathVariable("ksNote") String ksNote) {
    	return Result.resultOk(complaintService.getOne(ksNote));
    }
    
    /**
     * 查询合同数据
     */
    @PostMapping("htCode")
    public Result htCode(BaseDto dto) throws IOException {
    	return complaintService.getHtCode(dto).getResult();
    }
    
    /**
     * 查询品号数据
     */
    @PostMapping("productSelection.html")
	public Result getProductSelectionList(BaseDto dto) throws IOException {
		return Result.formPage(complaintService.getProductSelectionList(dto));
	}
    
    /**
     * 查询产品数据
     */
    @GetMapping("getCpcode/{ht_code}")
   	public List<Map> getCpcode(@PathVariable("ht_code") String ht_code) throws IOException {
   		return complaintService.getCpcode(ht_code);
   	}
    
    /**
     * 查询单位名称
     */
    @GetMapping("getDw/{ksDw}")
    public Result getDw(@PathVariable("ksDw") String ksDw) {
		return Result.resultOk(complaintService.getDw(ksDw));
    }
    
    /**
     * 查询人员名称
     */
    @GetMapping("getMan/{slMan}")
    public Result getMan(@PathVariable("slMan") String slMan) {
		return Result.resultOk(complaintService.getMan(slMan));
    }
    
}
