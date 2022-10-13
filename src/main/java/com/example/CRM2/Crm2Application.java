package com.example.CRM2;

import com.example.CRM2.model.*;
import com.example.CRM2.repositories.*;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.*;

import static com.example.CRM2.model.OpportunitiesProduct.*;

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

    public static void main(String[] args) {
        SpringApplication.run(Crm2Application.class, args);
    }

   static public SalesRep createSalesRep() {
        String name;
        Scanner sn = new Scanner(System.in);
        System.out.print("\nPlease enter the name: ");
        name = sn.next();
        return new SalesRep(name);
    }

    public void run(String... args) throws Exception {
        salesRepRepository.saveAll(List.of(
                new SalesRep("Mando"),
                new SalesRep("Lili"),
                new SalesRep("Cinta"),
                new SalesRep("Oxana")
        ));
        //Leads(String name, String phoneNumber, String email, String companyName, SalesRep salesRepId) {
        leadsRepository.saveAll(List.of(
                new Leads("saddgs","555666222","nada1@nada.com","pepinos ltd", salesRepRepository.findById(1L).get()),
                new Leads("khjgds","755666222","nada2@nada.com","papa ltd", salesRepRepository.findById(3L).get()),
                new Leads("vxgcnm","855666222","nada3@nada.com","tomate ltd", salesRepRepository.findById(3L).get()),
                new Leads("jhdsga","955666222","nada4@nada.com","brocoli ltd", salesRepRepository.findById(2L).get()),
                new Leads("utrtrg","255666222","nada5@nada.com","calabaza ltd", salesRepRepository.findById(1L).get())

        ));
        while (!exit) {
            mainMenu();
        }
        System.exit(0);
    }

    public void mainMenu() {
        String textLeads = null, textOpportunities = null, textAccounts = null, textSalesRep = null, textContacts = null;
        int opcion;

        if (leadsRepository.count() >= 0) textLeads = leadsRepository.count() + " leads";
        if (leadsRepository.count() == 1) textLeads = leadsRepository.count() + " lead";
        if (opportunitiesRepository.count() >= 0)
            textOpportunities = opportunitiesRepository.count() + " opportunities";
        if (opportunitiesRepository.count() == 1) textOpportunities = opportunitiesRepository.count() + " opportunity";
        if (accountsRepository.count() >= 0) textAccounts = accountsRepository.count() + " accounts";
        if (accountsRepository.count() == 1) textAccounts = accountsRepository.count() + " account";
        if (salesRepRepository.count() >= 0) textSalesRep = salesRepRepository.count() + " salesReps";
        if (salesRepRepository.count() == 1) textSalesRep = salesRepRepository.count() + " salesRep";
        if (contactsRepository.count() >= 0) textContacts = contactsRepository.count() + " contacts";
        if (contactsRepository.count() == 1) textContacts = contactsRepository.count() + " contact";

        System.out.println("\n1. New Sales Rep");
        System.out.println("2. Show all Sales Rep " + "\u001B[35m" + "         - Currently " + textSalesRep + "\u001B[0m");
        System.out.println("3. New lead");
        System.out.println("4. Show all leads " + "\u001B[32m" + "             - Currently " + textLeads + "\u001B[0m");
        System.out.println("5. Lookup Lead id");
        System.out.println("6. Convert lead to opportunity");
        System.out.println("7. Show all opportunities " + "\u001B[34m" + "     - Currently " + textOpportunities + "\u001B[0m");
        System.out.println("8. Update an opportunity");
        System.out.println("9. Show all accounts " + "\u001B[36m" + "          - Currently " + textAccounts + "\u001B[0m");
        System.out.println("10. Show all Contacts " + "\u001B[35m" + "         - Currently " + textContacts + "\u001B[0m");

        System.out.println("\u001B[35m" + "Reports by SalesRep        " + "\u001B[0m" + "    11. Leads           12. Opportunities   13. CLOSED-WON    14. CLOSED-LOST   15. OPEN");
        System.out.println("\u001B[35m" + "Reports by Product         " + "\u001B[0m" + "    16. Opportunities   17. CLOSED-WON      18. CLOSED-LOST   19. OPEN");
        System.out.println("\u001B[35m" + "Reports by Country         " + "\u001B[0m" + "    20. Opportunities   21. CLOSED-WON      22. CLOSED-LOST   23. OPEN");
        System.out.println("\u001B[35m" + "Reports by City            " + "\u001B[0m" + "    24. Opportunities   25. CLOSED-WON      26. CLOSED-LOST   27. OPEN");
        System.out.println("\u001B[35m" + "Reports by Industry        " + "\u001B[0m" + "    28. Opportunities   29. CLOSED-WON      30. CLOSED-LOST   31. OPEN");
        System.out.println("\u001B[35m" + "Reports of Employee Count  " + "\u001B[0m" + "    32. Mean            33. MEDIAN          34. MAX           35. MIN");
        System.out.println("\u001B[35m" + "Reports of Quantity        " + "\u001B[0m" + "    36. Mean            37. MEDIAN          38. MAX           39. MIN");
        System.out.println("\u001B[35m" + "Reports of Opps per Account" + "\u001B[0m" + "    40. Mean            41. MEDIAN          42. MAX           43. MIN");

        System.out.println("50. Exit");
        Scanner sn = new Scanner(System.in);
        System.out.print("Escribe una de las opciones: ");
        try {
            opcion = sn.nextInt();
            switch (opcion) {
                case 1 -> salesRepRepository.save(createSalesRep());
                case 2 -> showSalesRep();
                case 3 -> { if (salesRepRepository.count() > 0) leadsRepository.save(createLead());
                            else System.out.println("\nDebes crear primero un Sales Rep"); }
                case 4 -> showLeads();
                case 5 -> findLeads();
                case 6 -> opportunitiesRepository.save(convertLeadtoOpportunity());
                case 7 -> showOpportunities();
                case 8 -> updateOpportunity();
                case 9 -> showAccounts();
                case 10 -> showContacts();

                case 11 -> countLeadBySalesRep();
                case 12 -> countOpportunitiesBySalesRep();
                case 13 -> countCLOSEDWONBySalesRep();
                case 14 -> countCLOSEDLOSTBySalesRep();
                case 15 -> countOPENBySalesRep();

                case 16 -> countOpportunitiesByProduct();
                case 17 -> countCLOSEDWONOpportunitiesByProduct();
                case 18 -> countCLOSEDLOSTOpportunitiesByProduct();
                case 19 -> countOPENOpportunitiesByProduct();

                case 20 -> countOpportunitiesByCountry();
                case 21 -> countCLOSEDWONOpportunitiesByCountry();
                case 22 -> countCLOSEDLOSTOpportunitiesByCountry();
                case 23 -> countOPENOpportunitiesByCountry();

                case 24 -> countOpportunitiesByCity();
                case 25 -> countCLOSEDWONOpportunitiesByCity();
                case 26 -> countCLOSEDLOSTOpportunitiesByCity();
                case 27 -> countOPENOpportunitiesByCity();

                case 28 -> countOpportunitiesByIndustry();
                case 29 -> countCLOSEDWONOpportunitiesByIndustry();
                case 30 -> countCLOSEDLOSTOpportunitiesByIndustry();
                case 31 -> countOPENOpportunitiesByIndustry();

                case 32 -> employeeMean();
                case 33 -> employeeMedian();
                case 34 -> employeeMax();
                case 35 -> employeeMin();

                case 36 -> productsOrderMean();
                case 37 -> productsOrderMedian();
                case 38 -> productsOrderMax();
                case 39 -> productsOrderMin();

                case 40 -> opportunitiesAccountMean();
                case 41 -> opportunitiesAccountMedian();
                case 42 -> opportunitiesAccountMax();
                case 43 -> opportunitiesAccountMin();

                case 50 -> exit = true;
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



    private void countLeadBySalesRep() {
        System.out.println("\nLeads by SalesRep");
        for (List c:leadsRepository.countBySalesRep()) {
            System.out.println(c);
        }
    }
    private void countOpportunitiesBySalesRep() {
        System.out.println("\nOpportunities by SalesRep");
        for (List c:opportunitiesRepository.countBySalesRep()) {
            System.out.println(c);
        }
    }
    private void countCLOSEDWONBySalesRep() {
        System.out.println("\nCLOSED-WON by SalesRep");
        for (List c:opportunitiesRepository.countCLOSEDWONBySalesRep()) {
            System.out.println(c);
        }
    }
    private void countCLOSEDLOSTBySalesRep() {
        System.out.println("\nCLOSED-LOST by SalesRep");
        for (List c:opportunitiesRepository.countCLOSEDLOSTBySalesRep()) {
            System.out.println(c);
        }
    }
    private void countOPENBySalesRep() {
        System.out.println("\nOPEN by SalesRep");
        for (List c:opportunitiesRepository.countOPENBySalesRep()) {
            System.out.println(c);
        }
    }

    private void countOpportunitiesByProduct() {
        System.out.println("\nOpportunities by Product");
        for (List c:opportunitiesRepository.countByProduct()) {
            System.out.println(c);
        }
    }
    private void countCLOSEDWONOpportunitiesByProduct() {
        System.out.println("\nCLOSED-WON by Product");
        for (List c:opportunitiesRepository.countCLOSEDWONByProduct()) {
            System.out.println(c);
        }
    }
    private void countCLOSEDLOSTOpportunitiesByProduct() {
        System.out.println("\nCLOSED-LOST by Product");
        for (List c:opportunitiesRepository.countCLOSEDLOSTByProduct()) {
            System.out.println(c);
        }
    }
    private void countOPENOpportunitiesByProduct() {
        System.out.println("\nOPEN by Product");
        for (List c:opportunitiesRepository.countOPENByProduct()) {
            System.out.println(c);
        }
    }

    private void countOpportunitiesByCountry() {
        System.out.println("\nOpportunities by Country");
        for (List c:opportunitiesRepository.countByCountry()) {
            System.out.println(c);
        }
    }
    private void countCLOSEDWONOpportunitiesByCountry() {
        System.out.println("\nOpportunities CLOSED-WON by Country");
        for (List c:opportunitiesRepository.countCLOSEDWONByCountry()) {
            System.out.println(c);
        }
    }
    private void countCLOSEDLOSTOpportunitiesByCountry() {
        System.out.println("\nOpportunities CLOSED-LOST by Country");
        for (List c:opportunitiesRepository.countCLOSEDLOSTByCountry()) {
            System.out.println(c);
        }
    }
    private void countOPENOpportunitiesByCountry() {
        System.out.println("\nOpportunities OPEN by Country");
        for (List c:opportunitiesRepository.countOPENByCountry()) {
            System.out.println(c);
        }
    }

    private void countOpportunitiesByCity() {
        System.out.println("\nOpportunities by City");
        for (List c:opportunitiesRepository.countByCity()) {
            System.out.println(c);
        }
    }
    private void countCLOSEDWONOpportunitiesByCity() {
        System.out.println("\nOpportunities CLOSED-WON by City");
        for (List c:opportunitiesRepository.countCLOSEDWONByCity()) {
            System.out.println(c);
        }
    }
    private void countCLOSEDLOSTOpportunitiesByCity() {
        System.out.println("\nOpportunities CLOSED-LOST by City");
        for (List c:opportunitiesRepository.countCLOSEDLOSTByCity()) {
            System.out.println(c);
        }
    }
    private void countOPENOpportunitiesByCity() {
        System.out.println("\nOpportunities OPEN by City");
        for (List c:opportunitiesRepository.countOPENByCity()) {
            System.out.println(c);
        }
    }
    private void countOpportunitiesByIndustry() {
        System.out.println("\nOpportunities by Industry");
        for (List c:opportunitiesRepository.countByIndustry()) {
            System.out.println(c);
        }
    }
    private void countCLOSEDWONOpportunitiesByIndustry() {
        System.out.println("\nOpportunities CLOSED-WON by Industry");
        for (List c:opportunitiesRepository.countCLOSEDWONByIndustry()) {
            System.out.println(c);
        }
    }
    private void countCLOSEDLOSTOpportunitiesByIndustry() {
        System.out.println("\nOpportunities CLOSED-LOST by Industry");
        for (List c:opportunitiesRepository.countCLOSEDLOSTByIndustry()) {
            System.out.println(c);
        }
    }
    private void countOPENOpportunitiesByIndustry() {
        System.out.println("\nOpportunities OPEN by Industry");
        for (List c:opportunitiesRepository.countOPENByIndustry()) {
            System.out.println(c);
        }
    }

    private void employeeMean() {
        System.out.println("\nMean Employee Count");
        for (List c:accountsRepository.employeeMean()) {
            System.out.println(c);
        }
    }

    private void employeeMedian() {
        System.out.println("\nMedian Employee Count");
//        for (List c:accountsRepository.employeeMedian()) {
//            System.out.println(c);
//        }
    }

    private void employeeMax() {
        System.out.println("\nMax Employee Count");
        for (List c:accountsRepository.employeeMax()) {
            System.out.println(c);
        }
    }

    private void employeeMin() {
        System.out.println("\nMin Employee Count");
        for (List c:accountsRepository.employeeMin()) {
            System.out.println(c);
        }
    }

    private void productsOrderMean() {
        System.out.println("\nMean Products Order");
        for (List c:opportunitiesRepository.productsMean()) {
            System.out.println(c);
        }
    }

    private void productsOrderMedian() {
    }

    private void productsOrderMax() {
        System.out.println("\nMax Products Order");
        for (List c:opportunitiesRepository.productsMax()) {
            System.out.println(c);
        }
    }

    private void productsOrderMin() {
        System.out.println("\nMin Products Order");
        for (List c:opportunitiesRepository.productsMin()) {
            System.out.println(c);
        }
    }

    private void opportunitiesAccountMean() {
        System.out.println("\nMean Opportunities Account");
        for (List c:opportunitiesRepository.opportunitiesMean()) {
            System.out.println(c);
        }
    }

    private void opportunitiesAccountMedian() {
    }

    private void opportunitiesAccountMax() {
    }

    private void opportunitiesAccountMin() {
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

    private Opportunities convertLeadtoOpportunity() {
        Opportunities opportunities = null;
        if (leadsRepository.count() > 0) {
            //createLead();
            //leadsRepRepository.save(createLead());

            /********************************************/
            Long idLead;
            //opportunities = null;
            int quantity, product = 0;
            int employeeCount;
            String city;
            String country;
            OpportunitiesProduct product_type = null;
            Scanner sn = new Scanner(System.in);
            boolean salir3 = false;
            System.out.println("\u001B[32m" + "\nLeads\n" + leadsRepository.findAll(Sort.by("id")) + "\u001B[0m");
            System.out.print("\nPlease enter the Lead id: ");
            idLead = Long.valueOf(sn.nextInt());                // <--?????????????
            try {
                Contacts contact = null;
                Leads lead = null;
                if (leadsRepository.existsById((long) idLead)) {
                    //hay otra manera más elegante?
                    //contact = (Contacts) leadsRepository.getReferenceById(idLead);
                    //super(name, phoneNumber, email, companyName, salesRepId);
                    Leads leads = leadsRepository.findById(idLead).get();
                    contact = new Contacts(leads.getName(),leads.getPhoneNumber(),leads.getEmail(),leads.getCompanyName(),leads.getSalesRepId(),null);
                    //contact = new Contacts(leadsRepository.findById(idLead).get().getName(), leadsRepository.findById(idLead).get().getPhoneNumber(), leadsRepository.findById(idLead).get().getEmail(), leadsRepository.findById(idLead).get().getCompanyName(), leadsRepository.findById(idLead).get().getSalesRepId());
                    //contact = new Contacts(leadsRepository.findById(idLead).get());
                    //contact = (Contacts) leadsRepository.findById(idLead).get();
                    //lead = leadsRepository.findById(idLead).get();
                    //contactsRepository.save(contact);
                    leadsRepository.delete(leadsRepository.getReferenceById(idLead));
                }
                while (!salir3) {
                    System.out.print("\nPlease enter the product type (1. HYBRID, 2. FLATBED, 3. BOX): ");
                    try {
                        product = Integer.parseInt(sn.next());
                        if (product < 0 || product > 3)
                            throw new InputMismatchException("\u001B[31m" + "El número debe estar entre 1 y 3" + "\u001B[0m");
                        else salir3 = true;
                    } catch (InputMismatchException | IllegalArgumentException e) {
                        System.out.println("\u001B[31m" + "Debes insertar un número entre 1 y 3" + "\u001B[0m");
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
                opportunities = new Opportunities(product_type, quantity, contact, OpportunitiesStatus.OPEN,null);
                System.out.println("accountsRepository.count() " + accountsRepository.count());
                if (accountsRepository.count() < 1) {
                    //createAccount(contact,opportunities);
                    System.out.print("Please enter the number of employees: ");
                    employeeCount = Integer.parseInt(sn.next());
                    System.out.print("Please enter the city: ");
                    city = sn.next();
                    System.out.print("Please enter the country: ");
                    country = sn.next();
                    Accounts accounts = new Accounts(AccountIndustry.OTHER, employeeCount, city, country);
                    accountsRepository.save(accounts);
                    contact.setAccount(accounts);
                    opportunities.setAccount(accounts);
                    contactsRepository.save(contact);
                } else {
                    boolean exit4 = true;
                    while (exit4) {
                        System.out.print("Would you like to create a new Account? (Y/N) ");
                        String newAccount = sn.nextLine();
                        if (newAccount.toUpperCase() == "Y") {
                            //createAccount(contact,opportunities);
                            System.out.print("Please enter the number of employees: ");
                            employeeCount = Integer.parseInt(sn.next());
                            System.out.print("Please enter the city: ");
                            city = sn.next();
                            System.out.print("Please enter the country: ");
                            country = sn.next();
                            Accounts accounts = new Accounts(AccountIndustry.OTHER, employeeCount, city, country);
                            accountsRepository.save(accounts);
                            contact.setAccount(accounts);
                            opportunities.setAccount(accounts);
                            contactsRepository.save(contact);
                            exit4=false;
                        } else if (newAccount.toUpperCase() == "N") {


                        } else {
                            System.out.println("You must select Y/N");
                        }

                    }
                }
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
        return opportunities;
    }

    private void createAccount(Contacts contact, Opportunities opportunities) {
        //industry - an Enum with options PRODUCE, ECOMMERCE, MANUFACTURING, MEDICAL, OTHER
        //*****************************************
        //***************************************** HACERLO
        int employeeCount;
        String city;
        String country;
        Scanner sn = new Scanner(System.in);
        System.out.print("Please enter the number of employees: ");
        employeeCount = Integer.parseInt(sn.next());
        System.out.print("Please enter the city: ");
        city = sn.next();
        System.out.print("Please enter the country: ");
        country = sn.next();
        //Accounts accounts = new Accounts(AccountIndustry.OTHER, employeeCount, city, country, List.of(contact), List.of(opportunities));
        Accounts accounts = new Accounts(AccountIndustry.OTHER, employeeCount, city, country);
        accountsRepository.save(accounts);
    }
    private void showOpportunities() {
        System.out.println("\u001B[34m" + "\nOpportunities\n" + opportunitiesRepository.findAll(Sort.by("id")) + "\u001B[0m");
    }

    private void updateOpportunity() {
    }

    private void showAccounts() {
        System.out.println("\u001B[36m" + "\nAccounts\n" + accountsRepository.findAll(Sort.by("id")) + "\u001B[0m");
    }

    private void showSalesRep() {
        System.out.println("\u001B[35m" + "\nSales Reps\n" + salesRepRepository.findAll(Sort.by("id")) + "\u001B[0m");
    }

    public Leads createLead() {
        String name, phone, email, company;
        Long salesRepId = null;
        SalesRep salesRep = null;
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
                salesRep = salesRepRepository.getReferenceById(salesRepId);
                exit2 = true;
            } catch (NumberFormatException | InputMismatchException | ConstraintViolationException |
                     DataIntegrityViolationException | IllegalStateException e) {
                System.out.println(
                        "\u001B[31m" +
                                "Solo números entre 1 y " + salesRepRepository.count() +
                                "\u001B[0m");
                sn.nextLine();
            }
        }
        return new Leads(name, phone, email, company, salesRep);
    }

}

/*

        System.out.println("11. Report Lead by SalesRep");
        System.out.println("12. Report Opportunity by SalesRep");
        System.out.println("13. Report CLOSED-WON by SalesRep");
        System.out.println("14. Report CLOSED-LOST by SalesRep");
        System.out.println("15. Report OPEN by SalesRep");
        System.out.println("16. Report Opportunity by the product");
        System.out.println("17. Report CLOSED-WON by the product");
        System.out.println("18. Report CLOSED-LOST by the product");
        System.out.println("19. Report OPEN by the product");
        System.out.println("20. Report Opportunity by Country");
        System.out.println("21. Report CLOSED-WON by Country");
        System.out.println("22. Report CLOSED-LOST by Country");
        System.out.println("23. Report OPEN by Country");
        System.out.println("24. Report Opportunity by City");
        System.out.println("25. Report CLOSED-WON by City");
        System.out.println("26. Report CLOSED-LOST by City");
        System.out.println("27. Report OPEN by City");
        System.out.println("28. Report Opportunity by Industry");
        System.out.println("29. Report CLOSED-WON by Industry");
        System.out.println("30. Report CLOSED-LOST by Industry");
        System.out.println("31. Report OPEN by Industry");
        System.out.println("32. Mean EmployeeCount");
        System.out.println("33. Median EmployeeCount");
        System.out.println("34. Max EmployeeCount");
        System.out.println("35. Min EmployeeCount");
        System.out.println("36. Mean Quantity");
        System.out.println("37. Median Quantity");
        System.out.println("38. Max Quantity");
        System.out.println("39. Min Quantity");
        System.out.println("40. Mean Opps per Account");
        System.out.println("41. Median Opps per Account");
        System.out.println("42. Max Opps per Account");
        System.out.println("43. Min Opps per Account");
 */

/*
        System.out.println("\u001B[35m" + "\n                                   Reports by SalesRep" + "\u001B[0m");
        System.out.println("11. Leads           12. Opportunities   13. CLOSED-WON      14. CLOSED-LOST     15. OPEN");

        System.out.println("\u001B[35m" + "\n                                   Reports by Product" + "\u001B[0m");
        System.out.println("16. Opportunities   17. CLOSED-WON      18. CLOSED-LOST     19. OPEN");

        System.out.println("\u001B[35m" + "\n                                   Reports by Country" + "\u001B[0m");
        System.out.println("20. Opportunities   21. CLOSED-WON      22. CLOSED-LOST     23. OPEN");

        System.out.println("\u001B[35m" + "\n                                   Reports by City" + "\u001B[0m");
        System.out.println("24. Opportunities   25. CLOSED-WON      26. CLOSED-LOST     27. OPEN");

        System.out.println("\u001B[35m" + "\n                                   Reports by Industry" + "\u001B[0m");
        System.out.println("28. Opportunities   29. CLOSED-WON      30. CLOSED-LOST     31. OPEN");

        System.out.println("\u001B[35m" + "\n                                   Reports of Employee Count" + "\u001B[0m");
        System.out.println("32. Mean           33. MEDIAN           34. MAX              35. MIN");

        System.out.println("\u001B[35m" + "\n                                   Reports of Quantity" + "\u001B[0m");
        System.out.println("36. Mean           37. MEDIAN           38. MAX              39. MIN");

        System.out.println("\u001B[35m" + "\n                                   Reports of Opps per Account" + "\u001B[0m");
        System.out.println("40. Mean           41. MEDIAN           42. MAX              43. MIN");

 */