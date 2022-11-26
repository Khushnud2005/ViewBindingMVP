package uz.vianet.viewbindingmvp.presenter.impls

import uz.vianet.viewbindingmvp.model.Post


interface MainPresenterImpl {
    fun apiPostList()
    fun apiPostDelete(post: Post)
}