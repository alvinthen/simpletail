package com.ythen.simpletail.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ythen.simpletail.SimpleTail;

public class SimpleTailTest {

	SimpleTail sut;
	StringWriter writer;

	@Before
	public void setUp() throws Exception {
		sut = new SimpleTail();
		writer = new StringWriter();
	}

	@After
	public void tearDown() throws Exception {
		sut = null;
		writer.close();
	}

	@Test
	public void testTailLastTenLines() throws IOException {
		sut.tail("test", writer);

		assertEquals("11\n12\n13\n14\n15\n16\n17\n18\n19\n20\n",
				writer.toString());
	}

	@Test
	public void testTailZeroLines() throws IOException {
		sut.tail("test", writer, 0);

		assertEquals("", writer.toString());
	}

	@Test
	public void testTailOverheadLines() throws IOException {
		sut.tail("test", writer, 60);

		assertEquals(
				"1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11\n12\n13\n14\n15\n16\n17\n18\n19\n20\n",
				writer.toString());
	}

	@Test
	public void testTailLastTenBytes() throws IOException {
		sut.tailBytes("test", writer, 10);

		assertEquals("\n18\n19\n20\n", writer.toString());
	}

	@Test
	public void testTailZeroBytes() throws IOException {
		sut.tailBytes("test", writer, 0);

		assertEquals("", writer.toString());
	}

	@Test
	public void testTailOverheadBytes() throws IOException {
		sut.tailBytes("test", writer, 60);

		assertEquals(
				"1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11\n12\n13\n14\n15\n16\n17\n18\n19\n20\n",
				writer.toString());
	}

	@Test
	public void testTailLinesStartingFromTenthLine() throws IOException {
		sut.tailFromLine("test", writer, 10);

		assertEquals("10\n11\n12\n13\n14\n15\n16\n17\n18\n19\n20\n",
				writer.toString());
	}

	@Test
	public void testTailLinesStartingFromFirstLine() throws IOException {
		sut.tailFromLine("test", writer, 1);

		assertEquals(
				"1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11\n12\n13\n14\n15\n16\n17\n18\n19\n20\n",
				writer.toString());
	}

	@Test
	public void testTailLinesStartingFromOverheadLine() throws IOException {
		sut.tailFromLine("test", writer, 100);

		assertEquals("", writer.toString());
	}
	
	@Test
	public void testTailLinesStartingFromTenthByte() throws IOException {
		sut.tailFromByte("test", writer, 10);

		assertEquals("\n6\n7\n8\n9\n10\n11\n12\n13\n14\n15\n16\n17\n18\n19\n20\n",
				writer.toString());
	}

	@Test
	public void testTailLinesStartingFromFirstByte() throws IOException {
		sut.tailFromByte("test", writer, 1);

		assertEquals(
				"1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11\n12\n13\n14\n15\n16\n17\n18\n19\n20\n",
				writer.toString());
	}

	@Test
	public void testTailLinesStartingFromOverheadByte() throws IOException {
		sut.tailFromByte("test", writer, 100);

		assertEquals("", writer.toString());
	}
}
