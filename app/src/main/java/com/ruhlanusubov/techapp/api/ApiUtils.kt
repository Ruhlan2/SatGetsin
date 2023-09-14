package com.ruhlanusubov.techapp.api

import com.ruhlanusubov.techapp.service.Service
import com.ruhlanusubov.techapp.util.BASE_URL

class ApiUtils {
    companion object{
        fun getService(): Service {
            return RetrofitClient.getClient(BASE_URL).create(Service::class.java)
        }
    }
}