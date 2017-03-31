package com.xiaozan.common.corba.test1;

import org.omg.CORBA.ORB;

import com.xiaozan.common.corba.test1.getMessage.GetItPOA;

public class GetItImpl extends GetItPOA {

	private ORB orb;
	
	public void setOrb(ORB orb) {
		this.orb = orb;
	}
	
	@Override
	public String returnString(String str) {
		
		return "\nHello " + str;
	}

}
