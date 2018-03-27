package mybatis.controllers;
import mybatis.model.sunrisesunset.external.SunriseSunsetRoot;
import mybatis.model.sunrisesunset.internal.DayLengthComparison;
import mybatis.services.SunriseSunsetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sunrisesunset")
public class SunriseSunsetController {

    @Autowired
    SunriseSunsetService sunriseSunsetService;

    @RequestMapping("/")
    public SunriseSunsetRoot searchSunriseSunset(@RequestParam(value="lat", defaultValue="0") double lat,
                                               @RequestParam(value="lng", defaultValue="0") double lng,
                                                 @RequestParam(value="p", defaultValue="true") boolean persist) {
        return sunriseSunsetService.searchSunriseSunset(lat, lng, persist);
    }
    @RequestMapping("/compareDayLength")
    public DayLengthComparison compareDayLength(@RequestParam(value="lat1", required = true) double lat1,
                                                @RequestParam(value="lng1", required = true) double lng1,

                                                @RequestParam(value="lat2", required = true) double lat2,
                                                @RequestParam(value="lng2", required = true) double lng2){


        return  sunriseSunsetService.compareDayLength(lat1, lng1,lat2,lng2);
    }

}
