package com.XJK.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;






/**
 * @author MSI
 * 将中缀表达式转换为后缀表达式(逆波兰表达式)
 * 逆波兰表达式(后缀表达式)计算
 * 回头看 正则表达式
 * ""+int可以快速转为字符串
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
	 * 计算逆波兰表达式
	 * @param expressionString
	 * @return
	 */
	public static float calc(List<String> expressionList) {
		float num1,num2;
		Stack<String> stack = new Stack<String>();
		for(String item : expressionList) {
			if(item.substring(0, 1).matches("\\d+")) {  //匹配是多位数  正则表达式
				stack.push(item);
			}
			else {
				if(item.equals("+")) {
					num2 = Float.parseFloat(stack.pop());    
					num1 = Float.parseFloat(stack.pop());
					stack.push(""+(num1+num2));    //""+int  可以快速转换为字符串
				}
				if(item.equals("-")) {
					num2 = Float.parseFloat(stack.pop());
					num1 = Float.parseFloat(stack.pop());
					stack.push(""+(num1-num2));    //""+int  可以快速转换为字符串							
				}
				if(item.equals("*")) {
					num2 = Float.parseFloat(stack.pop());
					num1 = Float.parseFloat(stack.pop());
					stack.push(""+(num1*num2));    //""+int  可以快速转换为字符串		
				}
				if(item.equals("/")) {
					num2 = Float.parseFloat(stack.pop());
					num1 = Float.parseFloat(stack.pop());
					stack.push(""+(num1/num2));    //""+int  可以快速转换为字符串					
				}
			}
		}
		return Float.parseFloat(stack.pop());
	}
	/**
	 *将字符串的每个字符分离
	 * @param expression
	 * @return
	 */
	public static List<String> getListOfExpression(String expression) {
		int index = 0;
		List<String> list = new ArrayList<String>();
		do {

			if((expression.charAt(index) < 48 || expression.charAt(index) > 57)  && expression.charAt(index) != 46 && expression.charAt(index) != 32) {    //是符号,但不是小数点和空格
				list.add("" + expression.charAt(index));
				index++;
			}else if (expression.charAt(index) == 32) { 			//过滤空格、（制表符、和换行符---对应的ASKll码还没加进去）
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
	 * 是否是括号
	 * @param h
	 * @return
	 */
	public static boolean  isBracket(String h) {
		return h.equals("(") || h.equals(")");
	}
	/**
	 * 判断符号优先级
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
	 * 是否是计算符
	 * @param h
	 * @return
	 */
	public static boolean isOper(String h) {
		return h.equals("+") || h.equals("-") || h.equals("*") || h.equals("/");
	}
    /**
         * 将中缀表达式转为后缀表达式
     * @param list
     */
	public static List<String> infixToPostfix(List<String> list) {
		Stack<String>  operStack = new Stack<String>();   
		Stack<String>  numStack = new Stack<String>();          
		List<String>  outList = new ArrayList<String>();   //存放结果
		int index = 0;
		String tempString;
		do {
			tempString = list.get(index);
			//System.out.println("index:"+index);
			//System.err.println("size:"+list.size());
			//System.out.println(tempString);
			if (isOper(tempString)) { //是运算符
				while (true) {     //循环比较直到满足条件退出
					if(operStack.isEmpty() || isBracket(operStack.peek()) || priority(tempString) > priority(operStack.peek())) {  //符号栈为空、符号栈栈顶元素为括号、当前符号优先级大于符号栈栈顶元素优先级，且当前符号为运算符，直接入栈
						operStack.push(tempString);
						index++;
						break;     //不再继续去与符号栈顶元素比较，退出
					}else if(priority(tempString) <= priority(operStack.peek())) {   //当前符号的优先级小于等于符号栈栈顶符号优先级  ，，这里的if判断其实可以不用加
						//将符号栈栈顶元素弹出，压入数字栈
						numStack.push(operStack.pop());
						//回头继续与符号栈栈顶元素比较，没有break
					}
				}
			}else if (isBracket(tempString)) {   //是括号
				if(tempString.equals("(")) {  //左括号直接入栈
					operStack.push(tempString);
					index++;
				}else {  //当前为右括号
					while (!operStack.peek().equals("(")) {   //从符号栈中弹出元素,直到弹出左括号，这一对括号丢弃
						numStack.push(operStack.pop());
					}
					operStack.pop();  //弹出左括号
					index++;   //丢弃右括号
				}	
			}else {  //是数字，直接入栈
				numStack.push(tempString);
				index++;
			}
		} while (index < list.size());   //运算符遍历完毕
		//将符号栈中的元素依次弹出，并压入数栈
		while (!operStack.isEmpty()) {
			numStack.push(operStack.pop());
		}
        //逆序数栈
		Stack<String>  tempStack = new Stack<String>();  //临时栈，用于翻转
		while (!numStack.isEmpty()) {
			tempStack.push(numStack.pop());
		}
		while (!tempStack.isEmpty()) {
			outList.add(tempStack.pop());
		}
		return outList;
	}

}
