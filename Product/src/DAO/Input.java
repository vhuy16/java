package DAO;

import java.util.Scanner;

public class Input {

    private static Scanner sc = new Scanner(System.in);

    public static int getInt( int min, int max) {
        int n;
        while (true) {
            try {
                System.out.print("Enter the Quantity of product: ");
                n = Integer.parseInt(sc.nextLine());
                if (n < min || n > max) {
                    throw new Exception();
                } else {
                    return n;
                }
            } catch (Exception e) {
                System.out.println( "Enter again");
            
        }
        }
    }

    public static String validateInputString(String msg, String format) {
        String stringValidation;

        while (true) {
            System.out.println(msg);
            stringValidation = sc.nextLine();
            if (stringValidation.isEmpty() || stringValidation.matches(format) == false) {
                System.out.println("Enter again");
            } else {
                return stringValidation;
            }
        }
    }


    public static double getDouble(int min, int max) {
        double n;
        while (true) {
            try {
                System.out.print("Enter the Price of product: ");
                n = Double.parseDouble(sc.nextLine());
                if (n < min || n > max) {
                    throw new Exception();
                } else {
                    return n;
                }
            } catch (Exception e) {
                System.out.println("Enter again");
            }
        }

    }

    

    public static String setName(String format) {
        String name;

        while (true) {
            System.out.println("Enter the name of product: ");
            name = sc.nextLine();
            if (name.isEmpty() || name.matches(format) == true) {
                return name;
            }
            System.out.println("Enter again");
        }
        
    }

    public static double setUnitPrice( int min, int max) {
        String price;
        boolean check =true;
        double a = 0;
       do{
           
            System.out.println("Enter the Price of product: ");
            price = sc.nextLine();
            if (price.isEmpty()) {
                return -1;
               
            } else {
               
                try {
                     a = Double.parseDouble(price);
                    if (a < min || a > max) {
                        throw new Exception();
                    } else {
                        check = false;
                    }
                } catch (Exception e) {
                    System.out.println("Enter again");
                }
            }
        }while(check);
       return a; 
    }
  public static int setQuantity(int min, int max) {
        String price;
        boolean check = true;
        int a = 0;
        while (check){
            System.out.println("Enter the Quantity of product: ");
            price = sc.nextLine();
            if (price.isEmpty()) {
                return -1;
            } else {
                
                try {
                    a = Integer.parseInt(price);
                    if (a < min || a > max) {
                        throw new Exception();
                    } else {
                        check = false;
                    }
                } catch (Exception e) {
                    System.out.println("Enter again");
                }
            }
        }
       return a; 
    }
   
}
