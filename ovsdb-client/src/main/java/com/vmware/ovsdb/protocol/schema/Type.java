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

package com.vmware.ovsdb.protocol.schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.vmware.ovsdb.protocol.schema.deserializer.TypeDeserializer;

/**
 * <pre>
 * {@literal
 * <type>
 *   The type of a database column.  Either an <atomic-type> or a JSON
 *   object that describes the type of a database column, with the
 *   following members:
 *       "key": <base-type>                 required
 *       "value": <base-type>               optional
 *       "min": <integer>                   optional
 *       "max": <integer> or "unlimited"    optional
 *
 *   If "min" or "max" is not specified, each defaults to 1.  If "max"
 *   is specified as "unlimited", then there is no specified maximum
 *   number of elements, although the implementation will enforce some
 *   limit.  After considering defaults, "min" must be exactly 0 or
 *   exactly 1, "max" must be at least 1, and "max" must be greater
 *   than or equal to "min".
 *
 *   If "min" and "max" are both 1 and "value" is not specified, the
 *   type is the scalar type specified by "key".
 *
 *   If "min" is not 1 or "max" is not 1, or both, and "value" is not
 *   specified, the type is a set of scalar type "key".
 *
 *   If "value" is specified, the type is a map from type "key" to type
 *   "value".
 * }
 * </pre>
 */
@JsonDeserialize(using = TypeDeserializer.class)
public class Type {

    private final BaseType key;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final BaseType value;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Long min;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Long max;

    public Type(BaseType key) {
        this(key, null, null, null);
    }

    public Type(BaseType key, BaseType value, Long min, Long max) {
        this.key = key;
        this.value = value;
        this.min = min;
        this.max = max;
    }

    public BaseType getKey() {
        return key;
    }

    public BaseType getValue() {
        return value;
    }

    public Long getMin() {
        return min;
    }

    public Long getMax() {
        return max;
    }

    @Override
    public int hashCode() {
        int result = key != null
            ? key.hashCode()
            : 0;
        result = 31 * result + (value != null
            ? value.hashCode()
            : 0);
        result = 31 * result + (min != null
            ? min.hashCode()
            : 0);
        result = 31 * result + (max != null
            ? max.hashCode()
            : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Type)) {
            return false;
        }

        Type that = (Type) o;

        if (key != null
            ? !key.equals(that.key)
            : that.key != null) {
            return false;
        }
        if (value != null
            ? !value.equals(that.value)
            : that.value != null) {
            return false;
        }
        if (min != null
            ? !min.equals(that.min)
            : that.min != null) {
            return false;
        }
        return max != null
            ? max.equals(that.max)
            : that.max == null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " ["
            + "key=" + key
            + ", value=" + value
            + ", min=" + min
            + ", max=" + max
            + "]";
    }
}