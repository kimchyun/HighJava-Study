package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
    문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student클래스를 만든다.
    이 Student클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 초기화 처리를 한다.
    
    이 Student객체는 List에 저장하여 관리한다.
    
    List에 저장된 데이터들을 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현하고,
    총점의 역순(=내림차순)으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬되는 외부 정렬 기준 클래스를 작성하여
    정렬된 결과를 출력하시오.
    
    등수는 List에 전체 데이터가 추가된 후에 구해서 저장되도록 한다. (StudentTest클래스에 처리)
*/

public class StudentTest {
	
	// 등수를 구하는 메서드
	public void createRank(ArrayList<Student> rankStdList) {
		for(Student std1 : rankStdList) {    // 기준 데이터를 구하기 위한 반복문
			int rank = 1;     // 처음에는 등수를 1로 설정해 놓고 시작한다.
			
			for(Student std2 : rankStdList) {   // 비교 대상을 나타내는 반복문
				
				if(std1.getTotal() < std2.getTotal()) {   // 기준값보다 비교 대상이 크면 rank값을 증가시킨다.
					rank++;
				}
			}
			
			// 구해진 등수를 기준 데이터의 rank변수에 저장한다.
			std1.setRank(rank);
		}
    
	}

	public static void main(String[] args) {
		ArrayList<Student> stdList = new ArrayList<>();
		
		stdList.add(new Student(1, "김채현", 90, 95, 85));
		stdList.add(new Student(3, "이경진", 85, 85, 90));
		stdList.add(new Student(7, "서예린", 80, 90, 95));
		stdList.add(new Student(5, "박지민", 75, 95, 80));
		stdList.add(new Student(2, "김소희", 60, 85, 70));
		stdList.add(new Student(4, "조은비", 70, 80, 90));
		stdList.add(new Student(6, "은서현", 70, 80, 75));
		
		StudentTest test = new StudentTest();
		
		// 등수를 구하는 메서드 호출
		test.createRank(stdList);
		
		
		System.out.println("정렬 전. . .");
		for(Student student : stdList) {
			System.out.println(student);
		}
		
		System.out.println("===============================================");
		
		Collections.sort(stdList);  //내부 정렬

		System.out.println("학번의 오름차순 정렬 후. . .");
		for(Student student : stdList) {
			System.out.println(student);
		}
		
		System.out.println("===============================================");
		
		Collections.sort(stdList, new SortByTotal());
		
		System.out.println("총점의 역순으로 정렬 후. . .");
		for(Student student : stdList) {
			System.out.println(student);
		}
		System.out.println("===============================================");
	}
}

// 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현
class Student implements Comparable<Student> {
	private int id;
	private String name;
	private int korean;
	private int english;
	private int math;
	private int total;
	private int rank;
	
	// 생성자
	public Student(int id, String name, int korean, int english, int math) {
		super();
		this.id = id;
		this.name = name;
		this.korean = korean;
		this.english = english;
		this.math = math;
		this.total = korean + english + math;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKorean() {
		return korean;
	}

	public void setKorean(int korean) {
		this.korean = korean;
	}

	public int getEnglish() {
		return english;
	}

	public void setEnglish(int english) {
		this.english = english;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", korean=" + korean + ", english=" + english + ", math=" + math
				+ ", total=" + total + ", rank=" + rank + "]";
	}
    
	// 내부 정렬 기준은 현재 객체(this)와 매개변수에 저장되는 객체와 비교해서 처리한다.
	// 현재 객체(this)가 앞쪽 데이터, 매개변수에 저장된 객체가 뒤쪽 데이터라고 하고 코딩한다.
	@Override
	public int compareTo(Student std) {
		// 학번의 오름차순
		return Integer.compare(this.getId(), std.getId());
	}
}


// 총점의 역순(=내림차순)으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬되는 외부 정렬 기준 클래스
class SortByTotal implements Comparator<Student> {

	@Override
	public int compare(Student std1, Student std2) {
		if(std1.getTotal() == std2.getTotal()) {  // 총점이 같으면. . .
			return std1.getName().compareTo(std2.getName());   // 이름의 오름차순 정렬
		} else {
			return Integer.compare(std1.getTotal(), std2.getTotal()) * -1;  // 총점의 내림차순 정렬			
		}
	}
}
