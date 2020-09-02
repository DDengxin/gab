package com.tengzhi.business.sc.pd.htps.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.sale.saleProduct.saleContract.vo.EXsContractVo;
import com.tengzhi.business.sc.pd.htps.service.HtpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("/sc/pd/htps")
public class HtpsController {
	
	@Autowired
	private HtpsService ddhfService;
	
	@GetMapping(value="/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}
	
	
	/**
	 * 	查询数据列表
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	@PostMapping(value = "/ddhflist.html")
	public Result getSrchTopList(BaseDto baseDto) throws IOException {
		return ddhfService.findAll(baseDto).getResult();
	}
	
	
	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchBottomList")
	public Result getSrchBottomList(BaseDto baseDto) throws IOException {
		return Result.resultOk(ddhfService.getSrchBottomList(baseDto));
	}
	
    
    @GetMapping(value = "/getByNote/{htNO}")
    public Result getById(@PathVariable(value = "htNO") String htNO) {
        return Result.resultOk(ddhfService.findByNote(htNO));
    }
   
    
   
 
    @PutMapping(value = "/getFlag/{htNo}/{flag}")
	public Result getFlag(@PathVariable(value = "htNo") String htNo,@PathVariable(value = "flag") String flag) throws Exception {
		return ddhfService.getFlag(htNo,flag);
	}
    
  
   
    
    /**
     *  核销
     * @param htNo
     * @return
     * @throws Exception
     */
    @PutMapping(value = "/confirm/{htNo}/{htMo}/{htJq}")
    public Result confirm(@PathVariable(value = "htNo") String htNo,@PathVariable(value = "htMo") String htMo,@PathVariable(value = "htJq") String htJq) throws Exception {
        return ddhfService.confirm(htNo,htMo,htJq);
    }
    
    @PutMapping(value = "/cancel/{htNo}")
    public Result cancel(@PathVariable(value = "htNo") String htNo) throws Exception {
        return ddhfService.cancel(htNo);
    }
    
    @PostMapping(value = "reply")
    public Result add(@RequestBody EXsContractVo vo) throws Exception {
        return ddhfService.reply(EXsContractVo.initEXsContractVo(vo));
    }
	@PutMapping(value = "reply")
	public Result replay(@RequestBody EXsContractVo vo) throws Exception {
		return ddhfService.reply(EXsContractVo.initEXsContractVo(vo));
	}
 
}
