import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

/**
 * Created by jaro on 27.05.16.
 *
 */
public class main {
    public static void main(String[] args) throws URISyntaxException {
        WebSocketClient wsc = new WebSocketClient(new URI("ws://localhost:8080")) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                System.out.println("opened!!!");
            }

            @Override
            public void onMessage(String s) {
                byte[] bytes = s.getBytes();

                ByteBuffer bb = ByteBuffer.wrap(bytes);

                double x, y, z, ampl, freq;

                x = bb.getDouble();
                y = bb.getDouble();
                z = bb.getDouble();
                ampl = bb.getDouble();
                freq = bb.getDouble();

                System.out.println("[" + x + "," + y + "," + z + "] freq:" + freq + "; ampl:" + ampl);
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                System.out.println("closed!!!");
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        };

        wsc.connect();
    }

    static void out(String s) {
        System.out.println(s);
    }
}
