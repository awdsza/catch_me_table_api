package com.app.catchmetable.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class StringToEnumConverterFactory implements ConverterFactory<String,Enum> {



    public static class StringToEnumConverter<T extends Enum> implements Converter<String,T>{
        private final Map<String,T> constantMap;
        public StringToEnumConverter(Class<T> targetType) {
            T[] enums = targetType.getEnumConstants();
            constantMap = Arrays.stream(enums).collect(Collectors.toMap(enumConstant -> enumConstant.name().toLowerCase(), Function.identity()));
        }
        @Override
        public T convert(String source) {
            if(source == null || source.isBlank()){
                return null;
            }
            return constantMap.get(source);
            //return new StringToEnumConverter(source);
        }
    }


    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToEnumConverter(targetType);
    }
}
