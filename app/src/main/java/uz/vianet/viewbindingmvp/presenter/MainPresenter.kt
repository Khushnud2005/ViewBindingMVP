package uz.vianet.viewbindingmvp.presenter

import android.util.Log
import android.view.View
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.vianet.viewbindingmvp.model.Post
import uz.vianet.viewbindingmvp.network.RetrofitHttp
import uz.vianet.viewbindingmvp.presenter.impls.MainPresenterImpl
import uz.vianet.viewbindingmvp.view.MainView

class MainPresenter(var mainView: MainView) : MainPresenterImpl {
    override fun apiPostList() {

        RetrofitHttp.postService.listPost().enqueue(object : Callback<ArrayList<Post>> {
            override fun onResponse(call: Call<ArrayList<Post>>, response: Response<ArrayList<Post>>) {

                mainView.onPostListSuccess(response.body())
            }

            override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                Log.e("@@@", t.message.toString())
                mainView.onPostListFailure(t.message.toString())
            }
        })
    }

    override fun apiPostDelete(post: Post) {
        RetrofitHttp.postService.deletePost(post.id).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                apiPostList()
                mainView.onPostDeleteSuccess(response.body())
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                mainView.onPostDeleteFailure(t.message.toString())
            }
        })
    }

}