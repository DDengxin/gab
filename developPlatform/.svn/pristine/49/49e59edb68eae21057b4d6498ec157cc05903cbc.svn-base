package com.tengzhi.business.finance.voucher.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.tools.reflect.SelfResultTransformer;
import com.tengzhi.business.codeGeneration.vo.Structure;
import com.tengzhi.business.codeGeneration.vo.TableVo;
import com.tengzhi.business.finance.voucher.dao.BusinessCategoryDao;
import com.tengzhi.business.finance.voucher.dao.BusinessCategoryEntryDao;
import com.tengzhi.business.finance.voucher.model.EFVoucherBscategory;
import com.tengzhi.business.finance.voucher.service.BusinessCategoryService;
import com.tengzhi.business.finance.voucher.vo.BsCategoryVo;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

@Service
@Transactional
public class BusinessCategoryServiceImpl implements BusinessCategoryService {

    @Autowired
    private BusinessCategoryDao businessCategoryDao;
    @Autowired
    private BusinessCategoryEntryDao businessCategoryEntryDao;


    @Override
    public BasePage<EFVoucherBscategory> getList(BaseDto baseDto) throws IOException {
        Map<String,String> map = baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixWhere(item->{
            item.eq("data_corp", SessionUser.SessionUser().getCorpId());
            item.eq("ftable",map.get("ftable"));
        });
        String sql = " select * from e_f_voucher_bscategory "+where;
        return businessCategoryDao.QueryPageLists(baseDto,sql);
    }


    public List<Map> getList(){
        String sql = " select * from e_f_voucher_bscategory  where data_corp='"+SessionUser.SessionUser().getCorpId()+"' ";
        return businessCategoryDao.QueryListMap(sql);
    }

    public List<Map>  getReportList(){
        return businessCategoryDao.QueryListMap(" select fid,fname from e_f_voucher_bscategory where data_corp='"+SessionUser.SessionUser().getCorpId()+"' order by fid asc ");
    }

    @Override
    public List<Map> findTableFieldDistinctValue(String tableName, String fieldName) {
//当前系统无法保证表字段是否包含data_corp
String sql=" SELECT count(*)   FROM pg_class c left join pg_attribute a  on \n" +
        "               a.attrelid = c.oid  left join  pg_namespace dd ON dd.oid = c.relnamespace\n" +
        "               LEFT  JOIN pg_description b ON a.attrelid=b.objoid AND a.attnum = b.objsubid \n" +
        "               WHERE c.relname = 'e_f_voucher_report'  and a.attnum > 0 and dd.nspname = current_schema() \n" +
        " and a.attname='data_corp'";
        String ifexist =businessCategoryDao.getSingleResult(sql);
        String sqlwhere = SqlJoint.whereSuffixWhere(item->{
              item.eq("data_corp",ifexist.equals("0")?null:SessionUser.SessionUser().getCorpId());
        });
        return businessCategoryDao.QueryListMap(" select distinct "+fieldName+"  from "+tableName+"  "+sqlwhere);
    }
    @Override
    public List<Map>findTableField(String tablename) {
        String sql = "select  a.attname as field, COALESCE(b.description,a.attname) as comment  from pg_class c left join pg_attribute a  on  " +
                "a.attrelid = c.oid  left join  pg_namespace dd on dd.oid = c.relnamespace " +
                "left  join pg_description b on a.attrelid=b.objoid and a.attnum = b.objsubid " +
                "where c.relname = '"+tablename+"'  and a.attnum > 0 and dd.nspname = current_schema()  order by a.attnum";
        return businessCategoryDao.QueryListMap( sql);

    }
    @Override
    public List<TableVo> findAllTables() {

        String sql = "select relname as tabname,COALESCE(cast(obj_description(relfilenode,'pg_class') as varchar)||' '||relname,relname)  as comment from pg_class LEFT JOIN pg_namespace dd ON dd.oid = pg_class.relnamespace"
                + " where relkind = 'r' and relname not like 'pg_%' and relname not like 'sql_%' and dd.nspname = current_schema() and relname not like 'act_%' "
                + "  order by relname";
        return businessCategoryDao.QueryToVo(TableVo.class, sql);
    }



