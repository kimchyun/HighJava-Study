package test.java.test03;

import java.util.ArrayList;
import java.util.List;

public class Test01 {
    public static void main(String[] args) {
        // ParamMap 클래스의 init() 메서드를 사용하여 인스턴스를 생성
        ParamMap param = ParamMap.init();

        List<String> list = new ArrayList<>();
        list.add("1");

        param.put("list", list);

        // ParamMap 클래스의 get 메서드를 호출할 때 파라미터 타입을 지정
        List<String> list2 = param.get("list", List.class);
        System.out.println(list2);
    }
}
