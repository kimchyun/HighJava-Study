package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Lotto {

	public static void main(String[] args) {
       
		ArrayList<Integer> numList;
		ArrayList<Integer> userList;
		
		while(true) {
		   Scanner scanner = new Scanner(System.in);
		   System.out.println("==========================");
		   System.out.println("Lotto 프로그램");
		   System.out.println("--------------------------");
		   System.out.println("1. Lotto 구입");
		   System.out.println("2. 프로그램 종료");
		   System.out.println("==========================");
		   System.out.print("메뉴 선택 : ");
		   
		   if(scanner.nextInt()==1) {
			   System.out.println("Lotto 구입 시작");
			   System.out.println("(1000원에 로또번호 하나입니다.)");
			   System.out.print("금액 입력: ");
			   int money = scanner.nextInt();
			   if(money < 1000) {
				   System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");
				   continue;
			   } else if (money > 10000) {
				   System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");
				   continue;
			   }
			   int count = money / 1000;
			   System.out.println("행운의 로또번호는 아래와 같습니다.");
			   for (int i =0; i < count; i++) {
				   HashSet<Integer> lottoSet = new HashSet<>();
				    while(lottoSet.size() < 6) {
				    	lottoSet.add((int)(Math.random()* 45 + 1));
				    }
				    System.out.println("로또번호" + (i+1) + ":" + lottoSet);
			   }
			   
			   System.out.println("받은 금액은 " + money + "원이고 거스름돈은 " + (money-count*1000) + "원입니다.");
		   } else {
			   System.out.println("감사합니다.");
			   break;
		   }
		}
	}
}