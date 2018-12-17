package com.shiyou.arbitrage.common;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.math.BigDecimal;

public class BigDecimalTypeAdapter extends TypeAdapter<BigDecimal> {

    @Override
    public void write(JsonWriter writer, BigDecimal value) throws IOException {
        if (value == null) {
            writer.nullValue();
        } else if (value.compareTo(BigDecimal.ZERO) == 0) {
            writer.value(0);
        } else {
            value = value.setScale(8, BigDecimal.ROUND_DOWN);
            writer.value(value);
        }
    }

    @Override
    public BigDecimal read(JsonReader reader) throws IOException {
        if (reader.peek() == null) {
            return null;
        }
        String value = reader.nextString();
        return new BigDecimal(value).setScale(8, BigDecimal.ROUND_DOWN);
    }
}
