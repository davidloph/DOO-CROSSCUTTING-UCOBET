package co.edu.uco.ucobet.busienesslogic.facade;

public interface FacadeWithReturn<T, R>{
	
	R execute(T data);

}
