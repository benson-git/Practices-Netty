package practices.nio.nature;

/**
 * Nonblock implemented by JDK nio (New IO lib).
 * IO Multiplexing: Sometime call asyn blocking IO. Thank to Reactor Pattern.
 * The Java Selector is equivalent to Linux epoll.     
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}  
