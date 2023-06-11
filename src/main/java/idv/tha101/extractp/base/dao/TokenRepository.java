package idv.tha101.extractp.base.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import idv.tha101.extractp.base.pojo.Token;

public interface TokenRepository extends JpaRepository<Token, Integer> {

//	@Query(value = """
//			select t from Token t inner join MemberVO m
//			on t.user = m.member_id
//			where m.member_id = :id and (t.expired = false or t.revoked = false)
//			""")
	@Query(value = "select \r\n"
			+ "    t.*\r\n"
			+ "from token t\r\n"
			+ "join member m\r\n"
			+ "	on t.member_id = m.member_id\r\n"
			+ "where m.member_id = ?;",nativeQuery = true)
	List<Token> findAllValidTokenByUser(Integer id);

	Optional<Token> findByToken(String token);
}