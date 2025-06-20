package example.repositories;

import example.entity.DemoEntity;
import org.springframework.data.repository.CrudRepository;

public interface DemoEntityRepository extends CrudRepository<DemoEntity, Integer> {
}
