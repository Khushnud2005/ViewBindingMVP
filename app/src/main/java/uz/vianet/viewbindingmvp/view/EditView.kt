package uz.vianet.viewbindingmvp.view

import uz.vianet.viewbindingmvp.model.Post

interface EditView {
    fun onEditSuccess(post: Post)
    fun onEditFailure(error:String)
}