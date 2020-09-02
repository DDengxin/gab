package com.tengzhi.business.platform.enroll.controller;
import java.io.IOException;

import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.platform.enroll.model.GUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.annotations.Anonymous;
import com.tengzhi.business.platform.enroll.service.ExpertService;
import com.tengzhi.business.platform.enroll.service.PersonalService;
import com.tengzhi.business.platform.enroll.vo.modelVo;
import com.tengzhi.business.system.workflow.vo.Examine;

@RestController
@RequestMapping("platform/personal")
public class PersonalControler {

	@Autowired
	private PersonalService personalService;
	
	@GetMapping("/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}

	@PostMapping(value = "list")
	public Result getList(BaseDto dto) {
		return personalService.getList(dto).getResult();
	}

	/**
	 * 根据id查询
	 * @throws Exception 
	 * */
	 @GetMapping(value = "personById/{personNote}") 
	public Result getById(@PathVariable String personNote) throws Exception {
		return Result.resultOk(personalService.personById(personNote));
	}

	
	

    
    /**
     * 通过id获取对象
     **/
    @GetMapping(value = "personalone/{note}")
    public Result personalone(@PathVariable String note) {
        return Result.resultOk(personalService.personalone(note));
    }
    
	@PutMapping(value = "personedit") 
	public Result modify(@RequestBody modelVo vo) throws Exception {
    	try{

			return personalService.update(vo);
		}catch (RuntimeException  e){
			return Result.resultError(500,"服务器响应错误");
		}

	}

	@PostMapping(value = "gab/authPersonSave")
	public Result authPersonSave(@RequestBody modelVo vo) {
		GUserInfo gUserInfo = vo.getgUserInfo();
		SessionUser sessionUser = SessionUser.SessionUser();
		GUserInfo model =    sessionUser.getgUserInfo();
		gUserInfo.setUserId(model.getUserId());
		gUserInfo.setUserPwd(model.getUserPwd());
		vo.setgUserInfo(gUserInfo);
		personalService.update(vo);
		return Result.resultOkMsg("提交成功");
	}
    
    @PutMapping(value = "/agree")
	public Result agree(@RequestBody Examine examine ) throws Exception {
		return personalService.agree(examine);
	}
    /**
     *平台用户新增客户
     * @param vo
     * @return
     * @throws Exception
     */
    @PostMapping(value = "add")
    public Result add( @RequestBody modelVo vo) throws Exception { 
    	Result  rs =personalService.save(vo);
        return rs;
    }
    
    @DeleteMapping(value = "/deleteByPerson/{personNote}")
    public Result delete(@PathVariable(value = "personNote") String personNote) {
    	personalService.deleteByPersonNote(personNote);
        return Result.resultOk();
    }

    
    @GetMapping(value = "/getPersonalStatus/{userId}/{status}")
    public Result getExpetStatus(@PathVariable(value = "userId") String userId,@PathVariable(value = "status") String status) {
    	
    	return personalService.getPersonalStatus(userId,status);
        
    } 
    
}
