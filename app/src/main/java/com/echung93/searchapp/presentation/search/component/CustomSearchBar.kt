package com.echung93.searchapp.presentation.search.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import com.echung93.searchapp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchBar(
    modifier: Modifier = Modifier,
    onQuery: () -> Unit,
    active: Boolean = false,
    query: String = "",
    onQueryChange: (String) -> Unit,
    onActiveChange: (Boolean) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val onSearch = {
        keyboardController?.hide()
        onQuery()
    }

    val onClose = {
        keyboardController?.hide()
        onQueryChange("")
        onActiveChange(false)
    }

    SearchBar(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {
            onSearch()
        },
        active = false,
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
                if (active) {
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
        }
    ) {

    }
}