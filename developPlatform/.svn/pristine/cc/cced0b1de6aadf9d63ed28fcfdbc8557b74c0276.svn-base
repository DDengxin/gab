--存储过程返回多行多列(TABLE表形式) Sql语句写法
CREATE OR REPLACE FUNCTION getTable1(DemoName varchar)
    RETURNS TABLE(id varchar, name varchar) AS $返回集别名$
BEGIN
    RETURN QUERY select org_id,org_name from sys_org where org_name=DemoName;
END
$返回集别名$ LANGUAGE PLPGSQL;

--存储过程返回多行多列(TABLE表形式) EXECUTE动态拼接写法
CREATE OR REPLACE FUNCTION getTable2(DemoName varchar)
    RETURNS TABLE(id varchar, name varchar) AS $返回集别名$
BEGIN
    RETURN QUERY EXECUTE
        'select org_id,org_name from sys_org where org_name=$1'
        USING DemoName;
END
$返回集别名$ LANGUAGE PLPGSQL;

-- 根据out自定义输出列
CREATE OR REPLACE FUNCTION sunNumber(in num INTEGER,out sum INTEGER) AS $$
BEGIN
    if num>5 THEN
        RAISE EXCEPTION '你出问题了。该修修！';
    END if;
    sum := num+1;
END
$$ LANGUAGE PLPGSQL;


SELECT id,name FROM getTable1('辽宁金帝科技有限公司');
SELECT id,name FROM getTable2('辽宁金帝科技有限公司');
SELECT sum FROM sunNumber(9);


--------------------------------------聚合函数示例---------------------------------------

-- 聚合函数
CREATE AGGREGATE multi_column(varchar)(
	    SFUNC = array_join,
      STYPE = _varchar,
      FINALFUNC = multi_status,
			INITCOND ='{}'
)

-- 定义聚合函数自动拼接数组函数 并设置返回集合最大100单位
CREATE OR REPLACE FUNCTION array_join(merge_array _varchar,this_val varchar)
	RETURNS _varchar AS $BODY$
BEGIN
	RETURN merge_array||this_val;
END
$BODY$ LANGUAGE 'plpgsql' VOLATILE COST 100;


--创建聚合函数最后一次调用函数处理结果集
CREATE OR REPLACE FUNCTION multi_status(val_array _varchar)
	RETURNS VARCHAR AS $BODY$
BEGIN
		if '登记'=ANY(val_array::_varchar) THEN
			RETURN '登记';
		end if;
END
$BODY$ LANGUAGE 'plpgsql' VOLATILE COST 100;

-----------------------------------------------

SELECT '{1,2}' && '{1,2,3}'::int[];

SELECT * FROM pg_proc WHERE proname like'%multi%'