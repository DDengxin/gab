package com.tengzhi.business.mesGz.gzck.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.cg.yw.purchaseReceipt.vo.ECkInVo;
import com.tengzhi.business.mesGz.gzck.vo.ECKOutVo;
import com.tengzhi.business.mesGz.gzck.vo.EckOut;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface LyrkService extends BaseService {


    BasePage<EckOut> getSrchTopList(BaseDto baseDto) throws Exception;


    BasePage<EckOut> getSrchBottomList(BaseDto baseDto) throws IOException;


    Result getFlag(String outNote, String flag);


    Result save(ECKOutVo eCkOutVo) throws  Exception;

    Result gzmj(ECkInVo eCkInVo) throws  Exception;

    Result update(ECKOutVo eCkOutVo) throws  Exception;

    void deleteById(String outNote);


    EckOut findByInNote(BaseDto baseDto) throws Exception;


    List<Map> getSlSelectList(String outNote) throws IOException;


    Map<String, Object> getWlbmSelectList(BaseDto baseDto) throws IOException;


    List<Map> getLib(String param_id, String param_name);


}
