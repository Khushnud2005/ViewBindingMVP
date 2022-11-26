package uz.vianet.viewbindingmvp.view

import uz.vianet.viewbindingmvp.model.Post


interface MainView {

    fun onPostListSuccess(posts:ArrayList<Post>?)
    fun onPostListFailure(error:String)

    fun onPostDeleteSuccess(posts:Post?)
    fun onPostDeleteFailure(error:String)
}