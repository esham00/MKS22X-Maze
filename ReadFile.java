import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class ReadFile {
    public static void main(String args[]) throws FileNotFoundException {
	File text = new File("Mazel.txt");
	Scanner inf = new Scanner(text);
	while(inf.hasNextLine()) {
	    String line = inf.nextLine();
	    System.out.println(line);
	}
    }
}
