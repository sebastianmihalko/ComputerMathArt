import java.awt.event.*;  //for ActionListener, ActionEvent
import javax.swing.*;  //for JFrame, BoxLayout, JLabel, JTextField, JButton
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.awt.Image;
import javax.imageio.*;
import javax.swing.border.*;

public class MathArt extends JFrame implements ActionListener{
  private  JFrame frame;
  private  JPanel artBox, utilBox;
  
  //JPanels being used for utilBox
  private  JPanel colorBox, equationsInUse, userStuff, generate, save;
  //JLabels for user to know specifications of art
  private JLabel color, equations, iter, sav;
  private ArtPanel canvas;
  private RandomFunction function;
  private JButton inc,dec;
  private int comp;
  
  public static void main(String[] args){
    new MathArt(); 
  }
  
  public MathArt(){
    frame = new JFrame();
    frame.setSize(1000,500);
    frame.setVisible(true);
    frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
    frame.setTitle("MathArt");
    frame.setIconImage(Toolkit.getDefaultToolkit().getImage("MathArt.jpg"));
    frame.setBackground(Color.GRAY);
    artBox = new JPanel();
    frame.add(artBox);
    function = new RandomFunction(true,true,true,true,true,true,true,1);
    canvas = new ArtPanel(final_red,final_green,final_blue);
    artBox.add(canvas);
    comp = 0;
    utilBox = new JPanel();
    frame.add(utilBox);
    utilBoxMaker();
  }
  
  private JButton geneButton, saveButton;
  private JLabel comptext;
  
  public void utilBoxMaker(){
    utilBox.setLayout(new BoxLayout(utilBox, BoxLayout.X_AXIS));
    
    colorBox = new JPanel();
    colorBox.setBorder(new LineBorder(Color.GRAY));
    colorBox.setLayout(new BoxLayout(colorBox, BoxLayout.PAGE_AXIS));
    colorBox.setSize(1,1);
    utilBox.add(colorBox);
    color = new JLabel();
    color.setText("Color");
    colorBox.add(color);
    addColorBoxes();
    
    equationsInUse = new JPanel();
    equationsInUse.setBorder(new LineBorder(Color.GRAY));
    utilBox.add(equationsInUse);
    equationsInUse.setLayout(new BoxLayout(equationsInUse, BoxLayout.Y_AXIS));
    //equationsInUse.setAlignmentX(Component.LEFT_ALIGNMENT);
    equations = new JLabel();
    equations.setText("Equations");
    equationsInUse.add(equations);
    addEquations();
    
    
    
    userStuff = new JPanel();
    userStuff.setBorder(new LineBorder(Color.GRAY));
    userStuff.setLayout(new BoxLayout(userStuff, BoxLayout.Y_AXIS));
    utilBox.add(userStuff);
    JPanel sup = new JPanel();
    inc = new JButton("+1");
    sup.add(inc);
    inc.addActionListener(this);
    dec = new JButton("-1");
    sup.add(dec);
    dec.addActionListener(this);
    userStuff.add(sup);
    JPanel sub = new JPanel();
    comptext = new JLabel("Complexity:   "+comp+"      ");
    sub.add(comptext);
    userStuff.add(sub);
    
    
    generate = new JPanel();
    utilBox.add(generate);
    generate.setLayout(new BoxLayout(generate, BoxLayout.Y_AXIS));
    generate.setBackground(Color.GRAY);
    geneButton = new JButton("Generate");
    generate.add(geneButton);
    geneButton.addActionListener(this);
    save = new JPanel();
    utilBox.add(save);
    save.setLayout(new BoxLayout(save, BoxLayout.Y_AXIS));
    sav = new JLabel();
    sav.setText("Click Here to Save Image:");
    save.add(sav);
    saveButton = new JButton("Save");
    save.add(saveButton);
    saveButton.addActionListener(this);
    frame.pack();
  }
  
  private  JCheckBox red, green, blue;
  
  public  void addColorBoxes(){
    red = new JCheckBox("Red");
    green = new JCheckBox("Green");
    blue = new JCheckBox("Blue");
    colorBox.add(red);
    colorBox.add(green);
    colorBox.add(blue);
  }
  
  private  JPanel leftEquation, rightEquation, allEquations;
  private  JCheckBox sine, cosine, average, product;
  
  public  void addEquations(){
    allEquations = new JPanel();
    equationsInUse.add(allEquations);
    allEquations.setLayout(new BoxLayout(allEquations, BoxLayout.X_AXIS));
    allEquations.setAlignmentX(Component.LEFT_ALIGNMENT);
    sine = new JCheckBox("Sine");
    cosine = new JCheckBox("Cosine");
    average = new JCheckBox("Average");
    product = new JCheckBox("Product");
    leftEquation = new JPanel();
    leftEquation.setLayout(new BoxLayout(leftEquation, BoxLayout.Y_AXIS));
    leftEquation.setAlignmentX(Component.LEFT_ALIGNMENT);
    allEquations.add(leftEquation);
    rightEquation = new JPanel();
    rightEquation.setLayout(new BoxLayout(rightEquation, BoxLayout.Y_AXIS));
    rightEquation.setAlignmentX(Component.LEFT_ALIGNMENT);
    allEquations.add(rightEquation);
    leftEquation.add(sine);
    rightEquation.add(cosine);
    leftEquation.add(average);
    rightEquation.add(product);
  }
  
  
  
  
  public void actionPerformed(ActionEvent e){
    if(e.getSource() == geneButton){
      gatherInfo();
    }
    if(e.getSource().equals(inc)){
      comp++;
      comptext.setText("Complexity:   "+comp+"      ");
    }
    if(e.getSource().equals(dec)){
      comp--;
      comptext.setText("Complexity:   "+comp+"      ");
    }
    if(e.getSource().equals(saveButton)){
      BufferedImage image = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
      canvas.paintAll(image.getGraphics());
      try{
        String hash = Integer.toHexString(function.hashCode());
        
        File temp = new File(".png" );

         String absolutePath = temp.getAbsolutePath();
         System.out.println("File path : " + absolutePath);

         String filePath = absolutePath.
               substring(0,absolutePath.lastIndexOf(File.separator));
         filePath = filePath.replace(File.separator, "/");
         System.out.println("File path : " + filePath);
         
        String filename = hash+".png";
        File file = new File(filePath+"/"+filename);
        ImageIO.write(image,"png",file);
        System.err.println("Saved file to: " + filePath);
      }
      catch(IOException event){
        event.printStackTrace();
      }
      return;
    }
  }
  
  private boolean final_red, final_green, final_blue;
  private boolean final_sine, final_cosine, final_average, final_product;
  private int final_iterations;
  
  public void gatherInfo(){
    if(red.isSelected()){
      final_red = true;
    }else{
      final_red = false;
    }
    if(green.isSelected()){
      final_green = true;
    }else{
      final_green = false;
    }
    if(blue.isSelected()){
      final_blue = true;
    }else{
      final_blue = false;
    }
    if(sine.isSelected()){
      final_sine = true;
    }else{
      final_sine = false;
    }
    if(cosine.isSelected()){
      final_cosine = true;
    }else{
      final_cosine = false;
    }
    if(average.isSelected()){
      final_average = true;
    }else{
      final_average = false;
    }
    if(product.isSelected()){
      final_product = true;
    }else{
      final_product = false;
    }
    final_iterations = comp;
    
    canvas.getColors(final_red,final_green,final_blue);
    function = new RandomFunction(final_red, final_green, final_blue, final_sine, final_cosine, final_average, final_product, final_iterations);
    canvas.paint(canvas.getGraphics());
  } 
}