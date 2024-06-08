package features.home.components.searchbar

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MusakiSearchBarViewModel : ViewModel() {
    private val _text = MutableStateFlow("")
    private val _interactionSource = MutableStateFlow(MutableInteractionSource())

    val text = _text.asStateFlow()
    val interactionSource = _interactionSource.asStateFlow()
}