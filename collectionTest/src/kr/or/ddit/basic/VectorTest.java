package kr.or.ddit.basic;

import java.util.Vector;

public class VectorTest {

	public static void main(String[] args) {
		// 객체 생성
		Vector v1 = new Vector();
		
		System.out.println("처음 크기 : " + v1.size());
		
		// 데이터 추가하기 1 : add(추가할 데이터)
		// 반환값 : 추가성공(true), 추가실패(false)
		v1.add("aaaaa");
		v1.add(new Integer(100));
		v1.add(123);     // 오토박싱 (자동으로 객체화)  // 오토언박싱 기능이 지원된다.
		v1.add('a');
		v1.add(true);
		boolean r = v1.add(3.14);
		
		System.out.println("현재 크기 : " + v1.size());
        System.out.println("반환값 : " + r);
        System.out.println("v1 => " + v1.toString());
        System.out.println("v1 => " + v1);
        System.out.println();
        
        // 데이터 추가하기 2 : addElement(추가할 데이터)
        // ==> 이전 버전의 프로그램의 호환성을 위해서 남아있는 메서드
        v1.addElement("CCCC");
        System.out.println("v1 => " + v1);
        
        // 데이터 추가하기 3 : add(index, 데이터)
        // ==> 'index'번째에 '데이터'를 끼워 넣는다.
        // ==> 'index'는 0부터 시작한다. 
        // ==> 반환값은 없다.
        v1.add(1, "KKKK");   // 원래 위치하고 있던 데이터는 뒤로 이동함
        System.out.println("v1 => " + v1);
        
        
        // 데이터 꺼내오기 : get(index)
        // ==> 'index'번째 데이터를 꺼내서 반환한다.
        System.out.println("0번째 데이터 : " + v1.get(0)); 
        int iTemp = (int)v1.get(2);  //int iTemp = v1.get(2); 에서 에러가 나는 이유는 자동 형변환이 되지 않았기 때문이다.
        System.out.println("2번째 데이터 : " + iTemp);
        System.out.println();
        
        // 데이터 수정하기 : set(index, 새로운 데이터)
        // ==> 'index'번째 데이터를 '새로운 데이터'로 변경한다.
        // ==> 반환값 : 원래의 데이터
        String sTemp = (String)v1.set(0, "zzzzz");
        System.out.println("v1 => " + v1);
        System.out.println("원래의 데이터 : " + sTemp);
        
        // 데이터 삭제하기 1 : remove(index)
        // ==> 'index'번째 데이터를 삭제한다.
        // ==> 반환값 : 삭제된 데이터
        sTemp = (String)v1.remove(0); 
        System.out.println("삭제 후 v1 => " + v1);   
        System.out.println("삭제된 데이터 : " + sTemp);
        
        // 데이터 삭제하기 2 : remove(삭제할 데이터)
        // ==> '삭제할 데이터'를 찾아서 삭제한다.
        // ==> '삭제할 데이터'가 여러개이면 이 중에 첫번째 자료가 삭제된다.
        // ==> 반환값 : 삭제성공(true), 삭제실패(false)
        // ==> '삭제할 데이터'가 '정수형'이거나 'char형'일 경우에는 반드시 객체로 변환해서 사용해야 한다.
        
        v1.remove("CCCC");
        System.out.println("삭제 후 v1 => " + v1);
        
        
        // v1.remove(123);             // ==> 123을 index로 판단해서 오류 발생
        
        //v1.remove(new Integer(123));    // 123을 객체화 해서 데이터로 주어야 오류가 발생하지 않는다.
        v1.remove(Integer.valueOf(123));  // 객체화하는 방법 2
        
        System.out.println("123 삭제 후 v1 => " + v1);
        
        //v1.remove('a');
        v1.remove(new Character('a'));
        System.out.println("'a' 삭제 후 v1 => " + v1);
        
        v1.remove(true);
        System.out.println("삭제 후 v1 => " + v1);
        
        v1.remove(3.14);
        System.out.println("삭제 후 v1 => " + v1);
        
        // 전체 데이터 삭제하기 : clear();
        v1.clear();
        System.out.println("clear 삭제 후 v1 ==> " + v1);
        System.out.println();
        
        /*
        제네릭 타입 (Generic Type) ==> 클래스 내부에서 사용할 데이터의 타입을 객체를 생성할 때 
                     외부에서 지정해 주는 기법으로 객체를 선언할 때 <>괄호 안에 그 객체의 내부에서
                     사용할 데이터의 타입을 정해주는 것을 말한다.
           - 이런 식으로 선언하게 되면 그 지정된 데이터 타입 이외의 다른 종류의 데이터를 저장할 수 없다.
           - 이 때 제네릭으로 선언 될 수 있는 데이터 타입은 클래스형 이어야 한다.
             그래서 int형은 Integer, boolean은 Boolean, char는 Character 등으로 대체해서 사용해야 한다.
           - 제네릭 타입으로 선언하게 되면 데이터를 꺼내올 때도 별도의 형변환이 필요없다.         
        */
        Vector<Integer> v2 = new Vector<>();   // int형 자료만 저장할 수 있는 벡터
        Vector<String> v3 = new Vector<>();    // String형만 저장할 수 있는 벡터
        
        v3.add("안녕하세요");
        //v3.add(100);        // 오류 : 다른 종류의 데이터를 저장할 수 없다.
        //v2.add("ABCD");       // 오류
        
        String sTemp2 = v3.get(0);     // 형변환 없이 데이터를 꺼내와 사용할 수 있다.
        
        
        Vector<Vector> vv = new Vector<>();   // 2차원 배열
        Vector<Vector<Vector>> vvv = new Vector<>();   // 3차원 배열
        System.out.println();
        System.out.println("------------------------------------");
        
        v3.clear();
        System.out.println("v3의 size = " + v3.size());
        
        v3.add("AAA");
        v3.add("BBB");
        v3.add("CCC");
        v3.add("DDD");
        v3.add("EEE");
        
        Vector<String> v4 = new Vector<>();
        v4.add("BBB");
        v4.add("EEE");
        
        System.out.println("v3 => " + v3);
        System.out.println("v4 => " + v4);
        
        // 데이터 삭제하기 3 : removeAll(Collection객체)
        // ==> 벡터의 데이터 중에서 'Collection객체'가 가지고 있는 모든 데이터를 삭제한다.
        // ==> 반환값 : 삭제 성공(true), 삭제 실패(false)
        v3.removeAll(v4);   // v3의 데이터들 중에서 v4가 가지고 있는 데이터와 같은 데이터들을 모두 삭제한다.
        System.out.println("삭제 후 v3 => " + v3);  
        System.out.println();
        System.out.println("------------------------------------");
        
        v3.clear();
        
        v3.add("AAA");
        v3.add("BBB");
        v3.add("CCC");
        v3.add("DDD");
        v3.add("EEE");
        
        // 벡터의 데이터들을 순서대로 모두 가져와 사용하기
        // 이 때는 반복문을 사용하면 된다. (주로 for문)
        for(int i=0; i<v3.size(); i++) {
        	System.out.println(i + "번째 자료 :" + v3.get(i));
        }
        System.out.println("------------------------------------");
        
        // 향상된 for문
        for(String temp : v3) {
        	System.out.println(temp);
        } 
	}
}
