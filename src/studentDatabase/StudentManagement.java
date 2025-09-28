package studentDatabase;

import studentList.Student;
import studentDatabase.StudentDA;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class StudentManagement {
    public static List<Student> studentList = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("====== QUẢN LÝ SINH VIÊN NHẬP HỌC 2025 ======");
            System.out.println("1. Thêm sinh viên mới");
            System.out.println("2. Sửa thông tin sinh viên");
            System.out.println("3. Xóa sinh viên");
            System.out.println("4. Hiển thị danh sách sinh viên");
            System.out.println("5. Tìm kiếm sinh viên");
            System.out.println("6. Đánh dấu sinh viên đã nhập học");
            System.out.println("7. Đọc dữ liệu sinh viên từ file CSV");
            System.out.println("8. Ghi danh sách sinh viên ra file CSV");
            System.out.println("9. Thoát");
            System.out.print("Chọn chức năng (1-9): ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    editStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    showStudent();
                    break;
                case 5:
                    searchStudent();
                    break;
                case 6:
                    markStudent();
                    break;
                case 7:
                    readFile();
                    break;
                case 8:
                    writeFile();
                    break;
                case 9:
                    System.out.println("Thoát quản lý");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập 1-9!");
            }

            System.out.println();
        } while (choice != 9);

        sc.close();
    }

    private static Student enterInfor(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập id: "); String id  = sc.next();
        boolean mark = true;
        if (!studentList.isEmpty()){
            while (mark){
                for (Student s : studentList) {
                    if (s.getId().equals(id)) {
                        mark = false;
                        System.out.println("ID này đã tồn tại");
                        break;
                    }
                }
            }
        }
        System.out.print("Nhập tên: "); sc.nextLine(); String name = sc.nextLine();

        LocalDate birth = null;
        do {
            System.out.print("Nhập ngày sinh (yyyy-MM-dd): ");
            String birthStr = sc.next();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                birth = LocalDate.parse(birthStr, formatter);
            } catch (Exception e) {
                System.out.println("Ngày không hợp lệ, vui lòng nhập lại!");
            }
        } while(birth == null);

        System.out.print("Nhập giới tính: "); String gender = sc.next();
        System.out.print("Nhập ngành học: "); String major = sc.next();
        System.out.print("Nhập điểm: "); double score = sc.nextDouble();

        System.out.print("Sinh viên đã nhập học chưa? (y/n): ");
        String status = sc.next();
        String enrolled = status.equalsIgnoreCase("y") ? "Đã nhập học" : "Chưa nhập học";

        return new Student(id, name, birth, gender, major, score, enrolled);
    }

    private static void addStudent(){

        Student stu = enterInfor();
        StudentDA sda =  new StudentDA();
        if(sda.findById(stu.getId()) != null){
            System.out.println("Id của sinh viên đã tồn tại");
            return;
        }
        boolean ok = sda.addStudent(stu);
        System.out.println(ok ? "Thêm sinh viên thành công" : "Thêm sinh viên thất bại");
    }

    private static void editInfor(Student student){
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Chỉnh sửa thông tin sinh viên ---");
            System.out.println("1. Sửa tên");
            System.out.println("2. Sửa ngày sinh");
            System.out.println("3. Sửa ngành học");
            System.out.println("4. Sửa giới tính");
            System.out.println("5. Sửa điểm");
            System.out.println("6. Sửa trạng thái nhập học");
            System.out.println("7. Sửa tất cả");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng (0-7): ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Nhập tên mới: ");
                    sc.nextLine();
                    student.setName(sc.nextLine());
                    break;
                case 2:
                    LocalDate birth = null;
                    do {
                        System.out.print("Nhập ngày sinh mới (yyyy-MM-dd): ");
                        String birthStr = sc.next();
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            birth = LocalDate.parse(birthStr, formatter);
                            student.setBirth(birth);
                        } catch (Exception e) {
                            System.out.println("Ngày không hợp lệ, vui lòng nhập lại!");
                        }
                    } while(birth == null);
                    break;
                case 3:
                    System.out.print("Nhập ngành học mới: ");
                    student.setMajor(sc.next());
                    break;
                case 4:
                    System.out.print("Nhập giới tính mới: ");
                    student.setGender(sc.next());
                    break;
                case 5:
                    System.out.print("Nhập điểm mới: ");
                    student.setScore(sc.nextDouble());
                    break;
                case 6:
                    System.out.print("Sinh viên đã nhập học chưa? (y/n): ");
                    String status = sc.next();
                    String enrolled = status.equalsIgnoreCase("y") ? "Đã nhập học" : "Chưa nhập học";
                    student.setEnrolled(enrolled);
                case 7:
                    System.out.print("Nhập tên mới: ");
                    sc.nextLine();
                    student.setName(sc.nextLine());

                    LocalDate birthday = null;
                    do {
                        System.out.print("Nhập ngày sinh mới (dd/MM/yyyy): ");
                        String birthStr = sc.next();
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            birthday = LocalDate.parse(birthStr, formatter);
                            student.setBirth(birthday);
                        } catch (Exception e) {
                            System.out.println("Ngày không hợp lệ, vui lòng nhập lại!");
                        }
                    } while(birthday == null);

                    System.out.print("Nhập ngành học mới: ");
                    student.setMajor(sc.next());

                    System.out.print("Nhập giới tính mới: ");
                    student.setGender(sc.next());

                    System.out.print("Nhập điểm mới: ");
                    student.setScore(sc.nextDouble());

                    System.out.print("Sinh viên đã nhập học chưa? (y/n): ");
                    String statusInput = sc.next();
                    String enrolledStatus = statusInput.equalsIgnoreCase("y") ? "Đã nhập học" : "Chưa nhập học";
                    student.setEnrolled(enrolledStatus);
                    break;
                case 0:
                    System.out.println("Thoát chỉnh sửa.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        } while (choice != 0);
    }

    private static void editStudent(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập id cần tìm: "); String id = sc.next();
        StudentDA sda =  new StudentDA();
        Student stu = sda.findById(id);
        if (stu == null) {
            System.out.println("ID không tồn tại");
            return;
        }
        editInfor(stu);
        boolean ok = sda.updateStudent(stu);
        System.out.println(ok ? "Cập nhật thành công" : "Cập nhật thất bại");
    }

    private static void deleteStudent(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập ID cần tìm: "); String id = sc.next();
        StudentDA sda =  new StudentDA();
        Student stu = sda.findById(id);
        if (stu == null) {
            System.out.println("Không tìm thấy sinh viên.");
            return;
        }
        boolean ok = sda.deleteStudent(id);
        System.out.println(ok ? "Cập nhật thành công" : "Cập nhật thất bại");
    }

    private static void showStudent(){
        StudentDA sda =  new StudentDA();
        List<Student> studentList = sda.getAllStudents();
        if (!studentList.isEmpty()) {
            System.out.println("MSSV \t Họ tên \t Ngày sinh \t Giới tính \t Ngành \t Điểm trúng tuyển \t Trạng thái nhập học");
            for (Student s : studentList) {
                s.displayInfor();
            }
        }
        else{
            System.out.print("Danh sách trống.");
        }
    }

    private static void searchStudent(){
        Scanner sc = new Scanner(System.in);
        int choice;
        do {

            System.out.println("\n--- Tìm kiếm sinh viên ---");
            System.out.println("1. Tìm bằng MSV");
            System.out.println("2. Tìm bằng tên");
            System.out.println("0. Thoát");
            System.out.println("Lựa chọn (0-2):"); choice = sc.nextInt();

            switch (choice){
                case 1:
                    System.out.print("Nhập MSV cần tìm:"); String id = sc.next();
                    StudentDA  sda1 =  new StudentDA();
                    Student stu = sda1.findById(id);
                    if (stu == null) {
                        System.out.println("Không tìm thấy sinh viên.");
                        return;
                    }
                    stu.displayInfor();
                    break;
                case 2:
                    System.out.print("Nhập tên cần tìm:"); sc.nextLine(); String name = sc.nextLine();
                    StudentDA sda2 = new StudentDA();
                    List<Student> nameList = sda2.findByName(name);
                    if (nameList.isEmpty()) {
                        System.out.println("Không tìm thấy sinh viên.");
                        return;
                    }else{
                        System.out.println("MSSV \t Họ tên \t Ngày sinh \t Giới tính \t Ngành \t Điểm trúng tuyển \t Trạng thái nhập học");
                        for (Student s : nameList) {
                            s.displayInfor();
                        }
                    }
                    break;
                case 0:
                    System.out.println("Thoát tìm kiếm");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
                    break;
            }
        }while(choice != 0);
    }

    private static void markStudent(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập ID cần tìm: "); String id = sc.next();
        StudentDA  sda =  new StudentDA();
        Student stu = sda.findById(id);
        if (stu == null) {
            System.out.println("Không tìm thấy sinh viên");
            return;
        }
        System.out.print("Sinh viên đã nhập học chưa? (y/n): ");
        String statusInput = sc.next();
        String enrolledStatus = statusInput.equalsIgnoreCase("y") ? "Đã nhập học" : "Chưa nhập học";
        boolean ok = sda.markStudent(id, enrolledStatus);
        System.out.println(ok ? "Đã cập nhật trạng thái" : "Cập nhật thất bại");
    }

    private static void writeFile(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên file đầu ra: ");
        String out = sc.next();

        try (FileWriter writer = new FileWriter(out)) {
            StudentDA sda = new StudentDA();
            List<Student> list = sda.getAllStudents();
            for (Student s : list) {
                writer.write(s.line() + "\n");
            }
            System.out.println("Ghi file xong");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFile() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập đường dẫn file cần đọc: ");
            String path = sc.next();

            File f = new File(path);
            Scanner reader = new Scanner(f);
            StudentDA sda = new StudentDA();

            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] part = line.split(",");
                if (part.length == 7) {
                    String id = part[0].trim();
                    if (sda.findById(id) != null) {
                        System.out.println("MSV " + id + " đã có trong DB");
                        continue;
                    }
                    String name = part[1].trim();
                    LocalDate birth = LocalDate.parse(part[2].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    String gender = part[3].trim();
                    String major = part[4].trim();
                    double score = Double.parseDouble(part[5].trim());
                    String enrolled = part[6].trim();

                    Student s = new Student(id, name, birth, gender, major, score, enrolled);
                    sda.addStudent(s);
                } else {
                    System.out.println("Dòng không hợp lệ");
                }
            }
            reader.close();
            System.out.println("Đọc file xong");
        } catch (FileNotFoundException e) {
            System.out.println("File không tìm thấy");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//thêm thông báo chỉnh sửa xong  từng cái
