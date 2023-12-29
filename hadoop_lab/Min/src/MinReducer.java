import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MinReducer extends Reducer<Text, Text, Text, Text> {

    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        String employeeWithMinSalary = null;
        int minSalary = Integer.MAX_VALUE;

        for (Text value : values) {
            String[] parts = value.toString().split(":");
            String employee = parts[0];
            int salary = Integer.parseInt(parts[1]);

            if (salary < minSalary) {
                minSalary = salary;
                employeeWithMinSalary = employee;
            }
        }

        context.write(new Text("Employee with Minimum Salary:"), new Text(employeeWithMinSalary));
    }
}
