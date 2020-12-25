package cn.cocowwy.orange.orangeorder.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * 微信openId获取工具类
 * 通过该工具类  从小程序端获得到code
 * 通过微信接口得到用户在小程序登录的唯一表示id
 * @author Cocowwy
 * @create 2020-12-12-22:18
 */
public class WxOpenIdUtil {
    public static String getOpenId(String code) {
        String appid = "wxe3d87671fede012a";
        String secret = "71f3efa12f3db40cd2b16f5f7d61e338";

        //appid和secret是开发者分别是小程序ID和小程序密钥，开发者通过微信公众平台-》设置-》开发设置就可以直接获取，
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="
                + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
        BufferedReader in = null;

        try {
            URL weChatUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = weChatUrl.openConnection();
            // 设置通用的请求属性
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            throw new IllegalArgumentException("服务端获取openId出错");
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
