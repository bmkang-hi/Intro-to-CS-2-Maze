import java.util.NoSuchElementException;

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
 * FILE:		Queue.java
 * 
 * DESCRIPTION:	This file defines the queue class an its methods and fields
 * 
 **************************************************/
public class Queue<E> {
	DLL<E> qList; 
	int size;
	public Queue(){
		qList = new DLL<E>();
		size=0;
	}

	//should operate in O(1) time
	/*************************************************
	 * 
	 * METHOD:			offer
	 * 
	 * DESCRIPTION:		adds a new element to the end of the queue, returns the added element
	 * 
	 * @param element
	 * @return element
	 *************************************************/
	public E offer(E element){
		qList.add(element);
		size++;
		return element;
	}


	//: Removes and returns the head of the queue, or returns null if the queue is empty
	//Should operate in O(1) time
	/*************************************************
	 * 
	 * METHOD:			poll
	 * 
	 * DESCRIPTION:		Removes and returns the element from the front of the queue, returns null if the queue is empty
	 * 
	 * @return pollElem
	 *************************************************/
	public E poll(){
		if(qList.isEmpty()){
			return null;
		}
		else{
			E pollElem = qList.removeIndex(0);
			size--;
			return pollElem;
		}
	}


	//: Removes and returns the head of the queue, or throws a NoSuchElementException if the queue is empty
	//should run in O(1) time
	/*************************************************
	 * 
	 * METHOD:			remove
	 * 
	 * DESCRIPTION:		Removes and returns the element from the front of the queue. If the queue is empty, throws a NoSuchElementException
	 * 
	 * @return remElem
	 *************************************************/
	public E remove(){
		if(qList.isEmpty()){
			throw new NoSuchElementException();
		}
		else{
			E remElem = qList.removeIndex(0);
			size--;
			return remElem;
		}
	}	

	//: Returns the head of the queue, or returns null if the queue is empty
	//should run in O(1) time
	/*************************************************
	 * 
	 * METHOD:			peek
	 * 
	 * DESCRIPTION:		Returns the element at the front of the queue, returns null if the queue is empty
	 * 
	 * @return E
	 *************************************************/
	public E peek(){
		if(qList.isEmpty()){
			return null;
		}
		else{
			return qList.get(0);
		}
	}

	//: Returns the head of the queue, or throws a NoSuchElementException if the queue is empty
	//should run in O(1) time
	/*************************************************
	 * 
	 * METHOD:			element
	 * 
	 * DESCRIPTION:		Returns the value of the element at the front of the queue, throws NoSuchElementException if queue is empty
	 * 
	 * @return E
	 *************************************************/
	public E element(){
		if(qList.isEmpty()){
			throw new NoSuchElementException();
		}
		else{
			return qList.get(0);
		}
	}

	/*************************************************
	 * 
	 * METHOD:			toString
	 * 
	 * DESCRIPTION:		Converts the data stored in the queue into a string representation and returns that string.
	 * 
	 * @return queueString
	 *************************************************/
	public String toString(){
		String queueString = "[";
		if(qList.isEmpty()){
			queueString = "[]";
		}
		for(int i=0; i<size;i++)
			if(i == size-1){
				queueString+= qList.get(i) + "]";
			}
			else{
				queueString += qList.get(i) + ", ";
			}
		return queueString;
	}

	/*************************************************
	 * 
	 * METHOD:			empty
	 * 
	 * DESCRIPTION:		Returns true if the queue is empty, otherwise returns false.
	 * 
	 * @return boolean
	 *************************************************/
	public boolean empty(){
		return qList.isEmpty();
	}
}
