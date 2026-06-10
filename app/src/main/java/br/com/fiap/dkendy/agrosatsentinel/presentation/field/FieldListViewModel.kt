package br.com.fiap.dkendy.agrosatsentinel.presentation.field

import androidx.lifecycle.ViewModel
import br.com.fiap.dkendy.agrosatsentinel.domain.model.Field
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine

class FieldListViewModel : ViewModel() {

    private val _fields = MutableStateFlow(mockFields())
    private val _searchQuery = MutableStateFlow("")

    val searchQuery: StateFlow<String> = _searchQuery

    val filteredFields: kotlinx.coroutines.flow.Flow<List<Field>> =
        combine(_fields, _searchQuery) { fields, query ->
            if (query.isBlank()) fields
            else fields.filter {
                it.name.contains(query, ignoreCase = true) ||
                it.crop.contains(query, ignoreCase = true)
            }
        }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun toggleFavorite(fieldId: Int) {
        _fields.value = _fields.value.map { field ->
            if (field.id == fieldId) field.copy(isFavorite = !field.isFavorite) else field
        }
    }

    private fun mockFields(): List<Field> = listOf(
        Field(1, "Talhão Norte", "Soja", -23.45, -46.55, 150.0),
        Field(2, "Talhão Sul", "Milho", -23.60, -46.70, 200.0),
        Field(3, "Talhão Leste", "Cana-de-Açúcar", -23.40, -46.40, 80.0),
        Field(4, "Talhão Oeste", "Café", -23.55, -46.80, 50.0),
        Field(5, "Talhão Central", "Soja", -23.50, -46.60, 120.0),
        Field(6, "Talhão Reserva", "Pastagem", -23.65, -46.65, 300.0)
    )
}
