package DAO;

import Entity.Product;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ProductManager extends Product implements IProductDAO {

    ArrayList<Product> productList = new ArrayList();

    private Scanner sc = new Scanner(System.in);

    public ProductManager() {
       productList = readFile();
    }

    @Override
    public void createProduct() {
        String id;
        String name;
        double unitPrice;
        int quantity;
        String status;

        do {
            id = Input.validateInputString("Enter the ID of Product: ","[sS][eE][0-9]{1,3}");
        } while (checkDuplicateID(id) == false);

        do {
            name = Input.validateInputString("Enter the Name of Product: ","[a-zA-Z]{5,}");
        } while (checkDuplicateName(name) == false);

        unitPrice = Input.getDouble(0, 10000);
        quantity = Input.getInt(0, 1000);

        do {
            System.out.println("Enter Status of Product(Available or Not Available): ");
            status = sc.nextLine();
        } while (!status.equalsIgnoreCase("Available") && !status.equalsIgnoreCase("Not Available"));

        Product product = new Product(id, name, unitPrice, quantity, status);
        productList.add(product);
        System.out.println("Added successful");
    }

    @Override
    public void checkExistProduct() {
        String name;
        name = Input.validateInputString("Enter the Name of Product: ","[a-zA-Z]{5,}");
        for (Product product : readFile()) {
            if (product.getName().equals(name)) {
                System.out.println("Exist Product");
                return;
            }
        }
        System.out.println("Have no any product");
    }

    @Override
    public void searchProduct() {
        String nameCheck;
        nameCheck = Input.validateInputString("Enter the Name of Product: ","[a-zA-Z]{5,}");

        for (Product o : productList) {
            if (o.getName().equals(nameCheck)) {
                System.out.println(o);
                return;
            }
        }
        System.out.println("No Product Found!");
    }

    @Override
    public void updateProduct() {
        String checkID = Input.validateInputString("Enter the ID of Product: ","[sS][eE][0-9]{1,3}");
        for (Product o : productList) {
            if (o.getId().equals(checkID)) {

                String name;
                double unitPrice;
                int quantity;
                String status;

                do {
                    name = Input.setName("[a-zA-Z]{5,}");
                } while (!checkDuplicateName(name) == true && !name.isEmpty());
                if (!name.isEmpty()) {
                    o.setName(name);
                }

                unitPrice = Input.setUnitPrice(0, 10000);
                if (unitPrice > 0) {
                    o.setUnitPrice(unitPrice);
                }

                quantity = Input.setQuantity(0, 1000);
                if (quantity > 0) {
                    o.setQuantity(quantity);
                }
                
                do {
                    System.out.println("Enter Status of Product(Available or Not Available): ");
                    status = sc.nextLine();                    
                } while (!status.equalsIgnoreCase("Available") && !status.equalsIgnoreCase("Not Available") && !status.isEmpty());
                if (!status.isEmpty()) {
                    o.setStatus(status);
                }
                return;
            }
        }
        System.out.println("Productname does not exist");
    }

    @Override
    public void deleteProduct() {
        System.out.println("Enter the ID of Product that you want to Delete: ");
        String idCheck = sc.nextLine();

        for (Product o : productList) {
            if (o.getId().equals(idCheck)) {
                productList.remove(o);
                System.out.println("delete successful");
                return;
            }
        }
        System.out.println("Productname does not exist");
    }

    @Override
    public void saveProduct() {
        try {
            FileWriter createFile = new FileWriter("Product.dat");
            BufferedWriter saveFile = new BufferedWriter(createFile);
            for (Product product : productList) {
                saveFile.write(product.toString());
                saveFile.newLine();
            }
            saveFile.close();
            createFile.close();
        } catch (Exception e) {
            System.out.println("Save file failed!");
        }
        System.out.println("Save successful!");
    }

    @Override
    public void printList() {       
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    @Override
    public void printFromFile() {
        for (Product product : readFile()) {         
            System.out.println(product);
        }
    }

    public boolean checkDuplicateName(String nameCheck) {
        for (Product product : productList) {
            if (product.getName().equals(nameCheck)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkDuplicateID(String IDCheck) {
        for (Product product : productList) {
            if (product.getId().equals(IDCheck)) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Product> readFile() {
        ArrayList<Product> productListFromFile = new ArrayList<>();
        String line = "";
        try {
            FileReader sr = new FileReader("Product.dat");
            BufferedReader br = new BufferedReader(sr);

            while (true) {

                line = br.readLine();
                if (line == null) {
                    break;
                }
                String arr[] = line.split("\\s" + "\\|"+"\\|" +"\\s");
                String id = arr[0];
                String name = arr[1];
                double price = Double.parseDouble(arr[2]);
                int quantity = Integer.parseInt(arr[3]);
                String status = arr[4] ;
                productListFromFile.add(new Product(id, name, price, quantity, status));

            }
        } catch (Exception e) {
        }

        Collections.sort(productListFromFile, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.getQuantity() == o2.getQuantity()) {
                    if (o1.getUnitPrice() - o2.getUnitPrice() > 0) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else {
                    return o2.getQuantity() - o1.getQuantity();
                }
            }
        });
        return productListFromFile;
    }
}
