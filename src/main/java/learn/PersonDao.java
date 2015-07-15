package main.java.learn;

/**
 * Created by shamimyousuf on 14/07/2015.
 */
public interface PersonDao {

    public Person fetchPerson( Integer personID );
    public void update( Person person );

}
