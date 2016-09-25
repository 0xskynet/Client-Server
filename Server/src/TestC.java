import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class TestC {
    static PrintWriter writer;
    ServerSocket server;
    Socket client;
    BufferedReader in = null;
    Socket serv;
    NewJFrame gui;
    TestC (NewJFrame gui) throws IOException, Exception  {
        this.gui = gui;
        init();
    }

    private void init() throws IOException, Exception {
        server = new ServerSocket(7888); // 7888
        while(true) {
        try {
                System.out.println("LIstening the connection");
                serv = server.accept();
                listen();
                writer = new PrintWriter(serv.getOutputStream(), true);
                writer.flush();
                send("ACK|CON1|z");
        }
        catch (IOException e) {
            System.out.println("System is closed");
            serv.close();
        }
        }
    }
    
    private void listen() throws Exception {
        in = new BufferedReader(new InputStreamReader(serv.getInputStream()));
	new Thread() {
            @Override
	    public void run() {
                String response="";
                try {
                    while( (response = in.readLine()) != null ) {
                            if (response.length() == 0) continue; 
                            if (response == null) {
                                break;
                            }
                        try {
                            getdata(response);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(TestC.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(TestC.class.getName()).log(Level.SEVERE, null, ex);
                    try {
                        throw new IOException();
                    } catch (IOException ex1) {
                        Logger.getLogger(TestC.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
	    }
	}.start();
    }

    private void getdata(String s) throws IOException, InterruptedException {
        System.out.println("REcieved : " + s);
            FTP ftp = new FTP();
            String first = "";
            String parts[] = s.split("\\|");
            try {
                first = parts[0];
            } catch (Exception e) {
            }
            if(first.equals("FTP")) {
                send(ftp.handler(parts));  
            }
    }

    public static void send(String s) {
        try{
        System.out.println("Sent : " + s);
            writer.println(s);
            writer.flush();
        }
        catch(Exception e) {
        }
    }

    
    }
