import java.sql.*;
import java.util.*;

public class DatabaseManager{
    private Connection connection;

    public DatabaseManager(String url, String user, String pass) throws SQLException{
        this.connection = DriverManager.getConnection(url, user, pass);
    }
    private Employee extractEmployee(ResultSet rs) throws SQLException{
        int id = rs.getInt("employeeID");
        String name = rs.getString("employeeName");
        String ssn = rs.getString("employeeSSN");
        String title = rs.getString("jobTitle");
        String division = rs.getString("division");
        float salary = rs.getFloat("salary");
        return new Employee(id, name, ssn, title, division, salary);
    }

    public void addEmployee(Employee e) throws SQLException{
        String sql = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, e.getEmployeeID());
            stmt.setString(2, e.getEmployeeName());
            stmt.setString(3, e.getEmployeeSSN());
            stmt.setString(4, e.getJobTitle());
            stmt.setString(5, e.getDivision());
            stmt.setFloat(6, e.getSalary());
            stmt.executeUpdate();
        }
    }

    public Employee searchBySSN(String ssn) throws SQLException{
        String sql = "SELECT * FROM employee WHERE employeeSSN = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ssn);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return new Employee(
                    rs.getInt("employeeID"),
                    rs.getString("employeeName"),
                    rs.getString("employeeSSN"),
                    rs.getString("jobTitle"),
                    rs.getString("division"),
                    rs.getFloat("salary")
                );
            }
        }
        return null;
    }

public Employee searchByName(String name){
    String sql = "SELECT * FROM employee WHERE employeeName = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)){
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()){
            return extractEmployee(rs);
        }
    } catch (SQLException e){
        e.printStackTrace();
    }
    return null;
}

public Employee searchByID(int id){
    String sql = "SELECT * FROM employee WHERE employeeID = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)){
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return extractEmployee(rs);
        }
    } catch (SQLException e){
        e.printStackTrace();
    }
    return null;
}

    public void updateSalariesInRange(float min, float max, float percentage){
        String sql = "UPDATE employee SET salary = salary * (1 + ? / 100) WHERE salary >= ? AND salary < ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setFloat(1, percentage);
            stmt.setFloat(2, min);
            stmt.setFloat(3, max);
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    public List<Employee> getAllEmployees() throws SQLException{
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()){
                list.add(new Employee(
                    rs.getInt("employeeID"),
                    rs.getString("employeeName"),
                    rs.getString("employeeSSN"),
                    rs.getString("jobTitle"),
                    rs.getString("division"),
                    rs.getFloat("salary")
                ));
            }
        }
        return list;
    }

    public Map<String, Float> getPayByDivision() throws SQLException{
        Map<String, Float> result = new HashMap<>();
        String sql = "SELECT division, SUM(salary)/12 as monthly_pay FROM employee GROUP BY division";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()){
                result.put(rs.getString("division"), rs.getFloat("monthly_pay"));
            }
        }
        return result;
    }

    public Map<String, Float> getPayByJobTitle() throws SQLException{
        Map<String, Float> result = new HashMap<>();
        String sql = "SELECT jobTitle, SUM(salary)/12 as monthly_pay FROM employee GROUP BY jobTitle";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()){
                result.put(rs.getString("jobTitle"), rs.getFloat("monthly_pay"));
            }
        }
        return result;
    }
}

