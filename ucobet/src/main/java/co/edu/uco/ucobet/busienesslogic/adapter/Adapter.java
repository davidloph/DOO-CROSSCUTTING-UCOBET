package co.edu.uco.ucobet.busienesslogic.adapter;

public interface Adapter<D, T> {

	D adaptSource(T data);
	
	T adaptTarget(D data);
}
