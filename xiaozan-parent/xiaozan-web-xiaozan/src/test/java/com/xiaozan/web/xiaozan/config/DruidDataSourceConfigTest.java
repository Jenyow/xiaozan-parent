package com.xiaozan.web.xiaozan.config;

import org.junit.Test;

import com.alibaba.druid.filter.config.ConfigTools;

public class DruidDataSourceConfigTest {

	@Test
	public void testGetDruidPasswordRandom() throws Exception {
		 //密码明文
	    String password = "zhanyao";
	     
	    System.out.println("密码[ "+password+" ]的加密信息如下：\n");
	 
	    String [] keyPair = ConfigTools.genKeyPair(512);
	    //私钥
	    String privateKey = keyPair[0];
	    //公钥
	    String publicKey = keyPair[1];
	    //用私钥加密后的密文
	    password = ConfigTools.encrypt(privateKey, password);
	 
	    System.out.println("privateKey:"+privateKey);
	    System.out.println("publicKey:"+publicKey);
	    System.out.println("password:"+password);  
	    String decryptPassword=ConfigTools.decrypt(publicKey, password);
	    System.out.println("decryptPassword："+decryptPassword);
	}
	
	@Test
	public void testGetDruidPassword() throws Exception {
	    //公钥
	    String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMqE4gQ3B+/vqcXD7HsfTC3Oi8YYQKdDF8YdA4+KYWkoU3oK6fcT28YUmP+TWBzBPruKcERfDXQHo8hdm5TjXu0CAwEAAQ==";
	    //用私钥加密后的密文
	    String password = "CFP5peaTAHiVixoYeqram5gcNR8p/EjsY5Sm0g+XFtOUvTOXV/LawLaIivNhOtyFn9TMxISaIOYR9gaL6lDgIQ==";
	 
	    System.out.println("publicKey:"+publicKey);
	    System.out.println("password:"+password);  
	    String decryptPassword=ConfigTools.decrypt(publicKey, password);
	    System.out.println("decryptPassword："+decryptPassword);
	}

}
