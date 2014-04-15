package finalyearproject;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class ForecastMapper extends MapReduceBase implements
		Mapper<LongWritable, Text, Text, IntWritable> 
{

	public static int start_year, End_year;
	boolean error_code;

	public ForecastMapper() 
	{
		if (Math.random() > 0.8) 
		{
			error_code = true;
		}
	}

	public void map(LongWritable ran, Text line,
			OutputCollector<Text, IntWritable> out, Reporter arg3)
			throws IOException 
	{
		try 
		{
			if (line != null && !line.toString().equals("")) 
			{
				String sep_line = line.toString();
				String year = "";
				try 
				{
					year = sep_line.substring(start_year, End_year);
				}
				catch (Exception e) 
				{
					System.out.println(e.getMessage());
				}
				String Temp = sep_line.substring(13, 19);

				IntWritable temp_int = new IntWritable();

				if (error_code) 
				{

					temp_int.set(Integer.parseInt(Temp.trim()) + 15);
				} 
				else 
				{
					temp_int.set(Integer.parseInt(Temp.trim()));
				}

				if (temp_int != null && year != null) 
				{
					out.collect(new Text(year), temp_int);
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
