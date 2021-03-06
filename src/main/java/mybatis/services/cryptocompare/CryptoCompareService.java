package mybatis.services.cryptocompare;

import mybatis.mappers.cryptocompare.CryptoCompareMapper;
import mybatis.model.cryptocompare.CryptoCompareRoot;
import mybatis.model.cryptocompare.CryptoCompareSingle;
import mybatis.model.cryptocompare.TestCryptoResponse;
import mybatis.model.cryptocompare.histohour.external.Data;
import mybatis.model.cryptocompare.histohour.internal.DataHourSummary;
import mybatis.model.cryptocompare.histohour.external.HistoHourRoot;
import mybatis.model.cryptocompare.histohour.internal.SqlDataSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.sql.SQLIntegrityConstraintViolationException;


import java.util.ArrayList;
import java.util.Map;

@Service
public class CryptoCompareService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CryptoCompareMapper cryptoCompareMapper;

    public CryptoCompareRoot getMultiData(String fsyms, String tsyms, boolean persist) {

        // Query
        String fQuery = "https://min-api.cryptocompare.com/data/pricemultifull?fsyms="+fsyms+"&tsyms="+tsyms;
        CryptoCompareRoot response = restTemplate.getForObject(
                fQuery, CryptoCompareRoot.class);
        return response;
    }

    public TestCryptoResponse getSingleData(String fsym, String tsyms, boolean persist) {

        // Query
        String fQuery = "https://min-api.cryptocompare.com/data/price?fsym="+fsym+"&tsyms="+tsyms;
        System.out.println(fQuery);

        Map<String, Double> response = restTemplate.getForObject(fQuery, Map.class);

        TestCryptoResponse t = new TestCryptoResponse();

        for(Map.Entry<String, Double> val : response.entrySet()){
            t.getData().put(val.getKey(), val.getValue());
        }

        //if(persist){
        //    cryptoCompareMapper.insertSingle(response);
        //}

        return t;
    }

    public CryptoCompareSingle insertSingle(CryptoCompareSingle compareSingle){
            cryptoCompareMapper.insertSingle(compareSingle);
        return cryptoCompareMapper.getSingle(compareSingle.getfSym());
    }

    public HistoHourRoot getHistoHour(String fsym,String tsym,String e,String extraParams,boolean sign,int limit,boolean persist) throws SQLIntegrityConstraintViolationException {

        // Query
        String fQuery = "https://min-api.cryptocompare.com/data/histohour?fsym="+fsym+"&tsym="+tsym+"&e="+e+"&extraParams="+extraParams+"&sign="+sign+"&limit="+limit+"&persist="+persist;
        System.out.println(fQuery);
        HistoHourRoot hourResponse = restTemplate.getForObject(
                fQuery, HistoHourRoot.class);
        HistoHourRoot histoHourRoot = new HistoHourRoot();

        if(persist){
            histoHourRoot.setResponse(hourResponse.getResponse());

            for (Data element : hourResponse.getData()) {
                DataHourSummary dataSummary = new DataHourSummary();
                try{

                    dataSummary.setTime(element.getTime());
                    dataSummary.setDateTime(dataSummary.getTime());
                    dataSummary.setFsym(fsym);
                    dataSummary.setTsym(tsym);
                    dataSummary.setClose(element.getClose());
                    dataSummary.setOpen(element.getOpen());
                    dataSummary.setHigh(element.getHigh());
                    dataSummary.setLow(element.getLow());


                    insertHourSummary(dataSummary);
                    System.out.println("Added not unique data " + dataSummary.getTime());
                }catch(Exception dupE){
                    System.out.println("Caught a duplicate entry");
                }



            }


        }

        return hourResponse;
    }
    public void insertHourSummary(DataHourSummary result) throws Exception
    {

        cryptoCompareMapper.insertHourSummary(result);

    }


    public ArrayList<SqlDataSummary> getHourDataByFsym(String fsym) {

        return cryptoCompareMapper.getHourDataByFsym(fsym);

    }

//    public HistoDailyRoot getHistoDaily(String fsym, String tsym, String e, String extraParams, boolean sign, int limit, boolean persist) {
//    }
}
