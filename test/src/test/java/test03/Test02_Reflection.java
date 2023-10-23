package test.java.test03;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**	Reflection
 * - String -> 특정 Class 타입 객체 생성 -> Class<T>
 * - String -> Class<T> -> 객체생성
 * - String -> Class<T>, Constructor -> 객체생성
 * - String -> Class<T> -> Field -> 속성접근 및 객체의 속성조회
 * - String -> Class<T> -> Method -> 함수접근 및 함수호출
 * 
 * @author PC-01
 *
 */

public class Test02_Reflection {
	public static void main(String[] args) throws Exception {
		
		// String -> 특정 Class 타입 객체 생성 -> Class<T>
		// 클래스명 : test.java.test03.Test02_Reflection
		Class<?> forName = Class.forName("test.java.test03.MemberVO");
		
		// String -> Class<T> -> 객체생성
		{
			Object newInstance = forName.newInstance();	// new MemberVO
			System.out.println(newInstance);
		}
		
		// String -> Class<T>, Constructor -> 객체생성
		{
			// public MemberVO() {}
			Constructor<?> const1 = forName.getDeclaredConstructor();
			Object newInstance = const1.newInstance();	// new MemberVO()
			System.out.println(newInstance);
			
			// public MemberVO(String memNo, String memName, int memBirthYear)
			Constructor<?> const2 = forName.getDeclaredConstructor(String.class, String.class, int.class);
			Object newInstance2 = const2.newInstance("a001", "김은대", 1990);	// new MemberVO()
			System.out.println(newInstance2);
			
		}
		
		// String -> Class<T> -> Field -> 속성접근 및 객체의 속성조회
		{
			Constructor<?> const2 = forName.getDeclaredConstructor(String.class, String.class, int.class);
			Object newInstance2 = const2.newInstance("a001", "김은대", 1990);	// new MemberVO()
			
			// memNo, memName, memBirthYear
			Field field = forName.getDeclaredField("memName");	// private String memName;
			// memName -> 김은대 -> MemberVO memVO -> memVO.memName()
			field.setAccessible(true);
			Object object = field.get(newInstance2);	// [memVO].memName
			System.out.println(object);
			
			field.set(newInstance2, "홍길동");
			field.setAccessible(false);
			System.out.println(newInstance2);
		
		}
		
		// String -> Class<T> -> Method -> 함수접근 및 함수호출
		{
			Constructor<?> const2 = forName.getDeclaredConstructor(String.class, String.class, int.class);
			Object newInstance2 = const2.newInstance("a001", "김은대", 1990);
			
			// memVO.setMemName("홍길순);
			// forName.getDeclaredMethod("메소드명", 파라미터타입들)
			//	public void setMemName(String memName)
			Method method = forName.getDeclaredMethod("setMemName", String.class);
			method.invoke(newInstance2, "홍길순");	// memVO.setMemName("홍길순")
			
			System.out.println(newInstance2);
		}
		
	}

}
