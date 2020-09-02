package com.tengzhi.business.tooling.toolingstore.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.tools.UUID.UUIDUtil;
import com.tengzhi.base.tools.time.DateFormatUtil;
import com.tengzhi.business.ck.yw.ckck.model.ECkOut;
import com.tengzhi.business.mesGz.gzck.vo.EckOut;
import com.tengzhi.business.mesGz.gzda1.dao.MGzMjdaDao;
import com.tengzhi.business.mesGz.gzda1.model.MGzMjda;
import org.apache.commons.lang3.StringUtils;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.tooling.grantandrecover.dao.dispatcherJPADao;
import com.tengzhi.business.tooling.grantandrecover.pojo.MGzMjrecord;
import com.tengzhi.business.tooling.toolingstore.dao.ToolingStoreDao;
import com.tengzhi.business.tooling.toolingstore.dao.ToolingStoreJPADao;
import com.tengzhi.business.tooling.toolingstore.service.ToolingStoreDaoService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author 鱼游浅水
 * @create 2020-06-28
 */
@Service
public class ToolingStoreDaoServiceImpl implements ToolingStoreDaoService {

    @Autowired //JPA类
    private ToolingStoreJPADao toolingStoreJPADao;

    @Autowired //Sql自定义Sql语句Dao层
    private ToolingStoreDao toolingStoreDao;

    @Autowired
    private SysGenNoteService sysGenNoteService;

    @Autowired
    private MGzMjdaDao mGzMjdaDao;
    
    @Autowired
    private dispatcherJPADao mGzMjrecordDao;

    @Override
    public Result findAll(BaseDto baseDto) throws IOException {
        Map<String, String> map = baseDto.getParamsMap();
        return Result.formPage(toolingStoreDao.queryHomepageRecord(map), toolingStoreDao.queryHomepageRecord(map).size());
    }

    @Override
    public Result querySingleById(BaseDto baseDto){
        Map<String, String> map = baseDto.getParamsMap();
        List<Map<String,Object>> list = toolingStoreDao.checkRawMaterials(map.get("outNote"),map.get("Act"));
        return Result.formPage(list,list.size());
    }

    @Override
    public Result queryAllOutboundStorage(BaseDto baseDto) {
        Map<String, String> map = baseDto.getParamsMap();
        if("GM31".equals(map.get("Act"))){
            return Result.resultOk(toolingStoreDao.queryAllOutboundStorage(map));
        }else if("GM32".equals(map.get("Act"))){
            return Result.resultOk(toolingStoreDao.queryOutboundRecords(map));
        }
        return null;
    }

