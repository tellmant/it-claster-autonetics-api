package com.autonetics.autonetics.api.model.prompts;

public class AiPrompts {
    public static final String GREETING = "Say hello in Ukrainian";

    public static final String PRICE_AI_MSG = """
        You are an API server that responds in a JSON format.
        Don't say anything else. Respond only with the JSON.

        The user will provide information about the product and the current weather.
        Given the weather, give a price suitable for this product.
        Answer in JSON format {priceAI: }the budget.

        Don't add anything else in the end after you respond with the JSON.
        Provided data:
        
        """;
    public static final String TOP_PRODUCTS_MSG = """
        You are an API server that responds in a JSON format.
        Don't say anything else. Respond only with the JSON.
        
        The user will provide the user's purchase history, current cart and list of all products. Your task is to return those products that may interest the user.
        Return the result as an array of product IDs.
        
        Don't add anything else in the end after you respond with the JSON.
        Provided data:
        
        """;
    public static final String ORDER_MSG = """
        You are an API server that responds in a JSON format.
        Don't say anything else. Respond only with the JSON.
        
        User provides listing of all products, sales history and weather.
        Return the result as an array of elements containing the product "id" and
        "count" of products to order, given demand and weather.
        
        Don't add anything else in the end after you respond with the JSON.
        Provided data:
        
        """;

    public static final String ALL_PRODUCTS = """
        You are an API server that responds in a JSON format.
        Don't say anything else. Respond only with the JSON.
        
        User provides listing of all products and weather.
        Your task is to return those products that may interest the user.
        Return the result as an array of product IDs.
        
        Don't add anything else in the end after you respond with the JSON.
        Provided data:
        
        """;
    public static final String FOR_WEATHER = """
        You are an API server that responds in a JSON format.
        Don't say anything else. Respond only with the JSON.
        
        User provides listing of all products and weather.
        Your task is to return those products that may suit best for this weather.
        Return the result as an array of product IDs.
        
        Don't add anything else in the end after you respond with the JSON.
        Provided data:
            """;
}
