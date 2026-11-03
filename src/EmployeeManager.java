
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EmployeeManager {
    private final Map<Integer, Employee> employees =
            new LinkedHashMap<>();

    public void addEmployee(Employee employee) {
        if (employees.containsKey(employee.getId())) {
            throw new IllegalArgumentException(
                    "Employee ID already exists");
        }

        employees.put(employee.getId(), employee);
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    public Employee getEmployee(int id) {
        return employees.get(id);
    }

    public boolean updateEmployee(
            int id, String name, String role, double salary) {

        Employee employee = employees.get(id);

        if (employee == null) {
            return false;
        }

        employee.setName(name);
        employee.setRole(role);
        employee.setSalary(salary);

        return true;
    }

    public boolean deleteEmployee(int id) {
        return employees.remove(id) != null;
    }
}
