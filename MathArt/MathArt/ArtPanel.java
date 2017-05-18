import java.awt.*;  //for Graphics
import javax.swing.*;  //for JComponent, JFrame

public class ArtPanel extends JComponent
{
  public static void main(String[] args)
  {
    new ArtPanel(true,true,true);
  }
  private boolean red,green,blue;
  public ArtPanel(boolean r, boolean g, boolean b)
  {
    red = r;
    green = g;
    blue = b;
    JFrame frame = new JFrame();
    setPreferredSize(new Dimension(600, 400));
    frame.getContentPane().add(this);
    //frame.pack();
    //frame.setVisible(true);
  }
  
  public void getColors(boolean r, boolean g, boolean b){
    red = r;
    green = g;
    blue = b;
    
  }
  
  public void paintComponent(Graphics g)
  {
    int redCV = 0;
    int greenCV = 0;
    int blueCV = 0;
    for(int i = 0; i <= getWidth(); i++)
    {
      for(int j = 0; j <= getHeight(); j++)
      {
        //i and j between 0 and dim
        //need to convert to decimal between -1 and 1
        //num/dim -> dec. between 0 and 1
        //(num/dim)*2 -> dec. between 0 and 2
        //(num/dim)*2 - 1 -> dec. between -1 and 1
        double scaledx = (1.0*i/getWidth())*2-1;
        double scaledy = (1.0*j/getHeight())*2-1;
        double functionvalue = RandomFunction.evaluate(scaledx,scaledy);
        //functionvalue will be decimal between -1 and 1
        //need to convert to int between 0 and 255
        //fv*128 -> dec. between -128 and 128
        //(fv*128)+128 -> dec. between 0 and 256
        //casting will round down to int from 0 to 255
        int colorvalue = (int) ((functionvalue*127.5)+127.5);
        //System.out.println("i:"+i+" j:"+j+" scaledx:"+scaledx+" scaledy:"+scaledy+" fv:"+functionvalue+" cv:"+colorvalue);
        if(red == true){
          redCV = colorvalue;
        }
        if(green == true){
          greenCV = colorvalue;
        }
        if(blue == true){
          blueCV = colorvalue;
        }
        g.setColor(new Color(redCV,greenCV,blueCV));
        g.fillRect(i, j, 1, 1);
        
      }
    }
    
    
//    g.setColor(Color.WHITE);
//    g.fillRect(0, 0, getWidth(), getHeight());
//    g.setColor(Color.BLUE);
//    g.drawOval(100, 100, 200, 200);
  }
}