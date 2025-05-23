package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Enum.Levels;
import Model.Question;

// frame that present question and 4 answers , the user should choose one answer
public class QuestionFrame extends JFrame {

    private JPanel contentPane;
    private JLabel questionFrame;
    private boolean answer;
    public boolean answered;
    private QuestionAnsweredListener listener; // Callback listener

    public interface QuestionAnsweredListener {
        void onQuestionAnswered(boolean isCorrect);
    }

    public QuestionFrame(Question question, QuestionAnsweredListener listener) {
        this.listener = listener; // Assign the listener
        answer = false;
        answered = false;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        // Add a window listener to handle the closing action - ghaidaa
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Display a message to the user indicating that they cannot close the frame
                JOptionPane.showMessageDialog(contentPane, "Please submit your answer");
            }
        });
        setBounds(550, 200, 854, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setAlwaysOnTop(true); //  the frame stays on top

        // submit button to submit the answer that the player choosed
        JButton submitButton = new JButton("");
        ImageIcon icon = new ImageIcon(QuestionFrame.class.getResource("/View/img/submit.jpg"));
        submitButton.setIcon(icon);
        submitButton.setBounds(680, 400, 122, 38);
        contentPane.add(submitButton);
        
        JLabel lblNewLabel = new JLabel();
        lblNewLabel.setBounds(181, 36, 501, 106);
        contentPane.add(lblNewLabel);

        // Set initial font size
        int fontSize = 20;
        Font font = new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, fontSize);
        lblNewLabel.setFont(font);

        // Set content of the question
        String questionText = question.getContent();
        String formattedQuestionText = insertLineBreaks(questionText);
        lblNewLabel.setText("<html>" + formattedQuestionText + "</html>");

        // Center align the text within the label
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setVerticalAlignment(SwingConstants.CENTER);

        // set icon for easy level question
        if (question.getQLevel().equals(Levels.Easy)) {
            JLabel lblNewLabel_1 = new JLabel("");
            lblNewLabel_1.setBounds(627, 10, 203, 106);
            lblNewLabel_1.setIcon(new ImageIcon(QuestionFrame.class.getResource("/View/img/easyq.png")));
            contentPane.add(lblNewLabel_1);

            JLabel lblNewLabel_2 = new JLabel("Easy Question!");
            lblNewLabel_2.setForeground(new Color(0, 100, 0));
            lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
            lblNewLabel_2.setBounds(661, 107, 169, 29);
            contentPane.add(lblNewLabel_2);
            // set icon for hard level question
        } else if (question.getQLevel().equals(Levels.Hard)) {
            JLabel lblNewLabel_1 = new JLabel("");
            lblNewLabel_1.setBounds(627, 10, 203, 106);
            lblNewLabel_1.setIcon(new ImageIcon(QuestionFrame.class.getResource("/View/img/hardq.png")));
            contentPane.add(lblNewLabel_1);

            JLabel lblNewLabel_2 = new JLabel("Hard Question!!!");
            lblNewLabel_2.setForeground(new Color(255, 0, 0));
            lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
            lblNewLabel_2.setBounds(661, 107, 169, 29);
            contentPane.add(lblNewLabel_2);
            // set icon for Medium level question
        } else if (question.getQLevel().equals(Levels.Medium)) {
            JLabel lblNewLabel_1 = new JLabel("");
            lblNewLabel_1.setBounds(627, 10, 203, 106);
            lblNewLabel_1.setIcon(new ImageIcon(QuestionFrame.class.getResource("/View/img/medq.png")));
            contentPane.add(lblNewLabel_1);

            JLabel lblNewLabel_2 = new JLabel("Medium Question!!");
            lblNewLabel_2.setForeground(new Color(255, 165, 0));
            lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
            lblNewLabel_2.setBounds(661, 107, 169, 29);
            contentPane.add(lblNewLabel_2);
        }
        // order the answers of the question randomaly
        ArrayList<String> shuffledAnswers = new ArrayList<>(question.getAnswer());
        Collections.shuffle(shuffledAnswers);

        JRadioButton[] radioButtons = new JRadioButton[4];
        ButtonGroup G = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            radioButtons[i] = new JRadioButton();
            radioButtons[i].setBackground(Color.WHITE);
            radioButtons[i].setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 14));
            radioButtons[i].setBounds(103 + 388 * (i % 2), 153 + 133 * (i / 2), 322, 66);
            contentPane.add(radioButtons[i]);
            G.add(radioButtons[i]);

            // Insert line breaks after every seventh word
            String answerText = shuffledAnswers.get(i);
            String formattedAnswerText = insertLineBreaks(answerText);
            radioButtons[i].setText("<html>" + formattedAnswerText + "</html>");
        }
        int num;
        submitButton.addActionListener(e -> {
            JRadioButton selectedRadioButton = null;
            int selectedIndex = -1;

            for (int i = 0; i < radioButtons.length; i++) {
                JRadioButton radioButton = radioButtons[i];
                if (radioButton.isSelected()) {
                    selectedIndex = i; // Save the index of the selected radio button
                    selectedRadioButton = radioButton;
                    break;
                }
            }

            if (selectedRadioButton != null) {
                answered = true; // variable that check if the user has answered 
                String selectedAnswer =shuffledAnswers.get(selectedIndex) ;
                // check the selected answer if correct or wrong
                if (selectedAnswer.equals(question.getTrueAnswer())) {
                    answer = true; // the user answer correct
                    JOptionPane.showMessageDialog(contentPane, "Correct answer!");
                } else {
                    answer = false; // the user answer wrong
                    JOptionPane.showMessageDialog(contentPane, "Incorrect answer. Try again!");
                }
                listener.onQuestionAnswered(answer);
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(contentPane, "Please select an answer.");
            }
        });

        questionFrame = new JLabel("");
        questionFrame.setIcon(new ImageIcon(QuestionFrame.class.getResource("/View/img/questionFrame.jpg")));
        questionFrame.setBounds(5, 5, 830, 453);
        contentPane.add(questionFrame);
    }
    // function that Insert line breaks after every seventh word

    private String insertLineBreaks(String text) {
        String[] words = text.split("\\s+");
        StringBuilder result = new StringBuilder();
        int wordCount = 0;
        for (String word : words) {
            result.append(word).append(" ");
            wordCount++;
            if (wordCount == 6) {
                result.append("<br>");
                wordCount = 0;
            }
        }
        return result.toString().trim();
    }

    public boolean retAnswer() {
        return answer;
    }
}
