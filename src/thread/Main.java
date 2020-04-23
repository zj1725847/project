package thread;

import java.util.Date;

public class Main {
    // 顺序编程 吃喝示例：当吃饭吃不完的时候，是不能喝酒的，只能吃完晚才能喝酒
    public static void main(String[] args) throws Exception {
		// 先吃饭再喝酒
//        eat();
//        drink();
    	Date a = new Date();
    	System.out.println(a);
    	Thread.sleep(5000);
    	Date b = new Date();
    	System.out.println(b);
    	System.err.println(b.after(a));
    }

    private static void eat() throws Exception {
        System.out.println("开始吃饭?...\t" + new Date());
        Thread.sleep(5000);
        System.out.println("结束吃饭?...\t" + new Date());
    }

    private static void drink() throws Exception {
        System.out.println("开始喝酒?️...\t" + new Date());
        Thread.sleep(5000);
        System.out.println("结束喝酒?...\t" + new Date());
    }
}
