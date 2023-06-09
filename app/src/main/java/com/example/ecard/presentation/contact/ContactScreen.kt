package com.example.ecard.presentation.contact

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ecard.R
import com.example.ecard.data.model.SimpleUser
import com.example.ecard.presentation.navigation.NavigationDestination
import com.example.ecard.presentation.AppViewModelProvider
import com.example.ecard.presentation.BottomBarEdit
import com.example.ecard.presentation.home.HomeDestination
import com.example.ecard.presentation.home.TopAppBarEdit

object ContactDestination : NavigationDestination {
    override val route = "contact"
    override val titleRes = R.string.contact
}

@Composable
fun ContactScreen(
    contactViewModel: ContactViewModel = viewModel(factory = AppViewModelProvider.Factory),
    currentDestination: NavDestination?,
    navigateTo: (String) -> Unit
) {
    val context = LocalContext.current
    val simpleUserList = contactViewModel.uiState.value.userSimpleList

    val localFocusManager = LocalFocusManager.current

//    val photo = gUser?.photoUrl.toString().replace("s96-c", "s400-c")
    Scaffold(
        topBar = {
            TopAppBarEdit(title = R.string.contact)
        },
        bottomBar = {
            BottomBarEdit(navigateTo = navigateTo, currentDestination = currentDestination)
        }
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            OutlinedTextField(
                value = contactViewModel.uiState.value.searchKey,
                onValueChange = { contactViewModel.onSearchKeyChange(it) },
                placeholder = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.search_contact),
                            style = MaterialTheme.typography.h5.copy(
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                textStyle = MaterialTheme.typography.h5.copy(textAlign = TextAlign.Start),
                shape = MaterialTheme.shapes.large,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = Color.Black,
                    backgroundColor = MaterialTheme.colors.surface,
                    unfocusedBorderColor = MaterialTheme.colors.surface,
                    focusedBorderColor = Color.Gray,
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = null,
                        modifier = Modifier.padding(start = 8.dp),
                        tint = MaterialTheme.colors.onBackground
                    )
                },
                modifier = Modifier
                    .clip(CircleShape)
                    .padding(8.dp)
                    .fillMaxWidth()
                    .pointerInput(Unit) {
                        detectTapGestures(onTap = {
                            localFocusManager.clearFocus()
                        })
                    }

            )

            LazyColumn(
                modifier = Modifier
                    .background(MaterialTheme.colors.background)

            ) {
                items(simpleUserList) { simpleUser ->
                    ContactItem(
                        simpleUser = simpleUser,
                        navigateTo = { navigateTo("${HomeDestination.route}/${simpleUser.userId}") },
                        onDeleteButtonClick = {
                            contactViewModel.onDeleteButtonClick(simpleUser.userId)
                            navigateTo(ContactDestination.route)
                        }
                    )
                }

            }
            Spacer(modifier = Modifier.padding(it))
        }

    }
}

@Composable
fun ContactItem(
    navigateTo: () -> Unit,
    onDeleteButtonClick: () -> Unit,
    simpleUser: SimpleUser,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .padding(8.dp)
            .clickable(onClick = navigateTo),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
//                .background(color = color)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ContactIcon(simpleUser.image)
                ContactInformation(simpleUser.name)
                Spacer(Modifier.weight(1f))
                ContactItemButton(expanded = expanded, onClick = { expanded = !expanded })
            }
            if (expanded) {
                ContactExpended(
                    onAccessButtonClick = navigateTo,
                    onDeleteButtonClick = onDeleteButtonClick,
                    isMe = simpleUser.isMe,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }


    }
}

@Composable
private fun ContactItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            tint = colors.onBackground,
            contentDescription = stringResource(R.string.expand_button_content_description)
        )
    }
}

@Composable
fun ContactIcon(imageUrl: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(imageUrl)
            .build(),
        contentDescription = "",
        contentScale = ContentScale.FillBounds,
        modifier = modifier
            .size(64.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(50)),
    )
}

@Composable
fun ContactInformation(contactName: String, modifier: Modifier = Modifier) {
    Text(
        text = contactName,
        style = MaterialTheme.typography.h5,
        modifier = modifier.padding(start = 8.dp),
    )
}

@Composable
fun ContactExpended(
    onAccessButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit,
    isMe: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = onAccessButtonClick,
            modifier = Modifier
                .width(180.dp)
//                .padding(vertical = 5.dp, horizontal = 10.dp)
        ) {
            Text(
                text = "Xem thông tin",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6
            )
        }
        IconButton(
            onClick = onDeleteButtonClick,
            modifier = Modifier
        ) {
            if (!isMe) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = stringResource(id = R.string.delete)
                )
            }
        }
    }
}

