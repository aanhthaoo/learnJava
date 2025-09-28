package studentList;

import java.time.LocalDate;

public class Student {
    private String id;
    private String name;
    private LocalDate birth;
    private String gender;
    private String major;
    private double score;
    private String enrolled;

    public Student (String id, String name,  LocalDate birth, String gender, String major, double score, String enrolled) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.major = major;
        this.score = score;
        this.enrolled = enrolled;
    }

    public String getId() {return id;}
    public String getName() { return name;}
    public LocalDate getBirth() {return birth;}
    public String getGender() {return gender;}
    public String getMajor() {return major;}
    public double getScore() {return score;}
    public String getEnrolled() {return enrolled;}

    public void setId(String id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setBirth(LocalDate birth) {this.birth = birth;}
    public void setGender(String gender) {this.gender = gender;}
    public void setMajor(String major) {this.major = major;}
    public void setScore(double score) {this.score = score;}
    public void setEnrolled(String enrolled) {this.enrolled = enrolled;}

    public void displayInfor(){
        System.out.println(id + "\t" + name + "\t" + birth + "\t" + gender + "\t" + major + "\t" + score + "\t" + enrolled );
    }

    public String line(){
        return id + ", " + name + ", " + birth + ", " + gender + ", " + major + ", " + score + ", " + enrolled;
    }
    public static void main(String[] args){
        //System.out.println("MSSV \t Họ tên \t Ngày sinh \t Giới tính \t Ngành \t Điểm trúng tuyển \t Trạng thái nhập học");
    }

}
