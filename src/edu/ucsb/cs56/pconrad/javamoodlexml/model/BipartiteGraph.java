package edu.ucsb.cs56.pconrad.javamoodlexml.model;
import java.util.ArrayList;

public class BipartiteGraph {

    private ArrayList<BipartiteGraphEdge> edges;    
    private int numLeft;
    private int numRight;
    private String leftLabels="abcdefghi";
    private String rightLabels="123456789";

    /**
       Construct a new Bipartite graph, initially with no edges.   The edges are numbered
       from zero through numLeft-1 and 0 through numRight-1.

       @param numLeft number of vertices in left partition
       @param numRight number of vertices in right partition
     */

    public BipartiteGraph(int numLeft, int numRight) {
	if (numLeft > leftLabels.length() || numRight > rightLabels.length())
	    throw new IllegalArgumentException("graph too big!");	
	this.numLeft = numLeft;
	this.numRight = numRight;	
	edges = new ArrayList<BipartiteGraphEdge>();
    }
    
    /**
       Add an edge.    
       @throw IllegalArgumentException if left or right is larger then numLeft-1 or numRight-1
     */
    public void addEdge(int left, int right) {
	edges.add(new BipartiteGraphEdge(left,right));
    }
    
    /**
       Output as an SVG

    */
    public String toString() {
	
	int height=100;
	int width=100;

	String header = "<svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 " + width + " " + height + "'>";


	String triangleDefs = 
	    "    <marker id='triangle' " + 
	    "      viewBox='0 0 10 10' refX='0' refY='5' " + 
	    "      markerUnits='strokeWidth' " + 
	    "      markerWidth='4' markerHeight='3' " + 
	    "      orient='auto'> " + 
	    "      <path d='M 0 0 L 10 5 L 0 10 z' /> " + 
	    "    </marker> ";

	String arrowDefs = "<defs> " +
	    "    <marker id='arrowhead' orient='auto' markerWidth='2' markerHeight='4'" +
	    "            refX='0.1' refY='2'> " +
	    "      <path d='M0,0 V4 L2,2 Z' fill='black' /> " +
	    "    </marker> " +
	    "  </defs>  ";

	String result = header + triangleDefs;

	// Loop through, adding a circle for each of the points on the left
	
	int x = width / 4;

	int ySep = height/(this.numLeft+1); // separation between margin and points, and between points
	int y = ySep; // start by moving this far down
	for (int i=0; i < this.numLeft; i++) {
	    result += "<circle cx='" + x +  "' cy='" + y + "'" + 
		" r='2' stroke='black' stroke-width='1' fill='black'/> ";
	    result += "<text x = '" + (x-10) +  "' y = '" + y + "'" +
		" fill = 'navy' font-size = '10' font-family='sans-serif' >" + 
		this.leftLabels.charAt(i) +"</text> ";
	    y+=ySep;
	}

	// Loop through, adding a circle for each of the points on the right
	
	x = (width * 3)/4;
	ySep = height/(this.numRight+1); // separation between margin and points, and between points
	y = ySep; // start by moving this far down
	for (int i=0; i < this.numRight; i++) {
	    result += "<circle cx='" + x +  "' cy='" + y + "'" + 
		" r='2' stroke='black' stroke-width='1' fill='black'/> ";
	    result += "<text x = '" + (x+10) +  "' y = '" + y + "'" +
		" fill = 'navy' font-size = '10' font-family='sans-serif' >" + 
		this.rightLabels.charAt(i) +"</text> ";
	    y+=ySep;
	}

	// Now draw the lines for each of the edges

	for (BipartiteGraphEdge e: edges) {
	    int xFrom = width/4;
	    int yFrom = (e.from + 1) * ySep;
	    int xTo = ((width * 3) / 4) - 10; // leave room for the arrow (10 pts)
	    int yTo = (e.to + 1) * ySep;
	    result += "<path marker-end='url(#triangle)' stroke-width='1' fill='none' stroke='black' " +
		"d='M " + xFrom + " " + yFrom + " L " + xTo + " " + yTo + "' /> ";
	}
	
	result += "</svg> ";

	return result;

    } 
}