/*
	Danny Rivera
	CSC 311 - 01
	Dr Amlan Chatterjee
	Project 03
	April 6, 2017
	Implementation of a Node
*/
public class Node
{
	Object data;
	Node next;

	/* 1 Parameter Constructor */
	/* Holds data*/
	public Node(Object data)
	{
		this.data = data;
		this.next = null;
	}
	/* 2 Parameter Constructor */
	/* Holds data and next Node */
	public Node(Object data, Node current)
	{
		this.data = data;
		this.next = current;
	}
	// SETTER METHOD OF NODE 
	public void setNode(Node node)
	{
		this.next = node;
	}
	// SETTER METHOD OF DATA
	public void setData(Object data)
	{
		this.data = data;
	}
	// GETTER METHOD OF NODE
	public Node getNode()
	{
		return this.next;
	}
	// GETTER METHOD OF DATA
	public Object getData()
	{
		return this.data;
	}
}
