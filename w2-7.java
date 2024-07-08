import java.sql.*;
import java.util.ArrayList;

public class EmployeeJDBC {
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:employee.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // Create
    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO employees(id, name, department) VALUES(?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employee.getId());
            pstmt.setString(2, employee.getName());
            pstmt.setString(3, employee.getDepartment());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Read
    public Employee getEmployee(int id) {
        String sql = "SELECT id, name, department FROM employees WHERE id = ?";
        Employee employee = null;

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                employee = new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("department"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employee;
    }

    // Update
    public void updateEmployee(int id, String name, String department) {
        String sql = "UPDATE employees SET name = ?, department = ? WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, department);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Delete
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // List all employees
    public ArrayList<Employee> getAllEmployees() {
        String sql = "SELECT id, name, department FROM employees";
        ArrayList<Employee> employees = new ArrayList<>();

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                employees.add(new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("department")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employees;
    }
}
public class Main {
    public static void main(String[] args) {
        EmployeeJDBC employeeJDBC = new EmployeeJDBC();

        // Add employees
        Employee emp1 = new Employee(1, "John Doe", "HR");
        Employee emp2 = new Employee(2, "Jane Smith", "Finance");

        employeeJDBC.addEmployee(emp1);
        employeeJDBC.addEmployee(emp2);

        // List all employees
        System.out.println("All Employees:");
        for (Employee emp : employeeJDBC.getAllEmployees()) {
            System.out.println(emp);
        }

        // Get an employee by ID
        System.out.println("\nEmployee with ID 1:");
        System.out.println(employeeJDBC.getEmployee(1));

        // Update an employee
        employeeJDBC.updateEmployee(1, "John Doe", "IT");

        // List all employees after update
        System.out.println("\nAll Employees after update:");
        for (Employee emp : employeeJDBC.getAllEmployees()) {
            System.out.println(emp);
        }

        // Delete an employee
        employeeJDBC.deleteEmployee(2);

        // List all employees after deletion
        System.out.println("\nAll Employees after deletion:");
        for (Employee emp : employeeJDBC.getAllEmployees()) {
            System.out.println(emp);
        }
    }
}
