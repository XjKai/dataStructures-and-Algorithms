package com.XJK.stack;


/**
 * @author MSI
 * 用栈完成计算器
 */
public class Calculator {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String prob = "2*(3+1+3*(1-1))-100+2*(3-5)+6/(1+5)";
		System.out.println(calcString(prob));
	}
	
	/**
	 * 单步计算
	 * @param num1
	 * @param num2
	 * @param h
	 * @return
	 */
	public static int calc(int num1, int num2, char h) {
		switch (h) {
		case '+':
			return num1 + num2;
		case '-':
			return num1 - num2;
		case '*':
			return num1 * num2;
		case '/':
			return num1 / num2;

		default:
			throw new IllegalArgumentException("Unexpected value: ");
		}
	}
	/**
	 * 是否是括号
	 * @param h
	 * @return
	 */
	public static boolean  isBracket(char h) {
		return h == '(' || h == ')';
	}
	/**
	 * 判断符号优先级
	 * @param h
	 * @return
	 */
	public static int priority(char h) {
		switch (h) {
		case '+':
			return 0;
		case '-':
			return 0;
		case '*':
			return 1;
		case '/':
			return 1;

		default:
			throw new IllegalArgumentException("Unexpected value: ");
		}
	}
	/**
	 * 是否是计算符
	 * @param h
	 * @return
	 */
	public static boolean isOper(char h) {
		return h == '+' || h == '-' || h == '*' || h == '/';
	}
	/**
	 * 实现计算字符串     
	 * @param s
	 */
	public static int calcString(String s) {
		int index = 0;
		int num1,num2,op;
		char current;
		ArrayStack1 numArrayStack = new ArrayStack1(s.length());   //数字栈
		ArrayStack1 operArrayStack = new ArrayStack1(s.length());  //算符栈
		
		while (true) {
			current = s.subSequence(index, index+1).charAt(0);
			index++;
			if(isOper(current)) {    //是算符
				if(operArrayStack.isEmpty()) {     //算符栈为空直接入栈    
					operArrayStack.push(current);
				}else {
					if(priority(current) <= priority((char)operArrayStack.peek())) {      //!!!!!!!!!小于等于是对的!!!!!!!!!
						//如果当前的运算符优先级“小于等于”栈顶的运算符优先级，
						//就把数字栈的两个值pop出来以及把符号栈的一个值pop出来计算。小于等于：其实也可以用小于，
						//但是又可能这里会发生链式反应（如：+***的情况），这里就要改成while循环
							num2 = numArrayStack.pop();
							num1 = numArrayStack.pop();
							op = operArrayStack.pop();
							numArrayStack.push(calc(num1,num2,(char)op));    //这里num1和num2顺序不能换
							operArrayStack.push(current);
					}else {
						operArrayStack.push(current);
					}
				}
			}else if (! isBracket(current)){    //是数字
				String t = ""+current;
				while(true) {       //拼接
					//当index == s.length()就已经指向界外的一个了
					if(index == s.length() || isOper(s.subSequence(index, index+1).charAt(0))) {    //这里的index 已经是指向了current的下一个了
						break;
					}
					t = t + s.subSequence(index, index+1).charAt(0);
					index++;
				}
				numArrayStack.push(Integer.parseInt(t));
			}else {        //是括号
				String newS = "";    //括号里的计算串
				int bracketCount = 1; //放置括号里面有括号 
				while (true) {     
					if(s.subSequence(index, index+1).charAt(0) == '(') {    //如果再次遇到了'('
						bracketCount++;
					}
					if(s.subSequence(index, index+1).charAt(0) == ')') {  
						bracketCount--;
					}	
					if(s.subSequence(index, index+1).charAt(0) == ')' && bracketCount == 0) {      //确保拿到的是匹配的'('和')'
						index++;   //使index指向')'字符的下一个字符，即剔除')'     
						break;
					}
					newS = newS + s.subSequence(index, index+1).charAt(0);
					index++;
				}
				numArrayStack.push(calcString(newS));   //递归调用
			}
			if(index == s.length()) {
				break; 
			}
		}//入栈完毕
		while (true) {
			if(operArrayStack.isEmpty()) {
				break;
			}
			num2 = numArrayStack.pop();
			num1 = numArrayStack.pop();
			op = operArrayStack.pop();
			numArrayStack.push(calc(num1,num2,(char)op));    //这里num1和num2顺序不能换
		}
		return numArrayStack.peek();
	}
}



class ArrayStack1{
	private int size;
	private int[] arr;
	private int top = -1;  //栈顶指针初始化指向-1
	
	public ArrayStack1(int size) {
		this.size = size;
		arr =new int[size];
	}
	/**
	 * 栈是否满
	 * @return
	 */
	public boolean isFull() {
		if(top == size-1) {
			return true;
		}
		return false;
	}
	/**
	 * 栈是否空
	 * @return
	 */
	public boolean isEmpty() {
		if(top == -1) {
			return true;
		}
		return false;
	}
	/**
	 * 入栈
	 * @param n
	 */
	public void push(int n) {
		if (isFull()) {
			System.out.println("栈已满");
			return;
		}
		top++;
		arr[top] = n;
	}
	/**
	 * 查看栈顶的值
	 * @return
	 */
	public int peek() {
		if (isEmpty()) {
			throw new RuntimeException("栈为空");
		}
		return arr[top];
	}
	/**
	 * 出栈
	 * @return
	 */
	public int pop() {
		if(isEmpty()) {
			throw new RuntimeException("栈为空");
		}
		int value = arr[top];
		top--;
		return value;
	}
	/**
	 * 显示栈，先显示后进的
	 */
	public void show() {
		if (isEmpty()) {
			System.out.println("栈为空");
			return;
		}
		for (int i = top; i >= 0; i--) {
			System.out.printf("arr[%d] : %d \n",i,arr[i]);
		}
	}
}