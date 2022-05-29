package com.midterm.fashionrecommend.Common;

import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.widget.TextView;

import com.midterm.fashionrecommend.Model.CategoryModel;
import com.midterm.fashionrecommend.Model.ClothesModel;
import com.midterm.fashionrecommend.Model.UserModel;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

public class Common {
    public static final String USER_REFERENCES = "Users";
    public static final String POPULAR_CATEGORY_REF = "MostPopular";
    public static final String BEST_DEALS_REF = "BestDeals";
    public static final int DEFAULT_COLUMN_COUNT = 0;
    public static final int FULL_WIDTH_COLUMN = 1;
    public static final String CATEGORY_REF = "Category";
    public static final String COMMENT_REF = "Comments";
    public static final String ORDER_REF = "Order";
    public static UserModel currentUser;
    public static CategoryModel categorySelected;
    public static ClothesModel selectedClothes;
//    public static CategoryModel categorySelected;

    public static String formatPrice(double price) {
        if(price != 0) {
            DecimalFormat df = new DecimalFormat("#,##0.00");
            df.setRoundingMode(RoundingMode.UP);
            String finalPrice = new StringBuilder(df.format(price)).toString();
            return finalPrice.replace(".", ",");
        }else
            return "0.00";
    }


//    public static double calculateExtraPrice(SizeModel userSelectedSize, List<AddonModel> userSelectedAddon) {
//        Double result = 0.0;
//        if(userSelectedSize == null && userSelectedAddon == null)
//            return 0.0;
//        else if(userSelectedSize == null) {
//            //If userSelectedAddon != null, we need sum price
//            for(AddonModel addonModel : userSelectedAddon) {
//                result+=addonModel.getPrice();
//            }
//            return result;
//        } else if (userSelectedAddon == null) {
//            return  userSelectedSize.getPrice()*1.0;
//        } else {
//            //If both size and addon is select
//            result = userSelectedSize.getPrice()*1.0;
//            for(AddonModel addonModel : userSelectedAddon) {
//                result += addonModel.getPrice();
//            }
//            return result;
//        }
//    }

    public static void setSpanString(String welcome, String name, TextView txt_user) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(welcome);
        SpannableString spannableString = new SpannableString(name);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        spannableString.setSpan(boldSpan, 0, name.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(spannableString);
        txt_user.setText(builder, TextView.BufferType.SPANNABLE);
    }

    public static String createOrderNumber() {
        return new StringBuilder()
                .append(System.currentTimeMillis()) //Get current time in millisecond
                .append(Math.abs(new Random().nextInt())) // Add random number to block same order at same time
                .toString();
    }

    public static String getDateOfWeek(int i) {
        switch (i) {
            case 1:
                return "Monday";
            case 2:
                return "Tuesday";
            case 3:
                return "Webnesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            case 6:
                return "Saturday";
            case 7:
                return "Sunday";
            default: return "Unknow";
        }
    }

    public static String convertStatusToText(int orderStatus) {
        switch (orderStatus){
            case 0:
                return "Placed";
            case 2:
                return "Shipping";
            case -1:
                return "Cancelled";
            default:
                return "Unknow";
        }
    }
}
