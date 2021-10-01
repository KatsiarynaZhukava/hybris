package concerttours.daos.impl;
import concerttours.model.ConcertModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import concerttours.daos.BandDAO;
import concerttours.model.BandModel;
 
@Component(value = "bandDAO")
public class DefaultBandDAO implements BandDAO
{
    private static final String SELECT_ALL_BANDS = "SELECT {p:" + BandModel.PK + "} " +
            "FROM {" + BandModel._TYPECODE + " AS p} ";
    private static final String SELECT_BAND_BY_CODE = "SELECT {p:" + BandModel.PK + "} " +
            "FROM {" + BandModel._TYPECODE + " AS p} " +
            "WHERE " + "{p:" + BandModel.CODE + "}=?code ";

    @Autowired
    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<BandModel> findBands()
    {
        FlexibleSearchQuery query = new FlexibleSearchQuery(SELECT_ALL_BANDS);
        return flexibleSearchService.<BandModel> search(query).getResult();
    }

    @Override
    public List<BandModel> findBandsByCode(final String code)
    {
        FlexibleSearchQuery query = new FlexibleSearchQuery(SELECT_BAND_BY_CODE);
        query.addQueryParameter("code", code);
        return flexibleSearchService.<BandModel> search(query).getResult();
    }
}