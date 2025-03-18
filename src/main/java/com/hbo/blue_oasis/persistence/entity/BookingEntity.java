package com.hbo.blue_oasis.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
@Table(name = "booking")
public class BookingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at", nullable = true)
    private Date createAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at", nullable = true)
    private Date updateAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = true)
    private Date date;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "check_in_date", nullable = true)
    private Date checkInDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "check_out_date", nullable = true)
    private Date checkOutDate;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private GuestEntity guest;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "booking_room", joinColumns = @JoinColumn(name = "booking_id"), inverseJoinColumns = @JoinColumn(name = "room_id"))
    private List<RoomEntity> rooms;

}