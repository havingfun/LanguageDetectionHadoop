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
import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URLDecoder;
import java.util.StringTokenizer;

import javax.security.auth.login.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class LanguageMapper extends MapReduceBase implements
                Mapper<LongWritable, Text, Text, Text> {

        private Text languagekey = new Text();
        private Text languagepost = new Text();
        private Detector detector;
        
        
        private static int loaded;
        // It checks whether the profiles for Language Detection are already loaded or not
        private static String profilepath;
        // It is the Path for Language Profiles
        private int messageField;
        private int idField;
        // Number of the field which is to be checked for language
        
        
        public void configure(JobConf job) {
            loaded = Integer.parseInt(job.get("Loaded"));
            profilepath = job.get("ProfilesPath");
            messageField = Integer.parseInt(job.get("MessageFieldNumber"));
            idField = Integer.parseInt(job.get("IdFieldNumber"));
        }
        
        @Override
        public void map(LongWritable key, Text value,
                        OutputCollector<Text, Text> outputCollector, Reporter reporter)
                        throws IOException {
            String res = value.toString();
            System.out.println(res);
            String[] splitted = res.split("\t");
        try
        {
            if(loaded==0)
            {
                DetectorFactory.loadProfile(profilepath);
                loaded = 1;
            }
            detector = DetectorFactory.create();
            if(splitted.length>1)
            {
            
              detector.append(splitted[messageField]);
              //if(splitted[1].trim().length()>5)  Less than 5 chars can miss the required content to detect a language
              //{
            
                languagekey.set(detector.detect());
                languagepost.set("[["+splitted[idField]+"]] ");
                outputCollector.collect(languagekey,languagepost);
              //}
             }
              else
              {
                    System.out.println("Either ID or the Message is Missing");
              }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
