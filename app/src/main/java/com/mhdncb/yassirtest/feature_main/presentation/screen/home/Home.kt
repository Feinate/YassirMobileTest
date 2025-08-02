package com.mhdncb.yassirtest.feature_main.presentation.screen.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.mhdncb.yassirtest.core.ui.theme.Black
import com.mhdncb.yassirtest.core.ui.theme.White
import com.mhdncb.yassirtest.core.ui.theme.YassirPurple
import com.mhdncb.yassirtest.feature_main.presentation.component.CharacterCard
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    paddingValues: PaddingValues,
    toCharacterDetails: (id: Int) -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val context = LocalContext.current

    val lazyListState = rememberLazyListState()

    val scope = rememberCoroutineScope()

    val pagingCharacters = viewModel.paging.collectAsLazyPagingItems()

    val firstVisibleItemIndex by remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }

    LaunchedEffect(pagingCharacters.loadState) {
        if (pagingCharacters.loadState.refresh is LoadState.Error) {
            val e = pagingCharacters.loadState.refresh as LoadState.Error
            Toast.makeText(
                context,
                "Error : ${e.error.localizedMessage}",
                Toast.LENGTH_LONG
            ).show()
            e.error.printStackTrace()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Rick and Morty")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Black
                )
            )
        },
        containerColor = White
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            when (pagingCharacters.loadState.refresh) {
                is LoadState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = YassirPurple
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.matchParentSize(),
                        state = lazyListState,
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(
                            start = 16.dp,
                            end = 16.dp,
                            top = 8.dp,
                            bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() + 16.dp
                        ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(
                            count = pagingCharacters.itemCount,
                            key = {
                                pagingCharacters[it]?.id ?: it
                            }
                        ) { index ->
                            val character = pagingCharacters[index]

                            character?.let { characterNotNull ->
                                CharacterCard(
                                    character = characterNotNull,
                                    onClick = toCharacterDetails
                                )
                            }
                            if (index == pagingCharacters.itemCount - 1) {
                                Spacer(modifier = Modifier.padding(bottom = 8.dp))
                            }
                        }
                        item {
                            if (pagingCharacters.loadState.append is LoadState.Loading) {
                                CircularProgressIndicator(color = YassirPurple)
                            }
                        }
                    }
                    if (firstVisibleItemIndex > 0) {
                        FloatingActionButton(
                            onClick = {
                                scope.launch {
                                    lazyListState.animateScrollToItem(0)
                                }
                            },
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.KeyboardArrowUp,
                                contentDescription = "Scroll to top"
                            )
                        }
                    }
                }
            }
        }
    }
}