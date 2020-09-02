package com.tengzhi.business.xt.transaction.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.xt.transaction.model.Transaction;
import com.tengzhi.business.xt.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/xt/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "getList")
    public Result getList(BaseDto baseDto){
        return transactionService.getList(baseDto).getResult();
    }

    /**
     * 新增
     */
    @PostMapping(value = "add.html")
    public Result add(@RequestBody Transaction transaction) throws Exception {
        return transactionService.save(transaction);
    }

    /**
     * 修改
     *
     * @throws ParseException
     **/
    @PutMapping("add.html")
    public Result modify(@RequestBody Transaction transaction) {
        transactionService.update(transaction);
        return Result.resultOk(transaction.getSwNote());
    }

    /**
     * 删除
     *
     * @throws ParseException
     */
    @DeleteMapping(value = "delete.html/{swNote}")
    public Result delete(@PathVariable String swNote) {
        transactionService.delete(swNote);
        return Result.resultOk();
    }

    /**
     * 根据id查询
     *
     * @throws ParseException
     */
    @GetMapping(value = "add.html/{swNote}")
    public Result getById(@PathVariable String swNote) {
        return Result.resultOk(transactionService.find(swNote));
    }

    /**
     * 查询部门名称
     *
     * @throws ParseException
     */
    @GetMapping(value = "user/{swMan}")
    public String user(@PathVariable String swMan) {
        return transactionService.getManName(swMan);
    }
	
}
