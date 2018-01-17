
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
 * FILE:		Driver.java
 * 
 * DESCRIPTION:	This file contains the main method for running the maze program.
 * 
 **************************************************/
public class Driver {
	/*************************************************
	 * 
	 * METHOD:			main
	 * 
	 * DESCRIPTION:		main driver method that contains the code for the program to run from the command line properly
	 * 
	 * @param args: contains the command line inputs.
	 *************************************************/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Block commented area for Command line interface, Not using for testing currently
		int nRows=0,nCols=0;
		if(args.length !=3){
			System.out.println("Enter filename, number of rows in integers, and number of columns in integers seperated by a space.");
			System.exit(0);
		}
		else{
			String filename = args[0];
			try{
				nRows = Integer.parseInt(args[1]);
				if(nRows <=0){
					System.out.println("Please enter a number greater than 0");
					System.exit(0);
				}
			}
			catch(NumberFormatException e){
				System.out.println("Enter number of rows as a positive integer number. i.e. 1, 2, 3");
			}

			try{
				nCols = Integer.parseInt(args[2]);
				if(nCols <=0){
					System.out.println("Please enter a number greater than 0");
					System.exit(0);
				}
			}
			catch(NumberFormatException e){
				System.out.println("Enter number of columns as a positive integer number. i.e. 1, 2, 3");
			}

			Maze maze = new Maze(nRows,nCols,filename);
			System.out.println("Running find exit stack & Queue using following args: " +filename+" " + nRows+" " + nCols+" ");
			maze.findExitUsingStack();
			maze.resetMaze();
			maze.findExitUsingQueue();

		}

	}

}
