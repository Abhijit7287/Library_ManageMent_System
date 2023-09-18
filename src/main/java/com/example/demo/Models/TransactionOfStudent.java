package com.example.demo.Models;

import com.example.demo.Enums.TransactionStatusEnum;
import com.example.demo.Enums.TransactionTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionOfStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatusEnum transactionStatusEnum;

    @Enumerated(value = EnumType.STRING)
    private TransactionTypeEnum transactionTypeEnum;

    private Integer fineAmount;

    public TransactionOfStudent(TransactionStatusEnum transactionStatusEnum, TransactionTypeEnum transactionTypeEnum, Integer fineAmount) {
        this.transactionStatusEnum = transactionStatusEnum;
        this.transactionTypeEnum = transactionTypeEnum;
        this.fineAmount = fineAmount;
    }

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Book book;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Library_Card library_card;
}
