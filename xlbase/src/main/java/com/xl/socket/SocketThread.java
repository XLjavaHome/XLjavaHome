package com.xl.socket;

import com.xl.entity.User;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SocketThread implements Runnable {
    private Socket s;
    
    SocketThread(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            InputStream is = s.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            User p = (User) ois.readObject();
            String name = p.getName();
            String psw = p.getPsw();
            Scanner sr = new Scanner(System.in);
            if ("admin".equals(name)) {
                System.out.println("用户名正确！");
                pw.write("over");
            } else {
                System.out.println("姓名是admin,年纪要大于0");
            }
            ois.close();
            br.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
