/**
 * Copyright (C) 2014 Couchbase, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALING
 * IN THE SOFTWARE.
 */
package com.couchbase.client.core.env;

import io.netty.channel.EventLoopGroup;
import rx.Observable;
import rx.Scheduler;

/**
 * A {@link CoreEnvironment} provides all the core building blocks like environment settings and thread pools so
 * that the application can work with it properly.
 *
 * This interface defines the contract. How properties are loaded is chosen by the implementation. See the
 * {@link DefaultCoreEnvironment} class for the default implementation.
 *
 * Note that the {@link CoreEnvironment} is stateful, so be sure to call {@link #shutdown()} properly.
 */
public interface CoreEnvironment {

    /**
     * Shutdown the {@link CoreEnvironment}.
     *
     * @return eventually the success/failure of the shutdown without errors.
     */
    Observable<Boolean> shutdown();

    /**
     * Returns the IO pool for the underlying IO framework.
     *
     * @return the IO pool, shared across resources.
     */
    EventLoopGroup ioPool();

    /**
     * Returns the scheduler which should be used for all core actions that need to happen
     * asynchronously.
     *
     * @return the scheduler used for internal operations.
     */
    Scheduler scheduler();

    /**
     * Identifies if SSL should be enabled.
     *
     * @return true if SSL is enabled, false otherwise.
     */
    boolean sslEnabled();

    String sslKeystoreFile();

    String sslKeystorePassword();

    boolean queryEnabled();

    int queryPort();

    boolean bootstrapHttpEnabled();

    boolean bootstrapCarrierEnabled();

    /**
     * The port to use when bootstrapping through HTTP without SSL.
     *
     * @return the direct http port.
     */
    int bootstrapHttpDirectPort();

    /**
     * The port to use when bootstrapping through HTTP with SSL.
     *
     * @return the https port.
     */
    int bootstrapHttpSslPort();

    /**
     * The port to use when bootstrapping through carrier publication without SSL.
     *
     * @return the direct carrier publication port.
     */
    int bootstrapCarrierDirectPort();

    /**
     * The port to use when bootstrapping through carrier publication with SSL.
     *
     * @return the ssl carrier publication port.
     */
    int bootstrapCarrierSslPort();

    /**
     * Returns the configured IO pool size.
     *
     * @return the pool size (number of threads to use).
     */
    int ioPoolSize();

    int computationPoolSize();


    /**
     * Returns the size of the request ringbuffer.
     *
     * @return the size of the ringbuffer.
     */
    int requestBufferSize();

    /**
     * Returns the size of the response ringbuffer.
     *
     * @return the size of the ringbuffer.
     */
    int responseBufferSize();

    /**
     * The number of key/value service endpoints.
     *
     * @return amount of endpoints per service.
     */
    int kvEndpoints();

    /**
     * The number of view service endpoints.
     *
     * @return amount of endpoints per service.
     */
    int viewEndpoints();

    /**
     * The number of query service endpoints.
     *
     * @return amount of endpoints per service.
     */
    int queryEndpoints();

    /**
     * Library identification string, which can be used as User-Agent header in HTTP requests.
     *
     * @return identification string
     */
    String userAgent();

    /**
     * Returns name and the version of the package. This method used to by @{link userAgent()}.
     *
     * @return string containing package name and version
     */
    String packageNameAndVersion();
}
