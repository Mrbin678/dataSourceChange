package com.sccl.data_source_change.service;


import com.sccl.data_source_change.domain.Book;

import java.util.List;

/**
 * Create by wangbin
 * 2019-11-18-17:56
 */
public interface BookService  {
    List<Book> getAllBooks();
    List<Book> getAllBooks2();
    int addBook(Book book);
    int addBook2(Book book);
}
