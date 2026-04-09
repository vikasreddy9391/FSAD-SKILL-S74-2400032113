package com.klu.mainapp;

import com.klu.model.Product;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("1 Insert Product");
        System.out.println("2 Sort Price Ascending");
        System.out.println("3 Sort Price Descending");
        System.out.println("4 Sort Quantity Highest First");
        System.out.println("5 Pagination");
        System.out.println("6 Aggregate Functions");
        System.out.println("7 Group By Description");
        System.out.println("8 Filter Price Range");
        System.out.println("9 LIKE Operations");
        System.out.println("10 Exit");

        int ch = sc.nextInt();

        switch (ch) {

            case 1:
                System.out.print("How many products: ");
                int n = sc.nextInt();

                for (int i = 0; i < n; i++) {
                    Product p = new Product();

                    System.out.print("Name: ");
                    p.setName(sc.next());

                    System.out.print("Description: ");
                    p.setDescription(sc.next());

                    System.out.print("Price: ");
                    p.setPrice(sc.nextDouble());

                    System.out.print("Quantity: ");
                    p.setQuantity(sc.nextInt());

                    session.save(p);
                }
                tx.commit();
                System.out.println("Products inserted");
                break;

            case 2:
                List<Product> asc =
                        session.createQuery("from Product order by price asc", Product.class).list();
                asc.forEach(p ->
                        System.out.println(p.getName() + " " + p.getPrice()));
                break;

            case 3:
                List<Product> desc =
                        session.createQuery("from Product order by price desc", Product.class).list();
                desc.forEach(p ->
                        System.out.println(p.getName() + " " + p.getPrice()));
                break;

            case 4:
                List<Product> qty =
                        session.createQuery("from Product order by quantity desc", Product.class).list();
                qty.forEach(p ->
                        System.out.println(p.getName() + " " + p.getQuantity()));
                break;

            case 5:
                Query q = session.createQuery("from Product");
                q.setFirstResult(0);
                q.setMaxResults(3);
                System.out.println("First 3 Products");
                q.list().forEach(System.out::println);

                q.setFirstResult(3);
                q.setMaxResults(3);
                System.out.println("Next 3 Products");
                q.list().forEach(System.out::println);
                break;

            case 6:
                Long total =
                        (Long) session.createQuery("select count(*) from Product").uniqueResult();

                Long available =
                        (Long) session.createQuery("select count(*) from Product where quantity>0").uniqueResult();

                List<Object[]> group =
                        session.createQuery("select description,count(*) from Product group by description").list();

                Double min =
                        (Double) session.createQuery("select min(price) from Product").uniqueResult();

                Double max =
                        (Double) session.createQuery("select max(price) from Product").uniqueResult();

                System.out.println("Total Products: " + total);
                System.out.println("Quantity > 0: " + available);
                group.forEach(o ->
                        System.out.println(o[0] + " " + o[1]));
                System.out.println("Min Price: " + min);
                System.out.println("Max Price: " + max);
                break;

            case 7:
                List<Object[]> grp =
                        session.createQuery("select description,count(*) from Product group by description").list();
                grp.forEach(o ->
                        System.out.println(o[0] + " " + o[1]));
                break;

            case 8:
                System.out.print("Min price: ");
                double minp = sc.nextDouble();
                System.out.print("Max price: ");
                double maxp = sc.nextDouble();

                Query range =
                        session.createQuery("from Product where price between :a and :b");
                range.setParameter("a", minp);
                range.setParameter("b", maxp);

                range.list().forEach(System.out::println);
                break;

            case 9:
                System.out.println("1 Starts With");
                System.out.println("2 Ends With");
                System.out.println("3 Contains");
                System.out.println("4 Exact Length");

                int like = sc.nextInt();

                if (like == 1) {
                    System.out.print("Enter letters: ");
                    String s = sc.next() + "%";
                    session.createQuery("from Product where name like :x")
                            .setParameter("x", s)
                            .list().forEach(System.out::println);
                }

                if (like == 2) {
                    System.out.print("Enter letters: ");
                    String s = "%" + sc.next();
                    session.createQuery("from Product where name like :x")
                            .setParameter("x", s)
                            .list().forEach(System.out::println);
                }

                if (like == 3) {
                    System.out.print("Enter letters: ");
                    String s = "%" + sc.next() + "%";
                    session.createQuery("from Product where name like :x")
                            .setParameter("x", s)
                            .list().forEach(System.out::println);
                }

                if (like == 4) {
                    System.out.print("Enter length: ");
                    int len = sc.nextInt();
                    session.createQuery("from Product where length(name)=:l")
                            .setParameter("l", len)
                            .list().forEach(System.out::println);
                }
                break;

            case 10:
                System.out.println("Program Ended");
                break;
        }

        session.close();
        sf.close();
    }
}
