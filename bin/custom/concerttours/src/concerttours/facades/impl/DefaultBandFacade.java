package concerttours.facades.impl;

import concerttours.converters.BandConverter;
import concerttours.data.BandData;
import concerttours.data.ConcertData;
import concerttours.facades.BandFacade;
import concerttours.model.BandModel;
import concerttours.model.ConcertModel;
import concerttours.service.BandService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultBandFacade implements BandFacade {
    private BandService bandService;
    private Converter<BandModel, BandData> bandConverter;

    @Override
    public List<BandData> getBands() {
        final List<BandModel> bandModels = bandService.getBands();
        return bandModels.stream().map(bandModel -> bandConverter.convert(bandModel)).collect(Collectors.toList());
    }

    @Override
    public BandData getBand(final String code) {
        if (code == null) throw new IllegalArgumentException("Band code cannot be null");
        final BandModel band = bandService.getBandForCode(code);
        if (band == null) return null;
        return bandConverter.convert(band);
    }

    public void setBandService(BandService bandService) { this.bandService = bandService; }

    public void setBandConverter(Converter<BandModel, BandData> bandConverter) { this.bandConverter = bandConverter; }
}