    public Map<String,Object> getById(String fevbusid){
        Map<String,Object> rmap = new HashMap<String,Object>();
            String sqlwhere = SqlJoint.whereSuffixWhere(item->{
               item.eq("fevbusid",fevbusid);
               item.eq("data_corp",SessionUser.SessionUser().getCorpId());
            });
        rmap.put("data",   businessCategoryDao.QueryListMap(" select * from e_f_voucher_bscategoryentry   "+sqlwhere));
        return rmap;
    }


    public Result saveData(BsCategoryVo vo){
            BigInteger note=businessCategoryDao.getVoucherid();
            SessionUser securityUser=SessionUser.SessionUser();
            if(null==vo.getBscategory().getFevbusid()) vo.getBscategory().setFevbusid(note);

             vo.getBscategory().setDataCorp(securityUser.getCorpId());

            if (!vo.getAddedList().isEmpty()){
                vo.getAddedList().forEach( item -> {

                    item.setFevbusid(vo.getBscategory().getFevbusid());
                    item.setFid(businessCategoryEntryDao.getEntryid());
                    item.setDataCorp(securityUser.getCorpId());
                    businessCategoryEntryDao.save(item);

                });
            }
            if (!vo.getDeletedList().isEmpty()) {
                businessCategoryEntryDao.deleteAll(vo.getDeletedList());
            }
            if(!vo.getModifyedList().isEmpty()){
                vo.getModifyedList().forEach( item ->{

                    if(null==item.getFid()) item.setFid(businessCategoryEntryDao.getEntryid());
                    item.setFevbusid(vo.getBscategory().getFevbusid());
                    item.setDataCorp(securityUser.getCorpId());
                    businessCategoryEntryDao.save(item);
                    businessCategoryEntryDao.dynamicSave(item,businessCategoryEntryDao.findById(item.getFid()).orElse(null));
                });
            }
        EFVoucherBscategory category=businessCategoryDao.dynamicSave(vo.getBscategory(),businessCategoryDao.findById(vo.getBscategory().getFevbusid()).orElse(null));




        return Result.resultOk(category);
    }

    @Override
    public Result deleteById(String Id) {

        businessCategoryDao.executeUpdateSql("  delete from e_f_voucher_bscategory where fevbusid="+Id+"; delete from e_f_voucher_bscategoryentry where fevbusid="+Id +";  ");
//        businessCategoryDao.executeUpdateSql("  delete from e_f_voucher_bscategory where fevbusid="+Id);
//        businessCategoryDao.executeUpdateSql("  delete from e_f_voucher_bscategoryentry where fevbusid="+Id );
        return Result.resultOkMsg("删除成功");

    }

    @Override
    public List<Map> findAllBsCategoryTypes() {
        String sql = "select distinct ftype from e_f_voucher_bscategory where data_corp='" + SessionUser.SessionUser().getCorpId()+"'";
        return businessCategoryDao.QueryListMap( sql);
    }

    @Override
    public List<Map> findAllBsCategoryTypeNames(String typeName) {
        String sql = "select distinct fname from e_f_voucher_bscategory where data_corp='" + SessionUser.SessionUser().getCorpId()+"'  and ftype='"+typeName+"'";
        return businessCategoryDao.QueryListMap( sql);
    }

    @Override
    public List<Map> findAllCashFlowItems() {
        String sql = "select  freportitem,replace(fdesc,'|','')  as fdesc from e_f_voucher_reportitem where  data_corp='" + SessionUser.SessionUser().getCorpId()+"'  and freportid=(select fid from e_f_voucher_report where data_corp='" + SessionUser.SessionUser().getCorpId()+"'  and fname='现金流量表')";
        return businessCategoryDao.QueryListMap( sql);
    }

}
