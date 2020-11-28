/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hilos;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Scanner;

class Escribir extends Thread 
	{
	Socket socket;
	String name;
	Scanner entrada;
 	Escribir(Socket socket, String name){      //Recibe objeto de tipo Socket para identificar el Socket que está ejecutando el proceso y
                                                                                     // en el parámetro name recibirá como el cliente desea ser nombrado
		entrada = new Scanner(System.in);  //Objeto para recibir datos desde teclado
		this.socket=socket;
		this.name=name;
		start();  //Inicia el Hilo, se llama automáticamente al método run()
		}

    public void run(){
        
		try{
                    
			boolean terminar=false;
                        boolean terminar1=false;
                        boolean terminar2=false;
                        boolean terminar3=false;
			String mensaje, opcion, cifraroDecifrar, cifrar, descifrar, descifrarN, descifrarD;
                        System.out.println("Menu\n 1.-Encriptar \n 2.-Desencriptar \n 3.-Chat ");
                        opcion=entrada.nextLine();
                        switch(opcion){
                            case "1":
                                while(!terminar1){      // Creamos bucle infinito para escritura
                                    OutputStream os= socket.getOutputStream();
                                    DataOutputStream flujoDOS = new DataOutputStream(os);
                                
                                    System.out.println("Escribe tu mensaje a cifrar " + name +": ");
                                    cifrar=entrada.nextLine();
                                    RSA cifrar1 = new RSA(8);
                                    cifrar1.generarPrimos();
                                    cifrar1.generarClaves();
                                    BigInteger[] encriptado = cifrar1.encriptar(cifrar);
                                    String sMensajeEncriptado = cifrar1.imprimirCifrado(encriptado);
                                    String sMensajeD = cifrar1.imprimirD();
                                    String sMensajeN = cifrar1.imprimirN();
                                    System.out.println("El menssaje se ha encriptado: "+sMensajeEncriptado );
                                    //String sMensajeDesencriptado = cifrar1.descifrar(sMensajeEncriptado);
                                    //System.out.println("El menssaje desencriptado es["+sMensajeDesencriptado+"].");
                                    //encriptado.toByteArray();
                                    if(cifrar.equals("salir"))       // El bucle se romperá al ingresar la palabra Salir por teclado
                                            {
                                            terminar1=true;
                                            }
                                    else 
                                            {
                                            flujoDOS.writeUTF("el mensaje esta encriptado "+name+" : "+sMensajeEncriptado);
                                            flujoDOS.writeUTF("Variavle d "+name+" : "+sMensajeD);
                                            flujoDOS.writeUTF("Variavle n "+name+" : "+sMensajeN);//Si no se ingresa salir, se envía mensaje de escritura
                                            }
                                    
                                }
                             break;
                             case "2":
                                while(!terminar3){      // Creamos bucle infinito para escritura
                                    OutputStream os= socket.getOutputStream();
                                    DataOutputStream flujoDOS = new DataOutputStream(os);
                                
                                    System.out.println("Escribe tu mensaje a descifrar " + name +": ");
                                    descifrar=entrada.nextLine();
                                    System.out.println("Escribe tu variavle n " + name +": ");
                                    descifrarN=entrada.nextLine();
                                    System.out.println("Escribe tu variavle d " + name +": ");
                                    descifrarD=entrada.nextLine();
                                    RSA cifrar1 = new RSA(8);
                                    cifrar1.generarPrimos();
                                    cifrar1.generarClaves();
                                    //BigInteger[] encriptado = cifrar1.encriptar(cifrar);
                                    //String sMensajeEncriptado = cifrar1.imprimirCifrado(encriptado);
                                    //System.out.println("El menssaje se ha encriptado: "+sMensajeEncriptado );
                                    String sMensajeDesencriptado = cifrar1.descifrar(descifrar, descifrarN, descifrarD);
                                    System.out.println("El menssaje desencriptado es["+sMensajeDesencriptado+"].");
                                    //encriptado.toByteArray();
                                    if(descifrar.equals("salir"))       // El bucle se romperá al ingresar la palabra Salir por teclado
                                            {
                                            terminar3=true;
                                            }
                                    else 
                                            {
                                            flujoDOS.writeUTF("El cliente "+name+" pudo desencriptar : ");  //Si no se ingresa salir, se envía mensaje de escritura
                                            }
                            }
                             break;
                             case "3":
                                while(!terminar){      // Creamos bucle infinito para escritura
				OutputStream os= socket.getOutputStream();
				DataOutputStream flujoDOS = new DataOutputStream(os);
                                
                                    System.out.println("Escribe tu mensaje " + name +": ");
                                    mensaje=entrada.nextLine();
                                    if(mensaje.equals("salir"))       // El bucle se romperá al ingresar la palabra Salir por teclado
                                            {
                                            terminar=true;
                                            }
                                    else 
                                            {
                                            flujoDOS.writeUTF(name+" dice: "+mensaje);  //Si no se ingresa salir, se envía mensaje de escritura
                                            }
                                    
                                }
                             break;
                        }
                        /*if(opcion.toLowerCase().equals("si")||opcion.toLowerCase().equals("s")){
                            System.out.println("Quieres cifrar(c) este mensaje o descifrar(d) otro " + name +"?: ");
                            cifraroDecifrar=entrada.nextLine();
                            if(cifraroDecifrar.toLowerCase().equals("c")){
                                while(!terminar1){      // Creamos bucle infinito para escritura
                                    OutputStream os= socket.getOutputStream();
                                    DataOutputStream flujoDOS = new DataOutputStream(os);
                                
                                    System.out.println("Escribe tu mensaje a cifrar " + name +": ");
                                    cifrar=entrada.nextLine();
                                    RSA cifrar1 = new RSA(8);
                                    cifrar1.generarPrimos();
                                    cifrar1.generarClaves();
                                    BigInteger[] encriptado = cifrar1.encriptar(cifrar);
                                    String sMensajeEncriptado = cifrar1.imprimirCifrado(encriptado);
                                    //System.out.println("El menssaje se ha encriptado: "+sMensajeEncriptado );
                                    String sMensajeDesencriptado = cifrar1.descifrar(sMensajeEncriptado);
                                    System.out.println("El menssaje desencriptado es["+sMensajeDesencriptado+"].");
                                    //encriptado.toByteArray();
                                    if(cifrar.equals("salir"))       // El bucle se romperá al ingresar la palabra Salir por teclado
                                            {
                                            terminar1=true;
                                            }
                                    else 
                                            {
                                            flujoDOS.writeUTF("el mensaje esta encriptado "+name+" : "+sMensajeEncriptado);  //Si no se ingresa salir, se envía mensaje de escritura
                                            }
                                    
                                }
                            }else if(cifraroDecifrar.toLowerCase().equals("d")){
                            while(!terminar3){      // Creamos bucle infinito para escritura
                                    OutputStream os= socket.getOutputStream();
                                    DataOutputStream flujoDOS = new DataOutputStream(os);
                                
                                    System.out.println("Escribe tu mensaje a descifrar " + name +": ");
                                    descifrar=entrada.nextLine();
                                    RSA cifrar1 = new RSA(8);
                                    //cifrar1.generarPrimos();
                                    //cifrar1.generarClaves();
                                    //BigInteger[] encriptado = cifrar1.encriptar(cifrar);
                                    //String sMensajeEncriptado = cifrar1.imprimirCifrado(encriptado);
                                    //System.out.println("El menssaje se ha encriptado: "+sMensajeEncriptado );
                                    String sMensajeDesencriptado = cifrar1.descifrar(descifrar);
                                    System.out.println("El menssaje desencriptado es["+sMensajeDesencriptado+"].");
                                    //encriptado.toByteArray();
                                    if(descifrar.equals("salir"))       // El bucle se romperá al ingresar la palabra Salir por teclado
                                            {
                                            terminar3=true;
                                            }
                                    else 
                                            {
                                            flujoDOS.writeUTF("el mensaje esta encriptado "+name+" : "+sMensajeDesencriptado);  //Si no se ingresa salir, se envía mensaje de escritura
                                            }
                            }
                            }
                        }else{
			while(!terminar){      // Creamos bucle infinito para escritura
				OutputStream os= socket.getOutputStream();
				DataOutputStream flujoDOS = new DataOutputStream(os);
                                
                                    System.out.println("Escribe tu mensaje " + name +": ");
                                    mensaje=entrada.nextLine();
                                    if(mensaje.equals("salir"))       // El bucle se romperá al ingresar la palabra Salir por teclado
                                            {
                                            terminar=true;
                                            }
                                    else 
                                            {
                                            flujoDOS.writeUTF(name+" dice: "+mensaje);  //Si no se ingresa salir, se envía mensaje de escritura
                                            }
                                    
                                }
                        }*/
			socket.close();
			}
		catch(Exception e){
			System.out.println("Error");
                        e.printStackTrace();
			}
		}
	}

    

