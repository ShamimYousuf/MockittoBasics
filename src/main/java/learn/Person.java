package main.java.learn;

/**
 * Created by shamimyousuf on 14/07/2015.
 */
public class Person {

    private final Integer personID;
    private final String personName;

    public Person( Integer personID, String personName )
    {
        this.personID = personID;
        this.personName = personName;
    }

    public Integer getPersonID()
    {
        return personID;
    }

    public String getPersonName()
    {
        return personName;
    }
}
