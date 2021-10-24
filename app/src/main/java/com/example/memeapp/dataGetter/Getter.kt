package com.example.memeapp.dataGetter

import androidx.compose.runtime.mutableStateOf
import com.example.memeapp.resourse.Resourse
import com.example.memeapp.response.MemeData
import com.example.memeapp.response.MemeResponse
import com.example.memeapp.retrofit.MainRetroFace
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.lang.Exception

//work as repository
class repo(
    private val retroFace: MainRetroFace
) {
    suspend fun getMeme(): Resourse<MemeResponse> {
        val result = try {
            retroFace.getMemes()
        } catch (e: Exception) {
            return Resourse.Error("Error")
        }
        return Resourse.Success(result)
    }
}

//work as viewmodel
class model(
    private val repo : repo
){
    var loadError = mutableStateOf("")
    fun loadMemes(){
        MainScope().launch {
            val result = repo.getMeme()
            when(result){
                is Resourse.Success -> {
                    var author = result.data!!.author
                    var title = result.data!!.title
                    var url = result.data!!.url
                    MemeData(author,title,url)
                }
                is Resourse.Error -> {
                    loadError.value = result.message!!
                }
            }
        }
    }
}
