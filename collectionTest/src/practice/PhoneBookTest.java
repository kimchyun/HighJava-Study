package practice;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;


/*
추가 조건
1) '6. 전화번호 저장'메뉴를 추가하고 구현한다.
(저장파일명은 'phoneData.dat'로 한다.)

2) 이 프로그램이 시작될 때 저장된 파일이 있으면 그 파일의 데이터를 읽어와 Map에 셋팅한다.

3) 이 프로그램이 종료될 때 Map의 데이터가 변경(추가, 수정, 삭제) 되었으면 저장 후 종료되도록 한다.

*/


public class PhoneBookTest {
   private HashMap<String, Phone> phoneBookMap;
   private Scanner scan;
   
   private final String fileName = "d:/d_other/phoneData.dat";
   
   // 데이터가 변경되었는지 여부를 나타내는 변수   ==> 데이터가 변경되면 이 변수값이 true가 된다.
   private boolean dataChange;
   
   // 생성자
   public PhoneBookTest() {
	   scan = new Scanner(System.in);
//	   phoneBookMap = new HashMap<>();
	   
	   phoneBookMap = load();   // 파일 내용을 읽어와 Map객체에 저장한다.
	   
	   if(phoneBookMap == null) {    // 파일이 없거나 잘못되었을 때
		   phoneBookMap = new HashMap<>();
	   }
   }
   
   public static void main(String[] args) {
	  new PhoneBookTest().phoneBookStart();
   }
   
   // 프로그램을 시작하는 메서드
   public void phoneBookStart() {
	   System.out.println("++++++++++++++++++++++++++++++++");
	   System.out.println(" 전 화 번 호 관 리 프 로 그 램");
	   System.out.println("++++++++++++++++++++++++++++++++");
	   System.out.println();
	   
	   
	   while(true) {
		   int choice = displayMenu();
		   
		   switch(choice) {
		       case 1 :  // 등록
		    	  insert(); break;
		       case 2 :  // 수정
                  update(); break;
		       case 3 :  // 삭제
				  delete(); break;
		       case 4 :  // 검색
		    	  search(); break;
		       case 5 :  // 전체출력
				  displayAll(); break;
		       case 6 :  // 저장
			      save(); break;
		       case 0 :  // 종료
		    	   if(dataChange) {   // 데이터의 변경 여부를 검사한다.
		    		   save();
		    	   }
		    	   
		    	   System.out.println();
				   System.out.println("프로그램을 종료합니다. . .");
				   return;
			   default :
				   System.out.println();
				   System.out.println("작업 번호를 선택했습니다. 다시 선택하세요. . .");
		   }
	   }
   }
   
   // 파일로 저장된 전화번호 정보를 읽어와서 반환하는 메서드  ==> 읽어올 파일이 없으면 null반환
   private HashMap<String, Phone> load() {
	   HashMap<String, Phone> pMap = null;    // 읽어온 객체를 저장할 변수
	   
	   File file = new File(fileName);
	   if(!file.exists()) {   // 저장된 파일이 없으면...
		   return null;
	   }
	   
	   ObjectInputStream oin = null;
	   try {
		   // 입력용 스트림 객체 생성
		   oin = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
		   
		   // 파일에 저장된 객체 읽어오기
		   pMap = (HashMap<String, Phone>)oin.readObject();
		   
	} catch (IOException e) {
		return null;
	} catch (ClassNotFoundException e) {
		return null;
	} finally {
		// 사용했던 스트림 닫기
		if(oin != null) try { oin.close(); } catch(IOException e) {}
	}
	   
	return pMap;
	   
   }
   
   
   
   
   // 전화번호 정보를 파일로 저장하는 메서드
   private void save( ) {
	   ObjectOutputStream oout = null;
	   try {
		   // 객체 출력용 스트림 객체 생성
		oout = new ObjectOutputStream(
				new BufferedOutputStream(
						new FileOutputStream(fileName)));
		
		// 데이터 저장하기 ==> Map객체 자체를 저장한다.
		oout.writeObject(phoneBookMap);
		
		System.out.println("저장이 완료되었습니다.");
		
		dataChange = false;
	    	
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		// 사용했던 스트림 객체 닫기
		if(oout != null) try { oout.close(); } catch (IOException e) {
    }
		
		
	}
	   
  }
   
   // 전화번호 정보를 검색하는 메서드
   private void search() {
	   System.out.println();
	   System.out.println("검색할 전화번호 정보를 입력하세요. . .");
	   System.out.print("이 름 >> ");
	   String name = scan.next();
	   
	   if(!phoneBookMap.containsKey(name)) {  // 해당 이름이 없으면. . .
		   System.out.println();
		   System.out.println(name + "씨 전화번호 정보가 없습니다. . .");
	   } else {
		   Phone p = phoneBookMap.get(name);
		   System.out.println(name + "씨 전화번호 정보");
		   System.out.println("-----------------------");
//		   System.out.println("이 름 : " + name);
		   System.out.println("이 름 : " + p.getName());
		   System.out.println("전화번호 : " + p.getTel());
		   System.out.println("주 소 : " + p.getAddr());
		   System.out.println("-----------------------");
	   }
	   System.out.println();
	   
	   
   }
   
