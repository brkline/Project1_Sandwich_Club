package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String NOT_APPLICABLE = "N/A";

    public static Sandwich parseSandwichJson(String json) {
        JSONObject jsonRootObject = null;
        JSONObject jsonNameObject = null;
        List<String> alsoKnownAs = new ArrayList<>();
        List<String> ingredients = new ArrayList<>();

        JSONArray alsoKnownAsArray = new JSONArray();
        JSONArray ingredientsJsonArray = null;
        Sandwich currentSandwich = new Sandwich();


        try {
            jsonRootObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (!(jsonRootObject == null)) {

            if (jsonRootObject.has("name")) {

                try {
                    jsonNameObject = jsonRootObject.getJSONObject("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (!(jsonNameObject == null)) {
                    if (jsonNameObject.has("mainName")) {
                        try {
                            currentSandwich.setMainName(jsonNameObject.getString("mainName"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    if (jsonNameObject.has("alsoKnownAs")) {
                        try {
                            alsoKnownAsArray = jsonNameObject.getJSONArray("alsoKnownAs");
                            if (alsoKnownAsArray.length() > 0) {
                                for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                                    alsoKnownAs.add(alsoKnownAsArray.getString(i));
                                }
                            } else {
                                alsoKnownAs.add(currentSandwich.getMainName());
                            }
                            currentSandwich.setAlsoKnownAs(alsoKnownAs);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            if (jsonRootObject.has("placeOfOrigin")) {
                try {
                    currentSandwich.setPlaceOfOrigin(jsonRootObject.getString
                            ("placeOfOrigin"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (jsonRootObject.has("description")) {
                try {
                    currentSandwich.setDescription(jsonRootObject.getString
                            ("description"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (jsonRootObject.has("image")) {
                try {
                    currentSandwich.setImage(jsonRootObject.getString
                            ("image"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (jsonRootObject.has("ingredients")) {
                try {
                    ingredientsJsonArray = jsonRootObject.getJSONArray("ingredients");
                    if (ingredientsJsonArray.length() > 0) {
                        for (int i = 0; i < ingredientsJsonArray.length(); i++) {
                            ingredients.add(ingredientsJsonArray.getString(i));
                        }
                        currentSandwich.setIngredients(ingredients);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }

        return currentSandwich;
    }
}
