package main.java.learn;

/**
 * Created by shamimyousuf on 14/07/2015.
 */
public class StudentService {

    private final StudentDao studentDao;

    public StudentService(StudentDao studentDao){
        this.studentDao = studentDao;
    }

    public boolean update(String name, int rollNo){
        Student student = studentDao.getStudent(rollNo);

        if(student !=  null){

            Student updatedStudent = new Student(name, student.getRollNo());
            studentDao.updateStudent(updatedStudent);
            return true;
        }else{
            return false;
        }

    }
}
