package com.tengzhi.business.platform.shopping.controller;


import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.annotations.Anonymous;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.platform.shopping.model.ShoppingCart;
import com.tengzhi.business.platform.shopping.model.ShoppingVo;
import com.tengzhi.business.platform.shopping.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("platform/shopping")
public class ShoppingController {

    @Autowired
    ShoppingService shoppingService;

    /**
     * 查询购物车信息(当前登录用户)
     * @return
     */
    @GetMapping("findAllShoppingCart")
	public Result getShoppingAll(){
        SessionUser sessionUser = SessionUser.SessionUser();
        if(null != sessionUser.getgUserInfo()){
            return Result.resultOk(shoppingService.findAll(sessionUser.getgUserInfo().getUserId()));
        }else{
            return Result.error();
        }
    }

    /**
     * 判断购物车产品信息并保存
     * @param dataMan
     * @param productNum
     * @return
     * @throws Exception
     */

    @Anonymous
    @PostMapping("addShoppingCart")
    public Result addShoppingCart(@RequestParam("dataMan") String dataMan, @RequestParam("productNum") String productNum) throws Exception {
        if (SessionUser.SessionUser() == null) {
            return Result.error("加入购物车失败，请先登录");
        }
        return shoppingService.addShoppingCart(dataMan, productNum);
    }

    /**
     * 修改购物车信息 
     * @param shoppingCart
     * @return
     */
    @PutMapping("putShoppingCart")
    public Result putShoppingCart(@RequestBody ShoppingCart shoppingCart){
        return shoppingService.update(shoppingCart);
    }

    /**
     * 删除购物车信息
     * @param productId
     * @return
     */
    @DeleteMapping("delShoppingCart")
    public Result delShoppingCart(@RequestParam("productId") String productId) {
        return shoppingService.deleteByPId(productId);
    }

    /**
     * 批量删除购物车商品
     * @param productIds
     * @return
     */
    @ResponseBody
    @PostMapping("delShoppingCarts")
    public Result delShoppingCartS(final String[] productIds) {
        List<String> arry = Arrays.asList(productIds);
        return shoppingService.deleteByAll(arry);
    }
    
    /**
     * 购物车结算
     * @param
     * @return
     */
    @PostMapping("settlement")
    public Result settlement(@RequestBody List<ShoppingVo> products){
        return shoppingService.updateByPId(products);
    }

    /**
     * 查询订单信息中的商品
     * @return
     */
    @ResponseBody
    @PostMapping("getorderSure")
    public Result getorderSure(final String[] productIds) {
        List<String> arry = Arrays.asList(productIds);
        List<Map<String, Object>> all = shoppingService.findByPid(SessionUser.SessionUser().getgUserInfo().getUserId(), arry);
        return Result.resultOk(all);
    }
    
    /**
     * 确认订单
     * @return
     * @throws Exception
     */
    @PostMapping("sureShop")
    public Result sureShop(@RequestParam String goods) throws Exception {
    	return shoppingService.sureShop(goods);
    }
}
