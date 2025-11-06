package com.twsny.utils.map;

import com.twsny.utils.map.dto.Circle;
import com.twsny.utils.map.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapUtil {
    private final static Logger log = LoggerFactory.getLogger(MapUtil.class);
    private static String[] directCityArray; //直辖市
    //省辖县 参考https://lbs.amap.com/faq/webservice/webservice-api/geocoding/43267
    private static String[] directDistrictArray;
    private static double EARTH_RADIUS = 6371.393;
    public static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
    public static double pi = 3.1415926535897932384626;
    public static double a = 6378245.0; //克拉索夫斯基椭球参数长半轴a
    public static double ee = 0.00669342162296594323; //克拉索夫斯基椭球参数第一偏心率平方

    static {
        directCityArray = new String[]{"北京市", "天津市", "重庆市", "上海市"};
        directDistrictArray = new String[] {
                "济源市",

                "仙桃市", "潜江市", "天门市", "神农架林区",

                "五指山市", "文昌市", "琼海市", "万宁市", "东方市", "定安县",
                "屯昌县", "澄迈县", "临高县", "琼中黎族苗族自治县", "保亭黎族苗族自治县", "白沙黎族自治县",
                "昌江黎族自治县", "乐东黎族自治县", "陵水黎族自治县",

                "阿拉尔市", "图木舒克市", "五家渠市", "北屯市",
                "铁门关市", "双河市", "可克达拉市", "昆玉市",
                "石河子市", "胡杨河市", "新星市"
        };
    }

    /**
     * 计算两个经纬度之间的直线距离
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static double getLineDistance(double lat1, double lng1, double lat2, double lng2)
    {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.abs(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b/2),2))));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 1000);
        return s;
    }

    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }

    /**
     * 获得指定中心点半径的正方形的经纬度范围
     * @param longitude
     * @param latitude
     * @param jl 单位公里
     * @return
     */
    public static Double[] findNeighPosition(Double longitude, Double latitude, Double jl)
    {
        if (longitude == null || latitude == null || jl == null)
        {
            return null;
        }
        //先计算查询点的经纬度范围
        Double r = 6371.1;//地球半径千米
        Double dis = jl;//1千米距离
        Double dlng =  2 * Math.asin(Math.sin(dis/(2*r))/Math.cos(latitude*Math.PI/180));
        dlng = dlng*180 / Math.PI;//角度转为弧度
        Double dlat = dis/r;
        dlat = dlat*180/Math.PI;
        Double minlat = latitude - dlat;
        Double maxlat = latitude + dlat;
        Double minlng = longitude - dlng;
        Double maxlng = longitude + dlng;

        Double[] arr = {minlat, maxlat, minlng, maxlng};

        return arr;
    }


    /**
     * 谷歌地图(WGS84)
     * 百度地图(bd09),腾讯地图(gcj02),高德地图(gcj02)
     * 百度地图(bd09)坐标转腾讯坐标gcj02
     * @return
     */
    public static double[] bd09_To_GCJ02(double lat, double lon){
        double x = lon - 0.0065;
        double y = lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        return new double[]{z * Math.sin(theta),z * Math.cos(theta)};
    }

    /**
     * gps84转gcj02
     * @param lat
     * @param lon
     * @return
     */
    public static double[] gps84_To_Gcj02(double lat, double lon) {
        if (outOfChina(lat, lon)) {//国界之外，不做处理
            return new double[]{lat, lon};
        }
        double dLat = transformLat(lon - 105.0, lat - 35.0);
        double dLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        return new double[]{mgLat, mgLon};
    }

    /**
     * gcj02转gps84
     * @param lat
     * @param lon
     * @return
     */
    public static double[] gcj02_To_Gps84(double lat, double lon) {
        double[] gps = transform(lat, lon);
        double lontitude = lon * 2 - gps[1];
        double latitude = lat * 2 - gps[0];
        return new double[] { latitude, lontitude };
    }

    /**
     * gcj02转bd09
     *
     * @param lat
     * @param lon
     */
    public static double[] gcj02_To_Bd09(double lat, double lon) {
        double x = lon, y = lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
        double tempLon = z * Math.cos(theta) + 0.0065;
        double tempLat = z * Math.sin(theta) + 0.006;
        return new double[]{tempLat, tempLon };
    }

    public static boolean isPointInCircle(Double centerX, Double centerY, Double radius, Double toX, Double toY) {
        Circle circle = new Circle(centerX, centerY, radius);
        Point point = new Point(toX, toY);
        return isPointInCircle(circle, point);
    }

    private static boolean isPointInCircle(Circle circle, Point point) {
        Point center = circle.getCenter();
        Double radius = circle.getRadius();
        Double fromLatitude = center.getX();
        Double fromLongitude =  center.getY();
        Double toLatitude =  point.getX();
        Double toLongitude = point.getY();
        double distance = getLineDistance(fromLatitude, fromLongitude, toLatitude, toLongitude);
        if (distance > radius) {
            return false;
        }
        return true;
    }

    public static boolean outOfChina(double lat, double lon) {
        if (lon < 72.004 || lon > 137.8347) return true;
        if (lat < 0.8293 || lat > 55.8271) return true;
        return false;
    }

    public static double[] transform(double lat, double lon) {
        if (outOfChina(lat, lon)) {
            log.warn("outOfChina, lat: {}, lon: {}", lat, lon);
            return new double[] { lat, lon };
        }
        double dLat = transformLat(lon - 105.0, lat - 35.0);
        double dLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        return new double[] { mgLat, mgLon };
    }

    private static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y
                + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    private static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1
                * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0 * pi)) * 2.0 / 3.0;
        return ret;
    }

    public static void main(String[] args) {
        double distance = getLineDistance(23.62, 103.12, 25.62, 123.1);
        System.out.println("distance: " + distance);
    }
}
