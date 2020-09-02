package com.tengzhi.base.tools.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.tengzhi.base.tools.time.DateFormatUtil;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author  lgl
 * JSON字符串、Bean处理
 */
public class MapperFactory {
    private MapperFactory() {
    }

    /**
     * 获取ObjectMapper对象
     *
     * @return
     */
    public static ObjectMapper getObjectMapper() {
        return MapperFactoryInstance.objectMapper;
    }

    /**
     * 获取MapperFactory实例
     *
     * @return
     */
    public static MapperFactory getInstance() {
        return MapperFactoryInstance.instance;
    }

    /**
     * 字符串转Bean
     *
     * @param str
     * @param type
     * @param <T>
     * @return
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @throws IntrospectionException
     */
    public <T> T Str2Bean(String str, Class<T> type) throws IOException {
        return getObjectMapper().readValue(str, type);
    }

    /**
     * Map对象转实体类(由于存在强制类型转换，所以不建议使用)
     *
     * @param map
     * @param type
     * @param <T>
     * @return
     */
    public <T> T Map2Bean(Map map, Class<T> type) {
        return getObjectMapper().convertValue(map, type);
    }

    /**
     * Map数组对象转实体类数组(由于存在强制类型转换，所以不建议使用)
     *
     * @param mapList
     * @param type
     * @param <T>
     * @return
     */
    public <T> List<T> MapList2BeanList(List<Map> mapList, Class<T> type) {
       List<T> tList = new ArrayList<T>();
        for(Map map :mapList){
            if(null != map) {
                tList.add(Map2Bean(map, type));
            }else{
                tList.add(null);
            }
        }
        return tList;
    }

    /**
     * 字符串转Map对象
     *
     * @param str
     * @return
     * @throws IOException
     */
    public Map Str2Map(String str) throws IOException {
        return getObjectMapper().readValue(str, Map.class);
    }


    /**
     * 字符串转实体类数组
     *
     * @param str
     * @param typeReference
     * @param <T>
     * @return
     * @throws IOException
     * @exp:MapperFactory.getInstance().Str2BeanList(entrydata, new TypeReference<List<Bean>>() {})
     */
    public <T> List<T> Str2BeanList(String str, TypeReference<List<T>> typeReference) throws IOException {

        return getObjectMapper().readValue(str, typeReference);
    }

    /**
     * 实体类转Map对象
     *
     * @param bean
     * @return
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public Map Bean2Map(Object bean) {
        return getObjectMapper().convertValue(bean, Map.class);
    }

/********************************************自定义时间处理*********************************************************************/
    /**
     * 静态类初始化对象
     */
    private static class MapperFactoryInstance {
        private static final MapperFactory instance = new MapperFactory();
        private static final ObjectMapper objectMapper = new ObjectMapper();


