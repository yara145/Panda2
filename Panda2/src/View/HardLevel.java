package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import Enum.*;
import FlatLafDesign.AudioButton;
import FlatLafDesign.BackButton;
import FlatLafDesign.RoundedBorder;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.text.StyleConstants;

import com.oracle.webservices.internal.api.EnvelopeStyle.Style;

import Controller.Screenshot;
import Controller.SysData;
import Model.Game;
import Model.PlayAudio;
import Model.Player;
import Model.Question;
import Enum.*;


public class HardLevel extends JFrame implements ActionListener {
	static int N=50;
	static int X=13;
	static int Y=13;
	private JPanel contentPane;
	private JLabel mytext;
	private JLabel lblSnake; 
	private JLabel glblSnake;// Label for the snake image
	private JLabel rlblSnake;
	private JLabel ylblSnake;
	private JLabel lblsur;
	private JLabel lblq;
	private JButton diceButton;
	private ImageIcon[] diceIcons;
	private JLabel diceLabel ;
	private JLabel lblLadder; 
	private JLabel p1Label;
	private JLabel p2Label;
	private JLabel p3Label;
	private JLabel p4Label;
	private JLabel p1OnGame=null;
	private JLabel p2OnGame=null;
	private JLabel p3OnGame=null;
	private JLabel p4OnGame=null;
	private Image img1;
	private ImageIcon imgIcn1;
	private ImageIcon finalIcon1;
	private Image img2;
	private ImageIcon imgIcn2;
	private ImageIcon finalIcon2;
	private Image img3;
	private ImageIcon imgIcn3;
	private ImageIcon finalIcon3;
	private Image img4;
	private ImageIcon imgIcn4;
	private ImageIcon finalIcon4;
	private JLabel p1name;
	private JLabel p2name;
	private JLabel p3name;
	private JLabel p4name;
	private JLabel timerLabel;
	private Timer timer;
	private int secondsElapsed;
	private long endTime; // Variable to store the end time
	private boolean isPaused = false;
	private JButton pauseButton;

