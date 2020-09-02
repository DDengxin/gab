package com.tengzhi.business.tooling.grantandrecover.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.base.tools.UUID.UUIDUtil;
import com.tengzhi.base.tools.time.DateFormatUtil;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.system.params.dao.SysParamDao;
import com.tengzhi.business.system.params.model.SysParams;
import com.tengzhi.business.tooling.grantandrecover.dao.dispatcherDao;
import com.tengzhi.business.tooling.grantandrecover.dao.dispatcherJPADao;
import com.tengzhi.business.tooling.grantandrecover.pojo.MGzMjrecord;
import com.tengzhi.business.tooling.grantandrecover.pojo.MGzMjrecordPK;
import com.tengzhi.business.tooling.grantandrecover.service.dispatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

/**
 * @author 鱼游浅水
 * @create 2020-06-28
 */
@Service
public class dispatcherServiceImpl implements dispatcherService{

    @Autowired //JPA类
    private dispatcherJPADao dispatcherJPADao;

    @Autowired //Sql自定义Sql语句Dao层
    private dispatcherDao dispatcherDao;

    @Autowired
    private SysGenNoteService sysGenNoteService;

    @Autowired
    private SysParamDao sysParamDao;

    @Override
    public Result findAll(BaseDto baseDto) throws IOException {
        Map<String, String> map = baseDto.getParamsMap();
        return Result.formPage(dispatcherDao.queryHomepageRecord(map),dispatcherDao.queryHomepageRecord(map).size());
    }

    @Override
    public Result querySingleById(BaseDto baseDto) throws IOException {
        Map<String, String> map = baseDto.getParamsMap();
        List<Map<String,Object>> list = dispatcherDao.checkRawMaterials(map.get("mjNote"),map.get("mjAct"));
        return Result.formPage(list,list.size());
    }

    @Override
    public Result queryAllOutboundStorage(BaseDto baseDto) {
        Map<String, String> map = baseDto.getParamsMap();
        if("MJ02".equals(map.get("mjAct"))|| "MJ04".equals(map.get("mjAct"))){
            return Result.resultOk(dispatcherDao.queryOutboundRecords(map));
        }else{
            return Result.resultOk(dispatcherDao.queryAllOutboundStorage(map));
        }
    }

