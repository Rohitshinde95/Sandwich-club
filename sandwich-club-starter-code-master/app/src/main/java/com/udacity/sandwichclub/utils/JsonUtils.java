package com.udacity.sandwichclub.utils;

import android.content.Context;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json, Context context) {
        try {
            JSONObject sandwichJsonObject = new JSONObject(json);

            JSONObject name = sandwichJsonObject.getJSONObject(context.getResources().getString(R.string.key_name));
            String mainName = name.getString(context.getResources().getString(R.string.key_mainName));
            JSONArray alsoKnownAsJsonArray = name.getJSONArray(context.getResources().getString(R.string.key_alsoKnownAs));
            List<String> alsoKnownAs = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsJsonArray.length(); i++) {
                alsoKnownAs.add(alsoKnownAsJsonArray.getString(i));
            }
            String placeOfOrigin = sandwichJsonObject.getString(context.getResources().getString(R.string.key_placeOfOrigin));
            String description = sandwichJsonObject.getString(context.getResources().getString(R.string.key_description));
            String image = sandwichJsonObject.getString(context.getResources().getString(R.string.key_image));
            List<String> ingredients = new ArrayList<>();
            JSONArray ingredientsJsonArray = sandwichJsonObject.getJSONArray(context.getResources().getString(R.string.key_ingredients));
            for (int i = 0; i < ingredientsJsonArray.length(); i++) {
                ingredients.add(ingredientsJsonArray.getString(i));
            }
            Sandwich sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
            return sandwich;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
