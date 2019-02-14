package com.test.news;

public class MockedObjects {

    private static String mJsonResponse = "{\n" +
            "  \"status\": \"ok\",\n" +
            "  \"totalResults\": 1,\n" +
            "  \"sources\": [\n" +
            "    {\n" +
            "      \"id\": \"abc-news\",\n" +
            "      \"name\": \"ABC News\",\n" +
            "      \"description\": \"Your trusted source for breaking news, analysis, " +
            "exclusive interviews, headlines, and videos at ABCNews.com.\",\n" +
            "      \"url\": \"https://abcnews.go.com\",\n" +
            "      \"category\": \"general\",\n" +
            "      \"language\": \"en\",\n" +
            "      \"country\": \"us\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"articles\": [\n" +
            "    {\n" +
            "      \"source\": {\n" +
            "        \"id\": \"techcrunch\",\n" +
            "        \"name\": \"TechCrunch\"\n" +
            "      },\n" +
            "      \"author\": \"Jon Russell\",\n" +
            "      \"title\": \"Digital Garage teams up with Blockstream to develop " +
            "blockchain financial services in Japan\",\n" +
            "      \"description\": \"The global crypto market may have tanked last " +
            "year, but notable names have joined forces to develop Bitcoin and blockchain " +
            "financial services in Japan, which has emerged as one of the world’s most " +
            "crypto-friendly markets. Blockstream, a blockchain startup fou…\",\n" +
            "      \"url\": \"http://techcrunch.com/2019/01/21/digital-garage-teams-up-with-" +
            "blockstream-to-develop-blockchain-financial-services-in-japan/\",\n" +
            "      \"urlToImage\": \"https://techcrunch.com/wp-content/uploads/2017/03/" +
            "bitcoin-on-gold.png?w=711\",\n" +
            "      \"publishedAt\": \"2019-01-22T06:09:30Z\",\n" +
            "      \"content\": \"The global crypto market may have tanked last year, but " +
            "notable names have joined forces to develop Bitcoin and blockchain financial " +
            "services in Japan, which has emerged as one of the world’s most crypto-friendly " +
            "markets.\\r\\nBlockstream, a blockchain startup fo… [+1970 chars]\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public static String getJsonResponse() {
        return mJsonResponse;
    }

}
