package com.ahrijksmuseum.data.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType?) -> Boolean = { true }
): Flow<ResultType> = flow {

    val data = query().firstOrNull()

    val flow = if (shouldFetch(data)) {
        saveFetchResult(fetch())
        query()
    } else {
        query()
    }

    emitAll(flow)
}