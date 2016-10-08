public class Node 
{
	// boolean used to determine if this 
	// node has been checked
	boolean processed;
	
	// value of the node
	float value;
	
	// the values in this array are the arc 
	// weights and the indices are the nodes 
	// that they are associated with
	int [] links;
	
	/**
	 * constructor for node class
	 * 
	 * @param numOfNodes
	 */
	public Node(int numOfNodes) 
	{
		processed = false;
		
		// each node can have a possible link to every node
		links = new int [numOfNodes];
		
		// set all links to 0 and change the value only when you read in the text file
		for (int i = 0; i < links.length; i ++)
		{
			links[i] = 0;
		}
		
		// set the default value to positive infinity 
		// until you specify what your start node is
		this.value = Float.POSITIVE_INFINITY;
	}
}
