package com.tengzhi.business.xt.telbook.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.xt.telbook.service.impl.TelBookServiceImpl;

@RestController
@RequestMapping("/xt/telbook")
public class TelBookController {
    
    @Autowired
    private TelBookServiceImpl telBookServiceImpl;

    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

        /**
         * 查询数据列表
         *
         * @return
         */
        @PostMapping(value = "telbook.html")
        public Result getList(BaseDto baseDto) throws IOException {
        	return telBookServiceImpl.findAll(baseDto).getResult();
        }
    	 
    	/* 部门下拉框 */
    	 
    	 @GetMapping("selectDept")
    		public List<Map<Object,String>>  getRequestMethod(){
    			return telBookServiceImpl.selectDept();
    		}
    	 
    	/* 学历下拉框 */
    	 @GetMapping("selectXl")
    	    public List<SelectVo> selectXl(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText){
    		    return telBookServiceImpl.getXl(trueText, falseText);
    	 }
    	 
    	 /* 员工类型下拉框 */
    	 @GetMapping("selectYglx")
    	    public List<SelectVo> selectYglx(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText){
    		    return telBookServiceImpl.getYglx(trueText, falseText);
    	 }
    	 
    	 /* 工作岗位下拉框 */
    	 @GetMapping("selectGzgw")
    	    public List<SelectVo> selectGzgw(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText){
    		    return telBookServiceImpl.getGzgw(trueText, falseText);
    	 }
    	 
    	 
    	 /* 工作职务下拉框 */
    	 @GetMapping("selectGzzw")
    	    public List<SelectVo> selectGzzw(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText){
    		    return telBookServiceImpl.getGzzw(trueText, falseText);
    	 }
    	 
    	 /* 工作保险下拉框 */
    	 @GetMapping("selectGzbx")
    	    public List<SelectVo> selectGzbx(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText){
    		    return telBookServiceImpl.getGzbx(trueText, falseText);
    	 }



    	 /* 根据部门获取人员下拉框 */
        @GetMapping("/getDeptWorker/{deptId}")
        public List<SelectVo> getDeptWorker(@PathVariable String deptId ) {
            return telBookServiceImpl.getDeptWorker(deptId);
        }
      
    }
