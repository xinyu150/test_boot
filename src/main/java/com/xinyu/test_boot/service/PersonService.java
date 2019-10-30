package com.xinyu.test_boot.service;

import com.xinyu.test_boot.bean.PersonBean;

import java.util.List;

public interface PersonService {

    /**
     * 获取一条信息
     * @param persionId
     * @return
     */
    PersonBean getMessage(String persionId);

    /**
     * 添加信息
     */
    int addPersion(PersonBean bean);

    /**
     * 修改信息
     * @param bean
     * @return
     */
    int updatePerson(PersonBean bean);

    /**
     * 删除一条信息
     * @param personId
     * @return
     */
    int deletePerson(String personId);

    /**
     * 获取所有的信息
     * @return
     */
    List<PersonBean> getAllMessage();
}
