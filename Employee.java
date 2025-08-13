public class Employee {
    private int employeeID;
    private String employeeName;
    private String employeeSSN;
    private String jobTitle;
    private String division;
    private float salary;

    public Employee(int id, String name, String ssn, String jobTitle, String division, float salary) {
        this.employeeID = id;
        this.employeeName = name;
        this.employeeSSN = ssn;
        this.jobTitle = jobTitle;
        this.division = division;
        this.salary = salary;
    }

    //getter and setters methods
    public int getEmployeeID() { return employeeID; }
    public String getEmployeeName() { return employeeName; }
    public String getEmployeeSSN() { return employeeSSN; }
    public String getJobTitle() { return jobTitle; }
    public String getDivision() { return division; }
    public float getSalary() { return salary; }

    public void setEmployeeName(String name) { this.employeeName = name; }
    public void setJobTitle(String title) { this.jobTitle = title; }
    public void setDivision(String div) { this.division = div; }
    public void setSalary(float salary) { this.salary = salary; }

    public String toString() {
        return String.format("%d | %s | %s | %s | %s | $%.2f", employeeID, employeeName, employeeSSN, jobTitle, division, salary);
    }
}
