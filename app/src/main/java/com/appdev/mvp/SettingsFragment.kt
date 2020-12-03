package com.appdev.mvp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.w3c.dom.Text

class SettingsFragment : Fragment() {
    lateinit var nameView: TextView
    lateinit var departmentView: TextView
    lateinit var remainDayView: TextView
    lateinit var uploadImageView: ImageView

    lateinit var filePath: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        initView(view)
        initListener()

        return view
    }

    private fun initView(view: View) {
        nameView = view.findViewById(R.id.st_name)
        departmentView = view.findViewById(R.id.st_department)
        remainDayView = view.findViewById(R.id.st_remain_day)
        uploadImageView = view.findViewById(R.id.setting_upload_image)
    }

    private fun initListener() {
        val sharedPreferences = activity!!.getSharedPreferences("info", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "null")
        val department = sharedPreferences.getString("position", "null")
        val remainDay = sharedPreferences.getInt("remained_day", 0)
        nameView.text = name
        departmentView.text = department
        remainDayView.text = remainDay.toString() + "일"


        uploadImageView.setOnClickListener {
            getPicture()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            val uri: Uri = data!!.data!!
            filePath = getImageFilePath(uri)
        }
    }

    fun getImageFilePath(contentUri: Uri): String {
        var columnIndex = 0                                          // 절대 경로를 얻기 위한 과정
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = activity!!.contentResolver.query(contentUri, projection, null, null, null)
        if (cursor!!.moveToFirst()) {
            columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        }
        return cursor.getString(columnIndex)
    }

    private fun getPicture() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        intent.type = "image/*"
        startActivityForResult(intent, 1000)
    }
}