package mybatis.mappers.cryptocompare;

import mybatis.model.cryptocompare.CryptoCompareSingle;
import mybatis.model.cryptocompare.histohour.internal.DataHourSummary;
import mybatis.model.cryptocompare.histohour.internal.SqlDataSummary;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface CryptoCompareMapper {

    String INSERT_SINGLE = ("INSERT INTO `mybatis-test`.`crypto_compare` (`fsym`, `USD`, `EUR`,`GBP`,DKK`) " +
            "VALUES (#{fsym}, #{USD}, #{EUR},#{GBP},#{DKK};");

    String GET_FSYM_HOUR = ("SELECT * FROM `mybatis-test`.historical_hour where fsym = #{fsym}");
    String INSERT_HOURSUMMARY = ("INSERT INTO `mybatis-test`.historical_hour (fsym,tsym,time,dateTime, close, open, high, low) " +
            "VALUES (#{fsym}, #{tsym},#{time},#{dateTime},#{close}, #{open}, #{high},#{low})");

    @Insert(INSERT_HOURSUMMARY)
    public long insertHourSummary(DataHourSummary result);

    CryptoCompareSingle getSingle(String s);

    @Insert(INSERT_SINGLE)
    void insertSingle(CryptoCompareSingle compareSingle);
    @Select(GET_FSYM_HOUR)
    ArrayList<SqlDataSummary> getHourDataByFsym(String fsym);


}
