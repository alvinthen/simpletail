package com.ythen.simpletail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Writer;

public class SimpleTail {
	final static long DEFAULT_LINES = 10;

	public static void main(String[] args) {
		
	}

	/**
	 * Writes last 10 lines of file fileName using writer
	 * 
	 * @param fileName
	 * @param writer
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void tail(String fileName, Writer writer)
			throws FileNotFoundException, IOException {
		this.tail(fileName, writer, DEFAULT_LINES);
	}

	/**
	 * Writes last N lines of file filName using writer
	 * 
	 * @param fileName
	 * @param writer
	 * @param lines
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void tail(String fileName, Writer writer, long lines)
			throws FileNotFoundException, IOException {
		this.tail(new File(fileName), writer, lines);
	}

	/**
	 * Writes last N lines of File using writer
	 * 
	 * @param file
	 * @param writer
	 * @param lines
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void tail(File file, Writer writer, long lines)
			throws FileNotFoundException, IOException {
		if (lines == 0)
			return;

		RandomAccessFile raf = new RandomAccessFile(file, "r");

		long fileLength = raf.length() - 1;
		long linesToRead = lines;
		long filePtr;

		// Seek to \n of N - 1 lines
		for (filePtr = fileLength; filePtr != -1; filePtr--) {
			raf.seek(filePtr);

			if (raf.readByte() == 0x0A || filePtr == fileLength) {
				if (linesToRead-- == 0) // Read all required lines, break.
					break;
			}
		}

		// Seek to the EOL of N - 1 line
		raf.seek(filePtr + 1);

		readAndWrite(raf, writer);
	}

	/**
	 * Writes last N bytes of file fileName using writer
	 * 
	 * @param fileName
	 * @param writer
	 * @param bytes
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void tailBytes(String fileName, Writer writer, long bytes)
			throws FileNotFoundException, IOException {
		this.tailBytes(new File(fileName), writer, bytes);
	}

	/**
	 * Writes last N bytes of File using writer
	 * 
	 * @param file
	 * @param writer
	 * @param bytes
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void tailBytes(File file, Writer writer, long bytes)
			throws FileNotFoundException, IOException {
		if (bytes == 0)
			return;

		RandomAccessFile raf = new RandomAccessFile(file, "r");

		long bytesToRead = bytes > raf.length() ? raf.length() : bytes;

		// Seek to last N - 1 bytes requested.
		raf.seek(raf.length() - bytesToRead);

		readAndWrite(raf, writer);
	}

	/**
	 * Writes lines starting from line in file fileName using writer
	 * 
	 * @param fileName
	 * @param writer
	 * @param line
	 */
	public void tailFromLine(String fileName, Writer writer, long line)
			throws FileNotFoundException, IOException {
		this.tailFromLine(new File(fileName), writer, line);
	}

	/**
	 * Writes lines starting from line in File using writer
	 * 
	 * @param file
	 * @param writer
	 * @param line
	 */
	public void tailFromLine(File file, Writer writer, long line)
			throws FileNotFoundException, IOException {
		if (line == 0)
			return;

		RandomAccessFile raf = new RandomAccessFile(file, "r");
		long linesRead = 0;

		// Seek until EOL of N - 1 line
		while (linesRead != line - 1 && raf.readLine() != null)
			linesRead++;

		readAndWrite(raf, writer);
	}

	/**
	 * Writes starting from Nth byte in file fileName using writer
	 * 
	 * @param fileName
	 * @param writer
	 * @param byte
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void tailFromByte(String fileName, Writer writer, long b)
			throws FileNotFoundException, IOException {
		this.tailFromByte(new File(fileName), writer, b);
	}

	/**
	 * Writes starting from Nth byte in File using writer
	 * 
	 * @param file
	 * @param writer
	 * @param bytes
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void tailFromByte(File file, Writer writer, long b)
			throws FileNotFoundException, IOException {
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		
		if (b == 0 || b >= raf.length())
			return;

		// Seek to N - 1 byte
		raf.seek(b - 1);

		readAndWrite(raf, writer);
	}

	private void readAndWrite(RandomAccessFile raf, Writer writer)
			throws FileNotFoundException, IOException {
		byte[] p = new byte[(int) (raf.length() - raf.getFilePointer())];
		raf.readFully(p);
		writer.write(new String(p));
	}
}
