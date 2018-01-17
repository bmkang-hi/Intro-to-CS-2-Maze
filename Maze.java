import java.io.FileNotFoundException;
import java.io.FileReader;
//import java.util.Random;
import java.util.Scanner;


/*************************************************
 * 
 * NAME:		Brandon Kang
 * 
 * HOMEWORK:	6
 * 
 * CLASS:		ICS 211
 * 
 * INSTRUCTOR:	Scott Robertson
 * 
 * DATE:		Mar 9, 2016
 * 
 * FILE:		Maze.java
 * 
 * DESCRIPTION:	This file contains the constructor for generating the maze and the search methods using Queue and Stack structures.
 * 
 **************************************************/
public class Maze {
	private Queue<Cell> searchQ = new Queue<>();
	private Cell currentNode = searchQ.poll();

	private boolean hasStart=false, hasExit=false, isWalkable=false,startInMaze=false,exitInMaze=false;
	private Grid maze, initialMaze;
	private int numRows, numCols, startRow=0, startColumn=0, moveCount=0;
	/*************************************************
	 * 
	 * METHOD:			Maze
	 * 
	 * DESCRIPTION:		Constructor for building the randomly generated maze using text file as base for what maze elements are present (start, exit, pathway). File format is .txt containing any combination of WSE* with no spaces 
	 * 
	 * @param			none
	 * 
	 * @return			none
	 * 
	 *************************************************/
	//public Maze(int nRow, int nCol,String filename){
	/*	maze = new Grid(nRow,nCol);
		initialMaze = new Grid(nRow,nCol);
		Random rng = new Random();
		char[] mazeLegend = new char[4];
		numRows = nRow;
		numCols = nCol;
		try {
			Scanner readIn = new Scanner(new FileReader(filename));
			while(readIn.hasNext()){
				mazeLegend = readIn.next().toCharArray();
			}
			readIn.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not find file: " + filename);	
		}
		for(int i=0;i<mazeLegend.length;i++){
			if(mazeLegend[i] == 'S'){
				hasStart = true;
			}
			else if(mazeLegend[i] == 'E'){
				hasExit = true;
			}
			else if(mazeLegend[i] == '*'){
				isWalkable = true;
			}
		}
		for(int i = 0; i < nRow; i++) {
			for(int j = 0; j < nCol; j++) {
				int random = rng.nextInt(2);
				if(rng.nextInt(4) == 2 && !startInMaze && hasStart){
					maze.setData(i, j, "S");
					initialMaze.setData(i, j, "S");
					startInMaze=true;
					startRow=i;
					startColumn=j;

				}
				else if(rng.nextInt(4)==3 && !exitInMaze && hasExit){
					maze.setData(i, j, "E");
					initialMaze.setData(i, j, "E");
					exitInMaze=true;
				}
				else if(random == 0){
					maze.setData(i, j, "W");
					initialMaze.setData(i, j, "W");
				}
				else if(random == 1 && isWalkable){
					maze.setData(i, j, "*");
					initialMaze.setData(i, j, "*");
				}

			}
		}
		if(!startInMaze && hasStart){
			maze.setData(0, 0, "S");
		}
		if(!exitInMaze && hasExit){
			maze.setData(nRow-1, nCol-1, "E");
		}
	}*/

	/*************************************************
	 * 
	 * METHOD:			Maze
	 * 
	 * DESCRIPTION:		Constructor for generating navigable maze of given size <= the size stored in predefined text file.
	 * 
	 * @param			none
	 * 
	 * @return			none
	 * 
	 *************************************************/
	public Maze(int nRow,int nCol, String filename){
		maze = new Grid(nRow,nCol);
		initialMaze = new Grid(nRow,nCol);
		numRows = nRow;
		numCols = nCol;
		try {
			Scanner readIn = new Scanner(new FileReader(filename));
			while(readIn.hasNext()){

				for(int i = 0; i < nRow; i++) {
					for(int j = 0; j < nCol; j++) {
						if(readIn.hasNext()){
							String token = readIn.next();
							maze.setData(i, j, token);
							initialMaze.setData(i, j, token);
						}
						else{
							maze.setData(i, j, "W");
							initialMaze.setData(i, j, "W");
						}
					}
				}
			}
			readIn.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not find file: " + filename);	
		}
		//Search for start, end, walkable paths
		for(int i = 0; i < nRow; i++) {
			for(int j = 0; j < nCol; j++) {
				if(maze.getData(i, j).equals("S")){
					hasStart = true;
					startInMaze = true;
					startRow = i;
					startColumn = j;
				}
				else if(maze.getData(i, j).equals("E")){
					hasExit = true;
				}
				else if(maze.getData(i, j).equals("*")){
					isWalkable = true;
				}
			}
		}
	}





