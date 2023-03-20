package websoccet;

import java.io.*;
import java.net.*;
import java.util.Scanner;

class UDPClient {
	public static void main(String args[]) throws Exception {

		DatagramSocket clientSocket = new DatagramSocket();

		String servidor = "localhost";
		String serverName = "Cliente:Edder:";
		int porta = 9090;

		InetAddress IPAddress = InetAddress.getByName(servidor);

		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];

		Integer numero = lerNumero();
		
		String mensagem = String.format(serverName+"%s", numero);
			
		sendData = mensagem.getBytes();
		
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, porta);

		System.out.println("Enviando pacote UDP para " + servidor + ":" + porta);
		
		clientSocket.send(sendPacket);

		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

		clientSocket.receive(receivePacket);
		System.out.println("Pacote UDP recebido...");

		String modifiedSentence = new String(receivePacket.getData());

		System.out.println("Texto recebido do servidor:" + modifiedSentence);
		clientSocket.close();
		System.out.println("Socket cliente fechado!");
	}
	
	private static Integer lerNumero() {
		
        Scanner scanner = new Scanner(System.in);
        Integer numero;
        
        do {
            System.out.print("Digite um número inteiro entre 1 e 100:\n");
            while (!scanner.hasNextInt()) {
                System.out.print("Entrada inválida. Digite um número inteiro entre 1 e 100:\n");
                scanner.next();
            }
            numero = scanner.nextInt();
        } while (numero < 1 || numero > 100);
        
        scanner.close();
        return numero;
    }
	
}