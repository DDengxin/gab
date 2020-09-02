package com.tengzhi.base.jpa.dao.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLVariantRefExpr;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLUnionQuery;
import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.page.impl.BasePageImpl;
import com.tengzhi.base.tools.UUID.UUIDUtil;
import com.tengzhi.base.tools.beans.BeanUtils;
import com.tengzhi.base.tools.reflect.ReflectHelper;
import com.tengzhi.base.tools.reflect.SelfResultTransformer;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lqy
 */
public class BasedaoRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
        implements BasedaoRepository<T, ID> {

    /**
     * 持久化上下文
     */
    @PersistenceContext
    private EntityManager entityManager;
    private Class<T> entityClass;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public BasedaoRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.entityClass = entityInformation.getJavaType();
    }

    // 父类没有不带参数的构造方法，这里手动构造父类
    public BasedaoRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        this(JpaEntityInformationSupport.getEntityInformation(domainClass, entityManager), entityManager);
        this.entityManager = entityManager;
    }

    /************************************************持久化方法************************************************/
    @Override
    public void store(Object... item) {
        if (null != item) {
            for (Object entity : item) {
                innerSave(entity);
            }
        }
    }

    @Override
    public void update(Object... item) {
        if (null != item) {
            for (Object entity : item) {
                entityManager.merge(entity);
            }
        }
    }

    @Override
    public void updateAll(Iterable<T> obj) {
        if (null != obj) {
            obj.forEach(item -> update(item));
        }
    }

    @Override
    public int executeUpdate(String qlString, Object... values) {
        Query query = entityManager.createQuery(qlString);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i + 1, values[i]);
            }
        }
        return query.executeUpdate();
    }

    @Override
    public int executeUpdateSql(String sql, Object... values) {
        Query query = entityManager.createNativeQuery(sql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i + 1, values[i]);
            }
        }
        return query.executeUpdate();
    }

    @Override
    public int executeUpdate(String qlString, Map<String, Object> params) {
        Query query = entityManager.createQuery(qlString);
        for (String name : params.keySet()) {
            query.setParameter(name, params.get(name));
        }
        return query.executeUpdate();
    }

    @Override
    public int executeUpdate(String qlString, List<Object> values) {
        Query query = entityManager.createQuery(qlString);
        for (int i = 0; i < values.size(); i++) {
            query.setParameter(i + 1, values.get(i));
        }
        return query.executeUpdate();
    }

    /**
     * 保存对象
     *
     * @param item 保存对象
     * @return
     */
    private Serializable innerSave(Object item) {
        try {
            if (item == null) {
                return null;
            }
            Class<?> clazz = item.getClass();
            Field idField = ReflectHelper.getIdField(clazz);
            Method getMethod = null;
            if (idField != null) {
                Class<?> type = idField.getType();
                Object val = idField.get(item);
                if (type == String.class && (val == null || "".equals(val))) {
                    idField.set(item, UUIDUtil.uuid());
                }
            } else {
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    Id id = method.getAnnotation(Id.class);
                    if (id != null) {
                        Object val = method.invoke(item);
                        if (val == null || "".equals(val)) {
                            String methodName = "s" + method.getName().substring(1);
                            Method setMethod = clazz.getDeclaredMethod(methodName, method.getReturnType());
                            if (setMethod != null) {
                                setMethod.invoke(item, UUIDUtil.uuid());
                            }
                        }
                        getMethod = method;
                        break;
                    }
                }
            }
            entityManager.persist(item);
            entityManager.flush();
            if (idField != null) {
                return (Serializable) idField.get(item);
            }
            if (getMethod != null) {
                return (Serializable) getMethod.invoke(item);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /************************************************持久化方法************************************************/

    @Override
    public List<Map> QueryListMap(String sql, Object... params) {
        Query query = GeneratingQueryReturnedToVoThroughSQL(sql, Map.class, false);
        AssignQueryParameter(query, params);
        return query.getResultList();
    }

    @Override
    public List<Map> QueryListMap(String sql) {
        return QueryListMap(sql, new Object[0]);
    }

    @Override
    public List<Map<String, Object>> QueryhumpMap(String sql, Object... params) {
        return QueryToMap(sql, params);
    }

    @Override
    public List<Map<String, Object>> SelectListMap(String Sql, Object... params) {
        Query query = GeneratingQueryReturnedToVoThroughSQL(Sql, Map.class, false);
        AssignQueryParameter(query, params);
        return query.getResultList();
    }

    @Override
    public List<T> QueryListModel(Class<T> resultClass, String Sql, Object... params) {
        return QueryToVo(resultClass, Sql, params);
    }

    @Override
    public BasePage<T> QueryPageList(BaseDto baseDto, @Nullable Specification<T> spec) {
        return QueryPageList(baseDto.getPageIndex(), baseDto.getPageSize(), baseDto.getSortField(), baseDto.getSortOrder(), spec);
    }

    @SuppressWarnings({"unlikely-arg-type", "static-access"})
    @Override
    public BasePage<T> QueryPageList(@Nullable int pageNum, @Nullable int pageSize, @Nullable String sortby, @Nullable String sorting,
                                     @Nullable Specification<T> spec) {
        Sort sort = null;
        Pageable pageable = null;
        List<String> fieldList = Arrays.stream(StrUtil.nullToDefault(sortby, "").split(","))
                .filter(s -> StringUtils.isNotBlank(s)).collect(Collectors.toList());
        if (fieldList.size() > 0) {
            if (Direction.DESC.name().equals(String.valueOf(sorting).toUpperCase())) {
                sort = Sort.by(Direction.DESC, fieldList.toArray(new String[fieldList.size()]));
            } else {
                sort = Sort.by(Direction.ASC, fieldList.toArray(new String[fieldList.size()]));
            }
            pageable = PageRequest.of(pageNum, pageSize, sort);
        } else {
            pageable = PageRequest.of(pageNum, pageSize);
        }
        Page<T> page = this.findAll(spec, pageable);
        return new BasePageImpl(page.getContent(), page.getPageable(), page.getTotalElements());
    }

    @Override
    public BasePage<T> QueryNOPageList(BaseDto baseDto, @Nullable Specification<T> spec) {
        Sort sort = null;
        if (baseDto.getSortField() != null) {
            if (Direction.DESC.name().equals(String.valueOf(baseDto.getSortOrder()).toUpperCase())) {
                sort = Sort.by(Direction.DESC, baseDto.getSortField());
            } else {
                sort = Sort.by(Direction.ASC, baseDto.getSortField());
            }
        }
        return new BasePageImpl(this.findAll(spec, sort));
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public BasePage<Map<String, Object>> QueryPageList(String sql, String countSql, Pageable pageable) {
        Query query = entityManager.createNativeQuery(sql);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        // 转换为map对象
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> rankEntities = query.getResultList();
        //获取总页数
        BigInteger totalCount = getSQLCount(sql);
        //返回分页对象
        Page<Map<String, Object>> page = new PageImpl<>(rankEntities, pageable, totalCount.longValue());
        return new BasePageImpl(page.getContent(), page.getPageable(), page.getTotalElements());
    }

    @Override
    public BasePage<?> QueryPageList(Class<?> vo, BaseDto dto, String sql) {
        Pageable pageable = ConvertBastDtoToPageable(dto);
        return BaseQueryPageList(vo, pageable, sql, false);

    }

    @Override
    public BasePage<Map<String, Object>> QueryMapPageList(BaseDto dto, String sql, boolean humpConversion) {
        Pageable pageable = ConvertBastDtoToPageable(dto);
        return (BasePage<Map<String, Object>>) BaseQueryPageList(Map.class, pageable, sql, humpConversion);
    }

    /**
     * 基础分页查询函数
     *
     * @param vo             实体类或者Map对象
     * @param pageable       分页对象
     * @param sql            sql语句
     * @param humpConversion 下划线转驼峰(对于vo对象该参数无效)
     * @return
     */
    private BasePage<?> BaseQueryPageList(Class<?> vo, Pageable pageable, String sql, boolean humpConversion) {
        sql = joinOrderBy(pageable, sql);
        Query query = GeneratingQueryReturnedToVoThroughSQL(sql, vo, humpConversion);
        /* TODO:替换
        Query query = entityManager.createNativeQuery(sql);
        if (vo.equals(Map.class)) {
            if (humpConversion) {
                // 转换为map对象(下划线转驼峰)
                query.unwrap(NativeQueryImpl.class)
                        .setResultTransformer(com.tengzhi.base.jpa.extension.Transformers.ALIAS_TO_CAMEL_ENTITY_MAP);
            } else {
                // 转换为map对象
                query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

            }
        } else {
            query.unwrap(NativeQueryImpl.class).setResultTransformer(new SelfResultTransformer(vo));
        }*/
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        List<T> rankEntities = query.getResultList();
        BigInteger totalCount = getSQLCount(sql);
        Page<T> page = new PageImpl<T>(rankEntities, pageable, totalCount.longValue());
        return new BasePageImpl(page.getContent(), page.getPageable(), page.getTotalElements());
    }

    @Override
    public String getSingleResult(String Sql, Object... params) {
        return QueryFirstString(Sql, params);
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public BasePage<T> QueryPageLists(String sql, String countSql, Pageable pageable) {
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(new SelfResultTransformer(entityClass));
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        //获取数据
        List<T> rankEntities = query.getResultList();
        //获取总页数
        BigInteger totalCount = getSQLCount(sql);
        //返回分页对象
        Page<T> page = new PageImpl<>(rankEntities, pageable, totalCount.longValue());
        return new BasePageImpl(page.getContent(), page.getPageable(), page.getTotalElements());
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public BasePage<T> QueryPageLists(Pageable pageable, String sql) {
        sql = joinOrderBy(pageable, sql);

        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(new SelfResultTransformer(entityClass));
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        //获取数据
        List<T> rankEntities = query.getResultList();
        //获取总页数
        BigInteger totalCount = getSQLCount(sql);
        Page<T> page = new PageImpl<T>(rankEntities, pageable, totalCount.longValue());
        return new BasePageImpl(page.getContent(), page.getPageable(), page.getTotalElements());
    }

    @Override
    public BasePage<T> QueryNoPageLists(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(new SelfResultTransformer(entityClass));
        List<T> rankEntities = query.getResultList();
        //返回分页对象
        Page<T> page = new PageImpl<>(rankEntities);
        return new BasePageImpl(page.getContent(), page.getPageable(), page.getTotalElements());
    }

    @Override
    public T dynamicSave(T newEntity, T oldEntity) {
        T mergedEntity;
        if (oldEntity == null) {
            entityManager.persist(newEntity);
            mergedEntity = newEntity;
        } else {
            BeanUtil.copyProperties(newEntity, oldEntity, BeanUtils.getNullPropertyNames(newEntity));
            entityManager.merge(oldEntity);
            mergedEntity = oldEntity;
        }
        return mergedEntity;
    }

    @Override
    public BasePage<T> QueryPageLists(String sql, String countSql, BaseDto dto) {
        Pageable pageable = ConvertBastDtoToPageable(dto);
        return QueryPageLists(sql, countSql, pageable);
    }

    @Override
    public BasePage<T> QueryPageLists(BaseDto dto, String sql) {
        Pageable pageable = ConvertBastDtoToPageable(dto);
        return QueryPageLists(pageable, sql);
    }

    @Override
    public BasePage<Map<String, Object>> QueryPageList(String sql, String countSql, BaseDto dto) {
        Sort sort = null;
        if (dto.getSortField() != null) {
            if (Direction.DESC.name().equals(String.valueOf(dto.getSortOrder()).toUpperCase())) {
                sort = Sort.by(Direction.DESC, dto.getSortField());
            } else {
                sort = Sort.by(Direction.ASC, dto.getSortField());
            }
        }
        Pageable pageable = PageRequest.of(dto.getPageIndex(), dto.getPageSize(), sort);
        return QueryPageList(sql, countSql, pageable);
    }

    @Override
    public BasePage<Map<String, Object>> QueryNOPageList(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP); // 转换为map对象
        List<Map<String, Object>> rankEntities = query.getResultList();
        return new BasePageImpl(rankEntities);
    }

    /************************常用方法************************/
    @Override
    public List<Map<String, Object>> QueryToMap(String sql) {
        return QueryToMap(sql, new Object[0]);
    }

    @Override
    public List<Map<String, Object>> QueryToMap(String sql, Object... params) {
        Query query = GeneratingQueryReturnedToMapThroughSQL(sql);
        AssignQueryParameter(query, params);
        return query.getResultList();
    }

    @Override
    public List<Map<String, Object>> QueryToMap(String sql, Map<String, Object> map) {
        return QueryToMap(sql, new Object[]{map});
    }

    @Override
    public List<T> QueryToBean(String sql, Map<String, Object> map) {
        return QueryToBean(sql, new Object[]{map});
    }

    @Override
    public List<T> QueryToBean(String sql) {
        return QueryToBean(sql, new Object[0]);
    }

    @Override
    public List<T> QueryToBean(String sql, Object... params) {
        Query query = GeneratingQueryReturnedToEntityThroughSQL(sql);
        AssignQueryParameter(query, params);
        List<T> list = query.getResultList();
        return null == list ? new ArrayList<T>(0) : list;
    }

    @Override
    public <U> List<U> QueryToVo(Class<U> vo, String sql) {
        return QueryToVo(vo, sql, new Object[0]);
    }

    @Override
    public <U> List<U> QueryToVo(Class<U> vo, String sql, Object... params) {
        Query query = GeneratingQueryReturnedToVoThroughSQL(sql, vo);
        AssignQueryParameter(query, params);
        return query.getResultList();
    }

    @Override
    public <U> List<U> QueryToVo(Class<U> vo, String sql, Map<String, Object> map) {
        return QueryToVo(vo, sql, new Object[]{map});
    }

    @Override
    public Map<String, Object> QueryToFirstMap(String sql) {
        return QueryToFirstMap(sql, new Object[0]);
    }

    @Override
    public Map<String, Object> QueryToFirstMap(String sql, Object... params) {
        Query query = GeneratingQueryReturnedToMapThroughSQL(sql);
        AssignQueryParameter(query, params);
        query.setMaxResults(1);
        List<Map<String, Object>> list = query.getResultList();
        return null == list || list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Map<String, Object> QueryToFirstMap(String sql, Map<String, Object> map) {
        return QueryToFirstMap(sql, new Object[]{map});
    }

    @Override
    public T QueryToFirstBean(String sql) {
        return QueryToFirstBean(sql, new Object[0]);
    }

    @Override
    public T QueryToFirstBean(String sql, Object... params) {
        Query query = GeneratingQueryReturnedToEntityThroughSQL(sql);
        AssignQueryParameter(query, params);
        query.setMaxResults(1);
        List<T> list = query.getResultList();
        return null == list || list.isEmpty() ? null : list.get(0);
    }

    @Override
    public T QueryToFirstBean(String sql, Map<String, Object> map) {
        return QueryToFirstBean(sql, new Object[]{map});
    }

    @Override
    public Class<?> QueryToFirstVo(String sql, Class<?> VO) {
        return QueryToFirstVo(sql, VO, new Object[0]);
    }

    @Override
    public Class<?> QueryToFirstVo(String sql, Class<?> VO, Object... params) {
        Query query = GeneratingQueryReturnedToVoThroughSQL(sql, VO);
        AssignQueryParameter(query, params);
        query.setMaxResults(1);
        List<?> list = query.getResultList();
        return null == list || list.isEmpty() ? null : (Class<?>) list.get(0);
    }

    @Override
    public Class<?> QueryToFirstVo(String sql, Class<?> VO, Map<String, Object> map) {
        return QueryToFirstVo(sql, VO, new Object[]{map});
    }

    @Override
    public String QueryFirstString(String sql) {
        return QueryFirstString(sql, new Object[0]);
    }

    @Override
    public String QueryFirstString(String sql, Object... params) {
        Query query = createNativeQuery(sql);
        AssignQueryParameter(query, params);
        try {
            Object result = query.getSingleResult();
            return null == result ? "" : String.valueOf(result);
        } catch (EntityNotFoundException e) {
            //如果getSingleResult查不到数据则会抛出EntityNotFoundException异常
            return "";
        }
    }

    @Override
    public String QueryFirstString(String sql, Map<String, Object> map) {
        return QueryFirstString(sql, new Object[]{map});
    }

    @Override
    public BasePage<Map<String, Object>> QueryToMapPage(BaseDto dto, String sql) {
        return QueryToMapPage(dto, sql, new Object[0]);
    }

    @Override
    public BasePage<Map<String, Object>> QueryToMapPage(BaseDto dto, String sql, Object... params) {
        //提取分页对象以及排序信息
        Pageable pageable = ConvertBastDtoToPageable(dto);
        sql = joinOrderBy(pageable, sql);
        //通过sql生成可以转Map的Query对象
        Query query = GeneratingQueryReturnedToMapThroughSQL(sql);
        //填充参数
        AssignQueryParameter(query, params);
        //设置分页
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        //获取数据
        List<T> rankEntities = query.getResultList();
        //获取总页数
        BigInteger totalCount = getSQLCount(sql);
        //返回
        Page<T> page = new PageImpl<T>(rankEntities, pageable, totalCount.longValue());
        return new BasePageImpl(page.getContent(), page.getPageable(), page.getTotalElements());
    }

    @Override
    public BasePage<Map<String, Object>> QueryToMapPage(BaseDto dto, String sql, Map<String, Object> map) {
        return QueryToMapPage(dto, sql, new Object[]{map});
    }

    @Override
    public BasePage<?> QueryToVoPage(Class<?> vo, BaseDto dto, String sql) {
        return QueryToVoPage(vo, dto, sql, new Object[0]);
    }

    @Override
    public BasePage<?> QueryToVoPage(Class<?> vo, BaseDto dto, String sql, Object... params) {
        //提取分页对象以及排序信息
        Pageable pageable = ConvertBastDtoToPageable(dto);
        sql = joinOrderBy(pageable, sql);
        //通过sql生成可以转Map的Query对象
        Query query = GeneratingQueryReturnedToVoThroughSQL(sql, vo);
        //填充参数
        AssignQueryParameter(query, params);
        //设置分页
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        //获取数据
        List<T> rankEntities = query.getResultList();
        //获取总页数
        BigInteger totalCount = getSQLCount(sql);
        //返回
        Page<T> page = new PageImpl<T>(rankEntities, pageable, totalCount.longValue());
        return new BasePageImpl(page.getContent(), page.getPageable(), page.getTotalElements());
    }

    @Override
    public BasePage<?> QueryToVoPage(Class<?> vo, BaseDto dto, String sql, Map<String, Object> map) {
        return QueryToVoPage(vo, dto, sql, new Object[]{map});
    }

    @Override
    public BasePage<T> QueryToBeanPage(BaseDto dto, String sql) {
        return QueryToBeanPage(dto, sql, new Object[0]);
    }

    @Override
    public BasePage<T> QueryToBeanPage(BaseDto dto, String sql, Object... params) {
        //提取分页对象以及排序信息
        Pageable pageable = ConvertBastDtoToPageable(dto);
        sql = joinOrderBy(pageable, sql);
        //通过sql生成可以转Map的Query对象
        Query query = GeneratingQueryReturnedToEntityThroughSQL(sql);
        //填充参数
        AssignQueryParameter(query, params);
        //设置分页
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        //获取数据
        List<T> rankEntities = query.getResultList();
        //获取总页数
        BigInteger totalCount = getSQLCount(sql);
        //返回
        Page<T> page = new PageImpl<T>(rankEntities, pageable, totalCount.longValue());
        return new BasePageImpl(page.getContent(), page.getPageable(), page.getTotalElements());
    }

    @Override
    public BasePage<T> QueryToBeanPage(BaseDto dto, String sql, Map<String, Object> map) {
        return QueryToBeanPage(dto, sql, new Object[]{map});
    }
    /************************END 常用方法************************/

    /************************私有方法************************/
    /**
     * 通过原生SQL生成返回Query对象
     *
     * @param sql 原生SQL
     * @return
     */
    private Query createNativeQuery(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        return query;
    }

    /**
     * 通过原生SQL生成返回Map结果集合的Query对象
     * 注意：返回的数据据填充到Map对象时候，默认会把数据表中的数据表中的字段下划线转驼峰
     *
     * @param sql 原生SQL
     * @return Query
     */
    private Query GeneratingQueryReturnedToMapThroughSQL(String sql) {
        return GeneratingQueryReturnedToVoThroughSQL(sql, Map.class, true);
    }

    /**
     * 通过原生SQL生成返回当前Entity集合的Query对象
     *
     * @param sql 原生SQL
     * @return Query
     */
    private Query GeneratingQueryReturnedToEntityThroughSQL(String sql) {
        Query query = createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(new SelfResultTransformer(entityClass));
        return query;
    }

    /**
     * 通过原生SQL生成返回VO集合的Query对象
     *
     * @param sql 原生SQL
     * @return Query
     */
    private Query GeneratingQueryReturnedToVoThroughSQL(String sql, Class<?> VO) {
        return GeneratingQueryReturnedToVoThroughSQL(sql, VO, false);
    }

    /**
     * 通过原生SQL生成返回VO集合的Query对象
     *
     * @param sql            原生SQL
     * @param vo             VO对象(可以是Entity、VO、Map)
     * @param humpConversion 是否大小写转驼峰(如果传入的VO属于Map类型，则该参数有效)
     * @return
     */
    private Query GeneratingQueryReturnedToVoThroughSQL(String sql, Class<?> vo, boolean humpConversion) {
        Query query = createNativeQuery(sql);
        if (vo.equals(Map.class)) {
            if (humpConversion) {
                // 转换为map对象(下划线转驼峰)
                query.unwrap(NativeQueryImpl.class)
                        .setResultTransformer(com.tengzhi.base.jpa.extension.Transformers.ALIAS_TO_CAMEL_ENTITY_MAP);
            } else {
                // 转换为map对象
                query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

            }
        } else {
            query.unwrap(NativeQueryImpl.class).setResultTransformer(new SelfResultTransformer(vo));
        }
        return query;
    }

    /**
     * 填充Query对象的参数(位置参数 & 名称参数)
     *
     * @param query  Query
     * @param params 参数 如果只有一个参数传入并且类型为Map则为名称参数否则为位置参数
     */
    private void AssignQueryParameter(Query query, Object... params) {
        if (null != params && params.length > 0) {
            if (params.length == 1 && params[0] instanceof Map) {
                //根据名称填充参数
                Map<String, Object> map = (Map<String, Object>) params[0];
                if (null != map && !map.isEmpty()) {
                    for (String key : map.keySet()) {
                        String value = map.get(key).toString();
                        query.setParameter(key, value);
                    }
                }
            } else {
                //根据位置填充参数
                for (int i = 0; i < params.length; i++) {
                    query.setParameter((i + 1), params[i]);
                }
            }
        }
    }

    /**
     * 通过分页对象拼接Order by 片段
     *
     * @param pageable
     * @param sql
     * @return
     */
    private String joinOrderBy(Pageable pageable, String sql) {
        // 不存在分页语句就手动拼接,该处会将小写驼峰命名的字段转_小写，例如sqRq->sq_rq,如果排序报错请检查该处
        if (null != pageable && null != pageable.getSort()) {
            List<String> sorts = pageable.getSort()
                    .map((order) -> ReUtil.replaceAll(order.getProperty(), "([A-Z])", "_$1").toLowerCase() + " "
                            + order.getDirection().name())
                    .stream().collect(Collectors.toList());
            if (sorts.size() > 0) {
                int orderByIndex = ReUtil.replaceAll(sql.toLowerCase(), "\\s+", " ").lastIndexOf("order by");
                // 如果不存在order by关键词或者order by后没有“）”就会自动补充order by语句块
                if (orderByIndex < 0
                        || (orderByIndex >= 0 && sql.toLowerCase().lastIndexOf(")", orderByIndex) > orderByIndex)) {
                    sql = sql += " order by " + ArrayUtil.join(sorts.toArray(), ",");
                } else {
                    // 如果存在分页就追加
                    sql += " , " + ArrayUtil.join(sorts.toArray(), ",");
                }
            }
        }
        return sql;
    }

    /**
     * 通过BaseDto中的分页、排序参数转换成Pageable对象
     *
     * @param baseDto
     */
    private Pageable ConvertBastDtoToPageable(BaseDto baseDto) {
        Sort sort = null;
        Pageable pageable = null;
        List<String> fieldList = Arrays.stream(StrUtil.nullToDefault(baseDto.getSortField(), "").split(","))
                .filter(s -> StringUtils.isNotBlank(s)).collect(Collectors.toList());
        if (fieldList.size() > 0) {
            if (Direction.DESC.name().equals(String.valueOf(baseDto.getSortOrder()).toUpperCase())) {
                sort = Sort.by(Direction.DESC, fieldList.toArray(new String[fieldList.size()]));
            } else {
                sort = Sort.by(Direction.ASC, fieldList.toArray(new String[fieldList.size()]));
            }
            pageable = PageRequest.of(baseDto.getPageIndex(), baseDto.getPageSize(), sort);
        } else {
            pageable = PageRequest.of(baseDto.getPageIndex(), baseDto.getPageSize());
        }
        return pageable;
    }

    //剥离语句
    final String dbType = "POSTGRESQL"; // 可以是ORACLE、POSTGRESQL、SQLSERVER、ODPS等

    /**
     * 通过传入的sql总条数
     *
     * @param sql 原生SQL
     * @return BigInteger
     */
    private BigInteger getSQLCount(String sql) {
        //通过Druid的SqlParse解析抽象语法树，将非union的查询语句中查询体部分设置成*,以去除查询体中的子查询，提高查询速度
        //以下情况将不会处理1、union查询语句，2、sql字符串包含多条查询语句
        List<SQLStatement> sqlStatementList = SQLUtils.parseStatements(sql, dbType);
        if (null != sqlStatementList && sqlStatementList.size() == 1) {
            for (int i = 0; i < sqlStatementList.size(); i += 1) {
                if (sqlStatementList.get(i) instanceof SQLSelectStatement) {
                    SQLSelectStatement sqlSelectStatement = (SQLSelectStatement) sqlStatementList.get(i);
                    if (sqlSelectStatement.getSelect().getQuery() instanceof SQLSelectQueryBlock) {
                        // 非union的查询语句
                        SQLSelectQueryBlock sqlSelectQueryBlock = (SQLSelectQueryBlock) sqlSelectStatement.getSelect().getQuery();
                        //清除所有查询条件
                        sqlSelectQueryBlock.getSelectList().clear();
                        //清除所有orderby
                        if (null != sqlSelectQueryBlock.getOrderBy() && !sqlSelectQueryBlock.getOrderBy().getItems().isEmpty()) {
                            sqlSelectQueryBlock.getOrderBy().getItems().clear();
                        }
                        //追加简单的查询体
                        if (null != sqlSelectQueryBlock.getGroupBy() && !sqlSelectQueryBlock.getGroupBy().getItems().isEmpty()) {
                            //包含group by 的语句
                            sqlSelectQueryBlock.getGroupBy().getItems().forEach(sqlExpr -> {
                                SQLSelectItem item = new SQLSelectItem();
                                item.setExpr(sqlExpr);
                                sqlSelectQueryBlock.getSelectList().add(item);
                            });
                        } else {
                            //不包含group的语句
                            SQLSelectItem item = new SQLSelectItem();
                            item.setExpr(new SQLVariantRefExpr("*"));
                            sqlSelectQueryBlock.getSelectList().add(item);
                        }
                        sql = sqlSelectStatement.toString();
                    } else if (sqlSelectStatement.getSelect().getQuery() instanceof SQLUnionQuery) {
                        // union的查询语句
                    }
                }
            }
        }
        String count = QueryFirstString("select count(*) from(" + sql + ") __hibernate_inner_count__ ");
        return "".equals(count) ? BigInteger.valueOf(0L) : BigInteger.valueOf(Long.valueOf(count));
    }
    /************************END 私有方法************************/
}
