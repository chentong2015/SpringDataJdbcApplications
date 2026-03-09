package demo.repository_entitygraph;

import demo.repository_entitygraph.entity.ItemGraph;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemGraphRepository extends JpaRepository<ItemGraph, Long> {

    // TODO. 使用特定声明的EntityGraph名称来查询
    // The default value of the type argument is EntityGraphType.FETCH
    @EntityGraph(value = "Item.characteristics", type = EntityGraph.EntityGraphType.FETCH)
    ItemGraph findByName(String name);
}
