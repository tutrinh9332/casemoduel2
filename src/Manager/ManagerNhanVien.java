package Manager;

import Models.Account;
import Models.FullTime;
import Models.NhanVien;
import Models.PartTime;
import Validate.ValidateNhanVien;
import io.ReaderAndWriterNhanVien;

import java.util.ArrayList;
import java.util.Scanner;

public class ManagerNhanVien {
    ArrayList<NhanVien> nhanViens;
    Scanner scanner = new Scanner(System.in);

    ValidateNhanVien validateNhanVien = new ValidateNhanVien();

    ReaderAndWriterNhanVien<NhanVien> readerAndWriterNhanVien = new ReaderAndWriterNhanVien<>();
    ReaderAndWriterNhanVien<Account> readerAndWriterAccount = new ReaderAndWriterNhanVien<>();

    ArrayList<Account> accounts = readerAndWriterAccount.reader("D:\\codegym\\Module2\\caseStudy2\\src\\Data\\Account.txt");

    {
        nhanViens = readerAndWriterNhanVien.reader("D:\\codegym\\Module2\\caseStudy2\\src\\Data\\NhanVien.txt");
    }

    public void menuUser() {
        System.out.println("Xin chao " + ManagerAccount.account.getUserName());
        System.out.println("1. Tim nhan vien theo ten ");
        System.out.println("2. Show nhan vien ");
        System.out.println("3. Dang xuat ");
        int choice = validateNhanVien.choiceUser("option");
        switch (choice) {
            case 1:
                findNVByName();
                break;
            case 2:
                showNV();
                break;
            case 3:
                logOutNV();
                break;
        }
    }

    public void menuAdmin() {
        System.out.println("Xin chao " + ManagerAccount.account.getUserName());
        System.out.println("-------MENU-------");
        System.out.println("1. Them nhan vien ");
        System.out.println("2. Tim nhan vien theo ten");
        System.out.println("3. Kiem tra trang thai nhan vien ");
        System.out.println("4. Sua thong tin nhan vien ");
        System.out.println("5. Thay doi trang thai nhan vien ");
        System.out.println("6. Xoa nhan vien ");
        System.out.println("7. Thong tin tai khoan ");
        System.out.println("8. Tinh luong nhan vien");
        System.out.println("9. Read on file ");
        System.out.println("10. Write on file ");
        System.out.println("11. Show nhan vien ");
        System.out.println("12. Dang xuat ");
        System.out.println("13. Exit ");

        int choice = validateNhanVien.choiceAdmin("option");

        switch (choice) {
            case 1:
                addNV();
                break;
            case 2:
                findNVByName();
                break;
            case 3:
                checkStatusNV();
                break;
            case 4:
                editNV();
                break;
            case 5:
                changeStatusNV();
                break;
            case 6:
                deleteNV();
                break;
            case 7:
                showAccountNV();
                break;
            case 8:
                showSalary();
                break;
            case 9:
                readFileNV();
                break;
            case 10:
                writeFileNV();
                break;
            case 11:
                showNV();
                break;
            case 12:
                logOutNV();
                break;
            case 13:
                quitNV();
                break;
            default:
                break;


        }
    }

    public void addNV() {
        System.out.println("1. Them nhan vien full time ");
        System.out.println("2. them nhan vien part time ");
        int choiceKind = Integer.parseInt(scanner.nextLine());
        switch (choiceKind) {
            case 1:
                int id1 = validateNhanVien.validateIdNV(nhanViens);
                NhanVien nhanVienFullTime = createNhanVien(id1, true);
                nhanViens.add(nhanVienFullTime);
                readerAndWriterNhanVien.Write(nhanViens, "D:\\codegym\\Module2\\caseStudy2\\src\\Data\\NhanVien.txt");
                break;
            case 2:
                int id2 = validateNhanVien.validateIdNV(nhanViens);
                NhanVien nhanVienPartTime = createNhanVien(id2, false);
                nhanViens.add(nhanVienPartTime);
                readerAndWriterNhanVien.Write(nhanViens, "D:\\codegym\\Module2\\caseStudy2\\src\\Data\\NhanVien.txt");
                break;
            default:
                break;
        }
        System.out.println("Them thanh cong");
    }

