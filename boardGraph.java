/*
	Initialize graph with: boardGraph [NAME OF CHOICE] = new boardGraph([INSERT BOGGLEPLAYER BOARD]);

	Methods: 
			 Graph methods:
			 getNeighbors(x, y) - Based on a position, will return a character list all adjacent neighbors
			 printNeighbors(x, y) - Same as above, but instead will print all neighbors in their char form
			 

			 Vertex methods:
			 .getX() - Gets x position of a given vertex
			 .getY()- Gets y position of a given vertex
			 .getChar() - Method to get the character at a specific vertex
			 .isAdjacent() - Returns vertex, and if null, not adjacent
*/

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;


// Vertex/Node representation for each position on board
class Vertex {
	int x;
	int y;
	// Coordinate pair as a single integer
	int cantorNum;
	char character;
	// Neighbors map
	HashMap<Integer, Vertex> neighbors = new HashMap<>();

	public Vertex (int x, int y, char character, int cantorNum) {
		this.x = x;
		this.y = y;
		this.character = character;
		this.cantorNum = cantorNum;
	}

	// Getters for x, y, and CantorNum
	public int getX () {
		return x;
	}

	public int getY () {
		return y;
	}

	public int getCantorNum () {
		return cantorNum;
	}

	public char getChar () {
		return character;
	}

	// Neighbors methods
	public void setNeighbor (int i, Vertex v) {
		neighbors.put(i, v);
		return;
	}

	public Vertex getNeighbor (int i) {
		if (neighbors.get(i) == null) {
			return null;
		}
		return neighbors.get(i);
	}

	public HashMap<Integer, Vertex> getNeighborList () {
		return neighbors;
	}

	// Get adjacent vertices in a graph as array list
	public ArrayList getNeighbors () {
		ArrayList<Vertex> adjacents = new ArrayList<>();
		for (Map.Entry<Integer, Vertex> entry : neighbors.entrySet()) {
			Vertex adj = entry.getValue();
			adjacents.add(adj);
		}
		return adjacents;
	}

	// Method to print neighbors for a given vertec
	public void printNeighbors () {
		System.out.print(getChar() + " -----> ");
		for (Map.Entry<Integer, Vertex> entry : neighbors.entrySet()) {
			Vertex temp = entry.getValue();
			System.out.print(temp + ", ");
		}
		System.out.println();
	}

	@java.lang.Override
	public String toString() {
		return "" + getChar() + " ";
	}

}

// Adjacency Map Implementation of Graph
public class boardGraph {
	// Map to store all vertices
	private HashMap<Integer, Vertex> vertices = new HashMap<>();
	// Keep track of map size
	private int size = 0;
	// Current position vertex
	private Vertex currPos = new Vertex (-1,-1, '*', -1);
	// Use trie class
	Trie trie;

	// Sets board and creates adjacency map out of it
	public boardGraph (char[][] boardMatrix, Trie trie) {
		this.trie = trie;
		for (int i = 0; i < boardMatrix.length; i++) {
			for (int j = 0; j < boardMatrix[0].length; j++) {
				char curr = boardMatrix[i][j];
				Vertex v = addVertex(i, j, curr);
				if (i == 0 && j == 0) {
					currPos = v;
				}
			}
		}

	}

	// Print the current position
	public void printCurrPos () {
		currPos.printNeighbors();
	}

	// Method to print neighbors for a given vertec
	public void printNeighbors (int x, int y) {
		Vertex v = vertices.get(cantorPair(x,y));
		v.printNeighbors();
	}
	
	// Get size of graph
	public int size () {
		return size;
	}

	// Tell whether or not graph is empty
	public boolean isEmpty () {
		if (size == 0) {
			return true;
		}
		return false;
	}

	// Method to print graph
	public void printGraph() {
		int i = 0;
		for (Map.Entry<Integer, Vertex> entry : vertices.entrySet()) {
			if (i % 4 == 0) {
				System.out.println();
			}
			Vertex temp = entry.getValue();
			System.out.print(temp);
			i++;
		}
		System.out.println();
	}

	// Add vertex to map and connect with adjacent neighbors, if present
	private Vertex addVertex (int x, int y, char character) {
		// Use cantor pairing for hashmap key
		int cantorNum = cantorPair(x,y);
		Vertex newVertex = new Vertex (x, y, character, cantorNum);
		vertices.put(cantorNum, newVertex);
		System.out.print(newVertex + " -------> ");

		// If top left exists, add as neighbor
		int adjCantor = cantorPair(x-1,y-1);
		Vertex neighbor = isExisting(adjCantor);
		if (neighbor != null) {
			newVertex.setNeighbor(adjCantor, neighbor);
			neighbor.setNeighbor(cantorNum, newVertex);
			System.out.print(neighbor);
		}
		// If top (above) exists, add as neighbor
		adjCantor = cantorPair(x-1,y);
		neighbor = isExisting(adjCantor);
		if (neighbor != null) {
			newVertex.setNeighbor(adjCantor, neighbor);
			neighbor.setNeighbor(cantorNum, newVertex);
			System.out.print(neighbor);

		}
		// If top right exists, add as neighbor
		adjCantor = cantorPair(x-1,y+1);
		neighbor = isExisting(adjCantor);
		if (neighbor != null) {
			newVertex.setNeighbor(adjCantor, neighbor);
			neighbor.setNeighbor(cantorNum, newVertex);
			System.out.print(neighbor);
		}
		// If left exists, add as neighbor
		adjCantor = cantorPair(x,y-1);
		neighbor = isExisting(adjCantor);
		if (neighbor != null) {
			newVertex.setNeighbor(adjCantor, neighbor);
			neighbor.setNeighbor(cantorNum, newVertex);
			System.out.print(neighbor);
		}
		// If right exists, add as neighbor
		adjCantor = cantorPair(x,y+1);
		neighbor = isExisting(adjCantor);
		if (neighbor != null) {
			newVertex.setNeighbor(adjCantor, neighbor);
			neighbor.setNeighbor(cantorNum, newVertex);
			System.out.print(neighbor);
		}
		// If bottom left exists, add as neighbor
		adjCantor = cantorPair(x+1,y-1);
		neighbor = isExisting(adjCantor);
		if (neighbor != null) {
			newVertex.setNeighbor(adjCantor, neighbor);
			neighbor.setNeighbor(cantorNum, newVertex);
			System.out.print(neighbor);
		}
		// If bottom (below) exists, add as neighbor
		adjCantor = cantorPair(x+1,y);
		neighbor = isExisting(adjCantor);
		if (neighbor != null) {
			newVertex.setNeighbor(adjCantor, neighbor);
			neighbor.setNeighbor(cantorNum, newVertex);
			System.out.print(neighbor);
		}
		// If bottom right exists, add as neighbor
		adjCantor = cantorPair(x+1,y+1);
		neighbor = isExisting(adjCantor);
		if (neighbor != null) {
			newVertex.setNeighbor(adjCantor, neighbor);
			neighbor.setNeighbor(cantorNum, newVertex);
			System.out.print(neighbor);
		}
		System.out.println("\n");
		size++;
		return newVertex;
	}

	// Cantor pairing function to convert coordinate pair to single int
	private int cantorPair (int x, int y) {
		if (x < 0 || y < 0) {
			return -1;
		}
		return (int)(0.5 * (x + y) * (x + y + 1) + y);
	}

	// Tell whether a vertex exists in the graph. If so, return it
	private Vertex isExisting (int pair) {
		if (pair > -1 || vertices.get(pair) != null) {
			return vertices.get(pair);
		}
		return null;
	}
}