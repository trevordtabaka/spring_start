package mybatis.services.User;

import mybatis.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
public class SecurityService {

    @Autowired
    UserMapper userMapper;

    public String generateApiKey(int length) throws NoSuchAlgorithmException {

        SecureRandom random = new SecureRandom();
        byte [] bytes = new byte[length/8];
        random.nextBytes(bytes);

        return DatatypeConverter.printHexBinary(bytes).toLowerCase();
    }

    public boolean authenticate(String key){

        try{
            int x = userMapper.authenticate(key);

            if(x==1){
                return true;
            }
            return false;

        }catch(Exception e){
            return false;
        }



    }

}
