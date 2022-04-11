import java.awt.Color;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

public class Program implements ActionListener 
{
    private static final int SCR_WIDTH = 612;
    private static final int  SCR_HEIGHT = 612;

    static ArrayList<JButton> editBtns   = new ArrayList<JButton>();
    static ArrayList<JButton> deleteBtns = new ArrayList<JButton>();
    static ArrayList<JButton> detailBtns = new ArrayList<JButton>();

    public static void main(String[] args) {
        // List of students
        ArrayList<Student> students = new ArrayList<Student>();
        Student.loadData(students);

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
        JButton button2 = new JButton("Register new student");
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

        // List all students.
        JButton button3 = new JButton("Show Informations");
        button3.setBounds(window.getWidth()/2 - (180/2), window.getHeight()/2 + 20, 180, 30);
        button3.setBackground(Color.WHITE);

        window.add(button3);
        
        button3.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e) 
                {
                    String data = "<html>";
                    data += "<h4 bgcolor=\"#0275d8\" style=\"color:white;\" >" + "ID\tFullname\tAddress\tMobile\tDepartment\tStage  <hr>" + "</h4>";
                    for (Student student: students) 
                    {
                        data += "<p style=\"background-color:#f0ad4e;\" >";
                        data += student.htmlFormatted() + "<hr>";
                        editBtns.add(new JButton("Edit"));
                        deleteBtns.add(new JButton("Delete"));
                        detailBtns.add(new JButton("Detail"));
                        data += "</p>";
                    }
                    
                    data += "</html>";
                    if(!data.equals("")){
                        //JOptionPane.showMessageDialog(window, data, "List of student", JOptionPane.PLAIN_MESSAGE);
                        JFrame newWindow = new JFrame();
                        newWindow.setLayout(new BorderLayout());
                        JLabel text = new JLabel("Student Lists");
                        text.setText(data);
                        JPanel btnList = new JPanel();
                        GridBagConstraints cg = new GridBagConstraints();                        
                        btnList.setLayout(new GridBagLayout());

                        
                        for (int i = 0; i < editBtns.size(); i++)
                        {
                            cg.gridy++;
                            if(i == 0) {
                                btnList.add(new JLabel("<html><p style=\"background-color:#5bc0de;\">Edits</p><hr></html>"),cg);
                                btnList.add(new JLabel("<html><p style=\"background-color:#5bc0de;\">Deletes</p><hr></html>"),cg);
                                btnList.add(new JLabel("<html><p style=\"background-color:#5bc0de;\">Details</p><hr></html>"),cg);
                                cg.gridy++;
                            };
                            editBtns.get(i).setBackground(new Color(92, 184, 92));
                            deleteBtns.get(i).setBackground(new Color(217, 83, 79));
                            detailBtns.get(i).setBackground(new Color(91, 192, 222));

                            

                            btnList.add(editBtns.get(i),cg);
                            btnList.add(deleteBtns.get(i),cg);
                            btnList.add(detailBtns.get(i),cg);
                        }
                        
                        for(int j = 0; j < deleteBtns.size(); j++)
                        {
                            int id = j;
                            deleteBtns.get(j).addActionListener
                            (
                                new ActionListener()
                                {
                                    public void actionPerformed(ActionEvent e) 
                                    {
                                        int makeSure = JOptionPane.showConfirmDialog(newWindow, "Do u really want to delete item " + (id+1) + " ?", "Deletting!", JOptionPane.YES_NO_OPTION);
                                        if(makeSure == 0)
                                        {
                                            students.remove(id);
                                            Importer.clearFile("file.txt");
                                            for (Student student2 : students) {
                                                Importer.importData("file.txt", student2.toString() + "\n");
                                            }
                                        }
                                    }
                                }
                            );
                            detailBtns.get(j).addActionListener
                            (
                                new ActionListener()
                                {
                                    public void actionPerformed(ActionEvent e) 
                                    {
                                        JOptionPane.showMessageDialog(newWindow,students.get(id));
                                    }
                                }
                                
                            );
                        }

                        newWindow.add(text, BorderLayout.CENTER);
                        newWindow.setLayout(new GridBagLayout());
                        newWindow.add(btnList);
                        editBtns.clear();
                        deleteBtns.clear();
                        newWindow.setSize(600,400);
                        newWindow.setVisible(true);
                    }else{
                       JOptionPane.showMessageDialog(window, "List is empty!", "No Lists", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        );

        

        // List students in each department.
        JButton button4 = new JButton("List students in each department");
        button4.setBounds(window.getWidth()/2 - (220/2), window.getHeight()/2 + 55, 220, 30);
        button4.setBackground(Color.WHITE);

        window.add(button4);
        button4.addActionListener
        (
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
        for (int j = 0; j < deleteBtns.size(); j++) {
            if(deleteBtns.get(j).isSelected()){
                deleteBtns.remove(j);
                JOptionPane.showMessageDialog(null, j);
            }
        }
    }
}
