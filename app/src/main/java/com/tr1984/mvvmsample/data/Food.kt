package com.tr1984.mvvmsample.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "foods")
data class Food(@PrimaryKey(autoGenerate = true) val id: Long, val imageUrl: String, val name: String = "", val isFavorite: Boolean = false, val updatedAt: Long = System.currentTimeMillis()) {

    companion object {
        val dummy = listOf(
            Food(1, "https://cdn.pixabay.com/photo/2016/12/26/17/28/food-1932466__480.jpg", "Pasta", true),
            Food(2, "https://cdn.pixabay.com/photo/2010/12/13/10/05/background-2277__480.jpg", "Blueberry", false),
            Food(3, "https://cdn.pixabay.com/photo/2017/01/31/09/30/raspberry-2023404__480.jpg", "Raspberry", false),
            Food(4, "https://cdn.pixabay.com/photo/2014/04/07/05/25/gummibarchen-318362__480.jpg", "Jelly", true),
            Food(5, "https://cdn.pixabay.com/photo/2017/06/02/18/24/fruit-2367029__480.jpg", "Watermelon", false),
            Food(6, "https://cdn.pixabay.com/photo/2016/07/22/09/59/fruit-1534494__480.jpg", "Pear", true),
            Food(7, "https://cdn.pixabay.com/photo/2017/09/16/19/21/salad-2756467__480.jpg", "Salad", false),
            Food(8, "https://cdn.pixabay.com/photo/2016/04/13/07/18/blueberry-1326154__480.jpg", "Blueberry", true),
            Food(9, "https://cdn.pixabay.com/photo/2017/09/22/19/05/casserole-dish-2776735__480.jpg", "Casserole dish", false),
            Food(10, "https://cdn.pixabay.com/photo/2016/05/24/13/29/olive-oil-1412361__480.jpg", "Olive oil", true),
            Food(11, "https://cdn.pixabay.com/photo/2015/03/26/09/39/cupcakes-690040__480.jpg", "Cupcakes", false),
            Food(12, "https://cdn.pixabay.com/photo/2017/12/10/14/47/piza-3010062__480.jpg", "Pizza", false),
            Food(13, "https://cdn.pixabay.com/photo/2017/11/18/17/09/strawberry-2960533__480.jpg", "Strawberry", false),
            Food(14, "https://cdn.pixabay.com/photo/2015/04/08/13/13/food-712665__480.jpg", "Salmon", false),
            Food(15, "https://cdn.pixabay.com/photo/2014/08/14/14/21/shish-kebab-417994__480.jpg", "Kebab", false),
            Food(16, "https://cdn.pixabay.com/photo/2016/11/30/15/23/apple-1873078__480.jpg", "Apple", false),
            Food(17, "https://cdn.pixabay.com/photo/2017/01/26/02/06/platter-2009590__480.jpg", "Platter", false),
            Food(18, "https://cdn.pixabay.com/photo/2016/09/18/20/25/candy-1678933__480.jpg", "Candy", false),
            Food(19, "https://cdn.pixabay.com/photo/2017/01/04/19/41/caramel-1952997__480.jpg", "Caramel", false),
            Food(20, "https://cdn.pixabay.com/photo/2015/03/14/14/00/carrots-673184__480.jpg", "Carrots", false)
        )
    }
}