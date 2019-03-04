import java.util.*;
import java.io.*;
public class Maze{


    private char[][]maze;
    private boolean animate;//false by default
    private int startX = -1;
    private int startY = -1;
    private int endX = -1;
    private int endY = -1;

    public Maze(String filename) throws FileNotFoundException{
        //COMPLETE CONSTRUCTOR
	//used to see the length of the maze
	int x =  0;
	//used to add the characters to the maze
	int temp = 0;
	//getting the width of the maze as well as adding the characters to the maze
 	String a = "";
	animate = false;
	//scanning file
	File text = new File(filename);
	Scanner mazee = new Scanner(text);
	Scanner mazee2 = new Scanner(text);
	//used to initialize the maze array
	 while (mazee.hasNextLine()) {
	     x++;
	     a = mazee.nextLine();
	 }
	 //if the width or length is 0 or you entered too much so the space at the ends are too long & there is no width for the ends
	 if (x == 0 || a.length() == 0) { throw new IllegalStateException("there might be extra enter spaces in your maze or no maze at all in your text file");}
	 //initializing the maze
	 maze = new char[x][a.length()];
	 //inputting the characters onto the maze
	 while (mazee2.hasNextLine()) {
	     a = mazee2.nextLine();
	     for (int i =  0; i < a.length(); i++) {
		 maze[temp][i]  = a.charAt(i);
	     }
	     temp++;
	 }
	 //finding the start and end
	 for(int i = 0; i < maze.length; i++) {
	     for (int j = 0; j < maze[0].length; j++) {
		 if (maze[i][j] == 'S') {
		     startX = i;
		     startY = j;
		 }
		 if (maze[i][j] == 'E') {
		     endX = i;
		     endY = j;
		 }
	     }
	 }
	 //if there is not start or end then throw an exception
	 if (startX == -1 || startY == -1 || endX == -1 || endY == -1) {
	     throw new IllegalStateException("You must have a start and end");
	 }
    }
    //printing out the maze
    public String toString() {
	String output = "";
	//go through the maze
	for(int i  = 0; i  < maze.length; i++) {
	    for (int j = 0; j < maze[0].length; j++) {
		output += maze[i][j];
	    }
	    //new line since it's a rectangle 
	    output += "\n";
	}
	return output;
    } 
    private void wait(int millis){
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
     }


    public void setAnimate(boolean b){

        animate = b;

    }


    public void clearTerminal(){

        //erase terminal, go to top left of screen.
        System.out.println("\033[2J\033[1;1H");

    }



    public int solve(){
	//erase the S
	maze[startX][startY] = ' ';
	//and start solving at the location of the s.
	solve(startX, startY, 0);
	int num = 0;
	for(int i = 0; i < maze.length; i++) {
	    for(int y = 0; y < maze[0].length; y++) {
		if (maze[i][y] == '@') {
		    num++;
		}
	    }
	}
	return num;
    }

    //helper
    private int solve(int row, int col, int number){ //you can add more parameters since this is private
        //automatic animation! You are welcome.
        if(animate){

            clearTerminal();
            System.out.println(this);

            wait(20);
        }

        //COMPLETE SOLVE
	//base case: if they reached the end
	if (row == endX && col == endY) {
	    return number;
	} else {
	    //if they haven't check if row or col is out of bounds and whether or not you can move there (if it isn't a wall and hasn't be traced already)
	    if (row < maze.length && col < maze[0].length && row >= 0 && col >= 0 && maze[row][col] == ' ') {
		//if you can go onto that square put a @
		maze[row][col] = '@';
		//solve for the rest up down left right
		if (solve(row+1, col, number+1) > 0||
		    solve(row, col+1, number+1) > 0||
		    solve(row-1, col, number+1) > 0||
		    solve(row, col-1, number+1) > 0) {
		    return number;
		}
		//if it failed to solve, put a dot to indicate you've been there
		maze[row][col] = '.';
	    }
	    return -1; //so it compiles
	}
    }
    public static void main(String[] args) {
    	try {
    	    Maze a = new Maze("Mazel.txt");
    	    Maze b = new Maze("data1.dat");
    	    Maze c = new Maze("data2.dat");
    	    Maze d = new Maze("data3.dat");
    	    System.out.println(a.solve());
    	    b.solve();
    	    c.solve();
    	    d.solve();
    	}
    	catch (Exception a) {
    	    a.printStackTrace();
    	}
    }

}


