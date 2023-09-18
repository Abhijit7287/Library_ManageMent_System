package com.example.demo.Repositories;


import com.example.demo.Enums.TransactionStatusEnum;
import com.example.demo.Enums.TransactionTypeEnum;
import com.example.demo.Models.Book;
import com.example.demo.Models.Library_Card;
import com.example.demo.Models.TransactionOfStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionOfStudent,Integer> {

    @Query(value = "select * from TransactionofStudent where book=book and library_card=library_card and transactionStatus=transactionStatusEnum and transactiontype = transactionTypeEnum",nativeQuery = true)
    List<TransactionOfStudent>
    findbybookandlibrarycard(Book book, Library_Card library_card, TransactionStatusEnum transactionStatusEnum, TransactionTypeEnum transactionTypeEnum);
}
