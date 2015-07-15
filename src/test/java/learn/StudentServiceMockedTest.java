package test.java.learn;

import main.java.learn.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by shamimyousuf on 14/07/2015.
 */
public class StudentServiceMockedTest {

    @Mock
    StudentDao studentDao;
    StudentService studentService;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        studentService = new StudentService(studentDao);
    }

    @Test
    public void shouldUpdateStudentName(){
        Student student = new Student("Shamim", 1);
        when( studentDao.getStudent( 1 ) ).thenReturn(student);
        boolean updated = studentService.update("David", 1);


        assertTrue( updated );

        verify( studentDao ).getStudent(1);

        ArgumentCaptor<Student> personCaptor = ArgumentCaptor.forClass( Student.class );

        verify( studentDao ).updateStudent( personCaptor.capture() );

        Student updatedStudent = personCaptor.getValue();
        assertEquals( "David", updatedStudent.getName() );

        // asserts that during the test, there are no other calls to the mock object.
        verifyNoMoreInteractions(studentDao);
    }

    @Test
    public void shouldNotUpdateIfPersonNotFound()
    {   Student student = new Student("Shamim", 1);
        when( studentDao.getStudent( 1 ) ).thenReturn(null);
        boolean updated = studentService.update("David" , 1);
        assertFalse( updated );
        verify( studentDao ).getStudent(1);
        verifyZeroInteractions( studentDao );
        verifyNoMoreInteractions(studentDao);
    }
}
