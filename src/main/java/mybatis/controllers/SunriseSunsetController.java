package mybatis.controllers;
import mybatis.model.sunrisesunset.external.SunriseSunsetRoot;
import mybatis.model.sunrisesunset.internal.DayLengthComparison;
import mybatis.model.sunrisesunset.internal.SunriseSunsetOverview;
import mybatis.services.SunriseSunsetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping("/{id}")
    public SunriseSunsetOverview getById(@PathVariable(value="id")int id) {
        return sunriseSunsetService.getById(id);
    }
//    @RequestMapping("/")
//    public SunriseSunsetOverview getByLatLong(double latitude, double longitude) {
//        return sunriseSunsetService.getByLatLong(latitude,longitude);
//    }
    //Create/Post
    @RequestMapping(method = RequestMethod.POST, value = "/")
    public SunriseSunsetOverview addNew(@RequestBody SunriseSunsetOverview sunriseSunsetOverview) {
        return sunriseSunsetService.addNew(sunriseSunsetOverview);
    }
//
//    //Update
    @RequestMapping(method = RequestMethod.PATCH, value = "/")
    public SunriseSunsetOverview updateById(@RequestBody SunriseSunsetOverview sunriseSunsetOverview) {
        return sunriseSunsetService.updateById(sunriseSunsetOverview);
    }

    //Delete
    @RequestMapping(method= RequestMethod.DELETE, value="/")
    public SunriseSunsetOverview deleteById(@RequestParam(value="id")int id){
        return sunriseSunsetService.deleteById(id);
    }
}
