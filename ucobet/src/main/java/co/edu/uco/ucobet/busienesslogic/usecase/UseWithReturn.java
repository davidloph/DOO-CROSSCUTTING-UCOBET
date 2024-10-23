package co.edu.uco.ucobet.busienesslogic.usecase;

public interface UseWithReturn<D, R> {

	R execute(D data);
}