	/*************************************************
	 * 
	 * METHOD:			findExitUsingStack
	 * 
	 * DESCRIPTION:		Finds the exit, if reachable, of the generated maze using a depth first tree search on a Stack data structure.
	 * 
	 * @param none
	 * 
	 * @return none
	 *************************************************/
	public void findExitUsingStack(){
		System.out.println("Attempting to find exit using stack.");
		if(!isWalkable){
			System.out.println("The maze is unwalkable.");
			System.out.println(toString());
		}
		else if(!hasExit){
			System.out.println("The maze doesn't have an exit.");
			System.out.println(toString());
		}
		else if(!hasStart){
			System.out.println("The maze doesn't have a starting point.");
			System.out.println(toString());
		}

		else{
			//Depth first tree search, each cardinal direction is one branch of tree, ex if N,S walkable, tree splits to 2 sub nodes, etc
			Stack<Cell> searchStack = new Stack<>();
			if(!startInMaze){
				searchStack.push(maze.getCell(0, 0));

				while(searchStack.peek().getNorth() != null && searchStack.peek().getNorth().getData().equals("W") == false && (searchStack.peek().getNorth().getData().equals("*") || searchStack.peek().getNorth().getData().equals("E"))){
					System.out.println(toString());
					searchStack.push(searchStack.peek().getNorth());
					moveCount++;
					if(searchStack.peek().getData().equals("E")){
						System.out.println("Exit found!");
						System.out.println(toString());
						System.out.println("Move count: " + moveCount);
						break;
					}
					else if(searchStack.peek().getData().equals("S") == false){
						searchStack.peek().setData("@");
						System.out.println(toString());
					}
				}
				A : while(!searchStack.empty()){

					if(searchStack.peek().getNorth() != null && searchStack.peek().getNorth().getData().equals("@") == false && searchStack.peek().getNorth().getData()!= "W"){
						System.out.println(toString());
						while(searchStack.peek().getNorth() != null && (searchStack.peek().getNorth().getData().equals("*") || searchStack.peek().getNorth().getData().equals("E"))){
							System.out.println(toString());
							searchStack.push(searchStack.peek().getNorth());
							moveCount++;
							if(searchStack.peek().getData().equals("E")){
								System.out.println("Exit found!");
								System.out.println(toString());
								System.out.println("Move count: " + moveCount);
								break A;
							}
							else if(searchStack.peek().getData().equals("S") == false){
								searchStack.peek().setData("@");
								System.out.println(toString());
							}
						}
					}

					if(searchStack.peek().getEast() != null && searchStack.peek().getEast().getData().equals("@") == false && searchStack.peek().getEast().getData()!= "W"){
						System.out.println(toString());
						while(searchStack.peek().getEast() != null && (searchStack.peek().getEast().getData().equals("*") || searchStack.peek().getEast().getData().equals("E"))){
							System.out.println(toString());
							searchStack.push(searchStack.peek().getEast());
							moveCount++;
							if(searchStack.peek().getData().equals("E")){
								System.out.println("Exit found!");
								System.out.println(toString());
								System.out.println("Move count: " + moveCount);
								break A;
							}
							else if(searchStack.peek().getData().equals("S") == false){
								searchStack.peek().setData("@");
								System.out.println(toString());
							}
						}
					}

					if(searchStack.peek().getSouth() != null && searchStack.peek().getSouth().getData().equals("@") == false && searchStack.peek().getSouth().getData()!= "W"){
						System.out.println(toString());
						while(searchStack.peek().getSouth() != null && (searchStack.peek().getSouth().getData().equals("*") || searchStack.peek().getSouth().getData().equals("E"))){
							System.out.println(toString());
							searchStack.push(searchStack.peek().getSouth());
							moveCount++;
							if(searchStack.peek().getData().equals("E")){
								System.out.println("Exit found!");
								System.out.println(toString());
								System.out.println("Move count: " + moveCount);
								break A;
							}
							else if(searchStack.peek().getData().equals("S") == false){
								searchStack.peek().setData("@");
								System.out.println(toString());
							}
						}
					}


					if(searchStack.peek().getWest() != null && searchStack.peek().getWest().getData().equals("@") == false && searchStack.peek().getWest().getData()!= "W"){
						System.out.println(toString());
						while(searchStack.peek().getWest() != null && (searchStack.peek().getWest().getData().equals("*") || searchStack.peek().getWest().getData().equals("E"))){
							System.out.println(toString());
							searchStack.push(searchStack.peek().getWest());
							moveCount++;
							if(searchStack.peek().getData().equals("E")){
								System.out.println("Exit found!");
								System.out.println(toString());
								System.out.println("Move count: " + moveCount);
								break A;
							}
							else if(searchStack.peek().getData().equals("S") == false){
								searchStack.peek().setData("@");
								System.out.println(toString());
							}
						}
					}
					if((searchStack.peek().getNorth() == null || searchStack.peek().getNorth().getData().equals("W") || searchStack.peek().getNorth().getData().equals("@") || searchStack.peek().getNorth().getData().equals("S"))&&(searchStack.peek().getEast() == null || searchStack.peek().getEast().getData().equals("W") || searchStack.peek().getEast().getData().equals("@")|| searchStack.peek().getEast().getData().equals("S"))&&(searchStack.peek().getSouth() == null || searchStack.peek().getSouth().getData().equals("W") || searchStack.peek().getSouth().getData().equals("@")|| searchStack.peek().getSouth().getData().equals("S"))&&(searchStack.peek().getWest() == null || searchStack.peek().getWest().getData().equals("W") || searchStack.peek().getWest().getData().equals("@")|| searchStack.peek().getWest().getData().equals("S"))){
						searchStack.pop();
					}


				}
				System.out.println("Unreachable exit!");
				System.out.println(toString());
				System.out.println("Move count: " + moveCount);

			}
			//Randomly placed start
			else{
				searchStack.push(maze.getCell(startRow, startColumn));

				if(searchStack.peek().getNorth() != null  && searchStack.peek().getNorth().getData().equals("W")==false && searchStack.peek().getNorth().getData().equals("@")==false){
					while(searchStack.peek().getNorth() != null && (searchStack.peek().getNorth().getData().equals("*") || searchStack.peek().getNorth().getData().equals("E"))){
						searchStack.push(searchStack.peek().getNorth());
						moveCount++;
						if(searchStack.peek().getData().equals("E")){
							System.out.println("Exit found!");
							System.out.println(toString());
							System.out.println("Move count: " + moveCount);
							break;
						}
						else if(searchStack.peek().getData().equals("S")==false){
							searchStack.peek().setData("@");
							System.out.println(toString());
						}
					}
				}
				if(searchStack.peek().getEast() != null && searchStack.peek().getEast().getData().equals("W")==false&& searchStack.peek().getEast().getData().equals("@")==false){
					while(searchStack.peek().getEast() != null && (searchStack.peek().getEast().getData().equals("*") || searchStack.peek().getEast().getData().equals("E"))){
						searchStack.push(searchStack.peek().getEast());
						moveCount++;
						if(searchStack.peek().getData().equals("E")){
							System.out.println("Exit found!");
							System.out.println(toString());
							System.out.println("Move count: " + moveCount);
							break;
						}
						else if(searchStack.peek().getData().equals("S") == false){
							searchStack.peek().setData("@");
							System.out.println(toString());
						}
					}
				}
				if(searchStack.peek().getSouth() != null && searchStack.peek().getSouth().getData().equals("W")==false && searchStack.peek().getSouth().getData().equals("@")==false){
					while(searchStack.peek().getSouth() != null && (searchStack.peek().getSouth().getData().equals("*") || searchStack.peek().getSouth().getData().equals("E"))){
						searchStack.push(searchStack.peek().getSouth());
						moveCount++;
						if(searchStack.peek().getData().equals("E")){
							System.out.println("Exit found!");
							System.out.println(toString());
							System.out.println("Move count: " + moveCount);
							break;
						}
						else if(searchStack.peek().getData().equals("S") == false){
							searchStack.peek().setData("@");
							System.out.println(toString());
						}
					}
				}

				if(searchStack.peek().getWest() != null  && searchStack.peek().getWest().getData().equals("W")==false && searchStack.peek().getWest().getData().equals("@")==false){
					while(searchStack.peek().getWest() != null && (searchStack.peek().getWest().getData().equals("*") || searchStack.peek().getWest().getData().equals("E"))){
						searchStack.push(searchStack.peek().getWest());
						moveCount++;
						if(searchStack.peek().getData().equals("E")){
							System.out.println("Exit found!");
							System.out.println(toString());
							System.out.println("Move count: " + moveCount);
							break;
						}
						else if(searchStack.peek().getData().equals("S") == false){
							searchStack.peek().setData("@");
							System.out.println(toString());
						}
					}
				}

				A : while(!searchStack.empty()){
					if(searchStack.peek().getNorth() != null && searchStack.peek().getNorth().getData().equals("W") == false&& searchStack.peek().getNorth().getData().equals("@") == false&& searchStack.peek().getNorth().getData().equals("S") == false){
						while(searchStack.peek().getNorth() != null && (searchStack.peek().getNorth().getData().equals("*") || searchStack.peek().getNorth().getData().equals("E"))){
							searchStack.push(searchStack.peek().getNorth());
							moveCount++;
							if(searchStack.peek().getData().equals("E")){
								System.out.println("Exit found!");
								System.out.println(toString());
								System.out.println("Move count: " + moveCount);
								break A;
							}
							else if(searchStack.peek().getData().equals("S") == false){
								searchStack.peek().setData("@");
								System.out.println(toString());
							}
						}
					}

					if(searchStack.peek().getEast() != null && searchStack.peek().getEast().getData().equals("W") == false && searchStack.peek().getEast().getData().equals("@") == false && searchStack.peek().getEast().getData().equals("S") == false){
						while(searchStack.peek().getEast() != null && (searchStack.peek().getEast().getData().equals("*") || searchStack.peek().getEast().getData().equals("E"))){
							searchStack.push(searchStack.peek().getEast());
							moveCount++;
							if(searchStack.peek().getData().equals("E")){
								System.out.println("Exit found!");
								System.out.println(toString());
								System.out.println("Move count: " + moveCount);
								break A;
							}
							else if(searchStack.peek().getData().equals("S") == false){
								searchStack.peek().setData("@");
								System.out.println(toString());
							}
						}
					}

					if(searchStack.peek().getSouth() != null && searchStack.peek().getSouth().getData().equals("W") == false && searchStack.peek().getSouth().getData().equals("@") == false&& searchStack.peek().getSouth().getData().equals("S") == false){
						while(searchStack.peek().getSouth() != null && (searchStack.peek().getSouth().getData().equals("*") || searchStack.peek().getSouth().getData().equals("E"))){
							searchStack.push(searchStack.peek().getSouth());
							moveCount++;
							if(searchStack.peek().getData().equals("E")){
								System.out.println("Exit found!");
								System.out.println(toString());
								System.out.println("Move count: " + moveCount);
								break A;
							}
							else if(searchStack.peek().getData().equals("S") == false){
								searchStack.peek().setData("@");
								System.out.println(toString());
							}
						}
					}

					if(searchStack.peek().getWest() != null && searchStack.peek().getWest().getData().equals("W") == false&& searchStack.peek().getWest().getData().equals("@") == false&& searchStack.peek().getWest().getData().equals("S") == false){
						while(searchStack.peek().getWest() != null && (searchStack.peek().getWest().getData().equals("*") || searchStack.peek().getWest().getData().equals("E"))){
							searchStack.push(searchStack.peek().getWest());
							moveCount++;
							if(searchStack.peek().getData().equals("E")){
								System.out.println("Exit found!");
								System.out.println(toString());
								System.out.println("Move count: " + moveCount);
								break A;
							}
							else if(searchStack.peek().getData().equals("S") == false){
								searchStack.peek().setData("@");
								System.out.println(toString());
							}
						}
					}
					if((searchStack.peek().getNorth() == null || searchStack.peek().getNorth().getData().equals("W") || searchStack.peek().getNorth().getData().equals("@") || searchStack.peek().getNorth().getData().equals("S"))&&(searchStack.peek().getEast() == null || searchStack.peek().getEast().getData().equals("W") || searchStack.peek().getEast().getData().equals("@")|| searchStack.peek().getEast().getData().equals("S"))&&(searchStack.peek().getSouth() == null || searchStack.peek().getSouth().getData().equals("W") || searchStack.peek().getSouth().getData().equals("@")|| searchStack.peek().getSouth().getData().equals("S"))&&(searchStack.peek().getWest() == null || searchStack.peek().getWest().getData().equals("W") || searchStack.peek().getWest().getData().equals("@")|| searchStack.peek().getWest().getData().equals("S"))){
						searchStack.pop();
					}

				}
				if(searchStack.empty()){
					System.out.println("Unreachable exit!");
					System.out.println(toString());
					System.out.println("Move count: " + moveCount);
				}
			}

		}
	}




