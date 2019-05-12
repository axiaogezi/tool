package http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * 获取远程请求IP地址
 */
public class IPUtils {

    protected static Logger log = LoggerFactory.getLogger(IPUtils.class);
    /**
     * 获取请求的真实IP地址(适用于公网和局域网)
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ipIsBlank(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ipIsBlank(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipIsBlank(ip)){
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if(ipIsBlank(ip)){
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if(ipIsBlank(ip)){
            ip = request.getRemoteAddr();
        }
        // 如果是多级代理，那么取第一个ip为客户ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
        }
        return ip;
    }

    /**
     * 获取本机IP地址
     * @return
     */
    public static String getIpAddress() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                } else {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        if (ip != null && ip instanceof Inet4Address) {
                            return ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("IP地址获取失败", e);
        }
        return "0.0.0.0";
    }

    /**
     * ip是否为空
     * @param ip
     * @return
     */
    private static Boolean ipIsBlank(String ip){
        if(null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            return true;
        }
        return false;
    }
}
