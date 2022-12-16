package servidor;

import java.io.IOException;

public class MainServer {

	public static void main(String[] args) throws IOException {
		// Definimos objeto
		Servidor serv = new Servidor();
		System.out.println("Iniciando servidor . . .");

		// Iniciamos el servidor
		serv.iniciarServer();

		// finalizamos el servidor
		serv.finalizarServer();
	}
}
