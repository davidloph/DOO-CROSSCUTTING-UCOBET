package co.edu.uco.ucobet.busienesslogic.usecase.city.impl;

import java.util.UUID;

import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;
import co.edu.uco.ucobet.busienesslogic.adapter.entity.CityEntityAdapter;
import co.edu.uco.ucobet.busienesslogic.usecase.city.RegisterNewCity;
import co.edu.uco.ucobet.busienesslogic.usecase.city.rules.CityNameConsistencyIsValid;
import co.edu.uco.ucobet.busienesslogic.usecase.city.rules.CityNameDoesNotExistsForState;
import co.edu.uco.ucobet.busienesslogic.usecase.city.rules.impl.CityNameConsistencyIsValidImpl;
import co.edu.uco.ucobet.busienesslogic.usecase.city.rules.impl.CityNameDoesNotExistsForStateImpl;
import co.edu.uco.ucobet.busienesslogic.usecase.state.rules.StateExists;
import co.edu.uco.ucobet.busienesslogic.usecase.state.rules.impl.StateExistsImpl;
import co.edu.uco.ucobet.crosscutting.exceptions.BusinessLogicUcoBetException;
import co.edu.uco.ucobet.data.dao.DAOFactory;
import co.edu.uco.ucobet.domain.CityDomain;

public class RegisterNewCityImpl implements RegisterNewCity {
	
	private DAOFactory daoFactory;
	private CityNameDoesNotExistsForState cityNameDoesNotExistsForState = new CityNameDoesNotExistsForStateImpl();
	private StateExists stateExists = new StateExistsImpl();
	private CityNameConsistencyIsValid cityNameConsistencyIsValid = new CityNameConsistencyIsValidImpl();
	

	public RegisterNewCityImpl(final DAOFactory daoFactory) {
		setDaoFactory(daoFactory);
	}

	@Override
	public void execute(CityDomain data) {
		// TODO Auto-generated method stub
		cityNameConsistencyIsValid.execute(data.getName());
		cityNameDoesNotExistsForState.execute(data, daoFactory);
		stateExists.execute(data.getState().getId(), daoFactory);
		
		
		var cityDomainToMap = CityDomain.create(generateId(), data.getName(), data.getState());
		var cityEntity = CityEntityAdapter.getCityEntityAdapter().adaptSource(cityDomainToMap);
		daoFactory.getCityDAO().create(cityEntity);
	}
	
	private UUID generateId() {
		var id = UUIDHelper.generate();
		var cityEntity = daoFactory.getCityDAO().findByID(null);
		
		if(UUIDHelper.isEqual(cityEntity.getId(), id)) {
			id = generateId();
		}
		return id;
	}

	private void setDaoFactory(final DAOFactory daoFactory) {
		if (ObjectHelper.isNull(daoFactory)) {
			var userMessage= "Se ha presentado un problema inesperado tratando de llevar a cabo el registro de la información de la ciudad deseada. Por favor intente de nuevo y si el problema persiste, llame...";
			var technicalMessage = "El dao factory requerido para crear la clase que actualiza la ciudad llegó nula...";
			
			throw BusinessLogicUcoBetException.crear(userMessage, technicalMessage);
		}
		this.daoFactory = daoFactory;
	}
}
