package com.hbo.blue_oasis.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Entity
@Table(name = "room")
public class RoomEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, updatable = false)
    private int number;

    @Column(nullable = true, updatable = false)
    private Boolean availability;

    @Column(nullable = true, updatable = false)
    private BigDecimal price;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at", nullable = true)
    private Date createAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at", nullable = true)
    private Date updateAt;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = true)
    private List<ImageRoomEntity> imageRoom;

}