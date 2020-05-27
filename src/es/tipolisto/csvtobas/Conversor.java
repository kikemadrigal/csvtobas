package es.tipolisto.csvtobas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class Conversor {
	private String nombreArchivoAConvertir;
	private String nombreArchivoConvertido;
	private int contadorLinea;
	File fileArchivoConvertido;
	public Conversor(String nombre, int contadorLinea) {
		nombreArchivoAConvertir=nombre;
		nombreArchivoConvertido=nombreArchivoAConvertir.substring(0,nombreArchivoAConvertir.length()-3)+"-tobas.txt";
		fileArchivoConvertido=new File(nombreArchivoConvertido);
		if(fileArchivoConvertido.exists()) {
			fileArchivoConvertido.delete();
			fileArchivoConvertido=new File(nombreArchivoConvertido);
		}
		this.contadorLinea=contadorLinea;
	}
	
	public void convertir() {
		leerArchivo();
	}
	
	private void leerArchivo() {
		File archivo = null;
	    FileReader fr = null;
	    BufferedReader br = null;
	    String lineaSinDB="";
		try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         archivo = new File (nombreArchivoAConvertir);
	         if(!archivo.canRead()) {
	        	 JOptionPane.showMessageDialog(null, "El archivo está protegido y no se puede leer, cambia los permisos");
	         }else {
	        	 
	         }
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);

	         // Lectura del fichero
	         String textoFormateado;
	         String linea;
	         while((linea=br.readLine())!=null) {
	        	
	     	   	 textoFormateado=formatearTexto(linea);
	     	   	 escribirEnFichero(textoFormateado);
	  
	         }
	         JOptionPane.showMessageDialog(null, "Covertido!!!");
		}catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }

	}
	
	
	private void escribirEnFichero(String cadena) {
		FileWriter fileWriter = null;
        PrintWriter printWriter = null;
        try
        {
    		fileWriter = new FileWriter(fileArchivoConvertido, true);
            printWriter = new PrintWriter(fileWriter);
            printWriter.println(cadena);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fileWriter)
        	  fileWriter.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
	}
	
	
	private String formatearTexto(String cadena) {
		String textoSinMenosUno="";
		String textoFormateado="";
		int posicionMenosUno=cadena.indexOf("-1");
		if(posicionMenosUno!=-1) {
			textoSinMenosUno=cadena.replace("-1", "16");
			textoFormateado=textoSinMenosUno;
   		}
		if(cadena!="" && cadena!=null && cadena.length()>0) {
			contadorLinea++;
			textoFormateado=contadorLinea+" data "+textoFormateado;
	   		
		}
		return textoFormateado;
	}

}
