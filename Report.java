import java.util.Map;

public class Report {
    public static void generatePayByDivision(Map<String, Float> data) {
        System.out.println("Monthly Pay by Division:");
        data.forEach((div, amt) -> System.out.printf("%-20s : $%.2f%n", div, amt));
    }

    public static void generatePayByTitle(Map<String, Float> data) {
        System.out.println("Monthly Pay by Job Title:");
        data.forEach((title, amt) -> System.out.printf("%-20s : $%.2f%n", title, amt));
    }
}
