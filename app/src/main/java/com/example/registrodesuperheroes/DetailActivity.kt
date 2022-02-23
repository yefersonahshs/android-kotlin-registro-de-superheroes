package com.example.registrodesuperheroes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.registrodesuperheroes.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object{

        const val SUPERHERO_KEY_TOTAL="superhero"
        const val ALTEREGO_KEY="alterego"
        const val BIO_KEY="bio"
        const val RATINBAR_KEY="ratinbar"


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val bundle = intent.extras!!


            val superhero= bundle.getParcelable<Superhero>(SUPERHERO_KEY_TOTAL)!!



            binding.heroName.text=superhero.name
            binding.alterEgoName.text=superhero.alterEgo
            binding.editTextTextMultiLine.text=superhero.bio
            binding.ratingBar.rating=superhero.power
        }



    }


