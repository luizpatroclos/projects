package org.prosur.catalog.ws;

import java.io.InputStream;
import java.io.OutputStream;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/** Establish a SSL connection to a host and port, writes a byte and
 * prints the response. See
 * http://confluence.atlassian.com/display/JIRA/Connecting+to+SSL+services
 */
public class TestKey {
    public static void main(String[] args) {
                /*if (args.length != 2) {
                        System.out.println("Usage: "+TestKey.class.getName()+"172.20.4.5:8443");
                        System.exit(1);
                }*/
                try {
                        SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
                        SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket("172.20.4.5", 8443);

                        InputStream in = sslsocket.getInputStream();
                        OutputStream out = sslsocket.getOutputStream();

                        // Write a test byte to get a reaction :)
                        //out.write(1);
                        
                        

                        while (in.available() > 0) {
                                System.out.print(in.read());
                        }
                        System.out.println("Successfully connected");

                } catch (Exception exception) {
                        exception.printStackTrace();
                }
        }
}