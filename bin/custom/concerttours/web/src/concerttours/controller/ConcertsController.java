package concerttours.controller;


import concerttours.service.ConcertService;
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
public class ConcertsController
{
	@Resource
	private CatalogVersionService catalogVersionService;
	@Resource
	private ConcertService concertService;

	@GetMapping(value = "/concerts")
	public String getConcerts(final ModelMap model)
	{
		catalogVersionService.setSessionCatalogVersion(CATALOG_ID,"Online");
		model.addAttribute("concerts", concertService.getConcerts());
        return "concerts";
	}

	@GetMapping(value = "/concerts/{code}")
	public ModelAndView getConcert(final @PathVariable String code)
	{
		catalogVersionService.setSessionCatalogVersion(CATALOG_ID,"Online");
		ModelAndView modelAndView = new ModelAndView();
		try {
			modelAndView.addObject("concert", concertService.getConcertByCode(code));
			modelAndView.setViewName("concert");
		} catch (NullPointerException | AmbiguousIdentifierException e) {
			modelAndView.setStatus(HttpStatus.BAD_REQUEST);
			modelAndView.setViewName("400");
		} catch (UnknownIdentifierException e) {
			modelAndView.setStatus(HttpStatus.NOT_FOUND);
			modelAndView.setViewName("404");
		}
		return modelAndView;
	}
}