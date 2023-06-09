package com.example.ecard.presentation.edit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.PhoneInTalk
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ecard.R
import com.example.ecard.data.model.CONST
import com.example.ecard.data.model.Social
import com.example.ecard.data.model.SocialName
import com.example.ecard.data.model.User
import com.example.ecard.presentation.navigation.NavigationDestination
import com.example.ecard.presentation.AppViewModelProvider.Factory
import com.example.ecard.presentation.BottomBarEdit
import com.example.ecard.presentation.home.TopAppBarEdit
import com.example.ecard.app.theme.ECardTheme
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import kotlinx.coroutines.launch
import java.time.LocalDate

object EditDestination : NavigationDestination {
    override val route = "edit"
    override val titleRes = R.string.edit
}

@Composable
fun EditScreen(
    editViewModel: EditViewModel = viewModel(factory = Factory),
    currentDestination: NavDestination?,
    navigateTo: (String) -> Unit
) {
    val uiState = editViewModel.uiState.collectAsState()
    val user = uiState.value

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBarEdit(title = R.string.edit)
        },
        bottomBar = {
            BottomBarEdit(navigateTo = navigateTo, currentDestination = currentDestination)
        }
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item {
                ImageAndName(
                    imageUrl = user.image,
                    description = "",
                    name = user.name,
                    modifier = Modifier
                        .padding(0.dp, 20.dp)
                )

                PersonalInformation(
                    email = user.email,
                    phone = user.phone,
                    birthday = user.birthday,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .clickable {
                            editViewModel.onPickInforItem()
                        }
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                    ) {
                        SocialInforItem(
                            socialImage = painterResource(id = R.drawable.facebook),
                            socialName = stringResource(id = R.string.facebook),
                            modifier = Modifier
                                .width(100.dp)
                                .clickable { editViewModel.onPickSocialItem(0) }
                        )
                        SocialInforItem(
                            socialImage = painterResource(id = R.drawable.instagram),
                            socialName = stringResource(id = R.string.instagram),
                            modifier = Modifier
                                .width(100.dp)
                                .clickable { editViewModel.onPickSocialItem(1) }
                        )
                        SocialInforItem(
                            socialImage = painterResource(id = R.drawable.tiktok),
                            socialName = stringResource(id = R.string.tiktok),
                            modifier = Modifier
                                .width(100.dp)
                                .clickable { editViewModel.onPickSocialItem(2) }
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                    ) {
                        SocialInforItem(
                            socialImage = painterResource(id = R.drawable.youtube),
                            socialName = stringResource(id = R.string.youtube),
                            modifier = Modifier
                                .width(100.dp)
                                .clickable { editViewModel.onPickSocialItem(3) }
                        )
                        SocialInforItem(
                            socialImage = painterResource(id = R.drawable.linkedin),
                            socialName = stringResource(id = R.string.linkedIn),
                            modifier = Modifier
                                .width(100.dp)
                                .clickable { editViewModel.onPickSocialItem(4) }
                        )
                        SocialInforItem(
                            socialImage = painterResource(id = R.drawable.twitter),
                            socialName = stringResource(id = R.string.twitter),
                            modifier = Modifier
                                .width(100.dp)
                                .clickable { editViewModel.onPickSocialItem(5) }
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(it))
            }
        }

        if (editViewModel.isPopUpInforItem.value) {
            Dialog(
                onDismissRequest = { editViewModel.onCancelOrDismissClickInforPopup() },
                properties = DialogProperties(false)
            ) {
                InforItemEditPopup(
                    user = editViewModel.currentPickInfor.value!!,
                    onCancelClick = { editViewModel.onCancelOrDismissClickInforPopup() },
                    onSaveClick = {
                        coroutineScope.launch {
                            editViewModel.onPopUpInforItemSaveClick()
                        }
                    },
                    onNameChange = {
                        editViewModel.onPopUpInforItemNameChange(it)
                    },
                    onPhoneChange = {
                        editViewModel.onPopUpInforItemPhoneChange(it)
                    },
                    onEmailChange = {
                        editViewModel.onPopUpInforItemEmailChange(it)
                    },
                    onBirthdayChange = {
                        editViewModel.onPopUpInforItemBirthdayChange(it)
                    }
                )
            }
        }

        if (editViewModel.isPopUpSocialItem.value) {
            Dialog(
                onDismissRequest = { editViewModel.onCancelOrDismissClickPopup() },
                properties = DialogProperties(false)
            ) {
                SocialInforItemEditPopup(
                    social = editViewModel.currentPickSocial.value!!,
                    onCancelClick = { editViewModel.onCancelOrDismissClickPopup() },
                    onSaveClick = {
                        coroutineScope.launch {
                            editViewModel.onPopUpSocialItemSaveClick()
                        }
                    },
                    onUserNameChange = {
                        editViewModel.onPopUpSocialItemUserNameChange(it)
                    },
                    onLinkChange = {
                        editViewModel.onPickSocialItemLinkChange(it)
                    }
                )
            }
        }
    }
}

