import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;

import javax.swing.JOptionPane;

public class Importer 
{
    public static void importData(String path, String data)
    {
        FileOutputStream ofile = null;
        try {
            ofile = new FileOutputStream(path, true);
            byte[] myBytes = data.getBytes(); 
            ofile.write(myBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                ofile.flush();
                ofile.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void clearFile(String path){
        FileOutputStream ofile = null;
        try {
            ofile = new FileOutputStream(path, false);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                ofile.flush();
                ofile.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isEmpty(String path)
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            if (reader.readLine() == null) {
                return true;
            }
            else{
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "File is empty!");
            Importer.importData(path, null);
            return true;
        }
    }
    
    public static String reader(String path){
        String data = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            int i;
            while((i = reader.read()) != -1){
                data += (char)i;
            }
            return data;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "File is empty!");
            return null;
        }
    }
}
