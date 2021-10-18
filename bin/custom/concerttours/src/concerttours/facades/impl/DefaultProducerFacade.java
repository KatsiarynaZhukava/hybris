package concerttours.facades.impl;

import concerttours.converters.ProducerConverter;
import concerttours.data.ProducerData;
import concerttours.facades.ProducerFacade;
import concerttours.model.ProducerModel;
import concerttours.service.ProducerService;
import org.springframework.beans.factory.annotation.Required;

public class DefaultProducerFacade implements ProducerFacade {
    private ProducerService producerService;
    private ProducerConverter producerConverter;

    @Override
    public ProducerData getProducer(String code) {
        ProducerModel producer = producerService.getProducersByCode(code);
        return producerConverter.convert(producer);
    }

    @Required
    public void setProducerService(ProducerService producerService) {
        this.producerService = producerService;
    }
	@Required
    public void setProducerConverter(ProducerConverter producerConverter) {
        this.producerConverter = producerConverter;
    }
} 