    /*@Override
    public Result toolingSave(Map<String,Object> map) throws Exception {
        List<Map<String,Object>> list= (List<Map<String, Object>>) map.get("bodyData");
        Map<String,String> Head=(Map<String, String>) map.get("HeadData");
        SecurityUser securityUser= (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String note=sysGenNoteService.getyyyyMMddNote4(MGzMjrecord.class, "MG");
        Set<Map<String,String>> set=new HashSet<>();

        for(int i=0;i<list.size();i++){
            Map<String, Object> item=list.get(i);
            if(ObjectUtil.isNotEmpty(item.get("outRemarks"))){
                String sql="select '1' xx_xx from e_ck_out a RIGHT JOIN m_gz_mjda b on a.out_note=b.mj_note  where  out_act='"+Head.get("Act")+"' and mj_act is null and b.mj_code='"+item.get("outRemarks")+"' \n";
                if(mGzMjdaDao.QueryhumpMap(sql).size()>0){
                    return Result.error("数据中出现重复模具编码!");
                }
            }
        }
        list.forEach(item->{

            MGzMjda mGzMjda=new MGzMjda();
            mGzMjda.setMjNote(note);
            mGzMjda.setMjCode(ObjectUtil.isNotEmpty(item.get("outRemarks"))?item.get("outRemarks").toString():"");
            mGzMjda.setDataMan("");
            mGzMjda.setMjCpcode(item.get("outCode").toString());
            mGzMjda.setMjStatus(ObjectUtil.isNotEmpty(item.get("outOriginalPack"))?item.get("outOriginalPack").toString():"");
            mGzMjdaDao.save(mGzMjda);

            Map<String,String> s=new MyHashMap<>();
            s.put("outCustomer", Head.get("outCustomer"));
            s.put("outAct", Head.get("Act"));
            s.put("outRemarks", ObjectUtil.isNotEmpty(item.get("outRemarks"))?item.get("outRemarks").toString():"");
            s.put("outCode", item.get("outCode").toString());
            s.put("outRq", Head.get("outRq"));
            s.put("sl",String.valueOf(item.get("sl")));
            s.put("outPack",item.get("outPack").toString());
            s.put("outLib", Head.get("outLib"));
            s.put("outType", item.get("outType").toString());
            s.put("outHs", item.get("outHs").toString());
            s.put("outLyr", Head.get("outLyr"));
            s.put("outFlag", "登记");
            set.add(s);
        });
        set.forEach(Item->{
            Integer amount=Head.get("Act").equals("LY01")?-1:1;
            EckOut eckOut=new EckOut();
            eckOut.setOutNote(note);
            eckOut.setOutCustomer(Item.get("outCustomer"));
            eckOut.setOutAct(Item.get("outAct"));
            eckOut.setOutCode(Item.get("outCode"));
            eckOut.setOutRq(DateFormatUtil.parseDate(Item.get("outRq")));
            eckOut.setOutPack(Item.get("outPack"));
            eckOut.setOutBpack(Item.get("outPack"));
            eckOut.setOutLib(Item.get("outLib"));

            String sql="select sl from v_ck_mx where pack='"+Item.get("outPack")+"'";
            List<Map<String, Object>> maps=toolingStoreJPADao.QueryhumpMap(sql);
            if(maps.size()>0){
                Map<String, Object> mapItem=maps.get(0);
                eckOut.setOutJs(new BigDecimal(mapItem.get("sl").toString()));
            }

            //初始登记数据
            eckOut.setOutSl(new BigDecimal(amount));
            eckOut.setOutType(Item.get("outType"));
            eckOut.setOutFlag(Item.get("outFlag"));
            eckOut.setDataMan(securityUser.getUserId());
            eckOut.setDataDate(new Date());
            eckOut.setDataCorp(securityUser.getCorpId());
            eckOut.setOutHs(Item.get("outHs"));
            eckOut.setOutLyr(Item.get("outLyr"));
            toolingStoreJPADao.save(eckOut);
            MGzMjda mGzMjda0=
            new MGzMjda(
                note,DateFormatUtil.parseDate(Head.get("outRq")),
                ObjectUtil.isNotEmpty(Item.get("outRemarks"))?Item.get("outRemarks").toString():"",
                Item.get("outCode"),
                new BigDecimal(amount),
                new BigDecimal("0"),
                new BigDecimal("0"),
                Item.get("outFlag"),
                securityUser.getUserId(),
                new Date(),
                securityUser.getCorpId(),
                "登记",Head.get("Act")
            );
            mGzMjdaDao.save(mGzMjda0);

        });
        return Result.resultOkMsg("新增成功");
    }*/
    @Override
    public Result toolingSave(Map<String,Object> map) throws Exception {
        List<Map<String,String>> list= (List<Map<String, String>>) map.get("bodyData");
        Map<String,String> head=(Map<String, String>) map.get("HeadData");
        SessionUser securityUser=SessionUser.SessionUser();
        String note = sysGenNoteService.getyyMMNote4(ECkOut.class, head.get("Act"));
        HashSet<String> hashSet = new HashSet<>();
        
        if(head.get("Act").equals("GM31")) {
        	//判断是否有重复编码
            for(int i=0;i<list.size();i++){
            	hashSet.add(list.get(i).get("outRemarks"));
            }
            if(hashSet.size()!=list.size()) {
            	 return Result.error("数据中出现重复模具编码!");
            }else if(!"0".equals(mGzMjrecordDao.getSingleResult("select count(*) from m_gz_mjda where mj_code in ('"+StringUtils.join(hashSet.toArray(),"','")+"')"))) {
            	return Result.error("模具编码已存在!");
            }
        }
        //插入数据到模具记录表
        list.forEach(item->{
            MGzMjrecord mGzMjrecord=new MGzMjrecord();
            mGzMjrecord.setMjNote(note);
            mGzMjrecord.setMjRq(DateFormatUtil.parseDate(head.get("outRq")));
            mGzMjrecord.setMjCode(item.get("outRemarks").toString());
            mGzMjrecord.setMjCpcode(item.get("outCode").toString());
            mGzMjrecord.setMjSl(head.get("Act").equals("LY01")?-1:1);
            mGzMjrecord.setMjAct(head.get("Act"));
            mGzMjrecord.setMjFlag("登记");
            mGzMjrecord.setMjKid(UUIDUtil.uuid());
            mGzMjrecord.setDataMan(securityUser.getUserId());
            mGzMjrecord.setDataCorp(securityUser.getCorpId());
            mGzMjrecord.setDataMan(securityUser.getUserId());
            mGzMjrecord.setLyPack(item.get("outPack").toString());
            mGzMjrecordDao.save(mGzMjrecord);
        });
        
        //汇总插入到出库表里
        toolingStoreDao.insertOutData(note,head.get("outCustomer"),head.get("Act"),head.get("outLib"),securityUser.getUserId(),securityUser.getCorpId(),head.get("outLyr"));
        return Result.resultOkMsg("新增成功");
    }

