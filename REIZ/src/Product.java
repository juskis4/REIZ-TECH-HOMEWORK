public class Product
{
  private String name;
  private float price;
  private int quantity;

  public Product(String name, float price, int quantity)
  {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public void sell()
  {
    quantity --;
  }

  public float getPrice()
  {
    return price;
  }

  public String getName()
  {
    return name;
  }

}
