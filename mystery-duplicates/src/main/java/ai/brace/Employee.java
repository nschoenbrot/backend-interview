package ai.brace;

import java.util.Objects;

public class Employee
{
    public String firstName;
    public String middleInitial;
    public String lastName;
    public String socialSecurityNumber;

    public Employee(String _lastName, String _firstName, String _middleInitial, String _socialSecurityNumber)
    {
        firstName = _firstName;
        middleInitial = _middleInitial;
        lastName = _lastName;
        socialSecurityNumber = _socialSecurityNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return firstName.equals(employee.firstName) &&
                middleInitial.equals(employee.middleInitial) &&
                lastName.equals(employee.lastName) &&
                socialSecurityNumber.equals(employee.socialSecurityNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleInitial, lastName, socialSecurityNumber);
    }
}
