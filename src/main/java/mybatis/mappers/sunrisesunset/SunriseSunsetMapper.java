package mybatis.mappers.sunrisesunset;

import mybatis.model.sunrisesunset.external.SunriseSunsetRoot;
import mybatis.model.sunrisesunset.internal.SunriseSunsetOverview;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SunriseSunsetMapper {

//
    String INSERT_SUMMARY = ("INSERT INTO `mybatis-test`.`sunset_sunrise_results`(`latitude`, `longitude`, `day_length`) " +
                    "VALUES (#{latitude}, #{longitude}, #{day_length});");

    String GET_BY_LAT_LONG = "SELECT * FROM `mybatis-test`.sunset_sunrise_results where latitude = #{arg0} AND longitude = #{arg1}";
    String GET_BY_ID = "SELECT * FROM `mybatis-test`.sunset_sunrise_results where id = #{id}";


    String INSERT_LOCATION = "INSERT INTO `mybatis-test`.SunriseSunsetOverview (lat, lng, day_length) " +
            "VALUES (#{lat}, #{lng}, #{day_length})";
    String UPDATE_LOCATION = "UPDATE `mybatis-test`.sunset_sunrise_results SET latitude = #{latitude}, " +
            "longitude = #{longitude}, day_length = #{day_length} WHERE id = #{id}";
    String DELETE_LOCATION = "DELETE FROM `mybatis-test`.sunset_sunrise_results WHERE id = #{id}";

    @Insert(INSERT_SUMMARY)
    public int insertSummary(SunriseSunsetOverview result);


    @Select(GET_BY_LAT_LONG)
    public SunriseSunsetOverview getByLatLong(double latitude, double longitude);

    @Select(GET_BY_ID)
    SunriseSunsetOverview getByID(int id);
    @Select(DELETE_LOCATION)
    void deleteLocation(int id);

    @Select(UPDATE_LOCATION)
    void updateLocation(SunriseSunsetOverview sunriseSunsetOverview);

}
