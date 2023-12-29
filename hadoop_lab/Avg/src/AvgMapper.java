import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AvgMapper extends Mapper<Object, Text, Text, FloatWritable> {

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String[] tokens = value.toString().split(" ");
        String indicator = tokens[0];

        float sum = 0;
        int count = 0;

        for (int i = 1; i < tokens.length; i++) {
            float temperature = Float.parseFloat(tokens[i]);
            sum += temperature;
            count++;
        }

        float average = sum / count;
        context.write(new Text(indicator), new FloatWritable(average));
    }
}
