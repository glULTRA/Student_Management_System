abstract class Courses 
{
    private float[] lectures = new float[3];
    public static String course_title = 
        "<th>" +
            "Calculus"+
        "</th>"+
        "<th>"+
            "OOP"+
        "</th>"+
        "<th>"+
            "GIS"+
        "</th>"+
    "</tr>";
    public void setCourse(float[] lectures){

    }
    public void setMarks(float calculus, float oop, float gis){
        setCalculus(calculus);
        setOop(oop);
        setGis(gis);
    }
    public void setCalculus(float calculus){
        lectures[0] = calculus;
    }
    public float getCalculus(){
        return lectures[0];
    }
    public void setOop(float oop){
        lectures[1] = oop;
    }
    public float getOop(){
        return lectures[1];
    }
    public void setGis(float gis){
        lectures[2] = gis;
    }
    public float getGis(){
        return lectures[2];
    }
    public String getCourses(){
        return getCalculus() + "," + getOop() + "," + getGis();
    }
    public String getCoursesFormatted(){
        return "Calculus_II :" + getCalculus() + "\nOOP :" + getOop() + "\nGIS :" + getGis();
    }
    public String getCoursesHtmlFormatted(){
        return 
            "<td>"+
                getCalculus()+
            "</td>"+
            "<td>"+
                getOop()+
            "</td>"+
            "<td>"+
                getGis()+
            "</td>"+
        "</tr>";
    }
}
