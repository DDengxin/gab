package com.tengzhi.base.jpa.extension;

import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;

/**
 * @param <T>
 * @author lqy 工具类
 */
public class HsqlUtils<T> {

    private Root<T> root;
    private CriteriaQuery<?> query;
    private CriteriaBuilder builder;

    private List<Predicate> predicates = new ArrayList<>();

    private static final String SEPARATOR = ".";

    public HsqlUtils(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        this.root = root;
        this.query = query;
        this.builder = builder;
    }

    /**
     * or条件拼接
     *
     * @param action
     * @return
     */
    public HsqlUtils<T> or(Consumer<HsqlUtils<T>> action) {
        return this.newWrapper(false, action);
    }

    /**
     * and条件拼接
     *
     * @param action
     * @return
     */
    public HsqlUtils<T> and(Consumer<HsqlUtils<T>> action) {
        return this.newWrapper(true, action);
    }

    /**
     * @param isConjunction 为true 是and拼接，false 为or拼接
     * @param action
     * @return
     */
    public HsqlUtils<T> newWrapper(boolean isConjunction, Consumer<HsqlUtils<T>> action) {
        HsqlUtils<T> specification = new HsqlUtils<>(root, query, builder);
        CriteriaBuilder newBuilder = specification.getBuilder();
        Predicate predicate = isConjunction ? newBuilder.conjunction() : newBuilder.disjunction();
        action.accept(specification);
        predicate.getExpressions().addAll(specification.getPredicates());
        predicates.add(predicate);
        return this;
    }

    /**
     * 空
     *
     * @param condition 条件为true时进行拼接
     * @param name
     * @return
     */
    public HsqlUtils<T> isNull(boolean condition, String name) {
        return condition ? this.isNull(name) : this;
    }

    /**
     * 空 默认如果传入值为空不拼接
     *
     * @param name
     * @return
     */
    public HsqlUtils<T> isNull(String name) {
        if (StringUtils.isEmpty(name)) {
            return this;
        } else {
            return handle(name, e -> this.isNull(e));
        }
    }

    /**
     * 空
     *
     * @param expression
     * @return
     */
    public HsqlUtils<T> isNull(Expression<?> expression) {
        predicates.add(builder.isNull(expression));
        return this;
    }

    /**
     * 非空
     *
     * @param condition 条件为true时进行拼接
     * @param name
     * @return
     */
    public HsqlUtils<T> isNotNull(boolean condition, String name) {
        return condition ? this.isNotNull(name) : this;
    }

    /**
     * 非空 默认如果传入值为空不拼接
     *
     * @param name
     * @return
     */
    public HsqlUtils<T> isNotNull(String name) {
        if (StringUtils.isEmpty(name)) {
            return this;
        } else {
            return handle(name, e -> this.isNotNull(e));
        }
    }

    /**
     * 非空
     *
     * @param x
     * @return
     */
    public HsqlUtils<T> isNotNull(Expression<?> x) {
        predicates.add(builder.isNotNull(x));
        return this;
    }

    /**
     * 等于
     *
     * @param condition
     * @param name
     * @param value
     * @return
     */
    public HsqlUtils<T> eq(boolean condition, String name, Object value) {
        return condition ? this.eq(name, value) : this;
    }

    /**
     * 等于 默认如果传入值为空不拼接
     *
     * @param name
     * @param value
     * @return
     */
    public HsqlUtils<T> eq(String name, Object value) {
        if (StringUtils.isEmpty(value)) {
            return this;
        } else {
            return handle(name, e -> this.eq(e, value));
        }
    }


    /**
     * 等于 默认如果传入值为空不拼接
     *
     * @param map
     * @param fields
     * @return
     * @exp:eq(map,new String[]{'fields1','fields2'})
     */
    public HsqlUtils<T> eq(Map<String, String> map, String[] fields) {
        if (null != map && null != fields) {
            for (int i = 0, max = fields.length; i < max; i += 1) {
                if (map.containsKey(fields[i])) {
                    eq(fields[i], map.get(fields[i]));
                }
            }
        }
        return this;
    }

    /**
     * 等于
     *
     * @param x
     * @param value
     * @return
     */
    public HsqlUtils<T> eq(Expression<?> x, Object value) {
        predicates.add(builder.equal(x, value));
        return this;
    }

