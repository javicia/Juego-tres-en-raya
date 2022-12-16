package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class Cliente {

	private final String HOST = "localhost";
	private final int PUERTO = 4321;
	private Socket socket;

	public Cliente() throws IOException {
		socket = new Socket(HOST, PUERTO);
	}

	public void iniciarCliente() throws IOException {
		// Iniciamos la entrada de datos
		DataInputStream entradaServidor = new DataInputStream(socket.getInputStream());
		System.out.println(entradaServidor.readUTF()); // Mostramos el mensaje por pantalla

		
		DataOutputStream salidaServidor = new DataOutputStream(socket.getOutputStream());
		Random random = new Random();
		int numAleatorio = random.nextInt(5) + 1; // Enviar [1, 5]
		for (int i = 0; i < numAleatorio; i++) {

			salidaServidor.writeUTF("Este es el mensaje num: " + i);

		}
		salidaServidor.close();
		entradaServidor.close();
		socket.close();
	}
}
