package com.tengzhi.base.thymeleaf.tag.tz;

import com.tengzhi.base.thymeleaf.tag.tz.process.DataGridProcess;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.standard.processor.StandardXmlNsTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.HashSet;
import java.util.Set;


/**
 * 系统方言
 *
 * @author lgl
 * @createDate
 */
public class TzProcessorDialect extends AbstractProcessorDialect {
    //定义方言名称
    private static final String DIALECT_NAME = "Tzsoft";

    //定义方言前缀
    private final static String DIALECT_PREFIX = "tz";
    //默认优先级
    public final static int DEFAULT_PRECEDENCE=1000;

    public final static String DATAGRID="datagrid";
    public final static String LABEL="datagrid";


    public TzProcessorDialect() {
        //设置自定义方言与"方言处理器"优先级相同
        super(DIALECT_NAME, DIALECT_PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
    }

    /**
     * 元素处理器材
     *
     * @param dialectPrefix
     * @return
     */
    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        Set<IProcessor> processors = new HashSet<IProcessor>();

        processors.add(new DataGridProcess(dialectPrefix));

        processors.add(new StandardXmlNsTagProcessor(TemplateMode.HTML, dialectPrefix));
        return processors;
    }

}