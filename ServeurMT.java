package multithreads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServeurMT {
    public static void main(String[] args) {
        List<Socket> jouers = new ArrayList<>();
        int nbMagique = new Random().nextInt(100);
        try {
            ServerSocket ss = new ServerSocket(8090);
            while(true){
                Socket s = ss.accept();
                jouers.add(s);
                ServeurThread smt = new ServeurThread(s, jouers, nbMagique);
                smt.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
