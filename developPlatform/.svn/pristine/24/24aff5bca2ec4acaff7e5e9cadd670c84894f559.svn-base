package com.tengzhi.base.tools.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Id;

/**
 * @author lqy 反射相关工具类 未完善
 *
 */
public class ReflectHelper {

    /**
     * 获取实体类的字段信息
     *
     * @param clazz 实体类
     * @return 字段集合
     */
    public static Field getIdField(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Field item = null;
        for (Field field : fields) {
            // 获取实体类中标识@Id的字段
            Id id = field.getAnnotation(Id.class);
            if (id != null) {
                field.setAccessible(true);
                item = field;
                break;
            }
        }
        if (item == null) {
            Class<?> superclass = clazz.getSuperclass();
            if (superclass != null) {
                item = getIdField(superclass);
            }
        }
        return item;
    }

    /**
     * 根据属性，拿到set方法，并把值set到对象中
     *
     * @param obj   目标对象
     * @param field 字段
     * @param value 值
     * @throws Exception
     */
    private void Setfield(Object obj, String field, String value) throws Exception {
        setValue(obj, obj.getClass(), field, obj.getClass().getDeclaredField(field).getType(), value);
    }

    /**
     * 根据属性，拿到set方法，并把值set到对象中
     *
     * @param obj       目标对象
     * @param clazz     对象的class
     * @param filedName 需要设置的属性
     * @param typeClass 属性类型
     * @param value     属性值
     * @throws Exception
     */
    public static void setValue(Object obj, Class<?> clazz, String filedName, Class<?> typeClass, Object value) throws Exception {
        String methodName = "set" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
        Method method = clazz.getDeclaredMethod(methodName, typeClass);
        method.invoke(obj, getClassTypeValue(typeClass, value));

    }

    /**
     * 通过class类型获取获取对应类型的值
     *
     * @param typeClass class类型
     * @param value     值
     * @return Object
     * @throws Exception
     */
    private static Object getClassTypeValue(Class<?> typeClass, Object value) throws Exception {
        if (typeClass == int.class || typeClass == Integer.class) {
            if (null == value) {
                return 0;
            }
            return Integer.parseInt((String) value);
        } else if (typeClass == short.class) {
            if (null == value) {
                return 0;
            }
            return Short.parseShort((String) value);
        } else if (typeClass == byte.class) {
            if (null == value) {
                return 0;
            }
            return value;
        } else if (typeClass == double.class || typeClass == Double.class) {
            if (null == value) {
                return 0;
            }
            return Double.parseDouble((String) value);
        } else if (typeClass == long.class) {
            if (null == value) {
                return 0;
            }
            return Long.parseLong((String) value);
        } else if (typeClass == String.class) {
            if (null == value) {
                return "";
            }
            return value;
        } else if (typeClass == boolean.class) {
            if (null == value) {
                return true;
            }
            return value;
        } else if (typeClass == BigDecimal.class) {
            if (null == value) {
                return new BigDecimal(0);
            }
            return new BigDecimal(value + "");
        } else if (typeClass == Date.class) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                date = format.parse(value.toString());
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                date = getTime(value.toString());
            }
            return date;
        } else {
            return typeClass.cast(value);
        }
    }

    /**
     * 判断string类型格式化
     *
     * @param value
     * @return
     * @throws Exception
     */
    private static Date getTime(String value) throws Exception {
        Date date = null;
        if (value.indexOf("/") > 0) {
            if (value.length() == 10) {
                date = format("yyyy/MM/dd", value);
            } else {
                date = format("yyyy/MM/dd HH:mm:ss", value);
            }
        } else if (value.indexOf("-") > 0 && value.length() == 10) {
            if (value.length() == 10) {
                date = format("yyyy-MM-dd", value);
            } else {
                date = format("yyyy-MM-dd HH:mm:ss", value);
            }
        } else {
            throw new Exception("日期格式化错误");
        }
        return date;
    }

    /**
     * 日期格式化
     *
     * @param farmatstr
     * @param value
     * @return
     * @throws Exception
     */
    private static Date format(String farmatstr, String value) throws Exception {
        DateFormat format = new SimpleDateFormat(farmatstr);
        try {
            return format.parse(value);
        } catch (ParseException e) {
            throw new Exception("日期格式化错误");
		}
    }
}
