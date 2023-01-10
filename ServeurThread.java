package multithreads;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class ServeurThread extends Thread {
    private Socket s;
    private List<Socket> jouers;
    private int nbMagique;
    public ServeurThread(Socket s, List<Socket> jouers, int nbMagique) {
        this.s = s;
        this.jouers = jouers;
        this.nbMagique = nbMagique;
    }
    @Override
    public void run() {
        try{
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            PrintWriter pw = new PrintWriter(os, true);
            int nbJoueur;
            pw.print("Veullez saisir votre nom: ");
            System.out.println("DEBUG");
            String jName = br.readLine();
            do{
                pw.print("Veullez tenter un nombre: ");
                nbJoueur = Integer.parseInt(br.readLine());
                if(nbJoueur == nbMagique){
                    pw.print("Bravo! vous avez trouve le nombre magique");
                    for(Socket sJoueur : jouers){
                        if(sJoueur != s){
                            OutputStream osJ = sJoueur.getOutputStream();
                            PrintWriter pwJ = new PrintWriter(os, true);
                            pwJ.print("Le joueur " + jName + " a trouve le nombre magique");
                        }
                    }
                }
                else{
                    if(nbJoueur > nbMagique)
                        pw.println("Veuiller saisir un nombre inferieur !!");
                    else
                        pw.println("Veuiller saisir un nombre superieur !!");
                    for(Socket sJouer : jouers){
                        sJouer.close();
                    }
                }
            }while(nbJoueur != nbMagique);
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
