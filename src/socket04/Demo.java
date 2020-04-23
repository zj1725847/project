package socket04;

import java.util.Arrays;

public class Demo {
	public static void main(String[] args) {
		int[] allOut = {25,65,87,49,35,16,24,11};
		
		int pw = 35;
		
		System.out.println(Arrays.toString(allOut));
		//��pw��allOut������ɾ��
		for(int i=0;i<allOut.length;i++) {
			if(allOut[i]==pw) {
				System.err.println("==1==>"+allOut[i]);
				allOut[i] = allOut[allOut.length-1];
				System.err.println("==2==>"+allOut[i]);
				System.err.println("==3==>"+Arrays.toString(allOut));
				allOut = Arrays.copyOf(
						allOut, allOut.length-1);
				break;
			}
		}
		
		System.out.println(Arrays.toString(allOut));
	}
}
