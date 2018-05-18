package rodrigo.example.com.bitmapfromxmllayout.utils;

import android.app.Activity;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rodrigo.example.com.bitmapfromxmllayout.model.Mention;

/**
 * MentionUtils.java.
 *
 * @author Rodrigo Cericatto
 * @since Apr 7, 2016
 */
public class MentionUtils {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    /**
     * Match Strings.
     */

    public static final String MATCHES_USER = "user";
    public static final String MATCHES_USERNAME = "username";
    public static final String MATCHES_FULLNAME = "fullname";
    public static final String MATCHES_PHOTO = "photo";
    public static final String MATCHES_INITIAL_HASHTAG = "hashtag=";

    //--------------------------------------------------
    // Parent Methods
    //--------------------------------------------------

    public static void mentionUser(final Activity context, String title, final TextView textView) {
        if (!StringUtils.isEmpty(title)) {
            Boolean isUserTag = isUserTag(title);
            Boolean isHashtagTag = isHashtagTag(title);
            if (isHashtagTag || isHashtagTag) {
                String parts[] = title.split("\\[");

                // Gets the Mention list.
                List<Mention> mentionList = new ArrayList<>();
                for (int i = 0; i < parts.length; i++) {
                    if (isUserTag(parts[i])) {
                        Mention mention = getMention(parts[i]);
                        mentionList.add(mention);
                    } else if (isHashtagTag(parts[i])) {
                        Mention mention = getMention(parts[i]);
                        mentionList.add(mention);
                    }
                }

                // Gets the new formatted string.
                String mentionString = getMentionString(title);
                textView.setText(mentionString);
                List<Mention> updatedMentionList = getIndexes(mentionString, mentionList);
                setHashtags(context, textView, mentionString, updatedMentionList);
            }
        }
    }

    private static Boolean isUserTag(String text) {
        Boolean containsUserTag = text.contains(MATCHES_USER);
        Boolean containsUsernameTag = text.contains(MATCHES_USERNAME);
        Boolean containsFullnameTag = text.contains(MATCHES_FULLNAME);
        Boolean containsPhotoTag = text.contains(MATCHES_PHOTO);
        Boolean isUserTag = containsUserTag && containsUsernameTag && containsFullnameTag && containsPhotoTag;
        return isUserTag;
    }

    private static Boolean isHashtagTag(String text) {
        Boolean isHashtagTag = text.contains(MATCHES_INITIAL_HASHTAG);
        return isHashtagTag;
    }

    public static Mention getMention(String entry) {
        // Variables.
        Long userId = -1l;
        String userFullname = "";
        String userLogin = "";
        Long hashtagId = -1l;
        String hashtagTitle = "";
        Integer firstIndex = -1;
        Integer lastIndex = -1;

        // Checks the type of the link.
        if (isUserTag(entry)) {
            Object[] values = getUserValues(entry);
            userId = (Long)values[0];
            userFullname = (String)values[1];
            userLogin = (String)values[2];
        } else if (isHashtagTag(entry)) {
            Object[] values = getHashtagValues(entry);
            hashtagId = (Long)values[0];
            hashtagTitle = (String)values[1];
        }
        Mention mention = new Mention(userId, userFullname, userLogin, hashtagId, hashtagTitle, -1, -1);
        return mention;
    }

    private static Object[] getUserValues(String entry) {
        // Raw json.
        String rawJsonParts[] = entry.split("\\]");

        // Getting user id and user full name.
        String parse = rawJsonParts[0];
        String[] fieldsParts = parse.split("\\|");

        // Getting user id.
        parse = fieldsParts[0];
        String userIdParts[] = parse.split(":");
        Long userId = Long.valueOf(userIdParts[1]);

        // Getting user full name and login.
        parse = fieldsParts[2];
        String userFullNameParts[] = parse.split(":");
        String userFullName = userFullNameParts[1];
        String userLogin = rawJsonParts[1];

        // Return.
        Object[] object = new Object[] { userId, userFullName, userLogin };
        return object;
    }

    private static Object[] getHashtagValues(String entry) {
        // Raw json.
        String rawJsonParts[] = entry.split("\\]");

        // Getting hashtag id and title.
        String parse = rawJsonParts[0];
        String[] fieldsParts = parse.split(":");
        Long hashtagId = Long.valueOf(fieldsParts[1]);
        String hashtagTitle = rawJsonParts[1];

        // Return.
        Object[] object = new Object[] { hashtagId, hashtagTitle };
        return object;
    }

