package br.com.jmsstudio.processor.converter;

import br.com.jmsstudio.processor.converter.annotation.XmlTagName;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class XmlConverter {

    public String convert(Object instance) {
        StringBuilder stringBuilder = new StringBuilder();
        final Class<?> instanceClass = instance.getClass();
        final Field[] fields = instanceClass.getDeclaredFields();

        String tag;
        if (instance instanceof Collection) {
            tag = createTag("list",
                    String.valueOf(((Collection) instance).stream()
                            .map(this::convert)
                            .collect(Collectors.joining()))
            );

        } else {

            final XmlTagName classAnnotation = instanceClass.getDeclaredAnnotation(XmlTagName.class);
            final String classTagName = classAnnotation != null ? classAnnotation.value() : instanceClass.getName();

            tag = createTag(classTagName,
                    Arrays.stream(fields).map(field -> {
                        field.setAccessible(true);

                        try {
                            final XmlTagName fieldAnnotation = field.getDeclaredAnnotation(XmlTagName.class);
                            final String fieldTagName = fieldAnnotation != null ? fieldAnnotation.value() : field.getName();

                            return createTag(fieldTagName, String.valueOf(field.get(instance)));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                            throw new RuntimeException("Error on XML Conversion: ", e);
                        }
                    }).collect(Collectors.joining())
            );
        }
        stringBuilder.append(tag);

        return stringBuilder.toString();
    }

    private String createTag(String tagName, String value) {

        return "<" + tagName + ">"
                + value +
                "</" + tagName + ">";
    }

}
