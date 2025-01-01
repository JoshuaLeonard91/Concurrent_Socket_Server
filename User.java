import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class User {
    private static Scanner scnr = new Scanner(System.in);
    private static String hostname;
    private static int port;
    //private static Thread[] start ;// new Thread[100];


    public static void main(String[] args) throws IOException {
        if (args.length < 2) return;

        hostname = args[0];
        port = Integer.parseInt(args[1]);
        Socket socket = null; //= new Socket("127.0.0.1", 1002);
        while (true) {
            String text;
            PrintMenu();
            System.out.println("Enter Menu Item!");
            String menuInput = scnr.nextLine();

            if (menuInput.equals("1")) {
                System.out.println("How many times do you want to run this command?\nEnter only integers!\n");
                int n = scnr.nextInt();
                double s = System.currentTimeMillis();

                Thread[] start = new Thread[n];
                for (int i = 0; i < n; i++) {
                    Thread date = new Thread(dateTime());
                    start[i] = date;
                }
                for(int i = 0;i<n;i++){
                    start[i].start();
                }
                timeFunction(n,s);
            }
            if (menuInput.equals("2")) {

                System.out.println("How many times do you want to run this command?\nEnter only integers!\n");
                int n = scnr.nextInt();
                double s = System.currentTimeMillis();
                Thread[] start = new Thread[n];
                // Uptime
                for (int i = 0; i < n; i++) {
                   Thread upTime = new Thread(upTime());
                   start[i] = upTime;
                }
                for(int i = 0;i<n;i++){
                    start[i].start();
                }

                timeFunction(n, s);
            }
            if (menuInput.equals("3")) {
                System.out.println("How many times do you want to run this command?\nEnter only integers!\n");
                int n = scnr.nextInt();
                double s = System.currentTimeMillis();

                Thread[] start = new Thread[n];
                for (int i = 0; i < n; i++) {
                    Thread mem = new Thread(mem(n));
                    start[i] = mem;
                }
                for(int i = 0;i<n;i++){
                    start[i].start();
                }
                timeFunction(n, s);
            }
            if (menuInput.equals("4")) {
                System.out.println("How many times do you want to run this command?\nEnter only integers!\n");
                int n = scnr.nextInt();
                double s = System.currentTimeMillis();

                Thread[] start = new Thread[n];
                for (int i = 0; i < n; i++) {
                    Thread netstat = new Thread(netstat(n));
                    start[i] = netstat;
                }
                for(int i = 0;i<n;i++){
                    start[i].start();
                }
                timeFunction(n, s);
            }
            if (menuInput.equals("5")) {
                System.out.println("How many times do you want to run this command?\nEnter only integers!\n");
                int n = scnr.nextInt();
                double s = System.currentTimeMillis();
                Thread[] start = new Thread[n];
                for (int i = 0; i < n; i++) {
                    Thread tasklist = new Thread(taskList());
                    start[i] = tasklist;
                }
                for(int i = 0;i<n;i++){
                    start[i].start();
                }

                timeFunction(n, s);
            }
            if (menuInput.equals("6")) {
                System.out.println("How many times do you want to run this command?\nEnter only integers!\n");
                int n = scnr.nextInt();
                double s = System.currentTimeMillis();
                Thread[] start = new Thread[n];
                for (int i = 0; i < n; i++) {
                    Thread currentUsers = new Thread(currentUsers(n));
                    start[i] = currentUsers;
                }
                for(int i = 0;i<n;i++){
                    start[i].start();
                }
                timeFunction(n, s);
            }
            if (menuInput.equals("7")) {
                socket = new Socket(hostname, port);
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                text = "7";
                writer.println(text);
                writer.println(0);
                break;
            }
        }
        System.out.println("Program Ending!");
    }

    private static Runnable currentUsers(int n) throws IOException {
        String text;
        Socket socket = new Socket(hostname, port);
        InputStream input = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        OutputStream output = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(output, true);
        text = "6";
        // current user
        writer.println(text);
        String g6 = null;
        while (!(g6 = reader.readLine()).equals("\u0004")) {
            System.out.println(g6);
        }
        socket.close();
        return null;
    }

    private static Runnable taskList() throws IOException {
        String text;
        Socket socket = new Socket(hostname, port);
        InputStream input = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        OutputStream output = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(output, true);
        text = "5";
        // tasklist
        writer.println(text);
        String g5 = null;
        while (!(g5 = reader.readLine()).equals("\u0004")) {
            System.out.println(g5);
        }
        return null;
    }

    private static Runnable netstat(int n) throws IOException {
        String text;
        Socket socket = new Socket(hostname, port);
        InputStream input = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        OutputStream output = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(output, true);
        text = "4";
        // NetStat
        writer.println(text);
        String g4 = null;
        while (!(g4 = reader.readLine()).equals("\u0004")) {
            System.out.println(g4);
        }
        socket.close();
        return null;
    }

    private static Runnable mem(int n) throws IOException {
        String text;
        Socket socket = new Socket(hostname, port);
        InputStream input = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        OutputStream output = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(output, true);

        text = "3";
        // mem
        writer.println(text);

        String memoryUse = reader.readLine();
        System.out.println(memoryUse + " bytes");
        socket.close();
        return null;
    }

    private static Runnable upTime() throws IOException {
        String text;
        Socket socket = new Socket(hostname, port);
        InputStream input = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        OutputStream output = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(output, true);

        text = "2";
        writer.println(text);
        String uptime = reader.readLine(); // Get the uptime in millisecond
        long milliseconds = Long.parseLong(uptime); // Convert milllisecodn to Minutes and seconds
        long minutes = (milliseconds / 1000) / 60;
        long seconds = (milliseconds / 1000) % 60;
        System.out.println(minutes + " minutes and "
                + seconds + " seconds.");
        socket.close();
        return null;
    }

    private static Runnable dateTime() throws IOException {
        String text;
        Socket socket = new Socket(hostname, port);
        InputStream input = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        OutputStream output = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(output, true);

        text = "1";
        // Current Date and time
        writer.println(text);
        String time = reader.readLine();
        System.out.println(time);
        socket.close();
        return null;
    }


    public static void PrintMenu() {
        System.out.println("\nEnter one of the following commands:");
        System.out.println("\n1) Host Current Date/Time");
        System.out.println("2) Host Uptime");
        System.out.println("3) Host Momory Use");
        System.out.println("4) Host Netstat");
        System.out.println("5) Host Current Users");
        System.out.println("6) Host Ruuning Processes");
        System.out.println("7) Exit");

        System.out.print("Enter your choice: ");

    }

    private static void timeFunction(int n, double s) {
        double d = (System.currentTimeMillis() - s);
        System.out.println("Average Time: " + d / n + "ms");
        System.out.println("Total Time: " + d + "ms");
        scnr = new Scanner(System.in);
    }
}