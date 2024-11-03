package co.edu.uco.ucobet.busienesslogic.usecase;

public interface RuleWithoutFactory<T>{

	void execute(T data);
}
