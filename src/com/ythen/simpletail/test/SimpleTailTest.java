package com.ythen.simpletail.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.io.Writer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ythen.simpletail.SimpleTail;

public class SimpleTailTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPrintLastTenLines() throws IOException {
		Writer writer = mock(Writer.class);
		SimpleTail simpleTail = new SimpleTail();
		simpleTail.tail("test", writer);
		for (int i = 11; i <= 20; i++)
			verify(writer).write(Integer.toString(i));
	}

}
