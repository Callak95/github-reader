package com.trustly.challenger.github.repository;

import java.util.List;

import com.trustly.challenger.github.domain.File;

import reactor.core.publisher.Mono;

public interface ZipFileRepository {

    Mono<List<File>> getListFiles(final String url);
}
