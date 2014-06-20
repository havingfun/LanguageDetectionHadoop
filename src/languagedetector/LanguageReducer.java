/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package languagedetector;

/**
 *
 * @author rajesh
 */
import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class LanguageReducer extends MapReduceBase implements
                Reducer<Text, Text, Text, Text> {

        private Text outputKey = new Text();
        private Text outputValue = new Text();

        @Override
        public void reduce(Text LanguageKey, Iterator<Text> LanguageValues,
                        OutputCollector<Text, Text> results, Reporter reporter)
                        throws IOException {
                // in this case the reducer just creates a list so that the data can
                // used later
                String outputText = "";
                while (LanguageValues.hasNext()) {
                        Text locationName = LanguageValues.next();
                        outputText = outputText + locationName.toString() + " ,";
                }
                outputKey.set(LanguageKey.toString());
                outputValue.set(outputText);
                results.collect(outputKey, outputValue);
        }

}
