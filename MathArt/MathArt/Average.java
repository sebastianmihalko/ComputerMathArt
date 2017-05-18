public class Average implements Function
{
  Function function1;
  Function function2;
  
  public Average(Function f1, Function f2)
  {
    function1 = f1;
    function2 = f2;
  }
  
  public double evaluate(double x, double y)
  {
    //inputs between -1 and 1
    //(num1+num2)/2 will be between -1 and 1 also
    return (function1.evaluate(x,y) + function2.evaluate(x,y))/2;
  }
  
  public String toString()
  {
    return "average("+function1.toString()+","+function2.toString()+")";
  }
}