package finalyearproject;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class ForecastReducer extends MapReduceBase implements
		Reducer<Text, IntWritable, Text, DoubleWritable> 
{
	boolean error_code;

	public ForecastReducer() 
	{
		if (Math.random() > 0.8) 
		{
			error_code = true;
		}
	}

	public void reduce(Text year, Iterator<IntWritable> temp,
			OutputCollector<Text, DoubleWritable> out, Reporter arg3)
			throws IOException 
	{
		long sum = 0;
		double avg = 0.0;
		int count = 0;
		try 
		{
			while (temp.hasNext()) 
			{
				sum += temp.next().get();
				count++;
			}

			avg = sum / count;
			// introducing error
			if (error_code) 
			{
				avg += 12;
			}
			out.collect(year, new DoubleWritable(avg));
		}

		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
