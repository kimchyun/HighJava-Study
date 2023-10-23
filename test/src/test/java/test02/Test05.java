package test.java.test02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test05 {
   public static void main(String[] args) {
      
      String path = "src/test/java/test02/Test02.java";       
      
      // 2. 읽기
      try(BufferedReader br = new BufferedReader(new FileReader(path));) {
         while(true) {
            String readLine = null;
            readLine = br.readLine();
            if(readLine==null) { break; }
            System.out.println(readLine);
         }
      } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
} // 종료