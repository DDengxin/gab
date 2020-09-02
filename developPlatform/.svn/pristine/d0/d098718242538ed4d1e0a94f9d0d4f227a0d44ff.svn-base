package com.tengzhi.business.demo.procedure.model;

import javax.persistence.*;

/**
 * @author 鱼游浅水
 * @create 2020-06-15
 */
@Entity
/**
 *  @Procedure存储过程查询方法
 *  调用数据库存储过程需要在实体类定义定义
 * name: 在EntityManager中的名字 NamedStoredProcedureQuery使用
 * procedureName: 数据库里存储过程的名字
 * parameters: 使用IN/OUT参数
 *
 * 存储过程使用了注解@NamedStoredProcedureQuery 并绑定到一个JPA表。
 * procedureName是存储过程的名字
 * name是JPA中存储过程的名字
 * 使用注解@StoredProcedureParameter来定义存储过程使用的IN/OU参书
 */

//@NamedStoredProcedureQuery中procedureName参数是数据库中存储过程的名字；
//name参数是JPA中的存储过程的名字
//resultClasses参数是返回结果集绑定的实体名称（处理结果集重要参数)
@NamedStoredProcedureQueries({
//管理列表
@NamedStoredProcedureQuery(name = "getTable1", procedureName = "getTable1", resultClasses = {ProcedureModel.class},
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "DemoName", type = String.class)
        }),
@NamedStoredProcedureQuery(name = "getTable2", procedureName = "getTable2", resultClasses = {ProcedureModel.class},
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "DemoName", type = String.class)
        }),
@NamedStoredProcedureQuery(name = "sunNumber", procedureName = "sunNumber",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "num", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "sum", type = Integer.class)
        })
})
public class ProcedureModel {
    //存储过程机构ID
    private String id;
    //存储过程机构姓名
    private String name;


    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
