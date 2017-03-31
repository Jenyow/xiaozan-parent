package com.xiaozan.common.corba.test2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import com.xiaozan.common.corba.test2.getMessage.GetIt;
import com.xiaozan.common.corba.test2.getMessage.GetItHelper;

public class Server {  
    public static void main1(String[] args) throws InvalidName, AdapterInactive, ServantNotActive, WrongPolicy, IOException {  
          
    	// 创建和初始化 ORB
		ORB orb = ORB.init(args, null);
		// 取得对 rootpoa 对象的引用并激活 POAManager
		POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
		rootpoa.the_POAManager().activate();
        //创建实现类的实例  
		GetItImpl getItImpl = new GetItImpl();
        //调用DemoImpl的父类CorbaDPOA方法_this，获得目标对象的引用  
		getItImpl.setOrb(orb);
		// 取得对象引用
		org.omg.CORBA.Object ref = rootpoa.servant_to_reference(getItImpl);
		GetIt href = GetItHelper.narrow(ref);
          
        //创建ior文件  
        File file = new File("my.ior");  
        file.createNewFile();  
        PrintWriter writer = new PrintWriter(new FileWriter(file));  
        //具体实现转化成字符串写入ior文件  
        writer.println(orb.object_to_string(href));  
        writer.close();  
          
        //激活 POA，使客户机请求开始排队，并强制服务器输入其事件循环，以接收这些传入的请求  
        rootpoa.the_POAManager().activate();  
        System.out.println("server start...");  
        orb.run();  
    }  
} 