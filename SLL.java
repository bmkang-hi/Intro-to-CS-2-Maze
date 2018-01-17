
/*************************************************
 * 
 * NAME:		Brandon Kang
 * 
 * HOMEWORK:	4
 * 
 * CLASS:		ICS 211
 * 
 * INSTRUCTOR:	Scott Robertson
 * 
 * DATE:		Feb 16, 2016
 * 
 * FILE:		SLL.java
 * 
 * DESCRIPTION:	This class defines the methods of the SLL, single linked list class
 * 
 **************************************************/
import java.util.*;

public class SLL<E> {

	/*Needs a node class - CH2 of book has node class*/
	private SLLNode<E> head;
	private int size;

	/*Constructor*/
	/*************************************************
	 * 
	 * METHOD:			SLL
	 * 
	 * DESCRIPTION:		Constructor for SLL, single linked lists
	 * 
	 * @param			none
	 * 
	 * @return			none
	 * 
	 *************************************************/
	public SLL(){
		/*
		 *when a new single linked list is created, needs to set teh size of the list, and indicate the head node.
		 *Implement using Collection of Node objects
		 */
		head = null;
		size = 0;
	}


	/*Adds the element to the end of the list*/
	/*************************************************
	 * 
	 * METHOD:			add
	 * 
	 * DESCRIPTION:		This method adds a node containing the data passed in element to the end of the list.
	 * 
	 * @param element
	 * @return none
	 *************************************************/
	public boolean add(E element){
		/* Adds a node to the end of the list
go to end of list (when next is null)
change last nodes nextRef to the new node.
		 */
		SLLNode<E> currNode;
		if (head == null){
			head = new SLLNode<E>(element);
			size++;
			return true;
		}
		else{
			currNode = head;
			while(currNode.getNext() != null){
				currNode = currNode.getNext();
			}
			currNode.setNext(new SLLNode<E>(element));
			size++;
			return true;
		}
	}

	/*Adds the element to the given index*/
	/*************************************************
	 * 
	 * METHOD:			add
	 * 
	 * DESCRIPTION:		This add method inserts the data passed in element into a node at the index specified.
	 * 
	 * @param index
	 * @param element
	 * @return none
	 *************************************************/
	public boolean add(int index, E element){
		//Inserts node at given index, resizes the list appropriately.
		SLLNode<E> tempNode;

		if(index == 0 && head == null){
			return add(element);//also returns boolean, inserting to empty list is same as appending to empty list

		}
		else if(index == 0){
			tempNode = new SLLNode<E>(element, head);
			head = tempNode;
			size++;
			return true;
		}
		else if(index > 0 && index < size){
			tempNode = head;
			for(int i=0;i<index-1;i++){
				tempNode = tempNode.getNext();
			}
			tempNode.setNext(new SLLNode<E>(element, tempNode.getNext()));
			size++;
			return true;
		}
		else{
			return false;
		}
	}

	/*Clears all elements in the list*/
	/*************************************************
	 * 
	 * METHOD:			clear
	 * 
	 * DESCRIPTION:		This method clears all elements in the list.
	 * 
	 *************************************************/
	public void clear(){
		if(size != 0 && head != null){
			head = null;
			size = 0;
		}
	}

	/*Returns true if the list contains the given element. Returns false otherwise.*/
	/*************************************************
	 * 
	 * METHOD:			contains
	 * 
	 * DESCRIPTION:		This method searches the list and returns true if it finds any element that equals the passed argument element. It returns false if there are no matches
	 * 
	 * @param element
	 * @return boolean
	 *************************************************/
	public boolean contains(E element){
		SLLNode<E> tempNode = head;
		for(int i=0;i<size;i++){
			if(tempNode.getData().equals(element)){
				return true;
			}
			tempNode = tempNode.getNext();
		}
		return false;
	}


