import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Quizapplication extends JFrame implements ActionListener {
    private JLabel questionLabel;
    private JRadioButton[] options;
    private ButtonGroup group;
    private JButton nextButton;
    private JLabel timerLabel;
    private Timer timer;
    private int timeLeft;
    private int currentQuestion = 0;
    private int score = 0;
    private Map<String, String[]> questions;
    private Map<String, String> correctAnswers;
    private Map<Integer, String> userAnswers;

    public Quizapplication() {
        // Set up the JFrame
        setTitle("Quiz Application");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize quiz questions, options, and answers
        questions = new HashMap<>();
        correctAnswers = new HashMap<>();
        userAnswers = new HashMap<>();

        questions.put("What is the capital of France?", new String[]{"Paris", "London", "Berlin", "Madrid"});
        correctAnswers.put("What is the capital of France?", "Paris");

        questions.put("Which is the largest planet in our solar system?", new String[]{"Earth", "Mars", "Jupiter", "Saturn"});
        correctAnswers.put("Which is the largest planet in our solar system?", "Jupiter");

        questions.put("What is the boiling point of water?", new String[]{"90°C", "100°C", "80°C", "70°C"});
        correctAnswers.put("What is the boiling point of water?", "100°C");

        questions.put("Who wrote 'Hamlet'?", new String[]{"Charles Dickens", "William Shakespeare", "Mark Twain", "George Orwell"});
        correctAnswers.put("Who wrote 'Hamlet'?", "William Shakespeare");

        questions.put("What is the square root of 64?", new String[]{"6", "7", "8", "9"});
        correctAnswers.put("What is the square root of 64?", "8");

        // Set up GUI components
        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        options = new JRadioButton[4];
        group = new ButtonGroup();
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            group.add(options[i]);
            optionsPanel.add(options[i]);
        }

        nextButton = new JButton("Next");
        nextButton.addActionListener(this);

        timerLabel = new JLabel("Time left: 10 seconds", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // Add components to JFrame
        add(questionLabel, BorderLayout.NORTH);
        add(optionsPanel, BorderLayout.CENTER);
        add(timerLabel, BorderLayout.SOUTH);
        add(nextButton, BorderLayout.EAST);

        displayQuestion();

        setVisible(true);
    }

    private void displayQuestion() {
        if (currentQuestion < questions.size()) {
            // Set the question and options
            String question = (String) questions.keySet().toArray()[currentQuestion];
            questionLabel.setText("Q" + (currentQuestion + 1) + ": " + question);

            String[] optionsText = questions.get(question);
            for (int i = 0; i < 4; i++) {
                options[i].setText(optionsText[i]);
                options[i].setSelected(false);
            }

            // Start or reset the timer
            timeLeft = 10;
            if (timer != null) {
                timer.stop();
            }
            timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    timeLeft--;
                    timerLabel.setText("Time left: " + timeLeft + " seconds");
                    if (timeLeft <= 0) {
                        timer.stop();
                        evaluateAnswer();
                    }
                }
            });
            timer.start();
        } else {
            displayResults();
        }
    }

    private void evaluateAnswer() {
        String selectedOption = null;
        for (JRadioButton option : options) {
            if (option.isSelected()) {
                selectedOption = option.getText();
                break;
            }
        }

        // Store user's answer
        userAnswers.put(currentQuestion, selectedOption);

        // Check if the answer is correct
        String question = (String) questions.keySet().toArray()[currentQuestion];
        if (selectedOption != null && selectedOption.equals(correctAnswers.get(question))) {
            score++;
        }

        currentQuestion++;
        displayQuestion();
    }

    private void displayResults() {
        // Show the results screen
        StringBuilder resultMessage = new StringBuilder("<html><h2>Your Score: " + score + "/" + questions.size() + "</h2><br>");
        for (int i = 0; i < questions.size(); i++) {
            String question = (String) questions.keySet().toArray()[i];
            String userAnswer = userAnswers.get(i);
            String correctAnswer = correctAnswers.get(question);
            resultMessage.append("Q").append(i + 1).append(": ").append(question).append("<br>");
            resultMessage.append("Your Answer: ").append(userAnswer == null ? "No Answer" : userAnswer).append("<br>");
            resultMessage.append("Correct Answer: ").append(correctAnswer).append("<br><br>");
        }
        resultMessage.append("</html>");

        JOptionPane.showMessageDialog(this, resultMessage.toString(), "Quiz Results", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        evaluateAnswer();
    }

    public static void main(String[] args) {
        new Quizapplication();
    }
}