package challenge.feature.restaurants.data

internal const val RAW_RESPONSE_RESTAURANTS_SIZE = 2
internal const val RAW_RESPONSE_RESTAURANT_1_NAME = "Tanoshii Sushi"
internal const val RAW_RESPONSE_RESTAURANT_1_ID = "1"
internal const val RAW_RESPONSE_RESTAURANT_2_NAME = "Tandoori Express"
internal const val RAW_RESPONSE_RESTAURANT_2_ID = "2"
internal const val RAW_RESPONSE = """{
  "restaurants": [
    {
      "id": "$RAW_RESPONSE_RESTAURANT_1_ID",
      "name": "$RAW_RESPONSE_RESTAURANT_1_NAME",
      "status": "open",
      "sortingValues": {
        "bestMatch": 0.0,
        "newest": 96.0,
        "ratingAverage": 4.5,
        "distance": 1190,
        "popularity": 17.0,
        "averageProductPrice": 1536,
        "deliveryCosts": 200,
        "minCost": 1000
      }
    },
    {
      "id": "$RAW_RESPONSE_RESTAURANT_2_ID",
      "name": "$RAW_RESPONSE_RESTAURANT_2_NAME",
      "status": "closed",
      "sortingValues": {
        "bestMatch": 1.0,
        "newest": 266.0,
        "ratingAverage": 4.5,
        "distance": 2308,
        "popularity": 123.0,
        "averageProductPrice": 1146,
        "deliveryCosts": 150,
        "minCost": 1300
      }
    }
  ]
}"""