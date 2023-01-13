package com.xplore.collectorservice.service;

import com.xplore.collectorservice.model.News;
import com.xplore.collectorservice.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Override
    public News createNews(News news) {
        return newsRepository.save(news);
    }
}
