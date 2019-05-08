package com.xl.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-05-09
 * @Time: 17:20
 * To change this template use File | Settings | File Templates.
 */
public class IPUtil {
    public static String getIp4() throws SocketException {
        String result = null;
        Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
            Enumeration addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                ip = (InetAddress) addresses.nextElement();
                if (ip != null && ip instanceof Inet4Address && !ip.getHostAddress().startsWith("127.0") && ip.getHostAddress().matches(RegexUtil.IP)) {
                    result = ip.getHostAddress();
                }
            }
        }
        return result;
    }
}
