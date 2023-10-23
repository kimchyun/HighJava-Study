package kr.or.ddit.basic;

import java.util.Random;

/*
     3개의 쓰레드가 각각 알파벳을 A ~ Z까지 출력하는데
     출력을 끝낸 순서대로 결과를 나타내는 프로그램을 작성하시오
*/

public class ThreadTest12 {

	public static void main(String[] args) {
		DisplayCharacter[] thArr = new DisplayCharacter[] {
				new DisplayCharacter("홍길동"),
				new DisplayCharacter("이순신"),
				new DisplayCharacter("강감찬")
		};
		
		for(DisplayCharacter th : thArr) {
			th.start();
		}
		
		for(int i=0; i<thArr.length; i++) {
			try {
				thArr[i].join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		System.out.println();
		System.out.println("경기 결과");
		System.out.println("순 위 : " + DisplayCharacter.setOrder);

	}

}


// A ~ Z까지 출력하는 쓰레드
class DisplayCharacter extends Thread{
	public static String setOrder = "";    // 출력을 끝낸 순서대로 쓰레드 이름이 저장될 변수
	private String name;
	
	public DisplayCharacter(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		Random rnd = new Random();
		for(char c ='A'; c<='Z'; c++) {
			System.out.println(name + "의 출력 문자 : " + c);
			try {
				Thread.sleep(rnd.nextInt(400));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}  // for문 끝...
		
		System.out.println(name + "의 출력 끝...");
		
		// 출력을 끝낸 순서대로 이름을 배치한다.
		DisplayCharacter.setOrder += name + " ";
		
		
	}   
	
}
