package test.java.test02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Test02 {
   public static void main(String[] args) throws IOException {
      // 제가 쓰고 있는 파일을 읽기
      
      // 1. 읽기 객체 생성
      //   ㄴ FileInputStream, FileReader
      //   ㄴ BufferedInputStream(한번에 옮김), BufferedReader
      
      String path = "src/text/java/test02/Test02.java";
      
      // 2. 읽기
      BufferedReader br = new BufferedReader(new FileReader(path));
      while(true) {
         String readLine = br.readLine();
         if(readLine==null) { break; }
         System.out.println(readLine);
      }
      
      //3. 종료
      br.close();

   }
} // 종료