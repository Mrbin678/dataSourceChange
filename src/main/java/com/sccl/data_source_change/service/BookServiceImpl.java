package com.sccl.data_source_change.service;


import com.sccl.data_source_change.aspectj.annotation.DataSource;
import com.sccl.data_source_change.domain.Book;
import com.sccl.data_source_change.enumConst.DataSourceEnum;
import com.sccl.data_source_change.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Create by wangbin
 * 2019-11-18-17:57
 */
@Service
public class BookServiceImpl  implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Transactional
    @Override
    public List<Book> getAllBooks() {
        return bookMapper.getAllBooks();
    }
    @Transactional
    @DataSource(value = DataSourceEnum.SLAVE)
    @Override
    public List<Book> getAllBooks2() {
        return bookMapper.getAllBooks();
    }
    @Transactional
    @Override
    public int addBook(Book book) {
        return bookMapper.addBook(book);
    }
    @Transactional
    @DataSource(value = DataSourceEnum.SLAVE)
    @Override
    public int addBook2(Book book) {
        return bookMapper.addBook(book);
    }
}
