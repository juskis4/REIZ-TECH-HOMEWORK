import Exceptions.NotEnoughChangeException;
import Exceptions.PayNotAcceptedException;

import java.util.HashMap;

public class CashRegister
{
  private HashMap<Float, Integer> coins;
  public CashRegister()
  {
    coins = new HashMap<>();
    //Hardcoded values and quantities of money
    coins.put(2f, 15);
    coins.put(1f, 7);
    coins.put(0.5f, 9);
    coins.put(0.1f, 12);
  }

  //A method for getting a string that contains information
  // about the cash quantity in the register
  public String getCash()
  {
    String cashInRegister = "";
    for (float i : coins.keySet())
    {
      cashInRegister += "Value: " + i + ", quantity: " + coins.get(i) + "\n";
    }
    return cashInRegister;
  }

  //A method for getting the change from the register
  public String getChange(float change) throws NotEnoughChangeException
  {
    //All of these integers are for keeping track of the exact change
    int quantity2 = 0;
    int quantity1 = 0;
    int quantity05 = 0;
    int quantity01 = 0;

    while(!(change==0))
    {
      change = (float) (Math.round(change * 10.0)/10.0);
      if(change <= -2 && coins.get(2f) > 0)
      {
        change += 2;
        //Here I take it out of the register
        coins.put(2f, coins.get(2f) - 1);
        quantity2 ++;
      }
      else if(change <= -1 && coins.get(1f) > 0)
      {
        change += 1;
        coins.put(1f, coins.get(1f) - 1);
        quantity1 ++;
      }
      else if(change <= -0.5 && coins.get(0.5f) > 0)
      {
        change += 0.5;
        coins.put(0.5f, coins.get(0.5f) - 1);
        quantity05 ++;
      }
      else if(change <= -0.1 && coins.get(0.1f) > 0)
      {
        change += 0.1;
        coins.put(0.1f, coins.get(0.1f) - 1);
        quantity01 ++;
      }
      else{
        throw new NotEnoughChangeException("The cash register does not have sufficient change to complete this transaction");
      }

    }

    return "Here is your change"
        + "\nValue 2.0: " + quantity2
        + "\nValue 1.0: " + quantity1
        + "\nValue 0.5: " + quantity05
        + "\nValue 0.1: " + quantity01;
  }

  //A method for adding the money got from the customer to the cash register
  public void addMoney(float money) throws PayNotAcceptedException
  {
    if(!(coins.containsKey(money)))
    {
      throw new PayNotAcceptedException("Please enter the accepted bill or coin");
    }
    coins.put(money, coins.get(money) + 1);
  }


}
