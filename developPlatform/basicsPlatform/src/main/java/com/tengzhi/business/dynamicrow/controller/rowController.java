package com.tengzhi.business.dynamicrow.controller;

import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.dynamicrow.cache.rowCache;
import com.tengzhi.business.dynamicrow.model.dynamicrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 鱼游浅水
 * @create 2020-08-01
 */
@RestController
@RequestMapping("/grid/dynamic/column")
public class rowController {

    @Autowired
    private rowCache rowCache;

    @PostMapping("/currentAll")
    public Result rowAll(@RequestBody Map<String,Object> map){
       return rowCache.AllRowObj(map);
    }

    @PostMapping("/currentSavaAndUpdate")
    public Result rowUpdate(@RequestBody dynamicrow dynamicrow){
        return rowCache.SavaAndUpdate(dynamicrow);
    }

    @DeleteMapping("/DeleteIn")
    public Result rowDeleteIn(@RequestBody Map<String,Object> map){
        return rowCache.Delete(map);
    }

    @GetMapping("/whetherTheAdministrator")
    public Result isAdmin(){
        return rowCache.isAdmin();
    }

}
