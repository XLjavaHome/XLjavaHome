package com.xl.base;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-05-09
 * @Time: 16:57
 * To change this template use File | Settings | File Templates.
 */
public class WebServiceTest {
    public static void main(String[] args) throws ServiceException, RemoteException {
        //远程调用路径
        String endpoint = "http://10.0.20.22/cgi-bin/fglccgi/ws/r/aws_t118?WSDL";
        String result = "call failed";
        // 创建一个服务(service)调用(call)
        Service service = new Service();
        Call call;
        // 通过service创建call对象
        call = (Call) service.createCall();
        // 设置service所在URL
        call.setTargetEndpointAddress(endpoint);
        //调用的接口地址，调用的方法名
        call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/webservices", "aws_t110b2b_ins"));
        //设置参数名dbs，第二个参数表示类型，第三个表示入参
        call.addParameter("dbs", XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter("aba01", XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter("user", XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter("date", XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter("orderno", XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter("xml", XMLType.XSD_STRING, ParameterMode.IN);
        //设置返回值类型
        call.setReturnType(XMLType.XSD_STRING);
        String dbs = "SZ36";
        String aba01 = "9101";
        String user = "admin";
        String date = "2018-05-07";
        String orderno = "YF01-180400044";
        String xml = "xml";
        result = (String) call.invoke(new Object[]{dbs, aba01, user, date, orderno, xml});// 远程调用
        System.out.println(result);
    }
}
