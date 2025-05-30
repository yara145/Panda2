package Model;

import java.util.Random;

import Enum.Levels;

//-----------------------------Yomna--------------------------------
public class Ladder extends MovePlayerTemplate {


	private int Length;
	private int XStart; // the x coordinate for the ladder start
	private int YStart;// the y coordinate for the ladder start
	private int XEnd;// the x coordinate for the ladder end
	private int YEnd;// the x coordinate for the ladder end
	Random rand = new Random();
	public static int counter=14; // the ladders id start from number 14 so we can recognize that the item in the board is a ladder
	private int LadderId;
// consturctor
	public Ladder(int length) {
		super();
		Length = length;
		this.LadderId=this.counter++;
	}

//	getters and setters
	public int getLadderId() {
		return LadderId;
	}


	public void setLadderId(int ladderId) {
		LadderId = ladderId;
	}


	public int getLength() {
		return Length;
	}

	public void setLength(int length) {
		Length = length;
	}

	public int getXStart() {
		return XStart;
	}

	public void setXStart(int xStart) {
		XStart = xStart;
	}

	public int getYStart() {
		return YStart;
	}

	public void setYStart(int yStart) {
		YStart = yStart;
	}

	public int getXEnd() {
		return XEnd;
	}

	public void setXEnd(int xEnd) {
		XEnd = xEnd;
	}

	public int getYEnd() {
		return YEnd;
	}

	public void setYEnd(int yEnd) {
		YEnd = yEnd;
	}

	public Random getRand() {
		return rand;
	}

