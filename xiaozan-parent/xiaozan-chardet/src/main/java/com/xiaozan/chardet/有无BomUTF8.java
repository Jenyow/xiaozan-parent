package com.xiaozan.chardet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class 有无BomUTF8 {
	/** 
	     * 生成UTF-8文件. 
	     * 如果文件内容中没有中文内容，则生成的文件为ANSI编码格式； 
	     * 如果文件内容中有中文内容，则生成的文件为UTF-8编码格式。 
	     * @param fileName 待生成的文件名（含完整路径） 
	     * @param fileBody 文件内容 
	     * @return 
	     */  
	    public static boolean writeUTFFile(String fileName,String fileBody){  
	        FileOutputStream fos = null;  
	        OutputStreamWriter osw = null;  
	        try {  
	            fos = new FileOutputStream(fileName);  
	            osw = new OutputStreamWriter(fos, "UTF-8");  
	            osw.write(fileBody);  
	            return true;  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	            return false;  
	        }finally{  
	            if(osw!=null){  
	                try {  
	                    osw.close();  
	                } catch (IOException e1) {  
	                    e1.printStackTrace();  
	                }  
	            }  
	            if(fos!=null){  
	                try {  
	                    fos.close();  
	                } catch (IOException e1) {  
	                    e1.printStackTrace();  
	                }  
	            }  
	        }  
	    }  
	  
	//main()  
	public static void main(String[] argc){  
	        writeUTFFile("D:/DATA/ftpTMP/test1.txt","aaa");//test1.txt为ANSI格式文件  
	        writeUTFFile("D:/DATA/ftpTMP/test2.txt","中文aaa");//test2.txt为UTF-8格式文件  
	}  
}

