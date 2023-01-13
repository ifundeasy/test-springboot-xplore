package com.xplore.producerapi.mapper;

import com.xplore.producerapi.model.News;
import com.xplore.producerapi.rest.dto.CreateNewsRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "datetime", ignore = true)
    News toNews(CreateNewsRequest createNewsRequest);
}
