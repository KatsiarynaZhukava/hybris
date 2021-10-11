package concerttours.daos.impl;

import com.ctc.wstx.dtd.TokenModel;
import concerttours.daos.TokenDAO;
import concerttours.model.TokenItemModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;

@Component(value = "tokenDAO")
public class DefaultTokenDAO implements TokenDAO {
    @Resource
    private FlexibleSearchService flexibleSearchService;
    @Resource
    private ModelService modelService;

    @Override
    public List<TokenItemModel> getAllTokens() {
        String query = "SELECT {p:" + TokenItemModel.PK + "} FROM {" + TokenItemModel._TYPECODE + " AS p}";
        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(query);
        return flexibleSearchService.<TokenItemModel> search(flexibleSearchQuery).getResult();
    }

    @Override
    public void save(TokenModel tokenModel) {
        modelService.save(tokenModel);
    }
}