    @Override
    public Result toolingSave(Map<String,Object> map) throws Exception {
        List<Map<String,String>> list= (List<Map<String, String>>) map.get("bodyData");
        Map<String,String> Head=(Map<String, String>) map.get("HeadData");
        Map<String,String> NameTransform=(Map<String, String>) map.get("nameData");
        SecurityUser securityUser= (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String note=sysGenNoteService.getyyyyMMddNote4(MGzMjrecord.class, "MG");
        list.forEach(item->{
            String mjKid=("MJ01".equals(Head.get("mjAct"))|| "MJ03".equals(Head.get("mjAct")))?UUIDUtil.uuid():item.get("mjKid");
            //采用无参构造存值可能清晰可读性不是很好 但是我想不出更好的方法 将就看吧
            MGzMjrecord mGzMjrecord=new MGzMjrecord(note
            ,DateFormatUtil.parseDate(Head.get("mjRq")),Head.get("mjAct")
            ,item.get("mjCode"),item.get("mjKsize"),"登记"
            ,securityUser.getUserId(),new Date(),securityUser.getCorpId(),item.get("mjRemarks")
            ,Head.get("lyMan"),-1,
            item.get("mjXksize"),mjKid,Head.get("lyDept"),
            NameTransform.get("lyDeptName"),
            NameTransform.get("lyManName"),
            securityUser.getRealName(),item.get("mjCpcode"));
            dispatcherJPADao.save(mGzMjrecord);
        });
        return Result.resultOkMsg("新增成功");
    }

    @Override
    public Result toolingUpdate(Map<String,Object> map){
        List<Map<String,String>> list= (List<Map<String,String>>) map.get("bodyData");
        Map<String,String> Head=(Map<String,String>) map.get("HeadData");
        Map<String,String> NameTransform=(Map<String,String>) map.get("nameData");
        SecurityUser securityUser= (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //构建并行流 分片处理
        StreamSupport.stream(list.spliterator(),true).forEach(item->{
            String mjKid=("MJ01".equals(Head.get("mjAct"))|| "MJ03".equals(Head.get("mjAct")))?UUIDUtil.uuid():item.get("mjKid");
            switch (item.get("_state")) {
                case "added":
                    //采用无参构造存值可能清晰可读性不是很好 但是我想不出更好的方法 将就看吧
                    MGzMjrecord mGzMjrecord=new MGzMjrecord(Head.get("mjNote")
                    ,DateFormatUtil.parseDate(Head.get("mjRq")),Head.get("mjAct")
                    ,item.get("mjCode"),item.get("mjKsize"),"登记"
                    ,securityUser.getUserId(),new Date(),securityUser.getCorpId(),item.get("mjRemarks")
                    ,Head.get("lyMan"),-1,
                    item.get("mjXksize"),mjKid,Head.get("lyDept"),
                    NameTransform.get("lyDeptName"),
                    NameTransform.get("lyManName"),
                    securityUser.getRealName(),item.get("mjCpcode"));
                    dispatcherJPADao.save(mGzMjrecord);
                    break;
                case "modified":
                    dispatcherDao.moldModification(
                        item.get("mjCode"),
                        item.get("mjCpcode"),
                        item.get("mjXksize"),
                        Head.get("mjNote"),
                        item.get("oldmjCode"),
                        Head.get("mjAct")
                    );
                    break;
                case "removed":
                    MGzMjrecordPK DelId=new MGzMjrecordPK(Head.get("mjNote"),Head.get("mjAct"),item.get("mjCode"));
                    dispatcherJPADao.deleteById(DelId);
                    break;
                default:break;
            }
        });
        return Result.resultOkMsg("修改成功");
    }

    @Override
    public Result toolingDelete(String Note) {
        dispatcherJPADao.deleteByNote(Note);
        return Result.resultOkMsg("删除成功");
    }

    @Override
    public Result toolingconfirm(Map<String,Object> map) throws Exception {
        List<Map<String,String>> list= (List<Map<String,String>>) map.get("bodyData");
        Map<String,String> Head=(Map<String,String>) map.get("HeadData");
        Map<String,String> NameTransform=(Map<String,String>) map.get("nameData");
        SecurityUser securityUser= (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String note=sysGenNoteService.getyyyyMMddNote4(MGzMjrecord.class, "MG");

        if("MJ02".equals(Head.get("mjAct"))|| "MJ04".equals(Head.get("mjAct"))){
            list.forEach(item->{
                dispatcherJPADao.modifyStatus(Head.get("mjNote"),item.get("mjCode"),Head.get("mjAct"),1);
                //归还时如果出现维修的设备在维修入库里新增一条维修登记记录
                if("MJ02".equals(Head.get("mjAct")) && "待维修".equals(item.get("mjRemarks"))){
                    MGzMjrecord mGzMjrecord=new MGzMjrecord(note
                    ,DateFormatUtil.parseDate(Head.get("mjRq")),"MJ03"
                    ,item.get("mjCode"),item.get("mjKsize"),"登记"
                    ,securityUser.getUserId(),new Date(),securityUser.getCorpId(),""
                    ,Head.get("lyMan"),-1,
                    item.get("mjXksize"),UUIDUtil.uuid(),Head.get("lyDept"),
                    NameTransform.get("lyDeptName"),
                    NameTransform.get("lyManName"),
                    securityUser.getRealName(),item.get("mjCpcode"));
                    dispatcherJPADao.save(mGzMjrecord);
                }
            });
        }else{
            list.forEach(item->{
                dispatcherJPADao.modifyStatus(Head.get("mjNote"),item.get("mjCode"),Head.get("mjAct"),-1);
            });
        }
        return Result.resultOkMsg("确认成功");
    }

    @Override
    public Result enquiryRegistration(String Act) {
        return Result.resultOk(dispatcherJPADao.findAll(Specifications.where((c)->{
            if("MJ02".equals(Act)|| "MJ04".equals(Act)){
                String goOutAct= "MJ02".equals(Act)?"MJ01":"MJ03";
                c.in("mjAct",Act,goOutAct);
            }else{
                c.eq("mjAct",Act);
            }
            c.eq("mjFlag","登记");
        })));
    }

    @Override
    public List<SelectVo> SELECT_VOS() {
        List<SelectVo> selectVos=new ArrayList<>();
        List<SysParams> list = sysParamDao.findAll(Specifications.where((c)->{
            c.eq("parentId","MOLDSTATUS");
        }));
        list.forEach(item->{
            selectVos.add(new SelectVo(item.getParamName(),item.getParamValue1()));
        });
        return selectVos;
    }
}
