package finalyearproject;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

public class StartMapperClass implements Runnable {
	static Class mapperclass, reducerclass;
	static String input_dir, output_dir;

	/**
	 * @param mapperclass
	 * @param reducerclass
	 * @param input_dir
	 * @param output_dir
	 * @param fault_index
	 */
	static void StartMapperClass1(Class mapperclass1, Class reducerclass1,
			String input_dir1, String output_dir1) {

		mapperclass = mapperclass1;
		reducerclass = reducerclass1;
		input_dir = input_dir1;
		output_dir = output_dir1;

	}

	public void run() {
		JobConf conf = new JobConf(finalyearproject.ForecastDriver.class);

		// TODO: specify output types
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);
		conf.setSpeculativeExecution(true);
		// TODO: specify input and output DIRECTORIES (not files)
		conf.setInputFormat(TextInputFormat.class);
		conf.setOutputFormat(TextOutputFormat.class);
		FileInputFormat.setInputPaths(conf, new Path(input_dir));

		FileOutputFormat.setOutputPath(conf,
				new Path(output_dir + Math.random()));
		conf.setMapperClass(mapperclass);

		conf.setReducerClass(reducerclass);
		try {
			JobClient.runJob(conf);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
