package uz.vianet.viewbindingmvp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import uz.vianet.viewbindingmvp.databinding.ActivityCreateBinding
import uz.vianet.viewbindingmvp.model.Post
import uz.vianet.viewbindingmvp.presenter.CreatePresenter
import uz.vianet.viewbindingmvp.utils.Utils.toast
import uz.vianet.viewbindingmvp.view.CreateView

class CreateActivity : AppCompatActivity(), CreateView {

    lateinit var binding: ActivityCreateBinding

    lateinit var createPresenter: CreatePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        createPresenter = CreatePresenter(this)

        binding.btnSubmit.setOnClickListener(View.OnClickListener {
            val title: String = binding.etTitle.getText().toString()
            val body: String = binding.etText.getText().toString().trim { it <= ' ' }
            val id_user: String = binding.etUserId.getText().toString().trim { it <= ' ' }

            if (title.isNotEmpty() && body.isNotEmpty() && id_user.isNotEmpty()){
                val post = Post(id_user.toInt(), title, body)
                createPresenter.apiPostCreate(post)
            }

        })
    }

    override fun createPostSuccess(post: Post?) {
        val intent = Intent()
        setResult(RESULT_OK, intent)
        super@CreateActivity.onBackPressed()
    }

    override fun createPostFailure(error: String) {
        toast(this,error)
    }
}