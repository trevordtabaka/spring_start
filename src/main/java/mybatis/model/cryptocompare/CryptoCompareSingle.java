package mybatis.model.cryptocompare;

public class CryptoCompareSingle {

    String fSym;
    double USD;
    double EUR;
    double GBP;
    double DKK;

    public double getUSD() {
        return USD;
    }

    public void setUSD(double USD) {
        this.USD = USD;
    }

    public double getEUR() {
        return EUR;
    }

    public void setEUR(double EUR) {
        this.EUR = EUR;
    }

    public String getfSym() {
        return fSym;
    }

    public void setfSym(String fSym) {
        this.fSym = fSym;
    }

    public double getGBP() {
        return GBP;
    }

    public void setGBP(double GBP) {
        this.GBP = GBP;
    }

    public double getDKK() {
        return DKK;
    }

    public void setDKK(double DKK) {
        this.DKK = DKK;
    }
}
