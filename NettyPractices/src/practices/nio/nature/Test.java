package practices.nio.nature;

public class Test {

  public final static int OP_READ = 1 << 0;
  public final static int OP_WRITE = 1 << 2;
  public final static int OP_CONNECT = 1 << 3;
  public final static int OP_ACCEPT = 1 << 4;
  
  public static void main(String[] args) {
    
    
      System.out.println(OP_READ + "||" + Integer.toBinaryString(OP_READ));
      System.out.println(OP_WRITE+ "||" + Integer.toBinaryString(OP_WRITE));
      System.out.println(OP_CONNECT+ "||" + Integer.toBinaryString(OP_CONNECT));
      System.out.println(OP_ACCEPT+ "||" + Integer.toBinaryString(OP_ACCEPT));
      
      int a = 1, b=10;
      
      int c = a + b >>> 1;
  
      System.out.println(c+ "||" + Integer.toBinaryString(c));
      System.out.println(a+b+ "||" + Integer.toBinaryString(a+b));
     
  }

}
