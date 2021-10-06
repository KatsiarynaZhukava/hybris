package concerttours.controller;

import concerttours.service.BandService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;

@Controller
public class BandsController
{
	@Resource
	private BandService bandService;

	@GetMapping(value = "/bands")
	public String getBands(final ModelMap model)
	{
		model.addAttribute("bands", bandService.getBands());
		return "bands";
	}

	@GetMapping(value = "/bands/{code}")
	public ModelAndView getBand(final @PathVariable String code)
	{
		ModelAndView modelAndView = new ModelAndView();
		try {
			modelAndView.addObject("band", bandService.getBandForCode(code));
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