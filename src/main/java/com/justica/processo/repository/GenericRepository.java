package com.justica.processo.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface GenericRepository<T, I> extends PagingAndSortingRepository<T, I> {
}
