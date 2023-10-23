package kr.or.ddit.basic;

import java.io.File;

public class FileTest02 {

	public static void main(String[] args) {
		File f1 = new File("d:/d_other/test.txt");
		
		System.out.println(f1.getName() + " 파일의 크기 : " + f1.length() + "bytes");
		
		// getPath() ==> File객체를 생성할 때 생성자에 대입한 경로가 그대로 나온다.
		System.out.println("path : " + f1.getPath());     
		System.out.println("absolutePath : " + f1.getAbsolutePath());
		System.out.println();
		
		// 현재 위치는 ?  
		// ==> 이클립스에서 java프로그램을 실행하면 해당 java프로그램이 속한 프로젝트 폴더의 위치가 현재 위치가 된다.
		File f2 = new File(".");
		System.out.println("path : " + f2.getPath());
		System.out.println("absolutePath : " + f2.getAbsolutePath());
        System.out.println();
		
		if(f1.isFile()) {
			System.out.println(f1.getName() + "은 파일입니다.");
		} else if(f1.isDirectory()) {
			System.out.println(f1.getName() + "은 디렉토리(폴더) 입니다.");
		} else {
			System.out.println(f1.getName() + "은 뭘까???");
		}
		System.out.println();
		
		
		if(f2.isFile()) {
			System.out.println(f2.getName() + "은 파일입니다.");
		} else if(f2.isDirectory()) {
			System.out.println(f2.getName() + "은 디렉토리(폴더) 입니다.");
		} else {
			System.out.println(f2.getName() + "은 뭘까???");
		}
		System.out.println();
		
		
		File f3 = new File("d:/d_other/sample.exe");   // 현재 존재하지 않는 것
		if(f3.isFile()) {
			System.out.println(f3.getName() + "은 파일입니다.");
		} else if(f3.isDirectory()) {
			System.out.println(f3.getName() + "은 디렉토리(폴더) 입니다.");
		} else {
			System.out.println(f3.getName() + "은 뭘까???");
		}
		System.out.println();
		
		
	}

}
