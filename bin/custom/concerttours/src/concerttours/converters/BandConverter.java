package concerttours.converters;

import concerttours.data.BandData;
import concerttours.data.BandMemberData;
import concerttours.data.TourSummaryData;
import concerttours.enums.MusicType;
import concerttours.model.BandMemberModel;
import concerttours.model.BandModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

public class BandConverter implements Converter<BandModel, BandData> {
    private Converter<BandMemberModel, BandMemberData> bandMemberConverter;

    @Override
    public BandData convert(final BandModel source) throws ConversionException {
        final BandData data = new BandData();
        return convert(source, data);
    }

    @Override
    public BandData convert(final BandModel bandModel, final BandData bandData) throws ConversionException {
        if (bandModel.getCode() == null || bandModel.getCode().isBlank()) {
            throw new ConversionException("Code should not be null or blank");
        }
        bandData.setId(bandModel.getCode());
        bandData.setAlbumsSold(bandModel.getAlbumSales());
        bandData.setName(bandModel.getName());
        bandData.setDescription(bandModel.getHistory());
        List<TourSummaryData> tours = bandModel.getTours()
                    .stream()
                    .map(tour -> {
                        TourSummaryData data = new TourSummaryData();
                        data.setId(tour.getCode());
                        data.setTourName(tour.getName());
                        return data;
                        })
                    .collect(Collectors.toList());
        bandData.setTours(tours);
        bandData.setGenres(bandModel.getTypes().stream()
                                               .map(MusicType::getCode)
                                               .collect(Collectors.toList()));
        bandData.setMembers(bandModel.getMembers().stream()
                .map(bandMemberModel -> bandMemberConverter.convert(bandMemberModel))
                .collect(Collectors.toList()));
        return bandData;
    }

    public void setBandMemberConverter(Converter<BandMemberModel, BandMemberData> bandMemberConverter) {
        this.bandMemberConverter = bandMemberConverter;
    }
}