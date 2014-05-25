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
}
