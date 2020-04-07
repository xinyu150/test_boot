package com.xinyu.test_boot.dao;


import com.xinyu.test_boot.bean.PersonBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PersonDao {

    /**
     * 获取一条信息
     * @param id
     * @return
     */
    PersonBean getMessage(String id);

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
     * @param id
     * @return
     */
    int deletePerson(int id);

    /**
     * 获取所有的信息
     * @return
     */
    List<PersonBean> getAllMessage();

}
