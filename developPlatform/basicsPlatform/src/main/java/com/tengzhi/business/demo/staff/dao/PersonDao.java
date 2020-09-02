package com.tengzhi.business.demo.staff.dao;

import com.tengzhi.business.demo.staff.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @Auther: 黄彪
 * @Date: 2020/7/30 0030 16:01
 * @Description:
 */

public interface PersonDao extends JpaRepository<Person,String> {

}
