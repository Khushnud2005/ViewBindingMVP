package uz.vianet.viewbindingmvp.view

import uz.vianet.viewbindingmvp.model.Post


interface DetailView {
    fun onLoadSuccess(post: Post?)
    fun onLoadFailure(error: String)
}