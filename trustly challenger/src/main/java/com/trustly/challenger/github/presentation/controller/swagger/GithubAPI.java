package com.trustly.challenger.github.presentation.controller.swagger;

import org.springframework.http.MediaType;

import com.trustly.challenger.github.presentation.controller.representation.ExtensionRepresentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import reactor.core.publisher.Flux;

@Api(produces = MediaType.APPLICATION_JSON_VALUE)
public interface GithubAPI {

    @ApiOperation(value = "Endpoint to get information about a repository")
    @ApiResponses(value = {
        @ApiResponse(code = 202, message = "Get Information successfully", response = ExtensionRepresentation.class),
        @ApiResponse(code = 404, message = "Repository Private or Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Error")
    })
    Flux<ExtensionRepresentation> getRepoInformation(@ApiParam(value = "User of Repository", required = true) String user,
        @ApiParam(value = "Name of Repository", required = true) String repo);

}
