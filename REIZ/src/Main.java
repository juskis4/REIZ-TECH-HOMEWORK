import java.util.Scanner;

public class Main
{
  public static void main(String[] args)
  {
    SupermarketServiceImpl supermarketService = SupermarketServiceImpl.getInstance();
    Scanner scanner = new Scanner(System.in);
    System.out.println("----------------------------------------");
    System.out.println("Initial Product Inventory");
    System.out.println(supermarketService.getProductInventory());
    System.out.println("Initial Cash Inventory");
    System.out.println(supermarketService.getCashInventory());
    System.out.println("----------------------------------------");
    while(true)
    {
      System.out.println("What would you like to buy? Type in the name of the desired product.");
      System.out.println(supermarketService.getPrices());
      String product = scanner.nextLine();
      supermarketService.buyProduct(product);
      System.out.println("----------------------------------------");
      System.out.println("Updated Product Inventory");
      System.out.println(supermarketService.getProductInventory());
      System.out.println("Updated Cash Inventory");
      System.out.println(supermarketService.getCashInventory());
      System.out.println("----------------------------------------");
    }
  }
}
