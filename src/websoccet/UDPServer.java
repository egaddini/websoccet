package websoccet;
import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class UDPServer {
	public static void main(String args[]) throws Exception {
		
//		1.imprimir (exibir) o nome do cliente (extraído da mensagem recebida) e o nome do servidor	
//		2.ele mesmo escolhe um número inteiro entre 1 e 100 (tudo bem se o servidor usar o mesmo número o tempo todo) e exibir o número do cliente, seu número e a soma desses números
//		3.envie sua string de nome e o valor inteiro escolhido pelo servidor de volta ao cliente
//		4.se o seu servidor receber um valor inteiro fora do intervalo, ele deverá ser encerrado após a liberação de quaisquer sockets criados. Você pode usar isso para desligar seu servidor.


		int porta = 9090;
		int numConn = 1;
		String serverName = "Edder";
		String barramento = "--------------------------------------------------------";
		
		DatagramSocket serverSocket = new DatagramSocket(porta);

		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];

		while (true) {
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			System.out.println("Esperando por datagrama UDP na porta " + porta);
			serverSocket.receive(receivePacket);
			
			System.out.print("Datagrama UDP [" + numConn + "] recebido...\n");

			String sentence = new String(Arrays.copyOfRange(receivePacket.getData(), 0, receivePacket.getLength())); 
			 
			List<String> stringList = Arrays.asList(sentence.split(":"));
			
			System.out.println("Questão 1)");
			System.out.println("Servidor do: " + serverName);
			System.out.println("Cliente " + stringList.get(1));
			System.out.println(barramento);
			System.out.println("Questão 2)");
			Random rand = new Random();
			int nmrServidor = rand.nextInt(100) + 1;
			int nmrCliente = Integer.valueOf(stringList.get(2));
			int soma = (nmrServidor + nmrCliente);
			System.out.println("Número do servidor: " + nmrServidor);
			System.out.println("Número do cliente: " + stringList.get(2));
			System.out.println("Soma dos números: " + soma);
			System.out.println(barramento);
			
			String serverMsg = serverName + ":" + soma;
			sendData = serverMsg.getBytes();
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();


			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			

			serverSocket.send(sendPacket);
			System.out.println("OK\n");
		}
		
	}
}