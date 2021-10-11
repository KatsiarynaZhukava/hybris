package concerttours.service.impl;

import concerttours.daos.TokenDAO;
import concerttours.model.TokenItemModel;
import concerttours.service.TokenService;
import de.hybris.platform.servicelayer.model.ModelService;
import javax.annotation.Resource;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class DefaultTokenService implements TokenService {
    @Resource
    private TokenDAO tokenDAO;
    @Resource
    private ModelService modelService;

    @Override
    public void setTokenValue() {
        List<TokenItemModel> tokens = tokenDAO.getAllTokens();
        if (tokens.isEmpty()) {
            throw new NoSuchElementException("No tken found");
        }
        TokenItemModel token = tokens.get(0);
        token.setTokenField(UUID.randomUUID().toString());
        modelService.save(token);
    }

    public void setTokenDAO(TokenDAO tokenDAO) {
        this.tokenDAO = tokenDAO;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }
}
