package test.java.test02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test04 {
	public static void main(String[] args) {

		String path = "src/text/java/test02/Test02.java";

		// 2. 읽기
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			while (true) {
				String readLine = null;
				try {
					readLine = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (readLine == null) {
					break;
				}
				System.out.println(readLine);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 3. 종료
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

} // 종료