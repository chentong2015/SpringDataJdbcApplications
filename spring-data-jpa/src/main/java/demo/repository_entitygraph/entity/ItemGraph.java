package demo.repository_entitygraph.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

// TODO. 使用EntityGraph改变JPA查询方式，修改成立即获取
// Apply the FetchType.EAGER strategy on the specified attribute nodes
@Entity
@NamedEntityGraph(name = "Item.characteristics", attributeNodes = @NamedAttributeNode("characteristics"))
public class ItemGraph {

    @Id
    private Long id;
    private String name;

    // The default fetch strategy of the @OneToMany annotation is lazy.
    @OneToMany(mappedBy = "item")
    private List<Characteristic> characteristics = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }
}
