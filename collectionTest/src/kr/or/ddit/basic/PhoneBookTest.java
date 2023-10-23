package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class PhoneBookTest {
	
	private Scanner scan;
	private Map<String, Phone> phoneBookMap;
	
	public PhoneBookTest() {
		scan = new Scanner(System.in);
		phoneBookMap = new HashMap<>();
	}
	
	public void phoneBookStart() {
		while (true) {
			displayMenu();
			
			int menuNum = scan.nextInt();
			
			switch (menuNum) {
			case 1:
				insert();
				break;
			case 2:
                update(); // 수정
                break;
            case 3:
                delete(); // 삭제
                break;
            case 4:
                search(); // 검색
                break;
            case 5:
                displayAll(); // 전체 출력
                break;
            case 0:
                System.out.println("프로그램을 종료합니다...");
                return;
            default:
                System.out.println("잘못 입력했습니다. 다시 입력하세요.");
			}
		}
	}
    
	private void insert() {
		System.out.println();
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");
		String name = scan.next();
		
		if (phoneBookMap.get(name) != null) {
			System.out.println(name + "님은 이미 등록된 사람입니다.");
			return;
		}
		System.out.println("전화번호 >> ");
		String tel = scan.next();
		
		System.out.println("주소 >> ");
		scan.nextLine();
		
		String addr = scan.nextLine();
		
		phoneBookMap.put(name, new Phone(name, tel, addr));
		System.out.println("'" + name + "'" + "전화번호 등록 완료 !!");
	}
	
	private void update() {
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");
		String name = scan.next();
		
		if (phoneBookMap.get(name) == null) {
			System.out.println(name + "님은 전화번호 정보가 없습니다.");
			return;
		}
		
		System.out.print("전화번호 >> ");
		String tel = scan.next()	;
		
		System.out.print("주소 >> ");
		scan.nextLine();
		
		String addr = scan.nextLine();
		
		phoneBookMap.put(name,  new Phone(name, tel, addr));
		
		System.out.println("'" + name + "'" + " 수정 완료 !!");
	}
	
	private void delete() {
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >>");
		String name = scan.next();
		
		if (phoneBookMap.remove(name) == null) {
			System.out.println(name + "님은 등록된 사람이 아닙니다.");
		} else {
			System.out.println(name + "님 정보를 삭제하였습니다.");
		}
	}
	
	private void search( ) {
		System.out.println();
		System.out.println("검색할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >>");
		String name = scan.next();
		
		Phone p = phoneBookMap.get(name);
		
		if(p==null) {
			System.out.println(name + "님의 전화번호 정보가 없습니다.");
		} else {
			System.out.println(name + "님의 전화번호 정보");
			System.out.println("이  름 : " + p.getName());
			System.out.println("전화번호 : " + p.getTel());
			System.out.println("주  소 : " + p.getAddr());
		}
		System.out.println("검색 작업 완료");
	}
	
	private void displayAll() {
		Set<String> keySet = phoneBookMap.keySet();
		
		System.out.println("----------------------------------------");
		System.out.println(" 번호\t이름\t전화번호\t주소");
		System.out.println("----------------------------------------");
		
		if (keySet.size() == 0) {
			System.out.println("등록된 전화번호 정보가 없습니다.");
		} else {
			Iterator<String> it = keySet.iterator();
			
			int cnt = 0;
			while (it.hasNext()) {
				cnt++;
				String name = it.next();
				Phone p = phoneBookMap.get(name);
				System.out.println("" + cnt + "\t" + p.getName() + "\t" + p.getTel() + p.getAddr());
			}
			System.out.println("----------------------------------------");
			System.out.println("출력 끝 . . .");
		}
	}
	public void displayMenu() {
		System.out.println();
		System.out.println("\t메뉴\t");
		System.out.println(" 1. 전화번호 등록 ");
		System.out.println(" 2. 전화번호 수정 ");
		System.out.println(" 3. 전화번호 삭제 ");
		System.out.println(" 4. 전화번호 검색 ");
		System.out.println(" 5. 전화번호 전체 출력 ");
		System.out.println(" 0.  프로그램 종료 ");
		System.out.println("------------------");
		System.out.print(" 번호입력 >> ");
	}
	
	public static void main(String[] args) {
		new PhoneBookTest().phoneBookStart();
	}
}

class Phone {
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
