package org.newsapi

import android.content.Context
import android.telephony.TelephonyManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.newsapi.api.COUNTRY_IN
import java.util.*

fun ImageView.load(url: String?, errorRes: Int = R.drawable.dummy_image) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .error(errorRes)
        .into(this)
}

fun getCountry(context: Context?): String {
    try {
        val tm = context?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val simCountry = tm.simCountryIso
        if (simCountry != null && simCountry.length == 2) { // SIM country code is available
            return simCountry.toLowerCase(Locale.US)
        }
        else if (tm.phoneType != TelephonyManager.PHONE_TYPE_CDMA) { // Device is not 3G (would be unreliable)
            val networkCountry = tm.networkCountryIso
            if (networkCountry != null && networkCountry.length == 2) { // network country code is available
                return networkCountry.toLowerCase(Locale.US)
            }
        }
    }
    catch (e: Exception) {
    }
    return COUNTRY_IN
}