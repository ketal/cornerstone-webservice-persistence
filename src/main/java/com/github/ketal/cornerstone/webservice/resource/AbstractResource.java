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

import java.net.URI;

import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.github.ketal.cornerstone.webservice.controller.WsController;
import com.github.ketal.cornerstone.webservice.util.WSResponse;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

public abstract class AbstractResource<T, E extends WsController<T>> {

    @Context
    protected Request request;

    @Context
    protected UriInfo uri;

    protected abstract String getUriPath();

    protected abstract E getController();

    @POST
    public Response post(@Valid T object) throws Exception {
        int id = getController().post(object);
        URI location = UriBuilder.fromUri(uri.getBaseUri()).path(this.getUriPath()).path(Integer.toString(id)).build();
        return Response.created(location).build();
    }

    @GET
    @Path("{id: [1-9]\\d*}")
    public Response get(@PathParam("id") int id) throws Exception {
        return WSResponse.response(request, getController().get(id), Status.OK);
    }

    @PUT
    @Path("{id: [1-9]\\d*}")
    public Response put(@PathParam("id") int id, @Valid T object) throws Exception {
        getController().put(id, object);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id: [1-9]\\d*}")
    public Response delete(@PathParam("id") int id) throws Exception {
        getController().delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

}
