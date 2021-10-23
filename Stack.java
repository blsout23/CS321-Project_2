/*Ben Southwick and Dhruv Joshi
CS321 Project 2
Stack Implementation*/

import java.util.*;



public class Stack<E> {
	
	private LinkedList<E> ll;
	
	//init
	public Stack() {
		this.ll = new LinkedList<E>();
	}
	
	//remove top of stack and return value
	public E pop() {
		return this.ll.removeLast();
	}
	
	//add item to top of stack
	public void push(E obj) {
		this.ll.addLast(obj);
	}
	
	//clear all items from stakc
	public void clear() {
		this.ll.clear();
	}
	
	//convert the stack to an array
	public Object[] toArray() {
		return this.ll.toArray();
	}
		
	
	//   TEST METHODS
	public static void test_push() {
		Stack<Integer> stackInt = new Stack<Integer>();
		stackInt.push(1);
		assert stackInt.toArray().equals(new Integer[] {1});
		stackInt.push(2);
		stackInt.push(3);
		assert stackInt.toArray().equals(new Integer[] {1, 2, 3});
		
		Stack<Double> stackDbl = new Stack<Double>();
		stackDbl.push(1.1);
		assert stackDbl.toArray().equals(new Double[] {1.1});
		stackDbl.push(2.2);
		stackDbl.push(3.3);
		assert stackDbl.toArray().equals(new Double[] {1.1, 2.2, 3.3});
		
		Stack<String> stackStr = new Stack<String>();
		stackStr.push("a");
		assert stackStr.toArray().equals(new String[] {"a"});
		stackStr.push("b");
		stackStr.push("c");
		assert stackStr.toArray().equals(new String[] {"a", "b", "c"});
	}
	
	public static void test_pop() {
		Stack<Integer> stackInt = new Stack<Integer>();
		stackInt.push(1);
		Integer a = stackInt.pop();
		assert stackInt.toArray().equals(new Integer[] {});
		assert a == 1;
		stackInt.push(1);
		stackInt.push(2);
		stackInt.push(3);
		a = stackInt.pop();
		assert stackInt.toArray().equals(new Integer[] {1, 2});
		assert a == 3;
		stackInt.pop();
		stackInt.pop();
		assert stackInt.toArray().equals(new Integer[] {});
		
		Stack<Double> stackDbl = new Stack<Double>();
		stackDbl.push(1.1);
		Double b = stackDbl.pop();
		assert stackDbl.toArray().equals(new Double[] {});
		assert b == 1.1;
		stackDbl.push(1.1);
		stackDbl.push(2.2);
		stackDbl.push(3.3);
		b = stackDbl.pop();
		assert stackDbl.toArray().equals(new Double[] {1.1, 2.2});
		assert b == 3.3;
		stackDbl.pop();
		stackDbl.pop();
		assert stackDbl.toArray().equals(new Double[] {});
		
		Stack<String> stackStr = new Stack<String>();
		stackStr.push("a");
		String c = stackStr.pop();
		assert stackStr.toArray().equals(new String[] {});
		assert c == "a";
		stackStr.push("a");
		stackStr.push("b");
		stackStr.push("c");
		c = stackStr.pop();
		assert stackStr.toArray().equals(new String[] {"a", "b"});
		assert c == "c";
		stackStr.pop();
		stackStr.pop();
		assert stackStr.toArray().equals(new String[] {});
		
	}
	
	
	public static void test_clear() {
		Stack<Integer> stackInt = new Stack<Integer>();
		stackInt.push(1);
		stackInt.clear();
		assert stackInt.toArray().equals(new Integer[] {});
		stackInt.push(1);
		stackInt.push(2);
		stackInt.push(3);
		assert stackInt.toArray().equals(new Integer[] {1, 2, 3});
		
		Stack<Double> stackDbl = new Stack<Double>();
		stackDbl.push(1.1);
		stackDbl.clear();
		assert stackDbl.toArray().equals(new Double[] {});
		stackDbl.push(1.1);
		stackDbl.push(2.2);
		stackDbl.push(3.3);
		assert stackDbl.toArray().equals(new Double[] {1.1, 2.2, 3.3});
		
		Stack<String> stackStr = new Stack<String>();
		stackStr.push("a");
		stackStr.clear();
		assert stackStr.toArray().equals(new String[] {});
		stackStr.push("a");
		stackStr.push("b");
		stackStr.push("c");
		assert stackStr.toArray().equals(new String[] {"a", "b", "c"});
	}
	
	//run test methods
	public static void main( String[] argv ) {
		test_push();
		test_pop();
		test_clear();
	}
}