   // 전화번호 정보를 삭제하는 메서드
   private void delete() {
	   System.out.println();
	   System.out.println("삭제할 전화번호 정보를 입력하세요. . .");
	   System.out.print("이 름 >> ");
	   String name = scan.next();
	   
	   if(!phoneBookMap.containsKey(name)) {  // 해당 이름이 없으면. . .
		   System.out.println();
		   System.out.println(name + "씨 전화번호 정보가 없습니다. . .");
		   return;
	   }
	   
	   phoneBookMap.remove(name);   // key값(이름)을 이용하여 삭제한다.
	   
	   System.out.println(name + "씨 전화번호 정보 삭제 완료 !!!");
	   
	   dataChange = true;    // 데이터가 변경되었음을 나타낸다.
   }
   
   // 전화번호 정보를 수정하는 메서드
   private void update() {
	   System.out.println();
	   System.out.println("수정할 전화번호 정보를 입력하세요. . .");
	   System.out.print("이 름 >> ");
	   String name = scan.next();
	   
	   if(!phoneBookMap.containsKey(name)) {  // 해당 이름이 없으면. . .
		   System.out.println();
		   System.out.println(name + "씨 전화번호 정보가 없습니다. . .");
		   return;
	   }
	   
	   System.out.print("새로운 전화번호 >> ");
	   String newTel = scan.next();
	   
	   scan.nextLine();  // 입력 버퍼 비우기
	   
	   System.out.print("새로운 주소 >> ");
	   String newAddr = scan.nextLine();
	   
	   // 수정작업 ==> 같은 key값에 새로운 전화번호 정보를 저장한다. 
	   phoneBookMap.put(name, new Phone(name, newTel, newAddr));
	   
	   System.out.println(name + "씨의 전화번호 정보 변경 완료 !!!");
	   
	   dataChange = true;    // 데이터가 변경되었음을 나타낸다.
	   
   }
   
   // 전체 전화번호 정보를 출력하는 메서드
   private void displayAll() {
	   System.out.println();
	   
	   Set<String> phoneKeySet = phoneBookMap.keySet();
	   
	   System.out.println("-------------------------------------------");
	   System.out.println("번호    이름    전화번호    주소");
	   System.out.println("-------------------------------------------");
	   
	   if(phoneKeySet.size()==0) {
		   System.out.println("  등록된 전화번호 정보가 하나도 없습니다. . .");
	   } else {
		   int num = 0;    // 번호 출력용 변수 선언
		   Iterator<String> keyIt = phoneKeySet.iterator();
		   while(keyIt.hasNext()) {
			   num++;
			   String name = keyIt.next();   // 키값(이름) 가져오기
			   Phone p = phoneBookMap.get(name);
			   System.out.println(" " + num + "\t" + p.getName() + "\t" + p.getTel() + 
					   "\t" + p.getAddr() );
		   }
	   }
	   System.out.println("-------------------------------------------");
	   System.out.println("출력 끝. . .");
	   
	   
   }
   
   /*
   - Scanner객체의 next(), nextDouble(). . .등 nextLine()이 아닌 메서드
      ==> 사이띄기, Tab키, Enter키를 구분 문자로 분리해서 분리된 자료만 읽어간다.
      
   - Scanner객체의 nextLine()메서드
      ==> 한 줄 단위로 입력한다. 즉, 자료를 입력하고 Enter키를 누르면 Enter키까지 읽어가서 
          Enter키를 뺀 나머지 데이터를 반환한다.
          
   - 그래서 nextLine()메서드를 사용하기 전에 nextLine()이외의 메서드로 입력한 이력이 있는 경우에는
         nextLine()메서드를 한번 호출해서 입력 버퍼를 비워주어야 한다.            
   */
   
   // 새로운 전화번호 정보를 등록하는 메서드
   private void insert() {
	   System.out.println();
	   System.out.println("새롭게 등록할 전화번호 정보를 입력하세요. ");
	   System.out.print("이름 >> ");
	   String name = scan.next();
	   
	   // 이미 등록된 사람인지 여부 검사하기
	   if(phoneBookMap.containsKey(name)) {  // 이미 있으면. . .
		   System.out.println(name + "씨는 이미 등록된 사람입니다. . .");
		   return;
	   }
	   
	   System.out.print("전화번호 >> ");
	   String tel = scan.next();
	   
	   scan.nextLine();  // 입력 버퍼 비우기
	   
	   System.out.print("주 소 >> ");
	   String addr = scan.nextLine();
	   
//	   Phone p = new Phone(name, tel, addr);
//	   phoneBookMap.put(name, p);
	   
	   phoneBookMap.put(name, new Phone(name, tel, addr));
	   
	   System.out.println(name + " 전화번호 정보 등록 완료 !!!");
	   
	   dataChange = true;    // 데이터가 변경되었음을 나타낸다.
   }
   
   // 메뉴를 출력하고 작업번호를 입력받아 반환하는 메서드
   private int displayMenu() {
	   System.out.println();
	   System.out.println("===== 메 뉴 =====");
	   System.out.println("1. 전화번호 등록");
	   System.out.println("2. 전화번호 수정");
	   System.out.println("3. 전화번호 삭제");
	   System.out.println("4. 전화번호 검색");
	   System.out.println("5. 전화번호 전체 출력");
	   System.out.println("6. 전화번호 저장 ");
	   System.out.println("0. 프로그램 종료");
	   System.out.println("==================");
	   System.out.print("번호 입력 >> ");
	   return scan.nextInt();
   }
   
   
}

// 이름, 주소, 전화번호를 멤버로 갖는 Phone 클래스
class Phone implements Serializable {
	private String name;
	private String tel;
	private String addr;
	
	public Phone(String name, String tel, String addr) {
		super();
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "Phone [name=" + name + ", tel=" + tel + ", addr=" + addr + "]";
	}
}


