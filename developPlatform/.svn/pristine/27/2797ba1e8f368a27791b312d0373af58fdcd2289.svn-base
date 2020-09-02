package com.tengzhi.business.codeGeneration.dao;

import java.io.IOException;
import java.util.List;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.business.codeGeneration.vo.Structure;
import com.tengzhi.business.codeGeneration.vo.TableVo;

public interface CodeGenerationdao {

	BasePage<TableVo> findAllTable(BaseDto dto) throws IOException;

	List<TableVo> findTable(String tablename);

	List<Structure> findStructure(String tablename);
}
