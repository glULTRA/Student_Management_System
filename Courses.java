import java.util.ArrayList;

abstract class Courses 
{
    private float[] lectures = new float[3];
    
    public void setCourse(float[] lectures){

    }
    public void setMarks(float calculus, float oop, float gis){
        setCalculus(calculus);
        setOpp(oop);
        setGis(gis);
    }
    public void setCalculus(float calculus){
        lectures[0] = calculus;
    }
    public float getCalculus(){
        return lectures[0];
    }
    public void setOpp(float oop){
        lectures[1] = oop;
    }
    public float getOpp(){
        return lectures[1];
    }
    public void setGis(float gis){
        lectures[2] = gis;
    }
    public float getGis(){
        return lectures[2];
    }
    public String getCourses(){
        return "Calculus_II :" + getCalculus() + "\nOOP :" + getOpp() + "\nGIS :" + getGis();
    }
}
