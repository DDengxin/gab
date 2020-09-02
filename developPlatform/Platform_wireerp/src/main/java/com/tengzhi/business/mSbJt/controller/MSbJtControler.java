package com.tengzhi.business.mSbJt.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

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
import com.tengzhi.business.cg.yw.purchaseContract.model.ECgContract;
import com.tengzhi.business.cg.yw.purchaseContract.service.impl.PurchaseContractServiceImpl;
import com.tengzhi.business.cg.yw.purchaseContract.vo.ECgContractVo;
import com.tengzhi.business.mSbJt.model.MSbJt;
import com.tengzhi.business.mSbJt.service.MSbJtService;
import com.tengzhi.business.mSbJt.service.impl.MSbJtServiceImpl;
import com.tengzhi.business.mSbJt.vo.MSbJtVo;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.right.vo.SysRightVo;

@RestController
@RequestMapping("mSbJt/sb")
public class MSbJtControler {

	@Autowired
	private MSbJtService mSbJtService;

	@GetMapping("/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}

	/**
	 * 查询topList数据
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchTopList")
	public Result getSrchTopList(BaseDto baseDto) throws IOException {
		return mSbJtService.getSrchTopList(baseDto).getResult();
	}

	/**
	 * 查询BottomList数据
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchBottomList")
	public Result getSrchBottomList(BaseDto baseDto) throws IOException {
		return mSbJtService.getSrchBottomList(baseDto).getResult();
	}
	
	/**
     * @description: Post新增Restful
     */
    @PostMapping(value = "add.html")
    public Result add(@RequestBody MSbJtVo vo) throws Exception {
    	MSbJt  mSbJt=mSbJtService.save(MSbJtVo.initMSbJtVo(vo));
        return Result.resultOk(mSbJt.getMachineId());
    }
    /**
            * 修改
     * @param vo
     * @return
     * @throws IOException
     * @throws Exception
     */
    @PutMapping(value = "add.html")
    public Result modify(@RequestBody MSbJtVo vo) throws IOException, Exception {
    	mSbJtService.update(MSbJtVo.initMSbJtVo(vo));
        return Result.resultOkMsg("修改成功");
    }
    
    @DeleteMapping(value = "delete/{machineId}")
    public Result delete(@PathVariable(value = "machineId") String machineId) throws Exception {
    	mSbJtService.deleteById(machineId);
        return Result.resultOk();
    }
    
    /**
     * @Param: [vo]
     * @description: Get(获取数据|通过ID获取对象)Restful
     */
    @GetMapping(value = "getById/{machineId}")
    public Result getById(@PathVariable(value = "machineId") String machineId) {
        return Result.resultOk(mSbJtService.findById2(machineId));
    }
    
    //下拉框
    @GetMapping("/getMachineDtype")
    public List<SelectVo> getMachineDtype(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText) {
        return mSbJtService.getMachineDtype(trueText, falseText);
    }
    @GetMapping("/getMachineXtype")
    public List<SelectVo> getMachineXtype(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText) {
    	return mSbJtService.getMachineXtype(trueText, falseText);
    }
    @GetMapping("/getMachineZtype")
    public List<SelectVo> getMachineZtype(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText) {
    	return mSbJtService.getMachineZtype(trueText, falseText);
    }
    @GetMapping("/getMachineProcess")
    public List<SelectVo> getMachineProcess(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText) {
    	return mSbJtService.getMachineProcess(trueText, falseText);
    }
    
    
	/**
	 * 车台下拉框 根据车间
	 * 
	 * @param mainID
	 * @return
	 */
	@GetMapping("/getJtList/{gxCj}")
	public List<SelectVo> getJtList(@PathVariable(name = "gxCj") String gxCj) {
		return mSbJtService.getJtList(gxCj);
	}
	
	/**
	 * 车台下拉框 根据工序
	 * 
	 * @param mainID
	 * @return
	 */
	@GetMapping("/getGxJtList/{gxId}")
	public List<SelectVo> getGxJtList(@PathVariable(name = "gxId") String gxId) {
		return mSbJtService.getGxJtList(gxId);
	}
	
	
	
	/**
	 * 车台下拉框全部车台
	 * 
	 * @param mainID
	 * @return
	 */
	@GetMapping("/getJtAllList")
	public List<SelectVo> getJtAllList() {
		return mSbJtService.getJtAllList();
	}
}
