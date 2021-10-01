package concerttours.controller;

import concerttours.service.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BandsController
{
	@Autowired
	private BandService bandService;

	@GetMapping(value = "/bands")
	public String getBands(final ModelMap model)
	{
		model.addAttribute("bands", bandService.getBands());
		return "bands";
	}

	@GetMapping(value = "/bands/{code}")
	public String getBand(final @PathVariable String code,
						  final ModelMap model)
	{
		model.addAttribute("band", bandService.getBandForCode(code));
		return "band";
	}
}