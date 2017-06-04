package vn.viettel.xgboost;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;

/**
 * Created by huutuan on 03/06/2017.
 */
public class Parameter {

    private HashMap<String, Object> params;
    private Configuration config;

    public Parameter() {
        this.params = new HashMap<>();
    }

    /**
     * Constructor for initialize parameters from properties config file
     *
     * @param configFile
     */
    public Parameter(String configFile) throws ConfigurationException {
        this.config = new PropertiesConfiguration(configFile);
        this.params = new HashMap<>();
        initParams();
    }

    private void initParams() {
        // General Parameters
        if (!StringUtils.isEmpty(config.getString("xgboost.param.booster")))
            params.put("booster", config.getProperty("xgboost.param.booster"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.silent")))
            params.put("silent", config.getProperty("xgboost.param.silent"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.nthread")))
            params.put("nthread", config.getInt("xgboost.param.nthread"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.num_pbuffer")))
            params.put("num_pbuffer", config.getInt("xgboost.param.num_pbuffer"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.num_feature")))
            params.put("num_feature", config.getInt("xgboost.param.num_feature"));

        // Parameters for Tree Booster
        if (!StringUtils.isEmpty(config.getString("xgboost.param.eta")))
            params.put("eta", config.getFloat("xgboost.param.eta"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.gamma")))
            params.put("gamma", config.getFloat("xgboost.param.gamma"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.max_depth")))
            params.put("max_depth", config.getInt("xgboost.param.max_depth"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.min_child_weight")))
            params.put("min_child_weight", config.getProperty("xgboost.param.min_child_weight"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.max_delta_step")))
            params.put("max_delta_step", config.getProperty("xgboost.param.max_delta_step"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.subsample")))
            params.put("xgboost.param.subsample", config.getProperty("xgboost.param.subsample"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.colsample_bytree")))
            params.put("colsample_bytree", config.getProperty("xgboost.param.colsample_bytree"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.colsample_bylevel")))
            params.put("colsample_bylevel", config.getProperty("xgboost.param.colsample_bylevel"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.lambda")))
            params.put("lambda", config.getFloat("xgboost.param.lambda"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.alpha")))
            params.put("alpha", config.getFloat("xgboost.param.alpha"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.tree_method")))
            params.put("tree_method", config.getProperty("xgboost.param.tree_method"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.sketch_eps")))
            params.put("sketch_eps", config.getProperty("xgboost.param.sketch_eps"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.scale_pos_weight")))
            params.put("scale_pos_weight", config.getProperty("xgboost.param.scale_pos_weight"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.updater")))
            params.put("updater", config.getProperty("xgboost.param.updater"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.refresh_leaf")))
            params.put("refresh_leaf", config.getProperty("xgboost.param.refresh_leaf"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.process_type")))
            params.put("process_type", config.getProperty("xgboost.param.process_type"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.grow_policy")))
            params.put("grow_policy", config.getProperty("xgboost.param.grow_policy"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.max_leaves")))
            params.put("max_leaves", config.getProperty("xgboost.param.max_leaves"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.max_bins")))
            params.put("max_bins", config.getProperty("xgboost.param.max_bins"));

        // Additional parameters for Dart Booster
        if (!StringUtils.isEmpty(config.getString("xgboost.param.sample_type")))
            params.put("sample_type", config.getProperty("xgboost.param.sample_type"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.normalize_type")))
            params.put("normalize_type", config.getProperty("xgboost.param.normalize_type"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.rate_drop")))
            params.put("rate_drop", config.getProperty("xgboost.param.rate_drop"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.one_drop")))
            params.put("one_drop", config.getProperty("xgboost.param.one_drop"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.skip_drop")))
            params.put("skip_drop", config.getProperty("xgboost.param.skip_drop"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.lambda_bias")))
            params.put("lambda_bias", config.getProperty("xgboost.param.lambda_bias"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.tweedie_variance_power")))
            params.put("tweedie_variance_power", config.getProperty("xgboost.param.tweedie_variance_power"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.objective")))
            params.put("objective", config.getString("xgboost.param.objective"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.base_score")))
            params.put("base_score", config.getProperty("xgboost.param.base_score"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.eval_metric")))
            params.put("eval_metric", config.getProperty("xgboost.param.eval_metric"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.seed")))
            params.put("seed", config.getInt("xgboost.param.seed"));

        if (!StringUtils.isEmpty(config.getString("xgboost.param.num_class")))
            params.put("num_class", config.getInt("xgboost.param.num_class"));

    }

    public void setParam(String param, Object value) {
        params.put(param, value);
    }

    public HashMap<String, Object> getParams() {
        return params;
    }
}
