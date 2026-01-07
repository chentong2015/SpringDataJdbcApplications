package demo.repositories;

import demo.entity.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeakerJpaRepository extends JpaRepository<Speaker, Long> {

    Speaker findSpeakerBySpecialLastName(String lastName);

    // TODO. 必须使用自定义的Entity Name名称
    @Query("select speaker from demo.entity.Speaker speaker " +
            "where speaker.first_name = :firstName or speaker.last_name = :lastName")
    Speaker findBySpecialFirstNameAndLastName(@Param("lastName") String firstName, @Param("firstName") String lastName);

    @Query(value = "SELECT * from t_speackers", nativeQuery = true)
    List<Speaker> getListSpeakers();
}
