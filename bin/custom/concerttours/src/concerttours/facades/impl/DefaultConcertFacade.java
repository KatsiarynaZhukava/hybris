package concerttours.facades.impl;

import concerttours.converters.ConcertConverter;
import concerttours.data.ConcertData;
import concerttours.facades.ConcertFacade;
import concerttours.model.ConcertModel;
import concerttours.service.ConcertService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultConcertFacade implements ConcertFacade {
    private ConcertService concertService;
    private Converter<ConcertModel,ConcertData> concertConverter;

    @Override
    public List<ConcertData> getConcerts() {
        final List<ConcertModel> concerts = concertService.getConcerts();
        return concerts.stream().map(concertModel -> concertConverter.convert(concertModel)).collect(Collectors.toList());
    }

    @Override
    public ConcertData getConcert(final String code) {
        if (code == null) throw new IllegalArgumentException("Code should not be null");
        final ConcertModel concert = concertService.getConcertByCode(code);
        if (concert == null) return null;
        return concertConverter.convert(concert);
    }

    public void setConcertService(ConcertService concertService) {
        this.concertService = concertService;
    }

    public void setConcertConverter(Converter<ConcertModel,ConcertData> concertConverter) {
        this.concertConverter = concertConverter;
    }
}