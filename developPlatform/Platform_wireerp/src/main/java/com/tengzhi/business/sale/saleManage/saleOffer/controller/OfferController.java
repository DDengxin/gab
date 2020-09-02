package com.tengzhi.business.sale.saleManage.saleOffer.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.sale.saleManage.saleOffer.model.EXsOffer;
import com.tengzhi.business.sale.saleManage.saleOffer.service.OfferService;
import com.tengzhi.business.sale.saleManage.saleOffer.vo.EXsOfferVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("/sale/saleManage/saleOffer")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @GetMapping(value="/*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }


    @PostMapping("/getList")
    public Result getList(BaseDto baseDto)throws IOException{
       return offerService.findAll(baseDto).getResult();
    }

    @PostMapping("add.html")
    public Result add(@RequestBody EXsOfferVo eXsOfferVo) throws Exception{
        EXsOffer offer = offerService.save(EXsOfferVo.initEXsOfferVo(eXsOfferVo));
        return Result.resultOk(offer.getNote());
    }

    @PutMapping("add.html")
    public Result modify(@RequestBody EXsOfferVo eXsOfferVo){
        offerService.update(EXsOfferVo.initEXsOfferVo(eXsOfferVo));
        return Result.resultOkMsg("修改成功");
    }

    @GetMapping("getByNote/{note}")
    public Result getByNote(@PathVariable String note){
        return Result.resultOk(offerService.findByNote(note));
    }

    @DeleteMapping("delete/{note}")
    public Result deleteByNote(@PathVariable String note){
        offerService.deleteByNte(note);
        return Result.resultOkMsg("删除成功");
    }

    /**
     * 查询报价明细列表
     * @param baseDto
     * @return
     * @throws IOException
     */
    @PostMapping("/getOfferDetailList")
    public Result getOfferDetailList(BaseDto baseDto)throws IOException{
        return offerService.getOfferDetailList(baseDto).getResult();
    }

    @PutMapping("getFlag/{note}/{flag}")
    public Result getFlag(@PathVariable String note,@PathVariable String flag){
        return offerService.getFlag(note,flag);
    }

    /**
     * 确认数据并保存到历史记录
     * @param note
     * @return
     */
    @PutMapping("confirm/{note}")
    public Result confirm(@PathVariable String note){
        return offerService.confirm(note);
    }

    /**
     * 取消确认
     * @param note
     * @return
     */
    @PutMapping("cancle/{note}")
    public Result cancle(@PathVariable String note){
        return offerService.cancle(note);
    }

    /**
     * 销售报价技术明细修改
     * @param eXsOfferVo
     * @return
     */
    @PutMapping("xsbjjs.html")
    public Result updateDetailJs(@RequestBody EXsOfferVo eXsOfferVo){
        offerService.updateDetailJs(EXsOfferVo.initEXsOfferVo(eXsOfferVo));
        return Result.resultOkMsg("修改成功");
    }

    /**
     * 查询当前客户的历史报价
     * @param baseDto
     * @return
     * @throws IOException
     */
    @PostMapping("/getHistoryList")
    public Result getHistoryList(BaseDto baseDto)throws IOException{
        return offerService.getHistoryList(baseDto).getResult();
    }

}
