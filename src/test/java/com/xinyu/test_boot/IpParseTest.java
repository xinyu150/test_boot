package com.xinyu.test_boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xinyu.test_boot.helper.GsonUtil;
import com.xinyu.test_boot.helper.IpFilterService;
import com.xinyu.test_boot.helper.IpTableItem;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IpParseTest {
	
	
	
	@Autowired
    private IpFilterService IpFilterService;
	
	@Test
	public void test() {
		IpTableItem iptableItem = IpFilterService.findTableItemByIp("221.181.101.37");
		System.out.println("1" + GsonUtil.toJson(iptableItem));
		test2(); 
	}

	@Test
	public void test2() {
		IpTableItem iptableItem = IpFilterService.findTableItemByIp("221.181.101.37");
		System.out.println("2" + GsonUtil.toJson(iptableItem));
	}
}
