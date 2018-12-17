package com.shiyou.arbitrage.common;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class IntegerTypeAdapter extends TypeAdapter<Integer> {

    @Override
    public void write(JsonWriter writer, Integer value) throws IOException {
        if (value == null) {
            writer.nullValue();
        } else {
            writer.value(value.intValue());
        }
    }

    @Override
    public Integer read(JsonReader reader) throws IOException {
        if (reader.peek() == null) {
            return null;
        }
        String value = reader.nextString();
        return Integer.parseInt(value);
    }
}
