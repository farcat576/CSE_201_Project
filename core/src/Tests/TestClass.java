package Tests;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class TestClass {
public static void main(String[] args){
    Result result = JUnitCore.runClasses(MyTest.class);
    for(Failure failure: result.getFailure()){
        System.out.println(failure.toString());
    }
    System.out.println(result.wasSuccessful());
}
}

