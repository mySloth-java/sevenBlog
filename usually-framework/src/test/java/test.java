import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

/**
 * @author cgJavaAfter
 * @date 2023-04-24 09:22
 */
@SpringBootTest
public class test {
    //密码加密测试
//    @Autowired
//    private BCryptPasswordEncoder bc;
    @Test
    public void TestPassword(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123");
        System.out.println(encode);
    }


    @Test
    public void method(){
        int a = 0,b = 1;
        do{
            if(b %2 ==0){
                a +=b;
            }
            b++;
        }while (b<=100);
        System.out.println(a);
    }
}
