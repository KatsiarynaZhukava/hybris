package concerttours.facades.impl;

import concerttours.data.BandData;
import concerttours.facades.BandFacade;
import concerttours.model.BandModel;
import concerttours.service.BandService;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaFormatModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.media.MediaService;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultBandFacade implements BandFacade {
    public  static final String BAND_LIST_FORMAT = "band.list.format.name";
    private static final String BAND_DETAIL_FORMAT = "band.detail.format.name";
    private BandService bandService;
    private MediaService mediaService;
    private ConfigurationService configService;
    private Converter<BandModel, BandData> bandConverter;

    @Override
    public List<BandData> getBands() {
        final String mediaFormatName = configService.getConfiguration().getString(BAND_LIST_FORMAT);
        final MediaFormatModel format = mediaService.getFormat(mediaFormatName);
        final List<BandModel> bandModels = bandService.getBands();
        return bandModels.stream().map(bandModel -> {
            BandData bandData = bandConverter.convert(bandModel);
            bandData.setImageURL(getImageURL(bandModel,format));
            return bandData;
        }).collect(Collectors.toList());
    }

    @Override
    public BandData getBand(final String code) {
        if (code == null) throw new IllegalArgumentException("Band code cannot be null");
        final BandModel bandModel = bandService.getBandForCode(code);
        if (bandModel == null) return null;
        BandData bandData = bandConverter.convert(bandModel);
        final String mediaFormatName = configService.getConfiguration().getString(BAND_DETAIL_FORMAT);
        bandData.setImageURL(getImageURL(bandModel, mediaService.getFormat(mediaFormatName)));
        return bandData;
    }

    protected String getImageURL(final BandModel bandModel, final MediaFormatModel format) {
        final MediaContainerModel container = bandModel.getImage();
        if (container != null) {
            return mediaService.getMediaByFormat(container, format).getDownloadURL();
        }
        return null;
    }

    public void setMediaService(MediaService mediaService) { this.mediaService = mediaService; }
    public void setBandService(BandService bandService) { this.bandService = bandService; }
    public void setConfigurationService(final ConfigurationService configService) {
        this.configService = configService;
    }
    public void setBandConverter(Converter<BandModel, BandData> bandConverter) { this.bandConverter = bandConverter; }
}