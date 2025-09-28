package studentDatabase;
import studentList.Student;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDA {
    public boolean addStudent(Student s) {
        String sql = "INSERT INTO information (id, name, birth, gender, major, score, enrolled) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            p.setString(1, s.getId());
            p.setString(2, s.getName());
            if (s.getBirth() != null) p.setDate(3, Date.valueOf(s.getBirth()));
            else p.setDate(3, null);
            p.setString(4, s.getGender());
            p.setString(5, s.getMajor());
            p.setDouble(6, s.getScore());
            p.setString(7, s.getEnrolled());

            return p.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateStudent(Student s) {
        String sql = "UPDATE information SET name=?, birth=?, gender=?, major=?, score=?, enrolled=? WHERE id=?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            p.setString(1, s.getName());
            if (s.getBirth() != null) p.setDate(2, Date.valueOf(s.getBirth()));
            else p.setDate(2, null);
            p.setString(3, s.getGender());
            p.setString(4, s.getMajor());
            p.setDouble(5, s.getScore());
            p.setString(6, s.getEnrolled());
            p.setString(7, s.getId());

            return p.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteStudent(String id) {
        String sql = "DELETE FROM information WHERE id=?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setString(1, id);
            return p.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Student findById(String id) {
        String sql = "SELECT * FROM information WHERE id=?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setString(1, id);
            try (ResultSet rs = p.executeQuery()) {
                if (rs.next()) return mapRowToStudent(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public List<Student> findByName(String name) {
        String sql = "SELECT * FROM information WHERE name LIKE ?";
        List<Student> result = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setString(1, "%" + name + "%");
            try (ResultSet rs = p.executeQuery()) {
                while (rs.next()) result.add(mapRowToStudent(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return result;
    }

    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM information ORDER BY id";
        List<Student> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             Statement s = conn.createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            while (rs.next()) list.add(mapRowToStudent(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public boolean markStudent(String id, String enrolledStatus) {
        String sql = "UPDATE information SET enrolled=? WHERE id=?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setString(1, enrolledStatus);
            p.setString(2, id);
            return p.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    private Student mapRowToStudent(ResultSet rs) throws SQLException {
        String id = rs.getString("id");
        String name = rs.getString("name");
        Date d = rs.getDate("birth");
        LocalDate birth = (d != null) ? d.toLocalDate() : null;
        String gender = rs.getString("gender");
        String major = rs.getString("major");
        double score = rs.getDouble("score");
        String enrolled = rs.getString("enrolled");
        return new Student(id, name, birth, gender, major, score, enrolled);
    }
}
