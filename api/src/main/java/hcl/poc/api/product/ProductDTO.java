package hcl.poc.api.product;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

public class ProductDTO {

    @Schema(description = "Unique identifier of the product.",
            example = "1", required = true)
    private Long id;

    @Schema(description = "The name of the product.",
            example = "milk", required = true)
    private String name;

    @Schema(description = "The weight of the product.",
            example = "250.22", required = true)
    private double weight;

    public ProductDTO(){}

    @Override
    public String toString() {
        return "ProductDTO{" +
                "product_id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDTO)) return false;
        ProductDTO that = (ProductDTO) o;
        return Double.compare(that.getWeight(), getWeight()) == 0 && getId().equals(that.getId()) && getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getWeight());
    }

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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public ProductDTO(Long id, String name, double weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }
}

