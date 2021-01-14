package com.springcourse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "client")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 75, nullable = false)
    private String name;

    @Column(length = 14, nullable = false, name = "identification_number")
    private String identificationNumber;

    @Column(length = 200, nullable = false)
    private String address;

    @Column(length = 6, nullable = false, name = "address_number")
    private String addressNumber;

    @Column(length = 10, nullable = false)
    private String cep;

    @Column(length = 200)
    private String complement;

    @Column(length = 11, name = "phone_number")
    private String phoneNumber;

    @Getter(onMethod = @__({@JsonIgnore}))
    @OneToMany(mappedBy = "client")
    private List<Request> request;

}
