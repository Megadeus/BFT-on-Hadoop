package finalyearproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

/**
 * @author hduser
 * 
 */
public class ByzantineFaultSystem {
	String mapper_class, Reducer_class, Combiner_class,
			input_dir = "/home/hduser/workspace/newfinalyearproject/IN",
			out_dir = "/home/hduser/workspace/newfinalyearproject/OUT";
	Boolean mode;
	int fault_index;

	private static final String IN = "/home/hduser/workspace/newfinalyearproject/IN",
			OUT = "/home/hduser/workspace/newfinalyearproject/OUT";

	/**
	 * @return the mapper_class
	 */
	String getMapper_class() {
		return mapper_class;
	}

	/**
	 * @param mapper_class
	 *            the mapper_class to set
	 */
	void setMapper_class(String mapper_class) {
		this.mapper_class = mapper_class;
	}

	/**
	 * @return the reducer_class
	 */
	String getReducer_class() {
		return Reducer_class;
	}

	/**
	 * @param reducer_class
	 *            the reducer_class to set
	 */
	void setReducer_class(String reducer_class) {
		Reducer_class = reducer_class;
	}

	/**
	 * @return the combiner_class
	 */
	String getCombiner_class() {
		return Combiner_class;
	}

	/**
	 * @param combiner_class
	 *            the combiner_class to set
	 */
	void setCombiner_class(String combiner_class) {
		Combiner_class = combiner_class;
	}

	/**
	 * @return the input_dir
	 */
	String getInput_dir() {
		return input_dir;
	}

	/**
	 * @param input_dir
	 *            the input_dir to set
	 */
	void setInput_dir(String input_dir) {
		this.input_dir = input_dir;
	}

	/**
	 * @return the out_dir
	 */
	String getOut_dir() {
		return out_dir;
	}

	/**
	 * @param out_dir
	 *            the out_dir to set
	 */
	void setOut_dir(String out_dir) {
		this.out_dir = out_dir;
	}

	/**
	 * @return the mode
	 */
	Boolean getMode() {
		return mode;
	}

	/**
	 * @param mode
	 *            the mode to set
	 */
	void setMode(Boolean mode) {
		this.mode = mode;
	}

	/**
	 * @return the fault_index
	 */
	int getFault_index() {
		return fault_index;
	}

	/**
	 * @param fault_index
	 *            the fault_index to set
	 */
	void setFault_index(int fault_index) {
		this.fault_index = fault_index;
	}

	/**
	 * @param mapper_class
	 * @param reducer_class
	 * @param combiner_class
	 * @param input_dir
	 * @param out_dir
	 * @param mode
	 * @param fault_index
	 * @throws Exception 
	 */
	
	public ByzantineFaultSystem(String mapper_class, String reducer_class,
			String input_dir, String out_dir, Boolean mode, int fault_index) throws Exception {
		super();
		
//		new File("/home/hduser/workspace/newfinalyearproject/WebContent/new").createNewFile();
		
		File f = new File("/home/hduser/workspace/newfinalyearproject/OUT");
		File f1 = new File(
				"/home/hduser/workspace/newfinalyearproject/tempnsinput");
		File f2 = new File(
				"/home/hduser/workspace/newfinalyearproject/tempnsmresult");
		File f3 = new File(
				"/home/hduser/workspace/newfinalyearproject/tempnsrresult");
		File f4 = new File(
				"/home/hduser/workspace/newfinalyearproject/tempsinput");
		File f5 = new File(
				"/home/hduser/workspace/newfinalyearproject/tempsmresult");
		File f6 = new File(
				"/home/hduser/workspace/newfinalyearproject/tempsrresult");
		File f7 = new File(
				"/home/hduser/workspace/newfinalyearproject/tempsrresult1");
		
		deleteDirectory(f);
		deleteDirectory(f1);
		deleteDirectory(f2);
		deleteDirectory(f3);
		deleteDirectory(f4);
		deleteDirectory(f5);
		deleteDirectory(f6);
		deleteDirectory(f7);
		
//		Properties p =  new Properties();
//		
//		p.load(new FileInputStream("/home/hduser/Desktop/Resources.properties"));
//		
		PrintStream out = new PrintStream("/home/hduser/workspace/newfinalyearproject/WebContent/log.txt");
		
		System.setOut(out);
		
		setMapper_class(mapper_class);
		setReducer_class(reducer_class);
		// setOut_dir(out_dir);
		setMode(mode);
		setFault_index(fault_index);
		// setInput_dir(input_dir);
	}

	public static void main(String[] args) throws Exception {
		
		ByzantineFaultSystem ob = new ByzantineFaultSystem("mapper_class",
				"reducer_class",
				"/home/hduser/workspace/newfinalyearproject/IN",
				"/home/hduser/workspace/newfinalyearproject/OUT", true, 2);
		ob.run();

		FileUtils.copyFile(new File("/home/hduser/workspace/newfinalyearproject/OUT/new"), new File("/home/hduser/workspace/newfinalyearproject/WebContent/new"));
		
	}

	public static void deleteDirectory(File directory) {
		if (directory.exists()) {
			File[] files = directory.listFiles();
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					if (files[i].isDirectory()) {
						deleteDirectory(files[i]);
						files[i].delete();
					} else {
						files[i].delete();
					}
				}
			}
		}
	}

	/**
	 * 
	 */
	ByzantineFaultSystem() {
		super();
	}

	public int run() {
		try {
			
			if (mode == false) {
				JobTrackerNonSpecDriver obj = new JobTrackerNonSpecDriver();
				obj.run(ForecastMapper.class,
						finalyearproject.ForecastReducer.class, input_dir,
						out_dir, fault_index);

			} else {
				JobTrackerSpecDriver obj = new JobTrackerSpecDriver();
				obj.run(ForecastMapper.class,
						finalyearproject.ForecastReducer.class, input_dir,
						out_dir, fault_index);

			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return 0;
		}
		return 1;
	}

}
