package finalyearproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;

public class TextAreaPrintStream extends PrintStream {

	public TextAreaPrintStream(File file) throws FileNotFoundException {
		super(file);
	}
	
	 public TextAreaPrintStream(OutputStream out , boolean autoflush) {
			super(out , autoflush);
		}

	@Override
	public void println(String x) {
		super.println(x);
		System.out.flush();
	}
	
	@Override
	public void println(char[] x) {
		super.println(x);
		System.out.flush();
	}
	
	@Override
	public void print(String s) {
		super.print(s);
		System.out.flush();
	}
	
	@Override
	public void print(char[] s) {
		super.print(s);
		System.out.flush();
	}
	
	@Override
	public void flush() {
		super.flush();
	}

}
