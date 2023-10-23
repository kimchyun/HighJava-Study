package kr.or.ddit.basic.enumTest;

/*
 enum(열거형)  ==>  서로 관련있는 상수들의 집합을 나타낸다.
               ==>  클래스처럼 보이게 하는 상수
만드는 방법  ==>  열거형은 클래스처럼 독립된 java파일에 만들 수 있고,
                  하나의 java파일에 클래스와 같이 만들 수 있고,
                  클래스 안에 내부 클래스처럼 만들 수 있다. 
                  
- 열거형의 속성 및 메서드
  name()    ==>  열거형 상수의 이름을 문자열로 반환한다.
  ordinal()	==>  열거형 상수가 정의된 순서값 (index값)을 반환한다. (0부터 시작)
  valueOf("열거형상수명")  ==>  지정된 열거형에서 '열거형상수명'과 일치하는 열거형 상수를 반환한다.
  
  열거형이름.values()  ==>  열거형에 선언된 모든 상수들을 배열로 묶어서 반환한다.
  열거형이름.상수명    ==>  valueOf()메서드와 결과가 같다.
                     
- 열거형 선언하기
 방법1)
   enum 열거형이름 { 상수명1, 상수명2, ...}
   
 방법2)  ==>  열거형 상수에 특정한 값을 지정해서 만들고 싶을 때...
   enum 열거형이름 {
       상수명1(값들...),
       상수명2(값들...),
       ...
       상수명n(값들...) ;
       
       // '값들'을 저장할 변수들을 선언한다.
       private 자료형이름 변수명1;
       private 자료형이름 변수명2;
        ...
        
       // 열거형의 생성자를 만든다.
       // 열거형의 생성자는 '열거형 상수'에 지정한 '값들'을 변수에 셋팅하는 역할을 수행한다. 
       // 열거형의 생성자는 묵시적으로 접근 제한자가 'private'이다.
       
       // 열거형의 생성자의 매개변수는 '값들'과 갯수가 같고 자료형이 맞아야 한다.
       private 열거형이름(자료형이름 변수명1, ... ){
          위에 선언된 변수들을 매개변수로 초기화 한다.
          ...
       }  
       
       // 구성된 '값들'을 외부에서 불러올 수 있는 getter메서드를 만든다.
        
      
         
   }
   
   
*/


public class EnumTest {
    public enum Color { RED, GREEN, BLUE }
    
    public enum Count { ONE, TWO, THREE }
    
    public enum Season { 
    	봄("3월부터 5월까지", 13), 
    	여름("6월부터 8월까지", 25), 
    	가을("9월부터 11월까지", 15), 
    	겨울("12월부터 2월까지", 1);
    	
    	// 값들이 저장될 변수 선언
    	private String span;
    	private int temp;
    	
    	// 생성자
    	Season(String span, int temp) {  // private Season(String span, int temp) { 와 같다.
    		this.span = span;
    		this.temp = temp;
    	}
    	
    	// getter메서드
    	public String getSpan() {
    		return span;
    	}
    	
    	public int getTemp() {
    		return temp;
    	}
    	
    	
    }
    
	public static void main(String[] args) {
		/*
		System.out.println("Red : " + ConstTest.RED);
		System.out.println("Three : " + ConstTest.THREE);

        if(ConstTest.RED == ConstTest.TWO) {
        	System.out.println(". . . .");
        } else {
        	System.out.println("####");
        }
        */
        
		Color mycol = Color.valueOf("GREEN");  // Color.GREEN 와 같다.
		Count mycnt = Count.ONE;       // Count.valueOf("ONE")와 같다.
		
		System.out.println("mycol : " + mycol.name());
		System.out.println("mycnt : " + mycnt.name());
        System.out.println();
        
		System.out.println("mycol odinal ==> " + mycol.ordinal());
		System.out.println("mycnt odinal ==> " + mycnt.ordinal());
		
		/*
		// 서로 다른 종류의 열거형끼리의 비교는 불가하다.
		if(mycol == mycnt) {
			System.out.println("같다. . .");
		}
		*/
		if(mycol == Color.BLUE) {
			System.out.println("같다");
		} else {
			System.out.println("다르다");
		}
		
		
		// 열거형은 switch문으로 비교할 수 있다.
		// switch문의 case에 지정하는 상수값은 '열거형이름'을 생략한 '상수이름'만 지정해야 한다.
		switch(mycnt) {
		  
		  //case Count.ONE : System.out.println("ONE 입니다."); break;   // 오류 ('열거형이름'을 생략해야 한다.)
		  case ONE : System.out.println("ONE 입니다."); break;
		  case TWO : System.out.println("TWO 입니다."); break;
		  case THREE : System.out.println("THREE 입니다."); break;
		}
		
		System.out.println("----------------------------------");
		
		Season ss = Season.valueOf("봄");
		System.out.println("name : " + ss.name());
		System.out.println("ordinal : " + ss.ordinal());
		System.out.println("span : " + ss.getSpan());
		System.out.println("temp : " + ss.getTemp());
		System.out.println();
		
		for(Season time : Season.values()) {
			System.out.println(time + " == " + time.name() + " -- > " + time.getSpan() + ", 평균기온 : " + time.getTemp() + "도");
		}
	}
}

// 하나의 java파일에 클래스와 같이 만든 열거형
enum TestEnum2{
}