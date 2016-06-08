package examples;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Principal {
	 public static void main(String[] args) throws IOException {

		    String arquivo = "bike.csv";
		    BufferedReader br = null;
		    String linha = "";
		    String divisor = ";";
		    try {
		        br = new BufferedReader(new FileReader(arquivo));
		        while ((linha = br.readLine()) != null) {

		            String[] Vetor = linha.split(divisor);
		            System.out.println(" [cod= " + Vetor[0] +
		                            " - Esta��o= " + Vetor[1] +
		                            " - Latitude= " + Vetor[2] +
		                            " - Longitude= " + Vetor[3]+"]");
		        }

		    } catch (FileNotFoundException e) {
		        e.printStackTrace();
		    } 
		    
		  }

}
