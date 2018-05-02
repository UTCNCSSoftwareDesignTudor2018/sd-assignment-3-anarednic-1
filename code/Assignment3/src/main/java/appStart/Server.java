package appStart;

import java.io.*;
import businessLogic.WriterBusinessLogic;
import entity.Writer;

import java.net.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Server {
	public static void main(String[] args) throws IOException, ClassNotFoundException 
    {
        ServerSocket ss = new ServerSocket(5056);
        System.out.println("Server started");
        while (true) 
        {
        	Socket t = ss.accept();
            System.out.println("server connected");
            ObjectInputStream b = new ObjectInputStream(t.getInputStream());
     	    
            //Writer writer = (Writer) b.readObject();
            ObjectMapper mapper = new ObjectMapper();

			String received = (String) b.readObject();
			Writer writer=mapper.readValue(received, Writer.class);
			System.out.println(writer);
			received = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(writer);
	        System.out.println(received);

			WriterBusinessLogic wlogic= new WriterBusinessLogic();
			wlogic.insertWriter(writer);
			
            PrintWriter output = new PrintWriter(t.getOutputStream(), true);
            output.println("Writer "+writer.getName()+" has been received");
            b.close();
            output.close();
            t.close();    	
        	
        }
    }
}