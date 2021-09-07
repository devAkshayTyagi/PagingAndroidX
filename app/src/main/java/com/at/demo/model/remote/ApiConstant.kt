package com.at.demo.model.remote

class ApiConstant {

    companion object {

        /*********API BASE URL************/
        const val BASE_URL = "https://api.flickr.com/services/rest/"

        const val API_TIME_OUT: Long = 6000

        const val API_VIDEO_LIST =
            "?method=flickr.photos.search&" +
                    "api_key=3e7cc266ae2b0e0d78e279ce8e361736&" +
                    "format=json&" +
                    "nojsoncallback=1&" +
                    "safe_search=1&"

    }

}