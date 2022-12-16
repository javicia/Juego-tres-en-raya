package servidor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	private final int PUERTO = 4321; // no va a cambiar
	private ServerSocket serverSocket;
	private Socket socket;

	// Definimos el constructor
	public Servidor() throws IOException {
		serverSocket = new ServerSocket(PUERTO); // Definimos la conexión
		socket = new Socket(); // Iniciamos el cliente
	}

	// Función para iniciar la conexión
	public void iniciarServer() throws IOException {
		// Vamos a aceptar los datos que llegaron del cliente
		while (true) {
			System.out.println("Esperando la conexion del cliente");
			socket = serverSocket.accept(); // guardamos la petición que llegue al servidor en socket
			// El servidor se queda a la espera de recibir peticiones

			// Al recibir la petición, iniciamos la conexión
			DataOutputStream mensajeCliente = new DataOutputStream(socket.getOutputStream());
			// Enviamos mensaje al cliente
			mensajeCliente.writeUTF("Petición rebida");

			DataInputStream entrada = new DataInputStream(socket.getInputStream());

			String mensajeDeCliente;
			try {
				while (!(mensajeDeCliente = entrada.readUTF()).isEmpty())
					// Mostramos el mensaje por pantalla
					System.err.println(mensajeDeCliente);
			} catch (EOFException ex) {
				System.err.println("Fin de la comunicación");
			}
			System.err.println("Fin de la conexión");
			socket.close();
		}
	}

	public void finalizarServer() throws IOException {
		serverSocket.close();
		System.out.println("Servidor cerrado");
	}

}
