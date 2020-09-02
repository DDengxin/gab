package com.tengzhi.business.js.drawing.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.js.drawing.model.EJsCpcodeTdgl;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Auther: huangbiao
 * @Date: 2020/8/14 0014 10:38
 * @Description:
 */
@SuppressWarnings("SqlResolve")
public interface DrawingDao extends BasedaoRepository<EJsCpcodeTdgl,String> {

/*    @Query(value="SELECT b.file_path FROM e_js_cpcode_tdgl a, com_file b where a.tw_file=b.line_primary and a.tw_file=?1",
            nativeQuery=true)
    List<String> findByImgSuffix(String twFile);*/

}
