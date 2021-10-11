package concerttours.controller;

import concerttours.facades.BandFacade;
import concerttours.service.BandService;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;

import static concerttours.constants.ConcerttoursConstants.CATALOG_ID;

@Controller
public class BandsController
{
	@Resource
	private CatalogVersionService catalogVersionService;
	@Resource
	private BandFacade bandFacade;

	@GetMapping(value = "/bands")
	public String getBands(final ModelMap model)
	{
		catalogVersionService.setSessionCatalogVersion(CATALOG_ID,"Online");
		model.addAttribute("bands", bandFacade.getBands());
		return "bands";
	}

	@GetMapping(value = "/bands/{code}")
	public ModelAndView getBand(final @PathVariable String code)
	{
		catalogVersionService.setSessionCatalogVersion(CATALOG_ID,"Online");
		ModelAndView modelAndView = new ModelAndView();
		try {
			modelAndView.addObject("band", bandFacade.getBand(code));
			modelAndView.setViewName("band");
		} catch (NullPointerException | AmbiguousIdentifierException e) {
			modelAndView.setStatus(HttpStatus.BAD_REQUEST);
			modelAndView.setViewName("400");
		} catch (UnknownIdentifierException ex) {
			modelAndView.setStatus(HttpStatus.NOT_FOUND);
			modelAndView.setViewName("404");
		}
		return modelAndView;
	}
}