package demo.repository.entitygraph;

import demo.repository.entitygraph.entity.Characteristic;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacteristicsRepository extends JpaRepository<Characteristic, Long> {

    // TODO. 采用立即EAGER获取方式来查询
    // This will load the item property of the Characteristic entity eagerly,
    // even though our entity declares a lazy-loading strategy for this property.
    @EntityGraph(attributePaths = {"item"})
    Characteristic findByType(String type);
}
