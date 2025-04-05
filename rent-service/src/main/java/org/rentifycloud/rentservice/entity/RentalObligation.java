package org.rentifycloud.rentservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "rental_obligation")
@Getter
@Setter
public class RentalObligation extends AbstractVersionedEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    private Project project;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    @ManyToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "rent_obligation_instance",
            joinColumns = {@JoinColumn(name = "rent_obligation_id")},
            inverseJoinColumns = {@JoinColumn(name = "instance_id")})
    private List<Instance> instances;

    @Column(name = "start_date", nullable = false)
    private Instant startDate;

    @Column(name = "end_date", nullable = false)
    private Instant endDate;

    @Column(name = "shift_count", nullable = false)
    private double shiftCount;

    @Column(name = "discount", nullable = false)
    private double discount;

    @Column(name = "total_price", nullable = false)
    private long totalPrice;

    @Column(name = "state", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private RentalObligationStateEnum state;
    public enum RentalObligationStateEnum {
        BOOKED,
        RENTED,
        FINISHED,
        CANCELED
    }

    @Column(name = "instance_count", nullable = false)
    private int instanceCount;
}
