package com.ythen.simpletail.test;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.Writer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ythen.simpletail.SimpleTail;

public class SimpleTailTest {

	SimpleTail sut;
	Writer mockWriter;
	
	@Before
	public void setUp() throws Exception {
		sut = new SimpleTail();
		mockWriter = mock(Writer.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTailLastTenLines() throws IOException {
		sut.tail("test", mockWriter);
		for (int i = 11; i <= 20; i++)
			verify(mockWriter).write(Integer.toString(i));
	}

	@Test
	public void testTailZeroLines() throws IOException {
		sut.tail("test", mockWriter, 0);		
		verify(mockWriter, never()).write(anyString());
	}
	
	@Test
	public void testTailOverheadLines() throws IOException {
		sut.tail("test", mockWriter, 60);
		for (int i = 1; i <= 20; i++)
			verify(mockWriter).write(Integer.toString(i));
	}
	
	@Test
	public void testTailLastTenBytes() throws IOException {
		sut.tailBytes("test", mockWriter, 10);
		
		// Test result may not be reliable as the order of method calling is not tested.
		verify(mockWriter, times(4)).write('\n');
		verify(mockWriter, times(2)).write('1');
		verify(mockWriter).write('8');
		verify(mockWriter).write('9');
		verify(mockWriter).write('2');
		verify(mockWriter).write('0');
	}
	
	@Test
	public void testTailZeroBytes() throws IOException {
		sut.tailBytes("test", mockWriter, 0);		
		verify(mockWriter, never()).write(anyString());
	}
	
	@Test
	public void testTailOverheadBytes() throws IOException {
		sut.tailBytes("test", mockWriter, 60);
		
		// Test result may not be reliable as the order of method calling is not tested.
		verify(mockWriter, times(20)).write('\n');
		verify(mockWriter, times(12)).write('1');
		verify(mockWriter, times(3)).write('2');
		verify(mockWriter, times(2)).write('3');
		verify(mockWriter, times(2)).write('4');
		verify(mockWriter, times(2)).write('5');
		verify(mockWriter, times(2)).write('6');
		verify(mockWriter, times(2)).write('7');
		verify(mockWriter, times(2)).write('8');
		verify(mockWriter, times(2)).write('9');
		verify(mockWriter, times(2)).write('0');
	}
}
