package com.tengzhi.business.system.initdata.dao;


import javax.persistence.StoredProcedureQuery;
import java.util.Map;

public interface SysInitDataDaoBasic  {
StoredProcedureQuery callSolveDataProduce(String themeCode, Map<String,Object> map);
void callInitDataProduce(String pname,Map<String,Object> map);
}
