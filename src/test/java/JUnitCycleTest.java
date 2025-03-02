import org.junit.jupiter.api.*;

public class JUnitCycleTest {
    // 전체 테스트 시작 전 처음 한번만 실행
    @BeforeAll
    static void beforeAll(){
        System.out.println("@BeforeAll");
    }

    // 테스트 할 때마다 매번 실행
    @BeforeEach
    public void beforeEach(){
        System.out.println("@BeforeEach");
    }

    @Test
    public void test1(){
        System.out.println("test1");
    }

    @Test
    public void test2(){
        System.out.println("test2");
    }

    @Test
    public void test3(){
        System.out.println("test3");
    }
    
    // 전체 테스트 종료 전 한번만 실행
    @AfterAll
    static void afterAll(){
        System.out.println("@AfterAll");
    }

    // 테스트 종료할 때 마다 실행
    @AfterEach
    public void afterEach(){
        System.out.println("@AfterEach");
    }
}
