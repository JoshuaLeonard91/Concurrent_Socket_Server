import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Host {
    private static int i = 0;

    public static void main(String[] args) {
        if (args.length < 1)
            return;

        int port = Integer.parseInt(args[0]);

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Connection Successful with user: /" + socket.getInetAddress());

                new ServerThread(socket).start();
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static class ServerThread extends Thread {
        private Socket clientSocket;

        public ServerThread(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try (
                    InputStream input = clientSocket.getInputStream();
                    OutputStream output = clientSocket.getOutputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    PrintWriter writer = new PrintWriter(output, true)) {
                String text;
                text = reader.readLine();

                if (text.equals("1")) {
                    writer.println(new Date().toString());
                }
                if (text.equals("2")) {
                    RuntimeMXBean mxBean = ManagementFactory.getRuntimeMXBean();
                    writer.println(mxBean.getUptime());
                }
                if (text.equals("3")) {
                    Runtime rt = Runtime.getRuntime();
                    long total_mem = rt.totalMemory();
                    long free_mem = rt.freeMemory();
                    long used_mem = total_mem - free_mem;
                    writer.println(String.valueOf(used_mem));
                }
                if (text.equals("4")) {

                    // NetStat
                    String s4 = "";
                    Process p = Runtime.getRuntime().exec("netstat -a"); // a to ef
                    BufferedReader r4 = new BufferedReader(new InputStreamReader(p.getInputStream()));

                    while ((s4 = r4.readLine()) != null) {
                        writer.println(s4);
                        writer.flush();
                    }
                    writer.println("\u0004");
                    p.destroy();
                }
                if (text.equals("5")) {

                    // current users
                    String s5 = "";
                    Process p = Runtime.getRuntime().exec("w");
                    BufferedReader r5 = new BufferedReader(new InputStreamReader(p.getInputStream()));

                    while ((s5 = r5.readLine()) != null) {
                        writer.println(s5);
                        writer.flush();
                    }
                    writer.println("\u0004");
                    p.destroy();
                }
                if (text.equals("6")) {

                    // TaskList
                    String s6 = "";
                    Process p = Runtime.getRuntime().exec("ps -ef");
                    BufferedReader r6 = new BufferedReader(new InputStreamReader(p.getInputStream()));

                    while ((s6 = r6.readLine()) != null) {
                        writer.println(s6);
                        writer.flush();
                    }
                    writer.println("\u0004");
                    p.destroy();
                }
                if (text.equals("7")) {
                    System.out.println("Program Ending!");
                    clientSocket.close();
                    return;
                }
            } catch (IOException ex) {
                System.out.println("Error handling client: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
