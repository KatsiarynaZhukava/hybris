package concerttours.jobs;

import concerttours.service.TokenService;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import javax.annotation.Resource;
import java.util.NoSuchElementException;

public class GenerateTokenFieldValueJob extends AbstractJobPerformable<CronJobModel> {
    @Resource
    private TokenService tokenService;

    @Override
    public PerformResult perform(final CronJobModel cronJobModel) {
        try {
            tokenService.setTokenValue();
        } catch (NoSuchElementException exception) {
            return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
        }
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }
}