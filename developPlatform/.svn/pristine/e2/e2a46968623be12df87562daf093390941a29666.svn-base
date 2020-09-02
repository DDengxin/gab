package com.tengzhi.base.tools.excel;

import java.lang.annotation.*;

/**
 * excel注解
 * 
 * @author lqy
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Excel {
	/**
	 * 导出到Excel中的名字.
	 */
    String name();

	/**
	 * 设置版本区分同一个类多个模板
	 */
    String version() default "";


	/**
	 * 是否导出
	 */
    boolean show() default true;

	/**
	 * 宽度
	 */
    int width() default 0;

	/**
	 * 备用name，万一重名或其他原因
	 */
    String[]  spareName() default {};
}
