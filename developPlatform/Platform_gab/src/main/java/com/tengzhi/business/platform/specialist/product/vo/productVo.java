package com.tengzhi.business.platform.specialist.product.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tengzhi.base.tools.json.MapperFactory;
import com.tengzhi.business.platform.specialist.product.model.Jscpcode;

import java.io.IOException;
import java.util.Map;

/**
 * @author 鱼游浅水
 * @create 2020-07-14
 */
public class productVo {

    private Jscpcode jscpcode;
    private Map<String,Object> configForm;

    public Jscpcode getJscpcode() {
        return jscpcode;
    }

    public void setJscpcode(String jscpcode) {
        JSONObject needJson = JSONObject.parseObject(jscpcode);
        Jscpcode g_NeedObject = JSON.toJavaObject(needJson,Jscpcode.class);
        this.jscpcode = g_NeedObject;
    }

    public Map<String, Object> getConfigForm() {
        return configForm;
    }

    public void setConfigForm(String configForm) throws IOException {
        Map<String, Object> map = (Map<String, Object>) MapperFactory.getInstance().Str2Map(configForm);
        this.configForm = map;
    }

}
