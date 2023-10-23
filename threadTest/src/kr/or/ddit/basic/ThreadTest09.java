package kr.or.ddit.basic;


// 쓰레드의 상태 출력해 보기
public class ThreadTest09 {

	public static void main(String[] args) {
		
//		PrintStateThread th = new PrintStateThread(new TargetThread());
		TargetThread tt = new TargetThread();
		PrintStateThread th = new PrintStateThread(tt);
		
		th.start();

	}

}


// 쓰레드의 상태값의 검사의 대상이 되는 쓰레드
class TargetThread extends Thread {
	@Override
	public void run() {
       long sum = 0;
       for(long i=1; i<=2_000_000_000L; i++) {
    	   sum += i;
       }
       
       try {
		   Thread.sleep(1500);
	   } catch (InterruptedException e) {
		   // TODO: handle exception
	   }
       
       sum = 0;
       for(long i=1; i<=2_000_000_000L; i++) {
    	   sum += i;
       }
	}
}



// TargetThread의 상태값을 구해서 출력하는 쓰레드
class PrintStateThread extends Thread {
	private TargetThread target;   // 검사 대상 쓰레드가 저장될 변수
	
	// 생성자
	public PrintStateThread(TargetThread target) {
		this.target = target;
	}
	
	@Override
	public void run() {
		while(true) {
			// 쓰레드의 상태값 구하기  ==> getState()메서드 이용
			Thread.State state = target.getState();
			System.out.println("TargetThread의 상태값 : " + state);
			
			if(state == Thread.State.NEW) {   // TargetThread가 NEW상태이면...
				target.start();    // TargetThread 실행시키기
			}
			
			if(state == Thread.State.TERMINATED) {    // TargetThread가 종료상태이면...
				break;    // 현재 쓰레드로 종료하기
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
}






