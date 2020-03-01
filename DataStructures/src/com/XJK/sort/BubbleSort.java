package com.XJK.sort;


import java.util.Calendar;

/**1
 * @author MSI
 * 冒泡排序
 * 时间复杂度：O(n^2)
 * 8万数据：15s
 */
public class BubbleSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//int[] longArr = {5,3,2,1,0};
	    int[] longArr = new int[80000];
		for (int i = 0; i < longArr.length; i++) {
			longArr[i] = (int)(Math.random()*800000);
		}
		getTimr();
		Bsort(longArr);
		getTimr();
	}
	
	//冒泡排序
	public static int[] Bsort(int[] t) {
		int temp, count = 0;
		boolean flag = true;
		int[] s = t.clone(); // 复制一份，因为数组是引用类型，否在在函数里修改的就是原数组
		for (int i = s.length - 2; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {   //从头开始与到i的数据相比较，得到属于第i个位置的大小的数据
				count++;
				if (s[j] > s[j + 1]) {
					flag = false;
					temp = s[j + 1];
					s[j + 1] = s[j];
					s[j] = temp;
				}
			}
			if (flag == true) {   //优化，如果有一趟没有交换过，说明交换完毕，直接退出
				break;
			}else {
				flag = true;
			}
		}
		System.out.println("计算了：" + count);
		return s;
	}

//	public static void show(int[] j) {
//		for (int i = 0; i < j.length; i++) {
//			System.out.print(j[i] + " ");
//		}
//		System.out.println();
//	}
	/**
	 * 计时
	 */
	public static void getTimr() {
		Calendar c = Calendar.getInstance();
        int mm = c.get(Calendar.MINUTE);
        int ss = c.get(Calendar.SECOND);
        int ms = c.get(Calendar.MILLISECOND);

		System.out.println("分:"+mm+" 秒:"+ss+" 毫秒:"+ms);
	}
}
