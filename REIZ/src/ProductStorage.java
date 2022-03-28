import Exceptions.SoldOutException;

import java.util.ArrayList;

public class ProductStorage
{
  private ArrayList<Product> products;

  public ProductStorage()
  {
    this.products = new ArrayList<>();
    //Hardcoded products with prices and quantities into the product storage
    Product Apple = new Product("Apple", 10.0f, 5);
    Product Water = new Product("Water", 5.0f, 0);
    Product Bread = new Product("Bread", 6.3f, 15);
    products.add(Apple);
    products.add(Water);
    products.add(Bread);
  }

  //A method for returning a String with information about the whole product storage
  public String getStorage()
  {
    String storage = "";
    for (Product product : products)
    {
      storage += product.getName() + " Quantity: " + product.getQuantity() + "\n";
    }
    return storage;
  }

  //A method that checks if a product is in stock by its name
  public boolean isInStock(String productName) throws SoldOutException
  {
    for (Product product : products)
    {
      if(product.getName().equals(productName) && product.getQuantity() > 0)
      {
        return true;
      }
    }
    throw new SoldOutException("The product is sold out");
  }

  //A method for getting a product by its name
  public Product getProductByName(String product)
  {
    for(Product product1 : products)
    {
      if(product1.getName().equals(product))
      {
        return product1;
      }
    }
    return null;
  }

  //A method for getting all product prices as a String
  public String getPrices()
  {
    String prices = "";
    for (Product product: products)
    {
      prices += product.getName() + "(price: " + product.getPrice() + ") ";
    }
    return prices;
  }
}
