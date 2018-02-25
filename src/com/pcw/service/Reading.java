package com.pcw.service;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class Reading {
	public static void toSound(String str){
		 ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");  
        try {  
            // ���� 0-100  
            sap.setProperty("Volume", new Variant(100));  
            // �����ʶ��ٶ� -10 �� +10  
            sap.setProperty("Rate", new Variant(-2));  
            // ��ȡִ�ж���  
            Dispatch sapo = sap.getObject();  
            // ִ���ʶ�  
            Dispatch.call(sapo, "Speak", new Variant(str));  
            // �ر�ִ�ж���  
            sapo.safeRelease();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            // �ر�Ӧ�ó�������  
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
