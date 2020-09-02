package com.tengzhi.business.finance.voucher.service;

import com.tengzhi.business.finance.currency.dao.currencyDao;
import com.tengzhi.business.finance.currency.service.currencyService;
import com.tengzhi.business.system.params.model.SysParams;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Callable;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class RateExchange {
    //3.添加定时任务
    @Autowired
    currencyService xcurrencyService;
    @Autowired
    private currencyDao currencyDao;
//    每个整点执行一次
    @Scheduled(cron = "0 0 0/1 * * ?")
//    0/30 * * * * ?
    //或直接指定时间间隔，例如：30秒
    //@Scheduled(fixedRate=30000)
    protected void getRate() {

        Source source = null;
        try {
//            http://www.boc.cn/sourcedb/whpj/enindex.html
            source = new Source(new URL(
                    "https://www.bankofchina.com/sourcedb/whpj/enindex_1619.html"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Element> elementList = source.getAllElements("table").get(7)
                .getAllElements("tr");//.get(28).getAllElements("td");
        for (Element element : elementList) {
            List<Element> el=  element.getAllElements("td");
            if(el.size()>0){
                SysParams c= currencyDao.findByParamId(el.get(0).getContent().toString().trim());
                if(null!=c) {
                    System.out.println(c.getParamName());

                    double rate=Math.round((Double.parseDouble(el.get(5).getContent().toString().trim())/100*10000))/10000.0000; //保留小数点后4位
                    c.setParamValue1(String.valueOf(rate));
                    System.out.println(rate);
                    currencyDao.save(c);
                }

               

            }

        }



    }
}
