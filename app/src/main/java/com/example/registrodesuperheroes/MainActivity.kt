package com.example.registrodesuperheroes

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import com.example.registrodesuperheroes.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var heroImage:ImageView
    private var heroBitmap:Bitmap?=null

    private var picturePath=""
    private val getContent = registerForActivityResult(ActivityResultContracts.TakePicture()){
        success ->
        if (success && picturePath.isNotEmpty()){
            heroBitmap= BitmapFactory.decodeFile(picturePath)
            heroImage.setImageBitmap(heroBitmap)

        }
      //  heroImage.setImageBitmap(heroBitmap!!)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener{
            val superheroName=binding.heroNameEdit.text.toString()
            val alterego=binding.alterEgoEdit.text.toString()
            val bio=binding.descriptionEdit.text.toString()
            val ratinbar=binding.ratinBar.rating

            val hero=Superhero(superheroName, alterego, bio, ratinbar)


            openDetailActivity(hero)
        }

        heroImage= binding.heroImage
        binding.heroImage.setOnClickListener{
            openCamera()
    }

    }

    private fun openCamera(){
      /*  val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent,1000)
*/     val file = createImageFile()
       val uri= if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            FileProvider.getUriForFile(this,"$packageName.provider",
                file)

        }else{
            Uri.fromFile(file)
        }

        getContent.launch(uri)

    }

    private fun createImageFile(): File {
        val filename="superhero_image"
        val fileDirectory= getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val file= File.createTempFile(filename,".jpg",fileDirectory)
        picturePath = file.absolutePath
        return file
    }

    private fun openDetailActivity(superhero:Superhero) {

        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra(DetailActivity.SUPERHERO_KEY_TOTAL,superhero)
        intent.putExtra(DetailActivity.BITMAP_KEY,picturePath)
        startActivity(intent)
    }

/*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode==Activity.RESULT_OK && requestCode ==1000) {

            val extras: Bundle? = data?.extras
            val heroBitmap = extras?.getParcelable<Bitmap>("data")
            heroImage.setImageBitmap(heroBitmap)

        }
    }*/
}