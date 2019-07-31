package training.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.training.model.entities.enums.Status;
import ua.training.model.entities.enums.Street;
import ua.training.model.entities.enums.Type;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "ORDER")
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long cost;
    @Enumerated
    private Street startStreet;
    private int startHouse;
    @Enumerated
    private Street endStreet;
    private int endHouse;
    private int distance;
    private double waitingTime;
    private double drivingTime;

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(mappedBy = "order")
    private Coupon idCoupon;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User idUser;

    @ManyToOne
    @JoinColumn(name = "idTaxi")
    private Taxi idTaxi;
}
