package com.xinyu.test_boot.controller;

import com.xinyu.test_boot.bean.PersonBean;
import com.xinyu.test_boot.service.PersonService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="person")
public class PersonController {

    @Resource
    private PersonService personService;

    @RequestMapping(value="getMessage", method=RequestMethod.GET)
    private PersonBean getMessage(String id){
        PersonBean personBean = personService.getMessage(id);
        return personBean;
    }

    @RequestMapping(value="addPersion", method=RequestMethod.POST)
    private PersonBean addPersion(Map<String,String> map){
        String name = map.get("name");
        String age = map.get("age");
        PersonBean personBean = new PersonBean();
        personBean.setName(name);
        personBean.setAge(age);
        int cnt = personService.addPersion(personBean);
        return personBean;
    }

    @RequestMapping(value="updatePerson", method=RequestMethod.POST)
    private String updatePerson(Map<String,String> map){
        String id = map.get("id");
        int int_id = Integer.valueOf(id);
        String name = map.get("name");
        String age = map.get("age");
        PersonBean personBean = new PersonBean();
        personBean.setId(int_id);
        personBean.setName(name);
        personBean.setAge(age);
        int cnt = personService.updatePerson(personBean);
        return cnt>0?"成功！":"失败！";
    }

    @RequestMapping(value="deletePerson", method=RequestMethod.GET)
    private String deletePerson(String id){
        int cnt= personService.deletePerson(id);
        return cnt>0?"成功！":"失败！";
    }

    @RequestMapping(value="getAllMessage", method=RequestMethod.GET)
    private List<PersonBean> getAllMessage(){
        List<PersonBean> list = new ArrayList<>();
        list = personService.getAllMessage();
        return list;
    }

}
