package test.java.learn;

import main.java.learn.StudentDao;
import main.java.learn.StudentDaoImpl;
import main.java.learn.StudentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

/**
 * Created by shamimyousuf on 14/07/2015.
 */
public class StudentServiceTest {

    @Mock
    StudentDao studentDao;
    StudentService studentService;

    @Before
    public void setUp(){
        studentDao = new StudentDaoImpl();
        studentService = new StudentService(studentDao);
    }

    @Test
    public void shouldUpdateStudentName(){

        boolean updated = studentService.update("David", 1);
        Assert.assertTrue(updated);
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void shouldNotUpdateStudentName(){
        boolean updated = studentService.update("David", 3);
        Assert.assertTrue(updated);
    }
}
