package MainApp.java;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Util.com.HibernateUtil;
import entity.com.product;

public class mainclass {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== PRODUCT CRUD MENU =====");
            System.out.println("1. Insert Product");
            System.out.println("2. View Product by ID");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                // CASE 1: INSERT
                case 1: {
                    System.out.print("Enter Product Name: ");
                    sc.nextLine();
                    String name = sc.nextLine();

                    System.out.print("Enter Description: ");
                    String desc = sc.nextLine();

                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();

                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();

                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();

                    product p = new product(name, desc, price, qty);
                    session.save(p);

                    tx.commit();
                    session.close();

                    System.out.println("✅ Product Inserted Successfully");
                    break;
                }

                // CASE 2: READ
                case 2: {
                    System.out.print("Enter Product ID: ");
                    int id = sc.nextInt();

                    Session session = HibernateUtil.getSessionFactory().openSession();
                    product p = session.get(product.class, id);

                    if (p != null) {
                        System.out.println("ID: " + p.getId());
                        System.out.println("Name: " + p.getName());
                        System.out.println("Description: " + p.getDescription());
                        System.out.println("Price: " + p.getPrice());
                        System.out.println("Quantity: " + p.getQuantity());
                    } else {
                        System.out.println("❌ Product Not Found");
                    }

                    session.close();
                    break;
                }

                // CASE 3: UPDATE
                case 3: {
                    System.out.print("Enter Product ID to Update: ");
                    int id = sc.nextInt();

                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();

                    product p = session.get(product.class, id);

                    if (p != null) {
                        System.out.print("Enter New Price: ");
                        double price = sc.nextDouble();

                        System.out.print("Enter New Quantity: ");
                        int qty = sc.nextInt();

                        p.setPrice(price);
                        p.setQuantity(qty);

                        session.update(p);
                        tx.commit();
                        System.out.println("✅ Product Updated Successfully");
                    } else {
                        System.out.println("❌ Product Not Found");
                        tx.rollback();
                    }

                    session.close();
                    break;
                }

                // CASE 4: DELETE
                case 4: {
                    System.out.print("Enter Product ID to Delete: ");
                    int id = sc.nextInt();
                    
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();

                    product p = session.get(product.class, id);

                    if (p != null) {
                        session.delete(p);
                        tx.commit();
                        System.out.println("✅ Product Deleted Successfully");
                    } else {
                        System.out.println("❌ Product Not Found");
                        tx.rollback();
                    }

                    session.close();
                    break;
                }

                // CASE 5: EXIT
                case 5:
                    System.out.println("🚪 Exiting Application...");
                    break;

                default:
                    System.out.println("❌ Invalid Choice");
            }

        } while (choice != 5);

        sc.close();
    }
}