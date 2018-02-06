/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.ketal.cornerstone.webservice.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.github.ketal.cornerstone.webservice.controller.WsController;
import com.github.ketal.cornerstone.webservice.util.WSResponse;

public abstract class AbstractReadOnlyResource<T, E extends WsController<T>> {

    @Context
    protected Request request;

    @Context
    protected UriInfo uri;

    protected abstract String getUriPath();

    protected abstract E getController();

    @GET
    @Path("{id: [a-zA-Z0-9]*}")
    public Response get(@PathParam("id") String id) throws Exception {
        return WSResponse.response(request, getController().get(id), Status.OK);
    }
}
