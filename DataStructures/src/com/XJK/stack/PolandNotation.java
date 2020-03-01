package com.XJK.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;






/**
 * @author MSI
 * ����׺���ʽת��Ϊ��׺���ʽ(�沨�����ʽ)
 * �沨�����ʽ(��׺���ʽ)����
 * ��ͷ�� ������ʽ
 * ""+int���Կ���תΪ�ַ���
 */
public class PolandNotation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String expressionString = "3 4 + 5 * 6 -";
		//System.out.println(calc(expressionString));
		
		String s = "1*5.0+ 9+( 3.0- 5.0 )  *  2.55";
		System.out.println(getListOfExpression(s));
		System.out.println(infixToPostfix(getListOfExpression(s)));
		System.out.println(calc(infixToPostfix(getListOfExpression(s))));
	
	}
	
	/**
	 * �����沨�����ʽ
	 * @param expressionString
	 * @return
	 */
	public static float calc(List<String> expressionList) {
		float num1,num2;
		Stack<String> stack = new Stack<String>();
		for(String item : expressionList) {
			if(item.substring(0, 1).matches("\\d+")) {  //ƥ���Ƕ�λ��  ������ʽ
				stack.push(item);
			}
			else {
				if(item.equals("+")) {
					num2 = Float.parseFloat(stack.pop());    
					num1 = Float.parseFloat(stack.pop());
					stack.push(""+(num1+num2));    //""+int  ���Կ���ת��Ϊ�ַ���
				}
				if(item.equals("-")) {
					num2 = Float.parseFloat(stack.pop());
					num1 = Float.parseFloat(stack.pop());
					stack.push(""+(num1-num2));    //""+int  ���Կ���ת��Ϊ�ַ���							
				}
				if(item.equals("*")) {
					num2 = Float.parseFloat(stack.pop());
					num1 = Float.parseFloat(stack.pop());
					stack.push(""+(num1*num2));    //""+int  ���Կ���ת��Ϊ�ַ���		
				}
				if(item.equals("/")) {
					num2 = Float.parseFloat(stack.pop());
					num1 = Float.parseFloat(stack.pop());
					stack.push(""+(num1/num2));    //""+int  ���Կ���ת��Ϊ�ַ���					
				}
			}
		}
		return Float.parseFloat(stack.pop());
	}
	/**
	 *���ַ�����ÿ���ַ�����
	 * @param expression
	 * @return
	 */
	public static List<String> getListOfExpression(String expression) {
		int index = 0;
		List<String> list = new ArrayList<String>();
		do {

			if((expression.charAt(index) < 48 || expression.charAt(index) > 57)  && expression.charAt(index) != 46 && expression.charAt(index) != 32) {    //�Ƿ���,������С����Ϳո�
				list.add("" + expression.charAt(index));
				index++;
			}else if (expression.charAt(index) == 32) { 			//���˿ո񡢣��Ʊ�����ͻ��з�---��Ӧ��ASKll�뻹û�ӽ�ȥ��
				index++;
			}else {
				String t = "";
				while (index < expression.length() &&((expression.charAt(index)  >= 48 && expression.charAt(index) <=57) || expression.charAt(index) ==46)) {
					t = t + expression.charAt(index);
					index++;
				}
				list.add(t);
			}
		} while (index < expression.length());
		return list;
	}
	/**
	 * �Ƿ�������
	 * @param h
	 * @return
	 */
	public static boolean  isBracket(String h) {
		return h.equals("(") || h.equals(")");
	}
	/**
	 * �жϷ������ȼ�
	 * @param h
	 * @return
	 */
	public static int priority(String h) {
		if(h.equals("+") || h.equals("-")) {
			return 0;
		}else if (h.equals("*") || h.equals("/")) {
			return 1;
		}
		return 2;

	}
	/**
	 * �Ƿ��Ǽ����
	 * @param h
	 * @return
	 */
	public static boolean isOper(String h) {
		return h.equals("+") || h.equals("-") || h.equals("*") || h.equals("/");
	}
    /**
         * ����׺���ʽתΪ��׺���ʽ
     * @param list
     */
	public static List<String> infixToPostfix(List<String> list) {
		Stack<String>  operStack = new Stack<String>();   
		Stack<String>  numStack = new Stack<String>();          
		List<String>  outList = new ArrayList<String>();   //��Ž��
		int index = 0;
		String tempString;
		do {
			tempString = list.get(index);
			//System.out.println("index:"+index);
			//System.err.println("size:"+list.size());
			//System.out.println(tempString);
			if (isOper(tempString)) { //�������
				while (true) {     //ѭ���Ƚ�ֱ�����������˳�
					if(operStack.isEmpty() || isBracket(operStack.peek()) || priority(tempString) > priority(operStack.peek())) {  //����ջΪ�ա�����ջջ��Ԫ��Ϊ���š���ǰ�������ȼ����ڷ���ջջ��Ԫ�����ȼ����ҵ�ǰ����Ϊ�������ֱ����ջ
						operStack.push(tempString);
						index++;
						break;     //���ټ���ȥ�����ջ��Ԫ�رȽϣ��˳�
					}else if(priority(tempString) <= priority(operStack.peek())) {   //��ǰ���ŵ����ȼ�С�ڵ��ڷ���ջջ���������ȼ�  ���������if�ж���ʵ���Բ��ü�
						//������ջջ��Ԫ�ص�����ѹ������ջ
						numStack.push(operStack.pop());
						//��ͷ���������ջջ��Ԫ�رȽϣ�û��break
					}
				}
			}else if (isBracket(tempString)) {   //������
				if(tempString.equals("(")) {  //������ֱ����ջ
					operStack.push(tempString);
					index++;
				}else {  //��ǰΪ������
					while (!operStack.peek().equals("(")) {   //�ӷ���ջ�е���Ԫ��,ֱ�����������ţ���һ�����Ŷ���
						numStack.push(operStack.pop());
					}
					operStack.pop();  //����������
					index++;   //����������
				}	
			}else {  //�����֣�ֱ����ջ
				numStack.push(tempString);
				index++;
			}
		} while (index < list.size());   //������������
		//������ջ�е�Ԫ�����ε�������ѹ����ջ
		while (!operStack.isEmpty()) {
			numStack.push(operStack.pop());
		}
        //������ջ
		Stack<String>  tempStack = new Stack<String>();  //��ʱջ�����ڷ�ת
		while (!numStack.isEmpty()) {
			tempStack.push(numStack.pop());
		}
		while (!tempStack.isEmpty()) {
			outList.add(tempStack.pop());
		}
		return outList;
	}

}
