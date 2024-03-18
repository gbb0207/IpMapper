import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.xyf.util.IpTransformAddressUtil;
import org.junit.Test;

import java.io.IOException;

public class IpTest {

    @Test
    public void test1() throws IOException, GeoIp2Exception {
        String ip = "171.113.221.152";

        String country = IpTransformAddressUtil.getCountry(ip);
        System.out.println(country);

        String city = IpTransformAddressUtil.getCity(ip);
        System.out.println(city);

        String province = IpTransformAddressUtil.getProvince(ip);
        System.out.println(province);

        String area = IpTransformAddressUtil.getArea(ip);
        System.out.println(area);
    }
}
