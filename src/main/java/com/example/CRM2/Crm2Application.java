package com.example.CRM2;

import com.example.CRM2.model.*;
import com.example.CRM2.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;

import java.util.*;

import static com.example.CRM2.model.Opportunities_product.*;

@SpringBootApplication
public class Crm2Application implements CommandLineRunner {
    public static boolean exit = false;

    @Autowired
    LeadsRepository leadsRepository;
    @Autowired
    ContactsRepository contactsRepository;
    @Autowired
    OpportunitiesRepository opportunitiesRepository;
    @Autowired
    AccountsRepository accountsRepository;
    @Autowired
    SalesRepRepository salesRepRepository;

    public void run(String... args) throws Exception {
        while (!exit) {
            mainMenu();
        }
        System.exit(0);
    }
    public static void main(String[] args) {
        SpringApplication.run(Crm2Application.class, args);
    }
    public void mainMenu() {
        String textLeads = null, textOpportunities = null, textAccounts = null, textSalesRep = null, textContacts=null;
        int opcion;

        if (leadsRepository.count() >= 0) textLeads = leadsRepository.count() + " leads";
        if (leadsRepository.count() == 1) textLeads = leadsRepository.count() + " lead";
        if (opportunitiesRepository.count() >= 0) textOpportunities = opportunitiesRepository.count() + " opportunities";
        if (opportunitiesRepository.count() == 1) textOpportunities = opportunitiesRepository.count() + " opportunity";
        if (accountsRepository.count() >= 0) textAccounts = accountsRepository.count() + " accounts";
        if (accountsRepository.count() == 1) textAccounts = accountsRepository.count() + " account";
        if (salesRepRepository.count() >= 0) textSalesRep = salesRepRepository.count() + " salesReps";
        if (salesRepRepository.count() == 1) textSalesRep = salesRepRepository.count() + " salesRep";
        if (contactsRepository.count() >= 0) textContacts = contactsRepository.count() + " contacts";
        if (contactsRepository.count() == 1) textContacts = contactsRepository.count() + " contatc";

        System.out.println("\n1. New lead");
        System.out.println("2. Show all leads " + "\u001B[32m" + "            - Currently " + textLeads + "\u001B[0m");
        System.out.println("3. Lookup Lead id");
        System.out.println("4. Convert lead to opportunity");
        System.out.println("5. Show all opportunities " + "\u001B[34m" + "    - Currently " + textOpportunities + "\u001B[0m");
        System.out.println("6. Update an opportunity");
        System.out.println("7. Show all accounts " + "\u001B[36m" + "         - Currently " + textAccounts + "\u001B[0m");
        System.out.println("8. New Sales Rep");
        System.out.println("9. Show all Sales Rep " + "\u001B[35m" + "         - Currently " + textSalesRep + "\u001B[0m");
        System.out.println("9. Show all Contacts " + "\u001B[35m" + "         - Currently " + textContacts + "\u001B[0m");
        System.out.println("11. Exit");
        Scanner sn = new Scanner(System.in);
        System.out.print("Escribe una de las opciones: ");
        try {
            opcion = sn.nextInt();
            switch (opcion) {
                case 1 -> {
                    if (salesRepRepository.count() > 0) {
                        //createLead();
                        leadsRepository.save(createLead());
                    } else {
                        System.out.println("\nDebes crear primero un Sales Rep");
                    }
                }

                case 2 -> showLeads();
                case 3 -> findLeads();
                case 4 -> convertLeadtoOpportunity();
                case 5 -> showOpportunities();
                case 6 -> updateOpportunity();
                case 7 -> showAccounts();
                case 8 -> salesRepRepository.save(createSalesRep());
                case 9 -> showSalesRep();
                case 10 -> showContacts();
                case 11 -> exit = true;
                default -> System.out.println("""
                        \u001B[31m
                        Solo números entre 1 y 7
                        \u001B[0m""");
            }
        } catch (InputMismatchException e) {
            System.out.println("""
                    \u001B[31m
                    Debes insertar un número
                    \u001B[0m""");
            sn.next();
        }
    }

    private void showContacts() {
        System.out.println("\u001B[32m" + "\nContacts\n" + contactsRepository.findAll(Sort.by("id")) + "\u001B[0m");
    }

    private void showLeads() {
        //System.out.println(leadsRepository.findAll(Sort.by("id")));
        System.out.println("\u001B[32m" + "\nLeads\n" + leadsRepository.findAll(Sort.by("id")) + "\u001B[0m");
    }

