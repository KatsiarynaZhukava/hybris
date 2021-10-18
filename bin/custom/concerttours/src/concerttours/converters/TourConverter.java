package concerttours.converters;

import concerttours.data.ConcertData;
import concerttours.data.TourData;
import concerttours.enums.ConcertType;
import concerttours.model.ConcertModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.variants.model.VariantProductModel;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.List;

public class TourConverter implements Converter<ProductModel, TourData> {
    private ProducerConverter producerConverter;

    @Override
    public TourData convert(final ProductModel source) throws ConversionException {
        final TourData data = new TourData();
        return convert(source, data);
    }

    @Override
    public TourData convert(final ProductModel productModel,
                            final TourData tourData) throws ConversionException {
        final List<ConcertData> concerts = new ArrayList<>();
        if (productModel.getVariants() != null) {
            for (final VariantProductModel variant : productModel.getVariants()) {
                if (variant instanceof ConcertModel) {
                    final ConcertModel concert = (ConcertModel) variant;
                    final ConcertData summary = new ConcertData();
                    summary.setId(concert.getCode());
                    summary.setDate(concert.getDate());
                    summary.setVenue(concert.getVenue());
                    summary.setType(concert.getConcertType() == ConcertType.OPENAIR ? "Outdoors" : "Indoors");
                    concerts.add(summary);
                }
            }
        }
        tourData.setId(productModel.getCode());
        tourData.setTourName(productModel.getName());
        tourData.setDescription(productModel.getDescription());
        tourData.setConcerts(concerts);
        tourData.setProducer(producerConverter.convert(productModel.getProducer()));
        return tourData;
    }
    @Required
    public void setProducerConverter(ProducerConverter producerConverter) {
        this.producerConverter = producerConverter;
    }
}