	/*Returns the element at the given index. Returns null if the index is out of bounds*/
	/*************************************************
	 * 
	 * METHOD:			get
	 * 
	 * DESCRIPTION:		This method returns the data stored in the node at the location specified by the argument, index
	 * 
	 * @param index
	 * @return E
	 *************************************************/
	public E get(int index){
		SLLNode<E> tempNode = head;
		int indRef;
		if(index < 0 || index > size-1){
			return null;
		}
		else if(index == 0 && size >0){
			return head.getData();
		}
		else{
			for(indRef=0; indRef<index;indRef++){
				tempNode = tempNode.getNext();
			}
			return tempNode.getData();
		}
	}


	/*Returns the index of the first occurrence of the element or -1 if the element does not exist.*/
	/*************************************************
	 * 
	 * METHOD:			indexOf
	 * 
	 * DESCRIPTION:		This method returns the index of the first occurrence of any data that matches the argument, element. If there are no matches it returns -1.
	 * 
	 * @param element
	 * @return int
	 *************************************************/
	public int indexOf(E element){
		SLLNode<E> tempNode = head;
		for(int i=0;i<size;i++){
			if(tempNode.getData().equals(element)){
				return i;
			}
			tempNode = tempNode.getNext();
		}
		return -1;
	}


	/*Returns true if the list is empty or false if the list is not empty.*/
	/*************************************************
	 * 
	 * METHOD:			isEmpty
	 * 
	 * DESCRIPTION:		This method checks if the list is empty and returns true if it is, otherwise it returns false.
	 * 
	 * @return boolean
	 *************************************************/
	public boolean isEmpty(){
		if(size == 0){
			return true;
		}
		else{
			return false;
		}
	}


	/*Returns the index of the last occurrence of the element or -1 if the element does not exist.*/
	/*************************************************
	 * 
	 * METHOD:			lastIndexOf
	 * 
	 * DESCRIPTION:		This method returns the index of the last occurrence of data that matches the argument, element. If there are no matches it returns -1
	 * 
	 * @param element
	 * @return int
	 *************************************************/
	public int lastIndexOf(E element){
		int matchingIndex = -1;
		SLLNode<E> tempNode = head;
		for(int i = 0;i<size;i++){
			if(tempNode.getData().equals(element)){
				matchingIndex = i;
			}
			tempNode = tempNode.getNext();
		}
		return matchingIndex;
	}


	/*Removes the element at the given index. Returns the removed element or null if no element
was removed*/
	/*************************************************
	 * 
	 * METHOD:			removeIndex
	 * 
	 * DESCRIPTION:		This method removes the node at the index specified in the parameter, index. It returns the data that was removed or null if nothing was removed.
	 * 
	 * @param index
	 * @return E
	 *************************************************/
	public E removeIndex(int index){
		//need storage for removed element, need t change reference of element BEFORE the one to be removed to refer to the element referred to by the removed one
		SLLNode<E> removedNode;
		SLLNode<E> tempNode = head;
		if(index == 0 && size >0){
			removedNode = head;
			head = head.getNext();
			size--;
			return removedNode.getData();
		}
		else if(index <size && index >0){
			for(int i=0;i<index-1;i++){
				tempNode = tempNode.getNext();
			}
			removedNode = tempNode.getNext();
			tempNode.setNext(removedNode.getNext());
			size--;
			return removedNode.getData();
		}
		else{
			return null;
		}
	}


	/*Removes the first occurrence of the element. Returns the removed element or null if no
element was removed*/
	/*************************************************
	 * 
	 * METHOD:			removeElement
	 * 
	 * DESCRIPTION:		This method searches the array for the first occurrence of data matching the arg, element. It then removes that occurrence and returns the data. If nothing was removed it returns null.
	 * 
	 * @param element
	 * @return E
	 *************************************************/
	public E removeElement(E element){//!!!!!NOTE!!!! - WHen analyzing for Big-O YOu need to ensure yo trace through the methods called, i.e. if indexOf is O(n) and removeIndex is O(n) then remove element is O(n^2)
		int remIndex = indexOf(element);
		return removeIndex(remIndex);
	}


