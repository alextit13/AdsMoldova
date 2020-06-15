package com.auto.autoads.model.db

import com.auto.autoads.model.utils.DataHandler.getCarsMap
import com.auto.autoads.model.utils.DataHandler.getMicrobusMap

object DatabaseEntry {
    fun getCategories() = mutableListOf<String>().apply {
        add("")
        add("Продам")
        add("Куплю")
        add("На запчасти")
    }

    fun getCarMarkas(): MutableList<String> {
        val list = mutableListOf<String>()
        list.add("")
        list.addAll(getCarsMap().keys.toMutableList())
        return list
    }

    fun getMicrobusMarks(): MutableList<String> {
        val list = mutableListOf<String>()
        list.add("")
        list.addAll(getMicrobusMap().keys.toMutableList())
        return list
    }

    fun getMicrobusModels(marka: String): MutableList<String> {
        val list = mutableListOf<String>()
        list.add("")
        list.addAll(getMicrobusMap().get(marka)?.toMutableList() ?: mutableListOf())
        return list
    }

    fun getModels(marka: String): MutableList<String> {
        val list = mutableListOf<String>()
        list.add("")
        list.addAll(getCarsMap().get(marka)?.toMutableList() ?: mutableListOf())
        return list
    }

    fun getCargoMarks() = mutableListOf<String>().apply {
        add("")
        add("Astra")
        add("Avia")
        add("Bremach")
        add("Белаз")
        add("Chevrolet")
        add("Chrysler")
        add("Citroen")
        add("DAF")
        add("Daihatsu")
        add("Dodge")
        add("Faun")
        add("Fiat")
        add("Foden")
        add("Ford")
        add("ГАЗ")
        add("Ginaf")
        add("GMC")
        add("Hino")
        add("Hyundai")
        add("Isuzu")
        add("International")
        add("Iveco")
        add("Камаз")
        add("КРАЗ")
        add("Kassbohrer")
        add("Kia")
        add("Komatsu")
        add("МАЗ")
        add("MAN")
        add("Mazda")
        add("Mega")
        add("Mercedes-Benz")
        add("Mitsubishi")
        add("Multicar")
        add("Nissan")
        add("Opel")
        add("Peugeot")
        add("Plandex")
        add("Renault")
        add("Rolfo")
        add("Roman")
        add("Scam")
        add("Scania")
        add("Seat")
        add("Sisu")
        add("Suzuki")
        add("Tatra")
        add("Terberg")
        add("Toyota")
        add("Trail-Eze")
        add("УАЗ")
        add("Урал")
        add("Volvo")
        add("Volkswagen")
        add("ЗИЛ")
    }

    fun getFuel() = mutableListOf<String>().apply {
        add("")
        add("Бензин")
        add("Дизель")
        add("Газ(Пропан )|Бензин")
        add("Газ(Метан)|Бензин")
        add("Гибрид ")
        add("Электричество")
    }

    fun getcountryRegistration() = mutableListOf<String>().apply {
        add("")
        add("Молдова")
        add("Приднестровье")
        add("Другое")
    }

    fun getTransmissions() = mutableListOf<String>().apply {
        add("")
        add("Механическая")
        add("Автоматическая")
    }

    fun getTypeCargo() = mutableListOf<String>().apply {
        add("")
        add("Автоцистерна")
        add("Автовоз")
        add("Бетоновоз")
        add("Бортовой")
        add("Бортовой с тентом")
        add("Сдвоенная кабина")
        add("Седельный тягач")
        add("Скотовоз")
        add("Контейнеровоз")
        add("Лесовоз")
        add("Молоковоз")
        add("Платформа")
        add("Самосвал")
        add("С краном")
        add("Рефрижератор")
        add("Другое")
    }

    fun getUnitCargo() = mutableListOf<String>().apply {
        add("")
        add("4x2")
        add("4x4")
        add("6x2")
        add("6x4")
        add("6x6")
        add("8x4")
        add("8x6")
        add("8x8")
    }

    fun getBody() = mutableListOf<String>().apply {
        add("")
        add("Внедорожник")
        add("Кабриолет")
        add("Комби")
        add("Кроссовер")
        add("Купе")
        add("Лимузин")
        add("Лифтбек")
        add("Микровэн")
        add("Минивэн")
        add("Пикап")
        add("Родстер")
        add("Седан")
        add("Универсал")
        add("Фургон")
        add("Хэтчбек")
    }

    fun getUnit() = mutableListOf<String>().apply {
        add("")
        add("Передний")
        add("Задний")
        add("Полный")
    }

    fun getWheel() = mutableListOf<String>().apply {
        add("")
        add("Лево")
        add("Право")
    }

    fun getDoors() = mutableListOf<String>().apply {
        add("")
        add("3")
        add("5")
    }

    fun getConsist() = mutableListOf<String>().apply {
        add("")
        add("Новый")
        add("Отличное")
        add("Хорошее")
        add("Удовлетворительное")
        add("Плохое")
        add("Битый авто")
    }

    fun getLocation() = mutableListOf<String>().apply {
        add("")
        add("Тирасполь")
        add("Бендеры")
        add("Григориополь")
        add("Днестровск")
        add("Дубоссары")
        add("Каменка")
        add("Слободзея")
        add("Рыбница")
        add("Другой")
    }

