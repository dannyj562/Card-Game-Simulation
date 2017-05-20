/*
	Danny Rivera
	CSC 311 - 01
	Dr Amlan Chatterjee
	Project 03
	April 6, 2017
	Implementation of a Single Linked List
*/
public class SingleLinkedList
{	
	private Node head;
	private int size;


	public SingleLinkedList()
	{
		this.head = new Node(-1);
		this.size = 0;
	}

	public SingleLinkedList(Object item)
	{
		this.head = new Node(item);
		this.size = 0;
	} 


	public void add(Object item)
	{
		addAfter(getNode(this.size), item);
		
	}

	// Add methods
	// Method to add item at the beginning of the list
	public void addFirst(Object item)
	{
		Node first = new Node(item);			// Step 1: Create the node
		first.next = head.next;				// Step 2: Copy the next of head, to the next of node
		head.next = first;					// Step 3: Update the head next value to point to the new node
		size++;								// Step 4: Update the number of nodes in the list
	}

	// Method to add item after a given node reference
	public void addAfter(Node target, Object item)
	{
		Node insert = new Node(item);
		insert.next = target.next;
		target.next = insert;
		size++;
	}

	// Method to add item at the end of the list
	public void addLast(Object item)
	{
		// We will use the getNode() method to retrieve the reference, and then call addAfter()
		Node target = getNode(size);			// Get hold of the last node
		addAfter(target, item);				// Then call the addAfter mothod to do the work
	}
	

	// Helper method, returns the reference to the node at given index
	public Node getNode(int index)
	{
		if(index < 0 || index > size)
		{
			return null;
		}
		// Otherwise, iterate over the list, and return the reference
		Node iter = head;		// A dummy iterator
		// Use a loop to iterate over the list and go to the index
		for(int i = 0; i < index; i++)
		{
			iter = iter.next;
		}
		return iter;
	}

	// Method to remove data
	public Object removeFirst()
	{
		// Check if there is data to be removed, if not return null
		if(size == 0)	// size 0 indicates no data in the linkedlist
		{			
			return null;
		}
		Node removed = head.next;
		head.next = head.next.next;
		size--;
		return removed.data;
	}


	// Helper method to display the contents of the linkedList
	public void printList()
	{
		Node temp = head;		// Iterator
		System.out.print("Data: ");
		while(temp.next != null)	// As long as there is data
		{		
			System.out.print(temp.next.data + "->");		// Print the data
			temp = temp.next;							// Point to the next node
		}
		System.out.println(); 						// Go to the next line
	}
	
	public int getSize()
	{
		return this.size;
	}

	public boolean isEmpty()
	{
		return (this.head == null);
	}
}