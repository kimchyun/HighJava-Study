package kr.or.ddit.basic;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class ThreadTest13 {
	
	public static void main(String[] args) {
		Horse[] horseArr = new Horse[] {
				new Horse("01번말"),new Horse("02번말"),new Horse("03번말"),new Horse("04번말"),
				new Horse("05번말"),new Horse("06번말"),new Horse("07번말"),new Horse("08번말"),
				new Horse("09번말"),new Horse("10번말")
		};
		
		GameStatePrint gsp = new GameStatePrint(horseArr);
		
		// 말들의 경주 시작
		for(Horse h : horseArr) {
			h.start();
		}
		
		
		gsp.start();   // 말들의 경주 상태 출력하는 쓰레드 시작
		
		for(Horse h : horseArr) {
			try {
				h.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		try {
			gsp.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		System.out.println();
		System.out.println("경기 끝!!!");
		
		System.out.println("==경기 결과== ");
		
		
		// 등수의 오름차순으로 정렬하기
		Arrays.sort(horseArr);   // 배열 정렬하기
		
		for(Horse h : horseArr) {
			System.out.println(h);
		}
		
	}
	
}


/*
10마리의 말들이 경주하는 경마 프로그램 작성하기

말은 Horse라는 이름의 쓰레드 클래스로 구성하고,
이 클래스는 말이름(String), 현재위치(int), 등수(int)를 멤버변수로 갖는다.
그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬 기준이 있다.
( Comparable 인터페이스 구현)

경기 구간은 1~50구간으로 되어 있다.
*/

class Horse extends Thread implements Comparable<Horse> {
	public static int currentRank = 0;   // 말들의 등수를 결정할 변수
	
	
	private String horseName;    // 말이름
	private int location;        // 현재 위치
	private int rank;            // 등수
	
	//생성자
	public Horse(String horseName) {
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "경주마 " + horseName + "의 등수는 " + rank + "등 입니다.";
	}
	
	
	// 등수의 오름차순으로 처리할 내부 정렬 기준
	@Override
	public int compareTo(Horse horse) {
		return Integer.compare(rank, horse.rank);
	}
	
	// 말이 달리는 것을 쓰레드로 처리한다.
	@Override
	public void run() {
		Random rnd = new Random();
		
		for(int i=1; i<=50; i++) {
			this.location = i;   // 말의 현재 위치를 구한다.
			
			try {
				Thread.sleep(rnd.nextInt(500));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			
		}  // for문 끝
		
		// 한 마리의 말이 경주가 끝나면 등수를 구해서 저장한다.
		currentRank++;
		this.rank = currentRank;	
	}
	
}



/*
경기 중 중간 중간에 각 말들의 위치를 나타내시오.
예)
1번말 --->------------------------------------
2번말 ----->----------------------------------
...
*/

// 경기 중의 말의 현재 위치를 나타내는 쓰레드
class GameStatePrint extends Thread {
   	private Horse[] horseArr;

   	// 생성자
	public GameStatePrint(Horse[] horseArr) {
		this.horseArr = horseArr;
	}
	
	@Override
	public void run() {
		while(true) {
			// 모든 말들의 경기가 종료되었는지 여부 검사하기
			if(Horse.currentRank == horseArr.length) {
				break;
			}
			
			// 빈 줄 출력하기
			for(int i=0; i<=12; i++) {
				System.out.println();
			}
			
			for(int i=0; i<horseArr.length; i++) {
				System.out.print(horseArr[i].getHorseName() + " : ");
				
				for(int j=1; j<=50; j++) {
					// 말의 현재 위치를 찾아서 '>' 문자로 나타낸다.
					if(horseArr[i].getLocation() == j) {
						System.out.print(">");
					} else {
						System.out.print("-");						
					}
				}
				System.out.println();    // 줄바꿈
				
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			
		}
	}

}


