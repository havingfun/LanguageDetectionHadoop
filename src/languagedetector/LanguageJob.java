/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package languagedetector;

/**
 *
 * @author rajesh
 */
import au.com.bytecode.opencsv.CSVReader;
import java.io.FileReader;
import java.io.PrintWriter;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

public class LanguageJob {

        /**
         * @param args
         */
        public static void main(String[] args) throws Exception {
            
                JobConf conf = new JobConf(LanguageJob.class);
                conf.setJobName("LanguageClassification");

                conf.setOutputKeyClass(Text.class);
                conf.setOutputValueClass(Text.class);

                conf.setMapperClass(LanguageMapper.class);
                conf.setReducerClass(LanguageReducer.class);

                conf.setInputFormat(TextInputFormat.class);
                conf.setOutputFormat(TextOutputFormat.class);
                conf.set("Loaded", "0");
                conf.set("ProfilesPath",args[2]);
                conf.set("IdFieldNumber",args[3]);
                conf.set("MessageFieldNumber",args[4]);
                FileInputFormat.setInputPaths(conf, new Path(args[0]));
                FileOutputFormat.setOutputPath(conf, new Path(args[1]));
                JobClient.runJob(conf);

        }

}