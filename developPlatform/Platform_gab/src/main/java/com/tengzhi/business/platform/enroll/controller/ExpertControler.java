package com.tengzhi.business.platform.enroll.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.annotations.Anonymous;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.platform.enroll.model.GUserInfo;
import com.tengzhi.business.platform.enroll.service.ExpertService;
import com.tengzhi.business.platform.enroll.vo.modelVo;
import com.tengzhi.business.system.workflow.vo.Examine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("platform/expert")
public class ExpertControler {

	@Autowired
	private ExpertService expertService;

	@GetMapping("/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}


	@PostMapping(value = "list.html")
	public Result getList(BaseDto dto) {
		return expertService.getList(dto).getResult();
	}

	/**
	 * 根据id查询
	 *
	 * @throws Exception
	 */
	@GetMapping(value = "expertInfo/{expertNote}")
	public Result getById(@PathVariable(value = "expertNote") String expertNote) throws Exception {
		return Result.resultOk(expertService.findById(expertNote));
	}

	@PostMapping(value = "add")
	public Result add(@RequestBody modelVo vo) throws Exception {
		Result rs = expertService.Expertsave(vo);
		return rs;
	}


	@GetMapping(value = "/getExpetStatus/{userId}/{status}")
	public Result getExpetStatus(@PathVariable(value = "userId") String userId, @PathVariable(value = "status") String status) {

		return expertService.getExpetStatus(userId, status);

	}

	@DeleteMapping(value = "/deleteByExpertNote/{userId}")
	public Result delete(@PathVariable(value = "userId") String userId) {
		expertService.deleteByExpertNote(userId);
		return Result.resultOk();
	}


	@PostMapping(value = "expertedit")
	public Result modify(@RequestBody modelVo vo) throws Exception {
		expertService.update(vo);
		return Result.resultOkMsg("修改成功");
	}


	/**
	 * 通过id获取对象
	 **/
	@GetMapping(value = "expertalone/{note}")
	public Result expertalone(@PathVariable String note) {
		return Result.resultOk(expertService.expertalone(note));
	}

	/**
	 * 审批
	 *
	 * @param examine
	 * @return
	 * @throws Exception
	 */
	@PutMapping(value = "/agree")
	public Result agree(@RequestBody Examine examine) throws Exception {
		return expertService.agree(examine);
	}

	/**
	 * 获取专家的记录案列
	 *
	 * @param baseDto
	 * @return
	 */
	@Anonymous
	@PostMapping(value = "getExpertTech")
	public Result getExpertTech(BaseDto baseDto) {
		return expertService.getExpertTech(baseDto).getResult();
	}


	@PostMapping(value = "gab/authExpertSave")
	public Result authExpertSave(@RequestBody modelVo vo) {
		GUserInfo gUserInfo = vo.getgUserInfo();
		SessionUser sessionUser = SessionUser.SessionUser();
		if (sessionUser.getgUserInfo() == null) {
			return Result.error("该账户不是哥爱帮平台用户");
		} else {
			GUserInfo model = sessionUser.getgUserInfo();
			gUserInfo.setUserId(model.getUserId());
			gUserInfo.setUserPwd(model.getUserPwd());
			vo.setgUserInfo(gUserInfo);
			try {
				expertService.update(vo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return Result.resultOkMsg("提交成功");
		}
	}
}
