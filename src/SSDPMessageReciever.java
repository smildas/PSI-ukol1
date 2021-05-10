import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class SSDPMessageReciever implements Runnable {
    private final String SSDP_IP = "239.255.255.250";
    private final int SSDP_PORT = 1900;

    @Override
    public void run() {
        try {
            deviceDiscovery();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deviceDiscovery() throws IOException {
        byte[] buffer = new byte[1024];
        MulticastSocket socket = new MulticastSocket(SSDP_PORT);
        InetAddress multicastGroup = InetAddress.getByName(SSDP_IP);
        socket.joinGroup(multicastGroup);

        while (true) {

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String msg = new String(packet.getData(), packet.getOffset(), packet.getLength());

            System.out.println(msg);
            if ("OK".equals(msg)) {
                System.out.println("No more message. Exiting : " + msg);
                break;
            }
        }
        socket.leaveGroup(multicastGroup);
        socket.close();
    }
}
