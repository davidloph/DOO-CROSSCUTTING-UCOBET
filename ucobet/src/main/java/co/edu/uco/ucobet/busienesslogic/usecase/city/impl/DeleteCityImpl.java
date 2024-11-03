package co.edu.uco.ucobet.busienesslogic.usecase.city.impl;

import java.util.UUID;

import co.edu.uco.ucobet.busienesslogic.adapter.entity.CityEntityAdapter;
import co.edu.uco.ucobet.busienesslogic.usecase.city.DeleteCity;
import co.edu.uco.ucobet.data.dao.DAOFactory;

public class DeleteCityImpl implements DeleteCity{
	
	private DAOFactory daoFactory;
	
	public DeleteCityImpl(DAOFactory daoFactory) {
		
		setDaoFactory(daoFactory);
	}

	private void setDaoFactory(DAOFactory daoFactory2) {
		
		
	}

	@Override
	public void execute(UUID data) {
		
		//Validate Policies.
	}

}
