package co.edu.uco.ucobet.busienesslogic.facade.city.impl;

import java.util.List;

import co.edu.uco.ucobet.busienesslogic.adapter.dto.CityDTOAdapter;
import co.edu.uco.ucobet.busienesslogic.facade.city.FindCityFacade;
import co.edu.uco.ucobet.busienesslogic.usecase.city.impl.FindCityImpl;
import co.edu.uco.ucobet.crosscutting.exceptions.BusinessLogicUcoBetException;
import co.edu.uco.ucobet.crosscutting.exceptions.UcoBetException;
import co.edu.uco.ucobet.data.dao.DAOFactory;
import co.edu.uco.ucobet.data.dao.enums.DAOSource;
import co.edu.uco.ucobet.dto.CityDTO;

public final class FindCityFacadeImpl implements FindCityFacade{

	@Override
	public List<CityDTO> execute(CityDTO data) {
		var factory = DAOFactory.getFactory(DAOSource.SQLSERVER);
		
		try {
			var findCityUseCase = new FindCityImpl(factory);
			var cityDomain = CityDTOAdapter.getCityDTOAdapter().adaptSource(data);
			
			return CityDTOAdapter.getCityDTOAdapter().adaptTarget(findCityUseCase.execute(cityDomain));
		}catch (final UcoBetException exception) {
			throw exception;
		}catch (final Exception exception) {
			var userMessage ="Se ha presentado un problema tratando de consultar la información de las ciudades.";
			var technicalMessage ="Se ha presentado un problema inesperado consultando la información de las ciudades. Por favor revise el log de errores para tener más detalles...";
			throw BusinessLogicUcoBetException.crear(userMessage, technicalMessage, exception);
		} finally {
			factory.closeConnection();
		}
	}


}
