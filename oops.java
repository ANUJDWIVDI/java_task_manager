import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Task {
    String description;
    boolean completed;

    Task(String description) {
        this.description = description;
        this.completed = false;
    }
}

class TaskManager {
    private ArrayList<Task> tasks;

    TaskManager() {
        this.tasks = new ArrayList<>();
    }

    void addTask(String description) {
        Task task = new Task(description);
        tasks.add(task);
        System.out.println("Task added: " + description);
    }

    ArrayList<Task> getTasks() {
        return tasks;
    }

    void markTaskAsComplete(int index) {
        if (index >= 1 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            task.completed = true;
            System.out.println("Task marked as complete: " + task.description);
        } else {
            System.out.println("Invalid task index.");
        }
    }
}

public class oops {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        // Set look and feel to Nimbus for a modern appearance
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // Create and set up the main frame
        JFrame frame = new JFrame("Task Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Make it full screen
        frame.setLayout(new BorderLayout());

        // Set font to Times New Roman
        Font timesNewRomanFont = new Font("Times New Roman", Font.PLAIN, 16);

        // Create components
        JTextArea taskTextArea = new JTextArea();
        taskTextArea.setEditable(false);
        taskTextArea.setFont(timesNewRomanFont);

        JTextField inputField = new JTextField();
        inputField.setFont(timesNewRomanFont);

        JButton addButton = new JButton("Add Task");
        addButton.setFont(timesNewRomanFont);

        JButton listButton = new JButton("List Tasks");
        listButton.setFont(timesNewRomanFont);

        JButton markCompleteButton = new JButton("Mark as Complete");
        markCompleteButton.setFont(timesNewRomanFont);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(timesNewRomanFont);

        // Create a panel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.BLACK);

        // Project information text box
        JTextArea projectInfo = new JTextArea(
            "## TaskSwing - Task Manager Application 🚀\n" +
            "\n" +
            "**Overview**\n" +
            "\n" +
            "TaskSwing is a dynamic Java Swing-based task manager application designed to simplify task management through an intuitive graphical user interface. Users can efficiently organize and track their tasks, enhancing productivity and workflow management.\n" +
            "\n" +
            "### Key Features\n" +
            "\n" +
            "- **Add Task 📝:** Seamlessly add new tasks with a user-friendly interface.\n" +
            "- **List Tasks 📋:** View the task list with descriptions and status indicators.\n" +
            "- **Mark as Complete ✔️:** Easily mark tasks as complete by entering the task index.\n" +
            "- **Project Information ℹ️:** A dedicated section provides insights into the project.\n" +
            "- **Team Information 👥:** Display team members' names who contributed to TaskSwing.\n" +
            "- **Exit Button 🚪:** Gracefully exit the application for a smooth user experience.\n"
        );
        projectInfo.setEditable(false);
        projectInfo.setLineWrap(true);
        projectInfo.setWrapStyleWord(true);
        projectInfo.setFont(timesNewRomanFont);
        projectInfo.setBackground(Color.BLACK);
        projectInfo.setForeground(Color.GREEN);
        JScrollPane projectInfoScrollPane = new JScrollPane(projectInfo);
        panel.add(projectInfoScrollPane, BorderLayout.NORTH);

        // Create a panel for task-related components
        JPanel taskPanel = new JPanel();
        taskPanel.setLayout(new GridLayout(3, 1));
        taskPanel.setBackground(Color.BLACK);

        // Task Description components
        JPanel taskDescriptionPanel = new JPanel();
        taskDescriptionPanel.setLayout(new GridLayout(1, 2));
        taskDescriptionPanel.add(new JLabel("Task Description:"));
        taskDescriptionPanel.add(inputField);
        taskPanel.add(taskDescriptionPanel);

        // Button components
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));
        buttonPanel.add(addButton);
        buttonPanel.add(listButton);
        buttonPanel.add(markCompleteButton);
        buttonPanel.add(exitButton);
        taskPanel.add(buttonPanel);

        panel.add(taskPanel, BorderLayout.WEST);

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String description = inputField.getText();
                taskManager.addTask(description);
                inputField.setText("");
                // Automatically list tasks after adding a task
                listButton.doClick();
            }
        });

        listButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taskTextArea.setText("");  // Clear previous content
                for (int i = 0; i < taskManager.getTasks().size(); i++) {
                    Task task = taskManager.getTasks().get(i);
                    taskTextArea.append((i + 1) + ". " + task.description + " - " +
                            (task.completed ? "Completed" : "Incomplete") + "\n");
                }
            }
        });

        markCompleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int index = Integer.parseInt(JOptionPane.showInputDialog("Enter the index of the task to mark as complete:"));
                    taskManager.markTaskAsComplete(index);
                    // Automatically list tasks after marking as complete
                    listButton.doClick();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid index.");
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exiting the program. Goodbye!");
                System.exit(0);
            }
        });

        // Create a panel for displaying tasks
        JScrollPane taskDisplayScrollPane = new JScrollPane(taskTextArea);
        panel.add(taskDisplayScrollPane, BorderLayout.CENTER);

        // Add components to the frame
        frame.getContentPane().add(panel);
        frame.getContentPane().add(BorderLayout.NORTH, createTeamPanel());
        frame.setVisible(true);
    }

    private static JPanel createTeamPanel() {
        JPanel teamPanel = new JPanel();
        teamPanel.setLayout(new GridLayout(1, 2));
        teamPanel.setBackground(Color.BLACK);

        JLabel teamLabel = new JLabel("Team Members:");
        teamLabel.setForeground(Color.GREEN);
        teamPanel.add(teamLabel);

        JButton teamLinkButton = new JButton("<html><a href='https://example.com'>John, Jane, Alex</a></html>");
        teamLinkButton.setBorderPainted(false);
        teamLinkButton.setOpaque(false);
        teamLinkButton.setBackground(Color.BLACK);
        teamLinkButton.setForeground(Color.GREEN);
        teamPanel.add(teamLinkButton);

        return teamPanel;
    }
}
