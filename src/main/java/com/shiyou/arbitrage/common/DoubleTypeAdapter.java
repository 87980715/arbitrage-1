package com.shiyou.arbitrage.common;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class DoubleTypeAdapter extends TypeAdapter<Double> {

    @Override
    public void write(JsonWriter writer, Double value) throws IOException {
        if (value == null) {
            writer.nullValue();
        } else {
            writer.value(value.toString());
        }
    }

    @Override
    public Double read(JsonReader reader) throws IOException {
        if (reader.peek() == null) {
            return null;
        }
        String value = reader.nextString();
        return Double.parseDouble(value);
    }
}
