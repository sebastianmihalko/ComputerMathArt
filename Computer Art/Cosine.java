public class Cosine implements Function
{
  Function function;
  
  public Cosine(Function f)
  {
    function = f;
  }
  
  public double evaluate(double x, double y)
  {
    //see Sine's evaluate for logic behind formula
    return Math.cos((function.evaluate(x,y)+1)*Math.PI);
  }
  
  public String toString()
  {
    return "cos("+function.toString()+")";
  }
}