package uz.vianet.viewbindingmvp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import uz.vianet.viewbindingmvp.adapter.PostAdapter
import uz.vianet.viewbindingmvp.databinding.ActivityMainBinding
import uz.vianet.viewbindingmvp.model.Post
import uz.vianet.viewbindingmvp.presenter.MainPresenter
import uz.vianet.viewbindingmvp.utils.Utils.toast
import uz.vianet.viewbindingmvp.view.MainView

class MainActivity : AppCompatActivity(), MainView {

    final val TAG = MainActivity::class.java.simpleName

    lateinit var mainPresenter: MainPresenter
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        mainPresenter = MainPresenter(this)

        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        binding.floating.setOnClickListener { openCreateActivity() }

        mainPresenter.apiPostList()
        binding.pbLoading.visibility = View.VISIBLE

        val extras = intent.extras
        if (extras != null) {
            Log.d("@@@Extra",extras.getString("message")!!)
            toast(this, extras.getString("message")!!)
        }
    }

    private fun refreshAdapter(posts: ArrayList<Post>) {
        val adapter = PostAdapter(this, posts)
        binding.recyclerView.setAdapter(adapter)
        binding.pbLoading.visibility = View.GONE
    }
    fun openCreateActivity() {
        val intent = Intent(this@MainActivity, CreateActivity::class.java)
        launchCreateActivity.launch(intent)
    }

    var launchCreateActivity = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            binding.pbLoading.visibility = View.VISIBLE
            mainPresenter.apiPostList()
            Toast.makeText(this@MainActivity, "New Post Created", Toast.LENGTH_LONG).show()

        } else {
            Toast.makeText(this@MainActivity, "Operation canceled", Toast.LENGTH_LONG).show()
        }
    }

    override fun onPostListSuccess(posts: ArrayList<Post>?) {
        refreshAdapter(posts!!)
    }

    override fun onPostListFailure(error: String) {
        Log.d(TAG,error)
        binding.pbLoading.visibility = View.GONE
    }

    override fun onPostDeleteSuccess(posts: Post?) {
        mainPresenter.apiPostList()
    }

    override fun onPostDeleteFailure(error: String) {
        binding.pbLoading.visibility = View.GONE
    }


}