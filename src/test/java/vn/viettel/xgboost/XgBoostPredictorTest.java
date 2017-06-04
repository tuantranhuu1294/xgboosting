package vn.viettel.xgboost;

import ml.dmlc.xgboost4j.java.XGBoostError;

/**
 * Created by huutuan on 04/06/2017.
 */
public class XgBoostPredictorTest {

    @org.junit.Test
    public void lineTest() throws XGBoostError {
        XgBoostPredictor predictor = new XgBoostPredictor("data/model.bin");
        String line = "2:23.7 3:21.3 4:12.243 5:2.14";
        System.out.println(predictor.linePredict(line, false, 0));
    }

    @org.junit.Test
    public void fileTest() throws XGBoostError {
        XgBoostPredictor predictor = new XgBoostPredictor("data/model.bin");
        float[] result = predictor.predict("data/test.csv", false, 0);

        for (float aResult : result) {
            System.out.println(aResult);
        }
    }
}
