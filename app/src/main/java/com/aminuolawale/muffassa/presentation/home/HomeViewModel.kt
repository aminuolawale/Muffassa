package com.aminuolawale.muffassa.presentation.home

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.aminuolawale.muffassa.domain.model.Corpus
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(@ApplicationContext private val context: Context ) : ViewModel() {
    private var _state = MutableStateFlow<List<Corpus>>(emptyList())
    val state = _state.asStateFlow()


    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.NewCorpus -> {
                Toast.makeText(context, "New corpus", Toast.LENGTH_SHORT).show()
            }
        }
    }

}