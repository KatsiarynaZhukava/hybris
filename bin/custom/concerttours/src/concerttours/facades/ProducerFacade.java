package concerttours.facades;

import concerttours.data.ProducerData;

public interface ProducerFacade {
    ProducerData getProducer(String code);
}