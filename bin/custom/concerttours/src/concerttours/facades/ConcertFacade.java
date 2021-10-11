package concerttours.facades;

import concerttours.data.ConcertData;
import java.util.List;

public interface ConcertFacade {
    ConcertData getConcert(String code);
    List<ConcertData> getConcerts();
}
