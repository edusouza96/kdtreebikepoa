package datastructures;
import algorithms.GPSCoordinate;

public interface KDDataGPS {
	GPSCoordinate getValueForDimension(GPSCoordinate dimension);
	GPSCoordinate getDimensions();
	//double distance(KDDataGPS<T> other);
	//double compare(KDDataGPS<T> other, GPSCoordinate dimension);
}
