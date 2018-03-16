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
package com.vmware.ovsdb.jsonrpc.v1.exception;

/**
 * Thrown to indicate that a RPC connection is closed.
 */
public class JsonRpcConnectionClosedException extends JsonRpcException {

    public JsonRpcConnectionClosedException() {
        super();
    }

    public JsonRpcConnectionClosedException(String message) {
        super(message);
    }

    public JsonRpcConnectionClosedException(Throwable cause) {
        super(cause);
    }

    public JsonRpcConnectionClosedException(String message, Throwable cause) {
        super(message, cause);
    }
}
