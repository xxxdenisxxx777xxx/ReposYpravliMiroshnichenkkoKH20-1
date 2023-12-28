import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] argv) {
        String host = "127.0.0.1";
        int port = 8080;

        if (argv.length >= 2)
            host = argv[1];

        if (argv.length >= 1)
            port = Integer.parseInt(argv[0]);

        try (ServerSocket server = new ServerSocket(port, 0, InetAddress.getByName(host))) {
            System.err.println("listen Server on " + host + ":" + port + "\n");

            while (true) {
                try (Socket client = server.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                     PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {

                    System.out.println("Connection from " + client.getRemoteSocketAddress());

                    StringBuilder request = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null && !line.isEmpty()) {
                        request.append(line).append("\r\n");
                    }
                    System.out.println("Received request:\n" + request.toString());

                    String httpResponse = "HTTP/1.1 200 success \r \n \r \n";
                    out.println(httpResponse);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
