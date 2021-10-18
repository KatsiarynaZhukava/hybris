package concerttours.daos.impl;

import concerttours.daos.ProducerDAO;
import concerttours.model.ProducerModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component(value = "producerDAO")
public class DefaultProducerDAO implements ProducerDAO {
    private static final String SELECT_PRODUCER_BY_CODE = "SELECT {" + ProducerModel.PK + "} " +
            "FROM {" + ProducerModel._TYPECODE + "} WHERE {" + ProducerModel.CODE + "} = ?code";

    @Autowired
    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<ProducerModel> findProducersByCode(final String code) {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(SELECT_PRODUCER_BY_CODE);
        query.addQueryParameter("code", code);
        return flexibleSearchService.<ProducerModel> search(query).getResult();
    }
}