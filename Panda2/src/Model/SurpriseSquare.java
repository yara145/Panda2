package Model;
import java.util.Random;
//----- A child class of Square class By Yara------------
public class SurpriseSquare extends Square {

	public static int counter=1;
	private int SurpSquareId;

	public SurpriseSquare( int squareRow, int squareCol) {
		super(squareRow, squareCol);
		// TODO Auto-generated constructor stub
		this.SurpSquareId=this.counter++;
	}

// getters and setters
	public int getSurpSquareId() {
		return SurpSquareId;
	}

	public void setSurpSquareId(int surpSquareId) {
		SurpSquareId = surpSquareId;
	}


	// a function that receive player position and update it after a random surprise-Yara

	public int RandSurprise(int position) { 

		double randomNum = Math.random(); // Generates a random double between 0.0 (inclusive) and 1.0 (exclusive)

		Random random = new Random();

		// Generate a random integer between 0 and 1
		int randomNumber = random.nextInt(2); // Generates a random integer between 0 (inclusive) and 2 (exclusive)

		// Map 0 to option 1 and 1 to option 2
		int option = randomNumber == 0 ? 1 : 0;

		if (option == 0) {
			return position-10;
		}
		else {
			return position+10;
		}

	}

}