	public void setRand(Random rand) {
		this.rand = rand;
	}
	
//	LadderRandom function decides the coordinate of the start and the end of the ladder by taking random number for them
//	the random has constrains for example: the ladder did not take a place outside the gameboard
//	and also make the random for the y coordinate so it always be the yend-ystart= the length of the ladder
//	it receive the level of the game so it can decide the number and the length of the ladders
//	and it receive the ladder itself
	public void LadderRandom (Levels level, Ladder ladder) {

		if (level.equals(Levels.Easy)) {
			if (ladder.Length==1) {

				int Endx = rand.nextInt(7);
				int Endy = 1+rand.nextInt(6);
				while(Endy==1 && Endx==0) {
					Endy = 1+rand.nextInt(6);
				}

				ladder.setXEnd(Endx);
				ladder.setYEnd(Endy);

				int y=Endy;

				ladder.setYStart(y-1);

				ladder.setXStart(Endx);
			}
			else if (ladder.Length==2) {
				
				int Endx = rand.nextInt(7);
				int Endy = 2+rand.nextInt(5);
				while(Endy==2 && Endx==0) {
					Endy = 2+rand.nextInt(5);
				}
				ladder.setXEnd(Endx);
				ladder.setYEnd(Endy);

				int y=Endy;

				ladder.setYStart(y-2);

				ladder.setXStart(Endx);
			}
			else if (ladder.Length==3) {
				int Endx = rand.nextInt(5);
				int Endy = 3+rand.nextInt(4);
				while(Endy==3 && Endx==0) {
					Endy = 3+rand.nextInt(4);
				}
				ladder.setXEnd(Endx);
				ladder.setYEnd(Endy);

				int y=Endy;

				ladder.setYStart(y-3);
				int Startx;
				int r = rand.nextInt(3);

					Startx=Endx+r;
				
				ladder.setXStart(Startx);

			}
			else if (ladder.Length==4) {
				
				
				int Endx = rand.nextInt(7);
				int Endy = 4+rand.nextInt(3);
				while(Endy==4 && Endx==0) {
					 Endy = 4+rand.nextInt(3);
				}

				ladder.setXEnd(Endx);
				ladder.setYEnd(Endy);

				int y=Endy;

				ladder.setYStart(y-4);
				
				ladder.setXStart(Endx);


			}
		}
		else if (level.equals(Levels.Medium)) {
			if (ladder.Length==1) {

				int Endx = rand.nextInt(10);
				int Endy = 1+rand.nextInt(9);
				while(Endy==1 && Endx==0) {
					Endy = 1+rand.nextInt(9);
				}

				ladder.setXEnd(Endx);
				ladder.setYEnd(Endy);

				int y=Endy;

				ladder.setYStart(y-1);

				ladder.setXStart(Endx);
			}
			else if (ladder.Length==2) {

				int Endx = rand.nextInt(10);
				int Endy = 2+rand.nextInt(8);
				while(Endy==2 && Endx==0) {
					Endy = 2+rand.nextInt(8);
				}
				ladder.setXEnd(Endx);
				ladder.setYEnd(Endy);

				int y=Endy;

				ladder.setYStart(y-2);

				ladder.setXStart(Endx);
			}
			else if (ladder.Length==3) {
				int Endx = rand.nextInt(8);
				int Endy = 3+rand.nextInt(7);
				while(Endy==3 && Endx==0) {
					Endy = 3+rand.nextInt(7);
				}
				ladder.setXEnd(Endx);
				ladder.setYEnd(Endy);

				int y=Endy;

				ladder.setYStart(y-3);
				int Startx;
				int r = rand.nextInt(3);

					Startx=Endx+r;

				ladder.setXStart(Startx);

			}
			else if (ladder.Length==4) {

				int Endx = rand.nextInt(10);
				int Endy = 4+rand.nextInt(6);
				while(Endy==4 && Endx==0) {
					 Endy = 4+rand.nextInt(6);
				}

				ladder.setXEnd(Endx);
				ladder.setYEnd(Endy);

				int y=Endy;

				ladder.setYStart(y-4);
				
				ladder.setXStart(Endx);
				


			}
			else if(ladder.Length==5) {
				
				int Endx = rand.nextInt(8);
				int Endy = 5+rand.nextInt(5);
				while(Endy==5 && Endx==0) {
					Endy = 5+rand.nextInt(5);
				}
				ladder.setXEnd(Endx);
				ladder.setYEnd(Endy);

				int y=Endy;

				ladder.setYStart(y-5);
				int Startx;
				int r = rand.nextInt(3);
					Startx=Endx+r;

				ladder.setXStart(Startx);
				

			}
			else if(ladder.Length==6) {
				int Endx = rand.nextInt(7);
				int Endy = 6+rand.nextInt(4);
				while(Endy==6 && Endx==0) {
					Endy = 6+rand.nextInt(4);
				}
				ladder.setXEnd(Endx);
				ladder.setYEnd(Endy);

				int y=Endy;

				ladder.setYStart(y-6);
				int Startx;
				int r = 1+rand.nextInt(4);
					Startx=Endx+r;
				
				ladder.setXStart(Startx);
			}

		}
		else if (level.equals(Levels.Hard)) {

			if (ladder.Length==1) {

				int Endx = rand.nextInt(13);
				int Endy = 1+rand.nextInt(12);
				while(Endy==1 && Endx==0) {
					Endy = 1+rand.nextInt(12);
				}

				ladder.setXEnd(Endx);
				ladder.setYEnd(Endy);

				int y=Endy;

				ladder.setYStart(y-1);

				ladder.setXStart(Endx);
			}
			else if (ladder.Length==2) {

				int Endx = rand.nextInt(13);
				int Endy = 2+rand.nextInt(11);
				while(Endy==2 && Endx==0) {
					Endy = 2+rand.nextInt(11);
				}
				ladder.setXEnd(Endx);
				ladder.setYEnd(Endy);

				int y=Endy;

				ladder.setYStart(y-2);

				ladder.setXStart(Endx);
			}
			else if (ladder.Length==3) {
				int Endx = rand.nextInt(11);
				int Endy = 3+rand.nextInt(10);
				while(Endy==3 && Endx==0) {
					Endy = 3+rand.nextInt(10);
				}
				ladder.setXEnd(Endx);
				ladder.setYEnd(Endy);

				int y=Endy;

				ladder.setYStart(y-3);
				int Startx;
				int r = rand.nextInt(3);
					Startx=Endx+r;
				
				ladder.setXStart(Startx);

			}
			else if (ladder.Length==4) {

				int Endx = rand.nextInt(13);
				int Endy = 4+rand.nextInt(9);
				while(Endy==4 && Endx==0) {
					 Endy = 4+rand.nextInt(9);
				}

				ladder.setXEnd(Endx);
				ladder.setYEnd(Endy);

				int y=Endy;

				ladder.setYStart(y-4);
				
				ladder.setXStart(Endx);


			}
			else if(ladder.Length==5) {
				int Endx = rand.nextInt(11);
				int Endy = 5+rand.nextInt(8);
				while(Endy==5 && Endx==0) {
					Endy = 5+rand.nextInt(8);
				}
				ladder.setXEnd(Endx);
				ladder.setYEnd(Endy);

				int y=Endy;

				ladder.setYStart(y-5);
				int Startx;
				int r = rand.nextInt(3);
					Startx=Endx+r;
				
				ladder.setXStart(Startx);
			}
			else if(ladder.Length==6) {
				int Endx = rand.nextInt(10);
				int Endy = 6+rand.nextInt(7);
				while(Endy==12 && Endx==0) {
					Endy = 6+rand.nextInt(7);
				}
				ladder.setXEnd(Endx);
				ladder.setYEnd(Endy);

				int y=Endy;

				ladder.setYStart(y-6);
				int Startx;
				int r = 1+rand.nextInt(4);
					Startx=Endx+r;
				
				ladder.setXStart(Startx);
			}
			else if(ladder.Length==7) {
				
				
				int Endx = rand.nextInt(13);
				int Endy = 7+rand.nextInt(6);
				while(Endy==7 && Endx==0) {
					 Endy = 7+rand.nextInt(6);
				}

				ladder.setXEnd(Endx);
				ladder.setYEnd(Endy);

				int y=Endy;

				ladder.setYStart(y-7);

				ladder.setXStart(Endx);
				

			}
			else if(ladder.Length==8) {
				int Endx = rand.nextInt(10);
				int Endy = 8+rand.nextInt(5);
				while(Endy==8 && Endx==0) {
					Endy = 8+rand.nextInt(5);
				}
				ladder.setXEnd(Endx);
				ladder.setYEnd(Endy);

				int y=Endy;

				ladder.setYStart(y-8);
				int Startx;
					Startx=Endx+3;
				
				ladder.setXStart(Startx);
			}
		}
	}
	
	
//	MovePlayer function in this function it takes the coordinate of the player and checks if he stand on ladder start,
//	then he move him up to the ladder end (change his coordinate to be the same as the ladder end)
	@Override 
	public int[] MovePlayer (int playerX, int playerY){
		
		int XYEnd[]=new int[2];
		
		if(this.getXStart()==playerX && this.getYStart()==playerY) {
			

			XYEnd[0]=this.XEnd;
			XYEnd[1]=this.YEnd;
		}
		return XYEnd;
		
	}
}
