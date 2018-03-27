package mybatis.mappers.sunrisesunset;

import mybatis.model.sunrisesunset.external.SunriseSunsetRoot;
import mybatis.model.sunrisesunset.internal.SunriseSunsetOverview;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface SunriseSunsetMapper {

//
    String INSERT_SUMMARY = ("INSERT INTO `mybatis-test`.`sunset_sunrise_results`(`latitude`, `longitude`, `day_length`) " +
                    "VALUES (#{latitude}, #{longitude}, #{day_length});");

    @Insert(INSERT_SUMMARY)
    public int insertSummary(SunriseSunsetOverview result);


}
