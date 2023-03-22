package com.example.sample.repository;

import com.example.sample.dto.SearchHistoryDto;
import com.example.sample.entity.QSearchHistory;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<SearchHistoryDto> getInitPopularList() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        QSearchHistory qSearchHistory = QSearchHistory.searchHistory;

        List<SearchHistoryDto> popularList = queryFactory.select(
                        Projections.bean(SearchHistoryDto.class,
                                qSearchHistory.query.as("query"),
                                qSearchHistory.query.count().as("searchCount")))
                .from(qSearchHistory)
                .groupBy(qSearchHistory.query)
                .orderBy(qSearchHistory.query.count().desc())
                .fetch();

        return popularList;
    }
}
