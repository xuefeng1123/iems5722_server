package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Repository;

import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    public List<Token> findTokensByUserIdIn(List<Integer> user_ids);
    public Boolean existsAllByUserIdAndTokenStr(int user_id, String token_str);
}