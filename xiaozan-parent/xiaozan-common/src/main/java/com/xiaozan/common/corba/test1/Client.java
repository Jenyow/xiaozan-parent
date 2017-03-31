package com.xiaozan.common.corba.test1;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import com.xiaozan.common.corba.test1.getMessage.GetIt;
import com.xiaozan.common.corba.test1.getMessage.GetItHelper;

public class Client {

	private static GetIt getItImpl;
	
	public static void main1(String[] args) {
		try {
			// 创建和初始化 ORB
			ORB orb = ORB.init(args, null);
			// 获取根命名上下文
			// NameService 调用名称服务
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			// 解析命名中的对象引用，注意这里的名称和服务器中的相同
			String name = "GetIt";
			getItImpl = GetItHelper.narrow(ncRef.resolve_str(name));
			System.out.println("Obtained a handle on server object: ");
			System.out.println(getItImpl.returnString(args[0]));
		} catch (Exception e) {
			System.out.println("ERROR:" + e);
			e.printStackTrace(System.out);
		}
	}
}
