package com.abdurrahmanjun.core.remote

import io.reactivex.Observable
import retrofit2.Response

typealias TrolliResponse<T> = Observable<Response<BaseResponse<T>>>
typealias TrolliPagingResponse<T> = Observable<Response<BaseResponse<BasePagingResponse<T>>>>