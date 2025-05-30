package View;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;

import Model.PlayAudio;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
public class MainFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNewButton;
	public MainFrame() {
		PlayAudio.playHPSound();  // calling the function that play Home Page sound in order to Start the music -Yara

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 70,1200, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
/********************************Adan***********************************************/
		/*adding start button to the main frame-Adan*/
		btnNewButton = new JButton("start");
		btnNewButton.addActionListener(this);
		btnNewButton.setIcon(new ImageIcon(getClass().getResource("/View/img/start.png")));
		btnNewButton.setBackground(Color.CYAN);
		btnNewButton.setBounds(466, 407, 125, 130);

		contentPane.add(btnNewButton);
		
		/*adding instructions button- Adan*/
		JButton btnNewButton_1 = new JButton("inst");

		btnNewButton_1.setIcon(new ImageIcon(getClass().getResource("/View/img/instructions.png")));
		btnNewButton_1.setBackground(Color.YELLOW);
		btnNewButton_1.setBounds(634, 407, 125, 130);
		contentPane.add(btnNewButton_1);
		//***************Yara***************** Adding Music to the button
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Instructions inst=new Instructions();
				PlayAudio.hpClip.stop();  // calling the stop function of the home page clip -Yara
				 PlayAudio.playMenuSound(); // calling the function that play button click audio -Yara
				setVisible(false);
				inst.setVisible(true);
			}
		});
		/*adding history button-Adan*/
		JButton btnNewButton_2 = new JButton("hist");
		btnNewButton_2.setIcon(new ImageIcon(getClass().getResource("/View/img/history.png")));
		btnNewButton_2.addActionListener(this);
		btnNewButton_2.setBackground(Color.CYAN);
		btnNewButton_2.setBounds(634, 561, 125, 130);
		contentPane.add(btnNewButton_2);
		/*adding question managment  button-Adan*/
		JButton btnNewButton_3 = new JButton("New button");

		btnNewButton_3.setIcon(new ImageIcon(getClass().getResource("/View/img/question.png")));
		btnNewButton_3.setBackground(Color.CYAN);
		btnNewButton_3.setBounds(466, 561, 125, 130);
		contentPane.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LogIn log=new LogIn();
				PlayAudio.hpClip.stop();  // calling the stop function of the home page clip -Yara
				PlayAudio.playMenuSound(); // calling the function that play button click audio -Yara
			
				setVisible(false);
				log.setVisible(true);
			}
		});
		ImageIcon icon = new ImageIcon(Winner.class.getResource("/View/img/MainBG.png"));
		System.out.println("Image loaded: " + icon.getImageLoadStatus());

		//Verify image dimensions
		int containerWidth = 1200;
		int containerHeight = 900;
		System.out.println("Container size: " + containerWidth + "x" + containerHeight);
		//Create JLabel with scaled image
		Image scaledImg = icon.getImage().getScaledInstance(containerWidth, containerHeight, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImg);		
		JLabel lblNewLabel = new JLabel(scaledIcon);
		lblNewLabel.setBounds(-14, 0,1200, 871);
		contentPane.add(lblNewLabel);

	}	


	public void actionPerformed(ActionEvent e) {

		FlatLaf.registerCustomDefaultsSource("View");
		FlatLightLaf.setup();
		// TODO Auto-generated method stub
		String s=e.getActionCommand();
		if(s.equals("start")) {/*start the game and move to levelgame frame-ADAN*/
			LevelGame lframe=new LevelGame();
			PlayAudio.hpClip.stop();  // calling the stop function of the home page clip -Yaraorder to Quit the music -Yara
			PlayAudio.playMenuSound(); // calling the function that play button click audio -Yara
			this.setVisible(false);//Adan
			lframe.setVisible(true);//Adan

		}
		if(s.equals("hist")) {/*start the game and move to levelgame frame-Adan*/
			History lframe=new History();
			PlayAudio.hpClip.stop();  // calling the stop function of the home page clip in order to Quit the music -Yara
			PlayAudio.playMenuSound(); // calling the function that play button click audio -Yara
			this.setVisible(false);//Adan
			lframe.setVisible(true);//Adan

		}
	}
}