    @Override
    public Result toolingUpdate(Map<String,Object> map) {
        List<Map<String,String>> list= (List<Map<String, String>>) map.get("bodyData");
        Map<String,String> head=(Map<String, String>) map.get("HeadData");
        SessionUser securityUser=SessionUser.SessionUser();
        String note = sysGenNoteService.getyyMMNote4(ECkOut.class, head.get("Act"));
        HashSet<String> hashSet = new HashSet<>();
        
        toolingStoreJPADao.deleteByNote(head.get("outNote"));
        toolingStoreJPADao.deleteByT(head.get("outNote"));
        if(head.get("Act").equals("GM31")) {
        	//判断是否有重复编码
            for(int i=0;i<list.size();i++){
            	hashSet.add(list.get(i).get("outRemarks"));
            }
            if(hashSet.size()!=list.size()) {
            	 return Result.error("数据中出现重复模具编码!");
            }else if(!"0".equals(mGzMjrecordDao.getSingleResult("select count(*) from m_gz_mjda where mj_code in ('"+StringUtils.join(hashSet.toArray(),"','")+"')"))) {
            	return Result.error("模具编码已存在!");
            }
        }
        
        //插入数据到模具记录表
        list.forEach(item->{
            MGzMjrecord mGzMjrecord=new MGzMjrecord();
            mGzMjrecord.setMjNote(note);
            mGzMjrecord.setMjRq(DateFormatUtil.parseDate(head.get("outRq")));
            mGzMjrecord.setMjCode(item.get("outRemarks").toString());
            mGzMjrecord.setMjCpcode(item.get("outCode").toString());
            mGzMjrecord.setMjSl(head.get("Act").equals("LY01")?-1:1);
            mGzMjrecord.setMjAct(head.get("Act"));
            mGzMjrecord.setMjFlag("登记");
            mGzMjrecord.setMjKid(UUIDUtil.uuid());
            mGzMjrecord.setDataMan(securityUser.getUserId());
            mGzMjrecord.setDataCorp(securityUser.getCorpId());
            mGzMjrecord.setDataMan(securityUser.getUserId());
            mGzMjrecord.setLyPack(item.get("outPack").toString());
            mGzMjrecordDao.save(mGzMjrecord);
        });
        
        //汇总插入到出库表里
        toolingStoreDao.insertOutData(note,head.get("outCustomer"),head.get("Act"),head.get("outLib"),securityUser.getUserId(),securityUser.getCorpId(),head.get("outLyr"));
        return Result.resultOkMsg("修改成功");
    }
    /*@Override
    public Result toolingUpdate(Map<String,Object> map){
        List<Map<String,Object>> list= (List<Map<String,Object>>) map.get("bodyData");
        Map<String,String> Head=(Map<String,String>) map.get("HeadData");
        SecurityUser securityUser= (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Map<String,String>> set=new HashSet<>();
        toolingStoreJPADao.deleteByNote(Head.get("outNote"));
        toolingStoreJPADao.deleteByT(Head.get("outNote"));

        for(int i=0;i<list.size();i++){
            Map<String, Object> item=list.get(i);
            if(ObjectUtil.isNotEmpty(item.get("outRemarks"))){
                String sql="select '1' xx_xx from e_ck_out a RIGHT JOIN m_gz_mjda b on a.out_note=b.mj_note  where  out_act='"+Head.get("Act")+"' and mj_act is null and b.mj_cpcode='"+item.get("outRemarks")+"' \n";
                if(mGzMjdaDao.QueryhumpMap(sql).size()>0){
                    return Result.error("数据中出现重复模具编码!");
                }
            }
        }

        list.forEach(item->{
            MGzMjda mGzMjda=new MGzMjda();
            mGzMjda.setMjNote(Head.get("outNote"));
            mGzMjda.setMjCode(UUIDUtil.uuid());
            mGzMjda.setDataMan(item.get("outCode").toString());
            mGzMjda.setMjCpcode(ObjectUtil.isNotEmpty(item.get("outRemarks"))?item.get("outRemarks").toString():"");
            mGzMjda.setMjStatus(ObjectUtil.isNotEmpty(item.get("outOriginalPack"))?item.get("outOriginalPack").toString():"");
            mGzMjdaDao.save(mGzMjda);
            Map<String,String> s=new MyHashMap<>();
            s.put("outCustomer", Head.get("outCustomer"));
            s.put("outAct", Head.get("Act"));
            s.put("outCode", item.get("outCode").toString());
            s.put("outRq", Head.get("outRq"));
            s.put("sl",String.valueOf(item.get("sl")));
            s.put("outPack",item.get("outPack").toString());
            s.put("outLib", Head.get("outLib"));
            s.put("outType", item.get("outType").toString());
            s.put("outHs", item.get("outHs").toString());
            s.put("outLyr", Head.get("outLyr"));
            s.put("outFlag", "登记");
            set.add(s);
        });
        set.forEach(Item->{
            Integer amount=Head.get("Act").equals("LY01")?-1:1;
            EckOut eckOut=new EckOut();
            eckOut.setOutNote(Head.get("outNote").toString());
            eckOut.setOutCustomer(Item.get("outCustomer"));
            eckOut.setOutAct(Item.get("outAct"));
            eckOut.setOutCode(Item.get("outCode"));
            eckOut.setOutRq(DateFormatUtil.parseDate(Item.get("outRq")));
            eckOut.setOutPack(Item.get("outPack"));
            eckOut.setOutBpack(Item.get("outPack"));
            eckOut.setOutLib(Item.get("outLib"));

            //需实时同步
            String sql="select sl from v_ck_mx where pack='"+Item.get("outPack")+"'";
            List<Map<String, Object>> maps=toolingStoreJPADao.QueryhumpMap(sql);
            if(maps.size()>0){
                Map<String, Object> mapItem=maps.get(0);
                eckOut.setOutJs(new BigDecimal(mapItem.get("sl").toString()));
            }

            eckOut.setOutSl(new BigDecimal(amount));
            eckOut.setOutType(Item.get("outType"));
            eckOut.setOutFlag(Item.get("outFlag"));
            eckOut.setDataMan(securityUser.getUserId());
            eckOut.setDataDate(new Date());
            eckOut.setDataCorp(securityUser.getCorpId());
            eckOut.setOutHs(Item.get("outHs"));
            eckOut.setOutLyr(Item.get("outLyr"));
            toolingStoreJPADao.save(eckOut);
            MGzMjda mGzMjda0=
            new MGzMjda(
            Head.get("outNote").toString(),DateFormatUtil.parseDate(Head.get("outRq")),
            ObjectUtil.isNotEmpty(Item.get("outRemarks"))?Item.get("outRemarks").toString():"",
            Item.get("outCode"),
            new BigDecimal(amount),
            new BigDecimal("0"),
            new BigDecimal("0"),
            Item.get("outFlag"),
            securityUser.getUserId(),
            new Date(),
            securityUser.getCorpId(),
            "登记",Head.get("Act")
            );
            mGzMjdaDao.save(mGzMjda0);
        });
        return Result.resultOkMsg("修改成功");
    }
*/
    @Override
    public Result toolingDelete(String Note) {
        toolingStoreJPADao.deleteByNote(Note);
        toolingStoreJPADao.deleteByT(Note);
        return Result.resultOkMsg("删除成功");
    }

