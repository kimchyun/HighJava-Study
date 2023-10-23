package kr.or.ddit.basic;

import java.util.Properties;

public class PropertiesTest {
    /*
        Properties객체는 Map보다 축소된 기능의 객체라고 할 수 있다.
        
        Map객체는 key값과 value값에 모든 형태의 객체를 사용할 수 있지만,
        Properties객체는 key값과 value값에 String만 사용할 수 있다.
        주로 프로그램의 환경 설정에 필요한 정보를 관리하는 용도로 사용한다.
        
        Map객체는 put(), get()메서드를 이용하여 데이터를 입출력하지만
        Properties객체는 setProperties(), getProperty()메서드를 이용해서 데이터를 입출력한다.
        
        Properties객체는 데이터를 파일로 입출력할 수 있다.
    */
	public static void main(String[] args) {
		Properties prop = new Properties();
		
		// 데이터 저장
		prop.setProperty("name", "홍길동");
		prop.setProperty("age", "20");
		int age = 30;
		prop.setProperty("age2", age + "");
		prop.setProperty("age3", String.valueOf(age));
		prop.setProperty("tel", "010-1111-1111");
		prop.setProperty("addr", "대전시 중구 오류동");
		// --------------------------------------
		
		String name = prop.getProperty("name");
		int tempAge = Integer.parseInt(prop.getProperty("age"));
		String tel = prop.getProperty("tel");
		String addr = prop.getProperty("addr");
		
		System.out.println("이름 : " + name);
		System.out.println("나이 : " + age);
		System.out.println("전화 : " + tel);
		System.out.println("주소 : " + addr);
	}
}
