package concerttours.service;

import concerttours.model.ProducerModel;

public interface ProducerService {
    ProducerModel getProducersByCode(String code);
} 