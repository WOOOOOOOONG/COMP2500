package T1_최고레벨_클래스_검증;

/**
 * 접근 제어자 - 하나의 클래스 내부 최고 레벨에는 public 클래스 하나만 있어야 한다.
 */

// OK
public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");
    }
}

// Error Message: 클래스 'FakeMain'은(는) public이며, 이름이 'FakeMain.java'인 파일에 선언되어야 합니다
//public class FakeMain {
//    public static void main(String[] args) {}
//}

// Error Message: 제어자 'private'은(는) 허용되지 않습니다
//private class FakePrivateMain {
//    public static void main(String[] args) {}
//}
