package com.test.obvioustest.utils

import android.content.Context
import com.test.obvioustest.data.responses.PostData
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

object JsonUtils {
    fun loadJSONFromAsset(context: Context): String? {
        var json: String? = null
        json = try {
            val inputStreams: InputStream = context.getAssets().open("data.json")
            val size: Int = inputStreams.available()
            val buffer = ByteArray(size)
            inputStreams.read(buffer)
            inputStreams.close()
            val charset: Charset = Charsets.UTF_8
            String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    /**
     * Manual data parsing
     * Gson lib can be used for data parsing
     */
    suspend fun getJsonData(context: Context) : List<PostData>{
        val list = ArrayList<PostData>();
        val jsonArray = JSONArray(loadJSONFromAsset(context))
        for (i in 0..jsonArray.length()-1){
            var jsonObject: JSONObject = jsonArray.get(i) as JSONObject;
            var JsonData = PostData(
                if(jsonObject.has("copyright")) jsonObject.getString("copyright") else "NA",
                if(jsonObject.has("date")) jsonObject.getString("date") else "NA",
                if(jsonObject.has("explanation")) jsonObject.getString("explanation") else "NA",
                if(jsonObject.has("hdurl")) jsonObject.getString("hdurl") else "",
                if(jsonObject.has("media_type")) jsonObject.getString("media_type") else "",
                if(jsonObject.has("service_version")) jsonObject.getString("service_version") else "",
                if(jsonObject.has("title")) jsonObject.getString("title") else "NA",
                if(jsonObject.has("url")) jsonObject.getString("url") else ""
            );
            list.add(JsonData)
        }
        return list
    }
}