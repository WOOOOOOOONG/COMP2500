# Hello World
```angular2html
package academy.pocu;

public class HelloPocu {
    public static void main(String[] args) {
        System.out.println("Hello Pocu!");
    }
}
```
# 접근 제어자
### 1. 하나의 클래스 내부 최고 레벨에는 public 클래스 하나만 있어야 한다.
ex1) 둘 이상 정의할 경우 컴파일 오류 - ./src/T1_최고레벨_클래스_검증/Main.java
```angular2html
// OK
public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");
    }
}

// Error Message: 클래스 'FakeMain'은(는) public이며, 이름이 'FakeMain.java'인 파일에 선언되어야 합니다
public class FakeMain {
    public static void main(String[] args) {}
}

// Error Message: 제어자 'private'은(는) 허용되지 않습니다
private class FakePrivateMain {
    public static void main(String[] args) {}
}
```
### 2. 내포(nested) 클래스
최상단이 아닌 경우, 여러개의 클래스 정의 가능
```angular2html
public class Main {
    // OK
    public class One { }
    
    // OK
    public class Two {
        
    }
}
```

# I hate main function
Java는 반드시 아래와 같이 메인 클래스 내부에 메인 함수를 정의한다.
```angular2html
public class Main {
    public static void main(String[] args) {
        ...
    }
}
```
왜 그럴까?

바로 JVM의 동작 방식 때문이다. JVM이 프로그램을 시작할 떄는 아래와 같은 과정을 거친다.
1. Class Loader가 지정된 Main Class를 메모리에 로드
2. JVM이 그 클래스에서 '정확히 `main(String[] args)` 시그니처를 가진 메서드를 찾는다.' (단, 외부에서 호출하기 때문에 private면 호출이 불가능)
3. 해당 메서드를 호출해 프로그램을 시작한다.

따라서, 외출을 하기 위해서는 반드시 집 안의 문을 열고 나가야 하듯이, JVM 또한 프로그램을 시작하는 첫 단추를 `main(String[] args)`로 시작하는 것이다.

심상이 뒤틀린 나로서는 유독 자바에서 프로그램을 시작하기 위해 긴 문법을 강제하는 것이 마음에 들지 않았다. 다른 언어를 예로 들어보자
- 형식 존재 언어: C, Go, Rust
```angular2html
// 1. C
int main(int argc, char *arv[]) // 기본 선언
int main() {} // 간단 선언

// 2. C#
static void Main()

// 3. Go
func main()

// 4. Rust
fn main()
```
- 형식 미존재 언어: PHP, Python, Javascript

형식이 미존재하는 언어들은 대부분 '형식의 자유로움'을 추구하는 언어들이니 그렇다 치더라도, 형식을 강제하는 언어들 또한 Java보다 훨씬 간단하게 선언이 가능하다.
하지만 Java의 `public static void main(String[] args)`는 너무 복잡하다. 어째서 시그니처를 이렇게 잡았는가?

따라서 각각의 키워드를 강제하는 이유를 찾아보았다.
- public: JVM이 외부에서 이 메서드에 접근해야 함
- static: 객체 인스턴스를 생성하기 전에 호출되어야 함 (JVM이 짜치게 new MyClass().main()을 할 수는 없음)
- void: 프로그램의 시작점일 뿐이니 반환값은 의미가 없음
- String[] args: 명령행 인수를 받기 위한 표준 방식으로, 배열 형태로 전달되는 것이 일반적

나름의 일리가 있다.

추가로, 이토록 엄격한 규칙을 만든 이유는 자바의 가장 큰 장점인 '플랫폼 독립성' 때문인데, 어떠한 운영체제이든,
어떠한 JVM 구현체이든간에 동일하게 동작하기 위해서는 진입점이 명확하기 위해서라고 한다.

C/C++은 Linker를 통해 손쉽게 연결 가능하나 JVM은 Runtime에 Reflection으로 이 메서드를 찾아 호출하는 구조라고 한다.<br>(Reflection: 실행 중인 프로그램이 자기 자신의 구조를 들여다보고 조작할 수 있는 기능)

- C
```angular2html
소스코드.c -> 컴파일러 -> 오브젝트파일.c -> 링커 -> 실행파일.exe
```
- Java
```angular2html
소스코드.java -> javac -> .class 파일 -> JVM이 실행 시점에 처리
```

...납득

# META-INF 폴더
- .jar 파일 내부에 META-INF\MANIFEST.MF 파일 존재
- .jar 만들 때 같이 생성되는 파일
- Java 애플리케이션의 정보를 담고 있는 메타데이터 파일
- jar 파일의 시작점(메인 함수)에 대한 정보를 넣어야 한다.

# java.lang
- 기본 패키지이며, 모든 .java 파일에 자동으로 import되는 패키지
- 출력에 사용되는 System 클래스는 java.lang 안에 있는 클래스 중 하나이다.

# 실행 모델
