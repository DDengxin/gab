package com.tengzhi.IM.Dwr.config;

import org.directwebremoting.Container;
import org.directwebremoting.create.NewCreator;
import org.directwebremoting.extend.Configurator;
import org.directwebremoting.extend.CreatorManager;

/**
 * Dwr自定义配置
 * 配置javascript操作对象与java类的映射关系
 */
public class DwrXml implements Configurator {

	@Override
	public void configure(Container container) {
		CreatorManager creatorManager = container.getBean(CreatorManager.class);
		NewCreator creator = new NewCreator();
        creator.setClass("com.tengzhi.IM.Dwr.controller.Dwrmsg");
        creator.setJavascript("Dwrmsg");
		creatorManager.addCreator(creator);
	}
}