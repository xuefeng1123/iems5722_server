package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Service;

import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Entity.Token;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Repository.TokenRepository;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Autowired
    public TokenRepository tokenRepository;

    public String sendToken(int userId, String tokenStr){
        try{
            Token token = Token.builder()
                    .userId(userId)
                    .tokenStr(tokenStr)
                    .build();
            tokenRepository.saveAndFlush(token);
            return Response.ReturnOK();
        }catch(Exception e){
            return Response.ReturnError(e.getMessage());
        }
    }
}
