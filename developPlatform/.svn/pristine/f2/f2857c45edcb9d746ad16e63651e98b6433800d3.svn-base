import com.tengzhi.build.ApplicationServer;
import com.tengzhi.business.demo.procedure.service.ProcedureService;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author 鱼游浅水
 * @create 2020-06-15
 */
@RunWith(SpringJUnit4ClassRunner.class)//初始化spring上下文
@SpringBootTest(classes = ApplicationServer.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TmallApplicationTests {

    @Resource
    private ProcedureService procedureService;

 /*   @Test
    public void Test1(){
        List<ProcedureModel> list=procedureService.ProcedureCall();
        list.forEach(item->{
            System.out.println(item.getId());
            System.out.println(item.getName());
        });
    }

    @Test
    public void Test2(){
        procedureService.ProcedureCalls();
    }*/

}
