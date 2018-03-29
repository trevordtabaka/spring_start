package mybatis.model.cryptocompare;

import java.util.HashMap;

public class TestCryptoResponse {

    HashMap<String, Double> data = new HashMap();

    public TestCryptoResponse() {
    }

    public TestCryptoResponse(HashMap<String, Double> data) {
        this.data = data;
    }

    public HashMap<String, Double> getData() {
        return data;
    }

    public void setData(HashMap<String, Double> data) {
        this.data = data;
    }
}
