package datastructures;

public interface KDDataDouble<T extends Number> extends Comparable<KDDataDouble<T>> {
	T getValueForDimension(int dimension);
	int getDimensions();
	double distance(KDDataDouble<T> other);
	double compare(KDDataDouble<T> other, int dimension);
}