	/*************************************************
	 * 
	 * METHOD:			findExitUsingQueue
	 * 
	 * DESCRIPTION:		Finds the exit, if reachable, to the Maze it is called on using a breadth first tree search on a Queue data structure
	 * 
	 * @param none
	 * 
	 * @return none
	 * 
	 *************************************************/
	public void findExitUsingQueue(){
		System.out.println("Attempting to find exit using Queue.");
		boolean exitFound = true;
		//if startInMaze false , start placed at 0,0 (row,col)
		/*First enqueue the start/root node
		 * deque root to compare to search
		 * enqueue all child nodes of the dequed root
		 * dequeu next node
		 * enqueue all of that nodes children
		 * dequeue next node
		 * enqueue children
		 * repeat
		 * 
		 * determining if node has children?
		 * if N,E,S,W is not null, not W, not S, and not @
		 * then that respective directon is a child node of current node.
		 * checks done on the dequeue
		 * enqueue before checks.
		 * 
		 * */

		if(!hasStart){
			System.out.println("The maze has no start");
			System.out.println(toString());
		}
		else if(!hasExit){
			System.out.println("The maze has no exit");
			System.out.println(toString());
		}
		else if(!isWalkable){
			System.out.println("The maze is not walkable");
			System.out.println(toString());
		}
		else{

			if(!startInMaze){
				searchQ.offer(maze.getCell(0, 0)); // root node (start node)
				currentNode = searchQ.poll();
				enqChildren();
				while(!searchQ.empty()){
					currentNode = searchQ.poll();
					moveCount++;
					if(currentNode.getData().equals("E")){
						exitFound = true;
						System.out.println("Exit found!");
						System.out.println(toString());
						System.out.println("Move count: " + moveCount);
						break;
					}
					else{
						currentNode.setData("@");
						exitFound = false;
						System.out.println(toString());
					}
					enqChildren();
				}
				if(exitFound ==false){
					System.out.println("Unreachable exit!");
					System.out.println(toString());
				}
			}
			else{
				exitFound = false;
				searchQ.offer(maze.getCell(startRow, startColumn)); // root node (start node)
				currentNode = searchQ.poll();
				enqChildren();
				while(!searchQ.empty()){
					currentNode = searchQ.poll();
					moveCount++;
					if(currentNode.getData().equals("E")){
						exitFound = true;
						System.out.println("Exit found!");
						System.out.println(toString());
						System.out.println("Move count: " + moveCount);
						break;
					}
					else{
						currentNode.setData("@");
						exitFound = false;
						System.out.println(toString());
					}
					enqChildren();
				}
				if(exitFound ==false){
					System.out.println("Unreachable exit!");
					System.out.println(toString());
					System.out.println("Move count: " + moveCount);
				}
			}
		}

	}

