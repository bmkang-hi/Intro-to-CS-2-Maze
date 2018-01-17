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
 * FILE:		DLLNode.java
 * 
 * DESCRIPTION:	This is the Node defining class for the Double Linked List class
 * 
 **************************************************/
public class DLLNode<E>{
	private E data; //points to the data element stored in this node
	private DLLNode<E> next;	//Points to the next DLLNode<E> in the list, points to Null at end of list.
	private DLLNode<E> prev;	//points to the previous node in the list, points to null at beginning of list

	//construct by passing data for creating end of list node
	/*************************************************
	 * 
	 * METHOD:			DLLNode
	 * 
	 * DESCRIPTION:		Constructor for DLL initial node linked to null
	 * 
	 * @param			none
	 * 
	 * @return			none
	 * 
	 *************************************************/
	public DLLNode(E dat){
		data = dat;
		next = null;
		prev = null;
	}


	//Insertion node creation, sets the next node to passed Node
	/*************************************************
	 * 
	 * METHOD:			DLLNode
	 * 
	 * DESCRIPTION:		Consructor for DLL nodes that maintain links to other nodes
	 * 
	 * @param			none
	 * 
	 * @return			none
	 * 
	 *************************************************/
	public DLLNode(E dat, DLLNode<E> nextNode, DLLNode<E> prevNode){
		data = dat;
		next = nextNode;
		prev = prevNode;
	}
	
	/*************************************************
	 * 
	 * METHOD:			DLLNode
	 * 
	 * DESCRIPTION:		Constructor for DLL nodes at the end of list
	 * 
	 * @param			none
	 * 
	 * @return			none
	 * 
	 *************************************************/
	public DLLNode(E dat, DLLNode<E> prevNode){
		data = dat;
		prev = prevNode;
		next = null;
	}
	
	/*************************************************
	 * 
	 * METHOD:			DLLNode
	 *
	 * DESCRIPTION:		Constructor for DLL node inserted into front of list
	 * 
	 * @param			none
	 * 
	 * @return			none
	 * 
	 *************************************************/
	public DLLNode(DLLNode<E> nextNode, E dat){
		data = dat;
		next = nextNode;
		prev = null;
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
	public DLLNode<E> getNext(){
		return next;
	}
	
	
	/*************************************************
	 * 
	 * METHOD:			getPrev
	 * 
	 * DESCRIPTION:		gettor method for prev node reference
	 * 
	 * @return			prev
	 *************************************************/
	public DLLNode<E> getPrev(){
		return prev;
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
	public void setNext(DLLNode<E> nextRef){
		next = nextRef;
	}
	
	/*************************************************
	 * 
	 * METHOD:			setPrev
	 * 
	 * DESCRIPTION:		settor method for prev node reference
	 * 
	 * @param prevRef
	 *************************************************/
	public void setPrev(DLLNode<E> prevRef){
		prev = prevRef;
	}

}