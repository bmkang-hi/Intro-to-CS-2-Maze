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
 * FILE:		SLLNode.java
 * 
 * DESCRIPTION:	This is the Node defining class for the Single Linked List class
 * 
 **************************************************/
public class SLLNode<E>{
	private E data; //points to the data element stored in this node
	private SLLNode<E> next;	//Points to the next SLLNode<E> in the list, points to Null at end of list.

	//construct by passing data for creating end of list node
	/*************************************************
	 * 
	 * METHOD:			SLLNode
	 * 
	 * DESCRIPTION:		Constructor for SLL end nodes linked to null
	 * 
	 * @param			none
	 * 
	 * @return			none
	 * 
	 *************************************************/
	public SLLNode(E dat){
		data = dat;
		next = null;
	}


	//Insertion node creation, sets the next node to passed Node
	/*************************************************
	 * 
	 * METHOD:			SLLNode
	 * 
	 * DESCRIPTION:		Consructor for SLL nodes that maintain links to other nodes
	 * 
	 * @param			none
	 * 
	 * @return			none
	 * 
	 *************************************************/
	public SLLNode(E dat, SLLNode<E> nextNode){
		data = dat;
		next = nextNode;
	}


	/*************************************************
	 * 
	 * METHOD:			getData
	 * 
	 * DESCRIPTION:		gettor method for node data
	 * 
	 * @return			data
	 *************************************************/
	public E getData(){
		return data;
	}


	/*************************************************
	 * 
	 * METHOD:			getNext
	 * 
	 * DESCRIPTION:		gettor method for next node reference
	 * 
	 * @return			next
	 *************************************************/
	public SLLNode<E> getNext(){
		return next;
	}


	/*************************************************
	 * 
	 * METHOD:			setData
	 * 
	 * DESCRIPTION:		settor method for node data
	 * 
	 * @param 			dat
	 *************************************************/
	public void setData(E dat){
		data = dat;
	}


	/*************************************************
	 * 
	 * METHOD:			setNext
	 * 
	 * DESCRIPTION:		settor method for next node reference
	 * 
	 * @param 			nextRef
	 *************************************************/
	public void setNext(SLLNode<E> nextRef){
		next = nextRef;
	}

}