    public static String getMentionString(String entry) {
        String mentionString = "";
        String[] parts = entry.split("\\]");
        for (int i = 0; i < parts.length; i++) {
            // Getting mention string.
            String[] fieldParts = parts[i].split("\\[");
            mentionString = mentionString + " " + StringUtils.trimEdges(fieldParts[0]);
        }
        mentionString = StringUtils.trimEdges(mentionString);
        return mentionString;
    }

    //--------------------------------------------------
    // Index Methods
    //--------------------------------------------------

    private static List<Mention> getIndexes(String entry, List<Mention> mentionList) {
        // Removes white spaces.
        entry = StringUtils.trimEdges(entry);

        // Gets the number of occurrences.
        int count = 0;
        for (int i = 0; i < entry.length(); i++) {
            Boolean isArroba = entry.charAt(i) == '@';
            Boolean isHashtag = entry.charAt(i) == '#';
            if (isArroba || isHashtag) {
                count++;
            }
        }

        // Find all occurrences of first indexes.
        int[] firstIndexes = new int[count];
        int j = 0;
        for (int i = 0; i < entry.length(); i++) {
            Boolean isArroba = entry.charAt(i) == '@';
            Boolean isHashtag = entry.charAt(i) == '#';
            if (isArroba || isHashtag) {
                firstIndexes[j++] = i;
            }
        }

        // Find all occurrences of last indexes.
        int[] lastIndexes = new int[count];
        for (int i = 0; i < firstIndexes.length; i++) {
            lastIndexes[i] = getLastIndexes(firstIndexes, entry, i);
        }

        List<Mention> updatedMentionList = updateMentionListIndex(firstIndexes, lastIndexes, entry, mentionList);
        return updatedMentionList;
    }

    private static List<Mention> updateMentionListIndex(int[] firstIndexes, int[] lastIndexes,
        String entry, List<Mention> mentionList) {

        // Check which logins can be links.
        List<Integer> newFirstIndexes = new ArrayList<>();
        List<Integer> newLastIndexes = new ArrayList<>();
        for (int i = 0; i < firstIndexes.length; i++) {
            Integer first = firstIndexes[i];
            Integer last = lastIndexes[i] + 1;
            String substring = entry.substring(first + 1, last);
            for (Mention item : mentionList) {
                String userLogin = item.getUserLogin();

                Boolean containsHashtagLogin = false;
                String hashtagTitle = item.getHashtagTitle();
                if (!StringUtils.isEmpty(hashtagTitle)) {
                    containsHashtagLogin = hashtagTitle.contains(substring);
                }

                Boolean containsUserLogin = userLogin.contains(substring);
                if (containsUserLogin || containsHashtagLogin) {
                    newFirstIndexes.add(firstIndexes[i]);
                    newLastIndexes.add(lastIndexes[i]);
                }
            }
        }

        // Updates Mention list.
        for (int i = 0; i < mentionList.size(); i++) {
            mentionList.get(i).setFirstIndex(newFirstIndexes.get(i));
            mentionList.get(i).setLastIndex(newLastIndexes.get(i) + 1);
        }
        return mentionList;
    }

    private static int getLastIndexes(int[] firstIndexes, String text, int currentIndex) {
        boolean found = false;
        int index = 0;
        for (int i = firstIndexes[currentIndex]; !found; i++) {
            if (text.charAt(i) == ' ') {
                found = true;
                index = i - 1;
            }
            if (i == (text.length() - 1)) {
                found = true;
                index = i;
            }
        }
        return index;
    }

    //--------------------------------------------------
    // Link Methods
    //--------------------------------------------------

    private static void setHashtags(Activity context, TextView textView, String text, List<Mention> mentionList) {
        SpannableString spannable = new SpannableString(text);

        for (int i = 0; i < mentionList.size(); i++) {
            Integer firstIndex = mentionList.get(i).getFirstIndex();
            Integer lastIndex = mentionList.get(i).getLastIndex();
            spannable.setSpan(getClicableSpan(context, i, mentionList), firstIndex, lastIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        textView.setText(spannable);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private static ClickableSpan getClicableSpan(final Activity activity, final Integer index, final List<Mention> mentionList) {
        return new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Mention mention = mentionList.get(index);
                Log.i("Test", "Mention is " + mention.toString() + ".");
            }

            @Override
            public void updateDrawState(TextPaint paint) {
                super.updateDrawState(paint);
                paint.setUnderlineText(false);
                String color = "#FF6699";
                paint.setColor(Color.parseColor(color));
            }
        };
    }
}