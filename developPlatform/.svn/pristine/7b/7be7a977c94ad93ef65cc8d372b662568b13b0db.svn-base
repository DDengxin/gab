package com.tengzhi.business.demo.staff.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.tengzhi.business.demo.staff.dao.PersonDao;
import com.tengzhi.business.demo.staff.model.Person;
import com.tengzhi.business.demo.staff.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


/**
 * @Auther: 黄彪
 * @Date: 2020/7/30 0030 16:12
 * @Description:
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Override
    public String savePerson(Person person) {
        Person saveResult = personDao.save(person);
        System.out.println("saveResult结果:"+saveResult);
        if (ObjectUtil.isNotEmpty(saveResult)){
            return "保存成功";
        }
        return "保存失败";
    }

    @Override
    public String deleteByRy_id(String id) {
        //当id为非null并非空值时满足条件
        System.out.println(StrUtil.isNullOrUndefined(id));
        if(!StrUtil.isNullOrUndefined(id)){
            try{
                personDao.deleteById(id);
            }catch(EmptyResultDataAccessException e){
                e.printStackTrace();
                return "删除失败";
            }
            return "删除成功";
        }
        return "删除失败";
    }

    @Override
    public String updateByRy_Id(Person person) {
        Person saveResult = personDao.save(person);
        if (ObjectUtil.isNotEmpty(saveResult)){
            return "修改成功";
        }
        return "修改失败";
    }

    @Override
    public Person findPersonById(String id) {
        return personDao.findById(id).orElse(null);
    }
}

