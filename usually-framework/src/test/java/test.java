import org.junit.jupiter.api.Test;

/**
 * @author cgJavaAfter
 * @date 2023-04-24 09:22
 */
public class test {
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
