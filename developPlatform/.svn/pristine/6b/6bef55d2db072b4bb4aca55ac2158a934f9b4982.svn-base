package com.tengzhi.business.finance.voucher.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.tengzhi.annotations.Log;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.cg.da.sysCustomer.model.SysCustomer;
import com.tengzhi.business.cg.yw.purchaseRequisition.vo.EcgRequisitiontVo;
import com.tengzhi.business.ck.yw.ckck.service.WarehouseOutService;
import com.tengzhi.business.finance.voucher.dao.CertificateWordDao;
import com.tengzhi.business.finance.voucher.model.CertificateWord;
import com.tengzhi.business.finance.voucher.service.CertificateWordService;
import com.tengzhi.business.system.params.model.SysParams;

@RestController
@RequestMapping("/finance/voucher/certificateWord")
public class CertificateWordController {
	@Autowired
	private CertificateWordService  certificateWordService	;
	
	

	@GetMapping("/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}
//	/**
//	 *
//	 *
//	 * @param paramType 类型(CP,YL,WL)
//	 * @param paramMod 模块
//	 * @param request
//	 * @param rt
//	 * @param mv
//	 * @return
//	 */
//	@GetMapping(value = {"/list.html"})
//	public ModelAndView toPage(@PathVariable String paramType, @PathVariable String paramMod,
//			HttpServletRequest request, RedirectAttributes rt,ModelAndView mv) {
//		String servletPath = request.getServletPath();
//
//		System.out.println(servletPath);
//	
//		return mv;
//	}

    /**
     * 查询数据列表
     * @return
     * @throws Exception 
     */
    @PostMapping(value = "/getSrchTopList")
    public Result getSrchTopList(BaseDto baseDto) throws Exception {
        return certificateWordService.getSrchTopList(baseDto).getResult();
    }
    
    


    
    
	/**
	 * 新增
	 * **/
	@PostMapping(value = "/addCertificateWord")
	public Result add(@RequestBody CertificateWord certificateWord) throws Exception{
		certificateWord = certificateWordService.save(certificateWord);
		 return Result.resultOk(certificateWord.getFgenerateid());
	}
    
	/**
	 * 修改
	 * @param certificateWord
	 * @return
	 * @throws Exception
	 */
    @PutMapping(value = "/addCertificateWord")
    public Result modify(@RequestBody CertificateWord certificateWord) throws Exception {
       certificateWordService.update(certificateWord);
       return null;
    }
	
	
}
