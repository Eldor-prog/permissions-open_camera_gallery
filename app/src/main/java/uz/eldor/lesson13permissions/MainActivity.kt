package uz.eldor.lesson13permissions

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    private var REQUEST_CODE_CAMERA = 1
    private var REQUEST_CODE_GALLERY = 2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_camera.setOnClickListener {
            checkPermissions(Manifest.permission.CAMERA) {
                openCamera()
            }
        }
        button_gallery.setOnClickListener {
            checkPermissions(Manifest.permission.READ_EXTERNAL_STORAGE) {
                openGallery()
            }
        }

        button_send.setOnClickListener {
            if (edit_text_mail.text.isNotEmpty() && image.drawable != null) {
                Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No Success!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE_CAMERA)
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_CODE_GALLERY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK) {
            val uri = data?.data ?: return
            val ins = contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(ins)
            image.setImageBitmap(bitmap)
        }
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK) {
            val bundle = data?.extras
            val bitmap: Bitmap = bundle?.get("data") as Bitmap
            image.setImageBitmap(bitmap)
        }
    }
}