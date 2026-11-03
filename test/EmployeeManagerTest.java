
public class EmployeeManagerTest {

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
        System.out.println("PASS: " + message);
    }

    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager();

        Employee employee =
                new Employee(1, "Amit", "Developer", 50000);

        manager.addEmployee(employee);

        check(manager.getAllEmployees().size() == 1,
                "Add employee");

        check(manager.getEmployee(1).getName().equals("Amit"),
                "Read employee");

        boolean updated = manager.updateEmployee(
                1, "Amit Sharma", "Senior Developer", 65000);

        check(updated
                        && manager.getEmployee(1).getSalary() == 65000,
                "Update employee");

        check(!manager.updateEmployee(
                        99, "Unknown", "Tester", 30000),
                "Missing employee update is rejected");

        boolean duplicateRejected = false;

        try {
            manager.addEmployee(
                    new Employee(1, "Duplicate", "Tester", 30000));
        } catch (IllegalArgumentException e) {
            duplicateRejected = true;
        }

        check(duplicateRejected, "Duplicate employee ID rejected");

        check(manager.deleteEmployee(1), "Delete employee");

        check(manager.getAllEmployees().isEmpty(),
                "Employee list is empty after deletion");

        System.out.println("ALL TESTS PASSED");
    }
}
