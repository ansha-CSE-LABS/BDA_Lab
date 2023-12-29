import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MinDriver {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Please provide the input and output parameters");
            System.exit(-1);
        }

        try (Job job = new Job()) {
            job.setJarByClass(MinDriver.class);
            job.setJobName("Minimum Salary");

            FileInputFormat.addInputPath(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]));

            job.setMapperClass(MinMapper.class);
            job.setReducerClass(MinReducer.class);

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);

            System.exit(job.waitForCompletion(true) ? 0 : 1);
        }
    }
}
