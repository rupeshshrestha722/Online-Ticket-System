package com.interview.project.onlineticketsystem_backend.entities.requests;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.interview.project.onlineticketsystem_backend.entities.Role;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * This class defines the deserializer
 */
public class RoleDeserializer extends  JsonDeserializer<Role> {
    @Override
    public Role deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);

        if(node == null) {
            return null;
        }

        String text = node.textValue();
        if(text == null || text.isEmpty()){
            return null;
        }

        for(Role role: Role.values()){
            if(text.equals(role.toString())){
                return role;
            }
        }

        throw new IllegalArgumentException(
                "role must be in " + StringUtils.arrayToDelimitedString(Role.values(), ","));

    }
}
