package co.edu.uco.ucobet.busienesslogic.facade.city.impl;

import co.edu.uco.ucobet.busienesslogic.adapter.dto.CityDTOAdapter;
import co.edu.uco.ucobet.busienesslogic.facade.city.RegisterNewCityFacade;
import co.edu.uco.ucobet.busienesslogic.usecase.city.impl.RegisterNewCityImpl;
import co.edu.uco.ucobet.crosscutting.exceptions.BusinessLogicUcoBetException;
import co.edu.uco.ucobet.crosscutting.exceptions.UcoBetException;
import co.edu.uco.ucobet.data.dao.DAOFactory;
import co.edu.uco.ucobet.data.dao.enums.DAOSource;
import co.edu.uco.ucobet.dto.CityDTO;

public final class RegisterNewCityFacadeImpl implements RegisterNewCityFacade{
	
	@Override
	public void execute(final CityDTO data) {
		var factory = DAOFactory.getFactory(DAOSource.SQLSERVER);
		
		try {
			factory.initTransaction();
			var registerNewCityUseCase = new RegisterNewCityImpl(factory);
			var cityDomain = CityDTOAdapter.getCityDTOAdapter().adaptSource(data);
			
			registerNewCityUseCase.execute(cityDomain);
			
			factory.commitTransaction();
		}catch (final UcoBetException exception) {
			factory.rollbackTransaction();
			throw exception;
		}catch (final Exception exception) {
			factory.rollbackTransaction();
			var userMessage ="Se ha presentado un problema tratando de registrar la información de la nueva ciudad.";
			var technicalMessage ="Se ha presentado un problema inseperado registrando la información de la nueva ciudad. Por favor revise el log de errores para tener más detalles...";
			throw BusinessLogicUcoBetException.crear(userMessage, technicalMessage, exception);
		} finally {
			factory.closeConnection();
		}
	}

}
