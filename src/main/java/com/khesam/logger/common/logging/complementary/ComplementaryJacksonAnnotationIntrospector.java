package com.khesam.logger.common.logging.complementary;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

public class ComplementaryJacksonAnnotationIntrospector extends JacksonAnnotationIntrospector {

    @Override
    public Object findSerializer(Annotated am) {
        MaskMe annotation = am.getAnnotation(MaskMe.class);
        if (annotation != null) {
            return MaskDataJacksonSerializer.class;
        }

        return super.findSerializer(am);
    }
}
