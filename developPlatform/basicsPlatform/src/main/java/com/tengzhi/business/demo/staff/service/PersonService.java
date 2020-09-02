package com.tengzhi.business.demo.staff.service;

import com.tengzhi.business.demo.staff.model.Person;

/**
 * @Auther: 黄彪
 * @Date: 2020/7/30 0030 16:17
 * @Description:
 */

public interface PersonService {

    String savePerson(Person person);

    String deleteByRy_id(String id);

    String updateByRy_Id(Person person);

    Person findPersonById(String id);

}
