package test.java.test02;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Test01 {
   public static void main(String[] args) throws IOException {
      // 제가 쓰고 있는 파일을 읽기
      
      // 1. 읽기 객체 생성
      //   ㄴ FileInputStream, FileReader
      //   ㄴ BufferedInputStream(한번에 옮김), BufferedReader
      
      File file = new File("");
      System.out.println(file.getAbsolutePath()); //기준경로
      
      //F:\A_study\highjava\workspace\final\src\test\java\test01\Test02.java(절대경로)
      //F:\A_study\highjava\workspace\final(기본경로)
      String path = "src/text/java/test01/Test02.java";
      
      FileReader fr1 = new FileReader(path);
      //FileReader fr2 = new FileReader(new File(path));
      
      // 2. 읽기
      //     - 한글자씩 담기 : fr1.read()
      //     - 한번에 담기 : fr1.read(char[])
      char[] ch = new char[100];
      
      while(true) {
         
         /**
          int read = fr1.read(); // 읽은 글자 1글자
          if(read==-1) {break; }
          System.out.print((char)read);
          */
         
         /** char[]
          int read = fr1.read(ch);
          if(read==-1) {break; }
          String string = new String(ch);
          System.out.println(string);
          */
         int read = fr1.read(ch); // read : 읽은 글자의 수, 글자는 ch 배열에 담김
         if(read==-1) {break; }
         //String string = new String(ch);
         String string = new String(ch,0,read);	// ch배열에서 0~읽은 글자의 수만큼 String화
         System.out.println(string);
      }
      
      //3. 종료
      fr1.close();

   }
} // 종료