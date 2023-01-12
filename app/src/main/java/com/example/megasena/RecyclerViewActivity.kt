package com.example.megasena

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.megasena.databinding.ActivityRecyclerViewBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewBinding
    private lateinit var dao: TaskDAO
    private val adapter = ClassEntryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        iniciaOBanco()
        carregaDoBanco()

        binding.rvItems.layoutManager = LinearLayoutManager(this)
        binding.rvItems.adapter = adapter

        binding.buttonAdd.setOnClickListener {

            val newClass = ClassEntry(0, binding.numerosSorteados.text.toString(), binding.editTextClassDate.text.toString())

//            adapter.addNewClass(newClass)
            dao.insert(newClass)
            carregaDoBanco()
        }

        binding.buttonPlay.setOnClickListener {
            val guardarNumeros = mutableListOf<Int>()

            while (guardarNumeros.size <= 14) {
                val qualquerNome = Random.nextInt(1, 25)
                if (!guardarNumeros.contains(qualquerNome)) {
                    guardarNumeros.add(qualquerNome)
                }
            }
            guardarNumeros.sort()
            binding.numerosSorteados.text = guardarNumeros.toString()


        }

        binding.editTextClassDate.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .build()
            datePicker.addOnPositiveButtonClickListener {dateLong ->
                val date = Date(dateLong)
                val dateFormater = SimpleDateFormat("dd/MM/yyyy")
                val dateText = dateFormater.format(date)

                binding.editTextClassDate.setText(dateText, TextView.BufferType.EDITABLE)

            }
            datePicker.show(supportFragmentManager, "")
        }
    }

    private fun iniciaOBanco(){
        val bancoDeDados = Room.databaseBuilder(
            applicationContext,
            TaskDataBase::class.java,
            "taskdatabase.db"
        ).allowMainThreadQueries().build()

        dao = bancoDeDados.taskDao()
    }

    private fun carregaDoBanco(){
        val todasAsTask = dao.getAll()
        adapter.addNewClass(todasAsTask)

        Log.d("LUCAS", "as tarefas: $todasAsTask")
    }

    private fun qualquerCoisa(){

    }
}