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
    
    static JFrame window = new JFrame("Student Management System");

    static ArrayList<Student> students = new ArrayList<Student>();
    static ArrayList<JButton> editBtns   = new ArrayList<JButton>();
    static ArrayList<JButton> deleteBtns = new ArrayList<JButton>();
    static ArrayList<JButton> detailBtns = new ArrayList<JButton>();

    public static void main(String[] args) {
        // List of students
        Student.loadData(students);
        
        // Creating window
        window.setLayout(null);
        window.setSize(SCR_WIDTH,SCR_HEIGHT);
        window.setBounds(400,160, window.getWidth(), window.getHeight());
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
        button2.addActionListener(new Program());
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
                    if(!Importer.isEmpty("file.txt")){
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

                                        
                                        if(JOptionPane.showConfirmDialog(newWindow, "Do u really want to delete item " + (id+1) + " ?", "Deletting!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                                        {
                                            students.remove(id);
                                            Importer.clearFile("file.txt");
                                            for (Student student2 : students) {
                                                Importer.importData("file.txt", student2.toString() + "\n");
                                            }
                                            editBtns.clear();
                                            deleteBtns.clear();
                                            detailBtns.clear();
                                            newWindow.dispose();
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

                            editBtns.get(j).addActionListener(
                                new ActionListener(){
                                    public void actionPerformed(ActionEvent e){
                                        if(JOptionPane.showConfirmDialog(newWindow, "<----------------Student data---------->\n" + students.get(id) + "\nDo u want to edit its data ?", "Edit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                                        {
                                            Student editedStudent = new Student();
                                            
                                            String option;
                                            for (int i = 0; i < 1; i++) {
                                                option = JOptionPane.showInputDialog(newWindow, "Current Fullname :" + students.get(id).getFullname() + "\nNew Fullname :","Edit", JOptionPane.OK_CANCEL_OPTION);
                                                editedStudent.setFullname((option != null)  ? option : students.get(id).getFullname());
                                                try {
                                                    if(option.equals("")){
                                                        --i;
                                                        JOptionPane.showMessageDialog(newWindow, "Enter some data !", "Empty !", JOptionPane.ERROR_MESSAGE);
                                                    }
                                                } catch (Exception e2) {
                                                    
                                                }
                                            }
                                            
                                            for (int i = 0; i < 1; i++) {
                                                option = JOptionPane.showInputDialog(newWindow, "Current Address :" + students.get(id).getAddress() + "\nNew Address :","Edit", JOptionPane.OK_CANCEL_OPTION);
                                                editedStudent.setAddress((option != null)  ? option : students.get(id).getAddress());
                                                try {
                                                    if(option.equals("")){
                                                        --i;
                                                        JOptionPane.showMessageDialog(newWindow, "Enter some data !", "Empty !", JOptionPane.ERROR_MESSAGE);
                                                    }
                                                } catch (Exception e2) {
                                                }
                                            }
                                            
                                            for (int i = 0; i < 1; i++) {
                                                option = JOptionPane.showInputDialog(newWindow, "Current Mobile Number :" + students.get(id).getMobile() + "\nNew Mobile Number :","Edit", JOptionPane.OK_CANCEL_OPTION);
                                                editedStudent.setMobile((option != null)  ? option : students.get(id).getMobile());
                                                try {
                                                    if(option.equals("")){
                                                        --i;
                                                        JOptionPane.showMessageDialog(newWindow, "Enter some data !", "Empty !", JOptionPane.ERROR_MESSAGE);
                                                    }
                                                } catch (Exception e2) {
                                                }
                                            }


                                            for (int i = 0; i < 1; i++) {
                                                option = JOptionPane.showInputDialog(newWindow, "Current Department :" + students.get(id).getDepartment() + "\nNew Department :","Edit", JOptionPane.OK_CANCEL_OPTION);
                                                editedStudent.setDepartment((option != null)  ? option : students.get(id).getDepartment());
                                                try {
                                                    if(option.equals("")){
                                                        --i;
                                                        JOptionPane.showMessageDialog(newWindow, "Enter some data !", "Empty !", JOptionPane.ERROR_MESSAGE);
                                                    }
                                                } 
                                                catch (Exception e2) {
                                                }
                                            }
                                            
                                            for (int i = 0;i < 1; i++) {
                                                int opt;
                                                String optStr;
                                                optStr = JOptionPane.showInputDialog(newWindow, "Current Stage :" + students.get(id).getStage() + "\nNew Stage :", "Edit", JOptionPane.OK_CANCEL_OPTION);
                                                if(optStr == null){
                                                    break;
                                                }
                                                try {
                                                    opt = Integer.parseInt(optStr);
                                                    editedStudent.setStage((opt != 0)  ? opt : students.get(id).getStage());
                                                } 
                                                catch (Exception e2) {
                                                    --i;
                                                    JOptionPane.showMessageDialog(newWindow, "Please enter number only!", "Error", JOptionPane.ERROR_MESSAGE);
                                                }
                                            }

                                            students.set(id, editedStudent);
                                            Student.pushData("file.txt", students);
                                            newWindow.dispose();
                                        }
                                    }   
                                }
                            );
                        }

                        newWindow.add(text, BorderLayout.CENTER);
                        newWindow.setLayout(new GridBagLayout());
                        newWindow.add(btnList);
                        editBtns.clear();
                        deleteBtns.clear();
                        detailBtns.clear();
                        newWindow.setSize(600,400);
                        newWindow.setBounds(400,160, newWindow.getWidth(), newWindow.getHeight());
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
        if (Importer.isEmpty("file.txt")) {
            Importer.importData("file.txt", student.toString());
        }
        else{
            Importer.importData("file.txt", "\n" + student.toString());
        }
    }
}
