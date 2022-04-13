import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Student extends Courses
{
    private int id;
    private String fullname;
    private String mobile;
    private String address;
    private String department;
    private int stage;
    
    public Student(){
        autoIdProvider();
    }
    public Student(String fullname, String mobile, String address, String department, int stage){
        autoIdProvider();
        setMobile(mobile);
        setStage(stage);
        setDepartment(department);
        setAddress(address);
        setFullname(fullname);
    }
    private void autoIdProvider()
    {
        try {
            this.id = Integer.parseInt(Importer.reader("IDs.txt"));
            Importer.clearFile("IDs.txt");
            Importer.importData("IDs.txt", Integer.toString(++this.id));
        } catch (Exception e) {
            Importer.importData("IDs.txt", "0");
        }
        
    }
    public void idCorrecter(int newID){
        id = newID;
    }
    public int getID(){
        return id;
    }
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public int getStage() {
        return stage;
    }
    public void setStage(int stage) {
        this.stage = stage;
    }

    public static void pushData(String path, ArrayList<Student> students){
        Importer.clearFile(path);
        for (int i = 0; i < students.size(); i++) {
            if(i != students.size()-1)
                Importer.importData(path, students.get(i).toString() + "\n");
            else
                Importer.importData(path, students.get(i).toString());
        }
    }

    public static void loadData(ArrayList<Student> students)
    {
        try {
            String allData = Importer.reader("file.txt");
            String courseData = Importer.reader("course.txt");
            String[] splitUpByLines = allData.split("[, \n]");
            String[] course_split = courseData.split("[, \n]");

            int j = 0;
            int t = 0;
            // Loading all data
            for (int i = 0; i < splitUpByLines.length/6; i++)
            {
                Student student = new Student();
                ++j;
                // Set studenet data.
                student.setFullname(splitUpByLines[j++]);
                student.setAddress(splitUpByLines[j++]);
                student.setMobile(splitUpByLines[j++]);
                student.setDepartment(splitUpByLines[j++]);
                student.setStage(Integer.parseInt(splitUpByLines[j++]));

                // Set Course data.
                student.setCalculus(Float.parseFloat(course_split[t++]));
                student.setOpp(Float.parseFloat(course_split[t++]));
                student.setGis(Float.parseFloat(course_split[t++]));

                // push all data to array.
                students.add(student);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cannot loading data because files are empty!", "Loading failed!", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public String toString(){
        return getID() + "," + getFullname() + "," + getAddress() + "," + getMobile() +"," +getDepartment() + "," + getStage();
    }
    public String htmlFormatted(){
        return getID() + "&nbsp;" + getFullname() + "&nbsp;" + getAddress() + "&nbsp;" + getMobile() +"&nbsp;" +getDepartment() + "&nbsp;" + getStage();
    }
}
