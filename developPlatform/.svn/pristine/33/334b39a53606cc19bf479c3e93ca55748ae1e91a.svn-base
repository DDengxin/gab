package com.tengzhi.base.tools.http.reponse;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: gaodu
 * @date: 2020/8/24 13:03
 **/
public class HttpResponseUtil {
    /**
     * 直接返回json
     * @param response
     * @param responseObject
     */
    public static void writeJson(HttpServletResponse response,
                                         Object responseObject) {
        //将实体对象转换为JSON Object转换
        JSONObject responseJSONObject = JSONUtil.parseObj(responseObject);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(responseJSONObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
