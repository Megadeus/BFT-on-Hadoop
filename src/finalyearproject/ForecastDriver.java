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

public class ForecastDriver implements Runnable 
{

	/**
	 * @author Vaidhyanathan, Barath, Prashanth
	 */
	public ForecastDriver() 
	{
		Thread t1, t2;
		t1 = new Thread(this, "thread1");
		t2 = new Thread(this, "thread2");
		t1.start();
		t2.start();
	}

	public void run() 
	{
		JobConf conf = new JobConf(finalyearproject.ForecastDriver.class);
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);
		conf.setSpeculativeExecution(true);
		conf.setInputFormat(TextInputFormat.class);
		conf.setOutputFormat(TextOutputFormat.class);
		FileInputFormat.setInputPaths(conf, new Path("IN"));
		String outputfile = "output/output1" + Math.random();
		FileOutputFormat.setOutputPath(conf, new Path(outputfile));
		Class mapperclass = finalyearproject.ForecastMapper.class;
		Class reducerclass = finalyearproject.ForecastReducer.class;
		conf.setMapperClass(mapperclass);

		conf.setReducerClass(reducerclass);
		try 
		{
			JobClient.runJob(conf);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
