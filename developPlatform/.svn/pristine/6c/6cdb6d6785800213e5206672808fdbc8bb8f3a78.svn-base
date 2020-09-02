package com.tengzhi.publicmethod;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * 属性文件读取类
 *
 * @author lqy
 */
public class PropertiesUtil {
    static Logger logger = Logger.getLogger(PropertiesUtil.class);

    //禁止实例化
    private PropertiesUtil() {
    }

    private static Properties props;
    private static String system_encode = "UTF-8";

    //通过静态代码块默认先加载属性文件，通过字节码加载器获取不要用class获取
    static {
        system_encode = System.getProperties().getProperty("file.encoding");

        String path = PropertiesUtil.class.getClassLoader().getResource("").getPath();
        path = path.substring(1, path.indexOf("classes")) +  "configuration.properties";

        props = new Properties();
        try {
            FileInputStream fis = new FileInputStream(URLDecoder.decode(path, "UTF-8"));
            BufferedInputStream bis = new BufferedInputStream(fis);
            BufferedReader reader = new BufferedReader (new InputStreamReader(bis));
            props.load(reader);
        } catch (IOException e) {
            //正常用日志类记录
            logger.error("配置文件读取异常:" + path + ":\r\n" + e.getMessage());
        }
    }

    /**
     * 获取属性文件中主键
     *
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        return getProperty(key, null);
    }

    /**
     * @param key          获取属性文件中主键
     * @param defaultValue 如果属性文件没有的默认值
     * @return string
     */
    public static String getProperty(String key, String defaultValue) {
        String value = props.getProperty(key.trim());
        //编码处理
        String encode = getEncoding(value);
        if (null != encode && !system_encode.equals(encode)) {
            try {
                value = new String(value.getBytes(encode), system_encode);
            } catch (Exception e) {
            }
        }
        //默认值处理
        if (StringUtils.isBlank(value)) {
            value = defaultValue;
        }
        if (null != value) {
            return value.trim();
        } else {
            return value;
        }
    }

    /**
     * 通过常用编码格式猜测文字编码
     *
     * @param str
     * @return
     */
    public static String getEncoding(String str) {
        String[] encode_list = new String[]{
                "ISO-8859-1", "GB2312", "UTF-8", "GBK"
        };
        for (int i = 0; i < encode_list.length; i += 1) {
            String encode = encode_list[i];
            try {
                if (str.equals(new String(str.getBytes(encode), encode))) {
                    String s = encode;
                    return s;
                }
            } catch (Exception exception) {
            }
        }
        return null;
    }
}