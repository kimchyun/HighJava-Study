package kr.or.ddit.basic;


// 데몬 쓰레드 연습 ==> 자동 저장하는 쓰레드
public class ThreadTest08 {

	public static void main(String[] args) {
		AutoSaveThread autoSave = new AutoSaveThread();
		
		autoSave.setDaemon(true);   // 데몬 쓰레드로 설정하기 ==> 반드시 start()메서드 호출 전에 실행해야 한다.
		System.out.println("데몬 쓰레드 여부 : " + autoSave.isDaemon());
		
		autoSave.start();
		
		try {
			for(int i=1; i<=20; i++) {
				System.out.println(i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		System.out.println("main 쓰레드 종료");

	}

}


// 자동 저장하는 쓰레드 ==> 3초에 한번씩 자동으로 저장하기
class AutoSaveThread extends Thread {
	private void save() {   // 저장하는 일을 처리하는 메서드
		System.out.println("작업 내용을 저장합니다.");
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		    
			save();     // 저장하는 메서드 호출
			
		}
	}
}
