
package com.portfolio.lr.Interface;

import com.portfolio.lr.entity.Person;
import java.util.List;


public interface IPersonService {
    public List<Person> getPerson();
    
    public void savePerson(Person person);
    
    public void deletePerson(Long id);
    
    public Person findPerson(Long id);
}
