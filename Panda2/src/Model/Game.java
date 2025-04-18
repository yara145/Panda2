package Model;

import java.util.ArrayList;
import java.util.Random;

import Controller.SysData;
import Enum.Levels;
import Enum.SnakeColor;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class Game {
public static boolean now;
	private int GameId;
	private Levels GameLevel;
	private int RowsNum;
	private int ColsNum;
	private int Places[][];
	private int WinnerId;
	private Dice gameDice; //yara!
	private ArrayList<Player> Players;
	private ArrayList<Snake> Snakes ;
	private ArrayList<Ladder> Ladders ;
	private ArrayList<Square> Squares ;
	public static int PlayerTurn=0;
	public static Board GameBoard;  //yara!
	private String endTime; 
// constructor
	public Game(int gameid, Levels gameLevel, int rowsNum, int colsNum) {
		super();
		this.GameId = gameid;
		this.GameLevel = gameLevel;
		this.RowsNum = rowsNum;
		this.ColsNum = colsNum;
		this.Places=new int[RowsNum][ColsNum];
		//this.CurentTurn= new ArrayList<Integer>() ;
		this.Players= new ArrayList<Player>() ;
		this.Snakes= new ArrayList<Snake>() ;
		this.Ladders= new ArrayList<Ladder>() ;
		this.Squares= new ArrayList<Square>() ;
		this.gameDice=DiceFactory.createDice(gameLevel);
		this.GameBoard= new Board(colsNum);  
	}
// empty constructor
	public Game() {
		super();
	}

//getters and setters
	public int getGameId() {
		return this.GameId;
	}

	public void setGameId(int gameId) {
		GameId = gameId;
	}

	public Levels getGameLevel() {
		return this.GameLevel;
	}

	public void setGameLevel(Levels gameLevel) {
		GameLevel = gameLevel;
	}

	public int getRowsNum() {
		return this.RowsNum;
	}

	public void setRowsNum(int rowsNum) {
		this.RowsNum = rowsNum;
	}

	public int getColsNum() {
		return this.ColsNum;
	}

	public void setColsNum(int colsNum) {
		this.ColsNum = colsNum;
	}

	public int[][] getPlaces() {
		return this.Places;
	}

	public void setPlaces(int[][] places) {
		Places = places;
	}

	public int getWinnerId() {
		return this.WinnerId;
	}

	public void setWinnerId(int winnerId) {
		this.WinnerId = winnerId;
	}


	public ArrayList<Player> getPlayers() {
		return this.Players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.Players = players;
	}

	public ArrayList<Snake> getSnakes() {
		return this.Snakes;
	}

	public void setSnakes(ArrayList<Snake> snakes) {
		this.Snakes = snakes;
	}

	public ArrayList<Ladder> getLadders() {
		return this.Ladders;
	}

	public void setLadders(ArrayList<Ladder> ladders) {
		this.Ladders = ladders;
	}

	public ArrayList<Square> getSquares() {
		return this.Squares;
	}

	public void setSquares(ArrayList<Square> squares) {
		this.Squares = squares;
	}


	public static int getPlayerTurn() {
		return PlayerTurn;
	}

	public static void setPlayerTurn(int playerTurn) {
		PlayerTurn = playerTurn;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
//----------------------Yomna------------------------------------
// createGame function to create the Places[][] that will be have all the elements ids on it
	public void createGame() {
	
		for (int i = 0; i < this.RowsNum; i++) {
			for (int j = 0; j < this.ColsNum; j++) {
//			it fill the Places[][] 0 sp it represent a normal square that did not have anything on it
				this.Places[i][j] = 0; 	
			}
		}

	}
//--------------------------------Yomna-----------------------------
	
//	-----------------------------Yara-------------------------
public void youHaveToRoll(boolean b) {
	if(b==true) {
		now=true;
	}
	else {
		now=false;
	}
}
	public void PlacespecialSquares(Levels gameLevel) { //should be continueed have to check that snake and ladder not in the same place
		int randQol;
		int randRow;
		Random random = new Random();
		Boolean flag=true;
		Levels leverArr[]=new Levels[3];
		leverArr[0]=Levels.Easy;
		leverArr[1]=Levels.Medium;
		leverArr[2]=Levels.Hard;
		if(gameLevel==Levels.Easy) {
			for(int i=0;i<3;i++) {//adding a questionSquare to the game board
				flag=true;
				while(flag) {
					randQol = random.nextInt(ColsNum) ;
					randRow = random.nextInt(RowsNum) ;
					if(this.Places[randRow][randQol]==0) {
						while(randQol==0 && randRow==0 || randQol==6 && randRow==6) {
							randRow = random.nextInt(RowsNum);
						}
						QuestionSquare QS=new QuestionSquare(randRow,randQol,leverArr[i]);
						System.out.println(QS.getQuesSquareId());
						this.Places[randRow][randQol]=QS.getQuesSquareId();
						flag=false;
						Squares.add(QS);
					}

				}
			}

		}


		else if(gameLevel== Levels.Medium) {
			for(int i=0;i<3;i++) {//adding a questionSquare to the game board
				flag=true;
				while(flag) {

					randQol = random.nextInt(ColsNum) ;
					randRow = random.nextInt(RowsNum) ;
					if(this.Places[randRow][randQol]==0) {
						while(randQol==0 && randRow==0 || randQol==9 && randRow==9) {
							randRow = random.nextInt(RowsNum);
						}
						QuestionSquare QS=new QuestionSquare(randRow,randQol,leverArr[i]);
						this.Places[randRow][randQol]=QS.getQuesSquareId();
						flag=false;
						Squares.add(QS);
					}

				}
			}
			flag=true;
			while(flag) {
				randQol = random.nextInt(ColsNum) ;
				randRow = random.nextInt(RowsNum);
				if(this.Places[randRow][randQol]==0) {
					while(randQol==0 && randRow==0 || randQol==9 && randRow==9) {
						randRow = random.nextInt(RowsNum);
					}
					SurpriseSquare SS = new SurpriseSquare(randRow,randQol);
					this.Places[randRow][randQol]=SS.getSurpSquareId();
					Squares.add(SS);
					flag=false;
				}

			}


		}
		else {
			for(int i=0;i<3;i++) {//adding a questionSquare to the game board
				flag=true;
				while(flag) {

					randQol = random.nextInt(ColsNum) ;
					randRow = random.nextInt(RowsNum) ;
					if(this.Places[randRow][randQol]==0) {
						while(randQol==0 && randRow==0 || randQol==12 && randRow==12) {
							randRow = random.nextInt(RowsNum);
						}
						this.Places[randRow][randQol]=3;
						QuestionSquare QS=new QuestionSquare(randRow,randQol,leverArr[i]);
						this.Places[randRow][randQol]=QS.getQuesSquareId();
						flag=false;
						Squares.add(QS);
					}

				}
			}

			flag=true;
			for(int i=0;i<2;i++) {
				flag=true;
				while(flag) {
					randQol = random.nextInt(ColsNum) ;
					randRow = random.nextInt(RowsNum);
					if(this.Places[randRow][randQol]==0) {
						while(randQol==0 && randRow==0 || randQol==12 && randRow==12) {
							randRow = random.nextInt(RowsNum);
						}
						SurpriseSquare SS = new SurpriseSquare(randRow,randQol);
						this.Places[randRow][randQol]=SS.getSurpSquareId();
						Squares.add(SS);
						flag=false;
					}

				}
			}
		}
	}

	public void placeNormalSquares() {

		int i,j;
		for(i=0;i<this.RowsNum;i++) {
			for(j=0;j<this.RowsNum;j++) {
				if(this.Places[i][j]==0) {
					Squares.add(new Square(i,j)) ; 
				}
			}
		}

	}
//-------------------------------------------Yara--------------------------
	
	
//	-----------------------------Yomna----------------------------------
// PlaceSnakes function that locate	the Snakes on the Places matrix 
//	where the Head of the Snake located it put the id of the snake so we can now in this place there is a snake head
//	and in the places that located the tail of the snake it will put the number 50 (there is no element id can be reach this number)
//	the function check the place in the matrix if it 0 it will add the head or the tail 
//	if there is another number beside the 0 it will not allow him to put anything in this place
	public void PlaceSnakes() {
		if(this.GameLevel.equals(Levels.Easy)) {
			Snake redsnake=new Snake(SnakeColor.Red); 
			redsnake.SnackRandom(Levels.Easy, redsnake);
			while(this.Places[redsnake.getXHeadNum()][redsnake.getYHeadNum()]!=0 || this.Places[redsnake.getXTailNum()][redsnake.getYTailNum()]!=0) {
				redsnake.SnackRandom(Levels.Easy, redsnake);
			}
			this.Places[redsnake.getXHeadNum()][redsnake.getYHeadNum()]=redsnake.getSnakeId();
			Snakes.add(redsnake);

			Snake yellowsnake=new Snake(SnakeColor.Yellow); 
			yellowsnake.SnackRandom(Levels.Easy, yellowsnake);
			while(this.Places[yellowsnake.getXHeadNum()][yellowsnake.getYHeadNum()]!=0 || this.Places[yellowsnake.getXTailNum()][yellowsnake.getYTailNum()]!=0) {
				yellowsnake.SnackRandom(Levels.Easy, yellowsnake);
			}
			this.Places[yellowsnake.getXHeadNum()][yellowsnake.getYHeadNum()]=yellowsnake.getSnakeId();
			this.Places[yellowsnake.getXTailNum()][yellowsnake.getYTailNum()]=50;
			Snakes.add(yellowsnake);

			Snake greensnake=new Snake(SnakeColor.Green); 
			greensnake.SnackRandom(Levels.Easy, greensnake);
			while(this.Places[greensnake.getXHeadNum()][greensnake.getYHeadNum()]!=0 || this.Places[greensnake.getXTailNum()][greensnake.getYTailNum()]!=0) {
				greensnake.SnackRandom(Levels.Easy, greensnake);
			}
			this.Places[greensnake.getXHeadNum()][greensnake.getYHeadNum()]=greensnake.getSnakeId();
			this.Places[greensnake.getXTailNum()][greensnake.getYTailNum()]=50;
			Snakes.add(greensnake);

			Snake Bluesnake=new Snake(SnakeColor.Blue); 
			Bluesnake.SnackRandom(Levels.Easy, Bluesnake);
			while(this.Places[Bluesnake.getXHeadNum()][Bluesnake.getYHeadNum()]!=0 || this.Places[Bluesnake.getXTailNum()][Bluesnake.getYTailNum()]!=0) {
				Bluesnake.SnackRandom(Levels.Easy, Bluesnake);
			}
			this.Places[Bluesnake.getXHeadNum()][Bluesnake.getYHeadNum()]=Bluesnake.getSnakeId();
			this.Places[Bluesnake.getXTailNum()][Bluesnake.getYTailNum()]=50;
			Snakes.add(Bluesnake);

		}
		else if(this.GameLevel.equals(Levels.Medium)) {
			for(int i=0;i<2;i++) {
				Snake redsnake=new Snake(SnakeColor.Red); 
				redsnake.SnackRandom(Levels.Medium, redsnake);
				while(this.Places[redsnake.getXHeadNum()][redsnake.getYHeadNum()]!=0 || this.Places[redsnake.getXTailNum()][redsnake.getYTailNum()]!=0) {
					redsnake.SnackRandom(Levels.Easy, redsnake);
				}
				this.Places[redsnake.getXHeadNum()][redsnake.getYHeadNum()]=redsnake.getSnakeId();
				//				this.Places[redsnake.getXTailNum()][redsnake.getYTailNum()]=5;
				Snakes.add(redsnake);
			}

			Snake yellowsnake=new Snake(SnakeColor.Yellow); 
			yellowsnake.SnackRandom(Levels.Medium, yellowsnake);
			while(this.Places[yellowsnake.getXHeadNum()][yellowsnake.getYHeadNum()]!=0 || this.Places[yellowsnake.getXTailNum()][yellowsnake.getYTailNum()]!=0) {
				yellowsnake.SnackRandom(Levels.Easy, yellowsnake);
			}
			this.Places[yellowsnake.getXHeadNum()][yellowsnake.getYHeadNum()]=yellowsnake.getSnakeId();
			this.Places[yellowsnake.getXTailNum()][yellowsnake.getYTailNum()]=50;
			Snakes.add(yellowsnake);

			for(int i=0;i<2;i++) {
				Snake greensnake=new Snake(SnakeColor.Green); 
				greensnake.SnackRandom(Levels.Medium, greensnake);
				while(this.Places[greensnake.getXHeadNum()][greensnake.getYHeadNum()]!=0 || this.Places[greensnake.getXTailNum()][greensnake.getYTailNum()]!=0) {
					greensnake.SnackRandom(Levels.Easy, greensnake);
				}
				this.Places[greensnake.getXHeadNum()][greensnake.getYHeadNum()]=greensnake.getSnakeId();
				this.Places[greensnake.getXTailNum()][greensnake.getYTailNum()]=50;
				Snakes.add(greensnake);
			}


			Snake Bluesnake=new Snake(SnakeColor.Blue); 
			Bluesnake.SnackRandom(Levels.Medium, Bluesnake);
			while(this.Places[Bluesnake.getXHeadNum()][Bluesnake.getYHeadNum()]!=0 || this.Places[Bluesnake.getXTailNum()][Bluesnake.getYTailNum()]!=0) {
				Bluesnake.SnackRandom(Levels.Easy, Bluesnake);
			}
			this.Places[Bluesnake.getXHeadNum()][Bluesnake.getYHeadNum()]=Bluesnake.getSnakeId();
			this.Places[Bluesnake.getXTailNum()][Bluesnake.getYTailNum()]=50;
			Snakes.add(Bluesnake);
		}
		else if(this.GameLevel.equals(Levels.Hard)) {
			for(int i=0;i<2;i++) {
				Snake redsnake=new Snake(SnakeColor.Red); 
				redsnake.SnackRandom(Levels.Hard, redsnake);
				while(this.Places[redsnake.getXHeadNum()][redsnake.getYHeadNum()]!=0 || this.Places[redsnake.getXTailNum()][redsnake.getYTailNum()]!=0) {
					redsnake.SnackRandom(Levels.Easy, redsnake);
				}
				this.Places[redsnake.getXHeadNum()][redsnake.getYHeadNum()]=redsnake.getSnakeId();
				//				this.Places[redsnake.getXTailNum()][redsnake.getYTailNum()]=5;
				Snakes.add(redsnake);
			}
			for(int i=0;i<2;i++) {
				Snake yellowsnake=new Snake(SnakeColor.Yellow); 
				yellowsnake.SnackRandom(Levels.Hard, yellowsnake);
				while(this.Places[yellowsnake.getXHeadNum()][yellowsnake.getYHeadNum()]!=0 || this.Places[yellowsnake.getXTailNum()][yellowsnake.getYTailNum()]!=0) {
					yellowsnake.SnackRandom(Levels.Easy, yellowsnake);
				}	
				this.Places[yellowsnake.getXHeadNum()][yellowsnake.getYHeadNum()]=yellowsnake.getSnakeId();
				this.Places[yellowsnake.getXTailNum()][yellowsnake.getYTailNum()]=50;
				Snakes.add(yellowsnake);
			}

			for(int i=0;i<2;i++) {
				Snake greensnake=new Snake(SnakeColor.Green); 
				greensnake.SnackRandom(Levels.Hard, greensnake);
				while(this.Places[greensnake.getXHeadNum()][greensnake.getYHeadNum()]!=0 || this.Places[greensnake.getXTailNum()][greensnake.getYTailNum()]!=0) {
					greensnake.SnackRandom(Levels.Easy, greensnake);
				}
				this.Places[greensnake.getXHeadNum()][greensnake.getYHeadNum()]=greensnake.getSnakeId();
				this.Places[greensnake.getXTailNum()][greensnake.getYTailNum()]=50;
				Snakes.add(greensnake);
			}
			for(int i=0;i<2;i++) {
				Snake Bluesnake=new Snake(SnakeColor.Blue); 
				Bluesnake.SnackRandom(Levels.Hard, Bluesnake);
				while(this.Places[Bluesnake.getXHeadNum()][Bluesnake.getYHeadNum()]!=0 || this.Places[Bluesnake.getXTailNum()][Bluesnake.getYTailNum()]!=0) {
					Bluesnake.SnackRandom(Levels.Easy, Bluesnake);
				}
				this.Places[Bluesnake.getXHeadNum()][Bluesnake.getYHeadNum()]=Bluesnake.getSnakeId();
				this.Places[Bluesnake.getXTailNum()][Bluesnake.getYTailNum()]=50;
				Snakes.add(Bluesnake);
			}


		}

	}
//	------------------------------------Yomna--------------------------
	
//	----------------------------------Yara--------------------------------

	public void placeLadders() {
		Boolean flag=true;
		if (this.GameLevel==Levels.Easy) {
			for(int i=0;i<4;i++) {
				Ladder ladder=new Ladder(i+1);

				while(flag) {
					ladder.LadderRandom (Levels.Easy, ladder);
					if(this.Places[ladder.getXEnd()][ladder.getYEnd()]==0&&this.Places[ladder.getXStart()][ladder.getYStart()]==0) {
						this.Places[ladder.getXEnd()][ladder.getYEnd()]=60;
						this.Places[ladder.getXStart()][ladder.getYStart()]=ladder.getLadderId();
						Ladders.add(ladder);
						flag=false;
					}



				}
				flag=true;
			}


		}
		else if(this.GameLevel==Levels.Medium)  {
			for(int i=0;i<6;i++) {
				Ladder ladder=new Ladder(i+1);

				while(flag) {
					ladder.LadderRandom (Levels.Medium, ladder);
					if(this.Places[ladder.getXEnd()][ladder.getYEnd()]==0&&this.Places[ladder.getXStart()][ladder.getYStart()]==0) {
						this.Places[ladder.getXEnd()][ladder.getYEnd()]=60;
						this.Places[ladder.getXStart()][ladder.getYStart()]=ladder.getLadderId();
						flag=false;	
						Ladders.add(ladder);
					}


				}
				flag=true;
			}

		}
		else {
			for(int i=0;i<8;i++) {
				Ladder ladder=new Ladder(i+1);

				while(flag) {
					ladder.LadderRandom (Levels.Hard, ladder);
					if(this.Places[ladder.getXEnd()][ladder.getYEnd()]==0&&this.Places[ladder.getXStart()][ladder.getYStart()]==0) {
						this.Places[ladder.getXEnd()][ladder.getYEnd()]=60;
						this.Places[ladder.getXStart()][ladder.getYStart()]=ladder.getLadderId();
						Ladders.add(ladder);
						flag=false;
					}

				}
				flag=true;
			}
			for (int r =0; r<13; r++) {
				System.out.println("");

				for (int j =0; j<13; j++) {

					System.out.print("(" + r + "," + j + "):" + this.Places[r][j] +" ");
				}

			}
		}
	}

//---------------------------------Yara-------------------------------
	
//	-------------------------------Yomna--------------------------
// CurrentTurn function that return the player that in the queue an it his turn 
	public Player CurrentTurn() {

		if(PlayerTurn==this.getPlayers().size()) {
			PlayerTurn=0;
		}
		return this.getPlayers().get(PlayerTurn);

	}
// Roll function this function is the connection between rolling the dice in the backend and the frontend
//	it calles the RollDice function that return an object (integer/question)
//	 if the the object is an integer so it well update the player place ( his coordinate)
//	 and after the check it send to the frontend the object 
	public Object Roll() {

		Object roll=this.gameDice.RollDice();
		if(roll instanceof Integer) {
			int x=this.getPlayers().get(this.getPlayerTurn()).getPlayerRow();
			int y=this.getPlayers().get(this.getPlayerTurn()).getPlayerCol();
			int pos=this.GameBoard.getPosition(x, y);
			int newpos=pos+ (Integer)roll;
			int size=this.ColsNum*this.RowsNum;
			if(newpos>size) { //error!
				newpos=size;
			} 
			int afterRoll[]=new int[2];
			afterRoll=this.GameBoard.getCoordinates(newpos);
			System.out.println(afterRoll);
			this.getPlayers().get(this.getPlayerTurn()).UpdateRow(afterRoll[0]);
			this.getPlayers().get(this.getPlayerTurn()).UpdateCol(afterRoll[1]);

		}
				
		return roll;
	}

// checkQuestionSquare function that checks if the square in the Place matrix is a questionsquare
//	(it will be the id of the question square that it will be one of 3/4/5)
//	if the square is a questionsquare it will call the CallQuestion function and return the question that it CallQuestion returned 
	public Question checkQuestionSquare() {

		int num =this.Places[this.getPlayers().get(this.getPlayerTurn()).getPlayerRow()][this.getPlayers().get(this.getPlayerTurn()).getPlayerCol()];
		if(num==3|| num==4||num==5) {
			for(int i=0;i<this.Squares.size();i++) {
				if(this.Squares.get(i) instanceof QuestionSquare) {
					Question Q = new Question();
					Q.CallQuestion(((QuestionSquare)this.Squares.get(i)).getQuesLevel());
					return Q.CallQuestion(((QuestionSquare)this.Squares.get(i)).getQuesLevel());

				}
			}
		}
		return null;

	}
//--------------------------Yomna--------------------------------
	
	
//	------------------------Adan-------------------------------
	public Boolean checkQuestionSquare2() {

		int num =this.Places[this.getPlayers().get(this.getPlayerTurn()).getPlayerRow()][this.getPlayers().get(this.getPlayerTurn()).getPlayerCol()];
		if(num==3|| num==4||num==5) {
			for(int i=0;i<this.Squares.size();i++) {
				if(this.Squares.get(i) instanceof QuestionSquare) {
					return true;
				}
			}
		}
		return false;

	}
	
//	-----------------------Yomna------------------------------
//	updateByQuestion function that receive a question and answer
//	 it will send them and send the position that his turn to answerFeedback function,
//	answerFeedback will return the new position 
//	and the function will give it to the current player
	public boolean updateByQuestion(Question q,boolean Answer) {

		int x=this.getPlayers().get(this.getPlayerTurn()).getPlayerRow();
		int y=this.getPlayers().get(this.getPlayerTurn()).getPlayerCol();
		int pos=this.GameBoard.getPosition(x, y);
		Question Q = new Question();
		int newpos=Q.answerFeedback(q, Answer, pos);
		int size=this.ColsNum*this.RowsNum;
		if(newpos>size) { //error!
			newpos=size;
		}  else if (newpos<1) {
			newpos=1;

		}

		int afterQuestion[]=new int[2];
		afterQuestion=this.GameBoard.getCoordinates(newpos);
		this.getPlayers().get(this.getPlayerTurn()).UpdateRow(afterQuestion[0]);
		this.getPlayers().get(this.getPlayerTurn()).UpdateCol(afterQuestion[1]);
		System.out.println("updateeeeeeeeeeeeeeeeeeeeeeeeqqqqqq"+x+" "+y);
		return false;
		
		
// getPlayersFinalPLaces function that sort the player by their places 
	}public ArrayList<Player> getPlayersFinalPLaces(){
	    ArrayList<Player> winnersQueue = new ArrayList<>();
	    
	    for (Player player : this.Players) {
	        int position = this.GameBoard.getPosition(player.getPlayerRow(), player.getPlayerCol());
	        player.setPosition(position); // Set the position in the Player object
	        winnersQueue.add(player);
	    }
	    
	    // Sort the list based on player positions
	    Collections.sort(winnersQueue, new Comparator<Player>() {
	        @Override
	        public int compare(Player p1, Player p2) {
	            return Integer.compare(p1.getPosition(), p2.getPosition());
	        }
	    });

	    return winnersQueue;
	}

//	UpdatePlayerPlace function that updates the player coordinate by the item that stand on it 
//	if it is 0- normal square
//	1/2- surprisesquare
//	6-13- snake
//	14-21- ladder
	public int UpdatePlayerPlace() {

		int num =this.Places[this.getPlayers().get(this.getPlayerTurn()).getPlayerRow()][this.getPlayers().get(this.getPlayerTurn()).getPlayerCol()];
		int x=this.getPlayers().get(this.getPlayerTurn()).getPlayerRow();
		int y=this.getPlayers().get(this.getPlayerTurn()).getPlayerCol();

		if(num==1 || num ==2) {
			int pos=this.GameBoard.getPosition(x, y);
			for(int i=0;i<this.Squares.size();i++) {
				if(this.Squares.get(i) instanceof SurpriseSquare) {
					int newpos=((SurpriseSquare)this.Squares.get(i)).RandSurprise(pos);
					int size=this.ColsNum*this.RowsNum;
					if(newpos>size) { //error!
						newpos=size;
					}  else if (newpos<1) {
						newpos=1;

					}
					int afterSurprise[]=new int[2];
					afterSurprise=this.GameBoard.getCoordinates(newpos);
					this.getPlayers().get(this.getPlayerTurn()).UpdateRow(afterSurprise[0]);
					this.getPlayers().get(this.getPlayerTurn()).UpdateCol(afterSurprise[1]);  //error!

				}
			}
			return num;
		}
		if(num==3|| num==4||num==5) {
			return num;
		}

		if(num>=6 && num<=13) {
			for(int i=0;i<Snakes.size();i++) {
				if(Snakes.get(i).getSnakeId()==num) {
					this.getPlayers().get(this.getPlayerTurn()).UpdateRow(Snakes.get(i).getXTailNum());
					this.getPlayers().get(this.getPlayerTurn()).UpdateCol(Snakes.get(i).getYTailNum());

				}
			}
			return num;

		}
		if(num>=14 && num<=21) {
			for(int i=0;i<Ladders.size();i++) {
				if(Ladders.get(i).getLadderId()==num) {
					this.getPlayers().get(this.getPlayerTurn()).UpdateRow(Ladders.get(i).getXEnd());
					this.getPlayers().get(this.getPlayerTurn()).UpdateCol(Ladders.get(i).getYEnd());

				}
			}
			return num;

		}
		return 0;
	}
	
//	NextPlayer function that take the player in the next turn
	public int NextPlayer() {
		return ++this.PlayerTurn;
	}

//---------------------------------Yomna-----------------------------------

}
