package com.central.common.redis.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * @author: miv
 * @Date: 2019-05-31 20:23
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
public class RedisObjectSerializer implements RedisSerializer<Object> {
    /**
     * 做一个空数组，不是null
     */
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    /**
     *  为了方便进行对象与字节数组的转换，所以应该首先准备出两个转换器
     */
    private Converter<Object, byte[]> serializingConverter = new SerializingConverter();
    private Converter<byte[], Object> deserializingConverter = new DeserializingConverter();

    @Override
    public byte[] serialize(Object obj) {
        // 这个时候没有要序列化的对象出现，所以返回的字节数组应该就是一个空数组
        if (obj == null) {
            return EMPTY_BYTE_ARRAY;
        }
        // 将对象变为字节数组
        return this.serializingConverter.convert(obj);
    }

    @Override
    public Object deserialize(byte[] data) {
        // 此时没有对象的内容信息
        if (data == null || data.length == 0) {
            return null;
        }
        return this.deserializingConverter.convert(data);
    }
}
