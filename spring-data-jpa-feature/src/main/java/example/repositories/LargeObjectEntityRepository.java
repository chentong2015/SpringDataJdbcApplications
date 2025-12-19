package example.repositories;

import example.entity_large.LargeObjectEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LargeObjectEntityRepository extends CrudRepository<LargeObjectEntity, Integer> {

    // @Transactional
    List<LargeObjectEntity> findSettingsByName(String name);
}
