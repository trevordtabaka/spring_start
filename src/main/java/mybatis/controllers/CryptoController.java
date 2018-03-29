package mybatis.controllers;

import mybatis.model.cryptocompare.CryptoCompareRoot;
import mybatis.model.cryptocompare.TestCryptoResponse;
import mybatis.model.cryptocompare.histohour.HistoHourRoot;
import mybatis.services.cryptocompare.CryptoCompareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cryptocompare")
public class CryptoController {

    @Autowired
    CryptoCompareService cryptoCompareService;

    /**
     *
     * @param fsyms - You can specify the cryptocurrencies you want to query separated by commas. i.e. BTC,ETH,...
     * @param tsyms- You can specify the fiatcurrencies you want to compare against separated by commas. i.e. USD,EUR,...
     * @param persist - Persist set to true will notify the service that you wish to store the data into your database
     * @return- Returns a call to the method getMultiData() in the service package with the specified variables
     */
    @RequestMapping("/")
    public CryptoCompareRoot getMultiData(@RequestParam(value="fsyms", defaultValue="BTC,ETH,LTC") String fsyms,
                                        @RequestParam(value="tsyms", defaultValue="USD,EUR") String tsyms,
                                        @RequestParam(value="p", defaultValue="true") boolean persist) {
        return cryptoCompareService.getMultiData(fsyms, tsyms, persist);
    }

    /**
     *
     * @param fsym - You can specify the cryptocurrency (only one) you want to query. i.e. BTC
     * @param persist - Persist set to true will notify the service that you wish to store the data into your database
     * @return- Returns a call to the method getSingleData() in the service package with the specified variables
     */

    @RequestMapping("/test")
    public TestCryptoResponse getSingleData(@RequestParam(value="fsym", defaultValue="BTC") String fsym,
                                            @RequestParam(value="tsym", defaultValue="ETH") String tsym,
                                            @RequestParam(value="p", defaultValue="true") boolean persist) {
        return cryptoCompareService.getSingleData(fsym,tsym,persist);
    }
    /**
     *
     * @param fsym - You can specify the cryptocurrencies you want to query separated by commas. i.e. BTC,ETH,...
     * @param tsym- You can specify the fiatcurrencies you want to compare against separated by commas. i.e. USD,EUR,...
     * @param persist - Persist set to true will notify the service that you wish to store the data into your database
     * @return- Returns a call to the method getMultiData() in the service package with the specified variables
     */
    @RequestMapping("/")
    public HistoHourRoot getHistoHour(@RequestParam(value="fsym", defaultValue="BTC") String fsym,
                                      @RequestParam(value="tsym", defaultValue="USD,EUR,DKK") String tsym,
                                      @RequestParam(value="e", defaultValue="CCCAGG") String e,
                                      @RequestParam(value="extraParams", defaultValue="NotAvailable") String extraParams,
                                      @RequestParam(value="sign", defaultValue="false") boolean sign,
                                      @RequestParam(value="limit", defaultValue="168") int limit,
                                      @RequestParam(value="persist", defaultValue="true") boolean persist) {
        return cryptoCompareService.getHistoHour(fsym, tsym, e, extraParams, sign, limit, persist);
    }

}
