package com.csl.vhr.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author MaoLongLong
 * @date 2020/8/5
 */
public class NonNullObjectMapper extends ObjectMapper {

    private static final long serialVersionUID = -4391899840706943593L;

    public NonNullObjectMapper() {
        super();
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

}
