package com.xinyu.test_boot.helper;

import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

public class IpTableItem {
	String startIp;
	String endIp;
	long start;
	long end;
	String regionL1;
	String regionL2;
	String regionL3;
	String regionL4;
	String regionL5;
	String operator;
	String zipCode;
	String country;
	String conntryShort;
	double longtitude;
	double latitude;

	@Override
	public String toString() {
		return "IpTableItem [startIp=" + startIp + ", endIp=" + endIp + ", start=" + start + ", end=" + end + ", regionL1=" + regionL1 + ", regionL2="
				+ regionL2 + ", regionL3=" + regionL3 + ", regionL4=" + regionL4 + ", regionL5=" + regionL5 + ", operator=" + operator + ", zipCode="
				+ zipCode + ", country=" + country + ", conntryShort=" + conntryShort + ", longtitude=" + longtitude + ", latitude=" + latitude + "]";
	}

	public static IpTableItem createFromString(String s) {
		IpTableItem item = new IpTableItem();

		int i = 0;
		String[] parts = StringUtils.split(s, "|");
		while (parts != null) {
			String part = parts[0];
			switch (i) {
			case 0:
				item.startIp = part;
				break;
			case 1:
				item.endIp = part;
				break;
			case 2:
				item.start = NumberUtils.parseNumber(part, Long.class);
				break;
			case 3:
				item.end = NumberUtils.parseNumber(part, Long.class);
				break;
			case 4:
				item.regionL1 = part;
				break;
			case 5:
				item.regionL2 = part;
				break;
			case 6:
				item.regionL3 = part;
				break;
			case 7:
				item.regionL4 = part;
				break;
			case 8:
				item.regionL5 = part;
				break;
			case 9:
				item.operator = part;
				break;
			case 10:
				item.zipCode = part;
				break;
			case 11:
				item.country = part;
				break;
			case 12:
				item.conntryShort = part;
				break;
			case 13:
				item.longtitude = NumberUtils.parseNumber(part, Double.class);
				break;
			case 14:
				item.latitude = NumberUtils.parseNumber(part, Double.class);
				break;
			}
			if(i==13) {
				parts[0]=parts[1];
			}else {
				parts = StringUtils.split(parts[1], "|");
			}
			i++;
		}
		return item;
	}

	public boolean includeIp(long ip) {
		return (this.getStart() <= ip && this.getEnd() >= ip);
	}

	public String getStartIp() {
		return startIp;
	}

	public void setStartIp(String startIp) {
		this.startIp = startIp;
	}

	public String getEndIp() {
		return endIp;
	}

	public void setEndIp(String endIp) {
		this.endIp = endIp;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	public String getRegionL1() {
		return regionL1;
	}

	public void setRegionL1(String regionL1) {
		this.regionL1 = regionL1;
	}

	public String getRegionL2() {
		return regionL2;
	}

	public void setRegionL2(String regionL2) {
		this.regionL2 = regionL2;
	}

	public String getRegionL3() {
		return regionL3;
	}

	public void setRegionL3(String regionL3) {
		this.regionL3 = regionL3;
	}

	public String getRegionL4() {
		return regionL4;
	}

	public void setRegionL4(String regionL4) {
		this.regionL4 = regionL4;
	}

	public String getRegionL5() {
		return regionL5;
	}

	public void setRegionL5(String regionL5) {
		this.regionL5 = regionL5;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getConntryShort() {
		return conntryShort;
	}

	public void setConntryShort(String conntryShort) {
		this.conntryShort = conntryShort;
	}

	public double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
}
