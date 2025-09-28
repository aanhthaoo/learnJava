package studentList;
import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


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
        System.out.print("Nhập tên: "); String name = sc.next();

        LocalDate birth = null;
        do {
            System.out.print("Nhập ngày sinh (dd/MM/yyyy): ");
            String birthStr = sc.next();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
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

        return new Student (id, name, birth, gender, major, score, enrolled);
    }

    private static void addStudent(){

        Student stu = enterInfor();
        boolean mark = true;
        if (!studentList.isEmpty())
            for (Student s : studentList) {
                if (s.getId().equals(stu.getId())) {
                    mark = false;
                    System.out.println("ID này đã tồn tại");
                    break;
                }
            }
        if (mark) {studentList.add(stu);}
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
                    student.setName(sc.next());
                    break;
                case 2:
                    LocalDate birth = null;
                    do {
                        System.out.print("Nhập ngày sinh mới (dd/MM/yyyy): ");
                        String birthStr = sc.next();
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
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
                    student.setName(sc.next());

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
                    System.out.println("Hoàn thành chỉnh sửa.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        } while (choice != 0);

        System.out.println("Chỉnh sửa thành công!");
    }

    private static void editStudent(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập id cần tìm: "); String id = sc.next();

        if (!studentList.isEmpty())
            for (Student s : studentList) {
                if (s.getId().equals(id)) {
                    editInfor(s);
                    break;
                }
            }
        else{
            System.out.print("ID không tồn tại");
        }
    }

    private static void deleteStudent(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập ID cần tìm: "); String id = sc.next();

        boolean mark = false;
        if (!studentList.isEmpty())
            for (Student s : studentList) {
                if (s.getId().equals(id)) {
                    mark = true;
                    studentList.remove(s);
                    System.out.print("Xóa sinh viên thành công");
                    break;
                }
                if (!mark){
                    System.out.print("Không có sinh viên tương ứng");
                }
            }
        else{
            System.out.print("List rỗng");
        }
    }

    private static void showStudent(){
        System.out.println("MSSV \t Họ tên \t Ngày sinh \t Giới tính \t Ngành \t Điểm trúng tuyển \t Trạng thái nhập học");

        if (!studentList.isEmpty())
            for (Student s : studentList) {
                s.displayInfor();
            }
        else{
            System.out.print("List rỗng");
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

                    boolean mark = false;
                    if (!studentList.isEmpty())
                        for (Student s : studentList) {
                            if (s.getId().equals(id)) {
                                mark = true;
                                s.displayInfor();
                                break;
                            }
                            if (!mark){
                                System.out.println("Không có sinh viên tương ứng!");
                            }
                        }
                    else {
                        System.out.print("List rỗng");
                    }
                    break;
                case 2:
                    System.out.print("Nhập tên cần tìm:"); String name = sc.next();

                    boolean exist = false;
                    if (!studentList.isEmpty())
                        for (Student s : studentList) {
                            if (s.getName().equals(name)) {
                                exist = true;
                                s.displayInfor();
                            }
                            if (!exist){
                                System.out.println("Không có sinh viên tương ứng!");
                            }
                        }
                    else {
                        System.out.print("List rỗng");
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

        boolean mark  = false;
        if (!studentList.isEmpty())
            for (Student s : studentList) {
                if (s.getId().equals(id)) {
                    mark = true;
                    System.out.print("Sinh viên đã nhập học chưa? (y/n): ");
                    String statusInput = sc.next();
                    String enrolledStatus = statusInput.equalsIgnoreCase("y") ? "Đã nhập học" : "Chưa nhập học";
                    s.setEnrolled(enrolledStatus);

                    System.out.print("Đánh dấu nhập học thành công");
                    break;
                }
                if (!mark){
                    System.out.println("Không có sinh viên tương ứng");
                }
            }
        else{
            System.out.print("List rỗng");
        }
    }

    private static void writeFile(){
        Scanner  sc = new Scanner(System.in);
        System.out.print("Nhập tên file đầu ra: "); String file =  sc.next();

        try {
            FileWriter writer = new FileWriter("src/" + file);
            if (!studentList.isEmpty()){
                for (Student s:studentList){
                    writer.write(s.line() + "\n");
                }
                writer.close();
                }
            else{
                System.out.print("List rỗng");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFile(){
        try {
            Set<String> existedID = new HashSet<>();
            for (Student s : studentList) {
                existedID.add(s.getId());
            }

            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập file cần đọc:");
            String file = sc.next();

            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] part = line.split(",");
                if (part.length == 7) {
                    String id = part[0].trim();
                    if (!existedID.contains(id)) {
                        String name = part[1].trim();

                        String birthStr = part[2].trim();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate birth = LocalDate.parse(birthStr, formatter);

                        String gender = part[3].trim();
                        String major = part[4].trim();
                        double score = Double.parseDouble(part[5].trim());
                        String enrolled = part[6].trim();

                        Student student = new Student(id, name, birth, gender, major, score, enrolled);
                        studentList.add(student);
                        existedID.add(id);
                    } else {
                        System.out.println("MSV" + id + "đã có trong danh sách");
                    }
                } else {
                    System.out.println("Dòng không hợp lệ");
                }
            }

            reader.close();
            System.out.println("Đọc file và thêm vào list thành công!");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

