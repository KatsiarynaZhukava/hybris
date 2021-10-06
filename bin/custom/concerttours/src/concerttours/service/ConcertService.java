package concerttours.service;

import concerttours.model.ConcertModel;
import java.util.List;

public interface ConcertService {
    List<ConcertModel> getConcerts();
    ConcertModel getConcertByCode(String code);
}
