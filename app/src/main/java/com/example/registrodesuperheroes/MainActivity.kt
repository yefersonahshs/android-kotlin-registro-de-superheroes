package com.example.registrodesuperheroes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.registrodesuperheroes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
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


    }

    private fun openDetailActivity(superhero:Superhero) {



        val intent = Intent(this,DetailActivity::class.java)

        intent.putExtra(DetailActivity.SUPERHERO_KEY_TOTAL,superhero)


        startActivity(intent)

    }
}