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

package com.vmware.ovsdb.protocol.operation.notation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.vmware.ovsdb.protocol.operation.notation.deserializer.MapDeserializer;
import com.vmware.ovsdb.protocol.util.OvsdbConstant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * {@literal
 * <map>
 *   A 2-element JSON array that represents a database map value.  The
 *   first element of the array must be the string "map", and the
 *   second element must be an array of zero or more <pair>s giving the
 *   values in the map.  All of the <pair>s must have the same key and
 *   value types.
 *
 *   (JSON objects are not used to represent <map> because JSON only
 *   allows string names in an object.)
 * }
 * </pre>
 */
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@JsonDeserialize(using = MapDeserializer.class)
public class Map<K, V> extends Value {

    public String MAP = OvsdbConstant.MAP; // For serializing

    private List<Pair<K, V>> pairs;

    public Map(List<Pair<K, V>> pairs) {
        this.pairs = pairs;
    }

    public static <K, V> Map of(java.util.Map<K, V> map) {
        if (map == null) {
            return null;
        }
        return new Map<>(
            map.keySet().stream().map(
                key -> new Pair<>(new Atom<>(key), new Atom<>(map.get(key)))
            ).collect(Collectors.toList())
        );
    }

    public List<Pair<K, V>> getPairs() {
        return pairs;
    }

    public void setPairs(List<Pair<K, V>> pairs) {
        this.pairs = pairs;
    }

    @Override
    public int hashCode() {
        return pairs != null ? pairs.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Map)) {
            return false;
        }

        Map<?, ?> map = (Map<?, ?>) o;

        return pairs != null
            ? pairs.equals(map.pairs)
            : map.pairs == null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + pairs;
    }
}
