package finalyearproject;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class JobTrackerNonSpecMapper extends MapReduceBase implements
		Mapper<LongWritable, Text, Text, IntWritable> 
{
	public static int start_year = 0, End_year;

	public void map(LongWritable ran, Text line,
			OutputCollector<Text, IntWritable> out, Reporter arg3)
			throws IOException 
	{
		try 
		{
			if (line != null) 
			{
				String sep_line = line.toString();
				String year = sep_line.substring(start_year, End_year);
				String Temp = sep_line.substring(End_year);
				
				IntWritable temp_int = new IntWritable();

				temp_int.set(Integer.parseInt(Temp.trim()));
				if (temp_int != null && year != null)

					out.collect(new Text(year), temp_int);

			}
		} 
		
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

}
