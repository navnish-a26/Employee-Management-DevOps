
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--healthcheck")) {
            System.out.println("APP_OK");
            return;
        }

        EmployeeManager manager = new EmployeeManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Employee Management System ===");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Employee ID: ");
                        int id = Integer.parseInt(scanner.nextLine());

                        System.out.print("Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Role: ");
                        String role = scanner.nextLine();

                        System.out.print("Salary: ");
                        double salary =
                                Double.parseDouble(scanner.nextLine());

                        manager.addEmployee(
                                new Employee(id, name, role, salary));

                        System.out.println("Employee added successfully");
                        break;

                    case 2:
                        if (manager.getAllEmployees().isEmpty()) {
                            System.out.println("No employees found");
                        } else {
                            manager.getAllEmployees()
                                    .forEach(System.out::println);
                        }
                        break;

                    case 3:
                        System.out.print("Employee ID: ");
                        int updateId =
                                Integer.parseInt(scanner.nextLine());

                        System.out.print("New name: ");
                        String newName = scanner.nextLine();

                        System.out.print("New role: ");
                        String newRole = scanner.nextLine();

                        System.out.print("New salary: ");
                        double newSalary =
                                Double.parseDouble(scanner.nextLine());

                        boolean updated = manager.updateEmployee(
                                updateId, newName, newRole, newSalary);

                        System.out.println(updated
                                ? "Employee updated successfully"
                                : "Employee not found");
                        break;

                    case 4:
                        System.out.print("Employee ID: ");
                        int deleteId =
                                Integer.parseInt(scanner.nextLine());

                        System.out.println(manager.deleteEmployee(deleteId)
                                ? "Employee deleted successfully"
                                : "Employee not found");
                        break;

                    case 5:
                        System.out.println("Exiting application");
                        return;

                    default:
                        System.out.println("Invalid option");
                }

            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
