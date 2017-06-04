package vn.viettel.xgboost;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.*;

/**
 * Created by huutuan on 04/06/2017.
 */
public class Test {

    @org.junit.Test
    public void test() throws ConfigurationException {
        Configuration config = new PropertiesConfiguration("config.properties");
        Object seed = config.getProperty("xgboost.param.seed");
        if (seed != null)
            System.out.println("Not null");
        else System.out.println("Is null");
    }

    @org.junit.Test
    public void testWriter() throws IOException {
        File tempFile = File.createTempFile("input_line", ".csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        writer.write("Hello world !");
        writer.close();

        BufferedReader reader = new BufferedReader(new FileReader(tempFile.getAbsolutePath()));
        String line = reader.readLine();
        reader.close();
        System.out.println(line);
    }
}
