package examples;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import algorithms.GPSCoordinate;
import datastructures.KDData;
import datastructures.KDDataGPS;
import datastructures.KDTree;

class Point2DGPS implements KDData<Double> {
	public final GPSCoordinate gps;
	
	public Point2DGPS(GPSCoordinate Gps) {
		this.gps = Gps;
	}

	@Override
	public int compareTo(KDData<Double> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Double getValueForDimension(int dimension) {
		if (dimension == 0)
			return gps.latitude;
		return gps.longitude;
	}

	@Override
	public int getDimensions() {
		return 2;
	}

	
	@Override
	public double compare(KDData<Double> other, int dimension) {
		Point2DGPS o = (Point2DGPS)other;
		double xgps = o.getValueForDimension(0).doubleValue();
		double ygps = o.getValueForDimension(1).doubleValue();
		if(dimension == 0)
			return gps.latitude - xgps;
		return gps.longitude - ygps;
	}

	@Override
	public double distance(KDData<Double> other) {
		Point2DGPS o = (Point2DGPS)other;
		o.gps.caminhada =  gps.distance(o.gps);
		return o.gps.caminhada;
	}

	@Override
	public String toString() {
		
		if(gps.nome==null){
			
			return "latitude...: " +gps.latitude + " | longitude...: " + gps.longitude;
	
		}else{
			
			return String.format("Nome...: %s | Latitude...: %.8f | Longitide...: %.8f | Ter� que caminhar mais %.2f metros",gps.nome,gps.latitude,gps.longitude, gps.caminhada);
			
		}
		
	}
}

//classe Principal

public class KDTreeExample {
	public static void main(String... args) {
		(new KDTreeExample()).run();
	}
	
	private KDTree<Point2DGPS> kd1 = null;
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void run() {
		
		//L� o arquivo e coloca no vetor as info
		double [] lat = new double[38];
		double [] lon = new double[38];
		String [] nomeRua = new String [38];
		int x = 0;
                           //trocar o caminho
		String arquivo = "C:\\Users\\Edu\\Documents\\NetBeansProjects\\kdtreebikepoa\\poaBike\\src\\examples\\bike.csv";
	    BufferedReader br = null;
	    String linha = "";
	    String divisor = ";";
	    try {
	        br = new BufferedReader(new FileReader(arquivo));
	        br.readLine();
	        while ((linha = br.readLine()) != null) {
	        		
	            String[] Vetor = linha.split(divisor);
	            
	            nomeRua[x] = Vetor[1];
	            lat[x] = Double.parseDouble(conversorPonto(Vetor[2]));
	            lon[x] = Double.parseDouble(conversorPonto(Vetor[3]));
	            x++;
	        }

	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e1) {
			e1.printStackTrace();
		}
		//------------------------------------------------------------------------------
		int tv = lat.length;
		Point2DGPS[] pointD = new Point2DGPS[tv];
		GPSCoordinate parGps;
		GPSCoordinate buscaGps;
		for (int i = 0; i < tv; i++){
			parGps = new GPSCoordinate(lat[i], lon[i], nomeRua[i]);
			pointD[i] = new Point2DGPS(parGps);
		}
		kd1 = new KDTree<>(pointD);
		System.out.println();
		//ponto de partida
		buscaGps = new GPSCoordinate(ler("latitude"),ler("longitude"));
		findPointD(new Point2DGPS(buscaGps));
		//ponto de chegada
		buscaGps = new GPSCoordinate(ler("latitude"),ler("longitude"));
		findPointD(new Point2DGPS(buscaGps));
		
		
	}
	
	//Exibe na tela
	private void findPointD(Point2DGPS point) {
		System.out.println("\nPonto aonde estou ou aonde vou..: \n\t" + point.toString());
		System.out.println("Esta��o de bike mais pr�xima...: \n\t"+kd1.findNearestPoint(point));
	}

	
	//metodo conversor do ponto
	
	public String conversorPonto(String coordenada){

		char[] nomeArray = coordenada.toCharArray();
		nomeArray[3]='.';
		String novaString = new String(nomeArray);
		
		return novaString;
	}
	
	public double ler(String coor){
		Scanner in = new Scanner(System.in);
		System.out.println("\n\t~>Digite a "+coor);
		return in.nextDouble();
	}
}

