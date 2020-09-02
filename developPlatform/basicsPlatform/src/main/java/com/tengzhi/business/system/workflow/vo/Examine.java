package com.tengzhi.business.system.workflow.vo;

import com.tengzhi.base.tools.json.MapperFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Examine {
	private String backlogId;
	private String opinion;
	private String businessId;
	private String menuId;
	private String title;
	private String url;
	private String processId;
	private String signature;
	/**
	 * 扩展参数
	 */
	private String extension;


	public String getBacklogId() {
		return backlogId;
	}

	public void setBacklogId(String backlogId) {
		this.backlogId = backlogId;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * 获取扩展参数
	 * @return
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * 设置扩展参数
	 * @param extension
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}
	/**
	 * params字符串转JSON对象
	 *
	 * @return
	 * @throws IOException
	 */
	public Map<String, String> getExtendsionMap() {
		if (StringUtils.isEmpty(getExtension())) {
			return new HashMap<String, String>(0);
		} else {
			try{
				return MapperFactory.getInstance().Str2Map(getExtension());
			}catch(IOException e){
				//该处转抛出RuntimeException，因为传入的字符串不可控，为运行时异常
				throw new RuntimeException(e.getMessage(),e.getCause());
			}
		}
	}
}
