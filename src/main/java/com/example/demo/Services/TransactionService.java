package com.example.demo.Services;

import com.example.demo.Enums.CardStatus;
import com.example.demo.Enums.TransactionStatusEnum;
import com.example.demo.Enums.TransactionTypeEnum;
import com.example.demo.Models.Book;
import com.example.demo.Models.Library_Card;
import com.example.demo.Models.TransactionOfStudent;
import com.example.demo.Repositories.BookRepository;
import com.example.demo.Repositories.LibraryCardRepository;
import com.example.demo.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

     @Autowired
     TransactionService transactionService;

     @Autowired
     BookRepository bookRepository;

     @Autowired
     LibraryCardRepository libraryCardRepository;

     @Autowired
     TransactionRepository transactionRepository;

     public static final Integer bookLimit=6;


     public String issueBook(Integer bookId,Integer cardId) throws Exception {

         TransactionOfStudent transactionOfStudent =
                 new TransactionOfStudent(TransactionStatusEnum.PENDING, TransactionTypeEnum.ISSUE,0);


         Optional<Book> optionalBook = bookRepository.findById(bookId);

         if(!optionalBook.isPresent()){
             throw new Exception("Inavalid bookId");
         }

         Book book = optionalBook.get();

         if(!book.getIsAvailable()){
             throw new Exception(("Book is not Available"));
         }

         Optional<Library_Card> optionalLibrary_card = libraryCardRepository.findById(cardId);

         if(!optionalLibrary_card.isPresent()){
             throw new Exception(("libraryId is Invalid"));
         }

         Library_Card library_card = optionalLibrary_card.get();

         ///checking if cardStatus is not active
         if(!library_card.getCardStatus().equals(CardStatus.ACTIVE)){
             transactionOfStudent.setTransactionStatusEnum(TransactionStatusEnum.FAILED);
             transactionRepository.save(transactionOfStudent);
             throw new Exception("Card Status is Not Active");
         }

         ///checking book limit
         if(library_card.getNoOfBooksIssued()>=bookLimit){
             transactionOfStudent.setTransactionStatusEnum(TransactionStatusEnum.FAILED);
             transactionRepository.save(transactionOfStudent);
             throw new Exception("card has Already reached max limit");
         }

         /*  All the failed cases and invalid scenarios are over */
         //We have reached at a success point


         transactionOfStudent.setTransactionStatusEnum(TransactionStatusEnum.SUCCESS);

         book.setIsAvailable(Boolean.FALSE);
         library_card.setNoOfBooksIssued(library_card.getCard_no()+1);

         ///setting the foreign keys
         transactionOfStudent.setBook(book);
         transactionOfStudent.setLibrary_card(library_card);


         ///now we need to save the transaction object in parent classes

         TransactionOfStudent newTransactionOfStudent = transactionRepository.save(transactionOfStudent);

         book.getTransactionOfStudents().add(newTransactionOfStudent);
         library_card.getTransactionOfStudents().add(newTransactionOfStudent);

         bookRepository.save(book);
         libraryCardRepository.save(library_card);

         return "Transaction has been saved Successfully";

     }

     public String returnBook(Integer bookId,Integer cardId) throws Exception {

         Optional<Book> optionalBook = bookRepository.findById(bookId);

         if(!optionalBook.isPresent()){
             throw new Exception("bookId is Incorrect");
         }

         Book book = optionalBook.get();

         Optional<Library_Card> optionalLibrary_card = libraryCardRepository.findById(cardId);

         if(!optionalLibrary_card.isPresent()){
             throw new Exception("cardId is Incorrect");
         }

         Library_Card library_card = optionalLibrary_card.get();

         List<TransactionOfStudent> list =
                 transactionRepository.findbybookandlibrarycard(book,library_card,TransactionStatusEnum.SUCCESS,TransactionTypeEnum.ISSUE);


         TransactionOfStudent latestTransaction = list.get(list.size()-1);

         Date issueDate = latestTransaction.getCreatedAt();

         long millisecond = Math.abs(System.currentTimeMillis()-issueDate.getTime());

         ///calculating the no of days student has keep a book
         long no_of_days_issued = TimeUnit.DAYS.convert(millisecond,TimeUnit.MILLISECONDS);

         int fineAmount = 0;

         ///calculating finamount
         if(no_of_days_issued>15){
             fineAmount = (int)((no_of_days_issued-15)*5);
         }

         book.setIsAvailable(Boolean.TRUE);
         library_card.setNoOfBooksIssued(library_card.getNoOfBooksIssued()-1);

         TransactionOfStudent transactionOfStudent = new TransactionOfStudent(TransactionStatusEnum.SUCCESS,TransactionTypeEnum.RETURN,fineAmount);

         ///setting parents
         transactionOfStudent.setBook(book);
         transactionOfStudent.setLibrary_card(library_card);

         ///adding new transaction in db
         TransactionOfStudent newTransaction = transactionRepository.save(transactionOfStudent);

         book.getTransactionOfStudents().add(newTransaction);
         library_card.getTransactionOfStudents().add(newTransaction);

         bookRepository.save(book);
         libraryCardRepository.save(library_card);


         return "Book has been returned Successfully";


     }
}
