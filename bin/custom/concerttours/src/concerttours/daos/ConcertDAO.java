package concerttours.daos;

import concerttours.model.ConcertModel;

import java.util.List;

public interface ConcertDAO
{
    List<ConcertModel> findConcerts();
    List<ConcertModel> findConcertsByCode(String code);
}
