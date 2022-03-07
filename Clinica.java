
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.math.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.*;
import java.util.*;
import java.text.*;
import java.util.Formatter.*;


public class Clinica {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        //Especialistas
        String arrayEspecialistas[] = {"Maria","Juan","Paco"};
       
        
        //Ramas
        String arrayRamas[] = {"Nutricion","Fisioterapia"};
   
        
        //Precios
        int arrayPrecios[];
        arrayPrecios = new int[2];
        
        //Forma de cobro
        int arrayFormaCobro[];
        arrayFormaCobro = new int[3];
        
        ArrayList <Integer> formaCobro = new <Integer> ArrayList();
        ArrayList <Integer> precio = new <Integer> ArrayList();
               
        //Ramas de cada especialista
        int arrayEspecialistaRama[][] = {{0,1},{1},{0}};
  
        //Visitas generadas
        
        //cuando lo rellenemos habra que declarar el array de 7 posiciones e insertarlo con visitasGeneradas.add(nombre array);
        ArrayList <int[]> visitasGeneradas = new ArrayList <int[]>(); 

        LocalDate[] simulacion = new LocalDate[61];
        int dias = 1, mes = 3;
        
        for(int i = 0; i < simulacion.length; i++) {
            simulacion[i] = LocalDate.of(2022, mes, dias);
            dias++;
            
            if(i == 30) {
                dias = 1;
                mes++;
            }
            
        }
     
        
        int visitasDiarias;
        boolean salir = false;
        int opcionmenu = 0;
        int d = 0;
        int especialista = 0;

        //Switch con opciones para primer menu
        do {
        	
            opcionmenu = tryCatchOpcion();//metodo pedir opcion con try catch
            
            switch (opcionmenu) {
                case 1:
                	//j empieza en 1 para quitar el 1 de Marzo
                	for(int j = 1;j<60;j++) {
                		
                		//1 de Abril festivo
                		//2 de Abril festivo
                		while(j == 31 || j == 32) {
                			j++;
                		}
	
                		//Si estamos en Sabado pasa el dia a Lunes
                		if((j + 3)%7 == 0) {
    	    				j += 2;
    	    			}
                		
                		//Si estamos en domingo pasa el dia a Lunes
                		if((j + 2)%7 == 0) {
    	    				j++;
    	    			}
           
                		//Para que no se pueda pasar del 30 de Abril
                		if(j>0 && j<62) {
                			
	                		for(int x = 0;x<arrayEspecialistas.length;x++) {
	                			
								visitasDiarias = datosAleatorios(10,15);
								
							  //--Esto es Maria Viernes        //Esta es la condicion de Juan de los Lunes
								if(( (j + 4)%7 == 0 && x == 0 )||( (j+1)%7 == 0 && x == 1 )) {
									
									x++;
								}
						
			                	for (int i = 0; i < visitasDiarias; i++) {
			                		visitasGeneradas.add(generarVisitasUnDia(j,x,formaCobro,precio, LocalDate.of(2022, 3, 1)));			                		
								}

	                		}
                		}	
                	}
                	System.out.println("Datos Generados Correctamente!!");
                	break;

                case 2:
                		mostrarDatosGenerados(visitasGeneradas,simulacion);
                    break;

                case 3:
                	mostrarResumen(formaCobro, precio);
                    break;

                case 4:
                	salir = true;
                	System.out.println("Gracias por acudir a nuestra Clinica!!");
                    break;
           
                default:

                
                 break;
            }
        } while (!salir);
    }
    

