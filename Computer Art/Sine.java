public class Sine implements Function
{
  Function function;
  
  public Sine(Function f)
  {
    function = f;
  }
  
  public double evaluate(double x, double y)
  {
    //each input will be between -1 and 1
    //-1 corresponds to 0 degrees
    //1 corresponds to 360 degrees
    //num*180 -> -180 to 180
    //num*180+180 -> 0 to 360
    //Math.sin takes in angles in radians
    //(num*180+180) * pi/180 will convert to radians
    //equivalent to (num+1)*pi
    return Math.sin((function.evaluate(x,y)+1)*Math.PI);
  }
  
  public String toString()
  {
    return "sin("+function.toString()+")";
  }
}