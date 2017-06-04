package vn.viettel.xgboost;

import ml.dmlc.xgboost4j.java.Booster;
import ml.dmlc.xgboost4j.java.DMatrix;
import ml.dmlc.xgboost4j.java.XGBoost;
import ml.dmlc.xgboost4j.java.XGBoostError;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

/**
 * Created by huutuan on 03/06/2017.
 */
public class XgBoostTrainer {

    private String configFile;
    private Configuration config;
    private static final Logger LOG = LogManager.getLogger(XgBoostTrainer.class);

    public XgBoostTrainer(String configFile) {
        try {
            this.configFile = configFile;
            this.config = new PropertiesConfiguration(configFile);
        } catch (ConfigurationException e) {
            LOG.error("Config file must be properties file format", e);
        }
    }

    public synchronized void train(String trainFile, String testFile, String outputModel) throws XGBoostError,
            ConfigurationException {

        if (StringUtils.isEmpty(trainFile)) {
            LOG.warn("Training file does not exists");
            return;
        }

        DMatrix trainMat = new DMatrix(trainFile);
        DMatrix testMat = null;

        if (StringUtils.isEmpty(testFile))
            LOG.warn("No input test file. Execute training without testing");
        else
            testMat = new DMatrix(testFile);

        train(trainMat, testMat, outputModel);
    }


    public synchronized void train(DMatrix trainMat, DMatrix testMat, String outputModel) throws ConfigurationException, XGBoostError {

        // Init XgBoost parameters
        Parameter params = new Parameter(configFile);

        int round = config.getInt("xgboost.param.round");
        int treeLimit = config.getInt("xgboost.param.tree_limit");

        // specify watchList
        HashMap<String, DMatrix> watches = new HashMap<>();
        watches.put("Train", trainMat);

        // Train a booster
        Booster booster = XGBoost.train(trainMat, params.getParams(), round, watches, null, null);

        // Predict test data if input test file is exists
        if (testMat != null) {
            watches.put("Test", testMat);

            float[] labels = testMat.getLabel();
            float[][] testPredicts = booster.predict(testMat, false, treeLimit);

            int trueCount = 0;
            for (int i = 0; i < testPredicts.length; i++) {
                if (labels[i] == testPredicts[i][0]) {
                    LOG.info("[true prediction] - " + labels[i] + ":" + testPredicts[i][0]);
                    trueCount++;
                }
            }

            LOG.info("===============================================");
            LOG.info("Total true label: " + trueCount + "/" + testPredicts.length);
            LOG.info("Accuracy: " + (double) trueCount / (double) testPredicts.length);
        } else {
            LOG.warn("Cannot predict data test and accuracy of model. Caused by test does not exists.");
        }

        if (!StringUtils.isEmpty(outputModel)) {
            // Save model to file
            LOG.info("Saving model to file ...");
            booster.saveModel(outputModel);
            LOG.info("Model saved");
        } else {
            LOG.warn("Cannot save model to file because output model file is empty");
        }
    }
}
