package vn.viettel.xgboost;

import ml.dmlc.xgboost4j.java.Booster;
import ml.dmlc.xgboost4j.java.DMatrix;
import ml.dmlc.xgboost4j.java.XGBoost;
import ml.dmlc.xgboost4j.java.XGBoostError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * Created by huutuan on 04/06/2017.
 */
public class XgBoostPredictor {

    private String modelPath;
    private Booster booster;

    private static final Logger LOG = LogManager.getLogger(XgBoostPredictor.class);

    public XgBoostPredictor() {
    }

    public XgBoostPredictor(String modelPath) {
        this.modelPath = modelPath;
        try {
            loadModel(modelPath);
        } catch (XGBoostError xgBoostError) {
            xgBoostError.printStackTrace();
        }
    }

    public void loadModel(String modelPath) throws XGBoostError {
        LOG.info("Loading model from file " + modelPath + "...");
        this.booster = XGBoost.loadModel(modelPath);
        LOG.info("Model loaded successful");
    }

    public void loadModel(InputStream in) throws IOException, XGBoostError {
        LOG.info("Loading model from input stream...");
        this.booster = XGBoost.loadModel(in);
        LOG.info("Model loaded successful");
    }

    public float[] predict(DMatrix dMatrix, boolean outputMargin, int treeLimit) throws XGBoostError {

        LOG.info("Predicting...");
        float[][] predicts = booster.predict(dMatrix, outputMargin, treeLimit);
        float[] labelPredict = new float[predicts.length];
        LOG.info("Number of rows " + predicts.length);

        for (int i = 0; i < predicts.length; i++) {
            labelPredict[i] = predicts[i][0];
        }

        return labelPredict;
    }

    public float[] predict(String dataPath, boolean outputMargin, int treeLimit) throws XGBoostError {

        // Init DMatrix from file
        LOG.info("Load data from file " + dataPath);
        DMatrix data = new DMatrix(dataPath);

        return predict(data, outputMargin, treeLimit);
    }

    public float[] predict(float[] data, int nrow, int ncol, float floatMissing, boolean outputMargin, int treeLimit) throws
            XGBoostError {
        // Init DMatrix from input data
        DMatrix dMatrix = new DMatrix(data, nrow, ncol, floatMissing);

        return predict(dMatrix, outputMargin, treeLimit);
    }


    public float linePredict(String line, boolean outputMargin, int treeLimit) throws XGBoostError {

        // Write to temp file
        String tempFilePath = null;
        try {
            File tempFile = File.createTempFile("input_line", ".csv");
            tempFilePath = tempFile.getAbsolutePath();

            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            writer.write(line);
            writer.close();
        } catch (IOException e) {
            LOG.warn("Cannot write input line to temp file. Caused by " + e.getMessage(), e);
        }

        if (tempFilePath == null)
            throw new NullPointerException("File path not found");

        DMatrix dMatrix = new DMatrix(tempFilePath);
        float[] predicts = predict(dMatrix, outputMargin, treeLimit);

        return predicts[0];
    }
}
