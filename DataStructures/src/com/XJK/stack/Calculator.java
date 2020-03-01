package com.XJK.stack;


/**
 * @author MSI
 * ��ջ��ɼ�����
 */
public class Calculator {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String prob = "2*(3+1+3*(1-1))-100+2*(3-5)+6/(1+5)";
		System.out.println(calcString(prob));
	}
	
	/**
	 * ��������
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
	 * �Ƿ�������
	 * @param h
	 * @return
	 */
	public static boolean  isBracket(char h) {
		return h == '(' || h == ')';
	}
	/**
	 * �жϷ������ȼ�
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
	 * �Ƿ��Ǽ����
	 * @param h
	 * @return
	 */
	public static boolean isOper(char h) {
		return h == '+' || h == '-' || h == '*' || h == '/';
	}
	/**
	 * ʵ�ּ����ַ���     
	 * @param s
	 */
	public static int calcString(String s) {
		int index = 0;
		int num1,num2,op;
		char current;
		ArrayStack1 numArrayStack = new ArrayStack1(s.length());   //����ջ
		ArrayStack1 operArrayStack = new ArrayStack1(s.length());  //���ջ
		
		while (true) {
			current = s.subSequence(index, index+1).charAt(0);
			index++;
			if(isOper(current)) {    //�����
				if(operArrayStack.isEmpty()) {     //���ջΪ��ֱ����ջ    
					operArrayStack.push(current);
				}else {
					if(priority(current) <= priority((char)operArrayStack.peek())) {      //!!!!!!!!!С�ڵ����ǶԵ�!!!!!!!!!
						//�����ǰ����������ȼ���С�ڵ��ڡ�ջ������������ȼ���
						//�Ͱ�����ջ������ֵpop�����Լ��ѷ���ջ��һ��ֵpop�������㡣С�ڵ��ڣ���ʵҲ������С�ڣ�
						//�����ֿ�������ᷢ����ʽ��Ӧ���磺+***��������������Ҫ�ĳ�whileѭ��
							num2 = numArrayStack.pop();
							num1 = numArrayStack.pop();
							op = operArrayStack.pop();
							numArrayStack.push(calc(num1,num2,(char)op));    //����num1��num2˳���ܻ�
							operArrayStack.push(current);
					}else {
						operArrayStack.push(current);
					}
				}
			}else if (! isBracket(current)){    //������
				String t = ""+current;
				while(true) {       //ƴ��
					//��index == s.length()���Ѿ�ָ������һ����
					if(index == s.length() || isOper(s.subSequence(index, index+1).charAt(0))) {    //�����index �Ѿ���ָ����current����һ����
						break;
					}
					t = t + s.subSequence(index, index+1).charAt(0);
					index++;
				}
				numArrayStack.push(Integer.parseInt(t));
			}else {        //������
				String newS = "";    //������ļ��㴮
				int bracketCount = 1; //������������������ 
				while (true) {     
					if(s.subSequence(index, index+1).charAt(0) == '(') {    //����ٴ�������'('
						bracketCount++;
					}
					if(s.subSequence(index, index+1).charAt(0) == ')') {  
						bracketCount--;
					}	
					if(s.subSequence(index, index+1).charAt(0) == ')' && bracketCount == 0) {      //ȷ���õ�����ƥ���'('��')'
						index++;   //ʹindexָ��')'�ַ�����һ���ַ������޳�')'     
						break;
					}
					newS = newS + s.subSequence(index, index+1).charAt(0);
					index++;
				}
				numArrayStack.push(calcString(newS));   //�ݹ����
			}
			if(index == s.length()) {
				break; 
			}
		}//��ջ���
		while (true) {
			if(operArrayStack.isEmpty()) {
				break;
			}
			num2 = numArrayStack.pop();
			num1 = numArrayStack.pop();
			op = operArrayStack.pop();
			numArrayStack.push(calc(num1,num2,(char)op));    //����num1��num2˳���ܻ�
		}
		return numArrayStack.peek();
	}
}



class ArrayStack1{
	private int size;
	private int[] arr;
	private int top = -1;  //ջ��ָ���ʼ��ָ��-1
	
	public ArrayStack1(int size) {
		this.size = size;
		arr =new int[size];
	}
	/**
	 * ջ�Ƿ���
	 * @return
	 */
	public boolean isFull() {
		if(top == size-1) {
			return true;
		}
		return false;
	}
	/**
	 * ջ�Ƿ��
	 * @return
	 */
	public boolean isEmpty() {
		if(top == -1) {
			return true;
		}
		return false;
	}
	/**
	 * ��ջ
	 * @param n
	 */
	public void push(int n) {
		if (isFull()) {
			System.out.println("ջ����");
			return;
		}
		top++;
		arr[top] = n;
	}
	/**
	 * �鿴ջ����ֵ
	 * @return
	 */
	public int peek() {
		if (isEmpty()) {
			throw new RuntimeException("ջΪ��");
		}
		return arr[top];
	}
	/**
	 * ��ջ
	 * @return
	 */
	public int pop() {
		if(isEmpty()) {
			throw new RuntimeException("ջΪ��");
		}
		int value = arr[top];
		top--;
		return value;
	}
	/**
	 * ��ʾջ������ʾ�����
	 */
	public void show() {
		if (isEmpty()) {
			System.out.println("ջΪ��");
			return;
		}
		for (int i = top; i >= 0; i--) {
			System.out.printf("arr[%d] : %d \n",i,arr[i]);
		}
	}
}