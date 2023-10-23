package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class HotelTest {
	private HashMap<Integer, Room> HotelMap;
	private Scanner scan;
	
	// 생성자
	public HotelTest() {
		scan = new Scanner(System.in);
		HotelMap =  new HashMap<>();
		
		for(int i=201; i<=209; i++) {
			HotelMap.put(i, new Room(i, "싱글룸"));
		}
		
		for(int i=301; i<=309; i++) {
			HotelMap.put(i, new Room(i, "더블룸"));
		}
		
		for(int i=401; i<=409; i++) {
			HotelMap.put(i, new Room(i, "스위트룸"));
		}
	}

	public static void main(String[] args) {
		new HotelTest().HotelStart();

	}
	
	// 프로그램을 시작하는 메서드
	public void HotelStart() {
		System.out.println("*********************************************");
		System.out.println("      호텔문을 열었습니다. 어서오십시오.     ");
		System.out.println("*********************************************");
		System.out.println();
		
		while(true) {
			int choice = displayMenu();
			
			switch(choice) {
			 case 1:   // 체크인
				 in();
				 break;
			 case 2:   // 체크아웃
				 out();
				 break;
			 case 3:   // 객실상태
				 condition();
				 break;
			 case 4:   // 업무 종료
				 System.out.println();
				 System.out.println("*********************************************");
				 System.out.println("            호텔문을 닫았습니다.             ");
				 System.out.println("*********************************************");
				 return;
			  default :
				 System.out.println();
				 System.out.println("잘못된 입력입니다. 다시 선택하세요. . .");
			}	
		}
	}
	
	// 체크인 하는 메서드
	private void in() {
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println("   체크인 작업");
		System.out.println("----------------------------------------------");
		System.out.println("* 201~209 : 싱글룸");
		System.out.println("* 301~309 : 더블룸");
		System.out.println("* 401~409 : 스위트룸");
		System.out.println("----------------------------------------------");
		System.out.print("방번호 입력 >> ");
		int num = scan.nextInt();

		if(!HotelMap.containsKey(num)) {  // 해당 방 번호가 없으면. . .
			System.out.println(num + "호 객실은 존재하지 않습니다.");
			return;
		} 
		
		Room r = HotelMap.get(num); 
	    if (r.getName() != null) {    // 해당 방에 이미 손님이 있다면. . .
	        System.out.println(num + "호 객실에는 이미 손님이 있습니다.");
	        return;
	    }
		
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 >> ");
		String name = scan.next();
		
		r.setName(name);

		System.out.println("체크인이 완료되었습니다.");
		
	}
	
	// 객실상태 메서드
	private void condition() {
		
		Set<Integer> hotelKeySet = HotelMap.keySet();
		
		System.out.println("----------------------------------------------");
		System.out.println("		현재 객실 상태");
		System.out.println("----------------------------------------------");
		System.out.println("방 번호	 방 종류	 투숙객 이름");
		System.out.println("----------------------------------------------");
		
			Iterator<Integer> keyIt = hotelKeySet.iterator();
	        while (keyIt.hasNext()) {
	            int num = keyIt.next();
	            Room r = HotelMap.get(num);
	            String roomType = "";
	            if (num >= 201 && num <= 209) {
	                roomType = "싱글룸";
	            } else if (num >= 301 && num <= 309) {
	                roomType = "더블룸";
	            } else if (num >= 401 && num <= 409) {
	                roomType = "스위트룸";
	            }
	            System.out.println(" " + num + "\t" + "\t" + r.getRoomtype() + "\t" + (r.getName() != null ? r.getName() : "-"));
			}
		}
	
	// 체크아웃 하는 메소드
	private void out() {
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println("   체크아웃 작업");
		System.out.println("----------------------------------------------");
		System.out.println("체크아웃 할 방 번호를 입력하세요.");
		System.out.print("방번호 입력 >> ");
		int num = scan.nextInt();

		if(!HotelMap.containsKey(num)) {  // 해당 방 번호가 없으면. . .
			System.out.println(num + "호 객실은 존재하지 않습니다.");
			return;
		} 
		
		Room r = HotelMap.get(num);    // 체크인 한 사람이 없으면. . .
		if (r.getName() == null) {
			System.out.println(num + "호 객실에는 체크인 한 사람이 없습니다.");
			return;
		} 
		
		HotelMap.remove(num);
		
		System.out.println(num + "호 객실의 " + r.getName() + "님 체크아웃을 완료했습니다.");
		}
	

	
	// 메뉴를 출력하고 작업번호를 입력받아 반환하는 메서드
	private int displayMenu() {
		System.out.println();
		System.out.println();
		System.out.println("-----------------------------------------------------------");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료");
		System.out.println("-----------------------------------------------------------");
		System.out.print("선택 >> ");
		return scan.nextInt();
	}
}

class Room{
   private int num;
   private String roomtype; 
   private String name;
   
   public Room(int num, String roomtype) {
	   super();
	   this.num = num;
	   this.roomtype = roomtype;
	
    }

    public int getNum() {
	   return num;
    }

    public void setNum(int num) {
	   this.num = num;
    }

    public String getRoomtype() {
	   return roomtype;
    }

    public void setRoomtype(String roomtype) {
	   this.roomtype = roomtype;
    }

    public String getName() {
	   return name;
    }

    public void setName(String name) {
	   this.name = name;
    }

    @Override
    public String toString() {
 	    return "Room [num=" + num + ", roomtype=" + roomtype + ", name=" + name + "]";
    }
}
