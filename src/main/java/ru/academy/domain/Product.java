package ru.academy.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products", schema = "userdb")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "balance")
    private Double balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", type=" + type +
                ", userId=" + user.getId() +
                '}';
    }
}
