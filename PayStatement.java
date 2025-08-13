/* This class was initially going to be used to generate pay statements
* but was not implemented in the original code.
* It can be used to add functionality to the system's reporting and searching features in the future
*/
public class PayStatement {
    private int id;
    private int month;
    private int year;
    private float amount;

    public PayStatement(int id, int month, int year, float amount) {
        this.id = id;
        this.month = month;
        this.year = year;
        this.amount = amount;
    }

    public String getPayStatement() {
        return String.format("Pay Statement [%d/%d]: $%.2f", month, year, amount);
    }
}