    @Override
    public Result toolingconfirm(Map<String,Object> map){
        List<Map<String,String>> list= (List<Map<String,String>>) map.get("bodyData");
        Map<String,String> head=(Map<String,String>) map.get("HeadData");
        SecurityUser securityUser= (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        toolingStoreJPADao.settle(head.get("outNote"));
        list.forEach(item->{
            MGzMjda mGzmjda=new MGzMjda();
            mGzmjda.setMjNote(head.get("outNote"));
            mGzmjda.setMjRq(DateFormatUtil.parseDate(head.get("outRq")));
            mGzmjda.setMjCode(item.get("outRemarks").toString());
            mGzmjda.setMjCpcode(item.get("outCode").toString());
            mGzmjda.setMjAct(head.get("Act"));
            mGzmjda.setMjFlag("登记");
            mGzmjda.setDataMan(securityUser.getUserId());
            mGzmjda.setDataCorp(securityUser.getCorpId());
            mGzmjda.setDataMan(securityUser.getUserId());
            //mGzmjda.setLyPack(item.get("outPack").toString());
            mGzMjdaDao.save(mGzmjda);
        });
        return Result.resultOkMsg("确认成功");
    }

    @Override
    public Result enquiryRegistration(String Act) {
        String sql="select  b.mj_cpcode from e_ck_out a RIGHT JOIN m_gz_mjda b on a.out_note=b.mj_note  where  out_act='"+Act+"' and mj_act is null and out_flag in('登记')";
        return Result.resultOk(toolingStoreJPADao.QueryhumpMap(sql));
    }


    @Override
    public Result enquiryRegistrationOK(Map<String,String> map) {
        for (Map.Entry<String,String> entry : map.entrySet()) {
            String sql="select sl from v_ck_mx where pack='"+entry.getKey()+"'";
            List<Map<String, Object>> maps=toolingStoreJPADao.QueryhumpMap(sql);
            if(maps.size()>0){
                Map<String, Object> mapItem=maps.get(0);
                BigDecimal a= new BigDecimal(entry.getValue());
                BigDecimal b= new BigDecimal(mapItem.get("sl").toString());
                if(a.intValue()>b.intValue()){
                    return Result.error();
                }
            }
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
        }
        return Result.resultOk();
    }


}
