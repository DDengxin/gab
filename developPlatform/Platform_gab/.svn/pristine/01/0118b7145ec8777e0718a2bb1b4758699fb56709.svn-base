package com.tengzhi.business.platform.shopping.service.impl;

import cn.hutool.core.util.StrUtil;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.platform.shopping.dao.AddrDao;
import com.tengzhi.business.platform.shopping.dao.AddrSqlDao;
import com.tengzhi.business.platform.shopping.model.GShopAddr;
import com.tengzhi.business.platform.shopping.service.AddrService;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class AddrServiceImpl implements AddrService {

    @Autowired
    private SysGenNoteService sysGenNoteService;

    @Autowired
    private AddrDao addrDao;

    @Autowired
    private AddrSqlDao addrSqlDao;

    @Override
    public List<Map<String, Object>> findAllByUserId() {
        return addrSqlDao.findAllByUserId();
    }

    @Override
    public Result findDefaultAddress() {
        return Result.resultOk(addrSqlDao.findDefaultAddr());
    }

    @Override
    public Result saveAddress(GShopAddr gShopAddr) {
        SessionUser sessionUser = SessionUser.SessionUser();
        String addrId = sysGenNoteService.getNote("e_cw_customer_address", "GADDR", "yyyyMM", 4);
        gShopAddr.setAddressNote(addrId);
        //ERP操作信息
        gShopAddr.setDataCorp(SessionUser.getCurrentCorpId());
        gShopAddr.setGenUserId(sessionUser.getUserId());
        //哥爱帮操作信息
        gShopAddr.setCustomerId(SessionUser.getGabUserId());
        gShopAddr.setCustomerThreeName(gShopAddr.getContacts());
        gShopAddr.setIsDefault(false);
        gShopAddr.setGenTime(new Date());
        return Result.resultOk(addrDao.save(gShopAddr));
    }

    @Override
    public Result updateAddress(GShopAddr gShopAddr) {
        if(StrUtil.isEmpty(gShopAddr.getAddressNote())){
            return Result.error("原地址不存在,修改失败!");
        }else{
        SessionUser sessionUser = SessionUser.SessionUser();
        //ERP操作信息
        gShopAddr.setDataCorp(SessionUser.getCurrentCorpId());
        gShopAddr.setGenUserId(sessionUser.getUserId());
        //哥爱帮操作信息
        gShopAddr.setCustomerId(SessionUser.getGabUserId());
        gShopAddr.setCustomerThreeName(gShopAddr.getContacts());
        addrDao.dynamicSave(gShopAddr,addrDao.getOne(gShopAddr.getAddressNote()));
        return Result.resultOk();
        }
    }

    @Override
    public Result setDefaultAddress(String addressNote) {
        addrDao.setAllFalse(addressNote);
        addrDao.setTrue(addressNote);
        return Result.resultOk();
    }

    @Override
    public Result findAddress(String addressNote) {
        return Result.resultOk(addrSqlDao.findByAddrId(addressNote));
    }

    @Override
    public Result delAddress(String addressNote) {
        SessionUser sessionUser = SessionUser.SessionUser();
//        addrDao.deleteById(addressNote, sessionUser.getgUserInfo().getUserId());
        addrDao.deleteById(addressNote);
        return Result.resultOk();
    }
}
