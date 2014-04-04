package finalyearproject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class JobTrackerSpecDriver {

	private static final String tempsmresult="/home/hduser/workspace/newfinalyearproject/tempsmresult";

	private static final String tempsinput = "/home/hduser/workspace/newfinalyearproject/tempsinput";
	
	private static final String tempsrresult1 = "/home/hduser/workspace/newfinalyearproject/tempsrresult1";
	
	private static final String tempsrresult = "/home/hduser/workspace/newfinalyearproject/tempsrresult";
	
	public void run(Class mapperclass, Class reducerclass, String input_dir,
			String output_dir, int fault_index) { // running the map
		int i, k;
		Thread t[] = new Thread[50];
		Thread t1[] = new Thread[50];

		StartMapperClass.StartMapperClass1(mapperclass,
				finalyearproject.JobTrackerNonSpecReducer.class, input_dir,
				tempsmresult+"/temp");
		StartMapperClass ob = new StartMapperClass();
		Thread f1 = new Thread(ob);
		f1.start();
		while (f1.isAlive())
			;

		File f = new File(tempsmresult);

		String path = "";

		for (String s : f.list())
			path = tempsmresult + "/" + s;

		// wait for first map task to complte^^
		// start f+1 replicas of reduce
		StartReducerClass.StartReducerClass1(
				finalyearproject.JobTrackerNonSpecMapper.class, reducerclass,
				path, tempsrresult + "/temp");

		for (k = 0; k <= fault_index; k++) {
			StartReducerClass ob1 = new StartReducerClass();
			t1[k] = new Thread(ob1);
			t1[k].start();
		}
		// start remaining map task
		for (i = 1; i <= fault_index; i++) {
			StartMapperClass ob1 = new StartMapperClass();
			t[i] = new Thread(ob1);
			t[i].start();
		}
		boolean isalive = true;
		while (isalive) {

			for (int j = 1; j < i; j++) {
				isalive = false;
				if (t[j].isAlive() == true) {
					isalive = true;
					break;
				}

			}

		}
		// checking the error

		// reiterate
		try {
			while (true) {
				if (test( tempsmresult , tempsinput, fault_index + 1) == 1) {
					break;
				}

				// startMapper(mapperclass,
				// finalyearproject.JobTrackerNonSpecReducer.class,
				// input_dir,"tempnsmresult/temp");
				StartMapperClass ob2 = new StartMapperClass();
				Thread kt = new Thread(ob2);
				kt.start();
				while (kt.isAlive())
					;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// running the reduce
		// checking the error and reiterate
		// produce the output

		// check for previous reduce ask to complete
		isalive = true;
		while (isalive) {

			for (int j = 0; j < fault_index; j++) {
				isalive = false;
				if (t1[j].isAlive() == true) {
					isalive = true;
					break;
				}

			}
		}
		if (test1(path + "/part-00000", tempsinput + "/new") == 0) {
			try {
				while (true) {
					if (test(tempsrresult, output_dir, fault_index + 1) == 1) {
						break;
					}

					StartReducerClass.StartReducerClass1(
							finalyearproject.JobTrackerNonSpecMapper.class,
							reducerclass, path, tempsrresult + "/temp");
					StartReducerClass ob1 = new StartReducerClass();
					Thread kt = new Thread(ob1);
					kt.start();
					while (kt.isAlive())
						;

				}
			} catch (Exception e) {
				e.printStackTrace();

			}
		} else {
			StartReducerClass.StartReducerClass1(
					finalyearproject.JobTrackerNonSpecMapper.class,
					reducerclass, tempsinput , tempsrresult1 + "/temp");

			for (i = 0; i <= fault_index; i++) {
				StartReducerClass ob1 = new StartReducerClass();
				t[i] = new Thread(ob1);
				t[i].start();
			}
			isalive = true;
			while (isalive) {

				for (int j = 0; j <= fault_index; j++) {
					isalive = false;
					if (t[j].isAlive() == true) {
						isalive = true;
						break;
					}

				}
			} // checking the error

			// reiterate
			try {
				while (true) {
					if (test(tempsrresult1, output_dir, fault_index + 1) == 1) {
						break;
					}

					StartReducerClass ob1 = new StartReducerClass();
					Thread kt = new Thread(ob1);
					kt.start();
					while (kt.isAlive())
						;

				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static int test(String In, String Out1, int fault) throws Exception {

		// In = "/home/hduser/workspace/finalyearproject/" + In;
		// Out1 = "/home/hduser/workspace/finalyearproject/" + Out1 + "/new";
		Out1 = Out1 + "/new";
		File folder = new File(In);  
		File[] listOfFiles = folder.listFiles();
		int i = 0;
		Scanner s[] = new Scanner[100];

		for (File file : listOfFiles) {

			s[i] = new Scanner(new File(file.getAbsolutePath() + "/part-00000"));
			i++;

		}
		File f2 = new File(Out1);
		List<String> l = new LinkedList<String>();

		while (s[0].hasNext()) {
			HashMap<String, Integer> out = new HashMap<String, Integer>();
			for (int k = 0; k < i; k++) {
				String g = s[k].nextLine();
				if (!out.containsKey(g))
					out.put(g, 1);
				else
					out.put(g, out.get(g) + 1);
			}
			Iterator it = out.entrySet().iterator();
			int counter = 0;
			while (it.hasNext()) {

				Map.Entry<String, Integer> pairs = (Map.Entry<String, Integer>) it
						.next();
				// valueSystem.out.println(pairs.getKey()+" "+pairs.getValue());
				if (pairs.getValue() >= fault) {
					l.add(pairs.getKey());
					counter++;
					break;
				}
			}
			if (counter == 0) {
				return 0;
			}
		}

		if (!f2.isAbsolute()) {
			f2.createNewFile();

		}

		FileWriter fw = new FileWriter(f2.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		for (String n : l) {
			bw.write(n + System.lineSeparator());

		}
		bw.close();

		return 1;
	}

	public int test1(String path1, String path2) {
		File f1 = new File(path1);
		File f2 = new File(path2);

		try {
			Scanner s1 = new Scanner(f1);
			Scanner s2 = new Scanner(f2);
			while (s1.hasNext() && s2.hasNext()) {

				String g1 = s1.nextLine();
				String g2 = s2.nextLine();
				if (!g1.contentEquals(g2)) {
					return -1;
				}

			}
			return 0;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}

	}

}