	/*************************************************
	 * 
	 * METHOD:			enqChildren
	 * 
	 * DESCRIPTION:		Enqueues children nodes of current node for use in queue breadth-first search method
	 *
	 * @param none
	 * 
	 * @return none
	 *************************************************/
	private void enqChildren(){
		if(currentNode.getNorth() != null && currentNode.getNorth().getData().equals("W") == false && currentNode.getNorth().getData().equals("@") == false && currentNode.getNorth().getData().equals("S") == false){
			searchQ.offer(currentNode.getNorth());
			moveCount++;
		}
		if(currentNode.getEast() != null && currentNode.getEast().getData().equals("W") == false && currentNode.getEast().getData().equals("@") == false && currentNode.getEast().getData().equals("S") == false){
			searchQ.offer(currentNode.getEast());
			moveCount++;
		}
		if(currentNode.getSouth() != null && currentNode.getSouth().getData().equals("W") == false && currentNode.getSouth().getData().equals("@") == false && currentNode.getSouth().getData().equals("S") == false){
			searchQ.offer(currentNode.getSouth());
			moveCount++;
		}
		if(currentNode.getWest() != null && currentNode.getWest().getData().equals("W") == false && currentNode.getWest().getData().equals("@") == false && currentNode.getWest().getData().equals("S") == false){
			searchQ.offer(currentNode.getWest());
			moveCount++;
		}
	}


	/*************************************************
	 * 
	 * METHOD:			toString
	 * 
	 * DESCRIPTION:		returns a String representation of the Maze in it's current state of navigation as a 2 dimensional matrix
	 * 
	 * @param none
	 * 
	 * @return	stringMaze: the string representation of the maze
	 *************************************************/
	public String toString(){
		String stringMaze = "";
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numCols; j++) {
				stringMaze += maze.getData(i, j) + " ";
			}
			stringMaze += "\n";
		}
		return stringMaze;
	}






	/*************************************************
	 * 
	 * METHOD:			resetMaze
	 * 
	 * DESCRIPTION:		resets maze to the initial configuration when it was generated. Overwritten when the Maze constructor is called again.
	 * 
	 * @param none
	 * 
	 * @return none
	 * 
	 *************************************************/
	public void resetMaze(){
		for(int i=0;i<numRows;i++){
			for(int j=0;j<numCols;j++){
				maze.setData(i, j, initialMaze.getData(i, j));
			}
		}
	}
}

