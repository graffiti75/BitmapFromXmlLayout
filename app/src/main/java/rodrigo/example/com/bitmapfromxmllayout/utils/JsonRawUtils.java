package rodrigo.example.com.bitmapfromxmllayout.utils;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import rodrigo.example.com.bitmapfromxmllayout.DownloadJsonAsyncTask;
import rodrigo.example.com.bitmapfromxmllayout.model.Category;
import rodrigo.example.com.bitmapfromxmllayout.model.Filters;
import rodrigo.example.com.bitmapfromxmllayout.model.Interest;
import rodrigo.example.com.bitmapfromxmllayout.model.Post;
import rodrigo.example.com.bitmapfromxmllayout.model.PostComment;
import rodrigo.example.com.bitmapfromxmllayout.model.PostMedia;
import rodrigo.example.com.bitmapfromxmllayout.model.SearchFilters;
import rodrigo.example.com.bitmapfromxmllayout.model.SearchResults;
import rodrigo.example.com.bitmapfromxmllayout.model.User;

public class JsonRawUtils {

    //--------------------------------------------------
    // Json Parsing General
    //--------------------------------------------------

    public static void getPostSearchData() {
        // Task.
        DownloadJsonAsyncTask task = new DownloadJsonAsyncTask() {
            @Override
            protected void onPostExecute(String result) {
                // Checks the keys.
                String postsKey = "\"result_type\":\"POST\"";
                String resultsKey = "\"results\":{";
                String filtersKey = "\"filters\":{";

                // Gets all arrays.
                JSONArray jsonArray = null;
                List<Post> postList = new ArrayList<>();
                SearchResults results = null;
                SearchFilters filters = null;
                try {
                    jsonArray = new JSONArray(result);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        // Check which time is the current json item.
                        String jsonObjectString = jsonObject.toString();
                        Boolean containsPostsKey = jsonObjectString.contains(postsKey);
                        Boolean containsResultsKey = jsonObjectString.contains(resultsKey);
                        Boolean containsFiltersKey = jsonObjectString.contains(filtersKey);

                        // Gets the current json item.
                        if (containsPostsKey) {
                            Post post = getPostData(jsonObject);
                            postList.add(post);
                        } else if (containsResultsKey) {
                            results = getResultsData(jsonObject);
                        } else if (containsFiltersKey) {
                            filters = getFiltersData(jsonObject);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        task.execute("http://www.graffiti75.xpg.com.br/Webservice.json");
    }

    //--------------------------------------------------
    // Json Parsing Models
    //--------------------------------------------------

    private static  SearchFilters getFiltersData(JSONObject jsonObject) {
        SearchFilters searchFilters = null;
        try {
            String entry = jsonObject.getString("filters");
            jsonObject = new JSONObject(entry);
            entry = jsonObject.getString("categories");
            JSONArray jsonArray = new JSONArray(entry);
            List<Category> list = getCategoryListData(jsonArray);
            Filters filters = new Filters(list);
            searchFilters = new SearchFilters(filters);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return searchFilters;
    }

    private static List<Category> getCategoryListData(JSONArray jsonArray) {
        String entry = jsonArray.toString();
        List<Category> list = new ArrayList<>();
        String empty = "[[]]";
        if (!StringUtils.isEmpty(entry) && !entry.equals(empty)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Integer id = parseInteger(jsonObject, "id");
                    String name = parseString(jsonObject, "name");
                    String status = parseString(jsonObject, "status");
                    String color = parseString(jsonObject, "color");
                    String createdAt = parseString(jsonObject, "created_at");
                    String updatedAt = parseString(jsonObject, "updated_at");
                    String type = parseString(jsonObject, "type");
                    String iconPath = parseString(jsonObject, "icon_path");
                    Integer results = parseInteger(jsonObject, "results");
                    Category category = new Category(id, name, status, color, createdAt, updatedAt,
                        type, iconPath, results);
                    list.add(category);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else {
            list = null;
        }
        return list;
    }

    private static SearchResults getResultsData(JSONObject jsonObject) {
        SearchResults results;

        Integer total = parseInteger(jsonObject, "total");
        Integer generalListedTotal = parseInteger(jsonObject, "general_listed_total");
        Integer postsTotal = parseInteger(jsonObject, "posts_total");
        Integer profilesTotal = parseInteger(jsonObject, "profiles_total");
        Integer postsListedTotal = parseInteger(jsonObject, "posts_listed_total");
        Integer profilesListedTotal = parseInteger(jsonObject, "profiles_listed_total");

        results = new SearchResults(total, generalListedTotal, postsTotal, profilesTotal,
            postsListedTotal, profilesListedTotal);

        return results;
    }

    private static Post getPostData(JSONObject jsonObject) {
        Post post = null;
        try {
            Long id = parseLong(jsonObject, "id");
            Long userId = parseLong(jsonObject, "user_id");
            Integer categoryId = parseInteger(jsonObject, "category_id");

            String title = parseString(jsonObject, "title");
            String description = parseString(jsonObject, "description");

            String medias = parseString(jsonObject, "medias");
            JSONArray jsonArray = new JSONArray(medias);
            List<PostMedia> mediaList = getPostMediaListData(jsonArray);

            String status = parseString(jsonObject, "status");
            Long popularity = parseLong(jsonObject, "popularity");
            String createdAt = parseString(jsonObject, "created_at");
            String updatedAt = parseString(jsonObject, "updated_at");

            String cityName = parseString(jsonObject, "city_name");
            String stateName = parseString(jsonObject, "state_name");
            String countryName = parseString(jsonObject, "country_name");
            String latitude = parseString(jsonObject, "latitude");
            String longitude = parseString(jsonObject, "longitude");
            String formattedAddress = parseString(jsonObject, "formatted_address");

            String resultType = parseString(jsonObject, "result_type");

            String comments = parseString(jsonObject, "comments");
            List<PostComment> commentList = getPostCommentListData(comments);

            Integer commentTotal = parseInteger(jsonObject, "comments_total");
            Boolean iLike = jsonObject.getBoolean("i_like");
            Integer likesTotal = parseInteger(jsonObject, "likes_total");

            String category = jsonObject.getString("category");
            Interest interest = getInterestData(category);

            String publisher = parseString(jsonObject, "publisher");
            User user = getUserData(publisher);

            Long stateId = parseLong(jsonObject, "state_id");
            Long countryId = parseLong(jsonObject, "country_id");

            post = new Post(id, userId, categoryId, title, description, mediaList, status,
                popularity, createdAt, updatedAt, cityName, stateName, countryName, latitude,
                longitude, formattedAddress, resultType, commentList, commentTotal, iLike,
                likesTotal, interest, user, stateId, countryId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return post;
    }

    private static List<PostMedia> getPostMediaListData(JSONArray jsonArray) {
        String entry = jsonArray.toString();
        List<PostMedia> list = new ArrayList<>();
        String empty = "[[]]";
        if (!StringUtils.isEmpty(entry) && !entry.equals(empty)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String url = parseString(jsonObject, "url");
                    String type = parseString(jsonObject, "type");
                    PostMedia media = new PostMedia(url, type);
                    list.add(media);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else {
            list = null;
        }
        return list;
    }

    private static List<PostComment> getPostCommentListData(String entry) {
        List<PostComment> list = null;
        String empty = "[[]]";
        if (!StringUtils.isEmpty(entry) && !entry.equals(empty)) {
            Type listType = new TypeToken<List<PostComment>>() {}.getType();
            list = new GsonBuilder().create().fromJson(entry, listType);
        } else {
            list = null;
        }
        return list;
    }

    private static Interest getInterestData(String entry) {
        Interest interest = null;
        String empty = "[[]]";
        if (!StringUtils.isEmpty(entry) && !entry.equals(empty)) {
            interest = new GsonBuilder().create().fromJson(entry, Interest.class);
        } else {
            interest = null;
        }
        return interest;
    }

    private static User getUserData(String entry) {
        User user = null;
        if (!StringUtils.isEmpty(entry)) {
            user = new GsonBuilder().create().fromJson(entry, User.class);
        } else {
            user = null;
        }
        return user;
    }

    //--------------------------------------------------
    // Json Parsing Primitives
    //--------------------------------------------------

    private static Integer parseInteger(JSONObject jsonObject, String key) {
        Integer value = 0;
        try {
            value = jsonObject.getInt(key);
            if (value == null) {
                value = 0;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }

    private static Long parseLong(JSONObject jsonObject, String key) {
        Long value = 0l;
        try {
            value = jsonObject.getLong(key);
            if (value == null) {
                value = 0l;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }

    private static String parseString(JSONObject jsonObject, String key) {
        String value = "";
        try {
            value = jsonObject.getString(key);
            if (value == null) {
                value = "";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }
}