    private void findLeads() {
    }

    private void convertLeadtoOpportunity() {
        //copiar datos de lead a contacts y poner el id en decisionmaker
        //pedir nuevos datos
        if (leadsRepository.count() > 0) {
            //createLead();
            //leadsRepRepository.save(createLead());
            /********************************************/

            int id, quantity, product = 0, employeeCount;
            String city;
            String country;
            Opportunities_product product_type = null;
            Scanner sn = new Scanner(System.in);
            boolean salir3 = false;
            System.out.print("\nPlease enter the id: ");
            id = sn.nextInt();
            try {
                //contacs.add(leads.get(id));
                while (!salir3) {
                    System.out.print("\nPlease enter the product type (1. HYBRID, 2. FLATBED, 3. BOX): ");
                    try {
                        product = Integer.parseInt(sn.next());
                        if (product < 0 || product > 3)
                            throw new InputMismatchException("\u001B[31m"+"El número debe estar entre 1 y 3"+"\u001B[0m");
                        else salir3 = true;
                    } catch (InputMismatchException | IllegalArgumentException e) {
                        System.out.println("\u001B[31m"+"Debes insertar un número entre 1 y 3"+"\u001B[0m");
                        sn.nextLine();
                    }

                }
                switch (product) {
                    case 1 -> product_type = HYBRID;
                    case 2 -> product_type = FLATBED;
                    case 3 -> product_type = BOX;
                    default -> System.out.println("Solo números entre 1 y 3");
                }
                System.out.print("Please enter the quantity: ");
                quantity = Integer.parseInt(sn.next());
                System.out.print("Please enter the number of employees: ");
                employeeCount = Integer.parseInt(sn.next());
                System.out.print("Please enter the city: ");
                city = sn.next();
                System.out.print("Please enter the country: ");
                country = sn.next();
                //Opportunities temp = new Opportunities(product_type, quantity, leads.get(id), OPEN);
                //opportunities.add(temp);
                //accountsLeads.add(leads.get(id));
                //accountsOpportunities.add(temp);
                //accounts.add(new Accounts(PRODUCE, employeeCount, city, country, accountsLeads, accountsOpportunities));
                //leads.remove(id);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("""
                    \u001B[31m
                    El id está fuera de rango o no existe
                    \u001B[0m""");
            }


             /******************************************/
        } else {
            System.out.println("\nDebes crear primero un lead");
        }
    }

    private void showOpportunities() {
    }

    private void updateOpportunity() {
    }

    private void showAccounts() {
    }


    private void showSalesRep() {
        //Optional<SalesRep> salesRepOptional
        //List<SalesRep> salesRepList = salesRepRepository.findAll(Sort.by("id"));
        //System.out.println(salesRepList);
        //System.out.println(salesRepRepository.findAll(Sort.by("id")));
        System.out.println("\u001B[35m" + "\nSales Reps\n" + salesRepRepository.findAll(Sort.by("id")) + "\u001B[0m");
    }

    public Leads createLead() {
        String name, phone, email, company;
        Long salesRepId = null;
        boolean exit2 = false;
        Scanner sn = new Scanner(System.in);
        System.out.print("\nPlease enter the name: ");
        name = sn.next();
        System.out.print("Please enter the phone number: ");
        phone = sn.next();
        System.out.print("Please enter the email: ");
        email = sn.next();
        System.out.print("Please enter the company name: ");
        company = sn.next();
        while (!exit2) {
            System.out.println("\u001B[35m" + "\nSales Reps\n" + salesRepRepository.findAll(Sort.by("id")) + "\u001B[0m");
            System.out.print("\nPlease enter the Sales Rep Id: ");
            try {
                salesRepId = Long.parseLong(sn.next());
                if (salesRepId > salesRepRepository.count()) throw new InputMismatchException();
                else exit2=true;
            } catch (NumberFormatException | InputMismatchException | DataIntegrityViolationException e) {
                System.out.println(
                    "\u001B[31m"+
                    "Solo números entre 1 y " + salesRepRepository.count() +
                    "\u001B[0m");
                sn.nextLine();
            }
        }
        return new Leads(name, phone, email, company, salesRepId);
    }
    static public SalesRep createSalesRep() {
        String name;
        Scanner sn = new Scanner(System.in);
        System.out.print("\nPlease enter the name: ");
        name = sn.next();
        return new SalesRep(name);
    }

}
