package com.micahnyabuto.swiftshop.ui.home



import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.onPrimaryLight
import com.example.compose.primaryLight
import com.micahnyabuto.swiftshop.R
import com.micahnyabuto.swiftshop.data.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

@Composable
fun ProductItem(
    productViewModel: ProductViewModel,
    product: Product,

    ){
    val navController =rememberNavController()
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }
    var isAddedToCart by remember { mutableStateOf(false) }

    LaunchedEffect(product.image) {
        isLoading =true
        error = null
        try {
        val loadedBitmap = withContext(Dispatchers.IO){
            val connection =URL(product.image).openConnection() as HttpURLConnection
            connection.doInput=true
            connection.connect()
            val inputStream = connection.inputStream
            BitmapFactory.decodeStream(inputStream)
        }
            bitmap =loadedBitmap
        }catch (e: Exception){
            error =e.message
        } finally {
            isLoading =false
        }

    }
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable{
                navController.navigate("productDetails/${product.id}")

            },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface ,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {
            when {
                isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                error != null -> Icon(
                    painter = painterResource(id= R.drawable.baseline_error_24),
                    contentDescription = "Error",
                    modifier = Modifier.align(Alignment.Center)
                )
                bitmap != null -> Image(
                    bitmap = bitmap!!.asImageBitmap(),
                    contentDescription = product.title,
                    contentScale = ContentScale.Crop,
                    modifier =Modifier.fillMaxSize()
                )
            }
        }
            Spacer(modifier =Modifier .height(8.dp))
            Text(
                text=product.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier= Modifier.height(8.dp))
            Button(
                onClick = {
                    productViewModel.addToCart(product)
                    isAddedToCart=true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if(isAddedToCart) Color.Gray else primaryLight,
                    contentColor = onPrimaryLight
                ),
                modifier = Modifier.align(Alignment.End)
            ){
                Text(if (isAddedToCart)"Added to Cart" else "Add to cart")
            }
    }

    }
}