@Composable
fun ImageAndName(
    imageUrl: String,
    description: String,
    name: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val imageUrlEdit = imageUrl.replace("s96-c", "s400-c")

        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(imageUrlEdit)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = modifier
                .size(180.dp)
                .clip(RoundedCornerShape(50)),
        )

        Text(
            text = name,
            style = MaterialTheme.typography.h2,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}

@Composable
fun PersonalInformation(
    phone: String,
    email: String,
    birthday: String,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = 5.dp,
        shape = MaterialTheme.shapes.medium,
        backgroundColor = MaterialTheme.colors.surface,
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(vertical = 5.dp)) {
            PersonalInformationItem(
                image = Icons.Outlined.PhoneInTalk, content = phone
            )
            PersonalInformationItem(
                image = Icons.Outlined.Mail, content = email

            )
            PersonalInformationItem(
                image = Icons.Outlined.Event, content = birthday
            )
        }
    }
}

@Composable
fun PersonalInformationItem(image: ImageVector, content: String) {
    val textStyle: TextStyle = MaterialTheme.typography.h4

    Row(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = image,
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .padding(end = 19.dp)
        )
        Text(text = content, style = textStyle)
    }
}

@Composable
fun SocialInforItem(
    socialImage: Painter,
    socialName: String,
//    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = 5.dp,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(5.dp)
        ) {
            Image(
                painter = socialImage,
                contentDescription = socialName,
                modifier = Modifier
                    .size(60.dp)
                    .padding(5.dp)
            )
            Text(
                text = socialName,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 3.dp)
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SocialInforItemEditPopup(
    social: Social,
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit,
    onUserNameChange: (String) -> Unit,
    onLinkChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val image = when (CONST.SOCIAL_TYPE[social.socialTypeId]) {
        SocialName.FACEBOOK.sName -> painterResource(id = R.drawable.facebook)
        SocialName.INSTAGRAM.sName -> painterResource(id = R.drawable.instagram)
        SocialName.LINKEDIN.sName -> painterResource(id = R.drawable.linkedin)
        SocialName.TIKTOK.sName -> painterResource(id = R.drawable.tiktok)
        SocialName.YOUTUBE.sName -> painterResource(id = R.drawable.youtube)
        else -> painterResource(id = R.drawable.twitter)
    }

    Card(
        elevation = 5.dp,
        modifier = modifier.height(IntrinsicSize.Min),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = CONST.SOCIAL_TYPE[social.socialTypeId] as String,
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .padding(vertical = 5.dp)
            )
            Image(
                painter = image,
                contentDescription = CONST.SOCIAL_TYPE[social.socialTypeId] as String?,
                modifier = Modifier
                    .size(110.dp)
                    .padding(0.dp, 10.dp)
            )
            Column(modifier = Modifier.padding(bottom = 10.dp)) {
                OutLinedTextFieldEdit(
                    value = social.userName,
                    placeholderText = stringResource(id = R.string.place_holder_user_name),
                    onValueChange = onUserNameChange,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions()
                )
                OutLinedTextFieldEdit(
                    value = social.link,
                    placeholderText = stringResource(id = R.string.place_holder_links),
                    onValueChange = onLinkChange,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                    ),
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 0.dp)
                    .padding(bottom = 10.dp)
            ) {
                Button(
                    onClick = onCancelClick,
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        style = MaterialTheme.typography.h6,
                    )
                }

                Spacer(modifier = Modifier.width(20.dp))

                Button(
                    onClick = onSaveClick,
                    modifier = Modifier
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.onSecondary
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.save),
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.secondary
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun InforItemEditPopup(
    user: User,
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit,
    onNameChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onBirthdayChange: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {

//    val context = LocalContext.current
//    val keyboardController = LocalSoftwareKeyboardController.current
//    val focusManager = LocalFocusManager.current

    Card(
        elevation = 5.dp,
        modifier = modifier.height(IntrinsicSize.Min),
        shape = MaterialTheme.shapes.large,

        ) {
        val birthdayLocalDate =
            if (user.birthday != null) dateStringToLocalDate(user.birthday!!) else LocalDate.now()

        val selectedDates = remember { mutableStateOf<LocalDate>(birthdayLocalDate) }
//
        val calendarState = rememberUseCaseState()

        CalendarDialog(
            state = calendarState,
            config = CalendarConfig(
                yearSelection = true,
                monthSelection = true,
                style = CalendarStyle.MONTH,
            ),
            selection = CalendarSelection.Date { newDates ->
                selectedDates.value = newDates
                onBirthdayChange(newDates)
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Thông tin cá nhân",
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .padding(vertical = 5.dp)
            )

            Column(modifier = Modifier.padding(bottom = 10.dp)) {
                OutLinedTextFieldEdit(
                    value = user.name ?: "",
                    placeholderText = stringResource(id = R.string.place_holder_user_name),
                    onValueChange = onNameChange,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions()
                )
                OutLinedTextFieldEdit(
                    value = user.phone ?: "",
                    placeholderText = stringResource(id = R.string.place_holder_phone_number),
                    onValueChange = onPhoneChange,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                    ),
                )
                OutLinedTextFieldEdit(
                    value = user.email ?: "",
                    placeholderText = stringResource(id = R.string.place_holder_email),
                    onValueChange = onEmailChange,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions()
                )
                Box(
                    modifier = Modifier
                        .padding(horizontal = 5.dp, vertical = 5.dp)
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colors.surface)
                        .clickable {
                            calendarState.show()
                        },

                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        style = MaterialTheme.typography.h5.copy(textAlign = TextAlign.Center),
                        text = user.birthday ?: "",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 0.dp)
                    .padding(bottom = 10.dp)
            ) {
                Button(
                    onClick = onCancelClick,
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        style = MaterialTheme.typography.h6,
                    )
                }

                Spacer(modifier = Modifier.width(20.dp))

                Button(
                    onClick = onSaveClick,
                    modifier = Modifier
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.onSecondary
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.save),
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.secondary
                    )
                }
            }
        }
    }
}


@Composable
fun OutLinedTextFieldEdit(
    value: String,
    placeholderText: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    readOnly: Boolean = false,
    keyboardActions: KeyboardActions,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        readOnly = readOnly,
        textStyle = MaterialTheme.typography.h5.copy(textAlign = TextAlign.Center),
        shape = MaterialTheme.shapes.medium,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Color.Black,
            unfocusedBorderColor = MaterialTheme.colors.surface,
            focusedBorderColor = Color.Gray,
        ),
        placeholder = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = placeholderText,
                    style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        modifier = modifier
            .padding(horizontal = 5.dp, vertical = 5.dp)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colors.surface)
    )
}

@Preview()
@Composable
fun HomeScreenPreview() {
    ECardTheme() {
        Surface(modifier = Modifier.fillMaxSize()) {
            EditScreen(navigateTo = {}, currentDestination = null)
        }
    }
}