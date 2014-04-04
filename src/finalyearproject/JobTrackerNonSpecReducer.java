package finalyearproject;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class JobTrackerNonSpecReducer extends MapReduceBase implements
		Reducer<Text, IntWritable, Text, IntWritable> {

	@Override
	public void reduce(Text year, Iterator<IntWritable> temp,
			OutputCollector<Text, IntWritable> out, Reporter arg3)
			throws IOException {
		long sum = 0;
		double avg = 0.0;
		int count = 0;
		try {
			while (temp.hasNext()) {

				out.collect(year, new IntWritable(temp.next().get()));
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
