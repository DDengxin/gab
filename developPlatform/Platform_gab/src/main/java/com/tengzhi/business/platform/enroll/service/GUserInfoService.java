package com.tengzhi.business.platform.enroll.service;

import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.platform.common.model.UsersPwdVo;
import com.tengzhi.business.platform.enroll.model.GUserInfo;
import org.omg.CORBA.OBJ_ADAPTER;

import java.io.IOException;
import java.util.Map;


/**
 * @author common
 */
public interface GUserInfoService extends BaseService {
    /**
     * 用户注册
     * @return
     */
    Result register(GUserInfo gUserInfo);

    /**
     * 获取注册验证码
     *
     * @param gUserInfo
     * @return
     */
    Result getSmsCode(GUserInfo gUserInfo);

    /**
     * 判断用户名是否重复(userId & userMtel)
     *
     * @param gUserInfo
     * @return
     */
    boolean judgeUnique(GUserInfo gUserInfo);

    Map<String, Object> getIsSubmitApprove();

/*
    *//**
     * 保存
     *
     * @param modelVo
     * @return
     * @throws Exception
     *//*
    Result save(modelVo modelVo) throws Exception;
    *//**
     * 根据userid获取一条记录
     *
     * @return
     *//*
    modelVo findById();

    void update(modelVo vo);

    *//**
     * 获取数据
     *
     * @param cur
     * @param pageSize
     * @param pageIndex
     * @param searchType
     * @param keyWords
     * @return
     *//*
    Map<String, Object> getAll(String cur, String pageSize, String pageIndex, String searchType, String... keyWords);

    *//**
     * 获取专家团队数据
     *
     * @param cur
     * @param pageSize
     * @param pageIndex
     * @return
     *//*
    Map<String, Object> getSrchExpertTeam(String cur, String pageSize, String pageIndex);

    *//**
     * 根据id获取专家个人数据
     *
     * @param id
     * @return
     *//*
    Map<String, Object> getByidExpert(String id);

    *//**
     * 随机获取专家数据
     *
     * @return
     *//*
    Map<String, Object> getRandomExpert();

    *//**
     * 首页获取厂商数据
     *
     * @param supplyType
     * @return
     *//*
    List<Map<String, Object>> getSupply(String supplyType);

    *//**
     * 获取供应商数据
     *
     * @return
     *//*
    Result getAllsupply();

    *//**
     * 分页查询供应商
     *
     * @param baseDto
     * @return
     * @throws IOException
     *//*
    BasePage<Map<String, Object>> getSrchGridList(BaseDto baseDto) throws IOException;

    Result getSupplyByNote(String needNote);

    *//**
     * 审核通过授权
     *
     * @param examine
     * @return
     * @throws Exception
     *//*
    Result agree(Examine examine) throws Exception;


    *//**
     * 根据id获取供应商数据
     *
     * @param supplyId
     * @return
     *//*
    Map<String, Object> getSupplyById(String supplyId);

    List<SelectVo> getAllsupplyxxx();

    *//**
     * 根据导航栏查询专家团队
     *
     * @param expertVos
     * @return
     *//*
    Map<String, Object> getExpertTeamByParam(List<ExpertVo> expertVos);

    *//**
     * 根据搜索框关键字查询专家团队
     *
     * @param keyWords
     * @return
     *//*
    Map<String, Object> getExpertTeamBykeyWords(String keyWords);

    *//**
     * 获取客户案例
     *
     * @param supplyUid
     * @return
     *//*
    Map<String, Object> getAdvisory(String supplyUid);


    *//**
     * 获取供应商
     *
     * @param dto
     * @return
     *//*
    BasePage<Map<String, Object>> getList(BaseDto dto);


    Result SupplySave(modelVo modelVo) throws Exception;


    *//**
     * 获取状态
     *
     * @param expertNote
     * @param status
     * @return
     *//*
    Result getSupplyStatus(String expertNote, String status);


    *//**
     * 修改数据回显
     *
     * @param userId
     * @return
     * @throws Exception
     *//*
    modelVo SupplyInfo(String userId) throws Exception;

    *//**
     * 数据修改
     *
     * @param vo
     *//*
    void supplyedit(modelVo vo);

    */

    /**
     * 平台用户删除供应商
     *
     * @param userId
     *//*
    void deleteBySupplyNote(String userId);


    Result getGUserInfoStatus(String expertNote, String status);*/

    /*
		用户修改密码
	 */
    Result updatePwd(UsersPwdVo usersPwdVo);


}
