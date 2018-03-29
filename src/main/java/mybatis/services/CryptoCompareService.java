package mybatis.services;

import mybatis.mappers.cryptocompare.CryptoCompareMapper;
import mybatis.model.cryptocompare.CryptoCompareRoot;
import mybatis.model.cryptocompare.CryptoCompareSingle;
import mybatis.model.cryptocompare.TestCryptoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
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
}