	/*Sets the value at the given index to the given element. Returns the previous value or null if the
index is out of bounds*/
	/*************************************************
	 * 
	 * METHOD:			set
	 * 
	 * DESCRIPTION:		This method changes the data stored in the node specified by the arg, index. The data is changed to what was passed in the arg, element. It returns the previous value that was replaced, or null if nothing was changed.
	 * 
	 * @param index
	 * @param element
	 * @return E
	 *************************************************/
	public E set(int index, E element){
		//involves changeg node.data of element, requires navigating to point in index
		SLLNode<E> tempNode = head;
		E prevVal = null;
		if(index >0 && index <size){
			for(int i=0;i<index;i++){
				tempNode = tempNode.getNext();
			}
			prevVal = tempNode.getData();
			tempNode.setData(element);
			return prevVal;
		}
		else if(index == 0 && size > 0){
			prevVal = head.getData();
			head.setData(element);
			return prevVal;
		}
		else{
			return null;
		}
	}


	/*Returns the number of elements in this list.*/
	/*************************************************
	 * 
	 * METHOD:			size
	 * 
	 * DESCRIPTION:		This method returns the number of nodes in the list
	 * 
	 * @return size
	 *************************************************/
	public int size(){
		return size;
	}


	/*Returns a String representation of the list. If the list is empty, print [] Otherwise, print
[1, 2, 3, etc] You must include the brackets in your string or you will not get credit.*/
	/*************************************************
	 * 
	 * METHOD:			toString
	 * 
	 * DESCRIPTION:		This method converts the contents of the list into a comma delineated string of elements placed between brackets i.e. [E1, E2, ... En-1, En]. This string is returned
	 * 
	 * @return stringList
	 *************************************************/
	public String toString(){
		SLLNode<E> tempNode = head;
		String stringList = "[";
		if (tempNode == null || size<=0){
			stringList = "[]";
		}
		for(int i=0;i<size;i++){
			if(i == size-1){
				stringList += tempNode.getData() + "]";
			}
			else{
				stringList += tempNode.getData() + ", ";
				tempNode = tempNode.getNext();
			}
		}
		return stringList;
	}


	/*Returns an Iterator starting at the head of the list*/
	/*************************************************
	 * 
	 * METHOD:			Iterator
	 * 
	 * DESCRIPTION:		Returns a new SLLIterator for this single linked list
	 * 
	 * @return			SLLIterator
	 *************************************************/
	public SLLIterator Iterator(){
		//TODO Create an iterator for this Single Linked List
		return new SLLIterator();
	}


	//==============================================
	/*Private inner Iterator class*/
	private class SLLIterator implements Iterator<E> {


		private SLLNode<E> next;
		private int iterIndex = 0;

		/*Constructor*/
		/*************************************************
		 * 
		 * METHOD:			SLLIterator
		 * 
		 * DESCRIPTION:		Constructor for the SLLIterator
		 * 
		 * @param			none
		 * 
		 * @return			none
		 * 
		 *************************************************/
		public SLLIterator(){
			next = head;
		}
		//setup next 

		/*Returns true if the next method would return a value that is not null*/
		/*************************************************
		 * 
		 * METHOD:			hasNext
		 * 
		 * DESCRIPTION:		Returns true if the iterator has a next element available, returns false otherwise
		 * 
		 * @param			none
		 * 
		 * @return			boolean
		 *************************************************/
		public boolean hasNext(){
			if(next != null){
				return true;
			}
			else{
				return false;
			}
		}


		/*Returns the next value in the list*/
		/*************************************************
		 * 
		 * METHOD:			next
		 * 
		 * DESCRIPTION:		Returns the value stored in the next element in the list.
		 * 
		 * @param			none
		 * 
		 * @return			returnVal
		 *************************************************/
		public E next(){
			if(hasNext() == false){
				throw new NoSuchElementException();
			}
			else{
				E returnVal = next.getData();
				next = next.getNext();
				iterIndex++;
				return returnVal;
			}
		}


		/*Removes the last element returned by the next method*/
		/*************************************************
		 * 
		 * METHOD:			remove
		 * 
		 * DESCRIPTION:		Removes the element that was previously returned by this iterator's next() method
		 * 
		 * @param			none
		 * 
		 * @return			none
		 *************************************************/
		public void remove(){
			if(iterIndex >0){
				removeIndex(iterIndex-1);
				iterIndex--;
			}
			else{
				throw new IllegalStateException();
			}
		}


	}

}