    fun getMotoMarks() = mutableListOf<String>().apply {
        add("")
        add("Adiva")
        add("AGM")
        add("Apollo")
        add("Aprilia")
        add("Arctic Cat")
        add("Bashan")
        add("Benelli")
        add("Beta")
        add("Big Dog Motorcycles")
        add("BMW")
        add("Buell")
        add("Cagiva")
        add("Can-Am")
        add("CF MOTO")
        add("CZ")
        add("Cobra")
        add("Daelim")
        add("Delta")
        add("Deller")
        add("Derbi")
        add("Днепр")
        add("Doohan")
        add("Ducati")
        add("E.V.A.")
        add("EBR")
        add("Energica")
        add("Eglmotor")
        add("Explorer")
        add("Factory Bike")
        add("Falcon")
        add("Forsage")
        add("Fosti")
        add("G-max")
        add("GAS GAS")
        add("Generic")
        add("Gilera")
        add("GX moto")
        add("Harley-Davidson")
        add("HM")
        add("Honda")
        add("Horex")
        add("Husqvarna")
        add("Hyosung")
        add("Indian")
        add("IRBIS")
        add("Iron-Horse")
        add("Italjet")
        add("ИЖ")
        add("Jawa")
        add("JM")
        add("Jonway")
        add("KTM")
        add("Kawasaki")
        add("Kaxa Motors")
        add("Keeway")
        add("Kymco")
        add("Lexmoto")
        add("Lifan")
        add("Linhai")
        add("Longjia")
        add("Malaguti")
        add("MBK")
        add("MBS")
        add("Минск")
        add("ММЗ")
        add("Moto Guzzi")
        add("Nexsus")
        add("Nitro Motors")
        add("Omaks Motors")
        add("OSSA")
        add("Peugeot")
        add("Piaggio")
        add("Polaris")
        add("Qlink")
        add("REX")
        add("Rieju")
        add("Riga")
        add("Royal Enfield")
        add("Ski Doo")
        add("Suzuki")
        add("SYM")
        add("TGB")
        add("Tomoto")
        add("Triumph")
        add("TVS")
        add("Тула")
        add("UM")
        add("УРАЛ")
        add("Восход")
        add("Вятка")
        add("Victory")
        add("Wakan")
        add("Wels")
        add("Xmotos")
        add("Xispa")
        add("Yamaha")
        add("Yamasaki")
        add("YCF")
        add("Zero")
        add("Zipp")
        add("ZNEN")
        add("Другое")
    }

    fun getTypeMoto() = mutableListOf<String>().apply {
        add("")
        add("Вездеход")
        add("Детский")
        add("Дорожный")
        add("Квадроцикл")
        add("Мотоцикл")
        add("Мини мотоцикл	")
        add("Мотороллер/мопед")
        add("Спорт байк")
        add("Кроссовый")
        add("Чоппер")
        add("Другое")
    }

    fun getTypeCooling() = mutableListOf<String>().apply {
        add("")
        add("Воздушное")
        add("Жидкостное")
        add("Гибридный")
        add("Другое")
    }

    fun getTypeEngine() = mutableListOf<String>().apply {
        add("")
        add("Двухтактный")
        add("Четырехтактный")
        add("Другое")
    }

    fun getTypeMicrobus() = mutableListOf<String>().apply {
        add("")
        add("Пассажирский до 3,5 тон")
        add("Пассажирский от 3,5  до 5 тон")
        add("Грузовые до 3,5 тон")
        add("Грузовые от3,5 до 12 тон")
        add("Другое")
    }

    fun getBusMarks() = mutableListOf<String>().apply {
        add("")
        add("AUTOSAN")
        add("BLUE BIRD")
        add("Berkhof")
        add("BMC")
        add("Bova")
        add("Chevrolet")
        add("Chrysler")
        add("Citroen")
        add("Concorde")
        add("DAF")
        add("Drogmoller")
        add("EOS")
        add("Evobus")
        add("Fiat")
        add("Ford")
        add("Freightliner")
        add("ГАЗ")
        add("Golden Dragon")
        add("Hino")
        add("Higer")
        add("Hyundai")
        add("Ikarbus")
        add("Isuzu")
        add("Irisbus")
        add("Iveco")
        add("Icarus")
        add("Jonckheere")
        add("JAC Motors")
        add("Jelcz")
        add("КАвЗ")
        add("Kia")
        add("King")
        add("Karosa")
        add("ЛАЗ")
        add("ЛиАЗ")
        add("MAN")
        add("МАРЗ")
        add("МАЗ")
        add("MCI")
        add("Mercedes-Benz")
        add("Neoplan")
        add("Nissan")
        add("Novabus")
        add("НефАЗ")
        add("ПАЗ")
        add("Scania")
        add("Setra")
        add("Solaris Bus")
        add("Temsa")
        add("Toyota")
        add("Van Hool")
        add("Volvo")
        add("Другое")
    }

    fun getCategoriesServices() = mutableListOf<String>().apply {
        add("")
        add("Авто, мото сервис")
        add("Автоэлектрик")
        add("Авто покраска")
        add("Авторазборка")
        add("Автомойка")
        add("Грузоперевозки")
        add("Пассажироперевозки")
        add("Пригон авто")
        add("Установка газового оборудования")
        add("Установка аудио аппаратуры")
        add("Тюнинг")
        add("Шиномонтаж")
        add("Эвакуатор")
        add("Другие услуги")
    }

    fun getTypeCategory() = mutableListOf<String>().apply {
        add("")
        add("Куплю")
        add("Продам")
    }

    fun getForCarTypes() = mutableListOf<String>().apply {
        add("")
        add("Легковых")
        add("Грузовых")
        add("Автобусов и микроавтобусов")
        add("Мотоциклов")
        add("Велотехники")
        add("Спецтехники")
        add("Другое")
    }
}