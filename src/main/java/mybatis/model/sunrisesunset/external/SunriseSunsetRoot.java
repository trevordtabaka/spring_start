package mybatis.model.sunrisesunset.external;

import mybatis.model.sunrisesunset.external.Results;

public class SunriseSunsetRoot {

    Results results;
    String status;

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
