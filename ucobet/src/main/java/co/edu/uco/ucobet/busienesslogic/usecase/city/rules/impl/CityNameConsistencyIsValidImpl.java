package co.edu.uco.ucobet.busienesslogic.usecase.city.rules.impl;

import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.ucobet.busienesslogic.usecase.city.rules.CityNameConsistencyIsValid;
import co.edu.uco.ucobet.crosscutting.exceptions.BusinessLogicUcoBetException;

public class CityNameConsistencyIsValidImpl implements CityNameConsistencyIsValid{

	@Override
	public void execute(final String data) {
		// TODO Auto-generated method stub
		validateNotNull(data);
		validateLength(data);
		validateFormat(data);
	}
	
	private void validateLength(final String data) {
		if(!TextHelper.maxLenIsValid(data, 50)) {
			var userMessage= "El nombre de la ciudad puede contener máximo 50 caracteres."; 
			BusinessLogicUcoBetException.crear(userMessage);
		}
	}
	
	private void validateFormat(final String data) {
		if(!TextHelper.containsLettersAndSpaces(data)) {
			var userMessage= "El nombre de la ciudad sólo puede contener letras."; 
			BusinessLogicUcoBetException.crear(userMessage);
		}
	}
	
	private void validateNotNull(final String data) {
		if(TextHelper.isEmpty(data)) {
			var userMessage= "El nombre de la ciudad no puede estar vacío...";
			
			throw BusinessLogicUcoBetException.crear(userMessage);
		}
	}

}
