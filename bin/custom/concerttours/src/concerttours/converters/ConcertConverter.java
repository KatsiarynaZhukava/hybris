package concerttours.converters;

import concerttours.data.BandData;
import concerttours.data.ConcertData;
import concerttours.model.BandModel;
import concerttours.model.ConcertModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import java.util.HashMap;
import java.util.Map;

public class ConcertConverter implements Converter<ConcertModel, ConcertData> {
    private Converter<BandModel, BandData> bandConverter;

    @Override
    public ConcertData convert(final ConcertModel source) throws ConversionException {
        final ConcertData data = new ConcertData();
        return convert(source, data);
    }

    @Override
    public ConcertData convert(final ConcertModel concertModel,
                               final ConcertData concertData) throws ConversionException {
        if (concertModel.getCode() == null || concertModel.getCode().isBlank()) {
            throw new ConversionException("Code should not be null or blank");
        }
        concertData.setId(concertModel.getCode());
        if (concertModel.getConcertType() != null) {
            concertData.setType(concertModel.getConcertType().getCode());
        }
        concertData.setDate(concertModel.getDate());
        concertData.setVenue(concertModel.getVenue());
        concertData.setDaysUntil(concertModel.getDaysUntil());
        Map<Long, BandData> bandToPerformanceOrderMap = new HashMap<>();
        for (Map.Entry<Long,BandModel> entry: concertModel.getBandToPerformanceOrderMap().entrySet()) {
            bandToPerformanceOrderMap.put(entry.getKey(), bandConverter.convert(entry.getValue()));
        }
        concertData.setBandToPerformanceOrderMap(bandToPerformanceOrderMap);
        return concertData;
    }

    public void setBandConverter(Converter<BandModel, BandData> bandConverter) {
        this.bandConverter = bandConverter;
    }
}