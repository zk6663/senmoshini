import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 杨炯昊 on 2019/11/1.
 */
public class test {

    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ss = sdf.format(date);
        System.out.println(ss);
    }
}
