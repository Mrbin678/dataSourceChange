package com.sccl.data_source_change.mapper;


import com.sccl.data_source_change.domain.Book;

import java.util.List;

/**
 * Create by wangbin
 * 2019-08-07-1:18
 */
public interface BookMapper {
    List<Book> getAllBooks();
    int addBook(Book book);
}
