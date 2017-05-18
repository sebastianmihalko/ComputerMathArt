public class Product implements Function
{
  Function function1;
  Function function2;
  
  public Product(Function f1, Function f2)
  {
    function1 = f1;
    function2 = f2;
  }
  
  public double evaluate(double x, double y)
  {
    //inputs will be between -1 and 1
    //num1*num2 will be between -1 and 1 also
    return function1.evaluate(x,y) * function2.evaluate(x,y);
  }
  
  public String toString()
  {
    return "product("+function1.toString()+","+function2.toString()+")";
  }
}