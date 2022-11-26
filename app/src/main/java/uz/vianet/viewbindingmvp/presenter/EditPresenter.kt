package uz.vianet.viewbindingmvp.presenter

import android.util.Log
import android.view.View
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.vianet.viewbindingmvp.model.Post
import uz.vianet.viewbindingmvp.network.RetrofitHttp
import uz.vianet.viewbindingmvp.presenter.impls.EditPresenterImpl
import uz.vianet.viewbindingmvp.view.EditView

class EditPresenter(var editView: EditView) : EditPresenterImpl {
    override fun apiEditPost(post: Post?) {
        RetrofitHttp.postService.updatePost(post!!.id, post)
            .enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {

                    if (response.body() != null) {
                        Log.d("@@@", response.body().toString())
                        editView.onEditSuccess(response.body()!!)

                    } else {
                        editView.onEditFailure(response.toString())
                        Log.d("@@@", response.toString())
                    }
                }

                override fun onFailure(call: Call<Post?>, t: Throwable) {
                    editView.onEditFailure(t.message.toString())
                    Log.d("@@@", t.toString())
                }
            })
    }
}