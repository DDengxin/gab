package com.tengzhi.base.thymeleaf.tag.tz.process;

import com.tengzhi.base.thymeleaf.tag.tz.TzProcessorDialect;
import org.springframework.util.StringUtils;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.context.WebEngineContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.AttributeValueQuotes;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 标签处理器(setting)
 *
 * @author lgl
 * @createDate
 */
public class DataGridProcess extends AbstractAttributeTagProcessor {
    public DataGridProcess(String dialectPrefix) {
        super(
                TemplateMode.HTML,
                dialectPrefix,
                null,
                false,
                TzProcessorDialect.DATAGRID,
                true,
                TzProcessorDialect.DEFAULT_PRECEDENCE,
                true);
    }


    /**
     * tz:datagrid
     *
     * @param context          模板页上下文
     * @param tag              待处理标签
     * @param attributeName    属性名 text
     * @param attributeValue   属性值 {url:abc}
     * @param structureHandler 元素标签结构处理器
     */
    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
                             String attributeValue, IElementTagStructureHandler structureHandler) {

        final IEngineConfiguration configuration = context.getConfiguration();
        final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
        final IStandardExpression expression = parser.parseExpression(context, attributeValue);
        //计算转换后的值
        //final String value = (String) expression.execute(context);

        switch (attributeValue.toLowerCase()) {
            case "datagrid":
            case "treegrid": {
                String id = tag.getAttributeValue("id");
                String idField = tag.getAttributeValue("idField");
                Map<String, String> attributes = new HashMap<String, String>(11) {{
                    put("class", String.format("mini-%s", attributeValue.toLowerCase()));
                    put("style", "width:100%;height:100%;");
                    put("defaultColumnWidth", "150");
                    //排序字段默认为主键字段
                    put("sortField", idField);
                    //默认允许排序
                    put("allowSort", "true");
                    //分页配置
                    put("showPager", "true");
                    put("showPageInfo", "false");
                    put("data-pagebuttonbar", "true");
                    //ajax配置
                    put("ajaxType", "post");
                    //默认居中
                    put("align", "center");
                    put("showColumnsMenu", "true");

                }};
                setAttributes(tag, structureHandler, attributes);
            }
            break;
            case "column": {
                String field = tag.getAttributeValue("field");
                Map<String, String> attributes = new HashMap<String, String>(6) {{
                    put("displayField", field);
                    put("sortField", field);
                    put("headerAlign", "center");
                    put("align", "left");
                    put("allowSort", "true");
                }};
                setAttributes(tag, structureHandler, attributes);
            }
            break;
            case "indexcolumn":
            case "checkcolumn":
            case "checkboxcolumn":
            case "comboboxcolumn":
            case "treeselectcolumn": {
                Map<String, String> attributes = new HashMap<String, String>(2) {{
                    put("type", attributeValue.toLowerCase());
                    put("headerAlign", "center");
                }};
                if ("indexcolumn".equalsIgnoreCase(attributeValue)) {
                    attributes.put("width", "30px");
                }
                setAttributes(tag, structureHandler, attributes);
            }
            break;
            default:
                break;
        }
    }

    /**
     * 批量赋值节点属性(不会覆盖显示定义)
     *
     * @param tag              节点
     * @param structureHandler 元素标签结构处理器
     * @param map              属性键值对
     */
    private void setAttributes(IProcessableElementTag tag, IElementTagStructureHandler structureHandler, Map<String, String> map) {
        for (String key : map.keySet()) {
            if (!tag.hasAttribute(key) && !StringUtils.isEmpty(map.get(key))) {
                structureHandler.setAttribute(key, map.get(key));
            }
        }
    }
}