package mybatis.services;

import mybatis.mappers.sunrisesunset.SunriseSunsetMapper;
import mybatis.model.sunrisesunset.external.Results;
import mybatis.model.sunrisesunset.external.SunriseSunsetRoot;
import mybatis.model.sunrisesunset.internal.DayLengthComparison;
import mybatis.model.sunrisesunset.internal.SunriseSunsetOverview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SunriseSunsetService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    SunriseSunsetMapper sunrisesunsetMapper;

    public SunriseSunsetRoot searchSunriseSunset(double lat, double lng, boolean persist) {

        String fQuery = "https://api.sunrise-sunset.org/json?lat="+lat+"&lng="+lng;
                SunriseSunsetRoot response = restTemplate.getForObject(
                fQuery, SunriseSunsetRoot.class);
// make overview object and set values for pojo. Used to make database
        SunriseSunsetOverview sunriseSunsetOverview = new SunriseSunsetOverview();
        sunriseSunsetOverview.setLatitude(lat);
        sunriseSunsetOverview.setLongitude(lng);
        sunriseSunsetOverview.setDay_length(response.getResults().getDay_length());

        System.out.println("********* Got results **********");
        if(persist){
            insertSummary(sunriseSunsetOverview);
        }
        System.out.println("********* returning response ************");
        return  response;

    }

    public DayLengthComparison compareDayLength(double lat1, double lng1, double lat2, double lng2) {
        SunriseSunsetRoot response1 = searchSunriseSunset(lat1,lng1, false);
        SunriseSunsetRoot response2 = searchSunriseSunset(lat2, lng2,false);

        DayLengthComparison obj = new DayLengthComparison();

        obj.setDayLength1(response1.getResults().getDay_length());
        obj.setDayLength2(response2.getResults().getDay_length());

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        obj.setLat1(lat1);
        obj.setLng1(lng1);

        obj.setLat2(lat2);
        obj.setLng2(lng2);
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = format.parse(obj.getDayLength1());
            date2 = format.parse(obj.getDayLength2());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long difference = date2.getTime() - date1.getTime();
        difference = difference/1000;
        int hours = Math.abs((int) difference/(60*60));
        int mins = Math.abs((int) difference/60);
        int seconds = Math.abs((int) difference%60);
        if(difference>0){
            obj.setCompareResult("Day length of location 2 is " + hours + ":" + mins + ":" + seconds + " longer than location 1");
        }else if(difference<0){

            obj.setCompareResult("Day length of location 1 is " + hours + ":" + mins + ":" + seconds + " longer than location 2");
        }
        System.out.println("time difference is : "+ difference);

        return obj;

    }
    public void insertSummary(SunriseSunsetOverview result){

        sunrisesunsetMapper.insertSummary(result);


    }
}
