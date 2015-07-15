package main.java.learn;

import java.util.List;

/**
 * Created by shamimyousuf on 14/07/2015.
 * DAO Interface
 */
public interface StudentDao {

    public List<Student> getAllStudents();
    public Student getStudent(Integer rollNo);
    public void updateStudent(Student student);
    public void deleteStudent(Student student);

}
