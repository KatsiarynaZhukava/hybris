package concerttours.converters;

import concerttours.data.BandMemberData;
import concerttours.model.BandMemberModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

public class BandMemberConverter implements Converter<BandMemberModel, BandMemberData> {
    @Override
    public BandMemberData convert(final BandMemberModel source) throws ConversionException {
        final BandMemberData data = new BandMemberData();
        return convert(source, data);
    }

    @Override
    public BandMemberData convert(final BandMemberModel bandMemberModel,
                                  final BandMemberData bandMemberData) throws ConversionException {
        if (bandMemberModel.getCode() == null || bandMemberModel.getCode().isBlank()) {
            throw new ConversionException("Code should not be null or blank");
        }
        bandMemberData.setId(bandMemberModel.getCode());
        bandMemberData.setInstrument(bandMemberModel.getInstrument().getCode());
        bandMemberData.setFullName(bandMemberModel.getFullName());
        return bandMemberData;
    }
}