import java.sql.SQLException;
import java.util.*;

public class EmployeeManagementSystem {
    private DatabaseManager db;

    public EmployeeManagementSystem(DatabaseManager db) {
        this.db = db;
    }

    public void start() throws SQLException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Employee\n2. Search\n3. Update Salary\n4. Pay Report by Division\n5. Pay Report by Title\n6. Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            //this is the actual user input handling
            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("SSN: ");
                    String ssn = sc.nextLine();
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Division: ");
                    String div = sc.nextLine();
                    System.out.print("Salary: ");
                    float salary = sc.nextFloat();
                    sc.nextLine();
                    int id = new Random().nextInt(10000);
                    Employee emp = new Employee(id, name, ssn, title, div, salary);
                    db.addEmployee(emp);
                    break;
                case 2:
                    System.out.println("Search by:");
                    System.out.println("1. SSN");
                    System.out.println("2. Name");
                    System.out.println("3. Employee ID");
                    System.out.print("Choose option: ");
                    int searchOption = sc.nextInt();
                    sc.nextLine();
                    Employee result = null;
                    switch (searchOption) {
                        case 1:
                            System.out.print("Enter SSN: ");
                            result = db.searchBySSN(sc.nextLine());
                            break;
                        case 2:
                            System.out.print("Enter Name: ");
                            result = db.searchByName(sc.nextLine());
                            break;
                        case 3:
                            System.out.print("Enter Employee ID: ");
                            int searchId = sc.nextInt();
                            sc.nextLine();
                            result = db.searchByID(searchId);
                            break;
                        default:
                            System.out.println("Invalid search option.");
                    }
                    System.out.println(result == null ? "Not found." : result);
                    break;

                case 3:
                    System.out.print("Enter minimum salary: ");
                    float min = sc.nextFloat();
                    System.out.print("Enter maximum salary: ");
                    float max = sc.nextFloat();
                    System.out.print("Enter percentage increase (e.g., 3.2 for 3.2%): ");
                    float percent = sc.nextFloat();
                    sc.nextLine();
                    db.updateSalariesInRange(min, max, percent);
                    System.out.println("Salaries updated.");
                    break;

                case 4:
                    Report.generatePayByDivision(db.getPayByDivision());
                    break;
                case 5:
                    Report.generatePayByTitle(db.getPayByJobTitle());
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
