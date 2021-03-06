package com.dimas.onepiecefilm.core.source.remote

import android.util.Log
import com.dimas.onepiecefilm.core.source.remote.response.OnePieceResultResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception


class RemoteDataSource(private val apiService: ApiService){

    fun getList(): Flow<ApiResponse<List<OnePieceResultResponse>>> {
        return flow {
            try {
                val response = apiService.getList()
                Log.d("RemoteDataSource","Isi dataList ${response.results}")
                val dataList = response.results
                if (dataList.isNotEmpty()){
                    emit(ApiResponse.Success(dataList))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e :Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("Error Exception",e.toString())
            }
        } .flowOn(Dispatchers.IO)
    }
}