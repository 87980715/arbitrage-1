package com.shiyou.arbitrage.common;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class LongTypeAdapter extends TypeAdapter<Long> {

    @Override
    public void write(JsonWriter writer, Long value) throws IOException {
        if (value == null) {
            writer.nullValue();
        } else {
            writer.value(value.toString());
        }
    }

    @Override
    public Long read(JsonReader reader) throws IOException {
        if (reader.peek() == null) {
            return null;
        }
        String value = reader.nextString();
        return Long.parseLong(value);
    }
}
