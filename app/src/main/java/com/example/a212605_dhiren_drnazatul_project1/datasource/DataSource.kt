package com.example.a212605_dhiren_drnazatul_project1.datasource

import com.example.a212605_dhiren_drnazatul_project1.model.FlashDeal
import com.example.a212605_dhiren_drnazatul_project1.model.FoodCategory
import com.example.a212605_dhiren_drnazatul_project1.model.FoodDeal

object DataSource {

    val flashDeals = listOf(
        FlashDeal("🍱", "Bento\n-50%"),
        FlashDeal("🍜", "Noodles\n-40%"),
        FlashDeal("🎂", "Cakes\n-60%"),
        FlashDeal("🥗", "Salad\n-30%")
    )

    val categories = listOf(
        FoodCategory("🛍️", "Mystery"),
        FoodCategory("🍽️", "Dine-In"),
        FoodCategory("🥘", "Meals"),
        FoodCategory("🍰", "Dessert")
    )

    val budgetPicks = listOf(
        FoodDeal(
            emoji = "🍽️",
            name = "Weekdays Set Lunch",
            price = "RM 8.90",
            oldPrice = "RM 18.00",
            discount = "50% off",
            location = "Cafe Mahallah",
            time = "12:00-14:00",
            detail = "Includes rice and protein of your choice.",
            category = "Meals"
        ),
        FoodDeal(
            emoji = "🥗",
            name = "Healthy Box",
            price = "RM 7.50",
            oldPrice = "RM 15.00",
            discount = "50% off",
            location = "GreenBite",
            time = "11:00-14:00",
            detail = "Fresh salad with grilled chicken and dressing.",
            category = "Meals"
        )
    )

    val allDeals = listOf(
        FoodDeal(
            emoji = "🍽️",
            name = "Weekdays Set Lunch",
            price = "RM 8.90",
            oldPrice = "RM 18.00",
            discount = "50% off",
            location = "Cafe Mahallah",
            time = "12:00-14:00",
            detail = "Includes rice and protein of your choice.",
            category = "Meals"
        ),
        FoodDeal(
            emoji = "🥗",
            name = "Healthy Box",
            price = "RM 7.50",
            oldPrice = "RM 15.00",
            discount = "50% off",
            location = "GreenBite",
            time = "11:00-14:00",
            detail = "Fresh salad with grilled chicken and dressing.",
            category = "Meals"
        ),
        FoodDeal(
            emoji = "🍜",
            name = "Laksa Special",
            price = "RM 6.00",
            oldPrice = "RM 10.00",
            discount = "40% off",
            location = "Warung Pak Ali",
            time = "07:00-11:00",
            detail = "Authentic Penang laksa with prawn and pineapple.",
            category = "Dine-In"
        ),
        FoodDeal(
            emoji = "🍱",
            name = "Student Bento Box",
            price = "RM 9.50",
            oldPrice = "RM 16.00",
            discount = "40% off",
            location = "Bento Corner",
            time = "11:30-15:00",
            detail = "Rice, teriyaki chicken, egg, and veggies.",
            category = "Takeaway"
        ),
        FoodDeal(
            emoji = "🥙",
            name = "Kebab Wrap",
            price = "RM 5.50",
            oldPrice = "RM 8.00",
            discount = "31% off",
            location = "Kebab King",
            time = "12:00-22:00",
            detail = "Chicken kebab with garlic sauce and fresh veggies in flatbread.",
            category = "Takeaway"
        ),
        FoodDeal(
            emoji = "🍰",
            name = "Slice of Cake",
            price = "RM 4.00",
            oldPrice = "RM 8.50",
            discount = "53% off",
            location = "Sweet Corner",
            time = "14:00-18:00",
            detail = "Leftover cakes from the day — random flavour, always delicious.",
            category = "Dessert"
        ),
        FoodDeal(
            emoji = "🧋",
            name = "Bubble Tea Bundle",
            price = "RM 5.00",
            oldPrice = "RM 9.00",
            discount = "44% off",
            location = "Tealicious",
            time = "15:00-19:00",
            detail = "Buy 1 get 1 free on selected milk tea flavours.",
            category = "Dessert"
        ),
        FoodDeal(
            emoji = "🍛",
            name = "Nasi Campur",
            price = "RM 5.00",
            oldPrice = "RM 9.00",
            discount = "44% off",
            location = "Restoran Zam Zam",
            time = "11:00-15:00",
            detail = "Rice with 3 lauk choices — fish, chicken, or tofu.",
            category = "Dine-In"
        ),
        FoodDeal(
            emoji = "🥪",
            name = "Sandwich Meal Deal",
            price = "RM 6.90",
            oldPrice = "RM 11.00",
            discount = "37% off",
            location = "SubStation",
            time = "08:00-11:00",
            detail = "6-inch sandwich + drink + cookie combo.",
            category = "Takeaway"
        ),
        FoodDeal(
            emoji = "🍦",
            name = "Ice Cream Cups",
            price = "RM 2.50",
            oldPrice = "RM 5.00",
            discount = "50% off",
            location = "Chill Zone",
            time = "13:00-17:00",
            detail = "Assorted ice cream cups — chocolate, vanilla, strawberry.",
            category = "Dessert"
        )
    )
}
