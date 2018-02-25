package com.pcw.service;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class Reading {
	public static void toSound(String str){
		 ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");  
        try {  
            // 音量 0-100  
            sap.setProperty("Volume", new Variant(100));  
            // 语音朗读速度 -10 到 +10  
            sap.setProperty("Rate", new Variant(-2));  
            // 获取执行对象  
            Dispatch sapo = sap.getObject();  
            // 执行朗读  
            Dispatch.call(sapo, "Speak", new Variant(str));  
            // 关闭执行对象  
            sapo.safeRelease();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭应用程序连接  
            sap.safeRelease();  
        }
	}
/*	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while(true){
			String str = s.nextLine();
			toSound(str);
		}
	}*/
}
