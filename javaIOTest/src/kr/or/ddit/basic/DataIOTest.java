package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataIOTest {

	public static void main(String[] args) {
		try {
			
			// 파일 출력용 스트림 객체 생성
			FileOutputStream fout = new FileOutputStream("d:/d_other/test.dat");
			
			// 자료형 단위로 출력할 보조 스트림 객체 생성
			DataOutputStream dout = new DataOutputStream(fout);
			
			dout.writeInt(200);         // 정수형으로 데이터 출력
			dout.writeFloat(123.45f);   // 실수형(float)으로 데이터 출력
			dout.writeBoolean(true);   // 논리형으로 데이터 출력
			dout.writeUTF("ABCDabcd");  // 문자열형식으로 데이터 출력
			
			System.out.println("== 출력 끝 ==");
			System.out.println();
			
			dout.close();         // 스트림 닫기
			//--------------------------------------------------------
			
			// 출력한 자료 읽어오기
			DataInputStream din = new DataInputStream(new FileInputStream("d:/d_other/test.dat"));
			
			// DataInputStream으로 자료를 읽어올 때는 출력할 때의 순서와 같은 순서로 읽어와야 한다.
			System.out.println("정수형 : " + din.readInt());
			System.out.println("실수형 : " + din.readFloat());
			System.out.println("논리형 : " + din.readBoolean());
			System.out.println("문자열 : " + din.readUTF());
			
			System.out.println();
			System.out.println("== 읽기 작업 완료 ==");
			
			
			
			
			
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
