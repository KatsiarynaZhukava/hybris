package concerttours.daos.impl;
import static org.junit.Assert.assertTrue;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.Registry;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import java.lang.InterruptedException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import concerttours.daos.BandDAO;
import concerttours.model.BandModel;

@IntegrationTest
public class DefaultBandDAOIntegrationTest extends ServicelayerTransactionalTest
{
    @Resource
    private BandDAO bandDAO;
    @Resource
    private ModelService modelService;
    private static final String BAND_CODE = "ROCK-11";
    private static final String BAND_NAME = "Ladies of Rock";
    private static final String BAND_HISTORY = "All female rock band formed in Munich in the late 1990s";
    private static final Long ALBUMS_SOLD = Long.valueOf(1000L);
    @Before public void setUp() throws Exception {
      try {
        Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        new JdbcTemplate(Registry.getCurrentTenant().getDataSource()).execute("CHECKPOINT");
        Thread.sleep(TimeUnit.SECONDS.toMillis(1));
      } catch (InterruptedException exc) {}
    }
    @Test
    public void bandDAOTest()
    {
        List<BandModel> bandsByCode = bandDAO.findBandsByCode(BAND_CODE);
        assertTrue("No Band should be returned", bandsByCode.isEmpty());
        List<BandModel> allBands = bandDAO.findBands();
        final int size = allBands.size();
        final BandModel bandModel = modelService.create(BandModel.class);
        bandModel.setCode(BAND_CODE);
        bandModel.setName(BAND_NAME);
        bandModel.setHistory(BAND_HISTORY);
        bandModel.setAlbumSales(ALBUMS_SOLD);
        modelService.save(bandModel);
        allBands = bandDAO.findBands();
        Assert.assertEquals(size + 1, allBands.size());
        Assert.assertTrue("band not found", allBands.contains(bandModel));
        bandsByCode = bandDAO.findBandsByCode(BAND_CODE);
        Assert.assertEquals("Did not find the Band we just saved", 1, bandsByCode.size());
        Assert.assertEquals("Retrieved Band's code attribute incorrect", BAND_CODE, bandsByCode.get(0).getCode());
        Assert.assertEquals("Retrieved Band's name attribute incorrect", BAND_NAME, bandsByCode.get(0).getName());
        Assert.assertEquals("Retrieved Band's albumSales attribute incorrect", ALBUMS_SOLD, bandsByCode.get(0).getAlbumSales());
        Assert.assertEquals("Retrieved Band's history attribute incorrect", BAND_HISTORY, bandsByCode.get(0).getHistory());
    }
    @Test
    public void testFindBands_EmptyStringParam()
    {
        final List<BandModel> bands = bandDAO.findBandsByCode("");
        Assert.assertTrue("No Band should be returned", bands.isEmpty());
    }
 
    @Test(expected = IllegalArgumentException.class)
    public void testfindBands_NullParam()
    {
        bandDAO.findBandsByCode(null); //method's return value not captured
    }

    @After public void tearDown() {

    }
}