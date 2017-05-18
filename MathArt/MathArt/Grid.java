import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import javax.imageio.*;

public class Grid
{
  private static Color[][] colors;
  private static String[][] images;
  private static int cellSize;
  private static Color lineColor;
  private static boolean created = false;
  
  public static void main(String[] args){
    
  }  
  
  public static void window(int width, int height)
  {
    if (width > height)
      cellSize = 600 / width;
    else
      cellSize = 600 / height;
    Draw.window(width * cellSize, height * cellSize);
    Draw.setTitle("Grid");
    colors = new Color[width][height];
    images = new String[width][height];
    lineColor = null;
    int x;
    for (x = 0; x < width; x = x + 1)
    {
      int y;
      for (y = 0; y < height; y = y + 1)
      {
        colors[x][y] = new Color(0, 0, 0);
        images[x][y] = "";
        drawCell(x, y);
      }
    }
    created = true;
  }
  
  public static void window(String imageFileName)
  {
    BufferedImage image = loadImage(imageFileName);
    window(image.getWidth(), image.getHeight());
    showImage(image);
    Draw.setTitle(imageFileName);
  }
  
  private static BufferedImage loadImage(String imageFileName)
  {
    URL url = Grid.class.getResource(imageFileName);
    if (url == null)
      throw new RuntimeException("cannot find file:  " + imageFileName);
    try
    {
      return ImageIO.read(url);
    }
    catch(IOException e)
    {
      throw new RuntimeException("unable to read from file:  " + imageFileName);
    }
  }
  
  private static void showImage(BufferedImage image)
  {
    for (int cellX = 0; cellX < getWidth(); cellX++)
    {
      for (int cellY = 0; cellY < getHeight(); cellY++)
      {
        int x = cellX * image.getWidth() / getWidth();
        int y = cellY * image.getHeight() / getHeight();
        int c = image.getRGB(x, y);
        int red = (c & 0x00ff0000) >> 16;
        int green = (c & 0x0000ff00) >> 8;
        int blue = c & 0x000000ff;
        setColor(cellX, cellY, red, green, blue);
      }
    }
  }
  
  public static int getWidth()
  {
    if (!created)
      throw new RuntimeException("Must call window method first");
    return colors.length;
  }
  
  public static int getHeight()
  {
    if (!created)
      throw new RuntimeException("Must call window method first");
    return colors[0].length;
  }
  
  public static boolean isValid(int x, int y)
  {
    if (!created)
      throw new RuntimeException("Must call window method first");
    return x >= 0 && x < getWidth() && y >= 0 && y < getHeight();
  }
  
  private static void checkLoc(int x, int y)
  {
    if (!isValid(x, y))
      throw new RuntimeException("invalid location " + loc(x, y));
  }
  
  public static String loc(int x, int y)
  {
    return "(" + x + ", " + y + ")";
  }
  
  public static int getRed(int x, int y)
  {
    if (!created)
      throw new RuntimeException("Must call window method first");
    checkLoc(x, y);
    return colors[x][y].getRed();
  }
  
  public static int getGreen(int x, int y)
  {
    if (!created)
      throw new RuntimeException("Must call window method first");
    checkLoc(x, y);
    return colors[x][y].getGreen();
  }
  
  public static int getBlue(int x, int y)
  {
    if (!created)
      throw new RuntimeException("Must call window method first");
    checkLoc(x, y);
    return colors[x][y].getBlue();
  }
  
  private static void drawCell(int x, int y)
  {
    Color color = colors[x][y];
    Draw.setColor(color.getRed(), color.getGreen(), color.getBlue());
    Draw.setFill(true);
    Draw.rectangle(x * cellSize, y * cellSize, cellSize, cellSize);
    String image = images[x][y];
    if (!image.equals(""))
    {
      Draw.image(x * cellSize, y * cellSize, cellSize, cellSize, image);
    }
    if (lineColor != null)
    {
      Draw.setColor(lineColor.getRed(), lineColor.getGreen(), lineColor.getBlue());
      Draw.setFill(false);
      Draw.rectangle(x * cellSize, y * cellSize, cellSize, cellSize);
    }
  }
  
  private static boolean validColor(int n)
  {
    return n >= 0 && n <= 255;
  }
  
  private static void checkColor(int red, int green, int blue)
  {
    if (!validColor(red) || !validColor(green) || !validColor(blue))
      throw new RuntimeException("invalid color " + red + ", " +
                                 green + ", " + blue);
  }
  
  public static void setColor(int x, int y, int red, int green, int blue)
  {
    if (!created)
      throw new RuntimeException("Must call window method first");
    checkLoc(x, y);
    checkColor(red, green, blue);
    colors[x][y] = new Color(red, green, blue);
    drawCell(x, y);
  }
  
  public static void setImage(int x, int y, String image)
  {
    if (!created)
      throw new RuntimeException("Must call window method first");
    checkLoc(x, y);
    images[x][y] = image;
    drawCell(x, y);
  }
  
  public static String getImage(int x, int y)
  {
    if (!created)
      throw new RuntimeException("Must call window method first");
    checkLoc(x, y);
    return images[x][y];
  }
  
  public static void setLineColor(int red, int green, int blue)
  {
    if (!created)
      throw new RuntimeException("Must call window method first");
    if (red < 0)
      lineColor = null;
    else
      lineColor = new Color(red, green, blue);
    for (int x = 0; x < getWidth(); x++)
    {
      for (int y = 0; y < getHeight(); y++)
        drawCell(x, y);
    }
  }
  
  public static void pauseUntilMouse()
  {
    if (!created)
      throw new RuntimeException("Must call window method first");
    Draw.pauseUntilMouse();
  }
  
  public static int getMouseX()
  {
    return Draw.getMouseX() / cellSize;
  }
  
  public static int getMouseY()
  {
    return Draw.getMouseY() / cellSize;
  }
}