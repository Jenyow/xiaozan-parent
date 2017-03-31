package com.xiaozan.common.corba.test1;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import com.xiaozan.common.corba.test1.getMessage.GetIt;
import com.xiaozan.common.corba.test1.getMessage.GetItHelper;

public class Server {

	public static void main1(String[] args) {
		try {
			// 创建和初始化 ORB
			ORB orb = ORB.init(args, null);
			// 取得对 rootpoa 对象的引用并激活 POAManager
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();
			// 创建服务对象并将其向 ORB 注册
			GetItImpl getItImpl = new GetItImpl();
			getItImpl.setOrb(orb);
			// 取得对象引用
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(getItImpl);
			GetIt href = GetItHelper.narrow(ref);
			// 获取根命名上下文
			// NameService 调用名称服务
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			// 绑定命名中的对象引用
			String name = "GetIt";
			NameComponent path[] = ncRef.to_name(name);
			ncRef.rebind(path, href);
			System.out.println("GetItServer ready and waiting ...");
			// 等待来自客户机的调用
			orb.run();
		} catch (Exception e) {
			System.out.println("ERROR:" + e);
			e.printStackTrace(System.out);
		}
		System.out.println("GetItServer Exiting ...");
	}
}
