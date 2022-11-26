package uz.vianet.viewbindingmvp.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.vianet.viewbindingmvp.model.Post
import uz.vianet.viewbindingmvp.network.RetrofitHttp
import uz.vianet.viewbindingmvp.presenter.impls.CreatePresenterImpl
import uz.vianet.viewbindingmvp.view.CreateView

class CreatePresenter(var createView: CreateView): CreatePresenterImpl {
    override fun apiPostCreate(post: Post?) {
        RetrofitHttp.postService.createPost(post!!).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                createView.createPostSuccess(response.body())
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                createView.createPostFailure(t.message.toString())
            }
        })
    }

}