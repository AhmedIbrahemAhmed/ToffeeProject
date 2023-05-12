package Regitretion;

import java.util.Scanner;
import User.*;
import ClientDB.ClientDB;


public class Login{
    public Client clientLogin(){
        Client client=new Client();
        ClientDB clientDB=new ClientDB();
        
        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            client=clientDB.loadClient(username);
            if(client != null &&client.getPassword().equals(password)){
                return client;
            }
            else {
                System.out.println("Wrong username or password please try again ");
            }
        }
    }
    public Admin adminLogin(){
        Admin admin=new Admin();
        AdminDB adminDB=new AdminDB();
        while(true){
            Scanner scanner = new Scanner(System.in);
        
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            admin=adminDB.loadAdmin(username,password);
            if(admin!=null){
                return admin;
            }
            else {
                System.out.println("Wrong username or password please try again ");
            }
        }
    }
    public Owner ownerLogin(){
        Owner owner=new Owner();
        OwnerDB ownerDB=new OwnerDB();
        
        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            owner=ownerDB.loadOwner(username,password);
            if(owner!=null){
                return owner;
            }
            else {
                System.out.println("Wrong username or password please try again ");
            }
        }
    }

}