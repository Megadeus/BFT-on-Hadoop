package finalyearproject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class JobTrackerNonSpecDriver 
{
	private static final String tempnsmresult = "/home/hduser/workspace/newfinalyearproject/tempnsmresult";
	
	private static final String tempnsinput = "/home/hduser/workspace/newfinalyearproject/tempnsinput";
	
	private final static String tempnsrresult = "/home/hduser/workspace/newfinalyearproject/tempnsrresult";
	
	public void run(Class mapperclass, Class reducerclass, String input_dir,
			String output_dir, int fault_index) 
	{ // running the map
		int i;
		StartMapperClass.StartMapperClass1(mapperclass,
				finalyearproject.JobTrackerNonSpecReducer.class, input_dir,
				tempnsmresult + "/temp");
		Thread t[] = new Thread[50];
		for (i = 0; i <= fault_index; i++) 
		{
			StartMapperClass ob = new StartMapperClass();
			t[i] = new Thread(ob);
			t[i].start();
		}
		boolean isalive = true;
		while (isalive) 
		{
			for (int j = 0; j < i; j++) 
			{
				isalive = false;
				if (t[j].isAlive() == true) 
				{
					isalive = true;
					break;
				}

			}

		}
		// checking the error

		// reiterate
		try {
			while (true) 
			{
				if (test(tempnsmresult, tempnsinput, fault_index + 1) == 1) 
				{
					break;
				}

				StartMapperClass ob = new StartMapperClass();
				Thread k = new Thread(ob);
				k.start();
				while (k.isAlive());
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		// running the reduce
		// checking the error and reiterate
		// produce the output

		StartReducerClass.StartReducerClass1(
				finalyearproject.JobTrackerNonSpecMapper.class, reducerclass,
				tempnsinput, tempnsrresult + "/temp");

		for (i = 0; i <= fault_index; i++) 
		{
			StartReducerClass ob1 = new StartReducerClass();
			t[i] = new Thread(ob1);
			t[i].start();
		}
		isalive = true;
		while (isalive)
		{
			for (int j = 0; j < i; j++)
			{
				isalive = false;
				if (t[j].isAlive() == true) 
				{
					isalive = true;
					break;
				}

			}

		} // checking the error

		// reiterate
		try 
		{
			while (true) 
			{
				if (test(tempnsrresult, output_dir, fault_index + 1) == 1) 
				{
					break;
				}

				StartReducerClass ob = new StartReducerClass();
				Thread k = new Thread(ob);
				k.start();
				while (k.isAlive());
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

	public static int test(String In, String Out1, int fault) throws Exception 
	{
		Out1 = Out1 + "/new";
		File folder = new File(In);
		File[] listOfFiles = folder.listFiles();
		int i = 0;
		Scanner s[] = new Scanner[100];

		for (File file : listOfFiles) 
		{
			s[i] = new Scanner(new File(file.getAbsolutePath() + "/part-00000"));
			i++;
		}
		File f2 = new File(Out1);
		List<String> l = new LinkedList<String>();

		while (s[0].hasNext() && s[1].hasNext() && s[2].hasNext()) 
		{
			HashMap<String, Integer> out = new HashMap<String, Integer>();
			for (int k = 0; k < i; k++) 
			{
				String g = s[k].nextLine();
				if (!out.containsKey(g))
					out.put(g, 1);
				else
					out.put(g, out.get(g) + 1);
			}
			Iterator it = out.entrySet().iterator();
			int counter = 0;
			while (it.hasNext()) 
			{
				Map.Entry<String, Integer> pairs = (Map.Entry<String, Integer>) it.next();
				// to print all
				if (pairs.getValue() >= fault) 
				{
					l.add(pairs.getKey());
					counter++;
					break;
				}
			}
			if (counter == 0) 
			{
				return 0;
			}
		}

		if (!f2.isAbsolute()) 
		{
			f2.createNewFile();
		}

		FileWriter fw = new FileWriter(f2.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		for (String n : l) 
		{
			bw.write(n + System.lineSeparator());

		}
		bw.close();

		return 1;
	}

}
