import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class DijkstraMain 
{
	public static void main(String [] args) throws IOException
	{	
		// text file containing the nodes and links
		String name = "Dijkstra.txt";
		
		// pass in text file name and number of nodes in graph
		DijkstrasAlgorithm da = new DijkstrasAlgorithm(name, numOfNodes());
		
		// declare the start node
		da.shortestPath(2);
	}
	
	/**
	 * counts the number of nodes to start the graph with
	 * 
	 * @return
	 * @throws IOException
	 */
	private static int numOfNodes() throws IOException
	{
		int rows = 0;
		BufferedReader buffer = new BufferedReader(new FileReader("Dijkstra.txt"));
		
		while (buffer.ready())
		{
			buffer.readLine();
			rows ++;
		}
		
		buffer.close();
		
		return rows;
	}
}
