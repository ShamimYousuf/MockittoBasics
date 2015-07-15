package test.java.learn;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Created by shamimyousuf on 15/07/2015.
 * Reference - http://docs.mockito.googlecode.com/hg/latest/org/mockito/Mockito.html
 */
public class mockittoTest {

    List mockedList = mock(List.class);
    @Test //1
    public void verifySomeBehaviour(){
        List mockedList = mock(List.class);
        mockedList.add("one");
        mockedList.clear();

        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    @Test   //2
    public void stubbing(){
        LinkedList mockedLinkedList = mock(LinkedList.class);

        when(mockedLinkedList.get(0)).thenReturn("first");
        when(mockedLinkedList.get(1)).thenReturn(new RuntimeException());

        System.out.println(mockedLinkedList.get(0));
        System.out.println(mockedLinkedList.get(1));

        verify(mockedLinkedList).get(0);
    }

    @Test   //3
    public void argumentMatchers(){
        LinkedList mockedLinkedList = mock(LinkedList.class);
        when(mockedLinkedList.get(anyInt())).thenReturn("element");
        //when(mockedLinkedList.contains(argThat(isValid())))
        System.out.println(mockedLinkedList.get(anyInt()));
        verify(mockedLinkedList).get(anyInt());
    }

    @Test   //4
    public void numberOfInvocations(){
        LinkedList mockedLinkedList = mock(LinkedList.class);
        mockedLinkedList.add("once");
        mockedLinkedList.add("twice");
        mockedLinkedList.add("twice");
        mockedLinkedList.add("thrice");
        mockedLinkedList.add("thrice");
        mockedLinkedList.add("thrice");


        verify(mockedLinkedList).add("once");
        verify(mockedLinkedList, times(2)).add("twice");
        verify(mockedLinkedList, times(3)).add("thrice");

        verify(mockedLinkedList, never()).add("s");

        verify(mockedLinkedList, atLeast(2)).add("twice");
        verify(mockedLinkedList, atLeastOnce()).add("once");
        verify(mockedLinkedList, atMost(3)).add("thrice");

    }

    @Test(expected = RuntimeException.class)    //5
    public void stubbingVoidMethods(){
        LinkedList mockedLinkedList = mock(LinkedList.class);
        doThrow(new RuntimeException()).when(mockedLinkedList).clear();
        mockedLinkedList.clear();
    }

    @Test   //6
    public void verificationInOrder(){
        List mockedList = mock(List.class);

        mockedList.add("first");
        mockedList.add("second");

        InOrder inOrder = inOrder(mockedList);
        inOrder.verify(mockedList).add("first");
        inOrder.verify(mockedList).add("second");

        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        firstMock.add("firstly");
        secondMock.add("secondly");

        InOrder inOrder1 = inOrder(firstMock, secondMock);
        inOrder1.verify(firstMock).add("firstly");
        inOrder1.verify(secondMock).add("secondly");

    }

    @Test   //7
    public void interactionsNeverHappened(){
        List mockedList = mock(List.class);
        List mockedList1 = mock(List.class);
        List mockedList2 = mock(List.class);
        mockedList.add("one");
        verify(mockedList).add("one");

        verify(mockedList, never()).add("two");
        verifyZeroInteractions(mockedList1, mockedList2);
    }

    @Test   //8
    public void findingRedundantInvocations(){
        List mockedList = mock(List.class);
       // mockedList.add("one");
        verifyNoMoreInteractions(mockedList);
    }


    @Test(expected = RuntimeException.class)    //9
    public void stubbingConsecutiveCalls(){
        when(mockedList.get(anyInt())).thenReturn("Shamim").thenReturn("neethu").thenThrow(new RuntimeException());
        System.out.println(mockedList.get(1));
        System.out.println(mockedList.get(1));
        System.out.println(mockedList.get(1));
    }

    @Test   //10
    public void spyingOnRealObjects(){
        List list = new LinkedList();
        List spy = spy(list);

        //we can stub some methods
        when(spy.size()).thenReturn(100);

        //spy calls real methods
        spy.add("one");
        spy.add("two");

        System.out.println(spy.get(0));
        System.out.println(spy.size());

        verify(spy).add("one");
        verify(spy).add("two");

        //Use doReturn for spy stubbing
        doReturn("Shamim").when(spy).get(0);

        System.out.println(spy.get(0));

    }

    @Ignore
    @Test   //11
    public void argumentCapturing(){
        ArgumentCaptor<LinkedList> argumentCaptor = ArgumentCaptor.forClass(LinkedList.class);
//        mockedList.add("shamim");
        verify(mockedList).add(argumentCaptor);
        Assert.assertEquals("shamim", argumentCaptor.getValue().add("shamim"));
    }

    @Test
    public void resettingMock(){

        List mock = mock(List.class);
        when(mock.get(0)).thenReturn("hello");
        mock.add("hello");

        System.out.println(mock.get(0));
        reset(mock);
        System.out.println(mock.get(0));
    }

}
