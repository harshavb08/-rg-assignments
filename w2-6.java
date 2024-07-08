import java.util.ArrayList;

class Employee {
    private int id;
    private String name;
    private String department;

    public Employee(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}

class EmployeeCRUD {
    private ArrayList<Employee> employees = new ArrayList<>();

    // Create
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    // Read
    public Employee getEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    // Update
    public void updateEmployee(int id, String name, String department) {
        Employee employee = getEmployee(id);
        if (employee != null) {
            employee.setName(name);
            employee.setDepartment(department);
        }
    }

    // Delete
    public void deleteEmployee(int id) {
        Employee employee = getEmployee(id);
        if (employee != null) {
            employees.remove(employee);
        }
    }

    // List all employees
    public ArrayList<Employee> getAllEmployees() {
        return employees;
    }
}

public class Main {
    public static void main(String[] args) {
        EmployeeCRUD employeeCRUD = new EmployeeCRUD();

        // Add employees
        Employee emp1 = new Employee(1, "John Doe", "HR");
        Employee emp2 = new Employee(2, "Jane Smith", "Finance");

        employeeCRUD.addEmployee(emp1);
        employeeCRUD.addEmployee(emp2);

        // List all employees
        System.out.println("All Employees:");
        for (Employee emp : employeeCRUD.getAllEmployees()) {
            System.out.println(emp);
        }

        // Get an employee by ID
        System.out.println("\nEmployee with ID 1:");
        System.out.println(employeeCRUD.getEmployee(1));

        // Update an employee
        employeeCRUD.updateEmployee(1, "John Doe", "IT");

        // List all employees after update
        System.out.println("\nAll Employees after update:");
        for (Employee emp : employeeCRUD.getAllEmployees()) {
            System.out.println(emp);
        }

        // Delete an employee
        employeeCRUD.deleteEmployee(2);

        // List all employees after deletion
        System.out.println("\nAll Employees after deletion:");
        for (Employee emp : employeeCRUD.getAllEmployees()) {
            System.out.println(emp);
        }
    }
}
