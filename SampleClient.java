package build.sample;

import com.whirvis.jraknet.client.*;
import com.whirvis.jraknet.peer.*;
import com.whirvis.jraknet.protocol.ConnectionType;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class SampleClient {
    public static void main (String[] argv) throws Exception {
        // Create client
        RakNetClient client = new RakNetClient();
                
        // Add listener
        client.addListener(new RakNetClientListener() {

            // Connected to server
            @Override
            public void onConnect(RakNetClient client, InetSocketAddress address, ConnectionType connectionType) {
                System.out.println("Successfully connected to server with address " + address);
            }
            
            // Logged into server
            @Override
            public void onLogin(RakNetClient client, RakNetServerPeer peer) {
                System.out.println("Successfully logged into server");
                client.disconnect();
            }

            // Disconnected from server
            @Override
            public void onDisconnect(RakNetClient client, InetSocketAddress address, RakNetServerPeer peer, String reason) {
                System.out.println("Successfully disconnected from server with address " + address + " for reason \"" + reason + "\"");
            }

        });

        // Connect to server
        //client.connect("sg.lbsg.net", 19132);
        client.connect("gamepe.ru", 19132);
        //client.connect("minescar.com", 19138);

    }
}
