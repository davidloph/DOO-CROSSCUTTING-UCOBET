package co.edu.uco.ucobet.busienesslogic.adapter.dto;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;
import co.edu.uco.ucobet.busienesslogic.adapter.Adapter;
import co.edu.uco.ucobet.domain.CityDomain;
import co.edu.uco.ucobet.dto.CityDTO;

public class CityDTOAdapter implements Adapter<CityDomain, CityDTO>{

private static final Adapter<CityDomain, CityDTO> instance = new CityDTOAdapter();
	
	private CityDTOAdapter() {
		
	}
	
	public static Adapter<CityDomain, CityDTO>  getCityDTOAdapter() {
		return instance;
	}
	@Override
	public CityDomain adaptSource(CityDTO data) {
		var dtoToAdapt = ObjectHelper.getDefault(data, CityDTO.create());
		return CityDomain.create(UUIDHelper.convertToUUID(dtoToAdapt.getId()), dtoToAdapt.getName(), StateDTOAdapter.getStateDTOAdapter().adaptSource(dtoToAdapt.getState()));
	}

	@Override
	public CityDTO adaptTarget(final CityDomain data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CityDTO> adaptTarget(final List<CityDomain> data) {
		var results = new ArrayList<CityDTO>();
		for (CityDomain domain : data) {
			results.add(adaptTarget(domain));
		}
				
		return results;
	}
	
	

}
