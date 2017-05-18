public class Picture
{
  static Function f;
  
  public static void main(String[] args)
  {
    f = randomFunction(0);
    System.out.println(f);
    Draw.window(255,255);
    for(int i = 0; i < 255; i++)
    {
      for(int j = 0; j < 255; j++)
      {
        int colorValue = (int)(f.evaluate(i,j));
        Draw.setColor(colorValue, colorValue, colorValue);
        Draw.rectangle(i, j, 1, 1);
        
      }
    }
  }
  
  public static Function randomFunction(int complexity)
  {
    double random = Math.random();
    if(complexity == 0)
    {
      if(random < 0.5) return new X();
      else return new Y();
    }
    else
    {
      if(random < 0.25) return new Average(randomFunction(complexity-1),randomFunction(complexity-1));
      else if(random < 0.5) return new Product(randomFunction(complexity-1),randomFunction(complexity-1));
      else if(random < 0.75) return new Sine(randomFunction(complexity-1));
      else return new Cosine(randomFunction(complexity-1));
    }
    
  }
  
}