package Regitretion;

import java.util.Scanner;
import User.*;
import ClientDB.ClientDB;
import OTP;

public class SignUp{
    void signup(){
        ClientDB clientDB = new ClientDB();
        Client checkClient = new Client();
        OTP otp = new OTP();
        String userName;
        int code;

       while(true){
           Scanner scanner = new Scanner(System.in);
           System.out.print("Enter your username: ");
           userName = scanner.nextLine();
           scanner.close();

           checkClient=clientDB.loadClient(userName);

           if(checkClient==null){
               break;
           }
           else {
               System.out.println("this user name is already user please try another one. ");
           }
       }

       Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        scanner.close();

        code=otp.createOTP();

        while(true){
            otp.sendOTP(code,email);

            System.out.print("Enter your OTP: ");
            int userOTP = scanner.nextInt();

            if(userOTP==code){
                Client client = new Client(userName,password,email);
                clientDB.saveClient(client);
                return;
            }
            else {
                System.out.println("wrong OTP please try again ");
            }
        }
    }
}