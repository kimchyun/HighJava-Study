package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOTest02 {
    
	// 파일에 데이터를 출력하는 예제 - 바이트 기반 스트림 사용
	public static void main(String[] args) {
		try {
			// 파일에 출력할 출력용 바이트 기반 스트림 객체 생성하기
			FileOutputStream fout = new FileOutputStream("d:/d_other/out.txt");
			
			for(char ch='A'; ch<='Z'; ch++) {
				fout.write(ch);    // ch변수의 데이터를 파일로 출력한다.
			}
			
			System.out.println("출력 작업 완료");
			
			fout.close();        // 스트림 닫기
			
			
		} catch (IOException e) {
			System.out.println("입출력 오류 : " + e.getMessage());
		}

	}

}
