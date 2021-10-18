package concerttours.controller;
import concerttours.facades.ProducerFacade;
import de.hybris.platform.catalog.CatalogVersionService;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import concerttours.facades.BandFacade;

@Controller
public class ProducerController
{
	private ProducerFacade producerFacade;

	@RequestMapping(value = "/producers/{producerId}")
	public String showProducerDetails(final @PathVariable String producerId,
								  	  final Model model) throws UnsupportedEncodingException {
		final String decodedProducerId = URLDecoder.decode(producerId, "UTF-8");
		model.addAttribute("producer", producerFacade.getProducer(decodedProducerId));
		return "producer";
	}

	@Autowired
	public void setProducerFacade(ProducerFacade producerFacade) {
		this.producerFacade = producerFacade;
	}
}