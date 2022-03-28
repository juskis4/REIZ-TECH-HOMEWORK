import Exceptions.NotEnoughChangeException;
import Exceptions.PayNotAcceptedException;
import Exceptions.SoldOutException;

import java.util.Scanner;

public class SupermarketServiceImpl implements SupermarketService
{
  private static SupermarketServiceImpl firstInstance = null;

  private ProductStorage productStorage;
  private CashRegister cashRegister;
  private float leftToPay;

  private SupermarketServiceImpl()
  {
    productStorage = new ProductStorage();
    cashRegister = new CashRegister();
    leftToPay = 0;
  }

  public static SupermarketServiceImpl getInstance()
  {
    if (firstInstance == null)
    {
      firstInstance = new SupermarketServiceImpl();
    }
    return firstInstance;
  }

  @Override public String getProductInventory()
  {
    return productStorage.getStorage();
  }

  @Override public String getCashInventory()
  {
    return cashRegister.getCash();
  }

  @Override public String getPrices()
  {
    return productStorage.getPrices();
  }

  //Main method of buying a product
  //It takes a product name and then checks if its in stock
  //If it is then it asks the customer to give a bill or a coin and loops until
  //The whole product price is paid
  @Override public void buyProduct(String product)
  {
    try
    {
      if (productStorage.isInStock(product))
      {
        float total = 0;
        leftToPay = productStorage.getProductByName(product).getPrice();
        System.out.println(
            "You are trying to buy " + product + ". You need to pay "
                + leftToPay);
        System.out.println(
            "Provide a bill or a coin (accepted values: 0.1, 0.5, 1, 2)");
        while (leftToPay > 0)
        {
          //Taking user input
          Scanner in = new Scanner(System.in);
          float userIn = in.nextFloat();

          //Subtracting the user input from the total price left to pay
          this.leftToPay = leftToPay - userIn;
          //Rounding up the float to .1 decimal
          leftToPay = (float) (Math.round(leftToPay * 10.0) / 10.0);
          //Counting the total amount paid by the customer
          total += userIn;

          //Here I add the given bill/coin to the register
          cashRegister.addMoney(userIn);

          //If statement checking if it needs to ask the customer to pay more.
          //If the customer paid the whole sum or paid more, than it breaks out
          //of the while loop
          if (leftToPay <= 0)
          {
            break;
          }

          System.out.println(
              "You paid " + total + " in total. You still need to pay "
                  + leftToPay);
          System.out.println(
              "Provide a bill or a coin (accepted values: 0.1, 0.5, 1, 2)");
        }

        //This statement is only true when there is change for the store to return
        if (leftToPay < 0)
        {
          try
          {
            System.out.println(cashRegister.getChange(leftToPay));
          }
          catch (NotEnoughChangeException e)
          {
            e.printStackTrace();
          }
        }
        //Here I take out the sold product from the storage
        productStorage.getProductByName(product).sell();
        System.out.println("Here is your product: " + product);
      }

      else
      {
        System.out.println("Product is out of Stock");
      }
    }
    catch (SoldOutException | PayNotAcceptedException e)
    {
      e.printStackTrace();
    }
  }

}
