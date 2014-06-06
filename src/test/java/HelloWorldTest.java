import com.example.helloworld.HelloWorldApplication;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by dev on 06/06/14.
 */
public class HelloWorldTest{



    @Test
    public void checkThatGetNameReturnsHelloWorld(){

        HelloWorldApplication app = new HelloWorldApplication();

        assertEquals("hello-world", app.getName());

    }
}
