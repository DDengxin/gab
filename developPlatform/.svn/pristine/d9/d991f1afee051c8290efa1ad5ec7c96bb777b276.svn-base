package com.tengzhi.base.thymeleaf.config;

import com.tengzhi.base.thymeleaf.tag.tz.TzProcessorDialect;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Thymeleaf配置
 *
 * @author lgl
 * @createDate
 */
@Configuration
public class ThymeleafDialectConfig {
    /**
     * 配置自定义方言
     * @return
     */
    @Bean
    public TzProcessorDialect tzProcessorDialect() {
        return new TzProcessorDialect();
    }

    /**
     * 配置Layout方言
     * @return
     */
    @Bean
    public LayoutDialect layoutDialect(){
        return new LayoutDialect();
    }





    /**
     * 默认模板解析器
     */
    /*@Autowired
    @Qualifier("defaultTemplateResolver")
    ITemplateResolver defaultTemplateResolver;*/
    /**
     * 配置Spring thymeleaf layout引擎
     *
     * @return
     */
   /* @Bean(name = "templateEngine")
    public SpringTemplateEngine getTemplateEngine() {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        final Set<IDialect> dialects = new HashSet<IDialect>();
        //配置Spring thymeleaf layout布局方言(支持layout)
        dialects.add(new LayoutDialect());
        templateEngine.setAdditionalDialects(dialects);
        //配置默认模板解析器
        templateEngine.setTemplateResolver(defaultTemplateResolver);
        return templateEngine;
    }*/

}