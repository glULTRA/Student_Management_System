import java.awt.Color;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Program implements ActionListener 
{
    private static final int SCR_WIDTH = 612;
    private static final int  SCR_HEIGHT = 612;

    public static void main(String[] args) {
        // List of students
        ArrayList<Student> students = new ArrayList<Student>();
        String allData = Importer.reader("file.txt");
        String[] splitUpByLines = allData.split("[, \n]");
        int j = 0;
        for (int i = 0; i < splitUpByLines.length/6; i++) {
            Student student = new Student();
            ++j;
            student.setFullname(splitUpByLines[j++]);
            student.setMobile(splitUpByLines[j++]);
            student.setAddress(splitUpByLines[j++]);
            student.setDepartment(splitUpByLines[j++]);
            student.setStage(Integer.parseInt(splitUpByLines[j++]));
            students.add(student);
        }
        

        // Creating window
        JFrame window = new JFrame("Student Management System");
        window.setLayout(null);
        window.setSize(SCR_WIDTH,SCR_HEIGHT);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Design Window Attributes
        window.getContentPane().setBackground(Color.darkGray);
        window.getContentPane().setForeground(Color.WHITE);

        // Adding some Attributes
        // Navigation bar
        // commponents
        JButton search = new JButton("Search");
        search.setBackground(Color.GREEN);
        search.setBounds(window.getWidth() - 100, 10, 80,50);
        window.add(search);

        JTextField search_bar = new JTextField("search");
        search_bar.setBounds(window.getWidth() - 400, 10, 290,50);
        search_bar.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        search_bar.setAlignmentY(JTextField.CENTER_ALIGNMENT);
        //search_bar.addActionListener(new Program());
        window.add(search_bar);

        // Stroing student Informations
        JButton button2 = new JButton("Store new Informations");
        button2.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(window, "Hey Welcome To student management system!", "Data Entering", JOptionPane.PLAIN_MESSAGE);
                    Student student = new Student();
                    student.setFullname(JOptionPane.showInputDialog(window, "Enter fullname :"));
                    student.setAddress(JOptionPane.showInputDialog(window, "Enter Address :"));
                    student.setMobile(JOptionPane.showInputDialog(window, "Enter Mobile :"));
                    student.setDepartment(JOptionPane.showInputDialog(window, "Enter Department :"));
                    for (int i = 0; i < 1; i++) {
                        try {
                            student.setStage(Integer.parseInt(JOptionPane.showInputDialog(window, "Enter Stage :")));
                        } catch (Exception e2) {
                            --i;
                            JOptionPane.showMessageDialog(window, "Please enter number !", "Stage Error !", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    students.add(student);
                    Importer.importData("file.txt", "\n" + student.toString());
                }
            }
        );
        button2.setBounds(window.getWidth()/2 - (180/2), window.getHeight()/2 -(30/2), 180, 30);
        button2.setBackground(Color.white);
        window.add(button2);

        JButton button3 = new JButton("Show Informations");
        button3.setBounds(window.getWidth()/2 - (180/2), window.getHeight()/2 + 20, 180, 30);
        button3.setBackground(Color.WHITE);

        window.add(button3);
        button3.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e) {
                    String data = "";
                    for (Student student: students) {
                        data += student.toString() + "\n";
                    }
                    if(!data.equals(""))
                        JOptionPane.showMessageDialog(window, data, "List of student", JOptionPane.PLAIN_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(window, "List is empty!", "No Lists", JOptionPane.WARNING_MESSAGE);
                }
            }
        );
        window.setVisible(true);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Importer.clearFile("IDs.txt");
                Importer.importData("IDs.txt", "0");
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}