    /**
     * 等于 Boolean专属
     * str转换Boolean
     *
     * @param name
     * @param value
     * @return
     */
    public HsqlUtils<T> eqbool(String name, Object value) {
        if(value instanceof  Boolean){
            return handle(name, e -> this.eq(e, value));
        }else if (StringUtils.isEmpty(value)) {
            return this;
        } else {
            return handle(name, e -> this.eq(e, Boolean.parseBoolean(value.toString())));
        }
    }

    /**
     * 不等于
     *
     * @param condition
     * @param name
     * @param value
     * @return
     */
    public HsqlUtils<T> ne(boolean condition, String name, Object value) {
        return condition ? this.ne(name, value) : this;
    }

    /**
     * 不等于 为空默认不添加
     *
     * @param name
     * @param value
     * @return
     */
    public HsqlUtils<T> ne(String name, Object value) {
        if (StringUtils.isEmpty(value)) {
            return this;
        } else {
            return handle(name, e -> this.ne(e, value));
        }
    }

    /**
     * 不等于 为空默认不添加
     *
     * @param map
     * @param fields
     * @return
     * @exp:ne(map,new String[]{'fields1','fields2'})
     */
    public HsqlUtils<T> ne(Map<String, String> map, String[] fields) {
        if (null != map && null != fields) {
            for (int i = 0, max = fields.length; i < max; i += 1) {
                if (map.containsKey(fields[i])) {
                    ne(fields[i], map.get(fields[i]));
                }
            }
        }
        return this;
    }

    /**
     * 不等于
     *
     * @param x
     * @param value
     * @return
     */
    public HsqlUtils<T> ne(Expression<?> x, Object value) {
        predicates.add(builder.notEqual(x, value));
        return this;
    }

    /**
     * like
     *
     * @param condition
     * @param name
     * @param value
     * @return
     */
    public HsqlUtils<T> like(boolean condition, String name, String value) {
        return condition ? this.like(name, value) : this;
    }

    /**
     * like 默认为空不拼接
     *
     * @param name
     * @param value
     * @return
     */
    public HsqlUtils<T> like(String name, String value) {
        if (StringUtils.isEmpty(value)) {
            return this;
        }
        return handle(name, e -> this.like(e, value));
    }

    /**
     * like 默认为空不凭借
     *
     * @param map
     * @param fields
     * @return
     * @exp:like(map,new String[]{'fields1','fields2'})
     */
    public HsqlUtils<T> like(Map<String, String> map, String[] fields) {
        if (null != map && null != fields) {
            for (int i = 0, max = fields.length; i < max; i += 1) {
                if (map.containsKey(fields[i])) {
                    like(fields[i], map.get(fields[i]));
                }
            }
        }
        return this;
    }

    /**
     * like
     *
     * @param path
     * @param value
     * @return
     */
    public HsqlUtils<T> like(Expression<String> path, String value) {
        predicates.add(builder.like(path, value));
        return this;
    }

    /**
     * like
     *
     * @param condition
     * @param name
     * @param value
     * @return
     */
    public HsqlUtils<T> startingWith(boolean condition, String name, String value) {
        return condition ? this.startingWith(name, value) : this;
    }

    /**
     * like% 默认为空不拼接
     *
     * @param name
     * @param value
     * @return
     */
    public HsqlUtils<T> startingWith(String name, String value) {
        if (StringUtils.isEmpty(value)) {
            return this;
        } else {
            this.like(name, value + "%");
            return this;
        }
    }

    /**
     * %like 默认为空不拼接
     *
     * @param map
     * @param fields
     * @return
     * @exp:startingWith(map,new String[]{'fields1','fields2'})
     */
    public HsqlUtils<T> startingWith(Map<String, String> map, String[] fields) {
        if (null != map && null != fields) {
            for (int i = 0, max = fields.length; i < max; i += 1) {
                if (map.containsKey(fields[i])) {
                    startingWith(fields[i], map.get(fields[i]));
                }
            }
        }
        return this;
    }

    /**
     * like%
     *
     * @param condition
     * @param name
     * @param value
     * @return
     */
    public HsqlUtils<T> endingWith(boolean condition, String name, String value) {
        return condition ? this.endingWith(name, value) : this;
    }


