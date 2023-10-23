package test.java.test01;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class TestEncoding {
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		String str = "한글";
		
		byte[] bytes = str.getBytes();	// UTF-8
		byte[] bytes1 = str.getBytes("UTF-8");	// UTF-8
		byte[] bytes2 = str.getBytes("euc-kr");	// UTF-8
		
		System.out.println(bytes.length);
		System.out.println(bytes1.length);
		System.out.println(bytes2.length);
		
		Properties properties = System.getProperties();
		for(Object o : properties.keySet()) {
			if(o.toString().toLowerCase().contains("encoding")) {
				System.out.println(o + " : " + properties.getProperty((String)o));
				
			}
		}
	}
}
