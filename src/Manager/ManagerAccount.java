package Manager;

import Models.Account;
import Models.RoleType;
import Validate.ValidateNhanVien;
import io.ReaderAndWriterNhanVien;

import java.util.ArrayList;
import java.util.Scanner;

public class ManagerAccount {
    ReaderAndWriterNhanVien<Account> readerAndWriterNhanVien = new ReaderAndWriterNhanVien<>();
    Scanner scanner = new Scanner(System.in);

    public static Account account;
    public static ArrayList<Account> accounts = new ArrayList<>();

    ValidateNhanVien validateNhanVien = new ValidateNhanVien();

    {
        accounts = readerAndWriterNhanVien.reader("D:\\codegym\\Module2\\caseStudy2\\src\\Data\\Account.txt");
        if (accounts.size() == 0) {
            accounts.add(new Account("admin", "admin", "admin"));
        }
    }

    public void menuLogin() {
        System.out.println("1. Dang nhap ");
        System.out.println("2. Dang ky ");
        int choice1 = validateNhanVien.choiceAdmin("option Login");
        switch (choice1) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
        }
    }

    public boolean login() {
        System.out.println("Nhap tai khoan :");
        String user = scanner.nextLine();
        System.out.println("Nhap mat khau :");
        String pass = scanner.nextLine();

        for (Account acc : accounts) {
            if (acc.getUserName().equals(user) && acc.getPass().equals(pass)) {
                ManagerAccount.account = acc;
                return true;
            }
        }
        System.out.println(" Sai tai khoan hoac mat khau ");
        return false;
    }

    public void register() {
        String user = null;
        while (true) {
            System.out.println("Nhap tai khoan : ");
            user = scanner.nextLine();
            if (checkUserName(user)) {
                break;
            }
        }
        System.out.println("Nhap mat khau : ");
        String pass = scanner.nextLine();

        System.out.println("Vui lòng chọn role: ");
        System.out.println("1: admin");
        System.out.println("2: user");
        String role = scanner.nextLine();
        String roleName = null;
        if (role.equalsIgnoreCase("1"))
            roleName = RoleType.ADMIN.getValue();
        else if (role.equalsIgnoreCase("2"))
            roleName = RoleType.USER.getValue();
        else
            System.out.println("Lỗi! Vui lòng nhập đúng giá trị role là 1 hoặc 2");

        accounts.add(new Account(user, pass, roleName));
        readerAndWriterNhanVien.Write(accounts, "D:\\codegym\\Module2\\caseStudy2\\src\\Data\\Account.txt");
    }

    public boolean checkUserName(String userName) {
        for (Account acc : accounts) {
            if (acc.getUserName().equals(userName)) {
                ManagerAccount.account = acc;
                return false;
            }
        }
        return true;
    }

}
