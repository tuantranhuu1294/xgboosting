package vn.viettel.xgboost;

import ml.dmlc.xgboost4j.java.DMatrix;
import ml.dmlc.xgboost4j.java.XGBoostError;
import org.apache.commons.configuration.ConfigurationException;

import java.util.Random;

/**
 * Created by huutuan on 04/06/2017.
 */
public class XgBoostTrainerTest {

    @org.junit.Test
    public void testTrainFromFile() throws XGBoostError, ConfigurationException {
        XgBoostTrainer trainer = new XgBoostTrainer("config.properties");
        trainer.train("data/train.csv", "data/test.csv", "data/model.bin");
        System.out.println("Done");
    }

    @org.junit.Test
    public void testTrainFromDMatrix() throws XGBoostError, ConfigurationException {
        XgBoostTrainer trainer = new XgBoostTrainer("config.properties");
        DMatrix trainMatrix = initDMatrix(100, 5, true, 2);
        DMatrix testMatrix = initDMatrix(20, 5, true, 2);

        trainer.train(trainMatrix, testMatrix, "data/model.bin");
        System.out.println("DONE");
    }

    public DMatrix initDMatrix(int nrow, int ncol, boolean withLabel, int numLabels) throws XGBoostError {
        float[] data0 = new float[nrow * ncol];
        //put random nums
        Random random = new Random();
        for (int i = 0; i < nrow * ncol; i++) {
            data0[i] = random.nextFloat();
        }

        DMatrix dmat0 = new DMatrix(data0, nrow, ncol);

        //create label
        if (withLabel) {
            float[] label0 = new float[nrow];
            for (int i = 0; i < nrow; i++) {
                label0[i] = (float) random.nextInt(numLabels);
            }
            dmat0.setLabel(label0);
        }

        return dmat0;
    }
}
