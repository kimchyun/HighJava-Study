package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest01 {

	public static void main(String[] args) {
		// 입출력의 성능 향상을 위해서 Buffered스트림을 사용한다.
		
		// 출력용 Buffered스트림 사용 예제
		try {
			// 기반이 되는 스트림 생성
			FileOutputStream fout = new FileOutputStream("d:/d_other/bufferTest.txt");
			
			// 버퍼의 크기가 5인 Buffered스트림 객체 생성
			BufferedOutputStream bout = new BufferedOutputStream(fout, 5);
			
			for(int i='1'; i<='9'; i++) {
				bout.write(i);
			}
			bout.flush();   // 버퍼에 남아 있는 데이터를 모두 출력 장치로 출력 시킨다.
			
			//fout.close();
			bout.close();     // 보조 스트림을 닫으면 보조스트림에서 사용한 기반이 되는 스트림도 자동으로 닫힌다.
			                  // 버퍼 스트림의 close()메서드에는 flush()기능이 포함되어 있다.
			
			
			System.out.println("== 작업 끝 ==");
			
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		

	}

}
