package co.edu.uco.ucobet.busienesslogic.usecase.city;

import java.util.List;

import co.edu.uco.ucobet.busienesslogic.usecase.UseWithReturn;
import co.edu.uco.ucobet.busienesslogic.usecase.UseWithoutReturn;
import co.edu.uco.ucobet.domain.CityDomain;

public interface FindCity extends UseWithReturn<CityDomain, List<CityDomain>>{

}
