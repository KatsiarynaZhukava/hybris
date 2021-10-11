package concerttours.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static concerttours.constants.ConcerttoursConstants.PLATFORM_LOGO_CODE;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import javax.annotation.Resource;
import org.junit.Before;
import org.junit.Test;
import concerttours.service.ConcerttoursService;

@IntegrationTest
public class DefaultConcerttoursServiceIntegrationTest extends ServicelayerBaseTest
{
	@Resource
	private ConcerttoursService concerttoursService;
	@Resource
	private FlexibleSearchService flexibleSearchService;

	@Before
	public void setUp() throws Exception
	{
		concerttoursService.createLogo(PLATFORM_LOGO_CODE);
	}

	@Test
	public void shouldReturnProperUrlForLogo() throws Exception
	{
		final String logoCode = "concerttoursPlatformLogo";
		final String logoUrl = concerttoursService.getHybrisLogoUrl(logoCode);
		assertThat(logoUrl).isNotNull();
		assertThat(logoUrl).isEqualTo(findLogoMedia(logoCode).getURL());
	}

	private MediaModel findLogoMedia(final String logoCode)
	{
		final FlexibleSearchQuery fQuery = new FlexibleSearchQuery("SELECT {PK} FROM {Media} WHERE {code}=?code");
		fQuery.addQueryParameter("code", logoCode);
		return flexibleSearchService.searchUnique(fQuery);
	}

}
