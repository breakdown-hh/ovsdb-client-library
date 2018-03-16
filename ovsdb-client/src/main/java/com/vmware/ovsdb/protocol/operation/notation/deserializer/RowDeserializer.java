/*
 * Copyright (c) 2018 VMware, Inc. All Rights Reserved.
 *
 * This product is licensed to you under the BSD-2 license (the "License").
 * You may not use this product except in compliance with the BSD-2 License.
 *
 * This product may include a number of subcomponents with separate copyright
 * notices and license terms. Your use of these subcomponents is subject to the
 * terms and conditions of the subcomponent's license, as noted in the LICENSE
 * file.
 *
 * SPDX-License-Identifier: BSD-2-Clause
 */

package com.vmware.ovsdb.protocol.operation.notation.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.vmware.ovsdb.protocol.operation.notation.Row;
import com.vmware.ovsdb.protocol.operation.notation.Value;
import java.io.IOException;
import java.util.Map;

public class RowDeserializer extends StdDeserializer<Row> {

    protected RowDeserializer() {
        this(null);
    }

    protected RowDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Row deserialize(
        JsonParser jp, DeserializationContext ctxt
    ) throws IOException {
        TypeReference<Map<String, Value>> typeRef
            = new TypeReference<Map<String, Value>>() {
        };
        Map<String, Value> columns = jp.readValueAs(typeRef);
        return new Row(columns);
    }
}