        /**
         * 静态方法注册ObjectMapper
         */
        static {
            objectMapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
            objectMapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
            objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.AUTO_CLOSE_SOURCE, false);
            objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.IGNORE_UNDEFINED, true);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            //忽略未知属性
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
            objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
            objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
            objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            objectMapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);

            /**
             * 注册自定义时间处理
             */
            SimpleModule serializerModule = new SimpleModule("DateSerializer", PackageVersion.VERSION);
            //序列化
            serializerModule.addSerializer(Calendar.class, new CustomCalendarSerializer());
            serializerModule.addSerializer(Date.class, new CustomDateSerializer());
            //反序列化
            serializerModule.addDeserializer(Calendar.class, new CustomCalendarDeSerializer());
            serializerModule.addDeserializer(Date.class, new CustomDateDeSerializer());
            objectMapper.registerModule(serializerModule);
        }
    }
    /********************************************自定义时间处理*********************************************************************/


    /********************************************自定义时间处理*********************************************************************/
    static class CustomCalendarSerializer extends CalendarSerializer {
        @Override
        public void serialize(Calendar value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            if (value != null) {
                jgen.writeString(DateFormatUtil.CalendarToString(value, "yyyy-MM-dd HH:mm:ss"));
            } else {
                jgen.writeString("");
            }
        }
    }

    /**
     * 自定义反列化java.util.Calendar
     */
    static class CustomDateSerializer extends DateSerializer {
        @Override
        public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            if (value != null) {
                jgen.writeString(DateFormatUtil.DateToString(value, "yyyy-MM-dd HH:mm:ss"));
            } else {
                jgen.writeString("");
            }
        }
    }

    /**
     * 自定义反序列化java.util.Calendar
     */
    static class CustomCalendarDeSerializer extends DateDeserializers.CalendarDeserializer {
        @Override
        public Calendar deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            if (p != null) {
                return DateFormatUtil.parseCalendar(p.getText());
            } else {
                return super.deserialize(p, ctxt);
            }
        }
    }

    /**
     * 自定义反序列化java.util.Date
     */
    static class CustomDateDeSerializer extends DateDeserializers.DateDeserializer {
        @Override
        public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            if (p != null) {
                return DateFormatUtil.parseDate(p.getText());
            } else {
                return super.deserialize(p, ctxt);
            }

        }
    }

    /**********************************************END 自定义时间处理*******************************************************************/


   /* @SuppressWarnings({"unchecked", "rawtypes"})
    public Object convertStr2Bean(String str, Class type)
            throws IOException,
            IllegalAccessException, InstantiationException,
            InvocationTargetException, IntrospectionException,
            ClassNotFoundException, ParseException {
        Object o = null;
        Map<String, Object> map = (Map<String, Object>) getObjectMapper().readValue(str, Map.class);
        o = convertMap2Bean(type, map);
        return o;
    }

    @SuppressWarnings({"rawtypes"})
    public Object convertStr3Bean(String str, Class type)
            throws IOException,
            IllegalAccessException, InstantiationException,
            InvocationTargetException, IntrospectionException,
            ClassNotFoundException, ParseException {
        List<Object> o = new ArrayList<Object>();
        Map[] maplist = getObjectMapper().readValue(str, Map[].class);
        for (int i = 0; i < maplist.length; i++) {
            Object obj = convertMap2Bean(type, maplist[i]);
            o.add(obj);
        }
        return o;
    }

    @SuppressWarnings("rawtypes")
    public Object convertMap2Bean(Class type, Map map)
            throws IntrospectionException, IllegalAccessException,
            InstantiationException, InvocationTargetException,
            ClassNotFoundException, ParseException {
        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
        Object obj = type.newInstance(); // 创建 JavaBean 对象

        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors = beanInfo
                .getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();

            if (map.containsKey(propertyName)) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                Object value = map.get(propertyName);
                Object args = new Object[1];
                if (value != null) {
                    if (value.toString().length() > 0) {
                        args = value;
                    } else {
                        if (descriptor.getPropertyType() == String.class) {
                            args = "";
                        } else {
                            args = null;
                        }
                    }

                } else {
                    args = null;
                }
                if (args != null) {
                    if ((descriptor.getPropertyType() == long.class)
                            || (descriptor.getPropertyType() == Long.class)) {
                        descriptor.getWriteMethod().invoke(obj,
                                Long.parseLong(args.toString()));
                    } else if ((descriptor.getPropertyType() == int.class)
                            || (descriptor.getPropertyType() == Integer.class)) {
                        descriptor.getWriteMethod().invoke(obj,
                                Integer.parseInt(args.toString()));
                    } else if ((descriptor.getPropertyType() == double.class)
                            || (descriptor.getPropertyType() == Double.class)) {
                        descriptor.getWriteMethod().invoke(obj,
                                Double.parseDouble(args.toString()));
                    } else if ((descriptor.getPropertyType() == boolean.class)
                            || (descriptor.getPropertyType() == Boolean.class)) {
                        descriptor.getWriteMethod().invoke(obj,
                                Boolean.parseBoolean(args.toString()));
                    } else if (descriptor.getPropertyType() == String.class) {
                        if ((args.toString().length() - args.toString().replace("'", "").length()) % 2 == 0) {
                            descriptor.getWriteMethod().invoke(obj, args.toString());
                        } else {
                            descriptor.getWriteMethod().invoke(obj, args.toString().replace("'", "’"));
                        }
                    } else if (descriptor.getPropertyType() == Date.class) {
                        SimpleDateFormat sdf = new SimpleDateFormat(
                                "yyyy-MM-dd");// 小写的mm表示的是分钟
                        descriptor.getWriteMethod().invoke(obj,
                                sdf.parse(args.toString()));
                    }
                } else {
                    descriptor.getWriteMethod().invoke(obj, args);
                }
            }
        }
        return obj;
    }*/
}
