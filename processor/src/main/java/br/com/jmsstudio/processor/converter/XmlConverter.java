package br.com.jmsstudio.processor.converter;

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

            tag = createTag(instanceClass.getName(),
                    Arrays.stream(fields).map(field -> {
                        field.setAccessible(true);

                        try {
                            return createTag(field.getName(), String.valueOf(field.get(instance)));
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
