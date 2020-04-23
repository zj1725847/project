package impl;

public class UserImpl {

    public static void main(String[] args) throws Exception {
    	System.err.println("修改了一哈输出");
    	int a = 10;
    	for (int i = 0; i <= a; i++) {
    		System.out.println(i);
		System.out.println("i="+i);
			Thread.sleep(1000);
		}
    }
}
