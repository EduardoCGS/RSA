/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hilos;

/**
 *
 * @author KIKA
 */
import java.net.*;
import java.io.*;
import java.util.*;
public class Cliente 
    { 
    Scanner entrada;          
     Cliente()     {	 
       entrada = new Scanner(System.in);         
       try         {             
//                      String host = "189.228.173.45";
                      String host = "127.0.0.1";
                      Inet4Address in = (Inet4Address) Inet4Address.getByName(host);
                      Socket skCliente = new Socket (in, 5000);           			
                      System.out.println("Introduce tu Nombre:");			 
                      String nombre=entrada.next();						
                      Escribir hilo1 =new Escribir(skCliente,nombre);  //hilo que escribe se envía el nombre ingresado por el cliente y el socket 
                      Leer hilo2= new Leer(skCliente);    //hilo que lee, se envía como parámetro el Socket				   skCliente.close();         
                     }         
      catch (Exception e)    {  
          e.printStackTrace();      
      }     
      }	 
public static void main (String [] args)    
          {         new Cliente();     }     
}

