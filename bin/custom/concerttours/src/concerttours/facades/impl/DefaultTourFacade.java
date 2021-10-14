package concerttours.facades.impl;

import concerttours.converters.TourConverter;
import concerttours.data.TourData;
import concerttours.facades.TourFacade;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import org.springframework.beans.factory.annotation.Required;

public class DefaultTourFacade implements TourFacade
{
    private ProductService productService;
    private TourConverter tourConverter;

    @Override
    public TourData getTourDetails(final String tourId)
    {
        if (tourId == null) throw new IllegalArgumentException("Tour id cannot be null");
        final ProductModel product = productService.getProductForCode(tourId);
        if (product == null) return null;
        return tourConverter.convert(product);
    }

    @Required
    public void setProductService(final ProductService productService) {
        this.productService = productService;
    }

    @Required
    public void setTourConverter(final TourConverter tourConverter) {
        this.tourConverter = tourConverter;
    }
}