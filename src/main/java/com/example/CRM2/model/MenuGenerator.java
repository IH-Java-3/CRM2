package com.example.CRM2.model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuGenerator {
    public static void createMenu(List<ArrayList> menuList) {
        int option;
        if (menuList.size() > 0) {
            System.out.println("\n");
            for (int i = 0; i < menuList.size();i++) {
                System.out.println(menuList.get(i).get(1));
            }

            Scanner sn = new Scanner(System.in);
            System.out.print("Escribe una de las opciones: ");
            try {
                option = sn.nextInt();
                for (int i = 0; i < menuList.size(); i++) {
                    if (option == i) {
                        menuList.get(i).get(2);
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("""
                    \u001B[31m
                    Debes insertar un número
                    \u001B[0m""");
                sn.next();
            }
        }
    }
}
