package concerttours.service.impl;

import concerttours.daos.ConcertDAO;
import concerttours.daos.impl.DefaultConcertDAO;
import concerttours.model.ConcertModel;
import concerttours.service.ConcertService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import javax.annotation.Resource;
import java.util.List;

public class DefaultConcertService implements ConcertService {
    @Resource
    private ConcertDAO concertDAO;

    @Override
    public List<ConcertModel> getConcerts() {
        return concertDAO.findConcerts();
    }

    @Override
    public ConcertModel getConcertByCode(String code)
    {
        if (code == null) throw new NullPointerException("Code should not be null");
        final List<ConcertModel> result = concertDAO.findConcertsByCode(code);
        if (result.isEmpty())
        {
            throw new UnknownIdentifierException("Concert with code '" + code + "' not found!");
        }
        else if (result.size() > 1)
        {
            throw new AmbiguousIdentifierException("Concert code '" + code + "' is not unique, " + result.size() + " concerts found!");
        }
        return result.get(0);
    }

    public void setConcertDAO(ConcertDAO concertDAO) {
        this.concertDAO = concertDAO;
    }
}
