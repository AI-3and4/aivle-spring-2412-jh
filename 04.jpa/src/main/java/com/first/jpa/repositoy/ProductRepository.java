package com.first.jpa.repositoy;

import com.first.jpa.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<News, Long> {

}
