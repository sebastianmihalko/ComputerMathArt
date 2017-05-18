public class Random implements Function
{
  public Random()
  {
    
  }
  public double evaluate(double x, double y)
  {
    return (Math.random()*2)-1;
  }
  
  public String toString()
  {
    return "rand()";
  }
}