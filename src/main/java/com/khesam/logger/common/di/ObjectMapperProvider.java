package com.khesam.logger.common.di;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.khesam.logger.common.logging.complementary.ComplementaryJacksonAnnotationIntrospector;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

public class ObjectMapperProvider {

    @Produces
    @ApplicationScoped
    @Named("complementary-annotation-introspector")
    public JacksonAnnotationIntrospector provideJacksonAnnotationIntrospector() {
        return new ComplementaryJacksonAnnotationIntrospector();
    }

    @Produces
    @ApplicationScoped
    @Named("logger-object-mapper")
    public ObjectMapper provideObjectMapper(
            @Named("complementary-annotation-introspector") JacksonAnnotationIntrospector jacksonAnnotationIntrospector
    ) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setAnnotationIntrospector(jacksonAnnotationIntrospector);
        return objectMapper;
    }
}