	/**
	 * Create the frame.
	 */
	public HardLevel(Player p1,Player p2, Player p3,Player p4,int num) {
		//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//		setBounds(100, 0, 1800, 1100);
		//		contentPane = new JPanel();
		//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//		setContentPane(contentPane);
		//		

		PlayAudio.playGameSound(); // calling the function to play game background sound-Yara
		//---------------------yomna----------------------------------		
		String filePath = "AllGames.csv";
		System.out.println("test number11111111111111111111111");
		BufferedReader reader = null;
		int swap=0;

		try {
			String line = "";
			reader = new BufferedReader(new FileReader(filePath));
			reader.readLine();

			while((line = reader.readLine()) != null) {
				String[] fields = line.split(",");
				System.out.println("test number222222222222222222222222222");
				if(!fields[0].isEmpty()) {
					if(fields.length > 0) {
						System.out.println("test number333333333333333333333333333");
						Game game = new Game();
						Player player = new Player();
						if(swap<Integer.parseInt(fields[0])) {
							swap=Integer.parseInt(fields[0]);
						}
						System.out.println("swap in sysy data "+swap);

					}
				}else {
					System.out.println("the file is empty so the Game ID will be 1");
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}	
		System.out.println((swap+1)+" the new id for the new game");
		//-------------------------------yomna-------------------------------------------	
		Game g=new Game((swap+1),Levels.Hard, 13, 13);
		g.createGame();
		g.getPlayers().add(p1);
		g.getPlayers().add(p2);
		System.out.println("************************************"+p1.getNickName());
		/**************************************************************************************************/


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1900, 1100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		mytext = new JLabel("");
		mytext.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		mytext.setBounds(200, 0, 1400, 50);
		contentPane.add( mytext);
		Player proll=g.CurrentTurn();
		setPlayerText(proll, "you have to roll the dice");
		ImageIcon winIcon = new ImageIcon(HardLevel.class.getResource("/View/img/hardtable.png"));
		int winWidth = 1600; // Adjusted width based on grid size
		int winHeight = 880;//Adjusted height based on grid size

		//-----------------------------------yomna-----------------------------------
		pauseButton = new JButton("Pause");
		pauseButton.addActionListener(new PauseButtonListener());
		pauseButton.setBorder(new RoundedBorder(10));
		ImageIcon pauseIcon = new ImageIcon(EasyLevel.class.getResource("/View/img/pause.png"));
		pauseButton.setIcon(pauseIcon );
		pauseButton.setBounds(1790,70, 55, 60);
		contentPane.add(pauseButton);
		//        -----------------------------------yomna----------------------------

		// Scale down the size of the snake image
		Image scaledWinImage = winIcon.getImage().getScaledInstance(winWidth, winHeight, Image.SCALE_SMOOTH);
		ImageIcon scaledWinIcon = new ImageIcon(scaledWinImage);

		// Create a JLabel for the scaled snake image
		JLabel lblNewLabel_w2  = new JLabel(scaledWinIcon);
		lblNewLabel_w2.setBounds(185, 50, 1600,880);//
		// Add the snake label to the content 
		contentPane.add(lblNewLabel_w2);
		lblNewLabel_w2.setVisible(true);


		g.PlacespecialSquares(Levels.Hard);
		g.placeNormalSquares();
		g.PlaceSnakes();
		g.placeLadders();

		/****************************dice*************************************************************/
		// roll dice button to start the rolling- ghaidaa
		diceButton = new JButton("Roll Dice");
		diceButton.setIcon(new ImageIcon(getClass().getResource("/View/img/roll.jpg")));
		diceButton.setBounds(43, 902, 134, 49);
		diceButton.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isPaused) {
					System.out.println("in dice btn");
					PlayAudio.playDiceSound(); // calling the function that play the sound of RollDice -Yara
					rollDiceAnimation(g,num);
				}

			}});
		contentPane.add(diceButton); 

		diceLabel = new JLabel("");
		diceLabel.setIcon(new ImageIcon(HardLevel.class.getResource("/View/img/1.png")));
		diceLabel.setBounds(43, 722, 150, 150);

		contentPane.add(diceLabel); // Add the dice label to lblEasyTable panel
		diceLabel.setVisible(true);
		// the faces of the dice in hard level , to use it in the annimation - ghaidaa
		diceIcons = new ImageIcon[10];

		for (int i = 0; i < 7; i++) {
			ImageIcon originalIcon = new ImageIcon(getClass().getResource("/View/img/" + i  + ".png"));
			Image resizedImage = originalIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			diceIcons[i] = new ImageIcon(resizedImage);
		}
		ImageIcon originalIcon1 = new ImageIcon(getClass().getResource("/View/img/EasyD.png"));
		Image resizedImage1 = originalIcon1.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		diceIcons[7] = new ImageIcon(resizedImage1);
		ImageIcon originalIcon2 = new ImageIcon(getClass().getResource("/View/img/HardD.png"));
		Image resizedImage2 = originalIcon2.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		diceIcons[8] = new ImageIcon(resizedImage2);
		ImageIcon originalIcon3 = new ImageIcon(getClass().getResource("/View/img/MedD.png"));
		Image resizedImage3 = originalIcon3.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		diceIcons[9] = new ImageIcon(resizedImage3);

		ImageIcon p1icon;
		if(p1.getPlayerColor()==PlayerColor.Red)
		{
			p1icon= new ImageIcon(HardLevel.class.getResource("/View/img/r.png"));
			imgIcn1=new ImageIcon(HardLevel.class.getResource("/View/img/r.png"));
		}
		else if(p1.getPlayerColor()==PlayerColor.Green)
		{
			p1icon= new ImageIcon(HardLevel.class.getResource("/View/img/g.png"));
			imgIcn1=new ImageIcon(HardLevel.class.getResource("/View/img/g.png"));
		}
		else if(p1.getPlayerColor()==PlayerColor.Blue)
		{
			p1icon= new ImageIcon(HardLevel.class.getResource("/View/img/b.png"));
			imgIcn1=new ImageIcon(HardLevel.class.getResource("/View/img/b.png"));
		}
		else {
			p1icon= new ImageIcon(HardLevel.class.getResource("/View/img/y.png"));
			imgIcn1=new ImageIcon(HardLevel.class.getResource("/View/img/y.png"));
		}
		Image scaledP1Image = p1icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon scaledP1Icon = new ImageIcon(scaledP1Image);
		//setting icon on game
		img1=imgIcn1.getImage().getScaledInstance(N, N, Image.SCALE_SMOOTH);
		finalIcon1= new ImageIcon(img1);

		p1OnGame=new JLabel(finalIcon1);//finish putting the icon only setbound and set visible left


		// Create a JLabel for player p1
		p1Label = new JLabel(scaledP1Icon);


		int p1X = 35; // Adjusted x position based on the board offset and grid size
		int p1Y = 80; // Adjusted y position based on the board offset and grid size
		p1Label.setBounds(p1X, p1Y, 100, 100); // Set bounds for player p1 label


		// Add player p1 label to the content pane
		contentPane.add(p1Label);

		// Ensure player p1 label is visible
		p1Label.setVisible(true);

		p1name = new JLabel(p1.getNickName());
		p1name.setFont(new Font("Times New Roman", Font.BOLD, 16));
		p1name.setBounds(73, 70, 200, 13);
		contentPane.add(p1name);

		/******************************p2**********************/
		ImageIcon p2icon;
		if(p2.getPlayerColor()==PlayerColor.Red) {
			p2icon= new ImageIcon(HardLevel.class.getResource("/View/img/r.png"));
			imgIcn2=new ImageIcon(HardLevel.class.getResource("/View/img/r.png"));
		}
		else if(p2.getPlayerColor()==PlayerColor.Green) {
			p2icon= new ImageIcon(HardLevel.class.getResource("/View/img/g.png"));
			imgIcn2=new ImageIcon(HardLevel.class.getResource("/View/img/g.png"));
		}
		else if(p2.getPlayerColor()==PlayerColor.Blue) {
			p2icon= new ImageIcon(HardLevel.class.getResource("/View/img/b.png"));
			imgIcn2=new ImageIcon(HardLevel.class.getResource("/View/img/b.png"));
		}
		else {
			p2icon= new ImageIcon(HardLevel.class.getResource("/View/img/y.png"));
			imgIcn2=new ImageIcon(HardLevel.class.getResource("/View/img/y.png"));
		}
		Image scaledP2Image = p2icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon scaledP2Icon = new ImageIcon(scaledP2Image);
		// Create a JLabel for player p2
		p2Label = new JLabel(scaledP2Icon);
		p2Label.setBounds(p1X, 180, 100, 100); // Set bounds for player p1 label

		// Add player p1 label to the content pane
		contentPane.add(p2Label);
		System.out.println("queueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+p2Label);
		// Ensure player p1 label is visible
		p2Label.setVisible(true);

		img2=imgIcn2.getImage().getScaledInstance(N, N, Image.SCALE_SMOOTH);
		finalIcon2= new ImageIcon(img2);

		p2OnGame=new JLabel(finalIcon2);//finish putting the icon only setbound and set visible left





		p2name = new JLabel(p2.getNickName());
		p2name.setFont(new Font("Times New Roman", Font.BOLD, 16));
		p2name.setBounds(73, 170, 200, 13);
		contentPane.add(p2name);

		/******************************p3***************/

		if(num>2) {
			ImageIcon p3icon;
			if(p3.getPlayerColor()==PlayerColor.Red) {
				p3icon= new ImageIcon(HardLevel.class.getResource("/View/img/r.png"));
				imgIcn3=new ImageIcon(HardLevel.class.getResource("/View/img/r.png"));
			}
			else if(p3.getPlayerColor()==PlayerColor.Green) {
				p3icon= new ImageIcon(HardLevel.class.getResource("/View/img/g.png"));
				imgIcn3=new ImageIcon(HardLevel.class.getResource("/View/img/g.png"));
			}
			else if(p3.getPlayerColor()==PlayerColor.Blue) {
				p3icon= new ImageIcon(HardLevel.class.getResource("/View/img/b.png"));
				imgIcn3=new ImageIcon(HardLevel.class.getResource("/View/img/b.png"));
			}
			else {
				p3icon= new ImageIcon(HardLevel.class.getResource("/View/img/y.png"));
				imgIcn3=new ImageIcon(HardLevel.class.getResource("/View/img/y.png"));
			}
			Image scaledP3Image = p3icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			ImageIcon scaledP3Icon = new ImageIcon(scaledP3Image);
			// Create a JLabel for player p2
			p3Label = new JLabel(scaledP3Icon);
			p3Label.setBounds(p1X, 280, 100, 100); // Set bounds for player p1 label

			// Add player p1 label to the content pane
			contentPane.add(p3Label);

			// Ensure player p1 label is visible
			p3Label.setVisible(true);

			img3=imgIcn3.getImage().getScaledInstance(N, N, Image.SCALE_SMOOTH);
			finalIcon3= new ImageIcon(img3);

			p3OnGame=new JLabel(finalIcon3);//finish putting the icon only setbound and set visible left

			p3name = new JLabel(p3.getNickName());
			p3name.setFont(new Font("Times New Roman", Font.BOLD, 16));
			p3name.setBounds(73, 270, 200, 13);
			contentPane.add(p3name);


			if(num==4) {
				ImageIcon p4icon;
				if(p4.getPlayerColor()==PlayerColor.Red) {
					p4icon= new ImageIcon(HardLevel.class.getResource("/View/img/r.png"));
					imgIcn4=new ImageIcon(HardLevel.class.getResource("/View/img/r.png"));
				}
				else if(p4.getPlayerColor()==PlayerColor.Green) {
					p4icon= new ImageIcon(HardLevel.class.getResource("/View/img/g.png"));
					imgIcn4=new ImageIcon(HardLevel.class.getResource("/View/img/g.png"));
				}
				else if(p4.getPlayerColor()==PlayerColor.Blue) {
					p4icon= new ImageIcon(HardLevel.class.getResource("/View/img/b.png"));
					imgIcn4=new ImageIcon(HardLevel.class.getResource("/View/img/b.png"));
				}
				else {
					p4icon= new ImageIcon(HardLevel.class.getResource("/View/img/y.png"));
					imgIcn4=new ImageIcon(HardLevel.class.getResource("/View/img/y.png"));
				}
				Image scaledP4Image = p4icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				ImageIcon scaledP4Icon = new ImageIcon(scaledP4Image);
				// Create a JLabel for player p2
				p4Label = new JLabel(scaledP4Icon);
				p4Label.setBounds(p1X, 380, 100, 100); // Set bounds for player p1 label

				// Add player p1 label to the content pane
				contentPane.add(p4Label);

				// Ensure player p1 label is visible
				p4Label.setVisible(true);
				img4=imgIcn4.getImage().getScaledInstance(N, N, Image.SCALE_SMOOTH);
				finalIcon4= new ImageIcon(img4);

				p4OnGame=new JLabel(finalIcon4);//finish putting the icon only setbound and set visible left


				p4name = new JLabel(p4.getNickName());
				p4name.setFont(new Font("Times New Roman", Font.BOLD, 16));
				p4name.setBounds(73, 370, 200, 13);
				contentPane.add(p4name);

			}
		}


		// Calculate the size of the snake image
		int tWidth ; // Adjusted width based on grid size
		int tHeight ;//Adjusted height based on grid size

		// Scale down the size of the snake image
		Image scaledTImage ;
		ImageIcon scaledTIcon ;


		ImageIcon tIcon = new ImageIcon(Winner.class.getResource("/View/img/timer.png"));
		tWidth = 200; // Adjusted width based on grid size
		tHeight = 70;//Adjusted height based on grid size

		// Scale down the size of the snake image
		scaledTImage = tIcon.getImage().getScaledInstance(tWidth, tHeight, Image.SCALE_SMOOTH);
		scaledTIcon = new ImageIcon(scaledTImage);

		// Create a JLabel for the scaled snake image
		JLabel lblNewLabel_time  = new JLabel(scaledTIcon);
		lblNewLabel_time.setBounds(10, 0,tWidth, tHeight);//

		// Add the snake label to the content pane
		contentPane.add(lblNewLabel_time);
		lblNewLabel_time.setVisible(true);
		/*****************************timer*******************************************/
		// Calculate the size of the snake image
		timerLabel = new JLabel("00:00:00");
		timerLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timerLabel.setBounds(60, 20, 100, 30);

		contentPane.add(timerLabel,0);

		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isPaused) {
					secondsElapsed++;
					int hours = secondsElapsed / 3600;
					int minutes = (secondsElapsed % 3600) / 60;
					int seconds = secondsElapsed % 60;
					timerLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));

					// Check condition and stop timer if condition is met
					if (conditionMet()) {
						stopTimer();
						// Save the end time when the condition is met
						endTime = System.currentTimeMillis();
						long Lseconds = (System.currentTimeMillis() / 1000) % 60;
						long Lminutes = (System.currentTimeMillis() / (1000 * 60)) % 60;
						long Lhours = (System.currentTimeMillis() / (1000 * 60 * 60)) % 24;

						// Format the time
						String formattedTime = String.format("%02d:%02d:%02d", Lhours, Lminutes, Lseconds);
						System.out.println("End Time: " + endTime); // Print end time for demonstration
						g.setEndTime(formattedTime);
					}
				}
			}
		});
		startTimer();
		//
		//		BackButton backButton = new BackButton();
		//		backButton.setBounds(800, 950, 100, 40); // Set the bounds of the button
		//		backButton.setText("Home"); // Set the text of the button
		//		backButton.setFont(new Font("Arial", Font.BOLD, 16)); // Set the font of the button text
		//		backButton.setForeground(Color.black); // Set the text color
		//		backButton.setHoverBackgroundColor(Color.white); // Set the background color when hovered
		//		backButton.setPressedBackgroundColor(Color.decode("#7f7f7f")); // Set the background color when pressed
		//		backButton.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//				// TODO Auto-generated method stub
		//				MainFrame f=new MainFrame();
		//				f.setVisible(true);
		//				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
		//				frame.dispose();
		//			}
		//
		//		});
		//		contentPane.add(backButton,0);
		Container contentPane = getContentPane();
		contentPane.setLayout(null); // set audio
		ImageIcon defaultIcon = new ImageIcon(EasyLevel.class.getResource("/View/img/audioOn.png"));
		ImageIcon clickedIcon = new ImageIcon(EasyLevel.class.getResource("/View/img/audioOff.png"));

		// Create back button with icons
		AudioButton aButton = new AudioButton(defaultIcon, clickedIcon);

		// Set the bounds of the button
		aButton.setBounds(1790, 5, 50, 50); // x, y, width, height

		// Add the button to the content pane
		contentPane.add(aButton,0);
		winIcon = new ImageIcon(HardLevel.class.getResource("/View/img/win.png"));
		winWidth = 80; // Adjusted width based on grid size
		winHeight = 80;//Adjusted height based on grid size

		// Scale down the size of the snake image
		scaledWinImage = winIcon.getImage().getScaledInstance(winWidth, winHeight, Image.SCALE_SMOOTH);
		scaledWinIcon = new ImageIcon(scaledWinImage);

		// Create a JLabel for the scaled snake image
		JLabel lblNewLabel_3  = new JLabel(scaledWinIcon);
		lblNewLabel_3.setBounds(1680, 50,80,80);//

		// Add the snake label to the content pane
		contentPane.add(lblNewLabel_3);
		lblNewLabel_3.setVisible(true);
		contentPane.setComponentZOrder(lblNewLabel_3, 0);    
		/***************************************************************************************************************/
		//JLabel lblNewLabel = new JLabel("");
		//lblNewLabel.setIcon(new ImageIcon(MediumLevel.class.getResource("/View/img/game.png")));
		//lblNewLabel.setBounds(0, 0, 1200, 900);
		//contentPane.add(lblNewLabel);
		//img now working
		//Load the image
		ImageIcon icon = new ImageIcon(MediumLevel.class.getResource("/View/img/game.png"));
		System.out.println("Image loaded: " + icon.getImageLoadStatus());

		//Verify image dimensions
		int containerWidth = 1900;
		int containerHeight = 1100;
		System.out.println("Container size: " + containerWidth + "x" + containerHeight);

		//Create JLabel with scaled image
		Image scaledImg = icon.getImage().getScaledInstance(containerWidth, containerHeight, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImg);
		JLabel lblNewLabel = new JLabel(scaledIcon);
		lblNewLabel.setBounds(0, 0,containerWidth, containerHeight);//
		//Set layout manager to BorderLayout


		//Add JLabel to center of contentPane
		contentPane.add(lblNewLabel);
		lblNewLabel_3.setVisible(true);
		//Repaint container
		contentPane.revalidate();
		contentPane.repaint();

		contentPane.repaint();
		int i,j;

		for(i=0; i<g.getSnakes().size();i++)
		{
			SnakeColor color= g.getSnakes().get(i).getColor();
			int xHead=g.getSnakes().get(i).getXHeadNum();
			int xTail=g.getSnakes().get(i).getXTailNum();
			int yHead=g.getSnakes().get(i).getYHeadNum();
			int yTail=g.getSnakes().get(i).getYTailNum();
			if(color==SnakeColor.Blue)
			{
				setbluesnake(xHead,yHead,xTail,yTail);
				System.out.println("bluessnake (" +xHead  + "," + yHead+ "," + xTail+ "," + yTail+ "):" );

			}
			else if(color==SnakeColor.Green)
			{
				setgreensnake(xHead,yHead,xTail,yTail);
				System.out.println("greensnake (" + xHead + "," + yHead +"," +  xTail+ "," + yTail+ "):" );

			}
			else if(color==SnakeColor.Red)
			{
				setredsnake(xHead,yHead);
				System.out.println("redsnake (" + xHead + "," + yHead+"," +  xTail+ "," + yTail+ "):" );

			}
			else if (color==SnakeColor.Yellow)
			{
				setyellowsnake(xHead, yHead, xTail, yTail);
				System.out.println("yellow (" + xHead + "," + yHead +"," +  xTail+ "," + yTail+ "):" );

			}
		}
		for(i=0; i<g.getLadders().size();i++)
		{

			int length= g.getLadders().get(i).getLength();
			int xHead=g.getLadders().get(i).getXEnd();
			int xTail=g.getLadders().get(i).getXStart();
			int yHead=g.getLadders().get(i).getYEnd();
			int yTail=g.getLadders().get(i).getYStart();
			System.out.println("lader ("+ length + "," + xHead + "," + yHead +"," +  xTail+ "," + yTail+"):" );
			setLadders(length,xHead,yHead,xTail,yTail);
		}
		for (i=0 ; i<13; i++)
		{
			for (j=0 ; j<13; j++ )
			{
				if(g.getPlaces()[i][j]==2||g.getPlaces()[i][j]==1) {
					setsurprise(i, j);
					System.out.println("surprise (" + i + "," + j + "):" );

				}
				else if(g.getPlaces()[i][j]==3||g.getPlaces()[i][j]==4||g.getPlaces()[i][j]==5) {
					setq(i, j);
					System.out.println("question (" + i + "," + j + "):" );

				}
			}
		}


		/********************p4*******************************************************************88*/

		setPlayer(p1);//
		setPlayer(p2);//	
		if(num>2) {
			g.getPlayers().add(p3);
			setPlayer(p3);//
			if(num==4) {
				setPlayer(p4);//

				g.getPlayers().add(p4);
			}
		}



		// Calculate the position of the snake head and tail
		/**********************winner**********************/

	}
	class PauseButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == pauseButton) {
				isPaused = !isPaused;
				if (isPaused) {
					ImageIcon pauseIcon = new ImageIcon(EasyLevel.class.getResource("/View/img/resume.png"));
					pauseButton.setIcon(pauseIcon );
					timer.stop();
				} else {
					ImageIcon pauseIcon = new ImageIcon(EasyLevel.class.getResource("/View/img/pause.png"));
					pauseButton.setIcon(pauseIcon );
					timer.start();
				}
			}
		}
	}

	public void setbluesnake(int xhead, int yhead, int xtail, int ytail) {
		// Load the snake image
		ImageIcon snakeIcon = new ImageIcon(MediumLevel.class.getResource("/View/img/bluesnake2.png"));

		// Calculate the position of the snake head and tail
		int snakeHeadX = 185 + xhead * 124; // Adjusted x position based on the board offset and grid size
		int snakeHeadY = 880- yhead * 68; // Adjusted y position based on the board offset and grid size
		int snakeTailX = 185 + xtail * 124; // Adjusted x position based on the board offset and grid size
		int snakeTailY =  880- ytail * 68; // Adjusted y position based on the board offset and grid size

		// Calculate the size of the snake image
		int snakeWidth = Math.abs(snakeHeadX - snakeTailX) + 124; // Adjusted width based on grid size
		int snakeHeight = Math.abs(snakeHeadY - snakeTailY) + 68; // Adjusted height based on grid size

		// Scale down the size of the snake image
		Image scaledSnakeImage = snakeIcon.getImage().getScaledInstance(snakeWidth, snakeHeight, Image.SCALE_SMOOTH);
		ImageIcon scaledSnakeIcon = new ImageIcon(scaledSnakeImage);

		// Create a JLabel for the scaled snake image
		lblSnake = new JLabel(scaledSnakeIcon);
		lblSnake.setBounds(Math.min(snakeHeadX, snakeTailX), Math.min(snakeHeadY, snakeTailY), snakeWidth, snakeHeight);

		// Add the snake label to the content pane
		contentPane.add(lblSnake);

		// Ensure the snake label is visible
		lblSnake.setVisible(true);

		// Move the snake label to the front
		contentPane.setComponentZOrder(lblSnake, 0);

		// Repaint the content pane to ensure the changes are displayed
		contentPane.revalidate();
		contentPane.repaint();
	}
	public void setgreensnake(int xhead, int yhead, int xtail, int ytail) {
		// Load the snake image
		ImageIcon snakeIcon = new ImageIcon(MediumLevel.class.getResource("/View/img/greensnake.png"));

		// Calculate the position of the snake head and tail
		int snakeHeadX = 185 + xhead * 124; // Adjusted x position based on the board offset and grid size
		int snakeHeadY =  880 - yhead * 68; // Adjusted y position based on the board offset and grid size
		int snakeTailX = 188 + xtail * 124; // Adjusted x position based on the board offset and grid size
		int snakeTailY =  880  - ytail *  68; // Adjusted y position based on the board offset and grid size

		// Calculate the size of the snake image
		int snakeWidth = Math.abs(snakeHeadX - snakeTailX) + 124; // Adjusted width based on grid size
		int snakeHeight = Math.abs(snakeHeadY - snakeTailY) + 68; // Adjusted height based on grid size

		// Scale down the size of the snake image
		Image scaledSnakeImage = snakeIcon.getImage().getScaledInstance(snakeWidth, snakeHeight, Image.SCALE_SMOOTH);
		ImageIcon scaledSnakeIcon = new ImageIcon(scaledSnakeImage);

		// Create a JLabel for the scaled snake image
		glblSnake = new JLabel(scaledSnakeIcon);
		glblSnake.setBounds(snakeHeadX,snakeHeadY, snakeWidth, snakeHeight);

		// Add the snake label to the content pane
		contentPane.add(glblSnake);

		// Ensure the snake label is visible
		glblSnake.setVisible(true);

		// Move the snake label to the front
		contentPane.setComponentZOrder(glblSnake, 0);

		// Repaint the content pane to ensure the changes are displayed
		contentPane.revalidate();
		contentPane.repaint();
	}
	public void setredsnake(int xhead, int yhead) {
		// Load the snake image
		ImageIcon snakeIcon = new ImageIcon(MediumLevel.class.getResource("/View/img/redsnake.png"));

		// Calculate the position of the snake head and tail
		int snakeHeadX = 185 + xhead * 124; // Adjusted x position based on the board offset and grid size//170
		int snakeHeadY =880-yhead*68;

		// Calculate the size of the snake image
		int snakeWidth = 110; // Adjusted width based on grid size
		int snakeHeight = 68;//Adjusted height based on grid size

		// Scale down the size of the snake image
		Image scaledSnakeImage = snakeIcon.getImage().getScaledInstance(snakeWidth, snakeHeight, Image.SCALE_SMOOTH);
		ImageIcon scaledSnakeIcon = new ImageIcon(scaledSnakeImage);

		// Create a JLabel for the scaled snake image
		rlblSnake = new JLabel(scaledSnakeIcon);
		rlblSnake.setBounds(snakeHeadX,snakeHeadY, snakeWidth, snakeHeight);

		// Add the snake label to the content pane
		contentPane.add(rlblSnake);

		// Ensure the snake label is visible
		rlblSnake.setVisible(true);

		// Move the snake label to the front
		contentPane.setComponentZOrder(rlblSnake, 0);

		// Repaint the content pane to ensure the changes
		contentPane.revalidate();
		contentPane.repaint();
	}
	public void  setyellowsnake(int xhead, int yhead, int xtail, int ytail) {
		ImageIcon snakeIcon = new ImageIcon(MediumLevel.class.getResource("/View/img/yellosnake.png"));

		// Calculate the position of the snake head and tail
		int snakeHeadX = 185 + xhead * 124; // Adjusted x position based on the board offset and grid size
		int snakeHeadY = 880 - yhead * 68; // Adjusted y position based on the board offset and grid size
		int snakeTailX = 185 + xtail * 124; // Adjusted x position based on the board offset and grid size
		int snakeTailY = 880 - ytail * 68; // Adjusted y position based on the board offset and grid size
		// Calculate the size of the snake image
		int snakeWidth = Math.abs(snakeHeadX - snakeTailX) + 124; // Adjusted width based on grid size
		int snakeHeight = Math.abs(snakeHeadY - snakeTailY) + 68; // Adjusted height based on grid size

		// Scale down the size of the snake image
		Image scaledSnakeImage = snakeIcon.getImage().getScaledInstance(snakeWidth, snakeHeight, Image.SCALE_SMOOTH);
		ImageIcon scaledSnakeIcon = new ImageIcon(scaledSnakeImage);
		// Create a JLabel for the scaled snake image
		ylblSnake = new JLabel(scaledSnakeIcon);
		ylblSnake.setBounds(snakeHeadX,snakeHeadY, snakeWidth, snakeHeight);

		// Add the snake label to the content pane
		contentPane.add(ylblSnake);

		// Ensure the snake label is visible
		ylblSnake.setVisible(true);

		// Move the snake label to the front
		contentPane.setComponentZOrder(ylblSnake, 0);

		// Repaint the content pane to ensure the changes are displayed
		contentPane.revalidate();
		contentPane.repaint();
	}

	public void setLadders(int typeOfLader, int xhead, int yhead, int xtail, int ytail) {
		int ladderHeadX = 190 + xhead * 124; // Adjusted x position based on the board offset and grid size
		int ladderHeadY = 880 - yhead * 68; // Adjusted y position based on the board offset and grid size
		int ladderTailX = 190 + xtail * 124; // Adjusted x position based on the board offset and grid size
		int ladderTailY = 880 - ytail * 68; // Adjusted y position based on the board offset and grid size
		ImageIcon ladderIcon=null;
		// Calculate the size of the snake image
		int ladderWidth = Math.abs(ladderHeadX - ladderTailX) + 89; // Adjusted width based on grid size
		int ladderHeight = Math.abs(ladderHeadY - ladderTailY) + 60; // Adjusted height based on grid size

		System.out.println(typeOfLader);
		// Load the ladder image based on the ladder type
		switch (typeOfLader) {
		case 1:
			ladderIcon = new ImageIcon(HardLevel.class.getResource("/View/img/ladder1.png"));
			break;
		case 2:
			ladderIcon = new ImageIcon(HardLevel.class.getResource("/View/img/ladder2.png"));
			break;
		case 3:
			ladderIcon = new ImageIcon(HardLevel.class.getResource("/View/img/ladder3.png"));
			break;
		case 4:
			ladderIcon = new ImageIcon(HardLevel.class.getResource("/View/img/ladder4.png"));
			break;
		case 5:
			ladderIcon = new ImageIcon(HardLevel.class.getResource("/View/img/ladder5.png"));
			break;
		case 6:
			ladderIcon = new ImageIcon(HardLevel.class.getResource("/View/img/ladder6.png"));
			break;
		case 7: 
			ladderIcon = new ImageIcon(HardLevel.class.getResource("/View/img/ladder7.png"));
			break;
		case 8: 
			ladderIcon = new ImageIcon(HardLevel.class.getResource("/View/img/ladder8.png"));
			break;
		default:
			// Default ladder image

			break;
		}

		// Scale down the size of the snake image
		Image scaledLadderImage = ladderIcon.getImage().getScaledInstance(ladderWidth, ladderHeight, Image.SCALE_SMOOTH);
		ImageIcon scaledLadderIcon = new ImageIcon(scaledLadderImage);
		// Create a JLabel for the scaled snake image
		lblLadder = new JLabel(scaledLadderIcon);
		lblLadder.setBounds(ladderHeadX,ladderHeadY, ladderWidth, ladderHeight);

		// Add the snake label to the content pane
		contentPane.add(lblLadder);

		// Ensure the snake label is visible
		lblLadder.setVisible(true);

		// Move the snake label to the front
		contentPane.setComponentZOrder(lblLadder, 0);

		// Repaint the content pane to ensure the changes are displayed
		contentPane.revalidate();
		contentPane.repaint();
	}
	public void setsurprise(int x, int y) {
		// Load the surprise image
		ImageIcon snakeIcon = new ImageIcon(MediumLevel.class.getResource("/View/img/q.png"));

		// Calculate the position of the surprise
		int sX = 200 + x * 124; 
		int sY =892-y*68;

		// Calculate the size of the snake image
		int width = 50; // Adjusted width based on grid size
		int height = 50;//Adjusted height based on grid size

		// Scale down the size of the snake image
		Image scaledImage = snakeIcon.getImage().getScaledInstance( width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledSnakeIcon = new ImageIcon(scaledImage);

		// Create a JLabel for the scaled snake image
		lblsur = new JLabel(scaledSnakeIcon);
		lblsur.setBounds(sX,sY, width, height);

		// Add the snake label to the content pane
		contentPane.add(lblsur);

		// Ensure the snake label is visible
		lblsur.setVisible(true);

		// Move the snake label to the front
		contentPane.setComponentZOrder(lblsur, 0);

		// Repaint the content pane to ensure the changes
		contentPane.revalidate();
		contentPane.repaint();
	}
	public void setq(int x, int y) {
		// Load the surprise image
		ImageIcon snakeIcon = new ImageIcon(MediumLevel.class.getResource("/View/img/surprise.png"));

		// Calculate the position of the surprise
		int sX = 200 + x * 124; 
		int sY =900-y*68;

		// Calculate the size of the snake image
		int width = 40; // Adjusted width based on grid size
		int height = 40;//Adjusted height based on grid size

		// Scale down the size of the snake image
		Image scaledImage = snakeIcon.getImage().getScaledInstance( width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledSnakeIcon = new ImageIcon(scaledImage);

		// Create a JLabel for the scaled snake image
		lblq = new JLabel(scaledSnakeIcon);
		lblq.setBounds(sX,sY, width, height);

		// Add the snake label to the content pane
		contentPane.add(lblq);

		// Ensure the snake label is visible
		lblq.setVisible(true);

		// Move the snake label to the front
		contentPane.setComponentZOrder(lblq, 0);

		// Repaint the content pane to ensure the changes
		contentPane.revalidate();
		contentPane.repaint();
	}
	private void rollDiceAnimation(Game g,int num) {
		System.out.println("im in the roll dice func");
		final int NUM_FRAMES = 15; // Number of frames for the dice animation
		final int DELAY = 50; // Delay between each frame in milliseconds
		Random random = new Random();

		Timer timer = new Timer(DELAY, new ActionListener() {
			int frameCount = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isPaused) {
					// Change dice image randomly for animation- ghaidaa
					// Create a new JLabel with the dice image and add it to the lblEasyTable panel
					int randomIndex = random.nextInt(diceIcons.length);

					// Create a new JLabel with the dice image and add it to the lblEasyTable panel
					diceLabel.setIcon(diceIcons[randomIndex]);
					diceLabel.setBounds(40, 722, 150, 150);
					frameCount++;
					if (frameCount >= NUM_FRAMES) {

						Player p=g.CurrentTurn();

						int bx=p.getPlayerRow();
						int by=p.getPlayerCol();


						int squareResult;
						Object  CHECK = g.Roll();
						System.out.println("********************************************************8the dice rolled"+CHECK);
						Question q;
						String answer="";
						int awx=0,awy=0;
						if(CHECK instanceof Integer) {
							diceLabel.setIcon(diceIcons[(Integer)CHECK]);
							String s= "you have to walk "+CHECK+" steps!!";
							setPlayerText(p, s);

							answer=Integer.toString((Integer)CHECK);
							int ax=p.getPlayerRow();
							int ay=p.getPlayerCol();
							System.out.println("the current player is:"+p.getPlayerColor());
							movePlayer(p, bx, by,ax, ay,g);//moved player once


							while( g.UpdatePlayerPlace()!=0) {//

								if (g.checkQuestionSquare2() == true) {
									Question myQ = g.checkQuestionSquare();
									System.out.println("questionnnnnnn type from question square!!");

									if (myQ.getQLevel().equals(Levels.Easy)) {
										answer = "easy question";
										s= "you have landed on easy question";
										setPlayerText(p, s);
									} else if (myQ.getQLevel().equals(Levels.Medium)) {
										answer = "medium question";
										s= "you have landed on medium question";
										setPlayerText(p, s);
									} else {
										answer = "hard question";
										s= "you have landed on hard question";
										setPlayerText(p, s);
									}
									System.out.println("questionnnn frame");

									// Create the QuestionFrame- ghaidaa
									QuestionFrame qu = new QuestionFrame(myQ, new QuestionFrame.QuestionAnsweredListener() {
										@Override
										public void onQuestionAnswered(boolean isCorrect) {
											// This method will be called when the player answers the question- ghaidaa
											g.updateByQuestion(myQ, isCorrect);
											System.out.println("Answered: " + isCorrect);
										}
									});
									qu.setVisible(true);

									awx=p.getPlayerRow();//new x
									awy=p.getPlayerCol();// new y

									movePlayer(p, ax, ay,awx, awy,g);
									landedOn(g);

								}if( (g.UpdatePlayerPlace()==5||g.UpdatePlayerPlace()==4||g.UpdatePlayerPlace()==3)&&(ax==awx&&ay==awy))
									break;

								awx=p.getPlayerRow();//new x
								awy=p.getPlayerCol();// new y

								movePlayer(p, ax, ay,awx, awy,g);
								landedOn(g);
							}


							if(g.GameBoard.getPosition(p.getPlayerRow(), p.getPlayerCol())==X*Y) {
								s= "IS THE WINNER!!!!! ";
								setPlayerText(p, s);
							}else {
								g.NextPlayer();
								setPlayerText(g.CurrentTurn(), "you have to roll the dice");
								lineMangment(g.CurrentTurn().getPlayeringame(), num);
							}

						}/**************************question dice*************************8*/
						else {

							System.out.println("the current player is:" + p.getPlayerColor());
							System.out.println("questionnnnnnn type!!");
							q = (Question) CHECK;
							String s="";
							if (q.getQLevel().equals(Levels.Easy)) {
								diceLabel.setIcon(diceIcons[7]);//adan added
								answer = "easy question";
								s= "you have landed on easy question";
								setPlayerText(p, s);
							} else if (q.getQLevel().equals(Levels.Medium)) {
								diceLabel.setIcon(diceIcons[9]);//adan added
								answer = "medium question";
								s= "you have landed on medium question";
								setPlayerText(p, s);
							} else {
								diceLabel.setIcon(diceIcons[8]);//adan added
								answer = "hard question";
								s= "you have landed on hard question";
								setPlayerText(p, s);
							}
							System.out.println("questionnnn frame");
							QuestionFrame qu = new QuestionFrame(q, new QuestionFrame.QuestionAnsweredListener() {
								@Override
								public void onQuestionAnswered(boolean isCorrect) {
									// This method will be called when the player answers the question
									g.updateByQuestion(q, isCorrect);
									System.out.println("Answered: " + isCorrect);
									String s= "you'r answer is "+isCorrect;
									setPlayerText(p, s);
									// Continue with your logic here
									int ax = p.getPlayerRow();
									int ay = p.getPlayerCol();

									movePlayer(p, bx, by, ax, ay,g);    //move player by answer

									while (g.UpdatePlayerPlace() != 0) {//
										String answer="";
										if (g.checkQuestionSquare2() == true) {
											Question myQ = g.checkQuestionSquare();
											System.out.println("questionnnnnnn type from question square!!");

											if (myQ.getQLevel().equals(Levels.Easy)) {//check

												answer = "easy question";
											} else if (myQ.getQLevel().equals(Levels.Medium)) {

												answer = "medium question";
											} else {

												answer = "hard question";
											}
											System.out.println("questionnnn frame");
											QuestionFrame c = new QuestionFrame(myQ, new QuestionFrame.QuestionAnsweredListener() {
												@Override
												public void onQuestionAnswered(boolean isCorrect) {
													// This method will be called when the player answers the question
													g.updateByQuestion(myQ, isCorrect);
													System.out.println("Answered: " + isCorrect);
													// Continue with your logic here
												}
											});
											c.setVisible(true);


											int awx=p.getPlayerRow();//new x
											int awy=p.getPlayerCol();// new y

											movePlayer(p, ax, ay,awx, awy,g);
											landedOn(g);

										}if( (g.UpdatePlayerPlace()==5||g.UpdatePlayerPlace()==4||g.UpdatePlayerPlace()==3)&&(ax==p.getPlayerRow()&&ay==p.getPlayerCol()))
											break;

										int awx = p.getPlayerRow();
										int awy = p.getPlayerCol();

										movePlayer(p, ax, ay, awx, awy,g);

									}

									if(g.GameBoard.getPosition(p.getPlayerRow(), p.getPlayerCol())==X*Y) {
										s= "IS THE WINNER!!!!! ";
										setPlayerText(p, s);
									}else {
										g.NextPlayer();
										setPlayerText(g.CurrentTurn(), "you have to roll the dice");
										lineMangment(g.CurrentTurn().getPlayeringame(), num);
									}

								}
							});
							qu.setVisible(true);
						}


						((Timer) e.getSource()).stop();
						// Simulate rolling and display the final result- ghaidaa
					}
				}
			}
		});



		timer.start();
	}


	public void movePlayer(Player player,int beforx,int befory,int afterx,int aftery,Game g) {//check
		System.out.println("The player is *******"+player.getPlayerColor()+" "+player.getPlayeringame());
		int pX=0,pY=0,bx=0,by=0;
		System.out.println("("+beforx+","+befory+")"+" to ("+afterx+","+aftery+")");


		switch(player.getPlayeringame()) {//check
		case 1:
			pX = 185 + afterx * 124; // Adjusted x position based on the board offset and grid size//170
			bx= 185 + beforx * 124;
			pY =860-aftery*68;
			by=860-befory*68;
			//p1OnGame.setLocation(pX, pY);
			break;
		case 2://+N
			pX = 185+N + afterx * 124; // Adjusted x position based on the board offset and grid size//170
			bx= 185 +N+beforx * 124;
			pY =860-aftery*68;
			by=860-befory*68;

			break;
		case 3:
			pX = 185 + afterx * 124; // Adjusted x position based on the board offset and grid size//170
			bx= 185 + beforx * 124;
			pY =860+N-aftery*68;
			by=860+N-befory*68;
			//p1OnGame.setLocation(pX, pY);
			break;
		case 4://+N
			pY =860+N-aftery*68;
			by=860+N-befory*68;
			pX = 185+N + afterx * 124; // Adjusted x position based on the board offset and grid size//170
			bx= 185 +N+beforx * 124;			break;

		}


		final int finalBx = bx; // Declare effectively final variables
		final int finalBy = by;

		int steps = 50; // Number of steps for the movement
		double deltaX = (pX-bx) / (double) steps;
		double deltaY = (pY-by) / (double) steps;
		Timer timer = new Timer(20, null); // Create a timer without ActionListener
		timer.start(); // Start the timer
		final int[] count = {0};
		if((afterx-beforx==0)&&(aftery-befory==0)) {

		}else {
			PlayAudio.PlayStepsSound();
		}
		timer.addActionListener(e -> {
			if (count[0] < steps) {
				int newX = (int) (finalBx + deltaX * count[0]);
				int newY = (int) (finalBy+ deltaY * count[0]);
				if(player.getPlayeringame()==1)
					p1OnGame.setLocation(newX, newY);
				else if(player.getPlayeringame()==2)
					p2OnGame.setLocation(newX, newY);
				else if(player.getPlayeringame()==3)
					p3OnGame.setLocation(newX, newY);
				else if(player.getPlayeringame()==4)
					p4OnGame.setLocation(newX, newY);
				count[0]++;

			} else {
				timer.stop(); // Stop the timer when the movement is complete
				System.out.println("moveeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
				if(g.GameBoard.getPosition(afterx, aftery)==X*Y) {/*winner adan1*/

					System.out.println("thw winneeeeeeeeeeeeeeer is "+player.getNickName());
					stopTimer();
					System.out.println("moveeeeeeeeeeeeeeeeeeeee2");
					System.out.println(secondsElapsed);
					Player p1=g.CurrentTurn();
					g.setWinnerId(p1.getPlayerID());
					long Lseconds = secondsElapsed % 60;
					long Lminutes = (secondsElapsed / 60) % 60;
					long Lhours = (secondsElapsed / (60 * 60)) % 24;

					// Format the time
					String formattedTime = String.format("%02d:%02d:%02d", Lhours, Lminutes, Lseconds);
					System.out.println("timeeeeeeeeeeeeeeee"+formattedTime);
					g.setEndTime(formattedTime);
					SysData.gamesList.add(g);
					if(g.getPlayersFinalPLaces().size()==2) {
						SysData.winnerPlayer.add(g.getPlayersFinalPLaces().get(1));
					}else if(g.getPlayersFinalPLaces().size()==3) {
						SysData.winnerPlayer.add(g.getPlayersFinalPLaces().get(2));
					}else {
						SysData.winnerPlayer.add(g.getPlayersFinalPLaces().get(3));
					}

					Screenshot.captureScreenshot(this,g.getGameId());

					SysData.AddGame(g);


					System.out.println(g.getPlayersFinalPLaces()
							);

					if(g.getPlayersFinalPLaces().size()==2) {
						Winner w=new Winner(g.getPlayersFinalPLaces().get(1),g.getPlayersFinalPLaces().get(0),null,null,2); 
						w.setVisible(true);
						this.setVisible(false);
					}else if(g.getPlayersFinalPLaces().size()==3) {
						Winner w=new Winner(g.getPlayersFinalPLaces().get(2),g.getPlayersFinalPLaces().get(1),g.getPlayersFinalPLaces().get(0),null,3); 
						w.setVisible(true);
						this.setVisible(false);
					}else {
						Winner w=new Winner(g.getPlayersFinalPLaces().get(3),g.getPlayersFinalPLaces().get(2),g.getPlayersFinalPLaces().get(1),g.getPlayersFinalPLaces().get(0),4); 
						w.setVisible(true);
						this.setVisible(false);
					}
					//	Winner w=new Winner(p1,); 


				}
			}
		});
	}public void setPlayerText(Player p, String text) {
		// Get the player's name
		String playerName = p.getNickName();

		// Set the full text with player name and additional text
		String fullText = "<html>Player <font color=\"" + getColorCode(p.getPlayerColor()) + "\">" + playerName + "</font> " + text + "</html>";

		// Set the full HTML text
		mytext.setText(fullText);

		// Center the text horizontally
		mytext.setHorizontalAlignment(SwingConstants.CENTER);
	}

	// Method to get color code based on PlayerColor
	private String getColorCode(PlayerColor color) {
		switch (color) {
		case Red:
			return "red";
		case Green:
			return "green";
		case Yellow:
			return "yellow";
		case Blue:
			return "blue";
		default:
			return "black"; // Default color if player color is not recognized
		}
	}




	public void lineMangment(int turn,int num) {
		switch(num) {
		case 2:
			switch(turn) {
			case 1:
				p1Label.setLocation(35, 80);
				p1name.setLocation(73,70);
				p2Label.setLocation(35, 180);
				p1name.setLocation(73,170);
				break;
			case 2:
				p1Label.setLocation(35, 180);
				p1name.setLocation(73,170);
				p2Label.setLocation(35, 80);
				p2name.setLocation(73,70);
				break;	
			}
			break;


		case 3:
			switch(turn) {
			case 1:
				p1Label.setLocation(35, 80);
				p1name.setLocation(73,70);
				p2Label.setLocation(35, 180);
				p2name.setLocation(73,170);
				p3Label.setLocation(35, 280);
				p3name.setLocation(73,270);
				break;
			case 2:

				p2Label.setLocation(35, 80);
				p2name.setLocation(73,70);
				p3Label.setLocation(35, 180);
				p3name.setLocation(73,170);
				p1Label.setLocation(35, 280);
				p1name.setLocation(73,270);
				break;
			case 3:
				p3Label.setLocation(35, 80);
				p3name.setLocation(73,70);
				p1Label.setLocation(35, 180);
				p1name.setLocation(73,170);
				p2Label.setLocation(35, 280);
				p2name.setLocation(73,270);

			}
			break;
		case 4:
			switch(turn) {
			case 1:
				p1Label.setLocation(35, 80);
				p1name.setLocation(73,70);
				p2Label.setLocation(35, 180);
				p2name.setLocation(73,170);
				p3Label.setLocation(35, 280);
				p3name.setLocation(73,270);
				p4Label.setLocation(35, 380);
				p4name.setLocation(73,370);
				break;
			case 2:
				p2Label.setLocation(35, 80);
				p2name.setLocation(73,70);
				p3Label.setLocation(35, 180);
				p3name.setLocation(73,170);
				p4Label.setLocation(35, 280);
				p4name.setLocation(73,270);
				p1Label.setLocation(35, 380);
				p1name.setLocation(73,370);
				break;
			case 3:
				p3Label.setLocation(35, 80);
				p3name.setLocation(73,70);
				p4Label.setLocation(35, 180);
				p4name.setLocation(73,170);
				p1Label.setLocation(35, 280);
				p1name.setLocation(73,270);
				p2Label.setLocation(35, 380);
				p2name.setLocation(73,370);
				break;
			case 4:
				p4Label.setLocation(35, 80);
				p4name.setLocation(73,70);
				p1Label.setLocation(35, 180);
				p1name.setLocation(73,170);
				p2Label.setLocation(35, 280);
				p2name.setLocation(73,270);
				p3Label.setLocation(35, 380);
				p3name.setLocation(73,370);
				break;



			}
			break;

		}


	}

	public void setPlayer(Player player) {
		int pX,pY;
		switch(player.getPlayeringame()) {
		case 1:
			pX = 185 + player.getPlayerRow() * 124; // Adjusted x position based on the board offset and grid size//170
			pY =860-player.getPlayerCol()*68;
			System.out.println("setting player 1");
			p1OnGame.setBounds(pX, pY, N, N); // Set bounds for player p1 label

			// Add player p1 label to the content pane
			contentPane.add(p1OnGame);
			p1OnGame.setVisible(true);
			contentPane.setComponentZOrder(p1OnGame, 0);
			contentPane.revalidate();
			contentPane.repaint();

			break;
		case 2://+N
			pX = N+185 + player.getPlayerRow() * 124; // Adjusted x position based on the board offset and grid size//170
			pY =860-player.getPlayerCol()*68;
			System.out.println("setting player 2");
			p2OnGame.setBounds(pX, pY, N, N); // Set bounds for player p2 label

			// Add player p1 label to the content pane
			contentPane.add(p2OnGame);
			p2OnGame.setVisible(true);
			contentPane.setComponentZOrder(p2OnGame, 0);
			contentPane.revalidate();
			contentPane.repaint();
			break;
		case 3://+N
			pX = 185 + player.getPlayerRow() * 124; // Adjusted x position based on the board offset and grid size//170
			pY =N-15+860-player.getPlayerCol()*68;
			System.out.println("setting player 3");
			p3OnGame.setBounds(pX, pY, N, N); // Set bounds for player p2 label

			// Add player p1 label to the content pane
			contentPane.add(p3OnGame);
			p3OnGame.setVisible(true);
			contentPane.setComponentZOrder(p3OnGame, 0);
			contentPane.revalidate();
			contentPane.repaint();
			break;
		case 4://
			pX = N+185+ player.getPlayerRow() *  124; // Adjusted x position based on the board offset and grid size//170
			pY =N-15+860-player.getPlayerCol()*68;
			System.out.println("setting player 4");
			p4OnGame.setBounds(pX, pY, N, N); // Set bounds for player p2 label

			// Add player p1 label to the content pane
			contentPane.add(p4OnGame);
			p4OnGame.setVisible(true);
			contentPane.setComponentZOrder(p4OnGame, 0);
			contentPane.revalidate();
			contentPane.repaint();
			break;

		}



	}
	/*private void completeTask() {
		Timer timer = new Timer(20, null); // Create a timer without ActionListener
		int steps=3000;
		timer.start(); // Start the timer
		final int[] count = {0};
		timer.addActionListener(e -> {
			if (count[0] < steps) {

				count[0]++;

			} else {
				timer.stop(); // Stop the timer when the movement is complete
			}
		});

    }*/
	private boolean conditionMet() {
		// Replace this with your actual condition
		return secondsElapsed >= 30000; // Stop the timer after 10 seconds for demonstration
	}

	// Method to start the timer
	public void startTimer() {
		secondsElapsed = 0; // Reset seconds elapsed
		timer.start();
	}

	// Method to stop the timer
	public void stopTimer() {
		timer.stop();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
	public void landedOn(Game g) {
		String s=" ";
		Player p=g.CurrentTurn();

		int num=g.UpdatePlayerPlace();
		if(num==1||num==2) {
			s="landed on surprise square";
			setPlayerText(p, s);

		}else if(num>=6 && num<=13) {
			s="landed on a snake :(";
			setPlayerText(p, s);


		}else if(num>=14 && num<=21) {
			s="landed on a ladder :)";
			setPlayerText(p, s);

		}else if(num==3|| num==4||num==5) {

		}
	}
}
