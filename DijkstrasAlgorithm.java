import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DijkstrasAlgorithm implements DijkstraInterface
{
	// array of nodes
	Node [] nodes;

	public DijkstrasAlgorithm(String fileName, int numOfNodes) throws IOException 
	{
		// create the nodes array with a size of 
		// however many nodes there are in the graph
		nodes = new Node [numOfNodes];

		// fill the nodes array with new instances of the node class
		for (int i = 0; i < numOfNodes; i ++)
		{
			nodes[i] = new Node(numOfNodes);
		}
		
		// create a new buffer to read in the lines in the txt file
		BufferedReader buffer = new BufferedReader(new FileReader(fileName));

		int row = 0;
		while (buffer.ready())
		{
			// read in the line first
			String line = buffer.readLine();

			// then set up a tokenizer that will separate the string line
			// into the required parts to assign the arc
			StringTokenizer tokens = new StringTokenizer(line, "\t\n");

			if (tokens.hasMoreTokens())
			{	
				for (int column = 0; column < numOfNodes; column ++)
				{
					nodes[row].links[column] = Integer.valueOf(tokens.nextToken());
				}
				row++;
			}
		}
	}
	
	/**
	 * Calculates the shortest path around the graph
	 * using Dijkstra's algorithm
	 * 
	 * @param startNode
	 */
	public void shortestPath(int startNode)
	{
		// start node value is 0
		nodes[startNode].value = 0;
		
		// show the intial graph with the start node
		displayGraph(nodes);

		while (nodesLeft())
		{
			// set a node object to whichever node has the lowest distance
			Node n = minDistance();

			// iterate over the node n's links array and perform the relax
			// operation on each arc (any arc that isn't 0)
			for (int i = 0; i < n.links.length; i ++)
			{
				if (n.links[i] > 0)
				{
					// realx(n's value, arc weight, value of the node you connect to, index of the node you want to change the value of);
					relax((int) n.value, n.links[i], (int) nodes[i].value, i);
				}
			}

			// mark the node as processed so you do not revisit it
			n.processed = true;
			
			// show the updated graph
			displayGraph(nodes);
		}
	}

	/**
	 * a is the value of the node you are standing on
	 * b is the weight of the arc between you and another node
	 * c is the value of the node you want to link to
	 * index is the value 
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @param index
	 */
	private void relax(int a, int b, int c, int index)
	{
		// check if you found a lesser value
		if (a + b < c)
		{
			// change nodes value
			c = a + b;
			nodes[index].value = c;
		}
	}

	/**
	 * runs through the array of nodes and checks
	 * whether there is a node that still has 
	 * to be processed
	 * 
	 * @return answer
	 */
	private boolean nodesLeft()
	{
		boolean answer = false;

		for (int i = 0; i < nodes.length; i ++)
		{
			if (!nodes[i].processed)
			{
				answer = true;
				break;
			}
		}

		return answer;
	}

	/**
	 * runs through the node array and looks at
	 * the values and then determines which node
	 * holds the least distance
	 * 
	 * @return
	 */
	private Node minDistance()
	{
		// no value can be higher than infinity
		float lowest = Float.POSITIVE_INFINITY;
		
		// want o keep track of the index as you progress
		int index = 0;

		for (int i = 0; i < nodes.length; i ++)
		{
			// check if the value of the current node is less than our value and if the node has now been processed
			if (nodes[i].value < lowest && !nodes[i].processed)
			{
				// if the conditions are true, update the index and the lowest value
				index = i;
				lowest = nodes[i].value;
			}
		}
		
		// return the node with the lowest value
		return nodes[index];
	}

	/**
	 * Displays the nodes with their numbers and values
	 * 
	 * @param nodes
	 */
	private void displayGraph(Node [] nodes)
	{
		for (int i = 0; i < nodes.length; i ++)
		{
			System.out.println("Node " + i + " holds value " + nodes[i].value);
		}
		
		System.out.println();
	}
}