package demo.repository_entitygraph.entity;

import jakarta.persistence.*;

@Entity
public class Characteristic {

    @Id
    private Long id;
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private ItemGraph item;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ItemGraph getItem() {
        return item;
    }

    public void setItem(ItemGraph item) {
        this.item = item;
    }
}
