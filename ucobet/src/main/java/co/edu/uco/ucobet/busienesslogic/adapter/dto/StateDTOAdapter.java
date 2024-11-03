package co.edu.uco.ucobet.busienesslogic.adapter.dto;

import java.util.List;

import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;
import co.edu.uco.ucobet.busienesslogic.adapter.Adapter;
import co.edu.uco.ucobet.domain.StateDomain;
import co.edu.uco.ucobet.dto.StateDTO;

public class StateDTOAdapter implements Adapter<StateDomain, StateDTO>{

private static final Adapter<StateDomain, StateDTO> instance = new StateDTOAdapter();
	
	private StateDTOAdapter() {
		
	}
	
	public static Adapter<StateDomain, StateDTO>  getStateDTOAdapter() {
		return instance;
	}
	@Override
	public StateDomain adaptSource(StateDTO data) {
		var dtoToAdapt = ObjectHelper.getDefault(data, StateDTO.create());
		return StateDomain.create(UUIDHelper.convertToUUID(dtoToAdapt.getId()), dtoToAdapt.getName(), CountryDTOAdapter.getCountryDTOAdapter().adaptSource(dtoToAdapt.getCountry()));
	}

	@Override
	public StateDTO adaptTarget(StateDomain data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StateDTO> adaptTarget(List<StateDomain> data) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
