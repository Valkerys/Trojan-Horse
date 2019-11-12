
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Carlos
 */
public class TrojanClient
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {
        // TODO code application logic here
        String host = "127.0.0.1";
        String port = "6013";
        String command = "C:\\Program Files (x86)\\Notepad++\\notepad++.exe";
        
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        
        try
        {
            echoSocket = new Socket(host, Integer.parseInt(port));
            out = new PrintWriter(echoSocket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        }
        catch (Exception e)
        {
            System.err.println("ERRROR!!!");
        }
        
        out.println(command);
        String str = "";
        String s = "";
        while ((str = in.readLine()) != null)
        {
            s = s + str + "\n";
        }
        System.out.println(s);
        
        out.close();
        in.close();
        echoSocket.close();
    }
    
}
