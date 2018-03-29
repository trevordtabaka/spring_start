package mybatis.mappers.cryptocompare;

import mybatis.model.cryptocompare.CryptoCompareSingle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CryptoCompareMapper {

    String INSERT_SINGLE = ("INSERT INTO `mybatis-test`.`crypto_compare` (`fsym`, `USD`, `EUR`,`GBP`,DKK`) " +
            "VALUES (#{fsym}, #{USD}, #{EUR},#{GBP},#{DKK};");






    CryptoCompareSingle getSingle(String s);
    @Select(INSERT_SINGLE)
    void insertSingle(CryptoCompareSingle compareSingle);
}
