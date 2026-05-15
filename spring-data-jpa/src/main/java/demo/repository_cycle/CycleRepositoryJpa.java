package demo.repository_cycle;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// 该接口的实现由Data JPA自定生成
@Repository
public interface CycleRepositoryJpa extends CrudRepository<CycleClass, Long>, CycleRepositoryCustom {

}
