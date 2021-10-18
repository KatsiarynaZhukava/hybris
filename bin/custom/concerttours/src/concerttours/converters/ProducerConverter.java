package concerttours.converters;

import concerttours.data.ProducerData;
import concerttours.model.ProducerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

public class ProducerConverter implements Converter<ProducerModel,ProducerData> {
    @Override
    public ProducerData convert(final ProducerModel source) throws ConversionException {
        final ProducerData data = new ProducerData();
        return convert(source, data);
    }

    @Override
    public ProducerData convert(final ProducerModel producerModel,
                                final ProducerData producerData) throws ConversionException {
        if (producerModel.getCode() == null || producerModel.getCode().isBlank()) {
                throw new ConversionException("Code should not be null or blank");
        }
        producerData.setFirstName(producerModel.getFirstName());
        producerData.setLastName(producerModel.getLastName());
        return producerData;
    }
}