package build.sample;

import com.whirvis.jraknet.server.*;
import com.whirvis.jraknet.identifier.MinecraftIdentifier;
import com.whirvis.jraknet.*;

import com.whirvis.jraknet.peer.*;
import com.whirvis.jraknet.protocol.ConnectionType;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import com.whirvis.jraknet.windows.UniversalWindowsProgram;

public class SampleServer {
    public static void main (String[] argv) throws Exception {
        UniversalWindowsProgram microsoftEdge = new UniversalWindowsProgram("Microsoft.MicrosoftEdge_8wekyb3d8bbwe");
        if(!microsoftEdge.setLoopbackExempt(true)) {
            System.err.println("Failed to enable loopback exemption for Microsoft Edge");
        }
        
        // Add loopback exemption for Minecraft
        if (!UniversalWindowsProgram.MINECRAFT.setLoopbackExempt(true)) {
            System.err.println("Failed to add loopback exemption for Minecraft");
        }

        // Create server
        RakNetServer server = new RakNetServer(19132, 10);
        server.setIdentifier(new MinecraftIdentifier("JRakNet Example Server", 354, "1.11", 0, 11,
            server.getGloballyUniqueId(), "New World", "Survival"));

        // Add listener
        server.addListener(new RakNetServerListener() {

            // Client connected
            @Override
            public void onConnect(RakNetServer server, InetSocketAddress address, ConnectionType connectionType) {
                System.out.println("Client from address " + address + " has connected to the server");
            }
            
            // Client logged in
            @Override
            public void onLogin(RakNetServer server, RakNetClientPeer peer) {
                System.out.println("Client from address " + peer.getAddress() + " has logged in");
            }

            // Client disconnected
            @Override
            public void onDisconnect(RakNetServer server, InetSocketAddress address, RakNetClientPeer peer, String reason) {
                System.out.println("Client from address " + address
                                + " has disconnected from the server for reason \"" + reason + "\"");
            }
            
            @Override
            public void onPing(RakNetServer server, ServerPing ping) {
                System.out.println("Client ping" + ping + " for the server");
            }

            // Packet received
            @Override
            public void handleMessage(RakNetServer server, RakNetClientPeer peer, RakNetPacket packet, int channel) {
                System.out.println("Client from address " + peer.getAddress() + " sent packet with ID "
                        + RakNet.toHexStringId(packet) + " on channel " + channel);
            }

        });

        // Start server
        server.start();
    }
}
