package mybatis.mappers.cryptocompare;

import mybatis.model.cryptocompare.CryptoCompareSingle;
import mybatis.model.cryptocompare.histohour.DataHourSummary;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CryptoCompareMapper {

    String INSERT_SINGLE = ("INSERT INTO `mybatis-test`.`crypto_compare` (`fsym`, `USD`, `EUR`,`GBP`,DKK`) " +
            "VALUES (#{fsym}, #{USD}, #{EUR},#{GBP},#{DKK};");


    String INSERT_HOURSUMMARY = ("INSERT INTO `mybatis-test`.crypto_compare (fsym,tsym,time, close, open, high, low) " +
            "VALUES (#{fsym}, #{tsym},#{time}, #{close}, #{open}, #{high},#{low})");


    @Insert(INSERT_HOURSUMMARY)
    public int insertHourSummary(DataHourSummary result);
    CryptoCompareSingle getSingle(String s);

    @Insert(INSERT_SINGLE)
    void insertSingle(CryptoCompareSingle compareSingle);
}
