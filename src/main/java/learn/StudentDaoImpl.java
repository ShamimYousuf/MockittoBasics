package main.java.learn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shamimyousuf on 14/07/2015.
 */
public class StudentDaoImpl implements StudentDao {

    List<Student> students;

    public StudentDaoImpl(){
        students = new ArrayList<Student>();
        Student student1 = new Student("neethu", 0);
        Student student2 = new Student("raj" , 1);
        students.add(student1);
        students.add(student2);
    }

    @Override
    public List<Student> getAllStudents() {
        return students;
    }

    @Override
    public Student getStudent(Integer rollNo) {
        return students.get(rollNo);
    }

    @Override
    public void updateStudent(Student student) {
        students.get(student.getRollNo()).setName(student.getName());
        System.out.println("Student with rollNo "+ student.getRollNo() + " is updated in database. It is "+ student.getName());
    }

    @Override
    public void deleteStudent(Student student) {
        students.remove(student.getRollNo());
        System.out.println("Student with rollNo " + student.getRollNo() + " deleted from dB");
    }
}
