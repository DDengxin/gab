package com.tengzhi.business.xt.logistics.purchase.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.system.params.service.SysParamService;
import com.tengzhi.business.xt.logistics.purchase.service.PurchaseService;
import com.tengzhi.business.xt.logistics.purchase.vo.PurchaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/xt/logistics/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private SysParamService sysParamService;

    @GetMapping("/*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "/getSrchTopList")
    public Result getSrchTopList(BaseDto baseDto) throws Exception {
        return purchaseService.getSrchTopList(baseDto).getResult();
    }

    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "/getSrchBottomList")
    public Result getSrchBottomList(BaseDto baseDto) throws IOException {
        return purchaseService.getSrchBottomList(baseDto).getResult();
    }

    /**
     * @description: Post新增Restful
     */
    @PostMapping(value = "add.html")
    public Result add(@RequestBody PurchaseVo vo) throws Exception {
        return purchaseService.save(vo);
    }

    @PutMapping(value = "add.html")
    public Result modify(@RequestBody PurchaseVo vo) throws Exception {
        return purchaseService.update(vo);
    }


    /**
     * @Param: [vo]
     * @description: Get(获取数据 | 通过ID获取对象)Restful
     */
    @GetMapping(value = "getById/{note}")
    public Result getById(@PathVariable(value = "note") String note) {
        return Result.resultOk(purchaseService.findBySqnote(note));
    }

    @PutMapping(value = "/getDjFlag")
    public Result getDjFlag(@RequestBody BaseDto baseDto) throws Exception {
        Map<String, String> map = baseDto.getParamsMap();
        return purchaseService.getFlag(String.valueOf(map.get("Note")), "登记");
    }

    @PutMapping(value = "/getQrFlag")
    public Result getQrFlag(@RequestBody BaseDto baseDto) throws Exception {
        Map<String, String> map = baseDto.getParamsMap();
        return purchaseService.getFlag(String.valueOf(map.get("Note")), "核准");
    }


    @PutMapping(value = "/getFlag")
    public Result getFlag(@RequestBody BaseDto baseDto) throws Exception {
        Map<String, String> map = baseDto.getParamsMap();
        return purchaseService.getFlag(String.valueOf(map.get("Note")), String.valueOf(map.get("flag")));
    }

    @PutMapping(value = "/setFlag")
        public Result setFlag(@RequestBody BaseDto baseDto) throws Exception {
            Map<String, String> map = baseDto.getParamsMap();
            return purchaseService.setFlag(String.valueOf(map.get("Note")), String.valueOf(map.get("flag")));
    }

    @DeleteMapping(value = "delete/{Note}")
    public Result delete(@PathVariable(value = "Note") String Note) throws Exception {
        purchaseService.deleteById(Note);
        return Result.resultOk();
    }


}
