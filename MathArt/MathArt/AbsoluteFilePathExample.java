import java.io.File;
import java.io.IOException;

public class AbsoluteFilePathExample
{
    public static void main(String[] args)
    {
     try{

         File temp = File.createTempFile("tempfile", ".tmp" );

         String absolutePath = temp.getAbsolutePath();
         System.out.println("File path : " + absolutePath);

         String filePath = absolutePath.
               substring(0,absolutePath.lastIndexOf(File.separator));
         filePath = filePath.replace(File.separator, "/");
         System.out.println("File path : " + filePath);

     }catch(IOException e){

         e.printStackTrace();

     }

    }
}