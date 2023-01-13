package com.xplore.producerapi.rest;

import com.xplore.producerapi.bus.NewsStream;
import com.xplore.producerapi.mapper.NewsMapper;
import com.xplore.producerapi.model.News;
import com.xplore.producerapi.rest.dto.CreateNewsRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsStream newsStream;
    private final NewsMapper newsMapper;

    @Operation(summary = "Create News")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public News createNew(@Valid @RequestBody CreateNewsRequest createNewsRequest) {
        News news = newsMapper.toNews(createNewsRequest);
        newsStream.newsCreated(news);
        return news;
    }
}