    /**
     * like% 默认为空不拼接
     *
     * @param name
     * @param value
     * @return
     */
    public HsqlUtils<T> endingWith(String name, String value) {
        if (StringUtils.isEmpty(value)) {
            return this;
        } else {
            this.like(name, "%" + value);
            return this;
        }
    }

    /**
     * like% 默认为空不拼接
     *
     * @param map
     * @param fields
     * @return
     * @exp:endingWith(map,new String[]{'fields1','fields2'})
     */
    public HsqlUtils<T> endingWith(Map<String, String> map, String[] fields) {
        if (null != map && null != fields) {
            for (int i = 0, max = fields.length; i < max; i += 1) {
                if (map.containsKey(fields[i])) {
                    endingWith(fields[i], map.get(fields[i]));
                }
            }
        }
        return this;
    }

    /**
     * %like%
     *
     * @param condition
     * @param name
     * @param value
     * @return
     */
    public HsqlUtils<T> contains(boolean condition, String name, String value) {
        return condition ? this.contains(name, value) : this;
    }

    /**
     * %like%默认为空不拼接
     *
     * @param name
     * @param value
     * @return
     */
    public HsqlUtils<T> contains(String name, String value) {
        if (StringUtils.isEmpty(value)) {
            return this;
        } else {
            this.like(name, "%" + value + "%");
            return this;
        }
    }

    /**
     * %like%默认为空不拼接
     *
     * @param map
     * @param fields
     * @return
     * @exp:contains(map,new String[]{'fields1','fields2'})
     */
    public HsqlUtils<T> contains(Map<String, String> map, String[] fields) {
        if (null != map && null != fields) {
            for (int i = 0, max = fields.length; i < max; i += 1) {
                if (map.containsKey(fields[i])) {
                    contains(fields[i], map.get(fields[i]));
                }
            }
        }
        return this;
    }

    /**
     * 大于等于
     *
     * @param <Y>
     * @param condition
     * @param name
     * @param value
     * @return
     */
    public <Y extends Comparable<? super Y>> HsqlUtils<T> ge(boolean condition, String name, Y value) {
        return condition ? this.ge(name, value) : this;
    }

    /**
     * 大于等于 默认为空不拼接
     *
     * @param <Y>
     * @param name
     * @param value
     * @return
     */
    public <Y extends Comparable<? super Y>> HsqlUtils<T> ge(String name, Y value) {
        if (StringUtils.isEmpty(value)) {
            return this;
        } else {
            return handle(name, e -> this.ge(e, value));
        }
    }

    /**
     * 大于等于
     *
     * @param <Y>
     * @param path
     * @param value
     * @return
     */
    public <Y extends Comparable<? super Y>> HsqlUtils<T> ge(Expression<? extends Y> path, Y value) {
        predicates.add(builder.greaterThanOrEqualTo(path, value));
        return this;
    }

    /**
     * 小于等于
     *
     * @param <Y>
     * @param condition
     * @param name
     * @param value
     * @return
     */
    public <Y extends Comparable<? super Y>> HsqlUtils<T> le(boolean condition, String name, Y value) {
        return condition ? this.le(name, value) : this;
    }

    /**
     * 小于等于 默认为空不拼接
     *
     * @param <Y>
     * @param name
     * @param value
     * @return
     */
    public <Y extends Comparable<? super Y>> HsqlUtils<T> le(String name, Y value) {
        if (StringUtils.isEmpty(value)) {
            return this;
        } else {
            return handle(name, e -> this.le(e, value));
        }
    }

    /**
     * 小于等于
     *
     * @param <Y>
     * @param path
     * @param value
     * @return
     */
    public <Y extends Comparable<? super Y>> HsqlUtils<T> le(Expression<? extends Y> path, Y value) {
        predicates.add(builder.lessThanOrEqualTo(path, value));
        return this;
    }

    /**
     * 大于
     *
     * @param <Y>
     * @param condition
     * @param name
     * @param value
     * @return
     */
    public <Y extends Comparable<? super Y>> HsqlUtils<T> gt(boolean condition, String name, Y value) {
        return condition ? this.gt(name, value) : this;
    }

