/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import java.util.*;
import java.math.BigInteger;
import java.io.*;

public class RSA{

    //variables para el algoritmo
    int tamPrimo;
    BigInteger p, q, n;
    BigInteger phi;
    BigInteger e, d;

    //constructor

    public RSA(int tamPrimo){
        this.tamPrimo = tamPrimo;
    }

    RSA() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    //1234567890
    //metodo que se encarga de generar primos
    public void generarPrimos(){
        p = new BigInteger(tamPrimo, 10, new Random());
        do q = new BigInteger(tamPrimo, 10, new Random());
            while(q.compareTo(p)==0);
    }

    //generar las claves
    public void generarClaves(){
        //n = p*q
        n = p.multiply(q);

        //phi = (p-1)*(q-1)
        phi = p.subtract(BigInteger.valueOf(1));
        phi = phi.multiply(q.subtract(BigInteger.valueOf(1)));

        //calcular el primo relativo o coprimo e y menor que n

        do e = new BigInteger(2*tamPrimo, new Random());
            //calcular el mcd e
            while ((e.compareTo(phi)!=-1)||(e.gcd(phi).compareTo(BigInteger.valueOf(1))!=0));
        //calcular d
        d = e.modInverse(phi);
    }

    //cifrar

    public BigInteger[] encriptar(String mensaje){

        //variables
        int i;
        byte[] temp = new byte[1];
        byte[] digitos = mensaje.getBytes();
        BigInteger[] bigdigitos = new BigInteger[digitos.length];

        //primero es recorrer el tamaño de bigdigitos para asignarlos al temp
        for(i = 0; i<bigdigitos.length; i++){
            temp[0] = digitos[i];
            bigdigitos[i] = new BigInteger(temp);
        }

        //necesito un biginteger para el cifrado
        BigInteger[] cifrado = new BigInteger[bigdigitos.length];

        for(i = 0; i<bigdigitos.length; i++){
            //System.out.println("bigdigitos["+i+"]="+bigdigitos[i]+" - e["+e+"], n["+n+"]");
            //aplico el modulo para el cifrado
            
            cifrado[i] = bigdigitos[i].modPow(e, n);
           // System.out.println("cifrado["+i+"].length="+cifrado[i].toString(16));
        }
        return cifrado;
        
    }

    //descifrar
    public String descifrar(BigInteger[] cifrado, BigInteger d, BigInteger n){

        BigInteger[] descifrado = new BigInteger[cifrado.length];

        //descifrar
        for(int i = 0; i<descifrado.length; i++){
            //aplico el descifrado[]
            descifrado[i] = cifrado[i].modPow(d, n);
        }

        //lo tengo que colocar en un arreglo de caracteres para despues pasarlo a una cadena
        char[] charArray = new char[descifrado.length];

        for(int i = 0; i <charArray.length; i++){
            charArray[i] =(char)(descifrado[i].intValue());
        }

        return (new String(charArray));
    }
    
    public String imprimirCifrado(BigInteger cifrado[]){    
        StringBuffer sbResultado=new StringBuffer();
        for(int i = 0; i<cifrado.length; i++){
            sbResultado.append("|").append(cifrado[i].toString(16));
        }
        return sbResultado.toString();
    }
    public String imprimirD(){
        StringBuffer sbResultadoD=new StringBuffer();
        sbResultadoD.append(d.toString());
        
        return sbResultadoD.toString();
    }
    public String imprimirN(){
        StringBuffer sbResultadoD=new StringBuffer();
        sbResultadoD.append(n.toString());
        
        return sbResultadoD.toString();
    }
    
    public String descifrar(String sCifrado, String descifrarN, String descifrarD){
        System.out.println("sCifrado["+sCifrado+"]");
        String sArrBigIntegers[] = sCifrado.substring(1).split("\\|");
        BigInteger VarN = new BigInteger(descifrarN);
        BigInteger VarD = new BigInteger(descifrarD);
        BigInteger cifrado[] = new BigInteger[sArrBigIntegers.length];
        for(int i = 0; i<cifrado.length; i++){
            System.out.println("sArrBigIntegers["+i+"]=["+sArrBigIntegers[i]+"]");
            cifrado[i]= new BigInteger(sArrBigIntegers[i],16);
        }
        return descifrar(cifrado, VarD, VarN);        
    }

    //enviar los datos

    public BigInteger damep(){
        return p;
    }

    public BigInteger dameq(){
        return q;
    }

    public BigInteger damen(){
        return n;
    }

    public BigInteger damephi(){
        return phi;
    }

    public BigInteger damee(){
        return e;
    }

    public BigInteger damed(){
        return d;
    }
}
