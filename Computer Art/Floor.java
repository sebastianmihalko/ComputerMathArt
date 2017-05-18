public class Floor implements Function
{
  Function function;
  public static double M = 10;
  
  public Floor(Function f)
  {
    function = f;
  }
  
  public double evaluate(double x, double y)
  {
    //input=-1 -> -1M
    //input=1 -> 1M
    return Math.abs((Math.floor(function.evaluate(x*M, y*M))/Math.floor(M)))*2-1;
  }
  
  public String toString()
  {
    return "floor("+function.toString()+")";
  }
  
}