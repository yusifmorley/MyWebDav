/*
 * Copyright (C) 2016 the original author or authors.
 * See the NOTICE.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yusifmorley.WebDav;


import io.milton.config.HttpManagerBuilder;
import io.milton.http.HttpManager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang.Validate.notNull;

//调用总接口
/**
 * An easy to use and directly startable WebDAV server.
 */

public class MiltonWebDAVFileServer {
    private final File rootFolder;

    /**
     * @param rootFolder The folder that will be served by the created WebDAV server
     */
    public MiltonWebDAVFileServer(File rootFolder) {
        notNull(rootFolder, "'rootFolder' may not be null");
        if (!rootFolder.isDirectory()) {
            throw new IllegalArgumentException("Given 'rootFolder' is not a directory");
        }
        this.rootFolder = rootFolder;
    }
    /**
     * A map with all authenticated users. If the map contains at least one user at the {@link #start() startup} of the
     * server, authentication is enabled, otherwise disabled.
     *
     * @return a map to put the user credentials in
     */

    /**
     * Creates and starts the server with the current state (credentials, port). After startup, the method will return
     * and not block.
     *
     * @throws IllegalStateException if the server has already been started
     * @throws Exception             if creation of the server fails
     */
    public MiltonHandler start(Map<String,String> userCredentials) throws Exception {
        HttpManagerBuilder builder = new HttpManagerBuilder();
        builder.setResourceFactory(new MiltonWebDAVResourceFactory(this.rootFolder, userCredentials));
        builder.setEnableBasicAuth(userCredentials != null && !userCredentials.isEmpty());
        HttpManager mgr = builder.buildHttpManager();
       return  new MiltonHandler(mgr);
//  MiltonHandler 包含httpManager
    }


}
