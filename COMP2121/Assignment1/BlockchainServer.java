import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.*;

public class BlockchainServer {

	private Blockchain blockchain;

	public BlockchainServer() {
		blockchain = new Blockchain();
	}

	// getters and setters
	public void setBlockchain(Blockchain blockchain) {
		this.blockchain = blockchain;
	}

	public Blockchain getBlockchain() {
		return blockchain;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			return;
		}
		int portNumber = Integer.parseInt(args[0]);
		BlockchainServer bcs = new BlockchainServer();
		BufferedReader input;
		PrintWriter output;
		ServerSocket myservice;
		try{
			myservice = new ServerSocket(portNumber);
			Socket client = myservice.accept();
			input = new BufferedReader( new InputStreamReader(client.getInputStream()));
			output = new PrintWriter(client.getOutputStream());
		} catch(IOException e){
			System.err.println(e);
		}
			
		while(true)
		{
			String line = input.readLine();
		}
	}

	public void serverHandler(InputStream clientInputStream, OutputStream clientOutputStream) {

		BufferedReader inputReader = new BufferedReader(new InputStreamReader(clientInputStream));
		PrintWriter outWriter = new PrintWriter(clientOutputStream, true);


	}

	// implement helper functions here if you need any.
}
