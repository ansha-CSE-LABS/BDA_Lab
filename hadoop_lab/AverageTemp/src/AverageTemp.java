import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class AverageTemp {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Please provide the input and output parameters");
            System.exit(-1);
        }
        
        try (Job job = new Job()) {
            job.setJarByClass(AverageTemp.class);
            job.setJobName("Average temperature");

            FileInputFormat.addInputPath(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]));

            job.setMapperClass(AverageTempMapper.class);
            job.setReducerClass(AverageTempReducer.class);

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(FloatWritable.class);

            System.exit(job.waitForCompletion(true) ? 0 : 1);
        }
    }
}
