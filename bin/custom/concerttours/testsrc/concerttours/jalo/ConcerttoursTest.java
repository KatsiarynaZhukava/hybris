package concerttours.jalo;

import static org.assertj.core.api.Assertions.assertThat;
import de.hybris.platform.testframework.HybrisJUnit4TransactionalTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConcerttoursTest extends HybrisJUnit4TransactionalTest
{
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(ConcerttoursTest.class);

	@Before
	public void setUp()
	{
	}

	@After
	public void tearDown()
	{
	}

	@Test
	public void testConcerttours()
	{
		final boolean testTrue = true;
		assertThat(testTrue).isTrue();
	}
}
