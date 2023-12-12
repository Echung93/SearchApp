package com.echung93.searchapp.presentation.search.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.echung93.searchapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchBar(
    modifier: Modifier = Modifier,
    active: Boolean = false,
    query: String = "",
    closeButtonVisible: Boolean,
    onQueryChange: (String) -> Unit,
    onActiveChange: (Boolean) -> Unit = {},
    onCloseClicked: () -> Unit,
    onSearchClicked: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val onSearch = {
        keyboardController?.hide()
        onSearchClicked()
    }

    val onClose = {
        keyboardController?.hide()
        onQueryChange("")
        onCloseClicked()
    }

    SearchBar(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .onKeyEvent {
                if (it.key == Key.Enter) {
                    onSearch()
                    true
                } else {
                    false
                }
            },
        query = query,
        onQueryChange =  {
            if(!it.contains("\n")) {
                onQueryChange(it)
            }
        },
        onSearch = { onSearch() },
        active = active,
        onActiveChange = onActiveChange,
        placeholder = {
            Text(text = stringResource(id = R.string.search))
        },
        trailingIcon = {
            Row {
                IconButton(onClick = onSearch) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(id = R.string.search)
                    )
                }
                if (closeButtonVisible) {
                    IconButton(
                        onClick = onClose
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = stringResource(id = R.string.close)
                        )
                    }
                }
            }
        },
        content = {}
    )
}