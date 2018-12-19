package com.shiyou.arbitrage.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * Package: com.bloex.web.config
 * FileName: CustomLongConverter
 * Description: Long转String
 * Author: ZhangYX
 * Date:  2017/9/18
 */
public class CustomLongConverter extends StdSerializer<Long> {

    public CustomLongConverter() {
        super(Long.class);
    }

    @Override
    public void serialize(Long aLong, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (aLong.toString().length() > 12) {
            jsonGenerator.writeString(aLong.toString());
        } else {
            jsonGenerator.writeNumber(aLong);
        }
    }
}