//METODOS


    
    public static void mostrarDatosGenerados(ArrayList<int[]> visitas, LocalDate[]Simulacion) {
    	
    	String arrayEspecialistas[] = {"Maria","Juan","Paco"};
    	String arrayEspecialidad[] = {"Nutricion","Fisioterapia"};
    	String metodoPago[] = {"Efectivo","Tarjeta","Transferencia"};
    	String urgencia[] = {"Cita Normal","Urgencia"};
    	int arrayPago[] = {40, 50};
    	
    	for(int[] s: visitas){
    		
    			System.out.println("Dia: "+Simulacion[s[0]].toString()+ " | Cliente: "+ s[1]+ "   | Especialista: "+ arrayEspecialistas[s[2]]+ "   | Especialidad: "+ arrayEspecialidad[s[3]]+ "   | Precio: "+ arrayPago[s[4]]+ "   | Tipo de Cita: "+ urgencia[s[5]]+ " | Metodo de Pago: "+ metodoPago[s[6]]);
    		
    	}
    	
    }
    
    
    public static void mostrarResumen(ArrayList formaCobro, ArrayList precio) {
    	

    	int efectivo = 0;
    	int tarjeta = 0;
    	int transferencia = 0;
    	
    	int total = 0;
    	
    	int volcarCobro = 0;
    	int volcarPrecio = 0;
    	
    	
    	for(int i = 0;i<formaCobro.size();i++) {

    		volcarCobro = (int)formaCobro.get(i);

    		volcarPrecio = (int)precio.get(i);
    				
    		if(volcarCobro == 0) {

    			if(volcarPrecio == 0) {
    				efectivo = efectivo + 40;
    			}else {
    				efectivo = efectivo + 50;
    			}
    		}
    		
    		if(volcarCobro == 1) {
    			
    			if(volcarPrecio == 0) {
    				tarjeta = tarjeta + 40;
    			}else {
    				tarjeta = tarjeta + 50;
    			}
    		}
    		
    		if(volcarCobro == 2) {
    			
    			if(volcarPrecio == 0) {
    				transferencia = transferencia + 40;
    			}else {
    				transferencia = transferencia + 50;
    			}
    		}

    		
    	}
    	total = efectivo + tarjeta + transferencia;
    	
    	NumberFormat nf = NumberFormat.getInstance();
    	nf = NumberFormat.getCurrencyInstance();
    	
    	System.out.println("--------------------------------");
    	System.out.println("Efectivo: " + nf.format(efectivo));
		System.out.println("Tarjeta: " + nf.format(tarjeta));
		System.out.println("Transferencia: " + nf.format(transferencia));
		System.out.println("--------------------------------");
		System.out.println();
		System.out.println("TOTAL: " + nf.format(total));
		System.out.println("--------------------------------");
	

    }
    


public static int datosAleatorios(int limite1, int limite2) {
	int numero = 0;
	
	numero = (int)Math.floor(Math.random()*(Math.max(limite1,limite2) - Math.min(limite1, limite2) + 1)+Math.min(limite1, limite2));

	return numero;
	
}

public static int tryCatchOpcion() {
	Scanner sc = new Scanner (System.in);
	int num = 0;
	boolean salirTry;
	do {
		  salirTry = false;
		try {
			 System.out.println("=======================");
			 System.out.println("Seleccione una Opcion: ");
			 System.out.println("=======================");
			 
	        	System.out.println("1.Generar los datos de visitas");
	        	System.out.println("2.Mostrar los datos Generados");
	        	System.out.println("3.Mostrar Resumem");
	        	System.out.println("4.Salir");
	        		        	

	         num = sc.nextInt();
	       
		} catch (InputMismatchException ex) {
		System.out.println("No ha introducido un Numero, Vuelva a Introducirlo.");
			salirTry = true;
			sc.nextLine();
		}
	}while(salirTry);
	
	return num;
}


public static int[] generarVisitasUnDia(int dia,int especialista, ArrayList formaCobro, ArrayList precio, LocalDate fechaInicio){


		int urgencia = 0;
		int pago = 0;
		int numCliente = 0;

		int array[];
		array = new int[7];
		
		int i = 0;

	    array[i] = dia;
	      
	    i++;	

	    array[i] = datosAleatorios(1,3000);
	    i++;
	    
	    array[i] = especialista;

	    i++;

	    if(array[3] == 0){
	        array[i] = array[i+1] = datosAleatorios(0,1);
	        precio.add(array[i]);
	    }
	
	    if(array[3] == 1){
	        array[i] = array[i+1] = 1;
	        precio.add(array[i]);
	        	        
	    }else{
	        array[i] = array[i+1] = 0; 
	        precio.add(array[i]);

	    }
	    i += 2; 
	    
	    urgencia = datosAleatorios(60,0);
	    
	    if(urgencia == 60) {
	    	array[i]= 1; //Esto es urgencia
	    }else {
	    	 array[i]=0;
	    }
	    	    
	    i++;
	    
	    pago = datosAleatorios(0,2);  
	    array[i] = pago;
	    

	    formaCobro.add(array[i]); //guardamos metodo de pago
	  
		LocalDate fechaActual = fechaInicio.plusDays((int)array[0]);
	   

	    
    return array;

}





}



