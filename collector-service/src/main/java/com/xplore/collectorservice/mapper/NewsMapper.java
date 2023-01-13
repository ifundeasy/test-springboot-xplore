package com.xplore.collectorservice.mapper;

import com.xplore.collectorservice.model.News;
import com.xplore.commonsnews.avro.NewsEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = StringMapper.class)
public interface NewsMapper {

    News toNews(NewsEvent newsEvent);
}
