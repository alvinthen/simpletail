package com.ythen.simpletail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Writer;

public class SimpleTail {
	final static long DEFAULT_LINES = 10;
	final static int BUFFER_SIZE = 1024 * 32;

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
		RandomAccessFile raf = new RandomAccessFile(file, "r");

		long fileLength = raf.length() - 1;
		long linesToRead = lines;
		long filePtr;

		// Seek to last N - 1 lines requested.
		for (filePtr = fileLength; filePtr != -1; filePtr--) {
			raf.seek(filePtr);

			if (raf.readByte() == '\n' || filePtr == fileLength) {
				if (linesToRead-- == 0) // Read all required lines, break.
					break;
			}
		}

		// Seek to the line after N - 1 line
		raf.seek(filePtr + 1);

		for (int i = 0; i < lines; i++)
			writer.write(raf.readLine());
	}
}
