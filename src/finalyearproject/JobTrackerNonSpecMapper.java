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
		Mapper<LongWritable, Text, Text, IntWritable> {

	public static int start_year = 0, End_year = 7;

	@Override
	public void map(LongWritable ran, Text line,
			OutputCollector<Text, IntWritable> out, Reporter arg3)
			throws IOException {
		try {
			if (line != null) {
				String sep_line = line.toString();
				String year = sep_line.substring(start_year, End_year);
				String Temp = sep_line.substring(End_year);

				// IntWritable year_int=new IntWritable();
				IntWritable temp_int = new IntWritable();

				// year_int.set(Integer.parseInt(year.trim()));

				temp_int.set(Integer.parseInt(Temp.trim()));
				if (temp_int != null && year != null)

					out.collect(new Text(year), temp_int);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
