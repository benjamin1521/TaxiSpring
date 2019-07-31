package training.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.training.model.entities.enums.Street;
import ua.training.model.entities.enums.Type;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "TAXI")
@Table(name = "taxis")
public class Taxi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Street locationStreet;
    private int locationHouse;
    private String driverName;
    private String carNumber;
    private boolean busy;

    @Enumerated(EnumType.STRING)
    private Type type;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "idTaxi")
    private List<Order> orders;
}
