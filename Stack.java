import java.util.EmptyStackException;

/*************************************************
 * 
 * NAME:		Brandon Kang
 * 
 * HOMEWORK:	5
 * 
 * CLASS:		ICS 211
 * 
 * INSTRUCTOR:	Scott Robertson
 * 
 * DATE:		Feb 25, 2016
 * 
 * FILE:		Stack.java
 * 
 * DESCRIPTION:	This file defines the Stack class and defines its methods and fields
 * 
 **************************************************/
public class Stack<E> {
	SLL<E> sList;
	int size=0;

	public Stack(){
		sList = new SLL<E>();
	}
	//: Pushes the given element to the top of the stack and returns the element
	//should operate in O(1) time
	/*************************************************
	 * 
	 * METHOD:			push
	 * 
	 * DESCRIPTION:		Pushes given parameter onto the top of the stack
	 * 
	 * @param element
	 * @return element
	 *************************************************/
	public E push(E element){
		sList.add(0, element);
		size++;
		return element;
	}

	//: Removes and returns the element at the top of the Stack, or throws an EmptyStackException otherwise
	//should operate in O(1) time
	/*************************************************
	 * 
	 * METHOD:			pop
	 * 
	 * DESCRIPTION:		Takes first element off top of stack and returns it if stack is empt throws an EmptyStackException
	 * 
	 * @return popElem
	 *************************************************/
	public E pop(){
		if(size <= 0){
			throw new EmptyStackException();
		}
		else{
			E popElem = sList.removeIndex(0);
			size--;
			return popElem;
		}
	}

	//: Returns the element at the top of the Stack, or throws an EmptyStackException otherwise
	/*************************************************
	 * 
	 * METHOD:			peek
	 * 
	 * DESCRIPTION:		Returns the first element off top of stack, does not remove element from stack. If stack is empty, throws an EmptyStackException
	 * 
	 * @return	E
	 *************************************************/
	public E peek(){
		if(size <=0){
			throw new EmptyStackException();
		}
		else{
			return sList.get(0);
		}
	}

	//: Returns true if the Stack is empty, or false otherwise
	/*************************************************
	 * 
	 * METHOD:			empty
	 * 
	 * DESCRIPTION:		Returns true if the stack is empty, otherwise returns false.
	 * 
	 * @return boolean
	 *************************************************/
	public boolean empty(){
		return sList.isEmpty();
	}

	/*************************************************
	 * 
	 * METHOD:		 toString
	 * 
	 * DESCRIPTION:		Puts the elements in the stack into a string.
	 * 
	 * @return stackString
	 *************************************************/
	public String toString(){
		String stackString = "[";
		if(empty()){
			stackString = "[]";
		}
		else{
			for(int i=0; i<size;i++){
				if(i == size-1){
					stackString+= sList.get(i) + "]";
				}
				else{
					stackString += sList.get(i) + ", ";
				}
			}
		}
		return stackString;
	}

}