    public NhanVien createNhanVien(int id, boolean isFullTime) {
        String name = validateNhanVien.validateString("ho ten");
        int age = validateNhanVien.validateAge();
        String gender = validateNhanVien.validateString("gioi tinh");
        String phone = validateNhanVien.validatePhone();
        String address = validateNhanVien.validateString("dia chi");
        if (isFullTime) {
            System.out.println("Nhap so buoi lam fulltime");
            int heSo = Integer.parseInt(scanner.nextLine());
            return new FullTime(id, name, age, gender, phone, address, heSo, true);
        } else {
            System.out.println("Nhap so buoi lam parttime ");
            int number = Integer.parseInt(scanner.nextLine());
            return new PartTime(id, name, age, gender, phone, address, number, true);
        }
    }

    public void findNVByName() {
        String name = validateNhanVien.validateString("Ten can tim ");
        for (NhanVien nv : nhanViens) {
            if (nv.getName().equals(name)) {
                System.out.println(nv);
                break;
            }
            System.out.println("Nhan vien: " + name + " ko ton tai trong danh sach: " + nhanViens.toString());
        }

    }

    public void checkStatusNV() {
        System.out.println("Nhan vien dang lam : ");
        for (NhanVien nv : nhanViens) {
            if (!nv.isStatus()) {
                System.out.println(nv.getName());
            }
        }
    }

    public void editNV() {
        System.out.println("Nhap id nhan vien muon sua ");
        int id = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < nhanViens.size(); i++) {
            if (nhanViens.get(i).getIdNV() == id) {
                nhanViens.set(i, createNhanVien(nhanViens.get(i).getIdNV(), nhanViens.get(i) instanceof FullTime));
            }
        }
    }

    public void changeStatusNV() {
        int id = validateNhanVien.checkInt("id nhan vien can sua trang thai");
        for (int i = 0; i < nhanViens.size(); i++) {
            if (nhanViens.get(i).isStatus()) {
                nhanViens.get(i).setStatus(false);
            } else nhanViens.get(i).setStatus(true);
        }
    }

    public void deleteNV() {
        int id = validateNhanVien.validateDelNV();
        for (int i = 0; i < nhanViens.size(); i++) {
            if (nhanViens.get(i).getIdNV() == id) {
                nhanViens.remove(nhanViens.get(i));
            }
        }
        System.out.println("Xoa thanh cong ");
    }

    public void showAccountNV() {
        for (Account acc : accounts) {
            System.out.println(acc);
        }
    }

    public void showSalary() {
        System.out.println("1. FullTime");
        System.out.println("2. PartTime");
        int choice2 = Integer.parseInt(scanner.nextLine());
        switch (choice2) {
            case 1:
                for (NhanVien nv : nhanViens) {
                    if (nv instanceof FullTime)
                        System.out.println(nv.getName() + "=" + nv.getSalary());
                    else
                        System.out.println("Lỗi! Nhân viên: " + nv.getName() + " là nhân viên PartTime.");
                }
                break;
            case 2:
                for (NhanVien item : nhanViens) {
                    if (item instanceof PartTime)
                        System.out.println("Nhân viên: " + item.getName() + " có lương: " + item.getSalary());
                    else
                        System.out.println("Lỗi! Nhân viên: " + item.getName() + " là nhân viên FullTime.");
                }
        }
    }

    public void readFileNV() {
        nhanViens = readerAndWriterNhanVien.reader("D:\\codegym\\Module2\\caseStudy2\\src\\Data\\NhanVien.txt");
        System.out.println("Doc thanh cong ");
    }

    public void writeFileNV() {
        readerAndWriterNhanVien.Write(nhanViens, "D:\\codegym\\Module2\\caseStudy2\\src\\Data\\NhanVien.txt");
        System.out.println("Ghi thanh cong");
    }

    public void showNV() {
        for (NhanVien nv : nhanViens) {
            System.out.println(nv);
        }
    }

    public void logOutNV() {
        ManagerAccount.account = null;
    }

    public void quitNV() {
        System.exit(0);
    }

}
