package com.tengzhi.business.system.setting.service;

import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.setting.model.SysSet;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface SysSetService {

	/**
	 * 通过账套获取配置
	 * (该函数会调用getCorpSetting)
	 * @param corp
	 * @return
	 */
	List<Map> getSetting(String corp);

	/**
	 * 通过账套获取配置(数据库查询,已做缓存)
	 * @param corp
	 * @return
	 */
	List<Map> getCorpSetting(String corp);

	ModelAndView modelAndView(ModelAndView mv);

	/**
	 * 获取下拉框的候选值
	 * @param content
	 * @return
	 */
	List<SelectVo> findComboboxParams(String content);

	/**
	 * 保存图片
	 * @param file 文件对象
	 * @param pid 文件ID
	 * @param req 请求
	 * @return
	 * @throws IOException
	 */
	Map<String, Object> saveImg(MultipartFile file, String pid, HttpServletRequest req) throws IOException;

	/**
	 * 保存数据
	 * @param data
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> save(String data) throws Exception;

	/**
	 * 获取当前账套配置
	 * @param sysSecode
	 * @return
	 */
	SysSet getSysset(String sysSecode);
}
