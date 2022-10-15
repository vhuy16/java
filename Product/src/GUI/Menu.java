package GUI;

import DAO.Input;

import DAO.ProductManager;
import java.util.Scanner;

public class Menu {

    private Scanner sc = new Scanner(System.in);

    public void displayMenu() {
        int choice = 0;

        ProductManager product = new ProductManager();
        do {
            System.out.println("====================MENU====================");
            System.out.println("1. Create a Product.");
            System.out.println("2. Check to exist Product from file.");
            System.out.println("3. Search Product information by name.");
            System.out.println("4. Update Product.");           
            System.out.println("5. Save to file.");
            System.out.println("6. Print list from file.");
            System.out.println("7. Print list in queue");
            System.out.println("Other, Exit.");
            System.out.println("Enter your choice : ");           
            choice = sc.nextInt();                                 
            switch (choice) {
                case 1:
                    product.createProduct();
                    continueMenu();
                    break;
                case 2:
                    product.checkExistProduct();
                    continueMenu();
                    break;
                case 3:
                    product.searchProduct();
                    continueMenu();
                    break;
                
                case 4:
                    System.out.println("You want to update(enter number 1) or delete (enter number 2)");
                    int choiceUpdate = sc.nextInt();
                    switch (choiceUpdate) {
                        case 1:
                            product.updateProduct();
                            continueMenu();
                            break;
                        case 2: 
                            product.deleteProduct();
                            continueMenu();
                    }
                    
                    break;
                case 5:
                    product.saveProduct();
                    continueMenu();
                    break;
                case 6:
                    product.printFromFile();
                    continueMenu();
                    break;
                case 7:
                    product.printList();
                    continueMenu();
                    break;
                default:
                    System.exit(0);
            }
        } while (true);
    }

    public  void continueMenu() {
        String cont = "";
        do {
            System.out.println("Do you want to return to menu? Yes(Y) or No(N)");
            sc.nextLine();
            cont = sc.nextLine();
        } while (!cont.equalsIgnoreCase("y") && !cont.equalsIgnoreCase("n"));
        if (cont.equalsIgnoreCase("n")) {
            System.exit(0);
        }
    }
}
