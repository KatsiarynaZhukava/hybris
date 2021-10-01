package concerttours.daos.impl;

import concerttours.daos.ConcertDao;
import concerttours.model.ConcertModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "concertDAO")
public class DefaultConcertDao implements ConcertDao {
    @Autowired
    private FlexibleSearchService flexibleSearchService;

    private static final String SELECT_ALL_CONCERTS = "SELECT {" + ConcertModel.PK + "} " +
            "FROM {" + ConcertModel._TYPECODE + "}";
    private static final String SELECT_CONCERT_BY_CODE = "SELECT {" + ConcertModel.PK + "} " +
            "FROM {" + ConcertModel._TYPECODE + "} WHERE {" + ConcertModel.CODE + "} = ?code";

    @Override
    public List<ConcertModel> findConcerts() {
        FlexibleSearchQuery query = new FlexibleSearchQuery(SELECT_ALL_CONCERTS);
        return flexibleSearchService.<ConcertModel>search(query).getResult();
    }

    @Override
    public List<ConcertModel> findConcertsByCode(String code) {
        FlexibleSearchQuery query = new FlexibleSearchQuery(SELECT_CONCERT_BY_CODE);
        query.addQueryParameter("code", code);
        return flexibleSearchService.<ConcertModel>search(query).getResult();
    }
}