    /**
     * 大于 默认为空不拼接
     *
     * @param <Y>
     * @param name
     * @param value
     * @return
     */
    public <Y extends Comparable<? super Y>> HsqlUtils<T> gt(String name, Y value) {
        if (StringUtils.isEmpty(value)) {
            return this;
        } else {
            return handle(name, e -> this.gt(e, value));
        }

    }

    /**
     * 大于
     *
     * @param <Y>
     * @param path
     * @param value
     * @return
     */
    public <Y extends Comparable<? super Y>> HsqlUtils<T> gt(Expression<? extends Y> path, Y value) {
        predicates.add(builder.greaterThan(path, value));
        return this;
    }

    /**
     * 小于
     *
     * @param <Y>
     * @param condition
     * @param name
     * @param value
     * @return
     */
    public <Y extends Comparable<? super Y>> HsqlUtils<T> lt(boolean condition, String name, Y value) {
        return condition ? this.lt(name, value) : this;
    }

    /**
     * 小yu 默认为空不拼接
     *
     * @param <Y>
     * @param name
     * @param value
     * @return
     */
    public <Y extends Comparable<? super Y>> HsqlUtils<T> lt(String name, Y value) {
        if (StringUtils.isEmpty(value)) {
            return this;
        } else {
            return handle(name, e -> this.lt(e, value));
        }
    }

    /**
     * 小于
     *
     * @param <Y>
     * @param path
     * @param value
     * @return
     */
    public <Y extends Comparable<? super Y>> HsqlUtils<T> lt(Expression<? extends Y> path, Y value) {
        predicates.add(builder.lessThan(path, value));
        return this;
    }

    /**
     * in
     *
     * @param condition
     * @param name
     * @param value
     * @return
     */
    public HsqlUtils<T> in(boolean condition, String name, Object... value) {
        return condition ? in(name, value) : this;
    }

    /**
     * in默认不拼接空
     *
     * @param name
     * @param value
     * @return
     */
    public HsqlUtils<T> in(String name, Object... value) {
        if (StringUtils.isEmpty(value)) {
            return this;
        } else {
            return handle(name, e -> this.in(e, value));
        }
    }

    /**
     * in
     *
     * @param condition
     * @param name
     * @param value
     * @return
     */
    public HsqlUtils<T> in(boolean condition, String name, Collection<?> value) {
        return condition ? in(name, value) : this;
    }

    /**
     * in 默认不拼接空
     *
     * @param name
     * @param value
     * @return
     */
    public HsqlUtils<T> in(String name, Collection<?> value) {
        if (StringUtils.isEmpty(value)) {
            return this;
        } else {
            return this.in(name, value.toArray());
        }
    }

    /**
     * in
     *
     * @param <U>
     * @param expression
     * @param value
     * @return
     */
    public <U> HsqlUtils<T> in(Expression<? extends U> expression, Object... value) {
        predicates.add(expression.in(value));
        return this;
    }

    /**
     * not in
     *
     * @param condition
     * @param name
     * @param value
     * @return
     */
    public HsqlUtils<T> notIn(boolean condition, String name, Collection<?> value) {
        return condition ? notIn(name, value) : this;
    }

    /**
     * not in 默认不拼接为空
     *
     * @param name
     * @param value
     * @return
     */
    public HsqlUtils<T> notIn(String name, Collection<?> value) {
        if (StringUtils.isEmpty(value)) {
            return this;
        } else {
            return handle(name, e -> this.notIn(e, value.toArray()));
        }
    }

    /**
     * not in
     *
     * @param condition
     * @param name
     * @param value
     * @return
     */
    public HsqlUtils<T> notIn(boolean condition, String name, Object... value) {
        return condition ? notIn(name, value) : this;
    }

    /**
     * not in  默认不拼接为空
     *
     * @param name
     * @param value
     * @return
     */
    public HsqlUtils<T> notIn(String name, Object... value) {
        if (StringUtils.isEmpty(value)) {
            return this;
        } else {
            return handle(name, e -> this.notIn(e, value));
        }
    }


    /**
     * 增加排序字段
     * @param orderField
     * @param direction
     * @return
     */
    public HsqlUtils<T> addOrderBy(String orderField, String direction) {
        if(null != Sort.Direction.valueOf(direction)){
            return addOrderBy(orderField, Sort.Direction.valueOf(direction));
        }
        return this;
    }

