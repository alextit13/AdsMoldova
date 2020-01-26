package com.auto.autoads.view.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.text.format.DateFormat
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import com.auto.autoads.model.utils.Ad
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import java.util.*

class DetailActivity : AppCompatActivity() {

    companion object {
        var ad: Ad? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setDataInView()
    }

    @SuppressLint("SetTextI18n")
    private fun setDataInView() {
        date.text = "Дата: " + getDate()

        if (ad?.category == "") category.visibility = View.GONE
        category.text = "Категория: " + ad?.category

        if (ad?.marka == "") marka.visibility = View.GONE
        marka.text = "Марка: " + ad?.marka

        if (ad?.model == "") model.visibility = View.GONE
        model.text = "Модель: " + ad?.model

        if (ad?.year == 0) year.visibility = View.GONE
        year.text = "Год: " + ad?.year?.toString()

        if (ad?.typeFuel == "") typeFuels.visibility = View.GONE
        typeFuels.text = "Тип топлива: " + ad?.typeFuel

        if (ad?.engineValue == 0) engineValue.visibility = View.GONE
        engineValue.text = "Объем двигателя: " + ad?.engineValue?.toString() + " " + engineValue.text

        if (ad?.price == 0) price.visibility = View.GONE
        price.text = "Цена: $ " + ad?.price?.toString()

        if (ad?.isNotFixedPrice == null) price_non_fix.visibility = View.GONE
        price_non_fix.text = "Торг: " + if (ad?.isNotFixedPrice == true) "Да" else "Нет"

        if (ad?.registrationCountry == "") registrationCountry.visibility = View.GONE
        registrationCountry.text = "Страна регистрации: " + ad?.registrationCountry

        if (ad?.transmission == "") transmission.visibility = View.GONE
        transmission.text = "Трансмиссия: " + ad?.transmission

        if (ad?.distance == 0) distance.visibility = View.GONE
        distance.text = "Пробег, км: " + ad?.distance?.toString()

        if (ad?.body == "") body.visibility = View.GONE
        body.text = "Тип кузова: " + ad?.body

        if (ad?.color == "") color.visibility = View.GONE
        color.text = "Цвет: " + ad?.color

        if (ad?.unit == "") unit.visibility = View.GONE
        unit.text = "Привод: " + ad?.unit

        if (ad?.wheel == "") wheel.visibility = View.GONE
        wheel.text = "Расположение руля: " + ad?.wheel

        if (ad?.doors == 0) doors.visibility = View.GONE
        doors.text = "Количество дверей: " + ad?.doors

        if (ad?.consist == "") consist.visibility = View.GONE
        consist.text = "Состояние: " + ad?.consist
        // safety.text = "Безопасность: " + ad?.safety

        if (ad?.typeMoto == "") typeMoto.visibility = View.GONE
        typeMoto.text = "Тип мото: " + ad?.typeMoto

        if (ad?.typeCoolingSystem == "") typeCooler.visibility = View.GONE
        typeCooler.text = "Тип охлаждения: " + ad?.typeCoolingSystem

        if (ad?.typeEngine == "") typeEngine.visibility = View.GONE
        typeEngine.text = "Тип двигателя: " + ad?.typeEngine
        // complectation.text = "Комплектация: " + ad?.complectation

        if (ad?.places == 0) places.visibility = View.GONE
        places.text = "Количество мест: " + ad?.places

        if (ad?.location == "") location.visibility = View.GONE
        location.text = "Местоположение: " + ad?.location

        if (ad?.subscription == "") subscription.visibility = View.GONE
        subscription.text = "Описание: " + ad?.subscription

        if (ad?.typeCargo == "") typeCargo.visibility = View.GONE
        typeCargo.text = "Тип кузова: " + ad?.typeCargo

        /*if (ad?.user == "") user.visibility = View.GONE
        user.text = "Пользователь: " + ad?.user*/

        phone_1.text = "Телефон 1: " + ad?.phone_1
        phone_2.text = "Телефон 2: " + ad?.phone_2
        phone_3.text = "Телефон 3: " + ad?.phone_3
        phone_4.text = "Телефон 4: " + ad?.phone_4

        if (ad?.linkImages != null && ad?.linkImages?.isNotEmpty() == true) {
            try {
                Picasso.get().load(ad?.linkImages?.get(0)).into(iv_1)
                Picasso.get().load(ad?.linkImages?.get(1)).into(iv_2)
                Picasso.get().load(ad?.linkImages?.get(2)).into(iv_3)
                Picasso.get().load(ad?.linkImages?.get(3)).into(iv_4)
                Picasso.get().load(ad?.linkImages?.get(4)).into(iv_5)
            } catch (e: Exception) {

            }
        } else {
            hsv.visibility = View.GONE
        }
    }

    private fun getDate(): String {
        val date = Date(ad?.id ?: Date().time)

        return DateFormat.format("dd.MM.yyyy hh:mm", date).toString()
    }

    fun iv_1(view: View) {
        try {
            ImageActivity.urlImage = ad?.linkImages?.get(0) ?: ""
            startActivity(Intent(this, ImageActivity::class.java))
        } catch (e: Exception) {

        }
    }

    fun iv_2(view: View) {
        try {
            ImageActivity.urlImage = ad?.linkImages?.get(1) ?: ""
            startActivity(Intent(this, ImageActivity::class.java))
        } catch (e: Exception) {

        }
    }

    fun iv_3(view: View) {
        try {
            ImageActivity.urlImage = ad?.linkImages?.get(2) ?: ""
            startActivity(Intent(this, ImageActivity::class.java))
        } catch (e: Exception) {

        }
    }

    fun iv_4(view: View) {
        try {
            ImageActivity.urlImage = ad?.linkImages?.get(3) ?: ""
            startActivity(Intent(this, ImageActivity::class.java))
        } catch (e: Exception) {

        }
    }

    fun iv_5(view: View) {
        try {
            ImageActivity.urlImage = ad?.linkImages?.get(4) ?: ""
            startActivity(Intent(this, ImageActivity::class.java))
        } catch (e: Exception) {

        }
    }
}