package training.employees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeesRepository extends JpaRepository<Employee, Long> {

//    Employee findByName(String name);
    // return em.createQuery("select e from Employee e where name = :name")
    // .setParameter("name", name).firstResult();

//    @Query("select e from Employee e where name = :name")
//    Employee xyz(String name);
}
