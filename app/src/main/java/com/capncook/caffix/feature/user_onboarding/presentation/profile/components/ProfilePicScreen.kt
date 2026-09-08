package com.capncook.caffix.feature.user_onboarding.presentation.profile.components

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.capncook.caffix.R
import com.capncook.caffix.common.ui_components.theme.LightBrown
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily
import com.capncook.caffix.common.utils.FileUtils
import com.capncook.caffix.feature.user_onboarding.presentation.profile.ProfileNavigationAction
import com.capncook.caffix.feature.user_onboarding.presentation.profile.ProfilePicViewModel
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.capncook.caffix.common.Constants
import com.capncook.caffix.common.ui_components.theme.CoffeeBrown
import java.io.File

@Composable
fun ProfileScreen(
    viewModel: ProfilePicViewModel = hiltViewModel(),
    onNavigateNextFromUpload: () -> Unit,
    onNavigateNextFromSkip: () -> Unit,
    onForceLogout: () -> Unit
) {


    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current


    //We need to remember where we told the camera to save the photo
    var tempCameraUri by remember { mutableStateOf<Uri?>(null) }


    //Gallery Launcher
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        uri?.let { viewModel.onImageSelected(it.toString()) }
    }



    //Camera Launcher
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success: Boolean ->
        if(success) {
            tempCameraUri?.let { viewModel.onImageSelected(it.toString()) }
        }
    }


    //Navigation
    LaunchedEffect(state.navigationAction) {

        when(state.navigationAction) {
            ProfileNavigationAction.NAVIGATE_TO_NEXT_FROM_UPLOAD -> {
                onNavigateNextFromUpload()
                viewModel.onNavigationConsumed()
            }
            ProfileNavigationAction.NAVIGATE_TO_NEXT_FROM_SKIP -> {
                onNavigateNextFromSkip()
                viewModel.onNavigationConsumed()
            }
            ProfileNavigationAction.NAVIGATE_TO_LOGIN -> {
                onForceLogout()
                viewModel.onNavigationConsumed()
            }
            null -> Unit
        }
    }


    //Error
    LaunchedEffect(state.errorMessage) {
        if(state.errorMessage != null){
            Toast.makeText(context, state.errorMessage, Toast.LENGTH_SHORT).show()
            viewModel.clearError()
        }
    }


    val uploadInteractionSource = remember { MutableInteractionSource() }
    val isUploadPressed by uploadInteractionSource.collectIsPressedAsState()

    val cameraInteractionSource = remember { MutableInteractionSource() }
    val isCameraPressed by cameraInteractionSource.collectIsPressedAsState()



    Scaffold(
        topBar = {
            ProfileScreenTopBar(
                currentStep = 1
            )
        },
        bottomBar = {
            ProfileBottomBar(
                isLoading = state.isLoading,
                onNextClick = {

                    //Convert URI TO file and pass to viewmodel
                    val file = state.selectedImageUriString?.let { uriString ->
                        FileUtils.getFileFromUri(context, uriString.toUri())
                    }

                    viewModel.onContinueClick(file)
                },
                onSkipClick = { viewModel.onSkipClick() }
            )
        },
        containerColor = colorResource(R.color.account_bg)

    ) { innerPadding ->


        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .fillMaxSize()
        ) {


            Text(
                text = "Add a profile picture",
                fontFamily = poppinsFontFamily,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center
            )



            Spacer(modifier = Modifier.height(50.dp))



            val imageModifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(180.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Gray.copy(alpha = 0.7f), CircleShape)


            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                if(state.selectedImageUriString != null) {

                    //Newly selected local image
                    AsyncImage(
                        model = state.selectedImageUriString,  // Loads local URI from ViewModel State
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = imageModifier

                    )
                }else if(state.remoteImageUrl != null) {

                    //Previously uploaded image
                    val fullImageUrl = Constants.BASE_URL + state.remoteImageUrl
                    AsyncImage(
                        model = fullImageUrl,
                        contentDescription = "Remote Profile Image",
                        contentScale = ContentScale.Crop,
                        modifier = imageModifier
                    )
                }
                else{

                    Image(
                        painter = painterResource(R.drawable.profile_image),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = imageModifier
                    )
                }

                if(state.isFetchingProfile) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(40.dp),
                        color = CoffeeBrown
                    )
                }
            }





            Spacer(modifier = Modifier.height(80.dp))



            Row(
                modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {


                //Launch Gallery
                Card(
                    modifier = Modifier
                        .size(140.dp)
                        .graphicsLayer(
                            scaleX = if (isUploadPressed) 0.97f else 1f,
                            scaleY = if (isUploadPressed) 0.97f else 1f
                        )
                        .clickable(
                            interactionSource = uploadInteractionSource,
                            indication = null,
                            onClick = {
                                galleryLauncher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            }
                        ),
                    colors = CardDefaults.cardColors(containerColor = colorResource(R.color.account_bg)),
                    shape = RoundedCornerShape(14.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 1.dp
                    )
                ) {


                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {


                        Icon(
                            imageVector = Icons.Default.AddPhotoAlternate,
                            contentDescription = null,
                            modifier = Modifier.size(40.dp),
                            tint = LightBrown
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Upload",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            color = LightBrown,
                            textAlign = TextAlign.Center
                        )

                    }
                }




                //Camera launcher
                Card(
                    modifier = Modifier
                        .size(140.dp)
                        .graphicsLayer(
                            scaleX = if (isCameraPressed) 0.97f else 1f,
                            scaleY = if (isCameraPressed) 0.97f else 1f
                        )
                        .clickable(
                            interactionSource = cameraInteractionSource,
                            indication = null,
                            onClick = {
                                val uri = createTempPictureUri(context)
                                tempCameraUri = uri
                                cameraLauncher.launch(uri)
                            }
                        ),
                    colors = CardDefaults.cardColors(containerColor = colorResource(R.color.account_bg)),
                    shape = RoundedCornerShape(14.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 1.dp
                    )
                ) {


                    Column(modifier = Modifier
                        .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {


                        Icon(
                            imageVector = Icons.Default.CameraAlt,
                            contentDescription = null,
                            modifier = Modifier.size(40.dp),
                            tint = LightBrown
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Camera",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            color = LightBrown,
                            textAlign = TextAlign.Center
                        )

                    }
                }

            }

        }

    }

}



//Helper function to create a secure temporary URI for the Camera to write to.
fun createTempPictureUri(context: Context): Uri {
    val tempFile = File(context.cacheDir, "images")
    if (!tempFile.exists()) tempFile.mkdirs()
    val file = File(tempFile, "temp_camera_${System.currentTimeMillis()}.jpg")


    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider",
        file
    )
}
