package com.xinyu.test_boot.helper;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.NumberUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class IpFilterService {
	
	List<IpTableItem> ipTableItems = new ArrayList<IpTableItem>();

	boolean inited = false;

	public synchronized void initIpTable() {
		try {
			String ipSource = "C:\\Users\\Administrator\\Desktop\\test\\iptables.txt";
//			String ipSource = "iptables.txt";
//			ClassPathResource classPathResource = new ClassPathResource(ipSource);
//			// 获得File对象，当然也可以获取输入流对象
//			File file = classPathResource.getFile();
			List<String> lines = FileUtils.readLines(new File(ipSource), "GBK");
//			List<String> lines = FileUtils.readLines(file, "GBK");
			System.out.println("read " + lines.size() + " from ip table.");
			if (lines.size() > 0) {
				ipTableItems.clear();
				for (String line : lines) {
					IpTableItem item = IpTableItem.createFromString(line);
					ipTableItems.add(item);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		inited = true;
	}

	public IpTableItem findTableItemByIp(String ip) {
		System.out.println("查找IP: " + ip + " 所属的地址====");
		if (inited == false)
			System.out.println("未初始化");
			initIpTable();

		long ipNumber = getIpNumber(ip);

		int start = 0;
		int end = ipTableItems.size() - 1;
		int pos = (start + end) / 2;

		IpTableItem item = null;
		while (start < end) {
			item = ipTableItems.get(pos);
			if (item.includeIp(ipNumber))
				return item;

			if (item.getStart() > ipNumber) {
				end = pos;
			}
			if (item.getStart() < ipNumber) {
				start = pos;
			}
			pos = (start + end) / 2;
			if (pos == start) { // 最后一轮，需要考虑End的特殊情况
				item = ipTableItems.get(end);
				if (item.includeIp(ipNumber)) {
					return item;
				} else {
					return null;
				}
			}
		}
		if(null!=item){
			System.out.println("item : " + item);
			if (item.getStart() <= ipNumber && item.getEnd() >= ipNumber) {
				return item;
			}
		}
		return null;
	}

	public boolean filter(String ip) {
		IpTableItem item = findTableItemByIp(ip);
		if (item == null)
			return false;
		if ("CN".equals(item.getConntryShort())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean filterByCountries(String ip, String countries) {
		String[] cs = countries.split(",");
		return filterByCountries(ip, cs);
	}

	public boolean filterByCountries(String ip, String[] countries) {
		if (ip.startsWith("10."))
			return true;
		if (ip.startsWith("192.168."))
			return true;

		IpTableItem item = findTableItemByIp(ip);
		if (item == null)
			return false;
		String c = item.country;
		for (int i = 0; i < countries.length; i++) {
			if (c.equalsIgnoreCase(countries[i]))
				return true;
		}
		return false;
	}

	public long getIpNumber(String ip) {
		String[] parts = ip.split("\\.");
		if (parts.length != 4) {
			return -1;
		}
		long v = (NumberUtils.parseNumber(parts[0], Long.class) << 24) + (NumberUtils.parseNumber(parts[1], Long.class) << 16)
				+ (NumberUtils.parseNumber(parts[2], Long.class) << 8) + NumberUtils.parseNumber(parts[3], Long.class);
		return v;
	}

	public static void main(String[] args){
		IpFilterService main = new IpFilterService();
		main.initIpTable();
		String ip = "40.94.97.37";
		IpTableItem ipTableItem = main.findTableItemByIp(ip);
//		boolean flag = ipTableItem != null;
		Assert.notNull(ipTableItem, "ipTableTem为空！");
		System.out.println(GsonUtil.toJson(ipTableItem));
	}

}
