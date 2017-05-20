/*
    Danny Rivera
    CSC 311 - 01
    Dr Amlan Chatterjee
    Project 03
    April 6, 2017
    Implementation of a Linked List Queue
*/
public class LinkedListQueue
{
	private Node front;    // front of Node
	private Node rear;     // rear of Node
	private int size;      // size
    
	public LinkedListQueue()
	{
		this.size = 0;
		this.front = null;
		this.rear = null;
	}
    /* Function to insert elements as Queue storage */
	public void insert(Object data)
    {
        Node next = new Node(data, null);   
        if (rear == null)	// if is the first node 
        {
            this.front = next;
            this.rear = next;
        }
        else    // insert elements at the rear of Node. 
        {
            rear.setNode(next);
            rear = rear.getNode();
        }
        size++; // increment size of Node
    }    
     /*  Function to remove element as Queue storage */
    public Object remove()
    {
        if (isEmpty() ) // Check if Front Node is empty
        {
            return null;
        }
        Node temp = front;  // Store a temporary Node from the front of queue
        this.front = temp.getNode(); // Get the next node and stores it to front     
        if (front == null)
            rear = null;
        size-- ;        
        return temp.getData();
    }    
    /*  Function to check the front element of the queue */
    public Object peek()
    {
        if (isEmpty() )
        {
            return null;
        }
        return this.front.getData();
    }    
    // Check if front is empty
    public boolean isEmpty()
    {
    	return (this.front == null);
    }
    // Getter method to get Size of Link Queue
    public int getSize()
    {
    	return this.size;
    }
}