package com.sessions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LibraryTest {
    Library library;
    @BeforeEach
    public void setup(){
        library = new Library();
        System.out.println("At beforeEach");



    }


    @Test
    public void the_default_number_of_books_in_library_should_be_1_initially() {
        //Library library = new Library();
        int totalNumberOfBooks = library.getBooks().size();
        assertEquals(1,totalNumberOfBooks);

    }
    @Test
    public void adding_to_catalogue_should_increase_the_size_of_books_and_newly_created_book_id_should_be_2(){
        //Library library = new Library();
        Book newlyCreatedBook= library.addToCatalogue("discovery of India","Jawaharlal nehru", 432,11.99);

        int totalBooks=library.getBooks().size();
        List<Book> availableBooks =library.getBooks();
        assertEquals(1, newlyCreatedBook.getId());

    }
    @Test
    public void findBookName_called_with_bookname_available_in_library_should_return_book_object(){
    //Library library = new Library();
    Book book = library.findBookByName("The God Of Small Things");
    assertNotNull(book);
    }
    @Test
    public void findBookByName_alled_with_non_existant_bookname_should_return_null(){
        //Library library = new Library();
        Book book = library.findBookByName("some invalid name");
        assertNull(book);

    }
    @Test
    public void calcualateBookRent_should_return_2_dollars_if_number_of_days_is_4(){
    RentedBook rentedBook = Mockito.mock(RentedBook.class);
    LocalDate fourDaysBeforeToday = LocalDate.now().minusDays(4);
    Mockito.when(rentedBook.getRentedDate()).thenReturn(fourDaysBeforeToday);
    Double calculatedPrice = library.calculateBookRent(rentedBook);
    assertEquals(2, calculatedPrice);
    Mockito.verify(rentedBook,Mockito.times(2)).getRentedDate();
    }

    @Test
    public void calculateBookRent_should_return_6_dollars_if_number_of_days_is_6(){
        RentedBook rentedBook = Mockito.mock(RentedBook.class);
        LocalDate sixDaysBeforeToday = LocalDate.now().minusDays(6);
        Mockito.when(rentedBook.getRentedDate()).thenReturn(sixDaysBeforeToday);
        Double calculatedPrice = library.calculateBookRent(rentedBook);
        assertEquals(6, calculatedPrice);
        Mockito.verify(rentedBook,Mockito.times(2)).getRentedDate();

    }
    @Test
    public void when_returning_book_with_receipt_should_be_returned(){
        RentedBook rentedBook= library.rent("The God Of Small Things");
        double amount = 3.0;
        Receipt receipt=library.returnBook(rentedBook,amount);
        assertNotNull(receipt);
        assertEquals("The God Of Small Things",receipt.bookName);
        assertEquals(LocalDate.now(),receipt.receiptDate);

    }
}