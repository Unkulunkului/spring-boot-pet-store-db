package by.home.springbootpetstoredb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders")
public class Order {

    public Order(long petId, int quantity, String shipDate, OrderStatusEnum status, boolean complete) {
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Min(1)
    private long petId;
    @Min(1)
    private int quantity;
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
    private String shipDate;
    private OrderStatusEnum status;
    private boolean complete;
}
