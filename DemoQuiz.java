import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DemoQuiz extends JFrame {

    private JLabel questionLabel;
    private JRadioButton[] options;
    private ButtonGroup optionGroup;
    private JButton nextButton;

    private int currentQuestion = 0;
    private int score = 0;
    private BufferedImage backgroundImage;

    private Question[] questions = {

        // Chapter 1
        new Question("Model is the ____ of the MVC architecture.",
                "Middle level", "Top most level", "Bottom most level", "None of these", 2),

        new Question("Which constructor creates a TextArea with 10 rows and 20 columns?",
                "new TextArea(20, 10)", "new TextArea(10, 20)", "new TextArea(200)", "new TextArea()", 1),

        new Question("Which class is at the top of the AWT event hierarchy?",
                "java.awt.AWTEvent", "java.awt.Event", "java.util.EventObject", "javax.swing.Object", 0),

        new Question("Default layout manager for Window is",
                "GridLayout", "CardLayout", "GridBagLayout", "BorderLayout", 3),

        // Chapter 2
        new Question("JButtons generate which type of event?",
                "ActionEvent", "ChangeEvent", "WindowEvent", "MouseEvent", 0),

        new Question("Which package contains networking classes?",
                "java.io", "java.util", "java.net", "java.network", 2),

        new Question("Action event is generated when...",
                "Label is dragged", "Button is pressed", "Mouse is dragged", "Window is opened", 1),

        // Chapter 3
        new Question("At the root of Java event hierarchy is",
                "AWTEvent", "Event", "EventObject", "Events", 2),

        // Chapter 4
        new Question("How many bits are in an IP address?",
                "8", "16", "32", "64", 2),

        // Chapter 5
        new Question("How many JDBC driver types are defined?",
                "One", "Two", "Three", "Four", 3)
    };

    public DemoQuiz() {

        setTitle("Online Quiz System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Load background image
        try {
            backgroundImage = ImageIO.read(new File("background.jpg"));
        } catch (IOException e) {
            System.out.println("Background image not found");
        }

        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridwidth = 2;

        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Georgia", Font.BOLD, 24));
        questionLabel.setForeground(Color.BLUE);

        panel.add(questionLabel, gbc);

        options = new JRadioButton[4];
        optionGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setFont(new Font("Georgia", Font.PLAIN, 20));
            options[i].setOpaque(false);
            optionGroup.add(options[i]);
            gbc.gridy++;
            gbc.gridwidth = 1;
            panel.add(options[i], gbc);
        }

        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Georgia", Font.BOLD, 20));
        nextButton.setBackground(Color.GREEN);
        nextButton.setForeground(Color.WHITE);

        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(nextButton, gbc);

        setContentPane(panel);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processAnswer();
            }
        });

        showNextQuestion();
    }

    private void showNextQuestion() {
        if (currentQuestion < questions.length) {
            Question q = questions[currentQuestion];
            questionLabel.setText(q.getQuestionText());

            for (int i = 0; i < 4; i++) {
                options[i].setText(q.getOptions()[i]);
                options[i].setSelected(false);
            }
        } else {
            questionLabel.setText("Quiz Completed!");
            for (JRadioButton option : options) {
                option.setVisible(false);
            }
            nextButton.setText("Show Result");
        }
    }

    private void processAnswer() {
        if (currentQuestion < questions.length) {
            for (int i = 0; i < 4; i++) {
                if (options[i].isSelected() &&
                        i == questions[currentQuestion].getCorrectAnswerIndex()) {
                    score++;
                }
            }
            currentQuestion++;
            showNextQuestion();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Your Score: " + score + " / " + questions.length,
                    "Quiz Result",
                    JOptionPane.INFORMATION_MESSAGE);
            resetQuiz();
        }
    }

    private void resetQuiz() {
        currentQuestion = 0;
        score = 0;
        for (JRadioButton option : options) {
            option.setVisible(true);
        }
        nextButton.setText("Next");
        showNextQuestion();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DemoQuiz().setVisible(true);
        });
    }
}

/* ================= QUESTION CLASS ================= */

class Question {

    private String questionText;
    private String[] options;
    private int correctAnswerIndex;

    public Question(String questionText,
                    String option1, String option2,
                    String option3, String option4,
                    int correctAnswerIndex) {

        this.questionText = questionText;
        this.options = new String[]{option1, option2, option3, option4};
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}
