package concerttours.daos;

import com.ctc.wstx.dtd.TokenModel;
import concerttours.model.TokenItemModel;

import java.util.List;

public interface TokenDAO {
    List<TokenItemModel> getAllTokens();
    void save(final TokenModel tokenModel);
}
