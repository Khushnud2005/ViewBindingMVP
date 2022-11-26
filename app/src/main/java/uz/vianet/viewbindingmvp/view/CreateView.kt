package uz.vianet.viewbindingmvp.view

import uz.vianet.viewbindingmvp.model.Post

interface CreateView {
    fun createPostSuccess(post: Post?)
    fun createPostFailure(error: String)
}