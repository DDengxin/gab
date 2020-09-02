package com.tengzhi.business.system.sms.service.impl;

import cn.hutool.core.lang.Validator;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.tengzhi.base.property.Property;
import com.tengzhi.base.tools.log.Log;
import com.tengzhi.business.system.sms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class SmsServiceImpl implements SmsService {

    private static Property property;
    @Autowired
    private Property private_property;


    private static String api = null;
    private static String user = null;
    private static String password = null;
    private static String productid = null;
    private static String signature = null;
    /**
     * 通用错误码与中文描述映射关系
     */
    public static final Map<String, String> ERROR_CODE;

    static {
        Map<String, String> map = new HashMap<>(64);
        map.put("200", "提交成功");
        map.put("4001", "用户名错误");
        map.put("4002", "密码不能为空");
        map.put("4004", "手机号码错误");
        map.put("4006", "IP鉴权错误");
        map.put("4007", "用户禁用");
        map.put("4008", "tKey错误");
        map.put("4009", "密码错误");
        map.put("4013", "定时时间错误");
        map.put("4014", "模板错误");
        map.put("4015", "扩展号错误");
        map.put("4019", "用户类型错误");
        map.put("4023", "签名错误");
        map.put("4025", "模板变量内容为空");
        map.put("4026", "手机号码数最大2000个");
        map.put("4027", "模板变量内容最大200组");
        map.put("4029", "请使用post请求");
        map.put("4030", "请使用application/json");
        map.put("9998", "JSON解析错误");
        map.put("9999", "非法请求");
        ERROR_CODE = Collections.unmodifiableMap(map);
    }


    /**
     * 获取短信签名
     *
     * @return
     */
    public static String getSignature() {
        return signature;
    }

    @PostConstruct
    private void init() {
        property = private_property;
        //初始化短信配置
        try {
            if (null == property.getSms()) {
                throw new Exception("获取短信配置文件失效,请检查配置;");
            }
            api = property.getSms().getApi();
            user = property.getSms().getUser();
            password = property.getSms().getPasssword();
            productid = property.getSms().getProductid();
            signature = property.getSms().getSignature();
        } catch (Exception e) {
            e.printStackTrace();
            Log.error(e);
        }
    }

    /**
     * 发送短信
     *
     * @param mobile  手机号码
     * @param content 内容
     * @throws Exception 异常
     */
    @Override
    public String send(String mobile, String content) throws Exception {
        if (!Validator.isMobile(mobile)) {
            throw new Exception("手机号码格式不正确!");
        } else {
            JSONObject paramJson = new JSONObject();
            //东八区时间戳
            long tKey = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli() / 1000;
            //加密密文
            String pwd = SecureUtil.md5(SecureUtil.md5(password) + tKey);

            paramJson.putOnce("username", user);
            paramJson.putOnce("password", pwd);
            paramJson.putOnce("tKey", tKey);
            paramJson.putOnce("mobile", mobile);
            paramJson.putOnce("content", content);
            //模板ID
            paramJson.putOnce("tpId", productid);
            //发送时间。若为空，立即发送； 若不为空，设 置定时字符串日期，定时时间必须大于当前时间 10 分钟，格式：yyyy-MM-ddHH:mm:ss
            //paramMap.putOnce("time",null);
            //扩展号
            paramJson.putOnce("ext", "");
            //用户自定义信息
            paramJson.putOnce("extend", "");
            String result = HttpRequest.post(api)
                    .timeout(60000)
                    .body(paramJson.toJSONString(0), MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .execute()
                    .body();
            JSONObject json = JSONUtil.parseObj(result);
            if ("200".equals(json.getStr("code"))) {
                return result;
            } else {
                json.putOpt("describe", ERROR_CODE.get(json.getStr("code")));
                throw new Exception(json.toJSONString(0));
            }
        }
    }

}