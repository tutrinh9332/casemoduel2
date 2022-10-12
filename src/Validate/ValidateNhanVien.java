package Validate;

import Models.NhanVien;

import java.util.List;
import java.util.Scanner;

public class ValidateNhanVien {
    Scanner scanner = new Scanner(System.in);

    public int checkInt(String name) {
        while (true) {
            try {
                System.out.println("Nhap " + name);
                int result = Integer.parseInt(scanner.nextLine());
                return result;
            } catch (Exception e) {
                System.out.println(" Ban da nhap sai cu phap , vui long nhap lai");
                checkInt(name);
            }
        }
    }

    public int choiceAdmin(String option) {
        while (true) {
            try {
                System.out.println("Nhap " + option);
                int button = Integer.parseInt(scanner.nextLine());
                if (button > 0 && button < 14) {
                    return button;
                } else {
                    System.err.println("Vui long nhap lenh hop le");
                    return choiceAdmin(option);
                }
            } catch (Exception e) {
                System.out.println("Nhap sai cu phap , Vui long nhap lai.");
                checkInt(option);

            }
        }
    }

    public int choiceUser(String option) {
        while (true) {
            try {
                System.out.println("Nhap " + option);
                int button = Integer.parseInt(scanner.nextLine());
                if (button > 0 && button < 4) {
                    return button;
                } else {
                    System.err.println("Vui long nhap lenh hop le . ");
                    return choiceUser(option);
                }
            } catch (Exception e) {
                System.out.println("Nhap sai cu phap , vui long nhap lai .");
                checkInt(option);
            }
        }
    }

    public int validateIdNV(List<NhanVien> nhanViens) {
        while (true) {
            try {
                System.out.println("Nhap id");
                int id = Integer.parseInt(scanner.nextLine());
                if (getIndexId(id, nhanViens) != -1) {
                    throw new Exception();
                }
                return id;
            } catch (Exception e) {
                System.err.println("Nhap sai KDL, vui long nhap id bang so. ");
            }
        }
    }

    public int getIndexId(int id, List<NhanVien> nhanViens) {
        for (int i = 0; i < nhanViens.size(); i++) {
            if (nhanViens.get(i).getIdNV() == id) {
                return i;
            }
        }
        return -1;
    }

    public String validateString(String name) {
        while (true) {
            System.out.println("Nhap " + name);
            String str = scanner.nextLine();
            if (str.equals("")) {
                System.err.println("Khong duoc de trong. ");
                continue;
            } else {
                return str;
            }
        }
    }

    public int validateAge() {
        while (true) {
            try {
                System.out.println("Nhap tuoi ");
                int age = Integer.parseInt(scanner.nextLine());
                return age;
            } catch (Exception e) {
                System.out.println("Nhap sai KDL, vui long nhap tuoi bang so. ");
            }
        }
    }

    public String validatePhone() {
        while (true) {
            try {
                System.out.println("Nhap SDT : ");
                String phone = scanner.nextLine();
                return phone;
            } catch (Exception e) {
                System.out.println("Nhap sai KDL , hay nhap SDT bang so ");
            }
        }
    }

    public int validateDelNV() {
        while (true) {
            try {
                System.out.println("Nhap id ");
                int id = Integer.parseInt(scanner.nextLine());
                return id;
            } catch (Exception e) {
                System.err.println("Nhap sai KDL ");
            }
        }
    }

}