    /**
     * 增加排序字段
     * @param orderField
     * @param direction
     * @return
     */
    public HsqlUtils<T> addOrderBy(String orderField, Sort.Direction direction) {
        if (!StringUtils.isEmpty(orderField)) {
            if (Sort.Direction.ASC.equals(direction)) {
                getQuery().orderBy(getBuilder().asc(getRoot().get(orderField)));
            } else if (Sort.Direction.DESC.equals(direction)) {
                getQuery().orderBy(getBuilder().desc(getRoot().get(orderField)));
            }
        }
        return this;
    }

    /**
     * not in
     *
     * @param <U>
     * @param expression
     * @param value
     * @return
     */
    public <U> HsqlUtils<T> notIn(Expression<? extends U> expression, Object... value) {
        predicates.add(expression.in(value).not());
        return this;
    }

    /**
     * between
     *
     * @param <Y>
     * @param condition
     * @param name
     * @param start
     * @param end
     * @return
     */
    public <Y extends Comparable<? super Y>> HsqlUtils<T> between(boolean condition, String name, Y start, Y end) {
        return condition ? between(name, start, end) : this;
    }

    /**
     * between 默认不拼接为空
     *
     * @param <Y>
     * @param name
     * @param start
     * @param end
     * @return
     */
    public <Y extends Comparable<? super Y>> HsqlUtils<T> between(String name, Y start, Y end) {
        if (StringUtils.isEmpty(start) && StringUtils.isEmpty(end)) {
            return this;
        } else {
            return handle(name, e -> this.between(e, start, end));
        }
    }

    /**
    * @Param: [name, start, end]
    * @return: com.tengzhi.base.jpa.extension.HsqlUtils<T>
    * @Author: 鱼油浅水
    * @Date: 2020/6/22 15:20
    * @description: 创建满足我需求的时间判断
    */
    public <Date extends Comparable<? super Date>> HsqlUtils<T> between_common(String name, String start, String end){
        if (StringUtils.isEmpty(start) && StringUtils.isEmpty(end)) {
            return this;
        } else {
            HsqlUtils hsqlUtils=null;
            try {
                DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date begin = fmt.parse(start);
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(fmt.parse(end));
                calendar.add(Calendar.DATE,1);
                java.util.Date finish = calendar.getTime();
                hsqlUtils=handle(name, e -> this.between(e,begin,finish));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return hsqlUtils;
        }
    }

    /**
     * between
     *
     * @param <Y>
     * @param path
     * @param start
     * @param end
     * @return
     */
    public <Y extends Comparable<? super Y>> HsqlUtils<T> between(Expression<? extends Y> path, Y start, Y end) {
        predicates.add(builder.between(path, start, end));
        return this;
    }

    public <U> Join<T, U> leftJoin(String fieldName) {
        return root.join(fieldName, JoinType.LEFT);
    }

    public HsqlUtils<T> handle(String name, Consumer<Path> action) {
        Path<?> path;
        if (name.contains(SEPARATOR)) {
            String[] arr = name.split("\\" + SEPARATOR);
            path = this.leftJoin(arr[0]).get(arr[1]);
        } else {
            path = this.root.get(name);
        }
        action.accept(path);
        return this;
    }

    public Root<T> getRoot() {
        return root;
    }

    public void setRoot(Root<T> root) {
        this.root = root;
    }

    public CriteriaQuery<?> getQuery() {
        return query;
    }

    public void setQuery(CriteriaQuery<?> query) {
        this.query = query;
    }

    public CriteriaBuilder getBuilder() {
        return builder;
    }

    public void setBuilder(CriteriaBuilder builder) {
        this.builder = builder;
    }

    public List<Predicate> getPredicates() {
        return predicates;
    }

    public void setPredicates(List<Predicate> predicates) {
        this.predicates = predicates;
    }

    public static String getSeparator() {
        return SEPARATOR;
    }

    /**
     * 转移百分号()
     * //TODO:转义百分号(根据数据库类型)
     *
     * @return
     */
    private String encodePercent(String str) {
        return null;
    }


}
