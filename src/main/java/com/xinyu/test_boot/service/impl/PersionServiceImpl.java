package com.xinyu.test_boot.service.impl;

import com.xinyu.test_boot.bean.PersonBean;
import com.xinyu.test_boot.dao.PersonDao;
import com.xinyu.test_boot.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

@Service
public class PersionServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Override
    public PersonBean getMessage(String persionId) {
        PersonBean personBean = personDao.getMessage(persionId);
        return personBean;
    }

    @Override
    public int addPersion(PersonBean bean) {
        int cnt = personDao.addPersion(bean);
        return cnt;
    }

    @Override
    public int updatePerson(PersonBean bean) {
        int cnt = personDao.updatePerson(bean);
        return cnt;
    }

    @Override
    public int deletePerson(String personId) {
    	int int_id = Integer.valueOf(personId);
        int cnt = personDao.deletePerson(int_id);
        return cnt;
    }

    @Override
    public List<PersonBean> getAllMessage() {
        List<PersonBean> list = personDao.getAllMessage();
        return list;
    }
}
