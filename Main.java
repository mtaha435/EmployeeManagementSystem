public class Main {
    public static void main(String[] args) {
        // initializing the database connection, starting the employee management system
        try {
            DatabaseManager db = new DatabaseManager("jdbc:mysql://localhost:3306/example", "root", "");
            EmployeeManagementSystem system = new EmployeeManagementSystem(db);
            system.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
