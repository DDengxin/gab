package com.tengzhi.business.demo.staff.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.business.demo.staff.model.Person;
import com.tengzhi.business.demo.staff.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: 黄彪
 * @Date: 2020/7/30 0030 17:19
 * @Description: 人员档案控制层
 */

@RestController
@RequestMapping(value = "/person",method = RequestMethod.GET)
public class PersonController {

    private final PersonService personService;

    @Autowired
    private PersonController(PersonService personService){
        this.personService = personService;
    }

    @RequestMapping(value = "/savePerson",method = RequestMethod.POST)
    public String save(Person person){
        return personService.savePerson(person);
    }

    @RequestMapping(value = "/deletePerson",method = RequestMethod.DELETE)
    public String deletePersonById(BaseDto baseDto){
        System.out.println("id结果:"+baseDto.getParams());
        System.out.println("baseDto.getParamsMap().get(id):"+baseDto.getParamsMap().get("id"));
        return personService.deleteByRy_id(baseDto.getParamsMap().get("id"));
    }

    @RequestMapping(value = "/updatePerson",method = RequestMethod.POST)
    public String UpdatePerson(@RequestBody Person person){
        return personService.updateByRy_Id(person);
    }

    @RequestMapping(value = "/findPerson/{id}",method = RequestMethod.GET)
    public Person findPersonById(@PathVariable String id){
        return personService.findPersonById(id);
    }

}
