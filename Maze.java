import java.util.*;
import java.io.*;
public class Maze{


    private char[][]maze;
    private boolean animate;//false by default
    private int startX, startY, endX, endY;

    public Maze(String filename) throws FileNotFoundException{
        //COMPLETE CONSTRUCTOR
	int x =  0;
	int temp = 0;
 	String a = "";
	animate = false;
	File text = new File(filename);
	Scanner mazee = new Scanner(text);
	Scanner mazee2 = new Scanner(text);
	 while (mazee.hasNextLine()) {
	     x++;
	     a = mazee.nextLine();
	 }
	 if (x == 0 || a.length() == 0) { throw new IllegalStateException("there might be extra enter spaces in your maze or no maze at all in your text file");}
	 maze = new char[x][a.length()];
	 while (mazee2.hasNextLine()) {
	     a = mazee2.nextLine();
	     for (int i =  0; i < a.length(); i++) {
		 maze[temp][i]  = a.charAt(i);
	     }
	     temp++;
	 }
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
	 if (startX == null || startY == null || endX == null || endY == null) {
	     throw new IllegalStateException("You must have a start and end");
	 }
    }
    
    public String toString() {
	String output = "";
	for(int i  = 0; i  < maze.length; i++) {
	    for (int j = 0; j < maze[0].length; j++) {
		output += maze[i][j];
	    }
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
	return solve(startX, startY, 0);
    }

    private int solve(int row, int col, int number){ //you can add more parameters since this is private
        //automatic animation! You are welcome.
        if(animate){

            clearTerminal();
            System.out.println(this);

            wait(20);
        }

        //COMPLETE SOLVE
	if (row == endX && col == endY) {
	    return number;
	} else {
	    if (row < maze.length && col < maze[0].length && row >= 0 && col >= 0 && maze[row][col] == ' ') {
		maze[row][col] = '@';
		if (solve(row+1, col, number+1) > 0||
		    solve(row, col+1, number+1) > 0||
		    solve(row-1, col, number+1) > 0||
		    solve(row, col-1, number+1) > 0) {
		    return number;
		}
		maze[row][col] = '.';
	    }
	    return -1; //so it compiles
	}
    }
    // public static void main(String[] args) {
    // 	try {
    // 	    Maze a = new Maze("Mazel.txt");
    // 	    Maze b = new Maze("data1.dat");
    // 	    Maze c = new Maze("data2.dat");
    // 	    Maze d = new Maze("data3.dat");
    // 	    a.solve();
    // 	    b.solve();
    // 	    c.solve();
    // 	    d.solve();
    // 	    System.out.println(a);
    // 	    System.out.println(b + "/n" + c + "/n" + d);
    // 	}
    // 	catch (Exception a) {
    // 	    a.printStackTrace();
    // 	}
    // }

}


