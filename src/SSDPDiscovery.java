import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.io.IOException;
import java.net.*;

public class SSDPDiscavery implements Runnable {
    private String searchType;
    private boolean verbose;

    public SSDPDiscavery(String searchType, boolean verbose) {
        this.searchType = searchType;
        this.verbose = verbose;
    }

    @Override
    public void run() {
        try {
            DiscoveryLoop();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void DiscoveryLoop() throws IOException, InterruptedException {
        String data = "M-SEARCH * HTTP/1.1\r\n"+
                      "Host: 239.255.255.250:1900\r\n" +
                      "ST: " + searchType +"\r\n"+
                      "Man: \"ssdp:discover\"\r\n" +
                      "MX: 3\r\n\n";
                ;

        byte[] sendData;
        byte[] buffer = new byte[1024];
        String msg = "";

        sendData = data.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("239.255.255.250"), 1900);
        DatagramSocket clientSocket = new DatagramSocket();

        while (true){
            clientSocket.send(sendPacket);
            clientSocket.setSoTimeout(1000);
            while (msg != null){
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                try {
                    clientSocket.receive(packet);
                }catch (SocketTimeoutException e) {
                     break;
                }
                if(packet.getLength() > 0){
                    msg = new String(packet.getData(), packet.getOffset(), packet.getLength());
                    if(verbose == true){
                        System.out.println(msg);
                    }else{
                        System.out.println("Discovered device: " + packet.getAddress().getHostAddress());
                    }

                }


            }
            System.out.println();
            Thread.sleep(2000);
        }

    }
}
