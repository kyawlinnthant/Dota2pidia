package com.klt.dota2pidia

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.klt.dota2pidia.ui.theme.Dota2pidiaTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Dota2pidiaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun Greeting(name: String) {
    val scope = rememberCoroutineScope()
    val inputStream = LocalContext.current.resources.openRawResource(R.raw.words_dictionary)
    var outputList = emptyFlow<List<String>>()
    LaunchedEffect(key1 = name){
        scope.launch(Dispatchers.IO){
            val inputData = inputStream.bufferedReader().use(BufferedReader::readText)
            val jsonObject = JSONObject(inputData)
            val aaaaa = jsonObject.keys()
            val list = mutableListOf<String>()
            aaaaa.forEachRemaining {
                list.add(it)
            }
            Log.d("data.data.1",list.size.toString())
            Log.d("data.data.2",list.contains("zebra").toString())
            outputList = flow {
                emit(list)
            }
        }
    }
}
